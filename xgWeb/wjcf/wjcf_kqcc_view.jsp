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
	<script language="javascript">
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/wjcfFuction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/addkqxx.do" method="post">
		<html:text  property="xn_id" styleId="xn_id" style="display:none;"></html:text>
			<div class="title">
				<div class="title_img" id="title_m">
					
					<logic:equal value="11049" name="xxdm">
						当前所在位置：日常管理 - 考勤管理 - 增加考勤信息
					</logic:equal>
					<logic:notEqual value="11049" name="xxdm">
						当前所在位置：违纪处分 - 学生考勤 - 考勤信息维护 - 增加考勤信息
					</logic:notEqual>
				</div>
			</div>
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							考勤信息
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right">
						学年：
					</td>
					<td align="left">
						<html:text  property="xn" styleId="xn" readonly="true"></html:text>
					</td>
					<td align="right">
						学期：
					</td>
					<td align="left">
						<html:text  property="xq" styleId="xq" readonly="true"></html:text>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						班级名称：
					</td>
					<td align="left">
						<html:text  property="bjmc" readonly="true"></html:text>
					</td>
					<td align="right">
						课程名称：
					</td>
					<td align="left">
						<html:text  property="kcmc" readonly="true"></html:text>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<font color="red">*</font>实到人数：
					</td>
					<td align="left">
						<html:text  property="sdrs" onblur="" readonly="true"></html:text>
					</td>
					<td align="right">
						<font color="red">*</font>请假人数：
					</td>
					<td align="left">
						<html:text  property="qjrs" onblur="" readonly="true"></html:text>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<font color="red">*</font>应到人数：
					</td>
					<td align="left">
						<html:text  property="ydrs" onblur="" readonly="true"></html:text>
					</td>
					<td align="right">
						<font color="red">*</font>缺勤：
					</td>
					<td align="left"> 
						<html:text  property="qq" readonly="true" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<font color="red">*</font>出勤率：
					</td>
					<td align="left">
						<html:text  property="cql" readonly="true" />
					</td>
					<td align="right">
						抽查日期：
					</td>
					<td align="left"> 
						<html:text style="cursor:hand; width:85px;" property="rq" readonly="true" />
					</td>
				</tr>
				
				<tr style="height:22px">
					<td align="right">
						抽查时间（开始时间）：
					</td>
					<td align="left">
						<html:text  property="kssj"  styleId="kssj" readonly="true"></html:text>
					</td>
					<td align="right">
						抽查时间（结束时间）：
					</td>
					<td align="left">
						<html:text  property="jssj"  styleId="jssj" readonly="true"></html:text>
					</td>
				</tr>
				
				<tr style="height:22px">
					<td align="right">
						任课教师：
					</td>
					<td align="left">
						<html:text  property="rkjs"  styleId="rkjs" readonly="true"></html:text>
					</td>
					<td align="right">
						上课地点：
					</td>
					<td align="left">
						<html:text  property="skdd"  styleId="skdd" readonly="true"></html:text>
					</td>
				</tr>
				
				<tr style="height:22px">
					<td align="right">
						备注：
					</td>
					<td align="left" colspan="3">
						<html:textarea  property="bz" cols="50"></html:textarea>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
		<logic:equal value="view" name="result">
			<script>
			</script>
		</logic:equal>
	</body>
</html>
