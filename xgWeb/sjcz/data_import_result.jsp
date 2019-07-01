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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">

</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body
		onload="checkWinType();title_m.innerHTML = window.dialogArguments.document.all('title_m').innerHTML+' - 数据导入结果';loadImportInfo();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m"></div>
			</div>
			<logic:empty name="importInfo">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="importInfo">
				<input type="hidden" id="total" name="total"
					value="<bean:write name="total" scope="request"/>" />
				<input type="hidden" id="succInfo" name="succInfo"
					value="<bean:write name="succInfo" scope="request"/>" />
				<input type="hidden" id="failInfo" name="failInfo"
					value="<bean:write name="failInfo" scope="request"/>" />
				<input type="hidden" id="tableName" name="tableName"
					value="<bean:write name="tableName" scope="request"/>" />
				<fieldset>
					<legend>
						数据导入结果
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="center">
								<bean:write name="importInfo" scope="request" />
							</td>
						</tr>
						<tr>
							<td align="left">
								详细信息:
							</td>
						</tr>
						<tr>
							<td align="left">
								<span id="impDetailInfo"></span>
							</td>
						</tr>
						<logic:present name="error_file">
						<tr>
							<td align="left">
								<a target="_blank" href="<bean:write name="error_file"/>"> 错误数据(下载)</a>
							</td>
						</tr>
						</logic:present>
					</table>
					
					<logic:present name="errorList">
					<table style="width:100%" class="tbstyle">
						<logic:iterate id="e" name="errorList">
						<tr>
							<logic:iterate id="t" name="e">
							<td nowrap>
								<bean:write name="t"/>
							</td>
							</logic:iterate>
						</tr>
						</logic:iterate>
					</table>
					</logic:present>
				</fieldset>
				
				<div class="buttontool">
					<button type="button" class="button2" onclick="alert('提交完成!');Close();"
						style="width:80px" id="buttonSave">
						提 交 操 作
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="refreshForm('/xgxt/import_data_commit.do');alert('取消成功!');Close();"
						style="width:80px" id="buttonClose">
						取 消 操 作
					</button>
				</div>
			</logic:notEmpty>
		</html:form>
	</body>
</html>