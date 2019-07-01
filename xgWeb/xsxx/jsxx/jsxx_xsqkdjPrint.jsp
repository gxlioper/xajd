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

		<html:form action="/stu_info_add" method="post">
		<div align="center" style="font-size:28px;font:'黑体' "><b>江苏信息职业技术<bean:message key="lable.xsgzyxpzxy" /></b></div>
		<div align="center" style="font-size:18px;"><b>学生情况登记表</b></div>
		<br>
		<div align="left" style="font-size:10px;">
		<b>
		<logic:equal name="xs" value="yes">
		<bean:write name='rs' property="xymc" />&nbsp;系&nbsp;<bean:write name='rs' property="bjmc" />班
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                    
		学号  <bean:write name='rs' property="xh" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		填写日期：<bean:write name='rs' property="year" />年<bean:write name='rs' property="month" />月<bean:write name='rs' property="day" />日
		</logic:equal>
		<logic:equal name="xs" value="no">
		_________________系&nbsp;_________________班
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;               
		学号____________
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		填写日期：&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
		</logic:equal>
		</b>
		</div>
		<br>
		<table width="100%" id="rsT" class="printstyle">
			<tr>
			<td>
			<table width="99%" class="printstyle">
				<tr style="height:22px">
					<th width="10%">
						<div align="center">
							姓名
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="xm" />
						</div>
					</th>
					<th width="10%">
						<div align="center">
							曾用名
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="cym" />
						</div>
					</th>
					<th width="5%">
						<div align="center">
							性别
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="xb" />
						</div>
					</th>
					<th width="10%">
						<div align="center">
							出生年月
						</div>
					</th>
					<th width="20%" colspan="2">
						<div align="center">
							<bean:write name='rs' property="csrq" />
						</div>
					</th>
					<th  rowspan="5">
						<div align="center">
							贴照片处
						</div>
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							身份<br>证号
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							<bean:write name='rs' property="sfzh" />
						</div>
					</th>
					<th>
						<div align="center">
							准考证号
						</div>
					</th>
					<th colspan="3">
						<div align="center">
							<bean:write name='rs' property="zkzh" />
						</div>
					</th>
					<th width="10%">
						<div align="center">
							入学<br>成绩
						</div>
					</th>
					<th width="10%" >
						<div align="center">
							<bean:write name='rs' property="rxcj" />
						</div>
					</th>
				</tr>
				<tr>
					<th colspan="3">
						<div align="center">
							何时何地何人介绍入团（党）
						</div>
					</th>
					<th colspan="4">
						<div align="center">
							<bean:write name='rs' property="rdsj" />
							<bean:write name='rs' property="rddd" />
							<bean:write name='rs' property="rdjsr" />
						</div>
					</th>
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
				</tr>
				<tr>
					<th colspan="3">
						<div align="center">
							家庭详细地址
						</div>
					</th>
					<th colspan="6">
						<div align="center">
							<bean:write name='rs' property="jtdz" />	
						</div>
					</th>
				</tr>
				<tr>
					<th>
						<div align="center">
							邮编
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							<bean:write name='rs' property="yzbm" />	
						</div>
					</th>
					<th>
						<div align="center">
							宅电
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							<bean:write name='rs' property="jtdh" />	
						</div>
					</th>
					<th>
						<div align="center">
							个人电话
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							<bean:write name='rs' property="lxdh" />	
						</div>
					</th>
				</tr>
				<tr>
					<th colspan="3">
						<div align="center">
							个人爱好特长
						</div>
					</th>
					<th colspan="7">
						<div align="center">
							<bean:write name='rs' property="grahtc" />	
						</div>
					</th>
				</tr>
			</table>
			</td>
			</tr>
			<tr>
			<td>
			<table  width="99%" class="printstyle">
				<tr style="height:22px">
					<th width="5%" rowspan="4">
						<div align="center">
							个<br>人<br>主<br>要<br>经<br>历
						</div>
					</th>
					<th width="15%" colspan="2">
						<div align="center">
							何时起
						</div>
					</th>
					<th width="15%" colspan="2">
						<div align="center">
							何时止
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							在何地，何学校或何单位学习，工作	
						</div>
					</th>
					<th width="20%">
						<div align="center">
							任何职
						</div>
					</th>
				</tr>
				<tr style="height:22px">
					<th colspan="2">
						<div align="center">
							<bean:write name='rs' property="grjlkssj1" />	
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							<bean:write name='rs' property="grjljssj1" />	
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							<bean:write name='rs' property="grjlnr1" />	
						</div>
					</th>
					<th>
						<div align="center">
							<bean:write name='rs' property="grjlzw1" />	
						</div>
					</th>
				</tr>
				<tr style="height:22px">
					<th colspan="2">
						<div align="center">
							<bean:write name='rs' property="grjlkssj2" />	
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							<bean:write name='rs' property="grjljssj2" />	
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							<bean:write name='rs' property="grjlnr2" />	
						</div>
					</th>
					<th>
						<div align="center">
							<bean:write name='rs' property="grjlzw2" />	
						</div>
					</th>
				</tr>
				<tr style="height:22px">
					<th colspan="2">
						<div align="center">
							<bean:write name='rs' property="grjlkssj3" />	
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							<bean:write name='rs' property="grjljssj3" />	
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							<bean:write name='rs' property="grjlnr3" />	
						</div>
					</th>
					<th>
						<div align="center">
							<bean:write name='rs' property="grjlzw3" />	
						</div>
					</th>
				</tr>
				<tr style="height:22px">
					<th width="5%" rowspan="5">
						<div align="center">
							家<br>庭<br>成<br>员<br>情<br>况
						</div>
					</th>
					<th width="5%">
						<div align="center">
							称谓
						</div>
					</th>
					<th width="10%">
						<div align="center">
							姓名
						</div>
					</th>
					<th width="7%">
						<div align="center">
							年龄
						</div>
					</th>
					<th width="8%">
						<div align="center">
							政治面貌
						</div>
					</th>
					<th width="35%">
						<div align="center">
							何单位，部门工作
						</div>
					</th>
					<th width="10%">
						<div align="center">
							经济收入
						</div>
					</th>
					<th width="20%">
						<div align="center">
							联系电话
						</div>
					</th>
				</tr>
				<tr style="height:22px">
					<th width="5%">
						<div align="center">
							<bean:write name='rs' property="jtcych1" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="jtcyxm1" />	
						</div>
					</th>
					<th width="7%">
						<div align="center">
							<bean:write name='rs' property="jtcynl1" />	
						</div>
					</th>
					<th width="8%">
						<div align="center">
							<bean:write name='rs' property="jtcyzzmm1" />	
						</div>
					</th>
					<th width="35%">
						<div align="center">
							<bean:write name='rs' property="jtcydw1" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="jtcysr1" />	
						</div>
					</th>
					<th width="20%">
						<div align="center">
							<bean:write name='rs' property="jtcydh1" />	
						</div>
					</th>
				</tr>
				<tr style="height:22px">
					<th width="5%">
						<div align="center">
							<bean:write name='rs' property="jtcych2" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="jtcyxm2" />	
						</div>
					</th>
					<th width="7%">
						<div align="center">
							<bean:write name='rs' property="jtcynl2" />	
						</div>
					</th>
					<th width="8%">
						<div align="center">
							<bean:write name='rs' property="jtcyzzmm2" />	
						</div>
					</th>
					<th width="35%">
						<div align="center">
							<bean:write name='rs' property="jtcydw2" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="jtcysr2" />	
						</div>
					</th>
					<th width="20%">
						<div align="center">
							<bean:write name='rs' property="jtcydh2" />	
						</div>
					</th>
				</tr>
								<tr style="height:22px">
					<th width="5%">
						<div align="center">
							<bean:write name='rs' property="jtcych3" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="jtcyxm3" />	
						</div>
					</th>
					<th width="7%">
						<div align="center">
							<bean:write name='rs' property="jtcynl3" />	
						</div>
					</th>
					<th width="8%">
						<div align="center">
							<bean:write name='rs' property="jtcyzzmm3" />	
						</div>
					</th>
					<th width="35%">
						<div align="center">
							<bean:write name='rs' property="jtcydw3" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="jtcysr3" />	
						</div>
					</th>
					<th width="20%">
						<div align="center">
							<bean:write name='rs' property="jtcydh3" />	
						</div>
					</th>
				</tr>
				<tr style="height:22px">
					<th width="5%">
						<div align="center">
							<bean:write name='rs' property="jtcych4" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="jtcyxm4" />	
						</div>
					</th>
					<th width="7%">
						<div align="center">
							<bean:write name='rs' property="jtcynl4" />	
						</div>
					</th>
					<th width="8%">
						<div align="center">
							<bean:write name='rs' property="jtcyzzmm4" />	
						</div>
					</th>
					<th width="35%">
						<div align="center">
							<bean:write name='rs' property="jtcydw4" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="jtcysr4" />	
						</div>
					</th>
					<th width="20%">
						<div align="center">
							<bean:write name='rs' property="jtcydh4" />	
						</div>
					</th>
				</tr>
				<tr style="height:22px">
					<th width="5%" rowspan="4">
						<div align="center">
							主<br>要<br>社<br>会<br>关<br>系<br>情<br>况
						</div>
					</th>
					<th width="5%">
						<div align="center">
							<bean:write name='rs' property="shgxch1" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="shgxxm1" />	
						</div>
					</th>
					<th width="7%">
						<div align="center">
							<bean:write name='rs' property="shgxnl1" />	
						</div>
					</th>
					<th width="8%">
						<div align="center">
							<bean:write name='rs' property="shgxzzmm1" />	
						</div>
					</th>
					<th width="35%">
						<div align="center">
							<bean:write name='rs' property="shgxdw1" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="shgxsr1" />	
						</div>
					</th>
					<th width="20%">
						<div align="center">
							<bean:write name='rs' property="shgxdh1" />	
						</div>
					</th>
				</tr>
				<tr style="height:22px">
					<th width="5%">
						<div align="center">
							<bean:write name='rs' property="shgxch2" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="shgxxm2" />	
						</div>
					</th>
					<th width="7%">
						<div align="center">
							<bean:write name='rs' property="shgxnl2" />	
						</div>
					</th>
					<th width="8%">
						<div align="center">
							<bean:write name='rs' property="shgxzzmm2" />	
						</div>
					</th>
					<th width="35%">
						<div align="center">
							<bean:write name='rs' property="shgxdw2" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="shgxsr2" />	
						</div>
					</th>
					<th width="20%">
						<div align="center">
							<bean:write name='rs' property="shgxdh2" />	
						</div>
					</th>
				</tr>
				<tr style="height:22px">
					<th width="5%">
						<div align="center">
							<bean:write name='rs' property="shgxch3" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="shgxxm3" />	
						</div>
					</th>
					<th width="7%">
						<div align="center">
							<bean:write name='rs' property="shgxnl3" />	
						</div>
					</th>
					<th width="8%">
						<div align="center">
							<bean:write name='rs' property="shgxzzmm3" />	
						</div>
					</th>
					<th width="35%">
						<div align="center">
							<bean:write name='rs' property="shgxdw3" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="shgxsr3" />	
						</div>
					</th>
					<th width="20%">
						<div align="center">
							<bean:write name='rs' property="shgxdh3" />	
						</div>
					</th>
				</tr>
				<tr style="height:22px">
					<th width="5%">
						<div align="center">
							<bean:write name='rs' property="shgxch4" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="shgxxm4" />	
						</div>
					</th>
					<th width="7%">
						<div align="center">
							<bean:write name='rs' property="shgxnl4" />	
						</div>
					</th>
					<th width="8%">
						<div align="center">
							<bean:write name='rs' property="shgxzzmm4" />	
						</div>
					</th>
					<th width="35%">
						<div align="center">
							<bean:write name='rs' property="shgxdw4" />	
						</div>
					</th>
					<th width="10%">
						<div align="center">
							<bean:write name='rs' property="shgxsr4" />	
						</div>
					</th>
					<th width="20%">
						<div align="center">
							<bean:write name='rs' property="shgxdh4" />	
						</div>
					</th>
				</tr>
				<tr style="height:200px">
					<th>
						<div align="center">
							备<br>注
						</div>
					</th>
					<th colspan="7" height="100">
						<div align="center">
							<bean:write name='rs' property="bz" />	
						</div>
					</th>
				</tr>
			</table>
			</td>
			</tr>
		</table>
			
		<br><br>
		<div align="center" style="font-size:18px;"><b>学生综合素质情况记载</b></div>	<br><br>
		<table width="100%" id="rsT" class="printstyle">
			<tr>
			<td>
			<table class="printstyle" width="100%">
				<tr>
					<td width="2%" align="center">
						年级
					</td>
					<td width="10%" align="center">
						学期
					</td>
					<td width="10%" align="center">
						德育
					</td>
					<td width="10%" align="center">
						智育
					</td>
					<td width="10%" align="center">
						体育
					</td>
					<td width="10%" align="center">
						综合测评
					</td>
					<td width="10%" align="center">
						名次
					</td>
					<td width="" align="center">
						学年奖惩情况及任职情况
					</td>
					<td width="10%" align="center">
						记录人
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						一年级
					</td>
					<td>
						上学期
					</td>
					<td>
						<bean:write name='stuInfo' property="yydcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="yyzcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="yytcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="yykpf" />
					</td>
					<td>
						<bean:write name='stuInfo' property="yybjpm" />
					</td>
					<td rowspan="2" valign="top" height="100">
						<bean:write name='stuInfo' property="yqk" />
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						下学期
					</td>
					<td>
						<bean:write name='stuInfo' property="yrdcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="yrzcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="yrtcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="yrkpf" />
					</td>
					<td>
						<bean:write name='stuInfo' property="yrbjpm" />
					</td>
					<td></td>
				</tr>
				<tr>
					<td rowspan="2">
						二年级
					</td>
					<td>
						上学期
					</td>
					<td>
						<bean:write name='stuInfo' property="rydcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="ryzcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="rytcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="rykpf" />
					</td>
					<td>
						<bean:write name='stuInfo' property="rybjpm" />
					</td>
					<td rowspan="2" valign="top" height="100">
						<bean:write name='stuInfo' property="rqk" />
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						下学期
					</td>
					<td>
						<bean:write name='stuInfo' property="rrdcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="rrzcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="rrtcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="rrkpf" />
					</td>
					<td>
						<bean:write name='stuInfo' property="rrbjpm" />
					</td>
					<td></td>
				</tr>
				<tr>
					<td rowspan="2">
						三年级
					</td>
					<td>
						上学期
					</td>
					<td>
						<bean:write name='stuInfo' property="sydcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="syzcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="sytcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="sykpf" />
					</td>
					<td>
						<bean:write name='stuInfo' property="sybjpm" />
					</td>
					<td rowspan="2" valign="top" height="100">
						<bean:write name='stuInfo' property="sqk" />
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						下学期
					</td>
					<td>
						<bean:write name='stuInfo' property="srdcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="srzcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="srtcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="srkpf" />
					</td>
					<td>
						<bean:write name='stuInfo' property="srbjpm" />
					</td>
					<td></td>
				</tr>
				<tr>
					<td rowspan="2">
						四年级
					</td>
					<td>
						上学期
					</td>
					<td>
						<bean:write name='stuInfo' property="xydcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="xyzcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="xytcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="xykpf" />
					</td>
					<td>
						<bean:write name='stuInfo' property="xybjpm" />
					</td>
					<td rowspan="2" valign="top" height="100">
						<bean:write name='stuInfo' property="xqk" />
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						下学期
					</td>
					<td>
						<bean:write name='stuInfo' property="xrdcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="xrzcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="xrtcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="xrkpf" />
					</td>
					<td>
						<bean:write name='stuInfo' property="xrbjpm" />
					</td>
					<td></td>
				</tr>
				<tr>
					<td rowspan="2">
						五年级
					</td>
					<td>
						上学期
					</td>
					<td>
						<bean:write name='stuInfo' property="fydcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="fyzcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="fytcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="fykpf" />
					</td>
					<td>
						<bean:write name='stuInfo' property="fybjpm" />
					</td>
					<td rowspan="2" valign="top" height="100">
						<bean:write name='stuInfo' property="fqk" />
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						下学期
					</td>
					<td>
						<bean:write name='stuInfo' property="frdcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="frzcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="frtcj" />
					</td>
					<td>
						<bean:write name='stuInfo' property="frkpf" />
					</td>
					<td>
						<bean:write name='stuInfo' property="frbjpm" />
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						毕业鉴定
					</td>
					<td colspan="8" height="200" align="right" valign="bottom">
					班主任：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					年&nbsp;&nbsp;&nbsp;&nbsp;
					月&nbsp;&nbsp;&nbsp;&nbsp;
					日
					</td>
				</tr>
			</table>
			</td>
			</tr>
		</table>
		<br>
		<div align="left" style="font-size:15px;">说明：1、综合测评成绩包括德育成绩、智育成绩和体育成绩，其中德育成绩占30%、智育成绩占60%、体育成绩占10%，综合测评成绩为三项之和。</div>
		<br>
		<div align="left" style="font-size:15px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、本表请用碳素钢笔或蓝黑水笔书写，一式三份，一份<bean:message key="lable.xsgzyxpzxy" />存档、一份<bean:message key="lable.xb" />保存、一份归入学生档案。</div>
		
		
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
