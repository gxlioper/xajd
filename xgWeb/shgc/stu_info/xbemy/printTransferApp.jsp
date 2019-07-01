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
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
	<meta http-equiv="Expires" http-equiv="0" />

	<meta name="Copyright" content="正方软件 zfsoft" />

<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<base target="_self">
<link id="csssDate" rel="stylesheet" rev="stylesheet"
	href="js/calendar.css" type="text/css" media="all" />
<style media='print'>.noPrin{display:none}
	</style>
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/stuinfoFunction.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
<script type="text/javascript" src="js/calendar-zh.js"></script>
<script type="text/javascript" src="js/calendar-setup.js"></script>
<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
</head>
<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<body>
	<html:form action="/xbemyStuStatus.do" method="post">
		<p align="center">
			<strong>宁夏回族自治区普通高等学校学生转学审批表 </strong>
		</p>
		<p align="right">
			宁教普学籍字 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 号
		</p>
		<table cellspacing="0" cellpadding="0" class="tbstyle" width="100%">
			<tr>
				<td width="43">
					<p align="center">
						姓名
					</p>
				</td>
				<td width="72">
					<p align="center">
						${rs.xm}
					</p>
				</td>
				<td width="48" colspan="2">
					<p align="center">
						性别
					</p>
				</td>
				<td width="48">
					<p align="center">
						${rs.xb}
					</p>
				</td>
				<td width="48" colspan="2">
					<p align="center">
						民族
					</p>
				</td>
				<td width="60" colspan="2">
					<p align="center">
						${rs.mzmc}
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						准考证号
					</p>
				</td>
				<td width="192" colspan="4">
					<p align="center">
						${rs.zkzh}
					</p>
				</td>
			</tr>
			<tr>
				<td width="43">
					<p align="center">
						入学时间
					</p>
				</td>
				<td width="72">
					<p align="center">
						${rs.rxsj}
					</p>
				</td>
				<td width="48" colspan="2">
					<p align="center">
						学号
					</p>
				</td>
				<td width="156" colspan="5">
					<p align="center">
						${rs.xh}
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						联系电话
					</p>
				</td>
				<td width="192" colspan="4">
					<p align="center">
						${rs.lxdh}
					</p>
				</td>
			</tr>
			<tr>
				<td width="43" rowspan="4">
					<p align="center">
						转
					</p>
					<p align="center">
						出
					</p>
					<p align="center">
						学
					</p>
					<p align="center">
						校
					</p>
				</td>
				<td width="72">
					<p align="center">
						校 &nbsp;&nbsp;&nbsp; 名
					</p>
				</td>
				<td width="168" colspan="6">
					<p align="center">
						${rs.zcxxmc}
					</p>
				</td>
				<td width="48" colspan="2" rowspan="4" valign="top">
					<p align="center">
						转
					</p>
					<p align="center">
						入
					</p>
					<p align="center">
						学
					</p>
					<p align="center">
						校
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						校名
					</p>
				</td>
				<td width="180" colspan="3">
					<p align="center">
						${rs.zrxxmc}
					</p>
				</td>
			</tr>
			<tr>
				<td width="72">
					<p align="center">
						专 &nbsp;&nbsp;&nbsp; 业
					</p>
				</td>
				<td width="168" colspan="6">
					<p align="center">
						${rs.zczymc}
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						专业
					</p>
				</td>
				<td width="180" colspan="3">
					<p align="center">
						${rs.zrzymc}
					</p>
				</td>
			</tr>
			<tr>
				<td width="72">
					<p align="center">
						转出年级
					</p>
				</td>
				<td width="168" colspan="6">
					<p align="center">
						${rs.zcnj}
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						转入年级
					</p>
				</td>
				<td width="180" colspan="3">
					<p align="center">
						${rs.zrnj}
					</p>
				</td>
			</tr>
			<tr>
				<td width="72">
					<p align="center">
						学 &nbsp;&nbsp;&nbsp; 制
					</p>
				</td>
				<td width="36">
					<p align="center">
						${rs.zcxz}
					</p>
				</td>
				<td width="72" colspan="3">
					<p align="center">
						学历层次
					</p>
				</td>
				<td width="60" colspan="2">
					<p align="center">
						${rs.zcxlcc}
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						学制
					</p>
				</td>
				<td width="36">
					<p align="center">
						${rs.zrxz}
					</p>
				</td>
				<td width="84">
					<p align="center">
						学历层次
					</p>
				</td>
				<td width="60">
					<p align="center">
						${rs.zrxlcc}
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td width="43">
					<p align="center">
						转学申请（理由）
					</p>
				</td>
				<td width="540" colspan="14">
					<p style="height:145px ">
						${rs.sqly}
					</p>
					<p>
						申请人： ${rs.xm}
					</p>
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp; ${rs.dqn} 年&nbsp;&nbsp;&nbsp; ${rs.dqy}月 &nbsp;&nbsp;&nbsp;
						${rs.dqr}日
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td width="43">
					<p align="center">
						转
					</p>
					<p align="center">
						出
					</p>
					<p align="center">
						学
					</p>
					<p align="center">
						校
					</p>
					<p align="center">
						意
					</p>
					<p align="center">
						见
					</p>
				</td>
				<td width="240" colspan="7">
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p>
						学校领导签字：
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						（公章）
					</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年 &nbsp;&nbsp; 月 &nbsp;&nbsp; 日
					</p>
				</td>
				<td width="48" colspan="2">
					<p align="center">
						转
					</p>
					<p align="center">
						入
					</p>
					<p align="center">
						学
					</p>
					<p align="center">
						校
					</p>
					<p align="center">
						意
					</p>
					<p align="center">
						见
					</p>
				</td>
				<td width="252" colspan="5">
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p>
						学校领导签字：
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						（公章）
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年 &nbsp;&nbsp; 月 &nbsp;&nbsp; 日
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td width="43">
					<p align="center">
						转出省教育行政部门意见
					</p>
				</td>
				<td width="240" colspan="7">
						<br/>
						<br/>
						经办人：					
						<br/>
						<br/>
						处长：
						<br/>
						<br/>
						主管厅 ( 委 ) 领导：
						<br/>
						<br/>
					
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（公章）
					
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年 &nbsp;&nbsp; 月 &nbsp;&nbsp; 日
					</p>
				</td>
				<td width="48" colspan="2">
					<p align="center">
						转入省教育行政部门意见
					</p>
				</td>
				<td colspan="5">
					<br/>
						<br/>
						经办人：					
						<br/>
						<br/>
						处长：
						<br/>
						<br/>
						主管厅 ( 委 ) 领导：
						<br/>
						<br/>
					
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（公章）
					
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年 &nbsp;&nbsp; 月 &nbsp;&nbsp; 日
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td width="43">
					<p align="center">
						备
					</p>
					<p align="center">
						注
					</p>
				</td>
				<td width="540" colspan="14">
					<p>
						1 、办理转学手续时携带此表及招生录检表复印件，加盖学校学籍管理部门印章。
					</p>
					<p>
						2
						、本表区内转学一式四份，跨省转学一式五份，转出学校、转入学校、省教育行政部门及学校所在地公安户籍部门各存一份。转出学校和转入学校持此表到学校所在地公安户籍部门办理户籍迁移手续。
					</p>
					<p>
						3 、学历层次是指博士、硕士、本科、专科（高职）。
					</p>
				</td>
			</tr>
		</table>
	</html:form>
</body>
<div class='noPrin' align="center">
	<input type='button' class='button2' value='页面设置'
		onclick="WebBrowser.ExecWB(8,1);return false;">
	<input type='button' class='button2' value='打印预览'
		onclick="WebBrowser.ExecWB(7,1);return false;">
	<input type='button' class='button2' value='直接打印'
		onclick="WebBrowser.ExecWB(6,6);return false;">
</div>
</html:html>

