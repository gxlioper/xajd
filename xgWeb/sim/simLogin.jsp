<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>

<%
String targetUrl = request.getParameter("targetUrl");
String loginUrl = request.getParameter("loginUrl");
%>
<html>
<head>
<title></title>
</head>
<body>
	<iframe id="sim" name="sim" frameborder="0" scrolling="no" height="200" width="100%" style="display:;"
		src="simProcess.jsp?targetUrl=<%=targetUrl%>&loginUrl=<%=loginUrl%>"></iframe>
</body>
</html>