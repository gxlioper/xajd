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
			var userName = '${userName}';
			var timestamp = '${timestamp}';
			var cipher = '${cipher}';
			var gate = '${gate}';
			var url = "http://meiyuan.caajiuye.com/login.php?userName="+userName+"&timestamp="+timestamp+"&cipher="+cipher+"&gate="+gate;
			//window.open(url);
			document.location = url;
		</script>
	</body>
</html>
