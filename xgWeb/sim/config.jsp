<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//认证服务器的地址前缀
	String casServerUrlPrefix = "http://172.28.0.53:8082/cas/";
	//学工系统服务器的地址前缀
	String appServerUrlPrefix = "http://localhost:8080/xgxt/";
	
	//学工系统首页路径（需要区分学生、教师）
	String defaultTargetUrl = appServerUrlPrefix+"index.jsp";
	//学工系统登录路径
	String defaultLoginUrl = appServerUrlPrefix+"jinshida.jsp";
%>
