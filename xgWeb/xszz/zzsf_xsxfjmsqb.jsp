<%@ page language="java" contentType="text/html;charset=GBK"%>

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
<base target="_self">
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
	<meta http-equiv="Expires" http-equiv="0" />
	<meta name="Copyright" content="正方软件 zfsoft" />
</head>
<%
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/xszzFunction.js"></script>
<body>
	<html:form action="zzsf_xsxfjmsqb.do" method="post">
		<input type="hidden" name="tableName" id="tableName"
			value="<bean:write name="sqlb" />" />
		<table width="100%" border="1" class="tbstyle" id="theTable">
			<tr>
				<td colspan="8">
					<p align="center" style="font-size:24px">
						漳州师院&nbsp;
						<bean:write name='rs' property="nd" />
						&nbsp;学年度经济困难学生减、免学费登记表
					</p>
				</td>
			</tr>
			<tr>
				<td scope="col" width="10%">
					<div align="center">
						系别
					</div>
				</td>
				<td scope="col" width="15%">
					<div align="center">
						<bean:write name='rs' property="zymc" />
					</div>
				</td>
				<td scope="col" width="10%">
					<div align="center">
						学生类别
					</div>
				</td>
				<td scope="col" width="15%">
					<div align="center">
						<bean:write name='rs' property="xslbmc" />
					</div>
				</td>
				<td scope="col" width="10%">
					<div align="center">
						班级
					</div>
				</td>
				<td scope="col" width="15%">
					<div align="center">
						<bean:write name='rs' property="bjmc" />
					</div>
				</td>
				<td scope="col" width="10%">
					<div align="center">
						学号
					</div>
				</td>
				<td scope="col" width="15%">
					<div align="center">
						<bean:write name='rs' property="xh" />
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						专业
					</div>
				</td>
				<td colspan="7">
					<bean:write name='rs' property="zymc" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						姓名
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="xm" />
					</div>
				</td>
				<td>
					<div align="center">
						出生年月
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="csrq" />
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						身份证号
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="sfzh" />
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						生源地
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="syd" />
					</div>
				</td>
				<td>
					<div align="center">
						政治面貌
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="zzmm" />
					</div>
				</td>
				<td>
					<div align="center">
						性别
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="xb" />
					</div>
				</td>
				<td>
					<div align="center">
						家长姓名
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jzxm" />
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						家庭住址
					</div>
				</td>
				<td colspan="5">
					<bean:write name='rs' property="jtdz" />
				</td>
				<td>
					<div align="center">
						邮编
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="yzbm" />
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						家庭电话
					</div>
				</td>
				<td colspan="2">
					<bean:write name='rs' property="jtdh" />
				</td>
				<td>
					<div align="center">
						宿舍地址
					</div>
				</td>
				<td colspan="2">
					<bean:write name='rs' property="ssdz" />
				</td>
				<td>
					<div align="center">
						宿舍电话
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="ssdh" />
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						家庭经济情况（人均月收入）
					</div>
				</td>
				<td colspan="7">
					<bean:write name='rs' property="jtjjqk" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						院、系困难补助情况
					</div>
				</td>
				<td colspan="3">
					<bean:write name='rs' property="yxknbzqk" />
				</td>
				<td>
					<div align="center">
						勤工岗位
					</div>
				</td>
				<td colspan="3">
					<bean:write name='rs' property="qggw" />
				</td>
			</tr>
			<tr>
				<td rowspan="2" scope="row">
					<div align="center">
						平时表现情况
					</div>
				</td>
				<td colspan="5" rowspan="2">
					<bean:write name='rs' property="psbxqk" />
				</td>
				<td>
					<div align="center">
						申请种类
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="sqzl" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						申请金额
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="sqje" />
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						个人获奖情况
					</div>
				</td>
				<td colspan="3">
					<bean:write name='rs' property="gehjqk" />
				</td>
				<td>
					<div align="center">
						学习成绩(班级排名)
					</div>
				</td>
				<td colspan="3">
					<bean:write name='rs' property="xxcj" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />意见
					</div>
				</td>
				<td colspan="7">
					<div align="left">
						同意减免学费&nbsp;
						<bean:write name='rs' property="tyjmje" />
						元&nbsp;&nbsp; (￥&nbsp;
						<bean:write name='rs' property="tyjmjedx" />
						)
					</div>
					<bean:write name='rs' property="xyshyj" />
					<div align="right">
						分管领导签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						（章）&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						学校意见
					</div>
				</td>
				<td colspan="7">
					<bean:write name='rs' property="xxshyj" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						院领导意见
					</div>
				</td>
				<td colspan="7">
					&nbsp;
					<br />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						备注
					</div>
				</td>
				<td colspan="7">
					<bean:write name='rs' property="bz" />
				</td>
			</tr>
			<tr>
				<td colspan="8" scope="row">
					注：（1）新生学习成绩填写高考分数及班级排名。
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','')" />
	</div>
</body>
</html:html>
