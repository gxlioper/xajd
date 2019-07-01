<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=gb2312">
		<title>信阳师范学院特殊困难补助审批表</title>

		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
	</head>
	<body bgcolor="#FFFFFF" lang=ZH-CN>
		<div class=Section1 style='layout-grid: 15.6pt'>
			<p align=center>
				<b><span style='font-size: 18.0pt; font-family: 黑体'>信阳师范学院<u>&nbsp;&nbsp;${rs.xmmc}&nbsp;&nbsp;</u>申请审批表</span>
				</b>
			</p>
			<table class="tbstyle" border="0" align="center" style="width: 90%">
				<tr>
					<td width="9%">
						<p align=center>
							姓名
						</p>
					</td>
					<td width="12%">
						${rs.xm }
					</td>
					<td width="9%">
						<p align=center>
							性别
						</p>
					</td>
					<td width="9%">
						${rs.xb }
					</td>
					<td width="16%">
						<p align=center>
							专业、班级
						</p>
					</td>
					<td width="19%">
						${rs.zymc }&nbsp;${rs.mjmc }
					</td>
					<td width="8%">
						<p align=center>
							学号
						</p>
					</td>
					<td width="14%">
						${rs.xh }
					</td>
				</tr>
				<tr>
					<td width="21%" colspan=2>
						<p align=center>
							家庭住址
						</p>
					</td>
					<td width="78%" colspan=6>
						${rs.jtdz }
					</td>
				</tr>
				<tr>
					<td width="9%" rowspan=${cyNum+1 }>
						<p align=center>
							家庭成员情况
						</p>
					</td>
					<td width="12%">
						<p align=center>
							姓名
						</p>
					</td>
					<td width="9%">
						<p align=center>
							年龄
						</p>
					</td>
					<td width="9%">
						<p align=center>
							称谓
						</p>
					</td>
					<td width="35%" colspan=2>
						<p align=center>
							工作或学习单位
						</p>
					</td>
					<td width="23%" colspan=2>
						<p align=center>
							联系电话
						</p>
					</td>
				</tr>
				<logic:iterate name="cyList" id="cy">
					<tr>
						<td width="10%">
							<div align="center">
								${cy.cyxm }&nbsp;&nbsp;
							</div>
						</td>
						<td width="10%">
							<div align="center">
								${cy.cynl }&nbsp;&nbsp;
							</div>
						</td>
						<td width="10%">
							<div align="center">
								${cy.mc }&nbsp;&nbsp;
							</div>
						</td>
						<td width="10%" colspan=2>
							<div align="center">
								${cy.cygzdw }&nbsp;&nbsp;
							</div>
						</td>
						<td width="10%" colspan=2>
							<div align="center">
								${cy.cydh }&nbsp;&nbsp;
							</div>
						</td>
					</tr>
				</logic:iterate>
				<tr>
					<td width="9%">
						<p align=center>
							家庭困难情况说明
						</p>
					</td>
					<td width="90%" colspan=7>
						<p>
							&nbsp;&nbsp;${rs.sqsm }
						</p>
						<p align=right>
							申请人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align=right>
							年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;
						</p>
					</td>
				</tr>
			</table>
		</div>
		<div align="center" class='noPrin'>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				页面设置
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				打印预览
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6, 6);">
				直接打印
			</button>
		</div>
	</body>
</html>
