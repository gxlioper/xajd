<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
	<script language="javascript">
		var Rows=new Array();	//����ѡ�е��ж���
		var ShiftStartRow="";		//Shift��ѡʱ�洢��ʼ�ж���
		var cur_bgc = "#ffdead";//ѡ���б������ַ�����
		
		function rowMoreClick(objTr) {
		
		if (curr_row != null && curr_row.tagName.toLowerCase() == "tr") {
		curr_row.style.backgroundColor = obj_bgc;
		}
	curr_row = objTr;
	obj_bgc = curr_row.style.backgroundColor;
	curr_row.style.backgroundColor = cur_bgc;
		
	iRow=window.event.srcElement;
	do{
		iRow=iRow.parentElement;
	}while(iRow.tagName!='TR')

	//Ctrl��ѡ
	if(event.ctrlKey){
		var j=-1;
		for(i=0;i<Rows.length;i++){
			if(iRow==Rows[i]){
				j=i;
				break;
			}
		}
		if(j!=-1){
			for(i=j;i<Rows.length-1;i++){
				Rows[i]=Rows[i+1];
			}
			Rows.length=Rows.length-1;
			iRow.style.backgroundColor = "#FFFFFF";
		}else{
			Rows[Rows.length]=iRow;
		}
//		ShiftStartRow=iRow;
	}
	else{	
		if (Rows.length!=0){
			for (i=0; i<Rows.length; i++){	
				if (Rows[i].tagName.toLowerCase() == "tr") {
					Rows[i].style.backgroundColor = "#FFFFFF";
	    		}
			}
		}
		Rows.length=1;
		Rows[0]=iRow;
		
//		ShiftStartRow=iRow;
	}
	changeColor(iRow);
}

//ѡ���б�ɫ
function changeColor(E){
	
/*	for(var i=1;i<E.parentElement.rows.length;i++){
		E.parentElement.rows(i).style.backgroundColor=cur_bgc;
	}
*/
	for(i=0;i<Rows.length;i++){
		Rows[i].style.backgroundColor=cur_bgc;	
	}
}

function selectAll(){
	var checkBoxArr = document.getElementsByName("pk");
	var selall = document.getElementById('cb');
	if(selall.checked==true){
		for(var i=0;i<checkBoxArr.length;i++){
			checkBoxArr[i].checked = true;
		}
	} else {
		for(var i=0;i<checkBoxArr.length;i++){
			checkBoxArr[i].checked = false;
		}
	}
}

function chkAssisOne(url) {
	if (curr_row == null) {
		return false;
	} else {
		url += "&pkVal=";
		url += curr_row.getElementsByTagName("input")[0].value;
		showTopWin(url, 900, 600);
		return true;
	}
}

function szgs() {
	window.open("/xgxt/zgdzdx_xszz.do?method=gjzxdkgs", 1000, 1000);
	return true;
}

function zrls(){
	var RowsStr="!!splitOne!!";
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
    	}
	}
	document.forms[0].cbVal.value = RowsStr;
	
	if (RowsStr=="!!splitOne!!"){
		alert("��ѡ��Ҫת����ʷ��ļ�¼��");
		return false;
	}
	if (!confirm("ת����ʷ������ݻ�����ѧ����������ɾ����ȷ��Ҫ����ѡ����ת����ʷ�⣿")){
		return false;
	}
	document.forms[0].action="/xgxt/zgdzdx_xszz.do?method=gjzxdkQuery&go=add";
    document.forms[0].submit();
}

function dyjj(url) {
	if (curr_row == null) {
		alert("��ѡ��Ҫ��ӡ��ݵļ�¼��");
		return false;
	} else {
		url += "&pkVal=";
		url += curr_row.getElementsByTagName("input")[0].value;
		showOpenWindow(url, 370, 220);
		return true;
	}
}

