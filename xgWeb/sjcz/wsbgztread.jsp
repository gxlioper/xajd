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
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<logic:present name="rs">
		<html:form action="/topic_reply" method="post">
			<table width="100%" class="tbstyle">
				<thead>
				<tr>
					<td colspan="2" align="center">主题：${rs.ztbt}<input type="hidden" name="ztbh" id="ztbh" value="${rs.ztbh}"/>
					</td>
				</tr>
				<tr>
					<td width="12%">发布人</td>
					<td>正文</td>
				</tr>
				</thead>
				<tbody>
				<tr valign="top">
					<td width="12%">${rs.fbr}<br>楼主</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;时间：${rs.fbsj}<br><hr><bean:write name="rs" property="ztzw" filter="false"/></td>
				</tr>
				<logic:present name="gtxxList">
				<logic:iterate id="gtxx" name="gtxxList">
				<tr valign="top">
					<td>${gtxx.gtr}<br>第${gtxx.rownum}楼</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;时间：${gtxx.gtsj}<br><hr><bean:write name="gtxx" property="gtzw" filter="false"/></td>
				</tr>
				</logic:iterate>
				</logic:present>
				<tr>
					<td valign="top">回复</td>
					<td><textarea cols="55" rows="4" name="hfzw" wrap="hard"></textarea></td>
				</tr>
				</tbody>				
			</table>
				<div class="buttontool" align="center">
					<input type="hidden" name="act" value="save"/>
					<button type="button" class="button2" onclick="submit()" style="width:80px" id="buttonSave">
						提 交
					</button>
				</div>
		</html:form>
		</logic:present>
  </body>
</html>
