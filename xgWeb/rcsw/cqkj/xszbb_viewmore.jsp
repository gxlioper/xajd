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
				学生证详细信息
			</legend>

			<table width="100%" class="tbstyle">
				<tr>
					<td align="right" width="15%" bgcolor="DOEOEE">
						学号：
					</td>
					<td align="left" width="35%">
						<bean:write name="rs" property="xh" />
					</td>
					<td align="right" width="15%" bgcolor="DOEOEE">
						姓名：
					</td>
					<td align="left" width="35%">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td align="right" bgcolor="DOEOEE">
						性别：
					</td>
					<td align="left" >
						<bean:write name="rs" property="xb" />
					</td>
					<td align="right"  bgcolor="DOEOEE">
						年级：
					</td>
					<td align="left">
						<bean:write name="rs" property="nj" />
					</td>
				</tr>
				<tr>
					<td align="right" bgcolor="DOEOEE">
						学年：
					</td>
					<td align="left">
						<bean:write name="rs" property="xn" />
					</td>
					<td align="right"  bgcolor="DOEOEE">
						学期：
					</td>
					<td align="left" >
						<bean:write name="rs" property="xq" />
					</td>
				</tr>
				<tr>
					<td align="right"  bgcolor="DOEOEE">
						年度：
					</td>
					<td align="left" >
						<bean:write name="rs" property="nd" />
					</td>
					<td align="right" bgcolor="DOEOEE">
						学制：
					</td>
					<td align="left" >
						<bean:write name="rs" property="xz" />
					</td>
				</tr>
				<tr>
					<td align="right"  bgcolor="DOEOEE">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc" />
					</td>
					<td align="right" bgcolor="DOEOEE">
						申请时间：
					</td>
					<td align="left" >
						<bean:write name="rs" property="sqsj" />
					</td>
				</tr>
				<tr>
					<td align="right" bgcolor="DOEOEE">
						专业：
					</td>
					<td align="left" >
						<bean:write name="rs" property="zymc" />
					</td>
					<td align="right"  bgcolor="DOEOEE">
						补办时间：
					</td>
					<td align="left" >
						<bean:write name="rs" property="bbsj" />
					</td>
				</tr>
				<tr>
					<td align="right"  bgcolor="DOEOEE">
						班级：
					</td>
					<td align="left" >
						<bean:write name="rs" property="bjmc" />
					</td>
					<td align="right"  bgcolor="DOEOEE">
						经办人：
					</td>
					<td align="left" >
						<bean:write name="rs" property="jbr" />
					</td>
				</tr>
				<tr>
					<td align="right"  bgcolor="DOEOEE">
						身份证号：
					</td>
					<td align="left" >
						<bean:write name="rs" property="sfzh" />
					</td>
					<td align="right"  bgcolor="DOEOEE">
						是否领取：
					</td>
					<td align="left" >
						<bean:write name="rs" property="sflq" />
					</td>
				</tr>
				<tr>
					<td align="right"  bgcolor="DOEOEE">
						生源地：
					</td>
					<td align="left" >
						<bean:write name="rs" property="syd" />
					</td>
					<td align="right"  bgcolor="DOEOEE">
						补办原因：
					</td>
					<td align="left" >
						<bean:write name="rs" property="bbyy" />
					</td>
				</tr>
				<tr>
					<td align="right"  bgcolor="DOEOEE">
						出生日期：
					</td>
					<td align="left">
						<bean:write name="rs" property="csrq" />
					</td>
					<td align="right"  bgcolor="DOEOEE">
						火车站名：
					</td>
					<td align="left" >
						<bean:write name="rs" property="hczm" />
					</td>
				</tr>
				<tr>
					<td align="right">
						备注：
					</td>
					<td colspan="3">
					    <html:textarea name="rs" property="bz"  rows="8" style="width:100%" readonly="true"/>
					</td>
				</tr>

			</table>
		</fieldset>
	</body>
</html>


