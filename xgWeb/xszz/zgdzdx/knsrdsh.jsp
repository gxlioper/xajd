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

function del(url){
	var RowsStr="!!splitOne!!";
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
    	}
	}
	document.forms[0].cbVal.value = RowsStr;
	
	if (RowsStr=="!!splitOne!!"){
		alert("��ѡ��Ҫ����ɾ���ļ�¼��");
		return false;
	}
	if (!confirm("�¼��û������޸���ͨ���ϼ���˵����ݣ�ȷ��Ҫɾ����ѡ��¼��")){
		return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function plsh(url){
	var RowsStr="!!splitOne!!";
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
    	}
	}
	document.forms[0].cbVal.value = RowsStr;
	
	if (RowsStr=="!!splitOne!!"){
		alert("��ѡ��Ҫ��˵ļ�¼��");
		return false;
	}
	url += "&cbVal=" + RowsStr;
	showTopWin(url, 400, 260);
	return true;
}

function chkAssisOne(url) {
	if (curr_row == null) {
		return false;
	} else {
		url += "&pkVal=";
		url += curr_row.getElementsByTagName("input")[0].value;
		showTopWin(url, 750, 550);
		return true;
	}
}

function add(url){
	return showTopWin(url,750,550);
}

function modi(url){
	if((curr_row == null) || (curr_row == "")){
		alert("��ѡ��Ҫ�޸ĵļ�¼��");
		return false;
	}
	url += "&pkVal=";
	url += curr_row.getElementsByTagName("input")[0].value;
	return showTopWin(url,750,550);
}

