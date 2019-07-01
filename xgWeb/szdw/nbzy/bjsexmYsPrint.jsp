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
		<div align="center" style="font-size:25px;"><b>验收申请表</b></div>
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
		<table width="100%" id="rsT" class="printstyle">
			<tr>
				<th width="10%">
					<div align="center">
						1-1<br>项目<br>建设<br>的<br>详细<br>过程<br>（不少于<br>1000字）	
					</div>
				</th>
				<th height="380">
					<div align="center">
						<bean:write name='rs' property="jsgc" />
					</div>
				</th>
			</tr>
			<tr>
				<th width="10%">
					<div align="center">
						1-2<br>班级<br>学生<br>对<br>项目<br>建设<br>的评价<br>（不少于10人）
					</div>
				</th>
				<th height="580" valign="top">
					<div align="left">
						<bean:write name='rs' property="ry0" /><br>
						<bean:write name='rs' property="ry1" /><br>
						<bean:write name='rs' property="ry2" /><br>
						<bean:write name='rs' property="ry3" /><br>
						<bean:write name='rs' property="ry4" /><br>
						<bean:write name='rs' property="ry5" /><br>
						<bean:write name='rs' property="ry6" /><br>
						<bean:write name='rs' property="ry7" /><br>
						<bean:write name='rs' property="ry8" /><br>
						<bean:write name='rs' property="ry9" /><br>
						<bean:write name='rs' property="ry10" /><br>
						<bean:write name='rs' property="ry11" /><br>
						<bean:write name='rs' property="ry12" /><br>
						<bean:write name='rs' property="ry13" /><br>
						<bean:write name='rs' property="ry14" /><br>
						<bean:write name='rs' property="ry15" /><br>
						<bean:write name='rs' property="ry16" /><br>
						<bean:write name='rs' property="ry17" /><br>
						<bean:write name='rs' property="ry18" /><br>
						<bean:write name='rs' property="ry19" /><br>
					</div>
				</th>
			</tr>
		</table>
		<br>
		<table width="100%" id="rsT" class="printstyle">
			<tr>
				<th width="10%" rowspan="4">
					<div align="center">
						1-3<br>项目<br>实施<br>前后<br>班级<br>状况<br>比较
					</div>
				</th>
				<th>
					<div align="left">
						实施前：
					</div>
				</th>
			</tr>
			<tr>
				<th height="215" valign="top">
					<div align="left">
						<bean:write name='rs' property="ssqzk" />
					</div>
				</th>
			</tr>
			<tr>
				<th>
					<div align="left">
						实施后：
					</div>
				</th>
			</tr>
			<tr>
				<th height="215" valign="top">
					<div align="left">
						<bean:write name='rs' property="sshzk" />
					</div>
				</th>
			</tr>
			<tr>
				<th width="10%" colspan="2">
					<div align="left">
						项目建设的成果：
					</div>
				</th>
			</tr>
			<tr>
				<th width="10%" colspan="2" align="left" height="215" valign="top">
					<bean:write name='rs' property="jscg" />
				</th>
			</tr>
			<tr>
				<th width="10%" colspan="2">
					<div align="left">
						项目建设的得失：
					</div>
				</th>
			</tr>
			<tr>
				<th width="10%" colspan="2" align="left" height="215" valign="top">
					<bean:write name='rs' property="jsds" />
				</th>
			</tr>
		</table>
		<br>
		<table width="100%" id="rsT" class="printstyle">
			<tr>
				<th width="25%">
					<div align="center">
						支出项目
					</div>
				</th>
				<th width="25%">
					<div align="center">
						金额
					</div>
				</th>
				<th width="25%">
					<div align="center">
						支出项目
					</div>
				</th>
				<th width="25%">
					<div align="center">
						金额
					</div>
				</th>
			</tr>
			<tr>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcxm0" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcje0" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcxm13" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcje13" />
					</div>
				</th>
			</tr>
			<tr>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcxm1" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcje1" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcxm14" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcje14" />
					</div>
				</th>
			</tr>
			<tr>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcxm2" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcje2" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcxm15" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcje15" />
					</div>
				</th>
			</tr>
			<tr>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcxm3" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcje3" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcxm16" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcje16" />
					</div>
				</th>
			</tr>
			<tr>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcxm4" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcje4" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcxm17" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcje17" />
					</div>
				</th>
			</tr>
			<tr>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcxm5" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcje5" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcxm18" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcje18" />
					</div>
				</th>
			</tr>
			<tr>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcxm6" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcje6" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcxm19" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcje19" />
					</div>
				</th>
			</tr>
			<tr>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcxm7" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcje7" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcxm20" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcje20" />
					</div>
				</th>
			</tr>
			<tr>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcxm8" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcje8" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcxm21" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcje21" />
					</div>
				</th>
			</tr>
			<tr>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcxm9" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcje9" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcxm22" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcje22" />
					</div>
				</th>
			</tr>
			<tr>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcxm10" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcje10" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcxm23" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcje23" />
					</div>
				</th>
			</tr>
			<tr>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcxm11" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcje11" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcxm24" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcje24" />
					</div>
				</th>
			</tr>
			<tr>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcxm12" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="zcje12" />
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						合计
					</div>
				</th>
				<th height="30" width="25%">
					<div align="center">
						<bean:write name='rs' property="hj" />
					</div>
				</th>
			</tr>
		</table>
		<table width="100%" id="rsT" class="printstyle">
			<tr>
				<th height="60" width="10%">
					<div align="center">
						班主任<br>意见
					</div>
				</th>
				<th>
					<div align="left">
						<bean:write name='rs' property="bzryj" />
					</div>
					<br>
					<div align="right">
						负责人：
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name='rs' property="bzrxm" />
					</div>
					<div align="right">
						<bean:write name='rs' property="bzrshsj" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 60px;">
					<div align="center">
						二级分<br>院（系）<br>意见
					</div>
				</th>
				<th>
					<div align="left">
						<bean:write name='rs' property="xyyj" />
					</div>
					<br>
					<div align="right">
						负责人：	
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;
						（公章）
					</div>
					<div align="right">
						<bean:write name='rs' property="xyshsj" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 60px;">
					<div align="center">
						学工部<br>意见
					</div>
				</th>
				<th>
					<div align="left">
						<bean:write name='rs' property="xxyj" />
					</div>
					<br>
					<div align="right">
						负责人：	
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;
						（公章）
					</div>
					<div align="right">
						<bean:write name='rs' property="xxshsj" />
					</div>
				</th>
			</tr>
		</table>
		<div align="left" style="font-size:10px;">
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
