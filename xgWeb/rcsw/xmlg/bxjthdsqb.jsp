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
<html:html>
<base target="_self" />
<head>


	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<style media="print">
		.noprint{
			display:none;
		}
		.print{
			display:block;
		}
		.tbstyle {
	border-collapse: collapse;
}
		.tbstyle td {
	border: 1px #97B7DB solid;
	padding: 3px;
	font-size: 14px;
}
	</style>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>

</head>
<body>
	<div align="center">
		<h3 align="center">
			厦门理工<bean:message key="lable.xsgzyxpzxy" />学生课外集体外出活动申请表
			<br>
		</h3>
		<logic:equal value="bx" name="lb">
			<p>
				<font size="3">（系、班集体外出活动专用）</font>
			</p>
		</logic:equal>
		<logic:equal value="kx" name="lb">
			<p>
				<font size="3">（系跨系学生集体外出活动专用）</font>
			</p>
		</logic:equal>

		<table cellspacing=0 cellpadding=0 align=center class="tbstyle" style="width: 100%">
			<tr height="42px">
				<td align="center" width="14%">
					组织单位
				</td>
				<td align="left" width="20%">
					${form.zzdw}
				</td>
				<td align="center" width="13%">
					负责人
				</td>
				<td align="left" width="20%">
					${form.fzr}
				</td>
				<td align="center" width="13%">
					联系电话
				</td>
				<td align="left" width="20%">
					${form.fzrdh}
				</td>
			</tr>
			<tr height="42px">
				<td align="center">
					带队教师
				</td>
				<td align="left">
					${form.ddjs}
				</td>
				<td align="center">
					联系电话
				</td>
				<td align="left" colspan="3">
					${form.ddjsdh}
				</td>
			</tr>
			<tr height="42px">
				<td align="center">
					出行时间
				</td>
				<td align="left" colspan="5">
					&nbsp;&nbsp;${form.cxsj}
				</td>
			</tr>
			<tr height="42px">
				<td>
					<div align="center">
						
						<br>
						<br>
						<br>
						<br>
						活动内容
						<br>
						<br>
						<br>
						<br>
						<br>
					</div>
				</td>
				<td colspan="5" valign="top">
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;${form.hdnr}
					<br><br>
				</td>
			</tr>
			<tr height="42px">
				<td>
					<div align="center">
						
						<br>
						<br>
						<br>
						<br>
						安全措施
						<br>
						<br>
						<br>
						<br>
						<br>
					</div>
				</td>
				<td colspan="5" valign="top">
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;${form.aqcs}
					<br><br>
				</td>
			</tr>
			<logic:equal value="bx" name="lb">
				<tr height="42px">
					<td>
						<div align="center">
							<br><br>
							班<br>
							主<br>
							任<br>
							意<br>
							见<br>
							<br><br>
						</div>
					</td>
					<td colspan="2" valign="top">
						<br><br><br><br><br><br><br>
						<p align="right">签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
						&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>		
					</td>
					<td>
						<div align="center">
							<br><br>
							辅<br>
							导<br>
							员<br>
							意<br>
							见<br>
							<br><br>
						</div>
					</td>
					<td colspan="2" valign="top">
						<br><br><br><br><br><br><br>
						<p align="right">签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
						&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>	
					</td>
				</tr>
				<tr height="42px">
					<td>
						<div align="center">
							<br><br>
							系党<br>
							总支<br>
							审批<br>
							意见<br>
							<br><br>
						</div>
					</td>
					<td colspan="5" valign="top">
						<br><br><br><br><br><br>
						<p align="right">签章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
						&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>	
					</td>
				</tr>
			</logic:equal>
			<logic:equal value="kx" name="lb">
				<tr height="42px">
					<td>
						<div align="center">
							<br>
							主<br>
							管<br>
							部<br>
							门<br>
							意<br>
							见<br>
							<br>
						</div>
					</td>
					<td colspan="5" valign="top">
						<br><br><br><br><br><br>
						<p align="right">签章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
						&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>	
					</td>
				</tr>
				<tr height="42px">
					<td>
						<div align="center">
							<br>
							学<br>
							工<br>
							处<br>
							审<br>
							批<br>
							意<br>
							见<br>
							<br>
						</div>
					</td>
					<td colspan="5" valign="top">
						<br><br><br><br><br><br>
						<p align="right">签章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
						&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>	
					</td>
				</tr>
			</logic:equal>
		</table>
		<logic:equal value="bx" name="lb">
		<p align="left" style="font-size: 14px">
			注意事项：
			<br>
			1.学生集体外出活动必须填写申请表，并附活动计划、安全预案（一式三份），一份系里存档，<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其余两份报学工处、保卫处备案；
			<br>
			2.系、班学生集体外出应有班带队老师负责带队；

		</p>
		</logic:equal>
		<logic:equal value="kx" name="lb">
		<p align="left" style="font-size: 14px">
			注意事项：
			<br>
			1.学生集体外出活动必须填写申请表，并附活动计划、安全预案（一式三份），一份系里存档，<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其余两份报学工处、保卫处备案；
			<br>
			2.学生集体外出活动应有带队老师负责带队；

		</p>
		</logic:equal>
	</div>
	<br>
	<div class="buttontool noprint" align="center">
		<input  class="button2" value="页面设置"
			onclick="WebBrowser.ExecWB(8,1);">
		<input  class="button2" value="打印预览"
			onclick="WebBrowser.ExecWB(7,1)">
		<input  class="button2" value="直接打印"
			onclick="WebBrowser.ExecWB(6,6)">
	</div>
</body>
</html:html>
