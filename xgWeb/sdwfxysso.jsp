<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>系统提示</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="description" content="This is my page" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		request.setAttribute("No", "400");
	%>
<script language="javascript">
function onCommit()
  	{   //提交给后台进行处理
    	var userType = document.getElementById("userType").value;
  		if (userType == "" || userType == null) {
  			window.location.href="<%=request.getContextPath()%>/stuPage.jsp";
  		} else if (userType == "stu" || userType=="student") {
  			window.location.href="<%=request.getContextPath()%>/stuPage.jsp";
  		} else {  			
  			window.location.href="<%=request.getContextPath()%>/teaPage.jsp";
  		}
  	}

</script>
	<body onLoad="onCommit()" >
		<form id="MAINFORM" name="MAINFORM" method="post" action=""
			target="_self">
			<input type="hidden" id="userType" name="userType" value="${userType }" />
		</form>
	</body>
</html>
