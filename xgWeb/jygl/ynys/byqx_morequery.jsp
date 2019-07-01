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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<body>
		<fieldset>
			<legend>
				毕业去向信息
			</legend>
			<table width="100%" id="rsT" class="tbstyle">
				<thead>
					<tr>
						<td align="left" colspan="4">
							&nbsp;&nbsp;毕业年度:
							<html:text property="bynd" name="rs" style="width:35px"
								readonly="true" />
							&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />:
							<html:text property="xymc" name="rs" style="width:170px"
								readonly="true" />
							&nbsp;&nbsp;提交时间：
							<html:text name="rs" property="tjsj" style="width:140px"
								readonly="true" />
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right" width="15%">
						学号:
					</td>
					<td align="left" width="35%">
						<bean:write name="rs" property="xsxh" />
					</td>
					<td align="right" width="15%">
						姓名：
					</td>
					<td align="left" width="35%">
						<bean:write name="rs" property="name" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						性别：
					</td>
					<td align="left">
						<bean:write name="rs" property="xb" />
					</td>
					<td align="right">
						生源地区：
					</td>
					<td align="left">
						<bean:write name="rs" property="sydq" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						毕业去向：
					</td>
					<td align="left">
						<bean:write name="rs" property="byqx" />
					</td>
					<td align="right">
						联系地址：
					</td>
					<td align="left">
						<bean:write name="rs" property="lxdz" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						联系电话：
					</td>
					<td align="left">
						<bean:write name="rs" property="lxdh" />
					</td>
					<td align="right">
						邮政编码：
					</td>
					<td align="left">
						<bean:write name="rs" property="yzbm" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						移动电话：
					</td>
					<td align="left">
						<bean:write name="rs" property="yddh" />
					</td>
					<td align="right">
						电子邮箱：
					</td>
					<td align="left">
						<bean:write name="rs" property="email" />
					</td>
				</tr>
			</table>
		</fieldset>
	</body>
</html>
