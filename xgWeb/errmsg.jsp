<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- Í·ÎÄ¼þ -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
<script language="javascript" src="js/xgutil.js"></script>
<script language="javascript" src="js/xszz/xszzFunction.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">
	<body onload="">
		<html:form action="/xszz_forward">
			<br /> 
			<br /> 
			<br /> 
			<br /> 
			<br /> 
			<p align="center">
			<img src="/xgxt/pictures/xszz/pic_prompt.gif"></img>
			</p>
			<p align="center">
			<font size="5"><b>${errMsg }</b></font>
			</p>
		</html:form>
	</body>
</html>
