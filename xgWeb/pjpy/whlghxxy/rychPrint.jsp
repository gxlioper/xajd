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
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>

		<html:form action="/data_search" method="post">
		<div align="center" style="font-size:18px;font:'黑体' "><b>武汉理工华夏<bean:message key="lable.xsgzyxpzxy" /> ${rs.xn } 年度 ${rs.rychmc } 荣誉称号申请表</b></div>
			<table width="99%" id="rsT" class="printstyle">
				<tr style="height:22px">
					<th width="15%">
						<div align="center">
							学号
						</div>
					</th>
					<th width="15%">
						<div align="center">
							<bean:write name='rs' property="xh" />
						</div>
					</th>
					<th width="10%">
						<div align="center">
							姓名
						</div>
					</th>
					<th width="15%">
						<div align="center">
							<bean:write name='rs' property="xm" />
						</div>
					</th>
					<th width="10%">
						<div align="center">
							性别
						</div>
					</th>
					<th width="15%">
						<div align="center">
							<bean:write name='rs' property="xb" />
						</div>
					</th>
					<th width="20%" rowspan="5">
						<div align="center">
							贴照片处
						</div>
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							民族
						</div>
					</th>
					<th>
						<div align="center">
							<bean:write name='rs' property="mzmc" />
						</div>
					</th>
					<th>
						<div align="center">
							政治面貌
						</div>
					</th>
					<th>
						<div align="center">
							<bean:write name='rs' property="zzmmmc" />
						</div>
					</th>
					<th>
						<div align="center">
							外语水平
						</div>
					</th>
					<th>
						<div align="center">
							<bean:write name='rs' property="wydj" />
						</div>
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							出生年月
						</div>
					</th>
					<th>
						<div align="center">
							<bean:write name='rs' property="csrq" />
						</div>
					</th>
					<th>
						<div align="center">
							入学时间
						</div>
					</th>
					<th>
						<div align="center">
							<bean:write name='rs' property="rxrq" />
						</div>
					</th>
					<th>
						<div align="center">
							联系电话
						</div>
					</th>
					<th>
						<div align="center">
							<bean:write name='rs' property="lxdh" />
						</div>
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							系、专业、班级
						</div>
					</th>
					<th colspan="5">
						<bean:write name='rs' property="xymc" />
						<bean:write name='rs' property="zymc" />
						<bean:write name='rs' property="bjmc" />
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							申请荣誉称号类型
						</div>
					</th>
					<th colspan="5">
						<bean:write name='rs' property="rychmc" />
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							主
							<br />
							要
							<br />
							事
							<br />
							迹
						</div>
					</th>
					<th colspan="6">
						<bean:write name='rs' property="zysj" />
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							担任
							<br />
							社会
							<br />
							工作
							<br />
							情况
						</div>
					</th>
					<th colspan="6">
						<bean:write name='rs' property="drshgzqk" />
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							辅
							<br />
							导
							<br />
							员
							<br />
							意
							<br />
							见
						</div>
					</th>
					<th colspan="6">
										<p>&nbsp;</p>
															<p>&nbsp;</p>
						<bean:write name='rs' property="fdyyj" />
						<p align="right">签章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
						<p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;</p>
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							系
							<br />
							推
							<br />
							荐
							<br />
							意
							<br />
							见
						</div>
					</th>
					<th colspan="6">
										<p>&nbsp;</p>
															<p>&nbsp;</p>
						<bean:write name='rs' property="xyyj" />
						<p align="right">签章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
						<p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;</p>
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							学
							<br />
							院
							<br />
							意
							<br />
							见
						</div>
						
					</th>
					<th colspan="6">
					<p>&nbsp;</p>
										<p>&nbsp;</p>
						<bean:write name='rs' property="xxyj" />
						<p align="right">签章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
						<p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;</p>
					</th>
				</tr>
			</table>
			<div align="center" class='noPrin'>
				<button class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
					页面设置
				</button>
				<button class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
					打印预览
				</button>
				<button class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
					直接打印
				</button>
			</div>
		</html:form>
	</body>
</html>
