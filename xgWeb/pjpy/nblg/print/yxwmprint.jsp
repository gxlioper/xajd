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
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>
	<!-- 打印控件begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
		.noPrin{display:none;}
	</style>
	<!-- end -->
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
</head>
<body>
	<html:form action="/pjpynblgwh">
		<div align="center" style="font-size:20px;font:'黑体' ">
			<b>${nd} 年宁波高校“三优秀三文明”候选人推荐表</b>
		</div>
		<br />
		<div>
			<table width="100%" class="printstyle">
				<tr>
					<th width="10%">
						学&nbsp;&nbsp;校
					</th>
					<td width="40%">
						&nbsp;
					</td>
					<th width="12%">
						候选人姓名
						<br />
						或班级(寝室)
					</th>
					<td width="38%">
						&nbsp;
					</td>
				</tr>
				<tr>
					<th>
						荣誉名称
					</th>
					<td>
						&nbsp;
					</td>
					<th>
						候选人
						<br />
						任职情况
					</th>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<th colspan="4">
						<p align="left">
							主要事迹:(可另附页)
						</p>
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
					</th>
				</tr>
				<tr>
					<th>
						<p>
							&nbsp;
						</p>
						<p>
							学
						</p>
						<p>
							校
						</p>
						<p>
							意
						</p>
						<p>
							见
						</p>
						<p>
							&nbsp;
						</p>
					</th>
					<th colspan="3">
						<br />
						<br />
						<br />
						<br />
						<br />
						<p align="right">
							(盖章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<br />
							年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</th>
				</tr>
			</table>
			<div align="center" class='noPrin'>
				<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
					页面设置
				</button>
				<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
					打印预览
				</button>
				<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
					直接打印
				</button>
			</div>
		</div>
	</html:form>
</body>
</html:html>
