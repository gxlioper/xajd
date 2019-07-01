<%@ page language="java" contentType="text/html; charset=GBK"%>
<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=gb2312">

		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />	
		<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
		<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		
		<script language="javascript" src="js/commanFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/xszzFunction.js"></script>
		<script language="javascript" src="js/String.js"></script>

	</head>
	<body bgcolor="#FFFFFF" lang=ZH-CN>
		<div class=Section1 style='layout-grid:15.6pt' align="center">
			<p align=center style='text-align:center;line-height:16.0pt;
'>
				<b><span
					style='font-size:18.0pt;font-family:
宋体;&quot;Times New Roman&quot;'>
						宁波天一职业技术学院<br/><u>&nbsp;&nbsp;${rs.xn }&nbsp;&nbsp;</u>学年清寒天使奖学金申请表</span></b>
			</p>
			<table class="tbstyle" width="90%">
				<tr>
					<td width="8%" rowspan=4 align="center">
						本<br/>人<br/>情<br/>况<br/>
					</td>
					<td width="10%" align="center">
						姓名
					</td>
					<td width="15%" colspan=2 align="center">
						${rs.xm }
					</td>
					<td width="10%" align="center">
						性别
					</td>
					<td width="10%" align="center">
						${rs.xb }
					</td>
					<td width="12%" colspan=2 align="center">
						出生年月
					</td>
					<td width="15%" align="center">
						${rs.csrq }
					</td>
					<td width="10%" colspan=2 align="center">
						民族
					</td>
					<td width="10%" align="center">
						${rs.mzmc}
					</td>
				</tr>
				<tr>
					<td align="center">
						学号
					</td>
					<td colspan=2 align="center">
						${rs.xh }
					</td>
					<td colspan=3 align="center">
						专业、年级、班级
					</td>
					<td colspan=5 align="center">
						${rs.zymc }&nbsp;${rs.nj }&nbsp;${rs.bjmc }
					</td>
				</tr>
				<tr>
					<td align="center">
						奖惩情况
					</td>
					<td colspan=6 align="center">
						<p>
							&nbsp;&nbsp;${rs.jcqk}
						</p>
					</td>
					<td colspan=2 align="center">
						品德等第
					</td>
					<td colspan=2 align="center">
						${rs.pddd }
					</td>
				</tr>
				<tr>
					<td colspan=2 align="center">
						何学年获何<br/>种资助（写明<br/>受助金额）
					</td>
					<td colspan=5 align="center">
						${rs.hxnhzzz }
					</td>
					<td colspan=2 align="center">
						是否参加勤<br/>工助学和申<br/>请助学贷款
					</td>
					<td colspan=2 align="center">
						${rs.help }
					</td>
				</tr>
				<tr>
					<td colspan=12 valign=top>
						<p>
							申请理由：（应写明家庭具体困难情况，本人及家庭成员健康状况，目前的学习生活，品行表现。）
						</p>
						<p>
							&nbsp;&nbsp;${rs.sqly }
						</p>
						<p>
							本人保证以上内容与事实相符。
						</p>
						<p align="right">
							申请人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr>
					<td colspan=12 valign=top>
						<p>
							班级评议：（应写明家庭困难情况及程度、品行表现、学习生活现状。）
						</p>
							
						<p>
							&nbsp;&nbsp;${rs.bjpy }
						</p>
						<p align="right">
							签名：
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr>
					<td colspan=12 valign=top>
						<p>
							院系意见及公示结果：
						</p>
						<p>
							&nbsp;&nbsp;${rs.xyshyj }
						</p>
						<p align="right">
							签名：
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr>
					<td colspan=12 valign=top>
						<p>
							学生处意见：
						</p>
						<p>
							&nbsp;&nbsp;${rs.xxshyj }
						</p>
						<p align="right">
							签名：
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr>
					<td colspan=12 valign=top>
						<p>
							院领导审核意见：
						</p>
						<p>
							&nbsp;&nbsp;${rs.xxshyj }
						</p>
						<p align="right">
							签名：
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
			</table>
		</div>
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
	</body>
</html>

