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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();">
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：评奖评优 - 奖学金申请 - 填写申请表
				</div>
			</div>
				<div align="center" style="font: 15px"><b>${bjmc }综合素质测评成绩及排名汇总表</b></div>
				<logic:empty name="rs">
				<br/><br/>
				<p align="center">无任何记录!</p>
				</logic:empty>
				<logic:notEmpty name="rs">
				<br/>
					<table width="100%" align="center" class="tbstyle">
						<thead>
							<tr>
								<td align="center">
									行号
								</td>
								<td align="center">
									学年
								</td>
								<td align="center">
									学期
								</td>
								<td align="center">
									学号
								</td>
								<td align="center">
									姓名
								</td>
								<td align="center">
									德成绩
								</td>
								<td align="center">
									德成绩排名
								</td>
								<td align="center">
									智成绩
								</td>
								<td align="center">
									智成绩排名
								</td>
								<td align="center">
									体成绩
								</td>
								<td align="center">
									体成绩排名
								</td>
								<td align="center">
									技能加分
								</td>
								<td align="center">
									技能加分排名
								</td>
								<td align="center">
									综合测评总分
								</td>
								<td align="center">
									综合测评总分排名
								</td>
							</tr>
						</thead>
						<logic:iterate id="s" name="rs">
						<tr>
							<logic:iterate id="v" name="s">
							<td nowrap="nowrap">
								${v }
							</td>
							</logic:iterate>
						</tr>
						</logic:iterate>
					</table>
				</logic:notEmpty>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="Close();return false;" style="width:90px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
