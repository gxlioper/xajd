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
		<base target="_self" />
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
		<html:form action="/nblgxy_gygl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：公寓管理 - 信息维护 - 特殊学生信息
				</div>
			</div>

			<div align="center" style="font: bold;font-size: 20px" id="printTit">
				浙江大学宁波理工<bean:message key="lable.xsgzyxpzxy" />公寓楼内需要关注的学生信息卡
			</div>
			<div id="taPrint">
				<div align="right">
					填卡时间：&nbsp;&nbsp;
					<bean:write name="rs" property="rq" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					填卡人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				<table width="100%" class="tbstyle">
					<tr>
						<td align="center" width="10%">
							姓&nbsp;名
						</td>
						<td colspan="2" width="7%">
							<bean:write name="rs" property="xm" />
						</td>
						<td width="5%">
							学号
						</td>
						<td colspan="2" width="10%">
							<bean:write name="rs" property="xh" />
						</td>
						<td width="5%">
							民族
						</td>
						<td width="15%">
							<bean:write name="rs" property="mzmc" />
						</td>
						<td width="10%">
							政治面貌
						</td>
						<td width="10%">
							<bean:write name="rs" property="zmmmc" />
						</td>
					</tr>
					<tr>
						<td align="center">
							分&nbsp;院
						</td>
						<td colspan="3">
							<bean:write name="rs" property="xymc" />
						</td>
						<td>
							专业班级
						</td>
						<td colspan="3">
							<bean:write name="rs" property="zymc" />
							&nbsp;
							<bean:write name="rs" property="bjmc" />
						</td>
						<td>
							楼幢寝室
						</td>
						<td>
							<bean:write name="rs" property="ssmc" />
						</td>
					</tr>
					<tr>
						<td align="center">
							手&nbsp;机
						</td>
						<td colspan="3">
							长号&nbsp;&nbsp;
							<bean:write name="rs" property="lxfs_ch" />
						</td>
						<td colspan="3">
							短号&nbsp;&nbsp;
							<bean:write name="rs" property="lxfs_dh" />
						</td>
						<td>
							寝室电话
						</td>
						<td colspan="2">
							<bean:write name="rs" property="qsdh" />
						</td>
					</tr>
					<tr>
						<td align="center">
							特&nbsp;长
							<br />
							爱&nbsp;好
						</td>
						<td colspan="9" >
							<bean:write name="rs" property="ahtc" />
						</td>
					</tr>
					<tr>
						<td align="center">
							家&nbsp;庭
							<br />
							状&nbsp;况
						</td>
						<td colspan="9">
							<bean:write name="rs" property="jtzk" />
						</td>
					</tr>
					<tr>
						<td align="center">
							学习成
							<br />
							绩和学
							<br />
							风检查
							<br />
							情&nbsp;况
						</td>
						<td colspan="9">
							<bean:write name="rs" property="xfcj" />
						</td>
					</tr>
					<tr>
						<td align="center">
							获&nbsp;奖
							<br />
							荣&nbsp;誉
						</td>
						<td colspan="9">
							<bean:write name="rs" property="hjry" />
						</td>
					</tr>
					<tr>
						<td align="center">
							违&nbsp;纪
							<br />
							情&nbsp;况
						</td>
						<td colspan="9">
							<bean:write name="rs" property="wjqk" />
						</td>
					</tr>
					<tr>
						<td align="center">
							性格特
							<br />
							征和行
							<br />
							为习惯
						</td>
						<td colspan="9">
							<bean:write name="rs" property="xgtzxg" />
						</td>
					</tr>
					<tr>
						<td align="center">
							人际交
							<br />
							往和社
							<br />
							会活动
							<br />
							情&nbsp;况
						</td>
						<td colspan="9">
							<bean:write name="rs" property="rjjwshhd" />
						</td>
					</tr>
					<tr>
						<td align="center">
							关注、谈
							<br />
							心情况
						</td>
						<td colspan="9">
							<bean:write name="rs" property="gzqk" />
						</td>
					</tr>
					<tr>
						<td align="center">
							备&nbsp;注
						</td>
						<td colspan="9">
							<bean:write name="rs" property="bz" />
						</td>
					</tr>
				</table>
				<br/>
				<div align="right" style="font: bold;font-size: 15px">
					浙江大学宁波理工<bean:message key="lable.xsgzyxpzxy" />学工部制
				</div>
			</div>
			<br>
			<br>
			<div align="center">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input  type="button" value="打印" name="button_print"
				onclick="expTab('taPrint','','printTit')">
			</div>	
		</html:form>
	</body>
</html>
