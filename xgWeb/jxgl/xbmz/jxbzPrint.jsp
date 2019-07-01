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
<html:html>
  <head>
    <title>军训学生营连编配表打印页</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
			<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/yxglFunction.js"></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
				<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
	</head>
<body onload="close();document.getElementById('print').click()">
  <html:form action="/jxgljz_xbmz" method="post">
  <div align="center"><h1>军训学生营连编配表</h1></div>
	<logic:notEmpty name="rs">
	<table id="tabPri" width="100%">
		<tr>
			<td width="30%">
				<table width="100%" align="top" id="rsTable" class="tbstyle">
				<tbody>
					<tr align="center">
						<logic:iterate id="tit" name="topTr" offset="0" length="4">
							<td>
								<bean:write name="tit" property="cn" />
							</td>
						</logic:iterate>
					</tr>
					<logic:iterate name="rs" id="s">
						<tr>
						<logic:iterate id="b" name="s" property="rsList">	
							<td width="25%" height="<bean:write name="b" property="tdnum"/>">&nbsp;<bean:write name="b" property="bzmc1"/></td>
							<td width="25%" height="<bean:write name="b" property="tdnum"/>">&nbsp;<bean:write name="b" property="num1"  /></td>
							<td width="25%" height="<bean:write name="b" property="tdnum"/>">&nbsp;<bean:write name="b" property="jgmc1"  /></td>	
							<td width="25%" height="<bean:write name="b" property="tdnum"/>">&nbsp;<bean:write name="b" property="zdy1"  /></td>	
						<% out.print("</tr>"); %>																																							
						</logic:iterate>
					</logic:iterate>
				</tbody>

				</table>
			</td>
			<td>
				<table width="100%" align="top" id="rsTable" class="tbstyle">
				<tbody>
					<tr align="center">
						<logic:iterate id="tit" name="topTr" offset="4">
							<td>
								<bean:write name="tit" property="cn" />
							</td>
						</logic:iterate>
					</tr>
					<logic:iterate name="rs1" id="s">
						<tr>
						<logic:iterate id="v" name="s" offset="0" length="2">
							<td height="100" width="10%">
							<bean:write name="v" />
							</td>
						</logic:iterate>
						<logic:iterate id="v" name="s" offset="2" length="1">
							<td height="100">
							<bean:write name="v" />
							</td>
						</logic:iterate>
						<logic:iterate id="v" name="s" offset="3" length="3">
							<td height="100" width="10%">
							<bean:write name="v" />
							</td>
						</logic:iterate>
						</tr>
					</logic:iterate>
				</tbody>
				</table>
			</td>
		</tr>
	</table>
	<input type="hidden" class="button2" name="button2" id="print"
		style="width:100px;display:none;" value="打 印 "
		onclick="expTab('tabPri','军训学生营连编配表','')"/>	
	</logic:notEmpty>
</html:form>
</body>
</html:html>
