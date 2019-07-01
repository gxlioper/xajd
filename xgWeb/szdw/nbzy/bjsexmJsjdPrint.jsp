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
		<div align="right">
			<table width="15%" border="1" bordercolor="black" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30" width="50%" align="center">
						<font style="size: 20px">编号</font>
					</td>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
			</table>
		</div>
		<br><br><br><br><br><br><br>
		<div align="center" style="font-size:28px;font:'黑体' "><b>宁波职业技术<bean:message key="lable.xsgzyxpzxy" />班级特色项目建设</b></div>
		<br><br><br><br>
		<div align="center" style="font-size:25px;"><b>进程表</b></div>
		<br><br><br><br><br><br><br><br><br><br><br>
		<div align="center">
			<table width="50%">
				<tr>
					<td height="50" width="50%" align="center">
						<span style="font-size:14.0pt;">项 目 名 称：</span>
					</td>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
					</td>	
				</tr>
				<tr>
					<td height="50" width="50%" align="center">
						<span style="font-size:14.0pt;">验收申请班级：</span>
					</td>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td height="50" width="50%" align="center">
						<span style="font-size:14.0pt;">所 属 分 院：</span>
					</td>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
			</table>
		</div>
		<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
		<div align="center" style="font-size:20px;"><b>宁波职业技术<bean:message key="lable.xsgzyxpzxy" />学工部制</b></div>
		<div align="center" style="font-size:20px;"><b>二ΟΟ   年    月    日</b></div>
		<br><br><br><br><br><br><br><br><br><br><br>
		<div align="left" style="font-size:10px;">
		<table width="100%" id="rsT" class="printstyle">
			<tr>
				<th width="10%">
					<div align="center">
						1<br>申报<br>项目<br>的动<br>因
					</div>
				</th>
				<th colspan="2">
					<div align="center">
						<bean:write name='rs' property="sbdy" />
					</div>
				</th>
			</tr>
			<tr>
				<th rowspan="10">
					<div align="center" >
						2<br>班级<br>特色<br>项目<br>建设<br>的时间<br>阶段<br>和内容
					</div>
				</th>
				<th width="20%">
					<div align="center">
						时间阶段
					</div>
				</th>
				<th>
					<div align="center">
						基本内容
					</div>
				</th>
			</tr>
			<tr>
				<th height="60px">
					<div align="center">
						<bean:write name='rs' property="kssj0" />
						<br>
						-
						<br>
						<bean:write name='rs' property="jssj0" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="nr0" />
					</div>
				</th>
			</tr>
			<tr>
				<th height="60px">
					<div align="center">
						<bean:write name='rs' property="kssj1" />
						<br>
						-
						<br>
						<bean:write name='rs' property="jssj1" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="nr1" />
					</div>
				</th>
			</tr>
			<tr>
				<th height="60px">
					<div align="center">
						<bean:write name='rs' property="kssj2" />
						<br>
						-
						<br>
						<bean:write name='rs' property="jssj2" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="nr2" />
					</div>
				</th>
			</tr>
			<tr>
				<th height="60px">
					<div align="center">
						<bean:write name='rs' property="kssj3" />
						<br>
						-
						<br>
						<bean:write name='rs' property="jssj3" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="nr3" />
					</div>
				</th>
			</tr>
			<tr>
				<th  height="60px">
					<div align="center">
						<bean:write name='rs' property="kssj4" />
						<br>
						-
						<br>
						<bean:write name='rs' property="jssj4" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="nr4" />
					</div>
				</th>
			</tr>
			<tr>
				<th  height="60px">
					<div align="center">
						<bean:write name='rs' property="kssj5" />
						<br>
						-
						<br>
						<bean:write name='rs' property="jssj5" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="nr5" />
					</div>
				</th>
			</tr>
			<tr>
				<th  height="60px">
					<div align="center">
						<bean:write name='rs' property="kssj6" />
						<br>
						-
						<br>
						<bean:write name='rs' property="jssj6" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="nr6" />
					</div>
				</th>
			</tr>
			<tr>
				<th height="60px">
					<div align="center">
						<bean:write name='rs' property="kssj7" />
						<br>
						-
						<br>
						<bean:write name='rs' property="jssj7" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="nr7" />
					</div>
				</th>
			</tr>
			<tr>
				<th height="60px">
					<div align="center">
						<bean:write name='rs' property="kssj8" />
						<br>
						-
						<br>
						<bean:write name='rs' property="jssj8" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="nr8" />
					</div>
				</th>
			</tr>
		</table>	
			<div align="center" class='noPrin'>
				<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
					页面设置
				</button>
				<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
					打印预览
				</button>
				<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
					直接打印
				</button>
			</div>
		</html:form>
	</body>
</html>
