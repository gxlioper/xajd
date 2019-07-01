<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<body onload="xyDisabled('xy')">
		<script language="javascript" src="js/function.js"></script>
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

function del(url){
	var RowsStr="!!splitOne!!";
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
    	}
	}
	document.forms[0].pkDel.value = RowsStr;
	
	if (RowsStr=="!!splitOne!!"){
		alert("��ѡ��Ҫ����ɾ���ļ�¼��");
		return false;
	}
	
	if (!confirm("<bean:message key="lable.xsgzyxpzxy" />�û�����ɾ����ͨ��ѧУ��˵����ݣ�ȷ��Ҫɾ����ѡ��¼��")){
		return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function zlsk(url){
	var RowsStr="!!splitOne!!";
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
    	}
	}
	document.forms[0].pkDel.value = RowsStr;
	
	if (RowsStr=="!!splitOne!!"){
		alert("��ѡ��Ҫת����ʷ��ļ�¼��");
		return false;
	}
	
	if (!confirm("ȷ��Ҫ����ѡ��¼ת����ʷ�⣿")){
		return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function tbkn(url){
	var RowsStr="!!splitOne!!";
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
    	}
	}
	document.forms[0].pkDel.value = RowsStr;
	
	if (RowsStr=="!!splitOne!!"){
		alert("��ѡ��Ҫ�����޸�Ϊ�ر����ѵļ�¼��");
		return false;
	}
	
	document.forms[0].action=url;
    document.forms[0].submit();
}

function ybkn(url){
	var RowsStr="!!splitOne!!";
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
    	}
	}
	document.forms[0].pkDel.value = RowsStr;
	
	if (RowsStr=="!!splitOne!!"){
		alert("��ѡ��Ҫ�����޸�Ϊһ�����ѵļ�¼��");
		return false;
	}
	
	document.forms[0].action=url;
    document.forms[0].submit();
}

function ts(url){
	var RowsStr="!!splitOne!!";
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
    	}
	}
	document.forms[0].pkDel.value = RowsStr;
	
	if (RowsStr=="!!splitOne!!"){
		alert("��ѡ��Ҫ�����޸�Ϊ����ļ�¼��");
		return false;
	}
	
	document.forms[0].action=url;
    document.forms[0].submit();
}

function bkn(url){
	var RowsStr="!!splitOne!!";
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
    	}
	}
	document.forms[0].pkDel.value = RowsStr;
	
	if (RowsStr=="!!splitOne!!"){
		alert("��ѡ��Ҫ�����޸�Ϊ��ͨ���ļ�¼��");
		return false;
	}
	
//	if (!confirm("<bean:message key="lable.xsgzyxpzxy" />�û������޸���ͨ��ѧУ��˵����ݣ�ȷ��Ҫ�޸���ѡ��¼��")){
//		return false;
//	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function chec(){
      for(i=0;i<document.getElementsByName("pk").length;i++){
      	document.getElementsByName("pk")[i].checked=document.getElementsByName("qbxz")[0].checked;
      }
}

function chkAssisOne(url, act) {
	if (curr_row == null) {
		return false;
	} else {
		url += "?actDo=";
		url += act;
		url +="&act=";
		url += document.getElementById("act").value;
		url += "&pkVal=";
		url += curr_row.getElementsByTagName("input")[0].value;
		url += "&xh=";
		url += curr_row.getElementsByTagName("input")[2].value;
		url += "&tName=";
		url += document.getElementById("realTable").value;
		if(url.search('assisChkOne')){
			url += "&xydm="+document.getElementById("xy").value;
		}
		showTopWin(url, 750, 550);
		return true;
	}
}

function knssjrd(url) {
	var RowsStr="!!splitOne!!";
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
    	}
	}
	document.forms[0].pkDel.value = RowsStr;
	
	if (RowsStr=="!!splitOne!!"){
		alert("��ѡ��Ҫ�����޸ĵļ�¼��");
		return false;
	}
	url += "?pkVal=";
	url += RowsStr;
	showTopWin(url, 350, 300);
	return true;
}

function add(url){
	return showTopWin(url,750,550);
}

function modi(url){
	if((curr_row == null) || (curr_row == "")){
		alert("��ѡ��Ҫ�޸ĵļ�¼��");
		return false;
	}
	url += "?xh=";
	url += curr_row.getElementsByTagName("input")[2].value;
	return showTopWin(url,750,550);
}

