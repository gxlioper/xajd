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
<html:html locale="true">
<head>

	<title>wjcf_shgc_xswjcfqkb.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="Copyright" content="正方软件 zfsoft" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<link id="csssDate" rel="stylesheet" rev="stylesheet"
	href="js/calendar.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/shgc/td.css" type="text/css" media="all" />
<body>
	<script language="javascript" src="js/function.js"></script>

	<p align="center">
		<font size="5"><strong>关于&nbsp;&nbsp;&nbsp;<logic:present name="rs"><bean:write name="rs" property="xm"/></logic:present>&nbsp;&nbsp;&nbsp;同学处分后表现的主题班会会议记录</strong>
		</font>
	</p>
	<table width="90%" align="center" class="tbstyle" border="1" cellpadding="0" cellspacing="0" id="rsTable">
		<tr height="20">
			<td align="left">&nbsp;日期：<logic:present name="rs"><bean:write name="rs" property="rq"/></logic:present></td>
			<td align="left">&nbsp;出席人数：<logic:present name="rs"><bean:write name="rs" property="cxrs"/></logic:present></td>
			<td align="left">&nbsp;主持：<logic:present name="rs"><bean:write name="rs" property="zc"/></logic:present></td>
		</tr>
		<tr>
			<td align="left" colspan="3">&nbsp;出席人员签到：
				<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				<p align="center">&nbsp;</p>
				<p align="center">&nbsp;</p>
			</td>
		</tr>
		<tr>
			<td align="left" colspan="3">&nbsp;会议记录：
				<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				<p align="center">&nbsp;</p>
				<p align="center">&nbsp;</p>
				<p align="center">&nbsp;</p>
				<p align="center">&nbsp;</p>
				<p align="center">&nbsp;</p>
				<p align="center">&nbsp;</p>
				<p align="center">&nbsp;</p>
				<p align="center">&nbsp;</p>
				<p align="center">&nbsp;</p>
				<p align="center">&nbsp;</p>
				<p align="center">&nbsp;</p>
				<p align="center">&nbsp;</p>
				<p align="center">&nbsp;</p>
				<p align="center">&nbsp;</p>
				<p align="center">&nbsp;</p>
				<p align="center">&nbsp;</p>
				<p align="right">记录人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><br>
			</td>
		</tr>
	</table>
</body>
</html:html>