function dataExport2() {
	document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=gjzxdkExp";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
		</script>
</head>
	<body onload="xyDisabled('xy');">
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />
		<html:form action="/zgdzdx_xszz.do?method=gjzxdkQuery" method="post">
			<logic:present name="addO">
				<logic:match value="is" name="addO">
					<script language="javascript">
	         	alert("������ת����ʷ�⣡");
	         	</script>
				</logic:match>
			</logic:present>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }Ϣ</a>
				</p>
			</div>
			<input type="hidden" id="userType" name="userType" 
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<div class="toolbox">
			 <!-- ��ť -->
			 <div class="buttonbox">
			    <ul>
				<li> <a href="#" onclick="szgs();" class="btn_sz"> ���ø�ʽ </a> </li>
			      <li> <a href="#"onclick="dyjj('/xgxt/zgdzdx_xszz.do?method=gjzxdkjjsz');" class="btn_dy"> ��ӡ��� </a> </li>
				<li> <a href="#" onclick="zrls();" class="btn_sx"> ת����ʷ�� </a> </li>
				<li> <a href="#" onclick="impAndChkData()" class="btn_dr"> ���� </a> </li>
				<li> <a href="#" onclick="dataExport2()" class="btn_dc"> ���� </a> </li>
			    </ul>
			 </div>
			<div class="searchtab">
			<table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
		              <input type="hidden" name="go" value="a" />
		              <button type="button" class="btn_cx" id="search_go" 
		              	onclick="allNotEmpThenGo('/xgxt/zgdzdx_xszz.do?method=gjzxdkQuery&go=go')">
		              	�� ѯ
		              </button>
		              &nbsp;&nbsp;&nbsp;&nbsp;
		              <button type="button" class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
		              	�� ��
		              </button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			 <tbody>
						<tr>
							<th align="left">
								�꼶��
							</th>
							<td>
								<html:select property="nj" onchange="initBjList()" style="width:60px;padding-left:80px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<th>
								ѧ�ţ�
							</th>
							<td>
								<html:text property="xh" styleId="xh" style="width:80px;inputtext"
								styleClass="inputtext"></html:text>
							</td>
							<th>
								��ͬ�ţ�
							</th>
							<td>
								<html:text property="hth" styleId="hth" style="width:100px;inputtext"
								styleClass="inputtext"></html:text>
							</td>
						</tr>
						<tr>
							<th align="left" nowrap>
								������
							</th>
							<td>
								<html:text property="xm" styleId="xm" style="width:80px;inputtext"
								styleClass="inputtext"></html:text>
							</td>
							<th>
								���֤�ţ�
							</th>
							<td>
								<html:text property="sfzh" styleId="sfzh" style="width:130px;inputtext"
								styleClass="inputtext"></html:text>
							</td>
							<th>
								����Э��ǩ��ʱ�䣺
							</th>
							<td>
								<input type="text" readonly style="cursor:hand;width:80px"
									onclick="return showCalendar('hkxyqssj','y-mm-dd');"
									value="<bean:write name='rs1' property="hkxyqssj" />" name="hkxyqssj"
									id="hkxyqssj" />
							</td>
						</tr>
						<tr>
							<th  >
								<bean:message key="lable.xsgzyxpzxy" />��
							</th>
							<td>
								<html:select property="xydm" onchange="initZyList();initBjList()" style="width:150px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th>
								רҵ��
							</th>
							<td>
								<html:select property="zydm" onchange="initBjList()" style="width:150px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>
								������ţ�
							</th>
							<td>
								<html:select property="bjdm" style="width:150px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								ʱ�䷶Χ��
							</th>
							<td colspan="5">
								<input type="text" readonly style="cursor:hand;width:80px"
									onclick="return showCalendar('sjfw1','y-mm-dd');"
									value="<bean:write name='rs1' property="sjfw1" />" name="sjfw1"
									id="sjfw1" />&nbsp;��&nbsp;
								<input type="text" readonly style="cursor:hand;width:80px"
									onclick="return showCalendar('sjfw2','y-mm-dd');"
									value="<bean:write name='rs1' property="sjfw2" />" name="sjfw2"
									id="sjfw2" />
							</td>
						</tr>
					</tbody>
				</table>
				</div>
			</div>
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	��ѯ���&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">δ�ҵ��κμ�¼��</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs" >
			 		 	��¼����
								${rsNum}
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
				<logic:notEmpty name="rs">
							<div class="con_overlfow">
							 <table summary="" class="dateline tablenowrap" align="" width="100%">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td>
											<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="2" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)">
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" 
										ondblclick="chkAssisOne('/xgxt/zgdzdx_xszz.do?method=gjzxdkQueryOne')"
										style="cursor:hand;background-color:
									    <logic:iterate id="v" name="s" offset="0" length="1">
									    <bean:write name="v"/>
									    </logic:iterate>
									     ">
										<td align=center><input type="checkbox" id="pk" name="pk" 
										value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>" />
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
									    </td>
										<logic:iterate id="v" name="s" offset="2">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
								</tbody>
							</table>
						</div>
					</logic:notEmpty>
					</div>
					<div id="tmpdiv"></div>
		</html:form>
		 <script type="text/javascript" src="js/bottomButton.js"></script> 
		 <logic:notEmpty name="result">
		 	<logic:equal value="yes" name="result">
		 		<script>
		 			document.getElementById('search_go').click();
		 		</script>
		 	</logic:equal>
		 </logic:notEmpty>
	</body>
</html>