function dataExport2() {
	document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=knsrdExp";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
function xnDisabled() {
	var isQuery = document.getElementById('isQuery').value;
	if (isQuery != "is"){
		document.getElementById("xn").disabled = true;
	}
}
		</script>
	</head>
	<body onload="xyDisabled('xy');xnDisabled();">
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />
		<html:form action="/zgdzdx_xszz.do?method=knsrdsh" method="post">
			<div class="tab_cur">
				<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>
				<logic:equal name="isQuery" value="is">
					������ - ��������ѯ - �������϶�
					</logic:equal>
					<logic:notEqual name="isQuery" value="is">
					������ - ��� - �������϶�
					</logic:notEqual>
				</a>
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
			<input type="hidden" id="isQuery" name="isQuery"
				value="<bean:write name="isQuery" scope="request"/>" />
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			 <div class="toolbox">
			 <logic:equal value="yes" name="writeAble" scope="request">
			 <div class="buttonbox">
			    <ul>
			    <logic:equal name="isQuery" value="is">
			    	<li> <a href="#" onclick="add('/xgxt/zgdzdx_xszz.do?method=knsrdsq');" class="btn_zj"> ���� </a> </li>
			    	<li> <a href="#"  onclick="modi('/xgxt/zgdzdx_xszz.do?method=knsrdsq');" class="btn_xg"> �޸� </a> </li>
			    </logic:equal>
			    <logic:notEqual name="isQuery" value="is">
					<logic:equal name="shqx" value="1">
			    	<li> <a href="#" onclick="plsh('/xgxt/zgdzdx_xszz.do?method=knsrdplsh');" class="btn_shtg"> ������� </a> </li> 
			   		</logic:equal>
			    </logic:notEqual>
				<li> <a href="#" onclick="del('/xgxt/zgdzdx_xszz.do?method=knsrdsh&go=del');" class="btn_sc"> ɾ�� </a> </li>
				<logic:notEqual name="isQuery" value="is">
				<li> <a href="#" onclick="impAndChkData();" class="btn_dr"> ���� </a> </li>
				</logic:notEqual>
				<li> <a href="#" onclick="dataExport2()" class="btn_dc"> ���� </a> </li>
			    </ul>
			 </div>
			 </logic:equal>
			<div class="searchtab">
				<table width="100%" border="0">
					<tfoot>
				 	<tr>
				 		<td colspan="6" >
							<input type="hidden" name="go" value="a" />
							<div class="btn">
		             			 <button type="button" class="btn_cx" id="search_go" 
									onclick="allNotEmpThenGo('/xgxt/zgdzdx_xszz.do?method=knsrdsh&go=go')">
									�� ѯ
								 </button>
		                         &nbsp;&nbsp;&nbsp;&nbsp;
		                         <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
		              	           	 �� ��
		                         </button>
		                    </div>
						</td>
					</tr>
					</tfoot>
					<thead>
						<tr>
							<th>
								ѧ��
							</th>
							<td>
								<html:select property="xn" style="width:100px" 
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th>
								�꼶
							</th>
							<td>
								<html:select property="nj" onchange="initBjList()" style="width:60px;padding-left:80px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<th>
								ѧ��
							</th>
							<td>
								<html:text property="xh" styleId="xh" style="width:90px;inputtext"
								styleClass="inputtext"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:select property="xydm" onchange="initZyList();initBjList()" style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th>
								רҵ
							</th>
							<td>
								<html:select property="zydm" onchange="initBjList()" style="width:180px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>
								�༶
							</th>
							<td>
								<html:select property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								
							</td>
						</tr>
						<tr>
							<th>
								�Ƽ�����
							</th>
							<td>
								<html:select name="hs" property="tjdc" style="width:80px">
									<html:option value=""></html:option>
									<html:options collection="shList" property="shmc"
										labelProperty="shmc" />
								</html:select>
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />���
							</th>
							<td>
								<html:select name="hs" property="xysh" style="width:80px">
									<html:option value=""></html:option>
									<html:options collection="shList" property="shmc"
										labelProperty="shmc" />
								</html:select>
							</td>
							<th>
								ѧУ���
							</th>
							<td>
								<html:select name="hs" property="xxsh" style="width:80px">
									<html:option value=""></html:option>
									<html:options collection="shList" property="shmc"
										labelProperty="shmc" />
								</html:select>
							</td>
						</tr>
					</thead>
				</table>
				</div>
			</div>
			<div id="result">
				<div class="searchcontent">
					<div class="formbox">
				    <h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
				    	<logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font> 
				 		 </logic:empty>
				 		 <logic:notEmpty name="rs" >
										<font color="blue"><logic:present name="shsj">������<bean:message key="lable.xsgzyxpzxy" />����������϶���ʱ��Ϊ:<bean:write name="shsj" />�����ڽ�������ˡ�</logic:present>
										��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ���޸ģ�������ͷ��������;</font>
				 		 </logic:notEmpty>
				    </span>
				    </h3>
					<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table summary="" id="rsTable" class="dateline" width="100%">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td nowrap>
											<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="2" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
								<logic:iterate name="rs" id="s">
									<logic:equal name="isQuery" value="is">
									<tr onclick="rowOnClick(this)"
										ondblclick="chkAssisOne('/xgxt/zgdzdx_xszz.do?method=knsrdsq&isQuery=${isQuery }')"
										style="cursor:hand;background-color:
									    <logic:iterate id="v" name="s" offset="0" length="1">
									    <bean:write name="v"/>
									    </logic:iterate>
									     ">
										<td align=center>
											<input type="checkbox" id="pk" name="pk" 
											value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>" /><logic:iterate id="v" name="s" offset="1" length="1"><input type="hidden" value="<bean:write name="v"/>" /></logic:iterate>
									    </td>
										<logic:iterate id="v" name="s" offset="2">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
									</logic:equal>
									<logic:notEqual name="isQuery" value="is">
									<tr onclick="rowOnClick(this)"
										ondblclick="chkAssisOne('/xgxt/zgdzdx_xszz.do?method=knsrdshOne&isQuery=${isQuery }')"
										style="cursor:hand;background-color:
									    <logic:iterate id="v" name="s" offset="0" length="1">
									    <bean:write name="v"/>
									    </logic:iterate>
									     ">
										<td align=center><input type="checkbox" id="pk" name="pk" 
										value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>" /><logic:iterate id="v" name="s" offset="1" length="1"><input type="hidden" value="<bean:write name="v"/>" /></logic:iterate>
									    </td>
										<logic:iterate id="v" name="s" offset="2">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
									</logic:notEqual>
								</logic:iterate>
								</tbody>
							</table>
						</div>
							<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=xszzZgdzdxActionForm"></jsp:include>
					</logic:notEmpty>
					
					<div id="tmpdiv"></div>
				</div>
			</div>
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
