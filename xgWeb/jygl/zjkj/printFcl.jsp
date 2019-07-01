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
						${xxmc }${rs.nj }<logic:notPresent name="rs">${nd }</logic:notPresent>届各二级<bean:message key="lable.xb" />毕业生风采录
					</h2>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table class="tbstyle" width="100%">
						<tr>
							<td width="7%"></td>
							<td width="8%"></td>
							<td width="17%"></td>
							<td width="17%"></td>
							<td width="17%"></td>
							<td width="17%"></td>
							<td width="17%"></td>
						</tr>
						<tr style="height:35px">
							<td colspan="2" align="center">姓名</td>
							<td align="center">${rs.xm }</td>
							<td align="center"><bean:message key="lable.xb" />名称</td>
							<td align="center">${rs.xymc }</td>
							<td align="center">风采类型</td>
							<td align="center" >
								${rs.fclxmc }
							</td>
						</tr>
						
						<tr style="height:35px">
							<td colspan="2" align="center">性别</td>
							<td align="center">${rs.xb }</td>
							<td align="center">专业名称</td>
							<td align="center">${rs.zymc }</td>
							<td align="center">学历</td>
							<td align="center">${rs.xl }</td>
						</tr>
						
						<tr style="height:35px">
							<td colspan="2" align="center">籍贯</td>
							<td align="center">${rs.sydq }${rs.syds }${rs.sydx }</td>
							<td align="center">班级</td>
							<td align="center">${rs.bjmc }</td>
							<td align="center">学号</td>
							<td align="center">${rs.xh }</td>
						</tr>
						
						<tr style="height:35px">
							<td colspan="2" align="center">邮箱</td>
							<td colspan="3" align="center">${rs.dzyx }</td>
							<td align="center">手机号码</td>
							<td align="center">${rs.sjhm }</td>
						</tr>
						
						<tr style="height:35px">
							<td colspan="2" align="center">家庭地址</td>
							<td colspan="3" align="center">${rs.lxdz }</td>
							<td align="center">邮政编码</td>
							<td align="center">${rs.yzbh }</td>
						</tr>
						
						<tr style="height:35px">
							<td rowspan="4" align="center">社<br/>会<br/>工<br/>作</td>
							<td align="center">1</td>
							<td colspan="5">
								${rs.shgz1 }
							</td>
						</tr>
						<tr style="height:35px">
							<td align="center">2</td>
							<td colspan="5">
								${rs.shgz2 }
							</td>
						</tr>
						<tr style="height:35px">
							<td align="center">3</td>
							<td colspan="5">
								${rs.shgz3 }
							</td>
						</tr>
						<tr style="height:35px">
							<td align="center">4</td>
							<td colspan="5">
								${rs.shgz4 }
							</td>
						</tr>
						
						<tr style="height:35px">
							<td rowspan="4" align="center">获<br/>奖<br/>情<br/>况</td>
							<td align="center">1</td>
							<td colspan="5">
								${rs.hjqk1 }
							</td>
						</tr>
						<tr style="height:35px">
							<td align="center">2</td>
							<td colspan="5">
								${rs.hjqk2 }
							</td>
						</tr>
						<tr style="height:35px">
							<td align="center">3</td>
							<td colspan="5">
								${rs.hjqk3 }
							</td>
						</tr>
						<tr style="height:35px">
							<td align="center">4</td>
							<td colspan="5">
								${rs.hjqk4 }
							</td>
						</tr>
						
						<tr style="height:35px">
							<td rowspan="4" align="center">风<br/>采<br/>事<br/>迹</td>
							<td align="center">1</td>
							<td colspan="5">
								${rs.fcsj1 }
							</td>
						</tr>
						<tr style="height:35px">
							<td align="center">2</td>
							<td colspan="5">
								${rs.fcsj2 }
							</td>
						</tr>
						<tr style="height:35px">
							<td align="center">3</td>
							<td colspan="5">
								${rs.fcsj3 }
							</td>
						</tr>
						<tr style="height:35px">
							<td align="center">4</td>
							<td colspan="5">
								${rs.fcsj4 }
							</td>
						</tr>
						
						<tr>
							<td colspan="2" align="center">
								风采感言
							</td>
							<td colspan="5">
								<p style="height:80px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.fcgy }
								</p>
							</td>
						</tr>
						
						<tr>
							<td colspan="2" align="center">
								备注
							</td>
							<td colspan="5">
								<p style="height:80px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.bz }
								</p>
							</td>
						</tr>
					 </table>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center" class='noPrin'>
		<button class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			页面设置
		</button>
		<button class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			打印预览
		</button>
		<button class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			直接打印
		</button>
	</div>
</body>
</html:html>
