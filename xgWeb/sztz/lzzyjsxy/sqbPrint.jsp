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
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<base target="_self" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>	
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
<style media="print" type="text/css">
  .brk{
	page-break-after:always;
   }
   .noPrin{display:none}
</style>		
	<body bgcolor="#FFFFFF" class="Normal" lang=ZH-CN>
		<div class=Section1 style='layout-grid:15.6pt' align="center">
		<br><br><br><br>
			
			<b>柳州职业技术<bean:message key="lable.xsgzyxpzxy" />第二课堂素质教育活动申请表</b>
			<table class=MsoTableGrid border=1 cellspacing=0 cellpadding=0
				width=648>
				<tr>
					<td width=108 class="Normal">
						活动单位名称
					</td>
					<td width=168 class="Normal">
						${rs.xymc}
					</td>
					<td width=110 class="Normal">
						指导老师
					</td>
					<td width=76 class="Normal">
						${rs.xmsbr}
					</td>
					<td width=81 class="Normal">
						活动时间
					</td>
					<td width=105 class="Normal">
						${rs.zbsj}
					</td>
				</tr>
				<tr>
					<td width=108 class="Normal">
					   活动项目名称
					</td>
					<td width=168 class="Normal">
						${rs.xmmc}
					</td>
					<td width=110 class="Normal">
						活动项目类型
					</td>
					<td width=76 class="Normal">
						${rs.xmjb}
					</td>
					<td width=81 class="Normal">
						活动分值
					</td>
					<td width=105 class="Normal">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td width=108 class="Normal">
						活动参加对象
					</td>
					<td width=168 class="Normal">
						${rs.hddx}
					</td>
					<td width=110 class="Normal">
						活动计划人数
					</td>
					<td width=76 class="Normal">
						${rs.hdjhrs}
					</td>
					<td width=81 class="Normal">
						活动形式
					</td>
					<td width=105 class="Normal">
						${rs.hdxs}
					</td>
				</tr>
				<tr>
					<td class="Normal" align="center">
						<br> <br><br> <br>活动主题<br>及主要内容 <br> <br><br><br>
					</td>
					<td  class="Normal" colspan="5"  valign="bottom">
<%--						（有活动方案，请附方案后简写）--%>
						<p>
						${rs.xmms}
						</p>
						<p align="right" >
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						签章:
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 日
						</p>
					</td>
				</tr>
				<tr>
					<td  class="Normal" align="center"  >
						<br><br>系（部） <br>领导意见 <br><br> 
					</td>
					<td  class="Normal" align="right" valign="bottom" colspan="5">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						签章:
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 日
					</td>
				</tr>
				<tr>
					<td  class="Normal" align="center" >
						 <br><br>学生工作处<br>(团委)意见 <br><br>
					</td>
					<td  class="Normal" align="right"  valign="bottom" colspan="5">
					    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						签章:
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 日
					</td>
				</tr>
			</table>
			<div align="center">
			注:此表一式两份,一份留申请部门留存,一份由学生工作处(团委)留存
			</div>
		</div>
		<div class='noPrin' align="center">
				<input type='button' class='button2' value='页面设置'
					onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='打印预览'
					onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='直接打印'
					onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
	</body>
</html>

