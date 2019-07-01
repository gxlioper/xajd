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
	<base target="_self">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />

		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<base target="_self">
		<script language="javascript" src="js/function.js"></script>
	</head>
	<body>

		<html:form action="/qgzxNblg" method="post">
			<p align="center">
				<strong><bean:write name="xxmc"/>学生勤工助学岗位申请表 </strong>
			</p>
			<p align="center">
				<strong>&nbsp; </strong>
			</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				所在分院：<bean:write name="rs" property="xymc"/> 申请时间：<bean:write name="rs" property="year"/>-<bean:write name="rs" property="month"/>-<bean:write name="rs" property="day"/>
			</p>
			<table cellspacing="0" cellpadding="0" class="tbstyle" align="center">
				<tr>
					<td width="72">
						<p align="center">
							姓 名
						</p>
					</td>
					<td width="84">
						<p align="center">
							<bean:write name="rs" property="xm"/>
						</p>
					</td>
					<td width="72">
						<p align="center">
							学 号
						</p>
					</td>
					<td width="120">
						<p align="center">
							<bean:write name="rs" property="xh"/>
						</p>
					</td>
					<td width="72">
						<p align="center">
							性 别
						</p>
					</td>
					<td width="132">
						<p>
							<bean:write name="rs" property="xb"/>
						</p>
					</td>
				</tr>
				<tr>
					<td width="72">
						<p align="center">
							宿 舍
						</p>
					</td>
					<td width="84">
						<p align="center">
							<bean:write name="rs" property="ssbh"/>
						</p>
					</td>
					<td width="72">
						<p align="center">
							联 系
						</p>
						<p align="center">
							方 式
						</p>
					</td>
					<td width="120">
						<p align="center">
							<bean:write name="rs" property="lxdh"/>
						</p>
					</td>
					<td width="72">
						<p align="center">
							是否参加其他岗位
						</p>
					</td>
					<td width="132">
						<p align="center">
							<bean:write name="rs" property="sfcjqggw"/>
						</p>
					</td>
				</tr>
				<tr>
					<td width="72">
						<p align="center">
							专 业
						</p>
						<p align="center">
							班 级
						</p>
					</td>
					<td width="276" colspan="3">
						<p align="center">
							<bean:write name="rs" property="zymc"/><bean:write name="rs" property="bjmc"/>
						</p>
					</td>
					<td width="72">
						<p align="center">
							是 否
						</p>
						<p align="center">
							困难生
						</p>
					</td>
					<td width="132">
						<p align="center">
							<bean:write name="rs" property="sfpkf"/>
						</p>
					</td>
				</tr>
				<tr>
					<td width="156" colspan="2">
						<p align="center">
							申请勤工助学岗位意向
						</p>
					</td>
					<td width="396" colspan="4">
						<p align="center">
							<bean:write name="rs" property="gwmc"/>
						</p>
					</td>
				</tr>
				<tr>
					<td width="156" colspan="2">
						<p align="center">
							可参加勤工助学时间
						</p>
					</td>
					<td width="396" colspan="4">
						<p align="center">
							<bean:write name="rs" property="kcjqgzxsj"/>
						</p>
					</td>
				</tr>
				<tr>
					<td width="156" colspan="2">
						<p align="center">
							农 行 卡 号
						</p>
					</td>
					<td width="396" colspan="4">
						<p align="center">
							<bean:write name="rs" property="kh"/>
						</p>
					</td>
				</tr>
				<tr>
					<td width="72">
						<p align="center">
							用
						</p>
						<p align="center">
							人
						</p>
						<p align="center">
							单
						</p>
						<p align="center">
							位
						</p>
						<p align="center">
							意
						</p>
						<p align="center">
							见
						</p>
					</td>
					<td width="480" colspan="5" valign="top">
						<p>
							是否同意聘用：
						</p>
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
							&nbsp;
						</p>
						<p>
							签名（盖章）： 年 月 日
						</p>
					</td>
				</tr>
				<tr>
					<td width="72">
						<p align="center">
							分
						</p>
						<p align="center">
							院
						</p>
						<p align="center">
							意
						</p>
						<p align="center">
							见
						</p>
					</td>
					<td width="480" colspan="5" valign="top">
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
							&nbsp;
						</p>
						<p>
							签名（盖章）： 年 月 日
						</p>
					</td>
				</tr>
				<tr>
					<td width="72">
						<p align="center">
							学
						</p>
						<p align="center">
							院
						</p>
						<p align="center">
							学
						</p>
						<p align="center">
							生
						</p>
						<p align="center">
							处
						</p>
						<p align="center">
							意
						</p>
						<p align="center">
							见
						</p>
					</td>
					<td width="480" colspan="5" valign="top">
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
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							签名（盖章）： 年 月 日
						</p>
					</td>
				</tr>
				<tr>
					<td width="72">
						<p align="center">
							备
						</p>
						<p align="center">
							注
						</p>
					</td>
					<td width="480" colspan="5" valign="top">
						<p>
							<bean:write name="rs" property="bz"/>
						</p>
					</td>
				</tr>
			</table>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				注：⑴、此表一式两份，一份学生处备案，一份用人单位备案。
			</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				⑵、学生凭此表到用人单位报到。
			</p>
		</html:form>
	</body>
</html>
