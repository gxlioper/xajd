<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
<html:html>
<body>
	<html:form action="/typj" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
						<h2>
							<strong> 普通本科高校、高等职业学校国家励志奖学金申请表 </strong>
						</h2>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<br />
					<br />
					&nbsp;
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr height="40px">
							<td align="center" rowspan="5" width="6%">本<br />人<br />情<br />况</td>
							<td width="13%" align="center">姓名</td>
							<td width="13%" align="center">${rs.xm }</td>
							<td width="10%" align="center">性别</td>
							<td width="14%" align="center">${rs.xb }</td>
							<td width="10%" align="center">出生年月</td>
							<td width="16%" align="center">${stuMap.csrq }</td>
							<td width="18%" rowspan="4" align="center">
								<img
									src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" style="width:160;height:160" />
							</td>
						</tr>
						<tr height="40px">
							<td align="center">民族</td>
							<td align="center">${stuMap.mzmc }</td>
							<td align="center">政治<br />面貌</td>
							<td align="center">${stuMap.zzmmmc }</td>
							<td align="center">入学时间</td>
							<td align="center">${stuMap.rxrq }</td>
						</tr>
						<tr height="40px">
							<td align="center">身份证号码</td>
							<td colspan="3" align="center">${stuMap.sfzh }</td>
							<td align="center">联系电话</td>
							<td align="center">${stuMap.lxdh }</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="6">
									${xxmc }
									大学
									${rs.xymc }
									<logic:notPresent name="rs">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:notPresent>
									<bean:message key="lable.xb" />
									${rs.zymc }<logic:notPresent name="rs">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:notPresent>
									系
									${rs.bjmc }<logic:notPresent name="rs">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:notPresent>
									班
							</td>
						</tr>
						<tr height="50px">
							<td colspan="2" align="center">曾获何种奖励</td>
							<td colspan="5">
								&nbsp;&nbsp;&nbsp;&nbsp;${rs.hjqk }
							</td>
						</tr>
						<tr height="50px">
							<td rowspan="3" align="center">家<br />庭<br />经<br />济<br />情<br />况</td>
							<td align="center">家庭户口</td>
							<td colspan="4">
								<div align="center">
									<logic:notPresent name="rs">
										A、
									城镇&nbsp;&nbsp;&nbsp;&nbsp;
										B、
									农村
									</logic:notPresent>
									<logic:present name="rs">
										<logic:equal value="城镇" name="rs" property="jthk">
											A、√
												城镇&nbsp;&nbsp;&nbsp;&nbsp;
												B、
												农村
										</logic:equal>
										<logic:equal value="农村" name="rs" property="jthk">
											A、
												城镇&nbsp;&nbsp;&nbsp;&nbsp;
												B、√
												农村
										</logic:equal>
										<logic:equal value="" name="rs" property="jthk">
												A、
												城镇&nbsp;&nbsp;&nbsp;&nbsp;
												B、
												农村
										</logic:equal>
									</logic:present>
								</div>
							</td>
							<td align="center">家庭人口总数</td>
							<td align="center">${rs.rkzs }</td>
						</tr>
						<tr height="50px">
							<td align="center">家庭月总收入
							</td>
							<td align="center">${rs.jtyzsr }</td>
							<td colspan="2" align="center" >人均月收入</td>
							<td align="center">${rs.rjsr }</td>
							<td align="center">收入来源</td>
							<td align="center">${rs.srly }</td>
						</tr>
						<tr height="50px">
							<td align="center">家庭住址</td>
							<td colspan="4">${rs.jtxxdz }</td>
							<td align="center">邮政编码</td>
							<td align="center">${rs.yzbm }</td>
						</tr>
						<tr>
							<td colspan="8" >
								学习成绩
								<div style="height:80px">
									<logic:present  name="cjxx">
										<table class="tbstyle" width="100%"  >
											<tr>
												<td align="center">学年</td>
												<td align="center">课程名称</td>
												<td align="center">课程性质</td>
												<td align="center">成绩</td>
												<td align="center">补考成绩</td>
											</tr>
											<logic:iterate id="s" name="cjxx">
												<tr>
													<td align="center">${s.xn }</td>
													<td align="center">${s.kcmc }</td>
													<td align="center">${s.kcxz }</td>
													<td align="center">${s.cj }</td>
													<td align="center">${s.bkcj }</td>
												</tr>
											</logic:iterate>
										</table>
									</logic:present>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								申请理由
								<p style="height:120px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
								</p>
								<div align="right">
									申请人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								学校审核意见：
								<p style="height:140px">
								&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxyj }
								</p>
								<div align="right">
									（公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
