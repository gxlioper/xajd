<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=gb2312">
		<title>南通职业大学校园活动审批表</title>
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->
	</head>
	<body>
		<div align="center">
			<span style='font-size: 18.0pt; font-family: 华文中宋'>南通职业大学校园活动备案审批表</span>
			<p align=center>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span style='font-size: 14.0pt; font-family: 宋体'>申请日期：
				
				<logic:notEqual value="" name="rs" property="sqsj">
					 ${rs.sqsj }
				</logic:notEqual>
				
				<logic:equal value="" name="rs" property="sqsj">
				<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</u>年<u>&nbsp;&nbsp;&nbsp; </u>月<u>&nbsp; &nbsp;&nbsp;&nbsp;</u>日
				</logic:equal>
				</span>
			</p>
			<table class="tbstyle" width="85%" height="700px">
				<tr>
					<td align="center" width="20%">
						申请单位
					</td>
					<td colspan=3 align="center" width="40%">
						${rs.sqdw }
					</td>
					<td colspan=2 align="center" width="20%">
						总负责人
					</td>
					<td align="center" width="20%">
						${rs.zfzr }
					</td>
				</tr>
				<tr>
					<td align="center">
						活动拟开始时间
					
					</td>
					<td colspan=3 align="center">${rs.kssj }</td>
					
					<td colspan=2 align="center">
						地点
					</td>
					<td align="center">
						${rs.dd }
					</td>
				</tr>
				<tr>
					<td align="center">
						活动内容
					</td>
					<td colspan=3 align="center">
						${rs.hdnr }
					</td>
					<td colspan=2 align="center">
						参与人数
						
					</td>
					<td align="center">
						${rs.cyrs }
					</td>
				</tr>
				<tr>
					<td align="center">
						现场负责人一
						
					</td>
					<td colspan=2 align="center">
						${rs.xcfzr1 }
					</td>
					<td colspan=2 align="center">
						联系电话
					</td>
					<td colspan=2 align="center">
						${rs.fzr1dh }
					</td>
				</tr>
				<tr>
					<td align="center">
						现场负责人二
						
					</td>
					<td colspan=2 align="center">
						${rs.xcfzr2 }
					</td>
					<td colspan=2 align="center">
						联系电话
					</td>
					<td colspan=2 align="center">
						${rs.fzr2dh }
					</td>
				</tr>
				<tr height="200px">
					<td colspan=7 align="left" valign="top">
						活动方案简要描述：
						<p style="height: 140px">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.hdfa }
						</p>
					</td>
				</tr>
				<tr height="160px">
					<td colspan=3 align="left" valign="top">
						<p style="height: 140px">申请单位意见：<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqdwyj }
						</p>
						<p align="right">
						总负责人（签字）
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u><br/>
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>	年
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>	月
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>	日					
						<br/>
						</p>
					</td>
					<td colspan=4 align="left" valign="top">
						<p style="height: 120px">备案部门意见：</p>
						<p align="right">
						负责人（签字）<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						<br/>
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>	年
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>	月
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>	日					
						<br/>
						</p>
					</td>
				</tr>
			</table>
			<table border="0" width="85%">
			<tr>
			<td>
			<span style='font-family: 宋体;'>
			填表说明：<br/>
			&nbsp;&nbsp;&nbsp;&nbsp; 1、本表格适用于参与（现场）人数在10人以上、在学校公共场合举行的各类活动。<br/>
			&nbsp;&nbsp;&nbsp;&nbsp; 2、“总负责人”一般为各院系（部门）分管学生工作的负责人，“现场负责人一”一般为该院系（部门）辅导员，“现场负责人二”一般为始终在活动现场的团总支常务副书记、学生分会主席一级学生干部；所填写的联系电话为一直保持通讯畅通的移动电话号码。<br/>
			&nbsp;&nbsp;&nbsp;&nbsp; 3、本表一式三份，一份留存保卫处，一份留存团委，一份由申请单位留存。</span>
			
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
