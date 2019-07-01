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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
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
	<script language="javascript" src="js/function.js"></script>
	<body>
		<fieldset>
			<legend>
				日志详细信息
			</legend>

			<table width="99%" class="tbstyle">
				<tr>
					<td align="right" width="25%" bgcolor="DOEOEE">
						日志序号：
					</td>
					<td align="left" width="25%">
						<bean:write name="rs" property="rzxh" />
					</td>
					<td align="right" width="25%" bgcolor="DOEOEE">
						用户：
					</td>
					<td align="left" width="25%">
						<bean:write name="rs" property="yhm" />
					</td>
				</tr>

				<tr>
					<td align="right" bgcolor="DOEOEE">
						操作时间：
					</td>
					<td align="left">
						<bean:write name="rs" property="czsj" />
					</td>
					<td align="right" bgcolor="DOEOEE">
						操作ip地址：
					</td>
					<td align="left">
						<bean:write name="rs" property="ip" />
					</td>
				</tr>
				<tr>
					<td align="right" bgcolor="DOEOEE">
						操作电脑网卡mac地址：
					</td>
					<td align="left">
						<bean:write name="rs" property="mac" />
					</td>
					<td align="right" bgcolor="DOEOEE">
						主机名：
					</td>
					<td align="left">
						<bean:write name="rs" property="host" />
					</td>
				</tr>
				<tr>
					<td align="right" bgcolor="DOEOEE">
						客户机时间：
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="clienttime" />
					</td>
				</tr>
				<tr>
					<td align="right" bgcolor="DOEOEE">
						用户所做操作：
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="yhcz" />
					</td>
				</tr>
				<tr>
					<td align="right" bgcolor="DOEOEE">
						老数据：
					</td>
					<td align="left" colspan="3">
						<html:textarea name="rs" property="oldval" style="width:99%"
							rows="6" />
					</td>
				</tr>
				<tr>
					<td align="right" bgcolor="DOEOEE">
						新数据（如有更新）
					</td>
					<td align="left" colspan="3" >
						<html:textarea name="rs" property="newval" style="width:99%"
							rows="6" />
					</td>
				</tr>

<%--				<tr>--%>
<%--					<td align="right" bgcolor="DOEOEE">--%>
<%--						操作语句：--%>
<%--					</td>--%>
<%--					<td align="left" colspan="3">--%>
<%--						<html:textarea name="rs" property="sql" rows="5" cols="3"--%>
<%--							style="width:99%" />--%>
<%--					</td>--%>
<%--				</tr>--%>
			</table>
		</fieldset>
	</body>
</html>
