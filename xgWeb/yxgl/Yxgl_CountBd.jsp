<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="xgxt.DAO.DAO"/>
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
    
    <title>ѧ��������ѧУ��������</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
     <%	
     String count = "";			
try{					    					 
	String sql = "";
	DAO dao = DAO.getInstance();
	sql = "select count(*) sum from view_newstureportinfo where xybd='��' or yybd='��' or stbd='��' or ssbd='��'";
	String[] sum = dao.getOneRs(sql,  new String[] {},new String[]{"sum"});
	count = sum[0];
	out.println(count);
}catch(Exception e){
}
%> <br>
	������������Ϊ<%=count%>
  </body>
</html>
