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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
<title>在线普测</title>
<base target="_self" />
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
		<script type="text/javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
</head>	
<body>
	<html:form action="/xljk_xlcs_zxpc.do">
		<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					<bean:write name="tips" scope="request" />
				</div>
		</div>
		<table align="center"  width="100%" id="rsTable" class="tbstyle">
			<logic:iterate id="st"  name="result">
				<tr><td width="60%">
						<bean:write property="zw" name="st"/>:
					</td>
					<td>
						<bean:write property="df" name="st"/>
					</td>
				</tr>
			</logic:iterate>
			<tr>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<input type="button" onclick="Close();return false;" name="closeWindow" id="closeWindow" value=" 关   闭 " class="button2"/>
				</td>
			</tr>	
		</table>
	</html:form>
</body>
</html>