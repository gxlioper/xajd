<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
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
			if (!checkBoxArr[i].disabled) {
				checkBoxArr[i].checked = true;
			}
		}
	} else {
		for(var i=0;i<checkBoxArr.length;i++){
			checkBoxArr[i].checked = false;
		}
	}
}

function del(url){
	var RowsStr= false;
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr = true;
    	}
	}
	if (!RowsStr){
		alert("��ѡ��Ҫ����ɾ���ļ�¼��");
		return false;
	}
	
	if (confirm('��ȷ��Ҫɾ����ѡ��¼��')) {
		document.forms[0].action=url;
   		 document.forms[0].submit();
	} else {
		return false;
	}
}

function chkAssisOne(url) {
	if (curr_row == null) {
		return false;
	} else {
		url += "&pkVal=";
		url += curr_row.getElementsByTagName("input")[0].value;
		showTopWin(url, 650, 450);
		return true;
	}
}

function add(url){
	return showTopWin(url,650,450);
}

function modi(url){
	if((curr_row == null) || (curr_row == "")){
		alert("��ѡ��Ҫ�޸ĵļ�¼��");
		return false;
	}
	url += "&pkVal=";
	url += curr_row.getElementsByTagName("input")[0].value;
	return showTopWin(url,650,450);
}

function dataExport2() {
	document.forms[0].action = "/xgxt/zzlgdx_rcsw.do?method=zxzmExp";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}

function printZS(mod){
	var url="";
	if(curr_row==null){
		 alert('��ѡ��Ҫ��ӡ�ļ�¼��');
		 return false;
	}
	var xh = curr_row.cells[1].innerText;
	window.open("/xgxt/zzlgdx_rcsw.do?method=xszxzmPrint&xh="+xh);
}
function exePrint(){	
	var xh = "";
	if($("rsTable") && $("rsTable").rows.length > 1){	
		rowOnClick($("tabPri").rows[0]);
		xh = $("rsTable").rows[1].cells[1].innerText;
		window.open("/xgxt/zzlgdx_rcsw.do?method=xszxzmPrint&xh="+xh);			
		$('tempDiv').style.display='none';
		hiddenMessage(true,true);
	 } else{
		$('tempDiv').style.display='none';
		hiddenMessage(true,true);
	    alert("û�пɴ�ӡ�����ݣ�");	   
		return false;
	 }
}
	</script>
		<style media="print">
		.noprint{
			display:none;
		}
		.print{
			display:block;
		}
	</style>
	</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/zzlgdx_rcsw.do?method=zxzmDate" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�ճ����� - ��У֤�� - ��У���ּ��Ļ��̶���Ϣ</a>
				</p>
			</div>
			
			<logic:equal value="yes" name="writeAble">
				<div class="toolbox">
				<div class="buttonbox">	
					<ul>
						<li><a href="#" class="btn_zj" onclick="add('/xgxt/zzlgdx_rcsw.do?method=zxzmEdit');">����</a></li>
						<li><a href="#" class="btn_xg" onclick="modi('/xgxt/zzlgdx_rcsw.do?method=zxzmEdit&type=modi');">�޸�</a></li>
						<li><a href="#" class="btn_sc" onclick="del('/xgxt/zzlgdx_rcsw.do?method=zxzmDate&go=del');">ɾ��</a></li>
						<li><a href="#" class="btn_dr" onclick="impAndChkData()">����</a></li>
						<li><a href="#" class="btn_dc" onclick="dataExport2()">����</a></li>			
						<li> <a href="#" onclick="printZS(1);" class="btn_dy">������ӡ</a> </li>
						<li> <a href="#" onclick=" viewTempDiv('��ʾ��Ϣ','xxtsDiv',350,100);" class="btn_dy">֤������</a> </li>					
					</ul>
				</div>
				</div>
			</logic:equal>
			
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
			
			<div class="searchtab">		
				<table width="100%" class="" border="0">
					<tbody>
						<tr>
							<th>�꼶</th>
							<td><html:select property="nj" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select></td>
							<th>ѧ��</th>
							<td><html:text property="xh" styleId="xh" style="width:80px;inputtext"
								styleClass="inputtext"></html:text></td>
							<th>����</th>
							<td><html:text property="xm" styleId="xm" style="width:50px;inputtext"
								styleClass="inputtext"></html:text></td>
						</tr>
						<tr>
							<th>�Ա�</th>
							<td><html:select property="xb">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="Ů">Ů</html:option>
								</html:select></td>
							<th>���֤��</th>
							<td><html:text property="sfzh" styleId="sfzh" style="width:130px;inputtext" maxlength="18"
								styleClass="inputtext"></html:text>
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<td><html:select property="xydm" onchange="initZyList();initBjList()" styleId="xy" style="width: 150px">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select></td>
							<th>רҵ</th>
							<td><html:select property="zydm" style="width: 150px" onchange="initBjList()" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select></td>
							<th>�༶</th>
							<td><html:select property="bjdm" style="width: 150px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
							<div class="btn">
							<input type="hidden" name="go" value="a" />
							<button type="button" id="search_go" onclick="allNotEmpThenGo('/xgxt/zzlgdx_rcsw.do?method=zxzmDate&go=go')">
								��ѯ
							</button>
							 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
								����
							 </button>
							</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
					<div class="formbox">
						<logic:empty name="rs">
						    <h3 class="datetitle_01">
						    <span>
						    	��ѯ���&nbsp;&nbsp;
									<font color="red">δ�ҵ��κμ�¼��</font> 
						    </span>
						    </h3>
						 </logic:empty>
						<logic:notEmpty name="rs">
							<h3 class="datetitle_01">
								<span>
									��ѯ���&nbsp;&nbsp;<font color="blue">˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font> 
								</span>
							</h3>
							<table width="99%" id="rsTable" class="dateline">
									<thead>
								<tr align="center" style="cursor:hand">
									<td nowrap>
										<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" length="11" scope="request">
										<td id="${tit.en}" onclick="tableSort(this)" nowrap>
											${tit.cn}
										</td>
									</logic:iterate>
								</tr>
							</thead>
								<tbody id="tabPri">
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										ondblclick="chkAssisOne('/xgxt/zzlgdx_rcsw.do?method=zxzmEdit&type=view')"
										style="cursor:hand;">
										<td align=center>
											<input type="checkbox" id="pk" name="pk" 
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="1" length="11">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
								</tbody>
							</table>
							
							<!--��ҳ��ʾ-->
						     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswZzlgdxActionForm"></jsp:include>
							<!--��ҳ��ʾ-->
					</logic:notEmpty>
				<div id="xxtsDiv" style="display:none">
				<table class='formlist'>
					<tbody>
					<tr><td>�˲�����������ӡ��ʾ��ѧ������,ȷ��Ҫ���˲�����</td></tr>
					</tbody>
					<tfoot>
					<tr>
						<td align='center'><button type="button" onclick='exePrint()'>ȷ��</button>
						<button type="button" onclick="hiddenMessage(true,true);">�ر�</button>
						</td>
					</tr>
					</tfoot>
					</table>
			 </div>
					<div id="tempDiv"></div>
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
