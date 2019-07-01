<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="xgxt.form.User"%>
<%

		String systemPath = request.getContextPath();
		
		
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>	

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<script type='text/javascript' >
	<%
	    
		//User user = (User)request.getSession().getAttribute("user");
		String userOnLine = (String)request.getSession().getAttribute("userOnLine");
	
		if ("student".equals(userOnLine)){
	%>
			document.location.href="<%=systemPath%>/stuPage.jsp";
	<%
		} else {
	%>
			document.location.href="<%=systemPath%>/teaPage.jsp";
	<%
		}
	%>
</script>
  <head>
  </head>
  
  <body>
  </body>
</html>
