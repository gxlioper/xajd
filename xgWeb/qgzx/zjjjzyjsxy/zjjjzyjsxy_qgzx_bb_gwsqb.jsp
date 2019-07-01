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
<html:html locale="true">
<head>

	<title>qgzx_bb_gwsqb.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

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
<script language="javascript" src="js/function.js"></script>
<body>
	<h2 align="center">
		学生勤工助学申请表
	</h2>
	<h2 align="center">
		(${rsTime.year}年${rsTime.month} 月)
	</h2>
	<table class="tbstyle" align="center" width="80%;height:100%">
		<tr>
			<td width="55" height="34">
				姓名
			</td>
			<td width="116">
				${rs.xm}
			</td>
			<td width="37">
				性别
			</td>
			<td width="100">
				${rs.xb}
			</td>
			<td width="72">
				分院（部）
			</td>
			<td width="71">
				${rs.xymc }
			</td>
			<td width="55">
				班级
			</td>
			<td width="98">
				${rs.bjmc}
			</td>
			<td width="69">
				学号
			</td>
			<td width="43">
				${rs.xh}
			</td>
		</tr>
		<tr>
			<td height="39">
				籍贯
			</td>
			<td>
				${rs.jg}
			</td>
			<td>
				专业
			</td>
			<td colspan="3">
				${rs.zymc}
			</td>
			<td>
				应聘岗位
			</td>
			<td>
				${rs.gwmc }
			</td>
			<td>
				是否困难生
			</td>
			<td>
				${rs.isPks }
			</td>
		</tr>
		<tr>
			<td height="36">
				卡号
			</td>
			<td colspan="9">
				${rs.kh}
			</td>
		</tr>
		<tr>
			<td height="36">
				特长
			</td>
			<td colspan="9">
				${rs.yhtc}
			</td>
		</tr>
		<tr>
			<td>
				<p align="center">
					家
				</p>
				<p align="center">
					庭
				</p>
				<p align="center">
					情
				</p>
				<p align="center">
					况
				</p>
			</td>
			<td colspan="9">
				${rs.jtjjknqk}
			</td>
		</tr>
		<tr>
			<td>
				<p align="center">
					应
				</p>
				<p align="center">
					聘
				</p>
				<p align="center">
					理
				</p>
				<p align="center">
					由
				</p>
			</td>
			<td colspan="9">
				${rs.xssq}
			</td>
		</tr>
	</table>
	<p>
		&nbsp;
	</p>
</body>
</html:html>
