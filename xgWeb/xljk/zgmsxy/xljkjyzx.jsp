<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<base target="_blank">
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	
	</head>
	<body>
	<html:form action="/zjlg_xljk"></html:form>
		<script type="text/javascript">
			//document.location = "http://www.baidu.com";
			//document.forms[0].action = "http://www.baidu.com";
			//document.forms[0].target = "_blank";
			//document.forms[0].submit();
			//document.forms[0].target = "_self";
			if(${xxdm=="10277"}){
				window.open("http://psytest.sus.edu.cn/ZhgProgram/frmLogin.aspx");
			}else{
				window.open("http://www.baidu.com");
			}
		</script>
	</body>
</html>
