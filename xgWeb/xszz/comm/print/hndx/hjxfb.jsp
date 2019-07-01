<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=gb2312">
		<title>海南大学学生缓缴学杂费申请表</title>
		
		<!-- 打印控件begin -->
		<object id="WebBrowser" height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
	</head>
	<body bgcolor="#FFFFFF" lang=ZH-CN>
		<div align="center">
			<p align=center style='text-align: center'>
				<b><span style='font-size: 18.0pt; font-family: 宋体;'>海南大学学生缓缴学杂费申请表</span>
				</b>
			</p>
			<p align="left" style="font-size:15px;">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<bean:message key="lable.xb" />：${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
				学号：${rs.xh }
			</p>
			<table class="printstyle" width="85%">
				<tr>
					<td width="15%" colspan=2 align=center>
						<p>
							姓&nbsp; 名
						</p>
					</td>
					<td width="20%" align=center>
						${rs.xm }
					</td>
					<td width="10%" align=center>
						<p>
							性&nbsp; 别
						</p>
					</td>
					<td width="10%" align=center>
						${rs.xb }
					</td>
					<td width="15%" align=center>
						<p>
							专业班级
						</p>
					</td>
					<td width="30%" align=center>
						${rs.zymc }&nbsp;${rs.bjmc }
					</td>
				</tr>
				<tr>
					<td colspan=2 align=center>
						<p>
							家&nbsp; 庭
						</p>
						<p>
							所在地
						</p>
					</td>
					<td colspan=5 align=left>
						<p>
							&nbsp;&nbsp;${szsheng }&nbsp;&nbsp;省&nbsp;&nbsp;
							${szshi}&nbsp;&nbsp;县（市）&nbsp;&nbsp;
							${szxian }&nbsp;&nbsp; 镇（乡）&nbsp;&nbsp;
							${rs.szzhen }&nbsp;&nbsp;村&nbsp;&nbsp;
							 邮编： ${rs.jtyb }
						</p>
					</td>
				</tr>
				<tr height="120px">
					<td>
						<p align=center>
							家庭经济情况
						</p>
					</td>
					<td colspan=6 align=left height="60px">
						&nbsp;&nbsp;&nbsp;${rs.jtjjqk }
					</td>
				</tr>
				<tr height="120px">
					<td>
						<p align=center>
							拖欠学杂费情况
						</p>
					</td>
					<td colspan=6 align=left height="60px">
						&nbsp;&nbsp;&nbsp;${rs.tqxzfqk }
					</td>
				</tr>
				<tr height="120px">
					<td rowspan=2>
						<p align=center style='text-align: center; line-height: 24.0pt;'>
							缴费计划
						</p>
					</td>
					<td colspan=6 align=left height="60px">
						<p>
							&nbsp;&nbsp;&nbsp;计划：&nbsp;&nbsp;&nbsp;${rs.jfjh }
						</p>
					</td>
				</tr>
				<tr>
					<td colspan=6 align=left height="60px">
						<p>
							&nbsp;&nbsp;&nbsp;缴清欠费截止时间：&nbsp;&nbsp;&nbsp;${rs.jzsj }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr height="120px">
					<td colspan=7 height="60px">
						<p style='layout-grid-mode: char' align="center">
							<b>本人承诺：如不按时缴清所欠学杂费，按学校有关规定处理。</b>
						</p>
						<p align="right">
							本人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
							&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp; 日&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr height="120px">
					<td>
						<p align=center>
							<bean:message key="lable.xb" />意见
						</p>
					</td>
					<td colspan=6 align=center height="60px">
						&nbsp;
					</td>
				</tr>
				<tr height="120px">
					<td>
						<p align=center>
							学校意见
						</p>
					</td>
					<td colspan=6 align=center height="60px">
						&nbsp;
					</td>
				</tr>
			</table>
			<p align="right">
				<span style='font-family: 宋体;' >校学生工作处制&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</p>
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
