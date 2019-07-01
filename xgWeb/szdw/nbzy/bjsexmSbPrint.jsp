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
		<div align="center" style="font-size:25px;"><b>申报表</b></div>
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
				<th width="10%" rowspan="6">
					<div align="center">
						1-1<br><br>基本<br><br>信息
					</div>
				</th>
				<th width="15%">
					<div align="center">
						班级名称
					</div>
				</th>
				<th colspan="2" width="35%">
					<div align="center">
						<bean:write name='rs' property="bjmc" />
					</div>
				</th>
				<th width="20%">
					<div align="center">
						班主任
					</div>	
				</th>
				<th width="20%">
					<div align="center">
						<bean:write name='rs' property="bzrxm" />
					</div>
				</th>
			</tr>
			<tr>
				<th>
					<div align="center">
						班长
					</div>
				</th>
				<th colspan="2">
					<div align="center">
						<bean:write name='rs' property="bzxm" />
					</div>
				</th>
				<th>
					<div align="center">
						团支书姓名
					</div>	
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="tzsxm" />
					</div>
				</th>
			</tr>
			<tr>
				<th>
					<div align="center">
						所属分院
					</div>
				</th>
				<th colspan="2">
					<div align="center">
						<bean:write name='rs' property="xymc" />
					</div>
				</th>
				<th>
					<div align="center">
						人数
					</div>	
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="bjrs" />
					</div>
				</th>
			</tr>
			<tr>
				<th rowspan="3">
					<div align="center">
						联系人信息
					</div>
				</th>
				<th width="17%">
					<div align="center">
						姓名
					</div>
				</th>
				<th width="18%">
					<div align="center">
						<bean:write name='rs' property="lxrxm" />
					</div>
				</th>
				<th>
					<div align="center">
						职务
					</div>	
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="lxrzw" />
					</div>
				</th>
			</tr>
			<tr>
				<th>
					<div align="center">
						手机短号
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="lxrsjdh" />
					</div>
				</th>
				<th>
					<div align="center">
						寝室及电话
					</div>	
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="lxrqs" /> <bean:write name='rs' property="lxrqsdh" />
					</div>
				</th>
			</tr>
			<tr>
				<th>
					<div align="center">
						手机
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="lxrsj" />
					</div>
				</th>
				<th>
					<div align="center">
						E-mail
					</div>	
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="lxremail" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 780px;">
					<div align="center">
						1-2<br>班级<br>进校<br>后主<br>要成<br>绩和<br>项目<br>建设<br>已具<br>备条件
					</div>
				</th>
				<th colspan="5" valign="top">
					<div align="left">
						<bean:write name='rs' property="bjcjtj" />
					</div>
				</th>
			</tr>
			</table>
			<br><br>
			<table width="100%" id="rsT" class="printstyle">
			<tr>
				<th width="10%" rowspan="4">
					<div align="center">
						2-1<br>项目<br>建设<br>基本<br>方案、<br>特色<br>及进度

					</div>
				</th>
				<th>
					<div align="left">
						项目名称：<bean:write name='rs' property="xmmc" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 140px;" valign="top">
					<div align="left">
						项目特色：<br><bean:write name='rs' property="xmts" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 200px;" valign="top">
					<div align="left">	
						项目建设基本方案：<br><bean:write name='rs' property="xmjsfa" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 200px;" valign="top">
					<div align="left">
						进度：<br><bean:write name='rs' property="xmjd" />
					</div>
				</th>
			</tr>
			<tr>
				<th>
					<div align="center">
						2-2<br>全班<br><br>同学<br><br>对方<br><br>案的<br><br>认同
					</div>
				</th>
				<th style="height: 390px;" valign="top">
					<div align="left">
						<bean:write name='rs' property="qbrt" />
					</div>
				</th>
			</tr>
		</table>
		<br>
		<table width="100%" id="rsT" class="printstyle">
			<tr>
				<th style="height: 150px;" valign="top" colspan="5">
					<div align="left">
						项目预期效果:<br>
						<bean:write name='rs' property="yqxg" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 150px;" valign="top" colspan="5">
					<div align="left">
						项目验收要点:<br>
						<bean:write name='rs' property="ysyd" />
					</div>
				</th>
			</tr>
			<tr>
				<th  width="10%" rowspan="7">
					<div align="center">
						4-1<br>申请<br>学工<br>部资<br>助经<br>费<br>预算
					</div>
				</th>
				<th width="22%">
					<div align="center">
						支出项目
					</div>
				</th>
				<th width="22%">
					<div align="center">
						上半年度
					</div>
				</th>
				<th width="22%">
					<div align="center">
						下半年度
					</div>
				</th>
				<th width="24%">
					<div align="center">
						合计
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 30px;">
					<div align="center">
						<bean:write name='rs' property="zz_xm0" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_sbzz0" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_xbzz0" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_hj0" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 30px;">
					<div align="center">
						<bean:write name='rs' property="zz_xm1" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_sbzz1" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_xbzz1" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_hj1" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 30px;">
					<div align="center">
						<bean:write name='rs' property="zz_xm2" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_sbzz2" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_xbzz2" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_hj2" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 30px;">
					<div align="center">
						<bean:write name='rs' property="zz_xm3" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_sbzz3" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_xbzz3" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_hj3" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 30px;">
					<div align="center">
						<bean:write name='rs' property="zz_xm4" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_sbzz4" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_xbzz4" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_hj4" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 30px;">
					<div align="center">
						合计
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_sbhj" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_xbhj" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zz_zhj" />
					</div>
				</th>
			</tr>
			<tr>
				<th  width="10%" rowspan="7">
					<div align="center">
						4-2<br>班级<br>自有<br>经费<br>预算
					</div>
				</th>
				<th width="22%">
					<div align="center">
						支出项目
					</div>
				</th>
				<th width="22%">
					<div align="center">
						上半年度
					</div>
				</th>
				<th width="22%">
					<div align="center">
						下半年度
					</div>
				</th>
				<th width="24%">
					<div align="center">
						合计
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 30px;">
					<div align="center">
						<bean:write name='rs' property="zy_xm0" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_sbzz0" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_xbzz0" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_hj0" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 30px;">
					<div align="center">
						<bean:write name='rs' property="zy_xm1" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_sbzz1" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_xbzz1" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_hj1" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 30px;">
					<div align="center">
						<bean:write name='rs' property="zy_xm2" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_sbzz2" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_xbzz2" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_hj2" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 30px;">
					<div align="center">
						<bean:write name='rs' property="zy_xm3" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_sbzz3" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_xbzz3" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_hj3" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 30px;">
					<div align="center">
						<bean:write name='rs' property="zy_xm4" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_sbzz4" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_xbzz4" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_hj4" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 30px;">
					<div align="center">
						合计
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_sbhj" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_xbhj" />
					</div>
				</th>
				<th>
					<div align="center">
						<bean:write name='rs' property="zy_zhj" />
					</div>
				</th>
			</tr>
			<tr>
				<th style="height: 60px;">
					<div align="center">
						班主任<br>意见
					</div>
				</th>
				<th colspan="4">
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
				<th colspan="4">
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
				<th colspan="4">
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
