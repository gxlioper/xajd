<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
<html:html>
<body>
	<html:form action="/typj" method="post">
		<table width="95%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
					<h2>
						素质与能力拓展成就奖学金审批表
					</h2>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="5%"></td>
							<td width="5%"></td>
							<td width="10%"></td>
							<td width="5%"></td>
							<td width="7%"></td>
							<td width="7%"></td>
							<td width="10%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="8%"></td>
							<td width="3%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="12%"></td>
						</tr>
						<tr height="40px">
							<td colspan="2" align="center">姓名</td>
							<td align="center">
								${rs.xm }
							</td>
							<td colspan="2" align="center">学号</td>
							<td colspan="2" align="center">
								${rs.xh }
							</td>
							<td colspan="2" align="center">性别</td>
							<td colspan="2" align="center">
								${rs.xb }
							</td>
							<td align="center"><bean:message key="lable.xb" />及<br/>班级</td>
							<td colspan="2" align="center">
								${rs.xymc }<br/>${rs.bjmc }
							</td>
						</tr>
						<tr height="40px">
							<td rowspan="4" align="center">
								申<br/>请<br/>类<br/>别
							</td>
							<td colspan="3">
								<logic:present property="hjdj" name="rs">
									&nbsp;&nbsp;&nbsp;&nbsp;√竞赛
								</logic:present>
								<logic:notPresent property="hjdj" name="rs">
									&nbsp;&nbsp;&nbsp;&nbsp;□竞赛
								</logic:notPresent>
								
							</td>
							<td colspan="2" align="center">
								获奖等级
							</td>
							<td colspan="2" align="center">
								${rs.hjdj }
							</td>
							<td colspan="2" align="center">
								赛事主<br/>办单位
							</td>
							<td colspan="2" align="center">
								${rs.zbdw }
							</td>
							<td align="center">
								证书颁<br/>发单位
							</td>
							<td align="center">
								${rs.bfdw }
							</td>
						</tr>
						<tr height="40px">
							<td colspan="3" >
								<logic:present property="kwmckh" name="rs">
									&nbsp;&nbsp;&nbsp;&nbsp;√著作/论文
								</logic:present>
								<logic:notPresent property="kwmckh" name="rs">
									&nbsp;&nbsp;&nbsp;&nbsp;□著作/论文
								</logic:notPresent>
							</td>
							<td colspan="2" align="center">
								刊物名称<br/>及刊号
							</td>
							<td colspan="2" align="center">
								${rs.kwmckh }
							</td>
							<td colspan="2" align="center">
								作者<br/>排名
							</td>
							<td colspan="2" align="center">
								${rs.zzpm }
							</td>
							<td align="center">
								发表<br/>时间
							</td>
							<td align="center">
								${rs.fbsj }
							</td>
						</tr>
						<tr height="40px">
							<td colspan="3" >
								<logic:present property="cg" name="rs">
									&nbsp;&nbsp;&nbsp;&nbsp;√专利、发明
								</logic:present>
								<logic:notPresent property="cg" name="rs">
									&nbsp;&nbsp;&nbsp;&nbsp;□专利、发明
								</logic:notPresent>
							</td>
							<td colspan="2" align="center">
								成果
							</td>
							<td colspan="3" align="center">
								${rs.cg }
							</td>
							<td colspan="3" align="center">成果级别</td>
							<td colspan="2" align="center">
								${rs.cgjb }
							</td>
						</tr>
						<tr height="60px">
							<td colspan="3" >
								<logic:present property="zysj" name="rs">
									&nbsp;&nbsp;&nbsp;&nbsp;√其他
								</logic:present>
								<logic:notPresent property="zysj" name="rs">
									&nbsp;&nbsp;&nbsp;&nbsp;□其他
								</logic:notPresent>
								
							</td>
							<td colspan="2" align="center">
								主要事迹
							</td>
							<td colspan="8">
								<p style="height:55px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.zysj }
								</p>
							</td>
						</tr>
						<tr>
							<td colspan="14">
								<p style="height:160px">
									获奖申请（综合表现）：
								</p>
								<div align="right">
									学生签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="7">
								<p style="height:160px">
									组织部门意见（限赛事类）：
								</p>
								<div>
									组织者签名：<br/>
									组织部门盖章：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;
								</div>
							</td>
							<td colspan="7">
								<p style="height:160px">
									学生所在<bean:message key="lable.xb" />审核意见:${rs.xyyj }
								</p>
								<div>
									院领导签字<br/>
									<bean:message key="lable.xb" />盖章
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="14">
								<p style="height:160px">
									学校审核意见：${rs.xxyj }
								</p>
								<div align="right">
									（盖章）
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
					</table>
					<div align="left">附件：获奖证明材料附原件及复印件一份</div>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
