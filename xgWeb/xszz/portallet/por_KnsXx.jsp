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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<%@include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<html:form action="/portallet_xszz" method="post">
<%--			<div class="title">--%>
<%--				<div class="title_img" id="title_m">--%>
<%--					当前所在位置：测试 - 资助 - 困难生 - 详细信息--%>
<%--				</div>--%>
<%--			</div>--%>
			<table width="98%" align="center" class="tabform">
				<thead>
					<tr >
						<td colspan="4" align="center">	
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right">
						学号：
					</td>
					<td align="left">
						<bean:write name="rs" property="xh" />
					</td>
					<td align="right">
						年度：
					</td>
					<td align="left">
						<bean:write name="rs" property="nd" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						姓名：
					</td>
					<td align="left">
						<bean:write name="rs" property="xm" />
					</td>
					<td align="right">
						学年：
					</td>
					<td align="left">
						<bean:write name="rs" property="xn" />
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
						学期：
					</td>
					<td align="left">
						<bean:write name="rs" property="xq" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						年级：
					</td>
					<td align="left">
						<bean:write name="rs" property="nj" />
					</td>
					<td align="right">
						联系电话：
					</td>
					<td align="left">
						<bean:write name="rs" property='lxdh' />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc" />
					</td>
					<td align="right">
						申请时间：
					</td>
					<td align="left">
						<bean:write name="rs" property="sqsj" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						专业：
					</td>
					<td align="left">
						<bean:write name="rs" property="zymc" />
					</td>
					<td align="right">
						班级：
					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						申请理由：
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="sqyy" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						备注：
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="bz" />
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
