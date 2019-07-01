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
		<title>就业管理信息系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>



	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<fieldset>
			<legend>
				学生就业信息详细记录
			</legend>
			<table width="100%" class="tbstyle">
				<tr>
					<td align="center" colspan="4">
						学生就业基本情况
					</td>
				</tr>
				<tr>
					<td colspan="4">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 学号：
						<bean:write name="rs" property="xh" />
					</td>
				</tr>
				<tr>
					<td align="right">
						姓名：
					</td>
					<td>
						<bean:write name="rs" property="xm" />
					</td>
					<td align="right">
						性别：
					</td>
					<td>
						<bean:write name="rs" property="xb" />
					</td>
				</tr>
				<tr>
					<td align="right">
						年级：
					</td>
					<td>
						<bean:write name="rs" property="nj" />
					</td>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td>
						<bean:write name="rs" property="xymc" />
					</td>
				</tr>
				<tr>
					<td align="right">
						专业：
					</td>
					<td>
						<bean:write name="rs" property="zymc" />
					</td>
					<td align="right">
						班级：
					</td>
					<td>
						<bean:write name="rs" property="bjmc" />
					</td>
				</tr>
				<tr>
					<td align="right">
						单位证明：
					</td>
					<td colspan="3">
						<bean:write name="rs" property="dwzm" />
					</td>
				</tr>
				<tr>
					<td align="right">
						专业对口情况：
					</td>
					<td>
						<bean:write name="rs" property="zydkqk" />
					</td>
					<td align="right">
						就业部门：
					</td>
					<td>
						<bean:write name="rs" property="jybm" />
					</td>
				</tr>
				<tr>
					<td align="right">
						岗位性质：
					</td>
					<td>
						<bean:write name="rs" property="gwxz" />
					</td>
					<td align="right">
						岗位名称：
					</td>
					<td>
						<bean:write name="rs" property="gwmc" />
					</td>
				</tr>
				<tr>
					<td align="right">
						工资情况：
					</td>
					<td>
						<bean:write name="rs" property="gzqk" />
					</td>
					<td align="right">
						工作地：
					</td>
					<td>
						<bean:write name="rs" property="gzd" />
					</td>
				</tr>
				<tr>
					<td align="right">
						是否就业：
					</td>
					<td>
						<bean:write name="rs" property="sfjy" />
					</td>
					<td align="right">
						是否签约：
					</td>
					<td>
						<bean:write name="rs" property="sfqy" />
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						就业单位基本情况
					</td>
				</tr>
				<tr>
					<td align="right">
						单位名称：
					</td>
					<td>
						<bean:write name="rs" property="dwmc" />
					</td>
					<td align="right">
						单位性质：
					</td>
					<td>
						<bean:write name="rs" property="dwxz" />
					</td>
				</tr>
				<tr>
					<td align="right">
						单位地址：
					</td>
					<td>
						<bean:write name="rs" property="dwdz" />
					</td>
					<td align="right">
						单位联系人：
					</td>
					<td>
						<bean:write name="rs" property="dwlxr" />
					</td>
				</tr>
				<tr>
					<td align="right">
						单位电话：
					</td>
					<td>
						<bean:write name="rs" property="dwdh" />
					</td>
					<td align="right">
						登记时间：
					</td>
					<td>
						<bean:write name="rs" property="djsj" />
					</td>
				</tr>
			</table>
		</fieldset>
	</body>
</html>

