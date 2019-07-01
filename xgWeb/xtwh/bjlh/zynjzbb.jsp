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
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<base target="_self">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<script language="javascript" src="js/commanFunction.js"></script>
	<body onload="initSetZynjzBj();xyDisabled('xy');">
		<script language="javascript">
function getContInfo(){
	if($("zynjzxmList").options.length < 1 || $("zynjzxmList").selectedIndex < 0){
		return false;
	}
	$('zgh').value=$("zynjzxmList").value;
	refreshForm('bjlh_xtwh.do?method=zynjzbb');
}

function addZynjzBj(){
	var zynjzIndx = $("zynjzxmList").selectedIndex;
	var fromIndx = $("bjFlist").selectedIndex;
	if(zynjzIndx < 0){
		alert("��ѡ��˫�����û���");
		return false;
	}
	if(fromIndx < 0){
		return false;
	}
	$("bjTlist").options[$("bjTlist").options.length] = new Option($("bjFlist").options[fromIndx].text,$("bjFlist").options[fromIndx].value);
	$("bjFlist").options[fromIndx] = null;
	if($("bjFlist").options.length > 0){
		$("bjFlist").options[0].selected = true;
		$("delZynjzBjB").disabled = false;
	}else{		
		$("addZynjzBjB").disabled = true;
	}
	if($("bjTlist").options.length > 0){
		$("bjTlist").options[0].selected = true;
	}
}
function delZynjzBj(){
	var zynjzIndx = $("zynjzList").selectedIndex;
	var toIndx = $("bjTlist").selectedIndex;
	if(zynjzIndx < 0){
		alert("��ѡ��˫�����û���");
		return false;
	}
	if(toIndx < 0){
		return false;
	}
	$("bjFlist").options[$("bjFlist").options.length] = new Option($("bjTlist").options[toIndx].text,$("bjTlist").options[toIndx].value);
	$("bjTlist").options[toIndx] = null;
	if($("bjTlist").options.length > 0){
		$("bjTlist").options[0].selected = true;
		$("addZynjzBjB").disabled = false;
	}else{		
		$("delZynjzBjB").disabled = true;
	}	
	if($("bjFlist").options.length > 0){
		$("bjFlist").options[0].selected = true;
	}
}
function saveZynjzBj(){
	setEleDisable("button");
	showTips("���������У���ȴ�......");
	for(var i = 0 ; i < $("bjTlist").length; i++){
		var tmp = document.createElement("input");
		tmp.type = "hidden";
		tmp.name = "bjdm";
		tmp.value = $("bjTlist").options[i].value;
		document.forms[0].appendChild(tmp);
	}
	refreshForm("bjlh_xtwh.do?method=zynjzbb&act=save");
}
function initSetZynjzBj(){		
	if($("bjFlist").options.length > 0){
		$("bjFlist").options[0].selected = true;
	}
	if($("bjTlist").options.length > 0){
		$("bjTlist").options[0].selected = true;
	}
	if($("zgh").value == ""){
		$("addZynjzBjB").disabled = true;
		$("delZynjzBjB").disabled = true;
		$("bjFlist").disabled = true;
		$("bjTlist").disabled = true;
	}
	if($("bjFlist").options.length < 1){
		$("addZynjzBjB").disabled = true;
	}
	if($("bjTlist").options.length < 1){
		$("delZynjzBjB").disabled = true;
	}
}
function dataExport() {
	document.forms[0].action = "/xgxt/expData.do";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
		</script>
		<html:form action="/setPfbz" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ϵͳά�� - Ȩ��ά�� - רҵ�꼶����
				</div>
			</div>
			<fieldset>
				<legend>
					����Ա���
				</legend>
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="session"/>">
				<input type="hidden" id="realTable" name="realTable" value="zynjzjb">
				<input type="hidden" id="tableName" name="tableName"
					value="view_zynjzjb">
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="6">
								�꼶:
								<html:select name="zynjzInfo" property="nj"
									onchange="refreshForm('bjlh_xtwh.do?method=zynjzbb')">
									<option value=""></option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />:
								<html:select name="zynjzInfo" property="xydm" styleId="xy"
									onchange="refreshForm('bjlh_xtwh.do?method=zynjzbb')">
									<option value=""></option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;רҵ��
								<html:select name="zynjzInfo" property="zydm"
									onchange="refreshForm('bjlh_xtwh.do?method=zynjzbb')">
									<option value=""></option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
						</tr>
					</thead>
					<tr>
						<td width="30" rowspan="3" align="center" valign="middle">
							ר
							<br />
							ҵ
							<br />
							��
							<br />
							��
							<br />
							��
						</td>
						<td width="40%" rowspan="3">
							<html:select name="zynjzInfo" property="zynjzxm" size="10" styleId="zynjzxmList"
								style="width:100% " ondblclick="getContInfo()">
								<html:options collection="zynjzList" property="zgh"
									labelProperty="xm" />
							</html:select>
						</td>
						<td align="right" height="30" width="80" nowrap class="tbstyle">
							ְ���ţ�
						</td>
						<td width="100">
							<bean:write name="zynjzInfo" property="zgh" />
							<html:hidden name="zynjzInfo" property="zgh" styleId="zgh" />
						</td>
						<td width="80" align="right" nowrap class="tbstyle">
							������
						</td>
						<td nowrap>
							<bean:write name="zynjzInfo" property="xm" />
						</td>
					</tr>
					<tr>
						<td align="center" height="30" class="tbstyle">
							�������ţ�
						</td>
						<td colspan="3">
							<bean:write name="zynjzInfo" property="bmmc" />
						</td>
					</tr>
					<tr>
						<td colspan="4">
						</td>
					</tr>
					<tr>
						<td align="center" valign="middle">
							<p>
								��
							</p>
							<p>
								��
							</p>
						</td>
						<td>
							<html:select name="zynjzInfo" property="bjmc" styleId="bjFlist"
								ondblclick="addZynjzBj()" size="13" style="width:100% ">
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
						<td nowrap>
							<button type="button" class="button2" style="width:100%" id="addZynjzBjB"
								onclick="addZynjzBj()">
								&gt;&nbsp;&gt;
							</button>
							<br>
							<br>
							<br>
							<button type="button" class="button2" style="width:100%" id="delZynjzBjB"
								onclick="delZynjzBj()">
								&lt;&nbsp;&lt;
							</button>
						</td>
						<td colspan="3">
							����༶��
							<br>
							<html:select name="zynjzInfo" property="bjlist" size="12"
								ondblclick="delZynjzBj()" styleId="bjTlist" style="width:100% ">
								<html:options collection="zynjzBjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
				</table>
			</fieldset>

				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<button type="button" class="button2" onclick="saveZynjzBj()">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="refreshForm('bjlh_xtwh.do?method=zynjzbb')">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="impAndChkData();"
						style="width:80px">
						��������
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="dataExport()" style="width:80px">
						��������
					</button>
				</div>

			<logic:present name="ok" scope="request">
				<logic:equal value="ok" name="ok" scope="request">
					<script>alert("����ɹ���");</script>
				</logic:equal>
				<logic:equal value="no" name="ok" scope="request">
					<script>alert("����ʧ�ܣ������ԣ�");</script>
				</logic:equal>
			</logic:present>
			<script language="javascript" src="js/bottomButton.js"></script>
		</html:form>
	</body>
</html>
