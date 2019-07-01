<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
<html:html>
<body>
	<!-- 打印控件begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
			.noPrin{display:none;};
			.PageNext{
			page-break-after: always;
		}
		</style>
	<!-- end -->
	<html:form action="/stu_info_add" method="post">
		<table width="95%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
					<h2>
						四川省体育彩票助学金申请审批表（体育专业学生）
					</h2>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="90%" id="rsT" class="printstyle">
						<tr align="center" style="height: 35px">
							<td colspan=2>
								姓 &nbsp;名
							</td>
							<td colspan=2>
								${rs.xm}
							</td>
							<td colspan=2>
								性别
							</td>
							<td>
								${rs.xb}
							</td>
							<td>
								出生
								<br />
								年月
							</td>
							<td colspan=3>
								${rs.csrq}
							</td>
							<td rowspan=4>
								贴申请人
								<span lang=EN-US>2</span>寸照片
							</td>
						</tr>
						<tr align="center" style="height: 35px">
							<td colspan=2>
								院系
							</td>
							<td colspan=4>
								${rs.xymc}
							</td>
							<td colspan=3>
								年级/班级
							</td>
							<td colspan=2>
								${rs.nj}/${rs.bjmc}
							</td>
						</tr>
						<tr align="center" style="height: 35px">
							<td colspan=2>
								家庭地址
							</td>
							<td colspan=9 valign=middle>
								${rs.jtdz}
							</td>
						</tr>
						<tr align="center" style="height: 35px">
							<td colspan=2>
								联系电话
							</td>
							<td colspan=9>
								${rs.lxdh}
							</td>
						</tr>
						<tr align="center" style="height: 35px">
							<td rowspan=4>
								家庭<br/>基本<br/>情况
							</td>
							<td colspan=2>
								家庭成员
							</td>
							<td colspan=2>
								姓 名
							</td>
							<td colspan=7>
								家 庭 经 济 情 况 说 明
							</td>
						</tr>
						<logic:iterate name="cyList" id="cy">
						<tr align="center" style="height: 35px">
							<td colspan=2>
								&nbsp;${cy.mc }
							</td>
							<td colspan=2 >
								&nbsp;${cy.cyxm }
							</td>
							<td colspan=7>
								&nbsp;
							</td>
						</tr>
						</logic:iterate>
						<tr align="center" style="height: 35px">
							<td colspan=2 valign=middle>
								出具贫困<br/>证明单位
							</td>
							<td colspan=7 valign=middle>
								&nbsp;
							</td>
							<td valign=middle colspan="2">
								联 系<br/>电 话
							</td>
							<td valign=middle>
								&nbsp;
							</td>
						</tr>
						<tr align="center" style="height: 170px">
							<td>
								本人<br/>在校<br/>表现<br/>情况
							</td>
							<td colspan=11 valign=middle align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
							</td>
						</tr>
						<tr style="height: 130px">
							<td align="center">
								院系<br/>评审<br/>意见
							</td>
							<td colspan=11 valign=middle>
								<p style="height:110px" align="left">
									<span style="height: 100px">&nbsp;&nbsp;&nbsp;&nbsp;${rs.shzt1yj }</span>
								</p>
								<span style="vertical-align: bottom;float: left;margin-left: 30px">系（部）领导签字：</span>
								<span style="vertical-align: bottom;float: left;margin-left: 120px">系（部）盖章：</span>
								<br/>
								<span  style="vertical-align: bottom;float: right;margin-right: 40px">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日 </span>
							</td>
						</tr>
						<tr style="height: 130px">
							<td align="center">
								学校<br/>审查<br/>意见
							</td>
							<td colspan=11 valign=middle>
								<p style="height:110px" align="left">
									<span style="height: 100px">&nbsp;&nbsp;&nbsp;&nbsp;${rs.shzt2yj }</span>
								</p>
								<span style="vertical-align: bottom;float: left;margin-left: 30px">校领导签字：</span>
								<span style="vertical-align: bottom;float: left;margin-left: 150px">学校盖章：</span>
								<br/>
								<span  style="vertical-align: bottom;float: right;margin-right: 40px">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日 </span>
							</td>
						</tr>
						<tr style="height: 130px">
							<td align="center">
								四川省<br/>体育局<br/>审批<br/>意见
							</td>
							<td colspan=11 valign="middle">
								<span style="height: 100px"></span>
								<span style="vertical-align: bottom;float: left;margin-left: 30px">领导签字：</span>
								<span style="vertical-align: bottom;float: left;margin-left: 150px">省体育局盖章：</span>
								<br/>
								<span  style="vertical-align: bottom;float: right;margin-right: 40px">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日 </span>
							</td>
						</tr>
						<tr height=0>
							<td width=10%></td>
							<td width=6%></td>
							<td width=8%></td>
							<td width=8%></td>
							<td width=4%></td>
							<td width=4%></td>
							<td width=8%></td>
							<td width=7%></td>
							<td width=1%></td>
							<td width=6%></td>
							<td width=6%></td>
							<td width=24%></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

	</html:form>
</body>
</html:html>