function dataExport2() {
	document.forms[0].action = "/xgxt/expData2.do";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}

		</script>
		<html:form action="/prise_conf_rs" method="post">
	<div id="showDiv" style="display:none" >
			<div class="title">
				<div class="title_img" id="title_m">
					�������϶�ʱ��
				</div>
			</div>
			<table width="98%" align="center" class="tbstyle">
				<tr>
					<td align="center" width="100%">
						<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('rdsj','y-mm-dd');"
								value="<bean:write name='rs1' property="rdsj" />" name="rdsj"
								id="rdsj" />
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="rdsjbc('/xgxt/auditing_shgc_kns_one.do?actDo=knsrdsj')" style="width:80px"
					id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
	</div>
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="isQuery" name="isQuery"
				value="<bean:write name="isQuery" scope="request"/>" />
			<input type="hidden" id="pkDel" name="pkDel" value="" />
			<input type="hidden" id="rsExpString" name="rsExpString"
				value="<bean:write name="rsExpString" scope="request"/>" />
			<input type="hidden" id="colListS" name="colListS"
				value="<bean:write name="colListS" scope="request"/>" />
			<fieldset>
				<legend>
					��ѯ
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<logic:equal name="isQuery" value="is">
							<tr>
								<td align="left">
									�꼶��
									<html:select name="rs1" property="nj" onchange="refreshForm('/xgxt/auditing_shgc_kns.do')">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									ϵ��
									<html:select name="rs1" property="xydm" style="width:150px"
										styleId="xy"
										onchange="refreshForm('/xgxt/auditing_shgc_kns.do')">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									רҵ��
									<html:select name="rs1" property="zydm" style="width:180px"
										styleId="zy"
										onchange="refreshForm('/xgxt/auditing_shgc_kns.do')">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									�༶��
									<html:select name="rs1" property="bjdm" style="width:180px"
										styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
								<td width="10" align="center" valign="middle" rowspan="2">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/auditing_shgc_kns.do')">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
								<td>
									��ȣ�
									<html:select name="rs1" property="nd">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
									<bean:message key="lable.xsgzyxpzxy" />��ˣ�
									<html:select name="rs1" property="xysh" style="width:140px">
										<html:option value=""></html:option>
										<html:option value="δ���">δ���</html:option>
										<html:option value="�����">�����(������ͨ��)</html:option>
										<html:option value="��ͨ��">��ͨ��(�ų���ͨ��)</html:option>
										<html:option value="һ������">һ������</html:option>
										<html:option value="�ر�����">�ر�����</html:option>
										<html:option value="����">����</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
									</html:select>
									ѧУ��ˣ�
									<html:select name="rs1" property="xxsh" style="width:140px">
										<html:option value=""></html:option>
										<html:option value="δ���">δ���</html:option>
										<html:option value="�����">�����(������ͨ��)</html:option>
										<html:option value="��ͨ��">��ͨ��(�ų���ͨ��)</html:option>
										<html:option value="һ������">һ������</html:option>
										<html:option value="�ر�����">�ر�����</html:option>
										<html:option value="����">����</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
									</html:select>
									ѧ�ţ�
									<html:text name="rs1" property="xh" styleId="xh" style="width:80px;inputtext"
									styleClass="inputtext"></html:text>
									�Ƿ���ڣ�
									<html:select name="rs1" property="sfgq">
										<html:option value=""></html:option>
										<html:option value="1">��</html:option>
										<html:option value="0">��</html:option>
									</html:select>
								</td>
							</tr>
						</logic:equal>
						<logic:notEqual name="isQuery" value="is">
							<tr>
								<td align="left">
									�꼶��
									<html:select name="rs1" property="nj" onchange="refreshForm('/xgxt/auditing_shgc_kns.do')">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									ϵ��
									<html:select name="rs1" property="xydm" style="width:150px"
										styleId="xy"
										onchange="refreshForm('/xgxt/auditing_shgc_kns.do')">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									רҵ��
									<html:select name="rs1" property="zydm" style="width:180px"
										styleId="zy"
										onchange="refreshForm('/xgxt/auditing_shgc_kns.do')">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									�༶��
									<html:select name="rs1" property="bjdm" style="width:180px"
										styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
								<td width="10" align="center" valign="middle" rowspan="2">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/auditing_shgc_kns.do')">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
								<td>
									<bean:message key="lable.xsgzyxpzxy" />��ˣ�
									<html:select name="rs1" property="xysh" style="width:140px">
										<html:option value=""></html:option>
										<html:option value="δ���">δ���</html:option>
										<html:option value="�����">�����(������ͨ��)</html:option>
										<html:option value="��ͨ��">��ͨ��(�ų���ͨ��)</html:option>
										<html:option value="һ������">һ������</html:option>
										<html:option value="�ر�����">�ر�����</html:option>
										<html:option value="����">����</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
									</html:select>
									ѧУ��ˣ�
									<html:select name="rs1" property="xxsh" style="width:140px">
										<html:option value=""></html:option>
										<html:option value="δ���">δ���</html:option>
										<html:option value="�����">�����(������ͨ��)</html:option>
										<html:option value="��ͨ��">��ͨ��(�ų���ͨ��)</html:option>
										<html:option value="һ������">һ������</html:option>
										<html:option value="�ر�����">�ر�����</html:option>
										<html:option value="����">����</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
									</html:select>
									ѧ�ţ�
									<html:text name="rs1" property="xh" styleId="xh" style="width:80px;inputtext"
									styleClass="inputtext"></html:text>
									�Ƿ���ڣ�
									<html:select name="rs1" property="sfgq">
										<html:option value=""></html:option>
										<html:option value="1">��</html:option>
										<html:option value="0">��</html:option>
									</html:select>
								</td>
							</tr>
						</logic:notEqual>
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ�������Ըı����״̬��������ͷ��������</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="qbxz" value="all"
										onclick="chec('qbxz')">
								</td>
								<logic:iterate id="tit" name="topTr" offset="3">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap="nowrap">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<logic:equal name="isQuery" value="no">
								<tr onclick="rowOnClick(this)"
									style="cursor:hand;background-color:
    <logic:iterate id="v" name="s" offset="0" length="1">
    <bean:write name="v"/>
    </logic:iterate>
     "
									ondblclick="chkAssisOne('/xgxt/auditing_shgc_kns_one.do','view')">
									<td align="center">
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="checkbox" name="pk"
												value="<bean:write name="v"/>">
										</logic:iterate>
									</td>
									<td align="center">
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="3" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="4">
										<td align="center">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:equal>
							<logic:equal name="isQuery" value="is">
								<tr onclick="rowOnClick(this)"
									style="cursor:hand;background-color:
    <logic:iterate id="v" name="s" offset="0" length="1">
    <bean:write name="v"/>
    </logic:iterate>
     "
									ondblclick="chkAssisOne('/xgxt/shgc_kns.do','view')">
									<td align="center">
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="checkbox" name="pk"
												value="<bean:write name="v"/>">
										</logic:iterate>
									</td>
									<td align="center">
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="3" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="4">
										<td align="center">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:equal>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
			<center>
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<logic:equal name="isQuery" value="is">
						<button type="button" class="button2" onclick="add('/xgxt/shgc_kns.do');"
							style="width:70px">
							�� ��
						</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="modi('/xgxt/shgc_kns.do');"
							style="width:70px">
							�� ��
						</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
					<logic:equal name="isXX" value="is">
						<button type="button" class="button2" onclick="zlsk('/xgxt/auditing_shgc_kns_one.do?actDo=lsk');"
							style="width:70px">
							ת��ʷ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:equal>
					</logic:equal>
					<logic:notEqual name="isQuery" value="is">
					<button type="button" class="button2"
						onclick="tbkn('/xgxt/auditing_shgc_kns_one.do?actDo=tbkn');"
						style="width:70px">
						�ر�����
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="ybkn('/xgxt/auditing_shgc_kns_one.do?actDo=ybkn');"
						style="width:70px">
						һ������
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="ts('/xgxt/auditing_shgc_kns_one.do?actDo=ts');"
						style="width:70px">
						 ��  ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="bkn('/xgxt/auditing_shgc_kns_one.do?actDo=bkn');"
						style="width:70px">
						��ͨ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<logic:equal name="isXX" value="is">
					<button type="button" class="button2"
						onclick="knssjrd('/xgxt/auditing_shgc_kns_knssjrd.do');"
						style="width:70px">
						�϶�ʱ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:equal>
					</logic:notEqual>
					<button type="button" class="button2"
						onclick="del('/xgxt/auditing_shgc_kns_one.do?actDo=del');"
						style="width:70px">
						ɾ ��
					</button>
					<logic:equal name="isXX" value="is">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="impAndChkData();"
						style="width:70px">
						��������
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="dataExport2()" style="width:70px">
						��������
					</button>
					</logic:equal>
				</div>
			</center>
			<div id="tmpdiv"></div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
