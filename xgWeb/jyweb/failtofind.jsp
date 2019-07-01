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

<html>
	<head>
		<title><%=session.getAttribute("xxmc")%>就业网</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta content="all" name="robots" />
		<meta name="author" content="杭州正方电子工程有限公司 hzzfsoft@126.com" />
		<meta name="Copyright" content="Copyright" />
		<meta name="description" content="description" />
		<meta name="keywords" content="description" />

		<link id="csss" rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link href="jyweb/style/module.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/forms.css" rel="stylesheet" type="text/css">

		<script language="javascript" src="js/function.js"></script>

	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
		<logic:equal name="xxdm" value="10856" scope="session">
			<logic:equal name="yhm" value="" scope="session">
				<jsp:include flush="true" page="head_false.jsp"></jsp:include>
				<input type="hidden" name="lock" value="closed" />
			</logic:equal>
			<logic:notEqual name="yhm" value="" scope="session">
				<jsp:include flush="true" page="head.jsp"></jsp:include>
				<input type="hidden" name="lock" value="open" />
			</logic:notEqual>
		</logic:equal>
		<logic:notEqual name="xxdm" value="10856" scope="session">
			<jsp:include flush="true" page="head.jsp"></jsp:include>
			<input type="hidden" name="lock" value="open" />
		</logic:notEqual>

		<div align="center">
			<br>
			<br>
			<br>
			<img src="jyweb/images/caozuo_fail.gif" border="0" align="absmiddle">
			<br>
			<a href="index.do">返回登录页面</a>

		</div>

		<br>
		<br>
		<br>

		<jsp:include flush="true" page="foot.jsp"></jsp:include>
	</body>
</html>
