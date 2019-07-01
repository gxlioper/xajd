<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
<html:html>
<body>
	<html:form action="/typj" method="post">
		<table width="95%" border="0" id="theTable" align="center">
			<tr>
				<td align="left">
					<h3>
						附表1
					</h3>
				</td>
			</tr>
			<tr>
				<td align="center">
					<h2>
						${rs.jxjmc }申请表
					</h2>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr height="40px">
							<td rowspan="5" align="center" style="width:25px">本人<br/>情况</td>
							<td align="center">姓名</td>
							<td align="center" colspan="2"  width="70px">${rs.xm }</td>
							<td align="center" >性别</td>
							<td align="center" colspan="2">${rs.xb }</td>
							<td align="center" colspan="2" style="width:80px">出生年月</td>
							<td align="center">${stuMap.csrq }</td>
							<td rowspan="4" align="center" style="width:110px">
								<img
									src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" style="width:140;height:160" />
							</td>
						</tr>
						<tr height="40px">
							<td align="center" >民族</td>
							<td align="center" colspan="2">${stuMap.mzmc }</td>
							<td align="center" >政治面貌</td>
							<td align="center" colspan="2">${stuMap.zzmmmc }</td>
							<td align="center" colspan="2" style="width:80px">入学时间</td>
							<td align="center">${stuMap.rxrq }</td>
							
						</tr>
						<tr height="40px">
							<td align="center">身份证号</td>
							<td align="center" colspan="4">${stuMap.sfzh }</td>
							<td align="center" colspan="2">联系电话</td>
							<td align="center" colspan="2">${stuMap.lxdh }</td>
						</tr>
						<tr height="40px">
							<td colspan="9" align="center">
							&nbsp;&nbsp;&nbsp;&nbsp;${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp; <bean:message key="lable.xb" />（二级）
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							${rs.bjmc } &nbsp;&nbsp;&nbsp;&nbsp;班
							</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">曾获何种奖励</td>
							<td colspan="8" >
								&nbsp;&nbsp;&nbsp;&nbsp;${rs.hjqk }
							</td>
						</tr>
						<tr height="40px">
							<td align="center" rowspan="3" style="width:25px">家庭<br/>经济<br/>情况</td>
							<td align="center" colspan="2">
								家庭户口
							</td>
							<td colspan="6" align="center">
								${rs.jthk }
							</td>
							<td align="center" >
								家庭总人口数
							</td>
							<td align="center">
								${rs.rkzs }
							</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">
								家庭总收入
							</td>
							<td colspan="2" align="center">
								${rs.jtyzsr }
							</td>
							<td align="center" colspan="2">
								人均收入
							</td>
							<td colspan="2" align="center">
								${rs.rjsr }
							</td>
							<td align="center" >
								收入来源
							</td>
							<td align="center">
								${rs.srly }
							</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">
								家庭详细住址
							</td>
							<td colspan="6" align="center">
								${rs.jtxxdz }
							</td>
							<td align="center" >
								邮政编码
							</td>
							<td align="center">
								${rs.yzbm }
							</td>
						</tr>
						<tr>
							<td colspan="11">
								<p style="height:140px">学习成绩：
									<logic:present  name="cjxx">
										<table class="tbstyle" width="100%"  >
											<tr>
												<td>学年</td>
												<td>课程名称</td>
												<td>课程性质</td>
												<td>成绩</td>
												<td>补考成绩</td>
											</tr>
											<logic:iterate id="s" name="cjxx">
												<tr>
													<logic:iterate id="v" name="s" offset="2">
														<td>
															<bean:write name="v"/>
														</td>
													</logic:iterate>
												</tr>
											</logic:iterate>
										</table>
									</logic:present>
								</p>
							</td>
						</tr>
						<tr>
							<td colspan="11">
								<p style="height:90px">申请理由：
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
									<div align="right">
										申请人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									    二〇〇&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
									</div>
								</p>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<p style="height:90px">
									班委民评意见：
									<br/><br/><br/><br/>
									<div align="right">
										签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<br/>
										 二〇〇&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
									</div>
								</p>
							</td>
							<td colspan="5">
								<p style="height:90px">
									辅导员意见：
									${rs.fdyyj }
	
									<div align="right" style="margin-bottom: 0px">
										签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<br/>
										  二〇〇&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
									</div>
								</p>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<p style="height:90px">
									二级<bean:message key="lable.xb" />审核意见：
									${rs.xyyj }
									<div align="right" style="margin-bottom: 0px">
										负责人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										（公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<br/>
										 二〇〇&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
									</div>
								</p>
							</td>
							<td colspan="5">
								<p style="height:90px">
									<bean:message key="lable.xb" />资助管理中心审核：
									<br/><br/><br/><br/>
									<div align="right">
										（公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<br/>
										  二〇〇&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
									</div>
								</p>
							</td>
						</tr>
						<tr>
							<td colspan="11">
								<p style="height:100px">
									学校审批：
									<br/><br/><br/><br/>
									<div align="right">
										（公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<br/>
										 二〇〇&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
									</div>
								</p>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
