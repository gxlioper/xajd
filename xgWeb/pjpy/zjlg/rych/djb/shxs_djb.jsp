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
<style media="print" type="text/css">
   .noPrin{display:none}
</style>
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
    <object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<body>
		<script language="javascript" src="js/function.js"></script>

		<html:form action="/zjlgPjpy" method="post">
			<div align="center" style="font-size: 20px;font: bold">
							浙江理工大学<u>&nbsp;<bean:write name="xn"/>&nbsp;</u>学年“三好学生”登记表
			</div>
			<br>
			<table width="100%" class="tbstyle">
				<tr>
					<td width="5%" align="center">
						姓名
					</td>
					<td width="10%">
						<bean:write name="rs" property="xm"/>
					</td>
					<td width="5%"  align="center">
						性别
					</td>
					<td width="8%">
						<bean:write name="rs" property="xb"/>
					</td>
					<td width="5%"  align="center">
						生源地
					</td>
					<td width="15%" >
					<bean:write name="rs" property="syd"/>
					</td>
					<td width="5%"  align="center">
						出生年月
					</td>
					<td width="10%">
						<bean:write name="rs" property="csrq"/>
					</td>
				</tr>
				<tr>
					<td  align="center">
						学号
					</td>
					<td>
						<bean:write name="rs" property="xh"/>
					</td>
					<td  align="center">
						<bean:message key="lable.xsgzyxpzxy" />
					</td>
					<td>
						<bean:write name="rs" property="xymc"/>
					</td>
					<td  align="center">
						班级
					</td>
					<td>
						<bean:write name="rs" property="bjmc"/>
					</td>
					<td  align="center">
						政治面貌
					</td>
					<td>
						<bean:write name="rs" property="zzmmmc"/>
					</td>
				</tr>
				<tr>
					<td  align="center" colspan="2">
						综合测评成绩班级排名
					</td>
					<td colspan="2">
						<bean:write name="rsCj" property="pm"/>
					</td>
					<td  align="center">
						特长
					</td>
					<td colspan="3">
						<bean:write name="rs" property="tc"/>
					</td>
				</tr>
				<tr>
					<td height="200"  align="center">
						本<br><br>人<br><br>申<br><br>请
					</td>
					<td colspan="7">
						<bean:write name="bz" />
					</td>
				</tr>
				<tr>
					<td height="100"  align="center">
						班<br>级<br>推<br>意<br>见
					</td>
					<td colspan="7" valign="bottom">
						<div align="center">
							班主任签字（盖章）
						</div>
						<div align="right">
							&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日
						</div>
					</td>
				</tr>
				<tr>
					<td height="100"  align="center">
						学<br>院<br>意<br>见
					</td>
					<td colspan="7" valign="bottom">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />领导签字（盖章）
						</div>
						<div align="right">
							年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日
						</div>
					</td>
				</tr>
				<tr>
					<td height="100" align="center">
						学<br>校<br>意<br>见
					</td>
					<td colspan="7" valign="bottom">
						<div align="center">
							学校领导签字（盖章）
						</div>
						<div align="right">
							年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日
						</div>
					</td>
				</tr>
				<tr>
					<td  align="center">
						备<br>注
					</td>
					<td colspan="7">
						<div align="left">
							1. 本表一式二份，表中填写内容要求电脑打印；
						</div>
						<div align="left">
							2. <bean:message key="lable.xsgzyxpzxy" />、班级填写清楚。
						</div>
					</td>
				</tr>
			</table>
			<div align="right">
				填表日期： &nbsp;&nbsp;&nbsp;年 &nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;日
			</div>
			<div class='noPrin' align="center">
				<input type='button' class='button2' value='页面设置'
					onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='打印预览'
					onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='直接打印'
					onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
		</html:form>
	</body>

</html>
