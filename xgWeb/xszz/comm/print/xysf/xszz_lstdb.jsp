<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<html:html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=gb2312">
		<title>信阳师范学院新生入学“绿色通道”申请表</title>

		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
	</head>
	<body>
		<div>
			<p align=center>
				<b><span style='font-size: 16.0pt; font-family: 黑体'>信阳师范学院新生入学“绿色通道”申请表</span>
				</b>
			</p>
			<div align=center>
				<table class="tbstyle" border="0" align="center" style="width: 90%">
					<tr>
						<td rowspan=2 width="13%">
							<p align=center>
								本人
								<br />
								情况
								<br />
							</p>
						</td>
						<td width="8%">
							<p align=center>
								姓名
							</p>
						</td>
						<td width="16%" colspan=2>
							${rs.xm }
						</td>
						<td width="8%">
							<p align=center>
								性别
							</p>
						</td>
						<td width="8%">
							${rs.xb }
						</td>
						<td width="14%" colspan=2>
							<p align=center>
								出生年月
							</p>
						</td>
						<td width="16%" colspan=2 class="Normal">
							${rs.csrq }
						</td>
						<td width="8%">
							<p align=center>
								民族
							</p>
						</td>
						<td width="8%">
							${rs.mzmc}
						</td>
					</tr>
					<tr>
						<td colspan="7">
							<p align=left>
								<bean:message key="lable.xb" />：&nbsp;${rs.xymc }&nbsp;&nbsp;<br/>
								专业：&nbsp;${rs.zymc } &nbsp;&nbsp;<br/>
								班级：&nbsp;${rs.bjmc }<br/>
							</p>
						</td>
						<td colspan=2>
							<p align=center>
								应交费金额
							</p>
						</td>
						<td colspan=2>
							<p align=center>
							${rs.yjje }
							</p>
						</td>
					</tr>
					<tr>
						<td rowspan=${cyNum+1 }>
							<p align=center>
								家庭成员情况
							</p>
						</td>
						<td colspan=2>
							<p align=center>
								姓&nbsp;名
							</p>
						</td>
						<td>
							<p align=center>
								年龄
							</p>
						</td>
						<td colspan=3>
							<p align=center>
								与本人关系
							</p>
						</td>
						<td colspan=3>
							<p align=center>
								工作或学习单位
							</p>
						</td>
						<td colspan=2>
							<p align=center>
								联系电话
							</p>
						</td>
					</tr>
					<logic:iterate name="cyList" id="cy">
				<tr>
					<td width="10%" colspan=2>
						<div align="center">
							${cy.cyxm }&nbsp;&nbsp;
						</div>
					</td>
					<td width="10%">
						<div align="center">
							${cy.cynl }&nbsp;&nbsp;
						</div>
					</td>
					<td width="10%" colspan=3>
						<div align="center">
							${cy.mc }&nbsp;&nbsp;
						</div>
					</td>
					<td width="10%" colspan=3>
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
<%--					<tr>--%>
<%--						<td colspan=3>--%>
<%--							<p align=center>--%>
<%--								缓交金额（元）--%>
<%--							</p>--%>
<%--						</td>--%>
<%--						<td colspan=5>--%>
<%--							${rs.qjfy }--%>
<%--						</td>--%>
<%--						<td colspan=2>--%>
<%--							<p align=center>--%>
<%--								缓交期限（月）--%>
<%--							</p>--%>
<%--						</td>--%>
<%--						<td colspan=2>--%>
<%--							${rs.hjqx }--%>
<%--						</td>--%>
<%--					</tr>--%>
					<tr>
						<td>
							<p align=center>
								申
								<br />
								请
								<br />
								理
								<br />
								由
							</p>
						</td>
						<td colspan=11 valign=bottom>
							<p>
								${rs.sqly }
							</p>
							<p align=right>
								申请人签名：
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</p>
						</td>
					</tr>
					<tr>
						<td>
							<p align=center>
								班
								<br />
								主
								<br />
								任
								<br />
								意
								<br />
								见
								<br />
							</p>
						</td>
						<td colspan=11 valign=bottom>
							<p align=right>
								班主任签名：
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</p>
							<p align=right>
								年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;
							</p>
						</td>
					</tr>
					<tr>
						<td>
							<p align=center>
								所在
								<br />
								<bean:message key="lable.xb" />
								<br />
								意见
								<br />
							</p>
						</td>
						<td colspan=11 valign=bottom>
							<p>
								&nbsp;
							</p>
							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;同意暂交&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;元
							</p>
							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;同意缓交&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;元
								（期限：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;）
							</p>
							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（盖章）&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;
							</p>
							<p align="right">
								年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;
							</p>
						</td>
					</tr>
					<tr>
						<td>
							<p align=center>
								学
								<br />
								校
								<br />
								意
								<br />
								见
								<br />
							</p>
						</td>
						<td colspan=11>
							<p align=center>
								<br/>
								<span style='font-size:24.0pt;font-family:华文行楷'>同 意</span>
							</p>
							<p align=right>
								学生工作处 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</p>
							<p align=right>
								年&nbsp;&nbsp;月 &nbsp;&nbsp;日 &nbsp;
							</p>
						</td>
					</tr>
				</table>
				<br/><br/><br/>
				<table border="0">
				<tr>
				<td align="left">
				<span style='font-size: 12.0pt; font-family: 仿宋_GB2312'>
							此表一式两份，财务处、学生处各存一份。申请时需附个人申请及有关困难证明材料。</span>
				
				</td>
				</tr>
				</table>
			</div>
		</div>
		
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
