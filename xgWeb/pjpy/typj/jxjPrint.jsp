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
						${xxmc }奖学金申请表
					</h2>
					<br/>
					<logic:notPresent name="rs">
						（20&nbsp;&nbsp;&nbsp;&nbsp;---20&nbsp;&nbsp;&nbsp;&nbsp;学年）
					</logic:notPresent>
					<logic:present name="rs">
						（${rs.xn } &nbsp;&nbsp;学年）	
					</logic:present>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="7%"></td>
							<td width="6%"></td>
							<td width="10%"></td>
							<td width="7%"></td>
							<td width="6%"></td>
							<td width="10%"></td>
							<td width="7%"></td>
							<td width="8%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="6%"></td>
							<td width="10%"></td>
						</tr>
						<tr height="50px">
							<td align="center" colspan="2">姓&nbsp;&nbsp;&nbsp;&nbsp;名</td>
							<td align="center" colspan="2">${rs.xm }</td>
							<td align="center" colspan="2">性&nbsp;&nbsp;&nbsp;&nbsp;别</td>
							<td align="center" colspan="2">${rs.xb }</td>
							<td align="center" colspan="2">学&nbsp;&nbsp;&nbsp;&nbsp;号</td>
							<td align="center" colspan="2">${rs.xh }</td>
						</tr>
						<tr height="50px">
							<td align="center" colspan="2">学&nbsp;&nbsp;&nbsp;&nbsp;院</td>
							<td align="center" colspan="2">${rs.xymc }</td>
							<td align="center" colspan="2">专&nbsp;&nbsp;&nbsp;&nbsp;业</td>
							<td align="center" colspan="2">${rs.zymc }</td>
							<td align="center" colspan="2">班&nbsp;&nbsp;&nbsp;&nbsp;级</td>
							<td align="center" colspan="2">${rs.bjmc }</td>
						</tr>
						<tr height="50px">
							<td align="center" colspan="2">班级人数</td>
							<td align="center" colspan="2">${rs.bjrs }</td>
							<td align="center" colspan="2">学绩分成绩</td>
							<td align="center" colspan="2">${rs.xjfcj }</td>
							<td align="center" colspan="2">学绩分名次</td>
							<td align="center" colspan="2">${rs.xjfpm }</td>
						</tr>
						<tr height="50px">
							<td align="center" colspan="3">学生品行评价等级</td>
							<td align="center" colspan="5">${rs.pxdj }</td>
							<td align="center" colspan="2">《学生体质健康标准》等级</td>
							<td align="center" colspan="2">${rs.jkdj }</td>
						</tr>
						<tr height="60px">
							<td align="center">申请<br/>奖项</td>
							<td align="center" colspan="11">${rs.jxjmc }</td>
						</tr>
						<tr height="180px">
							<td align="center">申请<br/>理由</td>
							<td colspan="11">
								<p style="height:160px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
									<div align="right">
										申请人签名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
									
								</p>
							</td>
						</tr>
						<tr height="60px">
							<td align="center">评定<br/>奖项</td>
							<td align="center" colspan="11"></td>
						</tr>
						<tr>
							<td align="center">班级<br/>评议<br/>意见</td>
							<td align="center" colspan="5">
								<p style="height:110px">
									<div align="right">
									
									主任签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>		  
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
								</p>
							</td>
							<td align="center">学生<br/>社区<br/>服务<br/>管理<br/>中心<br/>意见</td>
							<td align="center" colspan="5">
								<p style="height:110px">
									<div align="right">
									
									盖&nbsp;&nbsp;&nbsp;章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>		  
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
								</p>
							</td>
						</tr>
						<tr>
							<td align="center">学<br/>院<br/>评<br/>审<br/>意<br/>见</td>
							<td align="center" colspan="5">
								<p style="height:110px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyyj }
									<div align="right">
									
									盖&nbsp;&nbsp;&nbsp;章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>		  
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
								</p>
							</td>
							<td align="center">学<br/>校<br/>审<br/>核<br/>意<br/>见</td>
							<td align="center" colspan="5">
								<p style="height:110px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxyj }
									<div align="right">
									
									盖&nbsp;&nbsp;&nbsp;章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>		  
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
								</p>
							</td>
						</tr>
					</table>
					<div align="left">
						注：本表请用 A4纸打印，黑色墨水钢笔或水笔填写
					</div>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
