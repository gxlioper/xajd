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
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
</head>
<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<body>
	<html:form action="/xbemyStuStatus.do" method="post">
		<p align="center">
			西北第二民族<bean:message key="lable.xsgzyxpzxy" />
		</p>
		<p align="center">
			学 生 学 籍 变 动 审 批 表
		</p>
		<table cellspacing="0" cellpadding="0" class="tbstyle" width="100%">
			<tr>
				<td width="88" colspan="2">
					<p align="center">
						姓 &nbsp; 名
					</p>
				</td>
				<td width="120" colspan="2">
					<p align="center">
						${rs.xm}
					</p>
				</td>
				<td width="60">
					<p align="center">
						性 &nbsp; 别
					</p>
				</td>
				<td width="48" colspan="2">
					<p align="center">
						${rs.xb}
					</p>
				</td>
				<td width="60" colspan="3">
					<p align="center">
						民族
					</p>
				</td>
				<td width="84" colspan="2">
					<p align="center">
						${rs.mzmc}
					</p>
				</td>
				<td width="84">
					<p align="center">
						出生年月日
					</p>
				</td>
				<td width="92" colspan="2">
					<p align="center">
						${rs.csrq}
					</p>
				</td>
			</tr>
			<tr>
				<td width="88" colspan="2">
					<p align="center">
						学 &nbsp; 号
					</p>
				</td>
				<td width="120" colspan="2">
					<p align="center">
						${rs.xh}
					</p>
				</td>
				<td width="156" colspan="5">
					<p align="center">
						变动前所在专业
					</p>
					<p align="center">
						年级、班级
					</p>
				</td>
				<td colspan="2">					
					${rs.ydqzymc}${rs.ydqnj}${rs.ydqbjmc}					
				</td>
				<td width="98" colspan="3">
					<p align="center">
						生源地
					</p>
				</td>
				<td width="90">
					<p align="center">
						${rs.syd}
					</p>
				</td>
			</tr>
			<tr>
				<td width="136" colspan="3">
					<p align="center">
						学籍变动种类
					</p>
				</td>
				<td width="72">
					<p align="center">
						${rs.ydlbmc}
					</p>
				</td>
				<td width="120" colspan="4">
					<p align="center">
						起止时间
					</p>
				</td>
				<td width="120" colspan="3">
					<p align="center">
						${rs.qssj}- ${rs.jzsj}
					</p>
				</td>
				<td width="98" colspan="3">
					<p align="center">
						变动后专业
					</p>
					<p align="center">
						年级、班级
					</p>
				</td>
				<td>
					<p align="center">
						${rs.ydhzymc}${rs.ydhnj}${rs.ydhbjmc}
					</p>
				</td>
			</tr>
			<tr>
				<td width="136" colspan="3">
					<p align="center">
						家庭住址及电话
					</p>
				</td>
				<td width="500" colspan="12">
					<p align="center">
						${rs.jtdz} &nbsp; &nbsp; &nbsp; ${rs.jtdh}
					</p>
				</td>
			</tr>
			<tr>
				<td width="76">
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						原
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						因
					</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
				<td colspan="14">
					<p align="center" style="height:125px">
						${rs.sqly}
					</p>
					<p>
						申请人签名：${rs.xm}
					</p>
					<p align="right">
						${rs.dqn}年 &nbsp;&nbsp;&nbsp;${rs.dqy}月
						&nbsp;&nbsp;&nbsp;${rs.dqr} 日
					</p>
				</td>
			</tr>
			<tr>
				<td width="76">
					<p align="center">
						学生
					</p>
					<p align="center">
						所在
					</p>
					<p align="center">
						院意见
					</p>
				</td>
				<td width="228" colspan="5">
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp; 签章：
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年 &nbsp; 月 &nbsp; 日
					</p>
				</td>
				<td width="72" colspan="4">
					<p align="center">
						后勤处
					</p>
					<p align="center">
						意 见
					</p>
				</td>
				<td width="260" colspan="5">
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 签章：
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年 &nbsp; 月 &nbsp; 日
					</p>
				</td>
			</tr>
			<tr>
				<td width="76">
					<p align="center">
						招就处
					</p>
					<p align="center">
						意 见
					</p>
				</td>
				<td width="228" colspan="5">
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp; 签章：
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年 &nbsp; 月 &nbsp; 日
					</p>
				</td>
				<td width="72" colspan="4">
					<p align="center">
						财务处
					</p>
					<p align="center">
						意 见
					</p>
				</td>
				<td width="260" colspan="5">
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 签章：
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年 &nbsp; 月 &nbsp; 日
					</p>
				</td>
			</tr>
			<tr>
				<td width="76">
					<p align="center">
						教务处
					</p>
					<p align="center">
						意 见
					</p>
				</td>
				<td width="228" colspan="5">
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p>
						签章：
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年 &nbsp; 月 &nbsp; 日
					</p>
				</td>
				<td width="72" colspan="4">
					<p align="center">
						学生处
					</p>
					<p align="center">
						意 见
					</p>
				</td>
				<td width="260" colspan="5">
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						签章：
					</p>
					<p align="center">
						年 &nbsp; 月 &nbsp; 日
					</p>
				</td>
			</tr>
			<tr>
				<td width="76">
					<p align="center">
						校领导
					</p>
					<p align="center">
						批 示
					</p>
				</td>
				<td width="560" colspan="14">
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						签章：
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年 &nbsp; 月 &nbsp; 日
					</p>
				</td>
			</tr>
		</table>
		<p>
			<strong>说明：此表签批后，原件 存学生 处，教务处、财务处、招就处、后勤处、学生所在<bean:message key="lable.xsgzyxpzxy" />及本人各持复印件一份 </strong>
		</p>
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

