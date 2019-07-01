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
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="xyDisabled('xy');">
		<center>
			<script language="javascript" src="/xgxt/js/function.js"></script>
			<script language="javascript" src="/xgxt/js/jsFunction.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
			<script type="text/javascript" src="/xgxt/js/jxglFunction.js"></script>
<script type="text/javascript">
function sendJsgh() {
	var zgh = replaceChar(curr_row.cells[0].innerText," ","");	
	var url = "";
	if(window.dialogArguments.document.getElementById("url")){
		url=window.dialogArguments.document.getElementById("url").value;
	}
	var str = url + "&zgh="+zgh;		
	window.dialogArguments.document.forms[0].action = str;
	window.dialogArguments.document.forms[0].submit(); 	
	window.close();	
}
</script>
			<html:form action="/xmlgfdyyp" method="post">
				<input type="hidden" id="xyV" name="xyV"  value="" />
				<input type="hidden" id="zyV" name="zyV"  value="" />
				<input type="hidden" id="bjV" name="bjV"  value="" />
				<div class="title">
					<div class="title_img" id="title_m">
						��ǰ����λ�ã�˼������ - ����Ա���� - ����Ա���Ų�ѯ
					</div>
				</div>
				<fieldset>
					<legend>
						�� ѯ
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									���ڲ��ţ�
									<logic:equal name="userType" value="xfgsj">
										<html:hidden property="bmdm"/>
										<html:select property="bmdm" style="width:300px" styleId="bmdm" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="bmList" property="bmdm"
												labelProperty="bmmc" />
										</html:select>	
									</logic:equal>
									<logic:notEqual name="userType" value="xfgsj">
										<html:select property="bmdm" style="width:300px" styleId="bmdm">
											<html:option value=""></html:option>
											<html:options collection="bmList" property="bmdm"
												labelProperty="bmmc" />
										</html:select>	
									</logic:notEqual>	
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px"
										onclick="document.forms[0].go.value='go';refreshForm('/xgxt/xmlgfdyyp.do?method=fdyxxSearch');">
										��ѯ
									</button>
								</td>
							</tr>	
							<tr>
								<td>
									ְ���ţ�
									<html:text property="zgh" style="width:100px"/>					
									&nbsp;������
									<html:text property="xm" style="width:100px"/>
									&nbsp;�Ա�
									<html:select property="xb" styleId="xb">
										<html:option value=""></html:option>
										<html:option value="��">��</html:option>
										<html:option value="Ů">Ů</html:option>
									</html:select>
								</td>
							</tr>						
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
								<font color="blue">��ʾ��������ͷ��������;˫����¼�ɲ鿴��ϸ.</font>
							</legend>
							<table width="100%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="0">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);"
										ondblclick="sendJsgh()"
										style="cursor:hand">
										<td>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v" />" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
					</logic:notEmpty>
			</html:form>
		</center>
	</body>
	<script type="text/javascript" src="js/bottomButton.js"></script>
</html>
