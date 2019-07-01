<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
<html:html>
<body>
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
	<html:form action="/typj" method="post">
		<br/><br/><br/><br/>
		<table width="80%" border="0" id="printstyle" align="center">
			<tr height="50px">
				<td align="center">
					<b>
					<span style='font-size:16.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						湖州师范学院紧急救助金申请表<br/>
					</span>
					</b>
					<div align="right">
						<br/>
						<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						填表日期：&nbsp;&nbsp;&nbsp;&nbsp;
						</span>
						<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						年&nbsp;&nbsp;&nbsp;&nbsp;
						月&nbsp;&nbsp;&nbsp;&nbsp;
						日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>
					</div>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="printstyle">
						<tr>
							<td width="15%"></td>
							<td width="6%"></td>
							<td width="10"></td>
							<td width="14"></td>
							<td width="20%"></td>
							<td width="35%"></td>
						</tr>
						<tr height="40px">
							<td align="left" colspan="2">
								<span style='font-size:14.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								姓&nbsp;&nbsp;&nbsp;&nbsp;名
								</span>
							</td>
							<td align="left" colspan="2">
								<span style='font-size:14.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xm }
								</span>
							</td>
							<td align="left">
								<span style='font-size:14.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								班级学号
								</span>
							</td>
							<td align="left">
								<span style='font-size:14.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xh }
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="left" colspan="2">
								<span style='font-size:14.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<bean:message key="lable.xb" />名称
								</span>
							</td>
							<td align="left" colspan="2">
								<span style='font-size:14.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xymc }
								</span>
							</td>
							<td align="left">
								<span style='font-size:14.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								联系方式
								</span>
							</td>
							<td align="left">
								<span style='font-size:14.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.sjhm }
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="left" >
								<span style='font-size:14.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								性&nbsp;&nbsp;&nbsp;&nbsp;别
								</span>
							</td>
							<td align="left" colspan="2">
								<span style='font-size:14.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xb }
								</span>
							</td>
							<td align="left">
								<span style='font-size:14.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								生源地
								</span>
							</td>
							<td align="left" colspan="2">
								<span style='font-size:14.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.sydqmc }
								</span>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<p style="height:170px">
									<span style='font-size:14.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									申请理由： <br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
									</span>
								</p>
								<div align="right">
									<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									本人签名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/><br/>
									</span>
								</div>
								<div align="right">
									<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									年&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</span>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<p style="height:60px">
									<span style='font-size:14.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<bean:message key="lable.xb" />党总支意见： <br/>
									</span>
									&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
								<div align="right">
									<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;盖章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/><br/>
									</span>
								</div>
								<div align="right">
									<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									年&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</span>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<p style="height:60px">
									<span style='font-size:14.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									学生处审核： <br/>
									</span>
									&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
								<div align="right">
									<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;盖章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/><br/>
									</span>
								</div>
								<div align="right">
									<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									年&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</span>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<p style="height:60px">
									<span style='font-size:14.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									分管领导审批： <br/>
									</span>
									&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
								<div align="right">
									<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/><br/>
									</span>
								</div>
								<div align="right">
									<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									年&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</span>
								</div>
							</td>
						</tr>
					</table>
					<div align="right">
						<br/>
						<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						湖州师院帮困救助中心办公室制&nbsp;&nbsp;&nbsp;&nbsp;
						</span>
					</div>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center" class='noPrin'>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			页面设置
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			打印预览
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			直接打印
		</button>
	</div>
</body>
</html:html>
