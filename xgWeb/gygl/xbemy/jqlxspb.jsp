<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
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
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body>
		<center id="theTable">
			<p>
			<h3 align="center">
				<bean:write name="rs"  property="xxmc"/>假期留校学生住宿审批表
			</h3>
			<div>
				<b>审批时间： &nbsp;&nbsp;&nbsp; 年 &nbsp;&nbsp;&nbsp; 月
					&nbsp;&nbsp;&nbsp; 日
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					编号：
				</b>
			</div>
			<table width="100%" border="0" id="theTable"  class="printstyle">
				<tr>
					<td align="center" width="15%">
						姓名
					</td>
					<td width="10%">
						<bean:write name="rs" property='xm'/>
					</td>
					<td width="10%">
						性别
					</td>
					<td width="10%">
						<bean:write name="rs" property='xb'/>
					</td>
					<td width="10%">
						年龄
					</td>
					<td width="10%">
						<bean:write name="rs" property="csnl" />
					</td>
					<td width="10%">
						生源地
					</td>
					<td width="25%">
						<bean:write name="rs" property="lydq" />
					</td>
				</tr>
				<tr>
					<td align="center">
						院 ( 系 )
					</td>
					<td colspan="3">
						<bean:write name="rs" property='xymc'/>
					</td>
					<td>
						专业
					</td>
					<td>
						<bean:write name="rs" property='zymc'/>
					</td>
					<td>
						学号
					</td>
					<td>
						<bean:write name="rs" property='xh'/>
					</td>
				</tr>
				<tr>
					<td align="center">
						学制
					</td>
					<td colspan="3">
						<bean:write name="rs" property='xz'/>
					</td>
					<td>
						联系电话
					</td>
					<td>
						<bean:write name="rs" property='lxdh'/>
					</td>
					<td>
						宿舍编号
					</td>
					<td>
						<bean:write name="rs" property='ssbh'/> 
					</td>
				</tr>
				<tr>
					<td align="center" height="200px">
						<div style="margin:10px ">
							详细
						</div>
						<div style="margin:10px ">
							申请
						</div>
						<div style="margin:10px ">
							原因
						</div>
					</td>
					<td colspan="7">
						<bean:write name="rs" property='lxyy'/>
					</td>
				</tr>
				<tr>
					<td align="center">
						<div style="margin:10px ">
							同意
						</div>
						<div style="margin:10px ">
							条款
						</div>
					</td>
					<td colspan="7">
						<p>
							1 、本人自觉遵守《北方民族大学住宿管理规定》和学校规章制度，不私自调换床位；<br>
							2 、本人认真执行宿舍卫生管理制度，自觉维护宿舍整洁；<br>
							3 、本人自觉遵守学校作息时间，团结同学、爱护公物，损坏公物自觉赔偿；<br>
							4 、本人按时交纳住宿费和其他有关费用，遵守用电安全规定；<br>
							5 、本人愿意服从学生处宿管科的安排和调配。
						</p>
						<p align="right">
							签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
							年&nbsp;&nbsp;&nbsp; 月 &nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr>
					<td align="center">
						<div style="margin:10px ">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
						<div style="margin:10px ">
							审批
						</div>
					<td colspan="7">
						<p>
							&nbsp;
						</p>
						<p>
							辅导员审核意见：
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p align="right">
							总支书记签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
							公章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
							年&nbsp;&nbsp;&nbsp; 月 &nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr>
					<td align="center">
						<div style="margin:10px ">
							学生
						</div>
						<div style="margin:10px ">
							退宿
						</div>
						<div style="margin:10px ">
							管科
						</div>
						<div style="margin:10px ">
							审批
						</div>
					</td>
					<td colspan="7">
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p align="right">
							签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
							年&nbsp;&nbsp;&nbsp; 月 &nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr>
					<td align="center">
						<div style="margin:10px ">
							办事
						</div>
						<div style="margin:10px ">
							流程
						</div>
					</td>
					<td colspan="7">
						<p>
							1 、按本表格要求填写相关内容；<br>
							2 、到主管部门（如学生处、本<bean:message key="lable.xsgzyxpzxy" />等）签署意见，并出具有关证明附在表后；<br>
							3 、将表格连同证明提交到北方民族大学学生处宿管科审批；<br>
							4 、审批通过后，按要求到本楼栋值班室办理入住手续。
						</p>
					</td>
				</tr>
				<tr>
					<td align="center">
						<div style="margin:10px ">
							处理
						</div>
						<div style="margin:10px ">
							结果
						</div>
					</td>
					<td colspan="7">
						调房安排到&nbsp;&nbsp;&nbsp; <bean:write name="rs" property='ldmc'/> &nbsp;&nbsp;&nbsp;<bean:write name="rs" property='qsh'/> 房
					</td>
				</tr>
			</table>
		</center>
	</body>
</html>
