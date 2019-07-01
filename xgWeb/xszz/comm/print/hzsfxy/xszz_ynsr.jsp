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
		<table width="80%" border="0" id="theTable" align="center">
			<tr height="60px">
				<td align="center">
					<b>
					<span style='font-size:16.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						“迎南树人”奖学金申请表<br/><br/>
					</span>
					</b>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="printstyle">
						<tr height="30px">
							<td align="center" width="8%">
								<span style='font-size:14.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>姓名</b>
								</span>
							</td>
							<td align="center" width="14%">
								<span style='font-size:14.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xm }
								</span>
							</td>
							<td align="center" width="5%">
								<span style='font-size:14.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>性别</b>
								</span>
							</td>
							<td align="center" width="8%">
								<span style='font-size:14.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xb }
								</span>
							</td>
							<td align="center" width="8%">
								<span style='font-size:14.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>出生年月</b>
								</span>
							</td>
							<td align="center" width="20%" colspan="2">
								<span style='font-size:14.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.csrq }
								</span>
							</td>
							<td rowspan="3" align="center" width="13%">
								<img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" style="width:115;height:125" />
							</td>
						</tr>
						<tr  height="25px">
							<td align="left" colspan="2">
								<span style='font-size:14.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>&nbsp;家&nbsp;庭&nbsp;地&nbsp;址</b>
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:14.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtdz }
								</span>
							</td>
						</tr>
						<tr height="25px">
							<td align="left"  colspan="2">
								<span style='font-size:14.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>&nbsp;所在<bean:message key="lable.xb" />及班级</b>
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:14.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xymc }&nbsp;${rs.bjmc }
								</span>
							</td>
						</tr>
						<tr height="25px">
							<td align="left" colspan="2">
								<span style='font-size:14.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>&nbsp;联系方式</b>
								</span>
							</td>
							<td align="left" colspan="6">
								<span style='font-size:14.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.sjhm }
								</span>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								<p style="height:240px">
									<span style='font-size:14.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									个人简介：(含获奖情况，材料可附页) <br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
									</span>
								</p>
								<div align="right">
									<span style='font-size:14.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									申请人（学生）签名：
									</span>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								<p style="height:80px">
									<span style='font-size:14.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									班主任意见：<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.bzrshyj }
									</span>
								</p>
								<div align="right">
									<span style='font-size:14.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									班主任签名：
									</span>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<p style="height:100px">
									<span style='font-size:14.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<br/>
									<bean:message key="lable.xb" />意见：
									<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
									</span>
								</p>
								<div align="center">
									<span style='font-size:14.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									（盖章）
									<br/><br/>
									</span>
								</div>
								<div align="right">
									<span style='font-size:14.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									年&nbsp;&nbsp;
									月&nbsp;&nbsp;
									日&nbsp;&nbsp;
									</span>
								</div>
							</td>
							<td colspan="3">
								<p style="height:100px">
									<span style='font-size:14.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<br/>
									“迎南树人”奖学金管理委员会办公室：
									<br/>
									</span>
								</p>
								<div align="center">
									<span style='font-size:14.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									（盖章）
									<br/><br/>
									</span>
								</div>
								<div align="right">
									<span style='font-size:14.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									年&nbsp;&nbsp;
									月&nbsp;&nbsp;
									日&nbsp;&nbsp;
									</span>
								</div>
							</td>
							<td colspan="2">
								<p style="height:100px">
									<span style='font-size:14.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<br/>
									学校意见：
									<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxshyj }
									</span>
								</p>
								<div align="center">
									<span style='font-size:14.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									（盖章）
									<br/><br/>
									</span>
								</div>
								<div align="right">
									<span style='font-size:14.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									年&nbsp;&nbsp;
									月&nbsp;&nbsp;
									日&nbsp;&nbsp;
									</span>
								</div>
							</td>
						</tr>
					</table>
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
