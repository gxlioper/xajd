<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<!-- 打印控件begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
		.noPrin{display:none;}
	</style>
	<!-- end -->
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>

	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
</head>
<body>
	<html:form action="/pjpyshcbyswh">
		<div align="center" style="font-size:18px;font:'黑体' ">
			&nbsp;
		</div>
		<h3 align="center">
			${title1}学期课程成绩表
		</h3>
		<table width="98%" class="tbstyle">

			<tr align="center">
				<th scope="col">
					学&nbsp;&nbsp;年
				</th>

				<th scope="col">
					学&nbsp;&nbsp;期
				</th>
				<th scope="col">
					课程名称
				</th>
				<th scope="col">
					课程类型
				</th>
				<th scope="col">
					成&nbsp;&nbsp;绩
				</th>
				<th scope="col">
					补考成绩
				</th>
			</tr>
			<logic:notEmpty name="rs1">
				<logic:iterate name="rs1" id="s">
					<tr style="cursor:hand;" align="center" onclick="rowOnClick(this)">
						<logic:iterate id="v" name="s">
							<td scope="col">
								<bean:write name="v" />
							</td>
						</logic:iterate>
					</tr>
				</logic:iterate>
				<tr><td colspan="6">&nbsp;</td></tr>
				<tr>
					<td>平均成绩</td><td>${map1.pjcj}</td>
					<td>平均成绩排名</td><td>${map1.pjcjpm}</td>
					<td>操行成绩</td><td>${map1.cxcj}</td>		
				</tr>
				<tr>
					<td>总成绩</td><td>${map1.zcj}</td>
					<td>总成绩排名</td><td>${map1.pm}</td>
					<td></td><td></td>		
				</tr>
			</logic:notEmpty>
			<logic:empty name="rs1">
				<tr>
					<th scope="col" colspan="6">
						未找到任何记录！
					</th>
				</tr>
			</logic:empty>
		</table>
		<h3 align="center">
			${title2}学期课程成绩表
		</h3>
		<table width="98%" class="tbstyle">

			<tr align="center">
				<th scope="col">
					学&nbsp;&nbsp;年
				</th>

				<th scope="col">
					学&nbsp;&nbsp;期
				</th>
				<th scope="col">
					课程名称
				</th>
				<th scope="col">
					课程类型
				</th>
				<th scope="col">
					成&nbsp;&nbsp;绩
				</th>
				<th scope="col">
					补考成绩
				</th>
			</tr>
			<logic:notEmpty name="rs2">
				<logic:iterate name="rs2" id="s">
					<tr style="cursor:hand;" align="center" onclick="rowOnClick(this)">
						<logic:iterate id="v" name="s">
							<td scope="col">
								<bean:write name="v" />
							</td>
						</logic:iterate>
					</tr>
				</logic:iterate>
				<tr><td colspan="6">&nbsp;</td></tr>
				<tr align="center">
					<td>平均成绩</td><td>${map2.pjcj}</td>
					<td>平均成绩排名</td><td>${map2.pjcjpm}</td>
					<td>操行成绩</td><td>${map2.cxcj}</td>		
				</tr>
				<tr align="center">
					<td>总成绩</td><td>${map2.zcj}</td>
					<td>总成绩排名</td><td>${map2.pm}</td>
					<td></td><td></td>		
				</tr>
			</logic:notEmpty>
			<logic:empty name="rs2">
				<tr>
					<th scope="col" colspan="6">
						未找到任何记录！
					</th>
				</tr>
			</logic:empty>
		</table>
		
	</html:form>
</body>
</html:html>
