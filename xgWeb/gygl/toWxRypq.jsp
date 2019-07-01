<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<base target="_self">
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>

		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<style media="print" type="text/css">
  .brk{
	page-break-after:always;
   }
   .noPrin{display:none}
</style>
	<body>
		<html:form action="/XsgyglDispatch.do" method="post">
			<div align="center">
				<bean:write name="xxmc"/>公寓维修派遣单
			</div>
			<div align="left">
				<u>&nbsp;&nbsp;<bean:write name="rs" property="xn"/>&nbsp;&nbsp;</u>学年
				<u>&nbsp;&nbsp;<bean:write name="rs" property="xq"/>&nbsp;&nbsp;</u>学期
			</div>
			<table width="100%" border="0" id="theTable"  class="printstyle">
				<tr>
					<td width="30%" align="right">
						宿舍：
					</td>
					<td>
						<bean:write name="rs" property="ldmc"/><bean:write name="rs" property="qsh"/>
					</td>
					<td width="30%" align="right">
						报修人：
					</td>
					<td>
						<bean:write name="rs" property="bxrxm"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						报修时间：
					</td>
					<td>
						<bean:write name="rs" property="bxsj"/>
					</td>
					<td align="right">
						报修人电话：
					</td>
					<td>
						<bean:write name="rs" property="lxfs"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						预维修时间：
					</td>
					<td>
						<bean:write name="rs" property="wxsj"/>
					</td>
					<td align="right">
						预派遣维修人：
					</td>
					<td>
						<bean:write name="rs" property="wxry"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						实际维修时间：
					</td>
					<td colspan="3">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td align="right">
						维修内容：
					</td>
					<td colspan="3">
						<bean:write name="rs" property="wxnr"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						报修人签名：
					</td>
					<td>
						&nbsp;
					</td>
					<td align="right">
						维修人签名：
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="4" valign="bottom" align="right" height="50"> 
						部门盖章
					</td>
				</tr>
			</table>
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
