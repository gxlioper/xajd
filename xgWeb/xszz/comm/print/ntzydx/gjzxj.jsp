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
						普通本科高校、高等职业学校国家助学金申请表
					</h3>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr height="40px">
							<td rowspan="6" align="center" width="7%">
								<br />
								<br />
								本人
								<br />
								情况
								<br />
								<br />
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
							<td align="center" width="15%" rowspan="4">
								<img
									src="<%=request.getContextPath()%>/stuPic.jsp?xh=<bean:write name="rs" property="xh" />"
									border="0" align="absmiddle" style="width:140;height:160" />
							</td>
						</tr>
						<tr  height="40px">							
							<td align="center">
								民族
							</td>
							<td align="center">${rs.mzmc }</td>
							<td align="center">
								政治面貌
							</td>
							<td align="center">${rs.zzmmmc }</td>
							<td align="center">
								入学时间
							</td>
							<td align="center">${rs.rxrq }</td>
						</tr>
						<tr  height="40px">
							<td align="center">
								身份证号
							</td>
							<td align="center" colspan="3">${rs.sfzh }</td>
							<td align="center">
								联系电话
							</td>
							<td align="center">${rs.lxdh }</td>
						</tr>
						<tr  height="40px">
							<td align="center" colspan="6">
								海南大学
								<logic:empty name="rs" property="xymc">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<bean:message key="lable.xb" />
								</logic:empty>
								${rs.xymc}
								<logic:empty name="rs" property="zymc">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								系 
								</logic:empty>
								${rs.zymc}
								<logic:empty name="rs" property="bjmc">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								班 
								</logic:empty>
								${rs.bjmc}
							</td>
						</tr>
						<tr  height="40px">
							<td align="center">
								学号
							</td>
							<td align="center" colspan="3">${rs.xh}</td>
							<td align="center">
								银行卡号
							</td>
							<td align="center" colspan="2">${rs.yhkh }</td>
						</tr>
						<tr  height="40px">
							<td align="center">
								曾获何种奖励
							</td>
							<td align="center" colspan="6">${rs.hjqk }</td>							
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
							<td align="center" colspan="3">
									A、<logic:equal value="城镇" property="jthk" name="rs">
										√
									</logic:equal>城镇
									&nbsp;&nbsp;
									B、<logic:equal value="农村" property="jthk" name="rs">
										√
									</logic:equal>
									农村
							</td>
							<td align="center">
								家庭人口总数
							</td>
							<td align="center" colspan="2">
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
							<td align="center">
								人均月收入
							</td>
							<td align="center">
								${rs.jtrjysr}
							</td>
							<td align="center">
								收入来源
							</td>
							<td align="center" colspan="2"">
								${rs.srly }
							</td>
						</tr>
						<tr  height="40px">
							<td align="center">
								家庭住址
							</td>
							<td align="center" colspan="3">
								${rs.jtdz }
							</td>
							<td align="center">
								邮政编码
							</td>
							<td align="center" colspan="2">
								${rs.jtyb }
							</td>
						</tr><!-- 家庭成员情况 -->
						<tr style="height:22px">
							<td width="5%" rowspan="${cyNum+1 }">
								<div align="center">
									家<br>
									庭<br>
									成<br>
									员<br>
									情<br>
									况
								</div>
							</td>
							<td width="10%">
								<div align="center">
									姓名
								</div>
							</td>
							<td width="10%">
								<div align="center">
									年龄
								</div>
							</td>
							<td width="10%">
								<div align="center">
									与本人关系
								</div>
							</td>
							<td width="10%" colspan="4">
								<div align="center">
									工作或学习单位
								</div>
							</td>
							</tr>
							<logic:iterate name="cyList" id="cy">
							</tr>
								<td width="10%">
									<div align="center">
										${cy.cyxm }&nbsp;&nbsp;
									</div>
								</td>
								<td width="10%">
									<div align="center">
										${cy.cynl }&nbsp;&nbsp;
									</div>
								</td>
								<td width="10%">
									<div align="center">
										${cy.mc }&nbsp;&nbsp;
									</div>
								</td>
								<td width="10%" colspan="4">
									<div align="center">
										${cy.cygzdw }&nbsp;&nbsp;
									</div>
								</td>
							</tr>
						</logic:iterate>
						<tr>
							<td colspan="8">
								<p style="height:200px">
									&nbsp;&nbsp;&nbsp;&nbsp;申请理由：
									<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
								</p>
								<div align="right">
									申请人签名：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								<p style="height:200px">
									&nbsp;&nbsp;&nbsp;&nbsp;学校意见：
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
