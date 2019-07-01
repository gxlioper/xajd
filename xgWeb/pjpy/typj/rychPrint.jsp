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
						附件1
					</h3>
				</td>
			</tr>
			<tr>
				<td align="center">
					<h2>
						全区优秀学生、三好学生、<br/>
						优秀学生干部推荐登记表
					</h2>
					<br/><br/>
				</td>
			</tr>
			<tr>
				<td align="left">
					<h3>
						推荐市：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						推荐学校名称：${xxmc }<br/><br/>
						推荐类别（请在括号前打√）：<br/><br/>
						（  ）优秀学生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（  ）三好学生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（  ）优秀学生干部
					</h3>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="8%"></td>
							<td width="5%"></td>
							<td width="15%"></td>
							<td width="10%"></td>
							<td width="15%"></td>
							<td width="10%"></td>
							<td width="15%"></td>
							<td width="22%"></td>
						</tr>
						<tr height="70px">
							<td align="center" colspan="2">姓名</td>
							<td align="center">${rs.xm }</td>
							<td align="center">性别</td>
							<td align="center">${rs.xb }</td>
							<td align="center">民族</td>
							<td align="center">${stuMap.mzmc }</td>
							<td align="center" rowspan="3" width="100px">一<br/>寸<br/>相<br/>片</td>
						</tr>
						<tr height="70px">
							<td align="center" colspan="2">政治面貌</td>
							<td align="center">${stuMap.zzmmmc }</td>
							<td align="center">出生年月</td>
							<td align="center" colspan="3">${stuMap.csrq }</td>
						</tr>
						<tr height="70px">
							<td align="center" colspan="3">所在学校、系、年级及所任职务</td>
							<td align="center" colspan="4">${xxmc}&nbsp;&nbsp;${rs.zymc }&nbsp;&nbsp;${rs.nj }&nbsp;&nbsp;${stuMap.zw }</td>
						</tr>
						<tr height="120px">
							<td align="center" colspan="3">被推荐为全区优秀学生<br/>的候选人的身份证号码</td>
							<td align="center" colspan="5">${stuMap.sfzh }</td>
						</tr>
						<tr height="120px">
							<td align="center" width="70px">在校期间<br/>何时获得<br/>何种奖励</td>
							<td align="center" colspan="7"></td>
						</tr>
						<tr height="260px">
							<td align="center">主<br/><br/><br/>要<br/><br/><br/>事<br/><br/><br/>迹</td>
							<td align="center" colspan="7"></td>
						</tr>
						
					</table>
					<br/>
					<table width="100%" class="tbstyle">
						<tr>
							<td width="8%"></td>
							<td width="7%"></td>
							<td width="260px"></td>
							<td width="7%"></td>
							<td></td>
						</tr>
						<tr height="180px" >
							<td align="center" width="70px">主<br/><br/><br/>要<br/><br/><br/>事<br/><br/><br/>迹</td>
							<td align="center" colspan="4"></td>
						</tr>
						<tr>
							<td align="center">学<br/><br/>校<br/><br/>意<br/><br/>见</td>
							<td align="center" colspan="4">
								<p style="height:200px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxyj }
									<div style="margin-bottom: 0px" align="right">
										盖&nbsp;&nbsp;章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
										年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
								</p>
							</td>
						</tr>
						<tr height="200px" >
							<td align="center" >市<br/>教<br/>育<br/>行<br/>政<br/>部<br/>门<br/>意<br/>见</td>
							<td align="center" colspan="2">
								<p style="height:180px">
									<div style="margin-bottom: 0px" align="right">
										盖&nbsp;&nbsp;章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
										年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
								</p>
							</td>
							<td align="center" width="100px">团<br/>市<br/>委<br/>意<br/>见</td>
							<td align="center">
								<p style="height:180px">
									<div style="margin-bottom: 0px" align="right">
										盖&nbsp;&nbsp;章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
										年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										日
									</div>
								</p>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								${rs.nd }年全区优秀学生、三好学生、优秀学生干部和先进班集体评审领导小组意见
							</td>
							<td colspan="3">
								<p style="height:200px">
									<div style="margin-bottom: 0px" align="right">
										盖&nbsp;&nbsp;章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
										年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										日
									</div>
								</p>
							</td>
						</tr>
						<tr>
							<td align="center">备注</td>
							<td align="left" colspan="4">
								<p style="height:40px">
									1.此表一式两份，其中一份留学生所在的学校保存，另一份作为推荐材料上报自治区教育厅。<br/>
									2.被推荐为全区优秀学生的候选人，除了要填写好本表外，还要用第三人称（或姓名）另外撰写1篇1500字的先进事迹材料（电子版），用于评审、公示。
									
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
