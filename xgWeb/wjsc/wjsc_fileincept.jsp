<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta content="all" name="robots" />
		<meta name="Copyright" content="Copyright" />
		<meta name="description" content="description" />
		<meta name="keywords" content="description" />

		<link id="csss" rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link href="jyweb/style/module.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/forms.css" rel="stylesheet" type="text/css">

		<script language="javascript" src="js/function.js"></script>
	</head>
	<script type="text/javascript">
	</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
		<br>
		<br>
		<div class="mainframe">
			<div class="newscon">
				<h1>
					<bean:write name="rs" property="wjm" />
				</h1>
				<b>文件号</b>：
				<bean:write name="rs" property="wjh" />
				&nbsp;&nbsp;&nbsp;&nbsp;
				<b>发布部门</b>：
				<bean:write name="rs" property="bmmc" />
				&nbsp;&nbsp;&nbsp;&nbsp;
				<b>发布时间</b>：
				<bean:write name="rs" property="wjffsj" />
				&nbsp;&nbsp;&nbsp;&nbsp;
				<b>发布人</b>：
				<bean:write name="rs" property="ffr" />
				<h4 style="width:80%"></h4>
				<h3>
					<bean:write name="rs" property="wjscsm" />
				</h3>
				<h4 style="width:80%"></h4>
				<table width="80%" border="0">
					<tr>
						<td align="right">
							<logic:notEmpty name="rs" property="filename">
							文件下载 ：
							<a
								href="fileDownload.do?wjsclj=<bean:write name="rs" property="wjsclj"/>&wjhID=<bean:write name="rs" property="wjh" />">
								<bean:write name="rs" property="filename" /> </a>
							</logic:notEmpty>
						</td>
					</tr>
				</table>
				<h2>
				</h2>
			</div>
		</div>
	</body>
</html>
