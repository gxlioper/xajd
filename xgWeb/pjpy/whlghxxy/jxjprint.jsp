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
			 <div align="center" style="font-size:18px;font:'黑体' "><b>武汉理工华夏<bean:message key="lable.xsgzyxpzxy" /> ${rs.xn } 年度 ${rs.jxjmc } 奖（助）学金申请表</b></div>
			<table width="99%" id="rsT" class="printstyle">
			
				<tr>
					<th width="20%">
						<div align="center">
							学号
						</div>
					</th>
					<th width="10%">
						<div align="center">
							${rs.xh }
						</div>
					</th>
					<th width="15%">
						<div align="center">
							姓名
						</div>
					</th>
					<th width="15%">
						<div align="center">
							${rs.xm }
						</div>
					</th>
					<th width="15%">
						<div align="center">
							性别
						</div>
					</th>
					<th width="10%">
						<div align="center">
							${rs.xb }
						</div>
					</th>
					<th width="15%" rowspan="4">
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
							${rs.mzmc }
						</div>
					</th>
					<th>
						<div align="center">
							政治面貌
						</div>
					</th>
					<th>
						<div align="center">
						${rs.zzmmmc }
						</div>
					</th>
					<th>
						<div align="center">
							外语水平
						</div>
					</th>
					<th>
						<div align="center">
							${rs.wysp }
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
							${rs.csrq }
						</div>
					</th>
					<th>
						<div align="center">
							入学时间
						</div>
					</th>
					<th>
						<div align="center">
							${rs.rxrq }
						</div>
					</th>
					<th>
						<div align="center">
							联系电话
						</div>
					</th>
					<th>
						<div align="center">
							${rs.lxdh }
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
						<div align="center">
							${rs.xymc }${rs.zymc }${rs.bjmc }
						</div>
					</th>
				</tr>
			</table>
			<table width="99%" id="rsT" class="printstyle">
				<tr>
					<th width="20%">
						<div align="center">
							社会工作情况
							<br />
							(现任职务)
						</div>
					</th>
					<th colspan="4">
						${rs.drzw }
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							申请奖(助)学金类别
						</div>
					</th>
					<th colspan="4">
						${rs.jxjmc }
					</th>
				</tr>
				<tr>
					<th rowspan="4">
						<div align="center">
							个人学习情况
						</div>
					</th>
					<th width="20%">
						<div align="center">
							平均学分绩点
						</div>
					</th>
					<th width="20%">
						<div align="center">
							${rs.xf }
						</div>
					</th>
					<th width="20%">
						<div align="center"> 
							&#25490;&#21517; 
						</div>
					</th>
					<th width="20%">
						<div align="center">
							${rs.xfpm }
						</div>
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							综合素质测评
						</div>
					</th>
					<th>
						<div align="center">
							${rs.zf }
						</div>
					</th>
					<th>
						<div align="center"> 
							&#25490;&#21517; 
						</div>
					</th>
					<th>
						<div align="center">
							${rs.zfpm }
						</div>
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							单科最低成绩
						</div>
					</th>
					<th>
						<div align="center">
							${rs.zdcj }
						</div>
					</th>
					<th>
						<div align="center">
							德育
						</div>
					</th>
					<th>
						<div align="center">
							${rs.dcj }
						</div>
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							奖励加分
						</div>
					</th>
					<th>
						<div align="center">
							${rs.jlf }
						</div>
					</th>
					<th>
						<div align="center">
							体质健康评定
						</div>
					</th>
					<th>
						<div align="center">
							${rs.tzjkbzdj }
						</div>
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							申请理由
						</div>
					</th>
					<th colspan="4" >
						<p>&nbsp;</p>
						<p>&nbsp;</p>
						${rs.sqly }
						<p>&nbsp;</p>
						<p>&nbsp;</p>
					</th>
				</tr>
				<tr>
					<th height="18">
						<div align="center">
							辅导员意见
						</div>
					</th>
					<th colspan="4">
					<p>&nbsp;</p>
					<p>&nbsp;</p>
						${rs.fdyyj }
						<p align="right">签章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
						<p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;</p>
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							系推荐意见
						</div>
					</th>
					<th colspan="4">
					<p>&nbsp;</p>
					<p>&nbsp;</p>
						${rs.xyshyj }
						<p align="right">签章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
						<p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;</p>
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />奖（助）学金
							<br />
							评审委员会意见
						</div>
					</th>
					<th colspan="4">
					<p>&nbsp;</p>
					<p>&nbsp;</p>
						${rs.xxshyj }
						
						<p align="right">签章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
						<p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;</p>
					</th>
				</tr>

			</table>
		</html:form>
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
	</body>
</html>
