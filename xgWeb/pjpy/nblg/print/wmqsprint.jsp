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
	<title><bean:message key="lable.title" />
	</title>
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
			<b>${xxmc }20<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>&nbsp;-&nbsp;20<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>学年<br />文明寝室申报表</b>
		</div>
		<br />
		<div>
			<table width="100%" class="printstyle">
				<tr align="center" height="23px">
					<td width="8%">
						分院
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td>
						专业班级
					</td>
					<td colspan="3">
						&nbsp;
					</td>
					<td>
						寝室
					</td>
					<td width="10%">
						&nbsp;
					</td>
					<td>
						免检时间
					</td>
					<td width="10%">
						&nbsp;
					</td>
				</tr>
				<tr align="center">
					<td>
						成员
						<br />
						姓名
					</td>
					<td>
						党员/入
						<br />
						党积极
						<br />
						分子/递
						<br />
						交入党
						<br />
						申请书
					</td>
					<td>
						智育成绩
						<br />
						专业排名
					</td>
					<td>
						综合测评
						<br />
						班级排名
					</td>
					<td>
						有无不及
						<br />
						格课程
					</td>
					<td>
						有无违
						<br />
						纪情况
					</td>
					<td>
						有无寝
						<br />
						室违章
						<br />
						电器、
						<br />
						私拉电
						<br />
						线等
					</td>
					<td>
						有无夜
						<br />
						不归宿
						<br />
						记录
					</td>
					<td colspan="2">
						其他获奖情况
					</td>
					<td>
						签名
					</td>
				</tr>
				<tr align="center" height="23px">
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr align="center" height="23px">
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr align="center" height="23px">
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr align="center" height="23px">
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr align="center" height="23px">
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="" align="center">
						文明
						<br />
						寝室
						<br />
						事迹
					</td>
					<td align="center" colspan="10">
						<br />
						<br />
						<br />
						<p align="right">
							年 &nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;日
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr>
					<td colspan="1" align="center">
						生活
						<br />
						园区
						<br />
						管理
						<br />
						服务
						<br />
						中心
						<br />
						意见
					</td>
					<td align="center" colspan="5">
						<br />
						<br />
						<br />
						<p align="right">
							(盖章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
							年 &nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;日
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
					<td colspan="1" align="center">
						分
						<br />
						院
						<br />
						意
						<br />
						见
					</td>
					<td align="center" colspan="4">
						<br />
						<br />
						<br />
						<p align="right">
							(盖章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
							年 &nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;日
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr>
					<td colspan="" align="center">
						<bean:message key="lable.xsgzyxpzxy" />
						<br />
						意见
					</td>
					<td colspan="10">
						<br />
						<br />
						<br />
						<p align="right">
							(盖章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
							年 &nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;日
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
			</table>
			<div>
				填表说明：1、寝室成员必须如实填写以上内容。如有不符，一经查实，全院通报。
				<br />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、文明寝室事迹另附页。
			</div>
		</div>
		<div align="center" class="noPrin">
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				页面设置
			</button>
			<button type="button" class="button2" onclick="WebBrowser.ExecWB(7,1);return false;">
				打印预览
			</button>
			<button type="button" class="" onclick="WebBrowser.ExecWB(6,6);return false;">
				直接打印
			</button>
		</div>
	</html:form>
</body>
</html:html>
