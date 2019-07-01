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
		<table width="95%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
					<h2>
						${xxmc }${rs.xmmc }学生资助申请表
					</h2>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="7%"></td>
							<td width="7%"></td>
							<td width="6%"></td>
							<td width="5%"></td>
							<td width="5%"></td>
							<td width="5%"></td>
							<td width="5%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
						</tr>
						<tr height="40px">
							<td align="center" rowspan="4">学<br/>生<br/>本<br/>人<br/>基<br/>本<br/>情<br/>况</td>
							<td align="center" colspan="2">姓&nbsp;&nbsp;名</td>
							<td align="center" colspan="2">${rs.xm }</td>
							<td align="center" colspan="2">性&nbsp;&nbsp;别</td>
							<td align="center">${rs.xb }</td>
							<td align="center">出生年月</td>
							<td align="center" colspan="2">${rs.csrq }</td>
							<td align="center">民族</td>
							<td align="center">${rs.mzmc }</td>
							
							
						</tr>
						<tr  height="40px">
							<td align="center" colspan="2">身份证号码</td>
							<td align="center" colspan="4">${rs.sfzh }</td>
							<td align="center">政治面貌</td>
							<td align="center" colspan="2">${rs.zzmmmc }</td>
							<td align="center" colspan="2">家庭人均年收入</td>
							<td align="center" >${rs.jtrjsr }&nbsp;元</td>
						</tr>
						<tr  height="40px">
							<td align="center" colspan="2">院系</td>
							<td align="center" colspan="5">${rs.xymc }</td>
							<td align="center" colspan="2">专业</td>
							<td align="center" colspan="3">${rs.zymc }</td>
						</tr>
						<tr  height="40px">
							<td align="center" colspan="2">班级</td>
							<td align="center" colspan="3">${rs.bjmc }</td>
							<td align="center" colspan="2">年级</td>
							<td align="center" >${rs.nj }</td>
							<td align="center" colspan="2">联系电话</td>
							<td align="center" colspan="2">${rs.lxdh }</td>
						</tr>
						<tr>
							<td align="center">申<br/><br/>请<br/><br/>理<br/><br/>由</td>
							<td colspan="12">
								<p style="height:220px">${rs.sqsm }</p>
								<div align="right">
									签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td align="center">学<br/>院<br/>审<br/>核<br/>意<br/>见</td>
							<td colspan="12">
								<p style="height:220px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
								</p>
								<div align="right">
									盖章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td align="center">学<br/>生<br/>处<br/>审<br/>核<br/>意<br/>见</td>
							<td colspan="12">
								<p style="height:220px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxshyj }
								</p>
								<div align="right">
									盖章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
