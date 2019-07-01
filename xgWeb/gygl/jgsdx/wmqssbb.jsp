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
	<script language="javascript" src="js/function.js"></script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/ahjg_gygl" method="post">
			<div align="center">
				文明寝室报批表
			</div>
			<p></p>
			<div align="center">
				院（系） 级 班 年月日
			</div>
			<table width="100%" class="tbstyle"  align="center">
				<tr>
					<td valign="middle">
						寝室号
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="80px" valign="middle">
						先进事迹
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="80px" valign="middle">
						班主任意见
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="80px" valign="middle">
						<bean:message key="lable.xsgzyxpzxy" />意见
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="80px" valign="middle">
						学校意见
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
			</table>
			<div align="right">
				(此表一式两份,可复印)
			</div>
		</html:form>
	</body>

</html>
