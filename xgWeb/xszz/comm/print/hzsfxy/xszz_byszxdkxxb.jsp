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
		<br/><br/><br/><br/>
		<table width="90%" border="0" id="printstyle" align="center">
			<tr height="50px">
				<td align="center">
					<b>
					<span style='font-size:16.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						毕业生助学贷款信息采集表<br/><br/>
					</span>
					</b>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="printstyle">
						<tr>
							<td width="12%"></td>
							<td width="4"></td>
							<td width="2"></td>
							<td width="6"></td>
							<td width="3"></td>
							<td width="12"></td>
							<td width="3"></td>
							<td width=""></td>
							<td width="8"></td>
							<td width="6"></td>
							<td width="6"></td>
							<td width="3"></td>
							<td width="2"></td>
							<td width="10"></td>
							<td width="8"></td>
						</tr>
						<tr height="40px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								班级QQ群号
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								个人Email
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.dzyx }
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								姓名
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xm }
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								所在院系
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xymc }
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								专业
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.zymc }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								院校联系方式
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="left" rowspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								本人身份证号码
								</span>
							</td>
							<td align="left" rowspan="2" colspan="5">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.sfzh }
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								家庭住址
								</span>
							</td>
							<td align="center" colspan="7">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtdz }
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								邮政编码
								</span>
							</td>
							<td align="center" colspan="7">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtyb }
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								父亲姓名
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<logic:iterate name="cyList" id="cy">
										<logic:equal value="父亲" name="cy" property="mc">
											${cy.cyxm }
										</logic:equal>
									</logic:iterate>
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								工作单位
								</span>
							</td>
							<td align="center" colspan="6">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<logic:iterate name="cyList" id="cy">
										<logic:equal value="父亲" name="cy" property="mc">
											${cy.cygzdw }
										</logic:equal>
									</logic:iterate>
								</span>
							</td>
							<td align="center">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								联系电话
								</span>
							</td>
							<td align="center">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<logic:iterate name="cyList" id="cy">
										<logic:equal value="父亲" name="cy" property="mc">
											${cy.cydh }
										</logic:equal>
									</logic:iterate>
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								母亲姓名
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<logic:iterate name="cyList" id="cy">
										<logic:equal value="母亲" name="cy" property="mc">
											${cy.cyxm }
										</logic:equal>
									</logic:iterate>
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								工作单位
								</span>
							</td>
							<td align="center" colspan="6">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<logic:iterate name="cyList" id="cy">
										<logic:equal value="母亲" name="cy" property="mc">
											${cy.cygzdw }
										</logic:equal>
									</logic:iterate>
								</span>
							</td>
							<td align="center">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								联系电话
								</span>
							</td>
							<td align="center">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<logic:iterate name="cyList" id="cy">
										<logic:equal value="母亲" name="cy" property="mc">
											${cy.cydh }
										</logic:equal>
									</logic:iterate>
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="center" rowspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								贷款总额
								</span>
							</td>
							<td align="center" rowspan="2" colspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									学费贷款数
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									生活费贷款数
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									住宿费贷款数
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									合同号
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									经办银行
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									分支机构名称
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									批准日期
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									总金额
								</span>
							</td>
							<td align="center">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									合同经办员
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									合同1
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									合同2
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									还款开始日期
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									还款截止日期
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									备注
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									合同1
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									合同2
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center">
								<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
						</tr>
					</table>
					<div align="left">
						<br/><br/>
						<span style='font-size:10.5pt;font-family:仿宋_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						备注：1.此表供<bean:message key="lable.xb" />上报数据时及贷后管理使用，请各<bean:message key="lable.xb" />务必组织相关同学认真如实填写。<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  2．“院校联系方式”，请填<bean:message key="lable.xb" />资助辅导员办公电话。<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  3.“父、母亲联系电话”请分别采集不同号码（其一为家庭电话，一为村委或居委会电话）。
						</span>
					</div>
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
