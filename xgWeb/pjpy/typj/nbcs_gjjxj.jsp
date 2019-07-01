<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
<html:html>
<body>
	<html:form action="/typj" method="post">
		<table width="95%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
					<font size="5">
						<strong>国家奖学金申请审批表</strong>
					</font>
					<font size="4">(&nbsp;&nbsp;&nbsp;&nbsp;${rs.xn }&nbsp;&nbsp;&nbsp;&nbsp;学年)</font>
				</td>
			</tr>
			<tr>
				<td align="left">
						<strong>学校：${xxmc }
						院系：${rs.xymc }<logic:notPresent name="rs">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:notPresent>
						专业：${rs.zymc }<logic:notPresent name="rs">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:notPresent>
						班级：${rs.bjmc }<logic:notPresent name="rs">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</strong>											 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:notPresent>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="7%"></td>
							<td width="10%"></td>
							<td width="4"></td>
							<td width="5%"></td>
							<td width="4%"></td>
							<td width="5%"></td>
							<td width="4%"></td>
							<td width="5%"></td>
							<td width="4%"></td> 
							<td width="5%"></td>
							<td width="4%"></td>
							<td width="5%"></td>
							<td width="4%"></td>
							<td width="5%"></td>
							<td width="4%"></td>
							<td width="5%"></td>
							<td width="4%"></td>
							<td width="5%"></td>
							<td width="4%"></td>
							<td width="5%"></td>
						</tr>
						<tr height="50px">
							<td align="center" rowspan="4"><strong>基<br/>本<br/>情<br/>况</strong></td>
							<td align="center">姓名</td>
							<td align="center" colspan="3">${rs.xm }</td>
							<td align="center" colspan="4">性别</td>
							<td align="center" colspan="3">${rs.xb }</td>
							<td align="center" colspan="4">出生年月</td>
							<td align="center" colspan="4">${stuMap.csrq }</td>
						</tr>
						<tr height="50px">
							<td align="center">学号</td>
							<td align="center" colspan="3">${rs.xh }</td>
							<td align="center" colspan="4">民族</td>
							<td align="center" colspan="3">${stuMap.mzmc }</td>
							<td align="center" colspan="4">入学时间</td>
							<td align="center" colspan="4">${stuMap.rxrq }</td>
						</tr>
						<tr height="50px">
							<td align="center">身份证号</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr height="50px">
							<td align="center">政治面貌</td>
							<td align="center" colspan="3">${stuMap.zzmmmc }</td>
							<td align="center" colspan="7">
								联系电话
							</td>
							<td align="center" colspan="8">
								${stuMap.lxdh }
							</td>
						</tr>
						<tr height="150px">
							<td align="center"><strong>学<br/>习<br/>等<br/>情<br/>况</strong></td>
							<td colspan="19">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;该学年必修课程数<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>门，
								其中，优秀<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>门，
								良好<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>门<br/><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;成绩排名（专业或年级）：<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>（名次/总人数）<br/><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;综合考评成绩<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>分，
								排名<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>（名次/总人数）（如无此项，填写“无”）
							</td>
						</tr>
						<tr height="150px">
							<td align="center"><strong>获<br/>奖<br/>情<br/>况</strong></td>
							<td colspan="19">
								<p style="height:80px">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;主要奖项:
								</p>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								其中，院级奖励<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>项；&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    校级奖励<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>项；&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    省级以上奖励<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>项
							</td>
						</tr>
						<tr height="400px">
							<td align="center">
							<br/><strong>申<br/>请<br/>理<br/>由<br/>︵<br/>全<br/>面<br/>反<br/>映<br/>德<br/>智<br/>体<br/>美<br/>情<br/>况<br/>︶</strong><br/>
							</td>
							<td colspan="19">
								
							</td>
						</tr>
					</table>
					<br/><br/>
					<table  width="100%" class="tbstyle">
						<tr>
							<Td width="7%"></Td>
							<Td>
								<p style="height:180px"/>
								<div align="right">
									申请人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>			
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								
							</Td>
						</tr>
						<tr>
							<Td align="center"><strong>年&nbsp;&nbsp;级<br/>(专业)<br/>推&nbsp;&nbsp;荐<br/>意&nbsp;&nbsp;见</strong></Td>
							<Td>
								<p style="height:180px"/>
								<div align="right">
									推荐人：
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									行政职务：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>			
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								
							</Td>
						</tr>
						<tr>
							<Td align="center"><strong><br/>院<br/><br/>（系）<br/><br/>意<br/><br/>见<br/></strong></Td>
							<Td>
								<p style="height:180px"/>
								<div align="right">
									    （公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>			
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								
							</Td>
						</tr>
						<tr>
							<Td align="center"><strong><br/>学<br/><br/>校<br/><br/>意<br/><br/>见<br/></strong></Td>
							<Td>
								<p style="height:180px">
									<br/><br/>
									&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;经评审，并在<u>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									范围内公示<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</u>天，无异议，现报请<br/><br/><br/>
									同意该同学获得<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</u>学年度国家奖学金。
								</p>
								<div align="right">
									    （公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>			
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								
							</Td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
