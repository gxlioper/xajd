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
				<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
		<style media="print">
.noprint{
	display:none;
}
.print{
	display:block;
}
</style>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/nblgxy_gygl" method="post">
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<div align="center">
					<b><bean:write name="rs" property="nf" />年<bean:write name="rs" property="yf" />月份入住公寓辅导员工作月报表</b>
				</div>
				<div align="right">
					汇报工作时间段：
					<bean:write name="rs" property="gzksrq" />
					－
					<bean:write name="rs" property="gzjsrq" />
				</div>
				<div align="center">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="15%">
								辅导员姓名
							</td>
							<td>
								<bean:write name="rs" property="xm" />
							</td>
							<td width="15%">
								入住楼栋
							</td>
							<td>
								<bean:write name="rs" property="rzqsh" />
							</td>
						</tr>
						<tr>
							<td>
								负责楼栋
							</td>
							<td colspan="3">
								<bean:write name="rs" property="fzld" />
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tr>
							<td rowspan="5" width="8%">
								<br>
								<br>
								<p align="center">
									主
								</p>
								<p align="center">
									要
								</p>
								<p align="center">
									工
								</p>
								<p align="center">
									作
								</p>
								<p align="center">
									内
								</p>
								<p align="center">
									容
								</p>
								<br>
								<br>
							</td>
							<td height="60px" title="每月一次寝室普查时间、内容、相关人员和整体检查情况简要反馈">
								<bean:write name="rs" property="gznr_jyfk" />
							</td>
						</tr>
						<tr>
							<td height='60px' title="每周值班时间内接待学生、谈心、走访寝室等的简要记录">
								<bean:write name="rs" property="jgznr_jyjl" />
							</td>
						</tr>
						<tr>
							<td height='60px' title="召开楼层长等学生骨干会议的时间、地点和主要会议内容">
								<bean:write name="rs" property="gznr_jhynr" />
							</td>
						</tr>
						<tr>
							<td height='60px' title="一个月来需要反馈的楼内学生动态">
								<bean:write name="rs" property="gznr_jxsdt" />
							</td>
						</tr>
						<tr>
							<td height='60px' title="其他工作内容">
								<bean:write name="rs" property="gznr_jqtnr" />
							</td>
						</tr>
						<tr>
							<td rowspan="3">
								<br>
								<br>
								<p align="center">
									需要
								</p>
								<p align="center">
									反馈
								</p>
								<p align="center">
									的情
								</p>
								<p align="center">
									况和
								</p>
								<p align="center">
									工作
								<p align="center">
									建议
								</p>
								<br>
								<br>
							</td>
							<td height='60px'
								title="反馈至学工部：（存在的困难、工作建议、重点工作进度情况、需要备案的突发事件处理等）">
								<bean:write name="rs" property="fkyj_xyfk" />
							</td>
						</tr>
						<tr>
							<td height='60px' title="反馈至分院：（包括学生动态、不稳定苗头、需关注学生等，注明分院）">
								<bean:write name="rs" property="fkyj_xgbfk" />
							</td>
						</tr>
						<tr>
							<td height='60px' title="反馈至生活园区管理服务中心：（涉及日常管理及其他需要协同的工作）">
								<bean:write name="rs" property="fkyj_fwzxfk" />
							</td>
						</tr>
						<tr>
							<td align="center">
								备注
							</td>
							<td>
								<bean:write name="rs" property="bz" />
							</td>
						</tr>
					</table>
				</div>
			</logic:notEmpty>
			<br>
			<br>
			<div class="buttontool noprint" align="center">
				<input type="button" class="button2" value="页面设置"
					onclick="WebBrowser.ExecWB(8,1);">
				<input type="button" class="button2" value="打印预览"
					onclick="WebBrowser.ExecWB(7,1)">
				<input type="button" class="button2" value="直接打印"
					onclick="WebBrowser.ExecWB(6,6)">
			</div>
		</html:form>
	</body>
</html>
