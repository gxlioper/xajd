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
		<table width="95%" border="0" id="theTable" align="center" >
			<tr>
				<td align="center">
					<h3>
						广西工学院鹿山学院国家励志奖学金申请表
					</h3>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="95%" class="printstyle">
						<tr height="40px">
							<td rowspan="5" align="center" width="7%">
								本人
								<br />
								情况
							</td>
							<td align="center" width="16%">
								姓名
							</td>
							<td align="center" width="16%">${rs.xm }</td>
							<td align="center" width="10%">
								性别
							</td>
							<td align="center" width="8%">${rs.xb }</td>
							<td align="center" width="14%">
								出生年月
							</td>
							<td align="center" width="14%">${rs.csrq }</td>
							<td rowspan="4" align="center" width="15%">
								<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}"
									border="0" align="absmiddle" style="width:145;height:175" />
							</td>
						</tr>
						<tr  height="40px">
							<td align="center">
								民族
							</td>
							<td align="center">${rs.mzmc }</td>
							<td align="center">
								政治<br/>面貌
							</td>
							<td align="center">${rs.zzmmmc }</td>
							<td align="center">
								入学年月
							</td>
							<td align="center">${rs.rxrq }</td>
						</tr>
						<tr  height="40px">
							<td align="center">
								身份证号码
							</td>
							<td align="center" colspan="3">${rs.sfzh }</td>
							<td align="center">
								联系电话
							</td>
							<td align="center">${rs.lxdh }</td>
						</tr>
						<tr  height="40px">
							<td align="center" colspan="6">
								${rs.xymc }系&nbsp;&nbsp;${rs.bjmc }班
							</td>
							
						</tr>
						<tr  height="40px">
							<td align="center" colspan="2">
								曾获何种奖励
							</td>
							<td colspan="5">
								&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr  height="40px">
							<td rowspan="3" align="center">
								家
								<br />
								庭
								<br />
								经
								<br />
								济
								<br />
								情
								<br />
								况
							</td>
							<td align="center">
								家庭户口
							</td>
							<td align="center" colspan="4">
									A、城镇 B、农村
							</td>
							<td align="center">
								家庭人口总数
							</td>
							<td align="center">
								&nbsp;
							</td>
						</tr>
						<tr  height="40px">
							<td align="center">
								家庭月总收入
							</td>
							<td align="center">
								&nbsp;
							</td>
							<td align="center" colspan="2">
								人均月收入
							</td>
							<td align="center">
								&nbsp;
							</td>
							<td align="center">
								收入来源
							</td>
							<td align="center">
								&nbsp;
							</td>
						</tr>
						<tr  height="40px">
							<td align="center">
								家庭住址
							</td>
							<td align="center" colspan="4">
								&nbsp;
							</td>
							<td align="center">
								邮政编码
							</td>
							<td align="center">
								
							</td>
						</tr>
						<tr >
							<td colspan="8" height="100px" style="vertical-align: top">
								学习成绩（上学年各课程名称及分数）：</td>
						</tr>
						<tr>
							<td colspan="8">
								<p style="height:70px">
									申请理由<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
								<br/>
									（需另附详细个人材料一份）
								<p align="right">
									申请人签名：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日
								</p>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								<p style="height:70px">
									<bean:message key="lable.xb" />意见：${rs.shzt1yj }
								</p>
								
								<div align="right">
									签名：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								<p style="height:70px">
									学生工作部意见:${rs.shzt2yj }
								</p>
								
								<div align="right">
									(公章)
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								<p style="height:70px">
									<bean:message key="lable.xb" />审核意见：<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
								
								<div align="right">
									(公章)
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：学生须认真、如实填写此表，如所填材料出现谎报、瞒报情况，立即取消评选资格。
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
