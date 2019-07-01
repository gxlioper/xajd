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
					<h3>
						普通本科高校、高等职业学校国家励志奖学金申请表V1.3
					</h3>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr height="40px">
							<td rowspan="5" align="center" width="7%">
								本
								<br />
								人
								<br />
								情
								<br />
								况
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
								<img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" style="width:145;height:175" />
							</td>
						</tr>
						<tr  height="40px">
							<td align="center">
								党团员
							</td>
							<td align="center">${rs.zzmmmc }</td>
							<td align="center">
								民族
							</td>
							<td align="center">${rs.mzmc }</td>
							<td align="center">
								入学时间
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
							<td align="center" colspan="4">
								${rs.xymc }&nbsp;&nbsp;院系&nbsp;&nbsp;${rs.nj }&nbsp;&nbsp;年级&nbsp;&nbsp;&nbsp;&nbsp;<br/>
								${rs.zymc }&nbsp;&nbsp;专业&nbsp;&nbsp;${rs.bjmc }&nbsp;&nbsp;班
							</td>
							<td align="center">
								学号
							</td>
							<td align="center">${rs.xh }</td>
						</tr>
						<tr  height="100px">
							<td align="center">
								获奖情况
							</td>
							<td colspan="6">
								&nbsp;&nbsp;&nbsp;&nbsp;${rs.hjqk }
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
								<logic:present name="rs" property="jthk">
									<logic:equal value="城镇" property="jthk" name="rs">
										城镇
									</logic:equal>
									<logic:equal value="农村" property="jthk" name="rs">
										农村
									</logic:equal>
								</logic:present>
								<logic:notPresent name="rs" property="jthk">
									A、城镇 B、农村
								</logic:notPresent>
							</td>
							<td align="center">
								家庭人口总数
							</td>
							<td align="center">
								${rs.jtrs }
							</td>
						</tr>
						<tr  height="40px">
							<td align="center">
								家庭月总收入
							</td>
							<td align="center">
								${rs.jtyzsr }
							</td>
							<td align="center" colspan="2">
								人均月收入
							</td>
							<td align="center">
								${rs.jtrjysr }
							</td>
							<td align="center">
								收入来源
							</td>
							<td align="center">
								${rs.srly }
							</td>
						</tr>
						<tr  height="40px">
							<td align="center">
								家庭住址
							</td>
							<td align="center" colspan="4">
								${rs.jtdz }
							</td>
							<td align="center">
								邮政编码
							</td>
							<td align="center">
								${rs.jtyb }
							</td>
						</tr>
						<tr  height="40px">
							<td align="center" rowspan="2">
								综
								<br />
								合
								<br />
								测
								<br />
								评
							</td>
							<td align="center">
								本专业
								<br />
								总人数
							</td>
							<td align="center">${rs.zyzrs }</td>
							<td align="center" colspan="2">
								总积分
								<br />
								名次
							</td>
							<td align="center">第&nbsp;&nbsp;${rs.zjfpm }&nbsp;&nbsp;名</td>
							<td align="center">
								总积分
								<br />
								排名
							</td>
							<td align="center">${rs.zjfpmBl }%</td>
						</tr>
						<tr  height="40px">
							<td align="center">
								德育排名
							</td>
							<td align="center">${rs.dypmBl }%</td>
							<td align="center" colspan="2">
								智育排名
							</td>
							<td align="center">${rs.zypmBl }%</td>
							<td align="center">
								文体排名
							</td>
							<td align="center">${rs.wtpmBl }%</td>
						</tr>
						<tr>
							<td colspan="8">
								<p style="height:200px">
									申请理由<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
								</p>
								<div>
									工行卡号：${rs.yhkh }
								</div>
								<div align="right">
									申请人签名：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<p style="height:200px">
									&nbsp;&nbsp;&nbsp;&nbsp;院（系）学生资助工作组审核意见：
									<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
								</p>
								<div align="right">
									负责人（签章）
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
							<td colspan="4">
								<p style="height:200px">
									&nbsp;&nbsp;&nbsp;&nbsp;校学生资助管理中心审核意见：
									<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxyj }
								</p>
								<div align="right">
									（公章）
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
