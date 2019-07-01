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
	<script language="javascript">
</script>
	<body onload="xyDisabled('xy')">
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

function plsh(url){
	var RowsStr="!!splitOne!!";
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
    	}
	}
	document.forms[0].pkDel.value = RowsStr;
	
	if (RowsStr=="!!splitOne!!"){
		alert("��ѡ��Ҫ������˵ļ�¼��");
		return false;
	}
	
	var userType = document.getElementById("userType").value;
	if ("xx" != userType){
		if (!confirm("�¼��û������޸���ͨ���ϼ���˵����ݣ�ȷ��Ҫ�޸���ѡ��¼��")){
			return false;
		}
	}
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
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<fieldset>
				<legend>
					��ѯ
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<logic:equal name="isQuery" value="is">
							<tr>
								<td align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ϵ��
									<html:select name="rs1" property="xydm" style="width:180px"
										styleId="xy"
										 onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;רҵ��
									<html:select name="rs1" property="zydm" style="width:180px"
										styleId="zy"
										 onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�༶��
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
										onclick="allNotEmpThenGo('/xgxt/auditing_zzsf_sydxyzxdk.do')">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ѧ�꣺
									<html:select name="rs1" property="xn" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�꼶��
									<html:select property="nj" onchange="initBjList()" style="width:90px;padding-left:80px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ѧ�ţ�
									<html:text name="rs1" property="xh" styleId="xh" style="width:90px;inputtext"
									styleClass="inputtext"></html:text>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;������
									<html:text name="rs1" property="xm" styleId="xm" style="width:90px;inputtext"
									styleClass="inputtext"></html:text>
								</td>
							</tr>
						</logic:equal>
						<logic:notEqual name="isQuery" value="is">
							<tr>
								<td align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ϵ��
									<html:select name="rs1" property="xydm" style="width:180px"
										styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;רҵ��
									<html:select name="rs1" property="zydm" style="width:180px"
										styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�༶��
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
										onclick="allNotEmpThenGo('/xgxt/auditing_zzsf_sydxyzxdk.do')">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�꼶��
									<html:select property="nj" onchange="initBjList()" style="width:90px;padding-left:80px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ѧ�ţ�
									<html:text name="rs1" property="xh" styleId="xh" style="width:90px;inputtext"
									styleClass="inputtext"></html:text>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;������
									<html:text name="rs1" property="xm" styleId="xm" style="width:90px;inputtext"
									styleClass="inputtext"></html:text>
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
									ondblclick="chkAssisOne('/xgxt/auditing_zzsf_sydxyzxdk_one.do','view')">
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
									ondblclick="chkAssisOne('/xgxt/zzsf_sydxyzxdk.do','view')">
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
						<button type="button" class="button2" onclick="add('/xgxt/zzsf_sydxyzxdk.do');"
							style="width:80px">
							�� ��
						</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="modi('/xgxt/zzsf_sydxyzxdk.do');"
							style="width:80px">
							�� ��
						</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							</logic:equal>
					<logic:notEqual name="isQuery" value="is">
							<button type="button" class="button2" onclick="plsh('/xgxt/auditing_zzsf_sydxyzxdk_one.do?actDo=plsh&jg=tg');"
								style="width:80px">
								ͨ ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="plsh('/xgxt/auditing_zzsf_sydxyzxdk_one.do?actDo=plsh&jg=btg');"
								style="width:80px">
								��ͨ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
					<button type="button" class="button2"
						onclick="del('/xgxt/auditing_zzsf_sydxyzxdk_one.do?actDo=del');"
						style="width:80px">
						ɾ ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="impAndChkData();"
						style="width:80px">
						��������
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="dataExport2()" style="width:80px">
						��������
					</button>
				</div>
			</center>
			<div id="tmpdiv"></div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
