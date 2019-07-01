<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
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
</head>
<body>
	<html:form action="/pjpyshcbyswh">
		<div align="center" style="font-size:18px;font:'黑体' ">
			&nbsp;
		</div>
		<br />
		<h3 align="center">
			${xxmc}奖学金申请表
		</h3>
		<table width="98%" class="printstyle" border="1">
			<tr>
				<th height="38" colspan="2" width="13%" scope="col">
					姓&nbsp;&nbsp;名
				</th>
				<td colspan="2" style="width:20%" scope="col" align="center">
					${rs.xm}
				</td>
				<th width="8%" scope="col">
					性&nbsp;&nbsp;别
				</th>
				<td colspan="2" scope="col" style="width:10%" align="center">
					${rs.xb}
				</td>
				<th width="12%" scope="col">
					出生年月
				</th>
				<td colspan="2" scope="col" align="center" width="13%">
					${rs.csrq}
				</td>
				<th width="10%" scope="col">
					民&nbsp;&nbsp;族
				</th>
				<td colspan="2" scope="col" align="center">
					${rs.mz}
				</td>
			</tr>
			<tr>
				<th height="38" colspan="2" scope="col">
					生源地
				</th>
				<td colspan="2" scope="col" align="center">
					${rs.syd}
				</td>
				<th colspan="3" scope="col">
					政治面貌
				</th>
				<td colspan="3" align="center">
					${rs.zzmm}
				</td>
				<th scope="col">
					职&nbsp;&nbsp;务
				</th>
				<td colspan="2" scope="col" align="center">
					${rs.drzw}
				</td>
			</tr>
			<tr>
				<th height="38" colspan="2" scope="col">
					专&nbsp;&nbsp;业
				</th>
				<td colspan="5" scope="col" align="center">
					${rs.zymc}
				</td>
				<th scope="col">
					学&nbsp;&nbsp;号
					<br>
					(10位)
				</th>
				<td colspan="5" scope="col" align="center">
					${rs.xh}
				</td>
			</tr>
			<tr>
				<th height="38" colspan="2" scope="col">
					学&nbsp;&nbsp;期
				</th>
				<td colspan="5" scope="col" align="center">
					${rs.xn}${rs.xq}
				</td>
				<th scope="col">
					绩&nbsp;&nbsp;点
				</th>
				<td colspan="5" scope="col" align="center">
					${rs.jd}
				</td>
			</tr>
			<tr>
				<th scope="row" colspan="13">
					<p align="left">
						&nbsp;&nbsp; 各科成绩：
					</p>
					<p align="left">
						<br />					
					<p>
						&nbsp;
					</p>
				</th>
			</tr>
			<tr>
				<th scope="row" colspan="13">
					<p align="left">
						&nbsp;&nbsp; 个人申请：
					</p>
					<p align="left">
						<br />
						
					</p>
					<p>
						&nbsp;
					</p>
					<p align="right">
						签名盖章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</th>
			</tr>
			<tr>
				<th scope="row" colspan="13">
					<p align="left">
						&nbsp;&nbsp;辅导员意见：(需填获奖等级)
					</p>
					<p align="left">
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p align="right">
						签名盖章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</th>
			</tr>
			<tr>
				<th scope="row" colspan="13">
					<p align="left">
						&nbsp;&nbsp;<bean:message key="lable.xb" />意见：
					</p>
					<p align="left">
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p align="right">
						签名盖章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</th>
			</tr>
			<tr>
				<th scope="row" colspan="13">
					<p align="left">
						&nbsp;&nbsp;学生处意见：
					</p>
					<p align="left">
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p align="right">
						签名盖章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</th>
			</tr>
		</table>
注：&nbsp;&nbsp;&nbsp;&nbsp;1.此表一式两份；本人档案、<bean:message key="lable.xb" />各一份<br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.用钢笔填写；字迹清楚，经系、校盖章方有效
    </html:form>
    
</body>
</html:html>
