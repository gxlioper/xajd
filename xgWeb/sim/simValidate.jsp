<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>

<%
String defaultLoginActionUrl = appServerUrlPrefix+"index.jsp";
String encoding = "UTF-8";
String successLoginTag = "已登录！";

String loginUrl = request.getParameter("loginUrl");
if (loginUrl==null || loginUrl.equals("")) {
	loginUrl = defaultLoginActionUrl;
}
if (loginUrl.startsWith("http://")||loginUrl.startsWith("https://")){
	
} else {
	loginUrl = CommonUtil.decodeServiceURI(loginUrl);
}

String user = request.getParameter("username");
String pass = request.getParameter("password");

String url = loginUrl;

Map data = new HashMap();
data.put("username", user);
data.put("password", pass);

String res = CommonUtil.PerformHttpPost(url, data, encoding);

if (res==null || res.indexOf(successLoginTag)==-1) {
	response.getWriter().print("{\"result\":\"Failure\",\"msg\":\"用户名或密码错误！\"}");
} else {
	response.getWriter().print("{\"result\":\"Success\",\"msg\":\"验证成功！\"}");
}
%>
