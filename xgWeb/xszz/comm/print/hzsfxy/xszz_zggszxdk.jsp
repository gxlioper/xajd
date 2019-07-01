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
		<br/><br/>
		<table width="95%" border="0" id="printstyle" align="center">
			<tr height="50px">
				<td align="center">
					<b>
					<span style='font-size:18.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						中国工商银行湖州市分行助学贷款申请审批表<br/><br/>
					</span>
					</b>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="printstyle">
						<tr>
							<td width="4%"></td>
							<td width="12%"></td>
							<td width="8%"></td>
							<td width="2%"></td>
							<td width="2%"></td>
							<td width="6%"></td>
							<td width="5%"></td>
							<td width="12%"></td>
							<td width="6%"></td>
							<td width="5%"></td>
							<td width="5%"></td>
							<td width="7%"></td>
							<td width="12%"></td>
							<td width="12%"></td>
						</tr>
						<!-- 申请人情况 -->
						<!-- 第1行 -->
						<tr height="20px">
							<td align="center" rowspan="4">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								申<br/>
								请<br/>
								人<br/>
								情<br/>
								况
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								姓名
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xm }
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								身份证号码
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.sfzh }
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								性&nbsp;&nbsp;&nbsp;&nbsp;别
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xb }
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								出生年月
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.csrq }
								</span>
							</td>
						</tr>
						<!-- 第2行 -->
						<tr height="20px">
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								就读学校
								</span>
							</td>
							<td align="center" colspan="6">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xxmc }
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								学校电话
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								传&nbsp;&nbsp;&nbsp;&nbsp;呼
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
						</tr>
						<!-- 第3行 -->
						<tr height="20px">
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								院系
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xymc }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								专业
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.zymc }
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								学&nbsp;&nbsp;&nbsp;&nbsp;制
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								四年
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								学&nbsp;&nbsp;&nbsp;&nbsp;号
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xh }
								</span>
							</td>
						</tr>
						<!-- 第4行 -->
						<tr height="20px">
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								家庭地址
								</span>
							</td>
							<td align="center" colspan="6">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtdz }
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								家庭电话
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtdh }
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								邮政编码
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtyb }
								</span>
							</td>
						</tr>
						<!-- 家庭成员 -->
						<tr style="height:20px">
							<td align="center" rowspan="${cyNum+1 }">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									家<br>
									庭<br>
									成<br>
									员
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									姓名&nbsp;
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									称谓&nbsp;
								</span>
							</td>
							<td align="center" colspan="8">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									工&nbsp;&nbsp;&nbsp;作&nbsp;&nbsp;&nbsp;单&nbsp;&nbsp;&nbsp;位
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									联系电话
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									月收入
								</span>
							</td>
							</tr>
							<logic:iterate name="cyList" id="cy">
							<tr style="height:20px">
								<td align="center">
									<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										${cy.cyxm }&nbsp;
									</span>
								</td>
								<td align="center" colspan="2">
									<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										${cy.mc }&nbsp;
									</span>
								</td>
								<td align="center" colspan="8">
									<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										${cy.cygzdw }&nbsp;
									</span>
								</td>
								<td align="center">
									<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										${cy.cydh }&nbsp;
									</span>
								</td>
								<td align="center">
									<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										${cy.cyysr }&nbsp;
									</span>
								</td>
							</tr>
						</logic:iterate>
						<!-- 借款情况 -->
						<!-- 第1行 -->
						<tr style="height:20px">
							<td align="center" rowspan="4">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								借<br/>
								款<br/>
								情<br/>
								况
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								贷款金额
								</span>
							</td>
							<td align="left" colspan="12">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								总额(大写)：<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>元，共<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>学年<br/><br/>
								其中学费贷款（小写）：<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>元/学年，生活费贷款（小写）：<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>元/学年
								</span>
							</td>
						</tr>
						<!-- 第2行 -->
						<tr style="height:20px">
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								贷款期限
								</span>
							</td>
							<td align="center" colspan="12">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
						</tr>
						<!-- 第3行 -->
						<tr style="height:20px">
							<td align="left" colspan="7">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								□活期存款账号<br/><br/>
								□信&nbsp;&nbsp;&nbsp;用&nbsp;&nbsp;&nbsp;卡
								</span>
							</td>
							<td align="center" rowspan="2" colspan="6">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								本人保证以上各栏填写内容均真实无误。<br/><br/>
								借款申请人：贷款人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日
								</span>
							</td>
						</tr>
						<!-- 第4行 -->
						<tr style="height:20px">
							<td align="left">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								账号
								</span>
							</td>
							<td align="center" colspan="6">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
						</tr>
						<!-- 加粗 -->
						<tr>
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
						<!-- 见证人情况 -->
						<!-- 第1行 -->
						<tr style="height:20px">
							<td align="center" rowspan="4">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								见<br/>
								证<br/>
								人<br/>
								情<br/>
								况
								</span>
							</td>
							<td align="left" colspan="13">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								本栏由见证人签名盖章，不得代签。
								</span>
							</td>
						</tr>
						<!-- 第2行 -->
						<tr style="height:20px">
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								姓&nbsp;&nbsp;名
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								称&nbsp;&nbsp;谓
								</span>
							</td>
							<td align="center" colspan="7">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								工&nbsp;&nbsp;作&nbsp;&nbsp;单&nbsp;&nbsp;位&nbsp;&nbsp;的&nbsp;&nbsp;部&nbsp;&nbsp;门
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								联系电话
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								本人签名盖章
								</span>
							</td>
						</tr>
						<!-- 第3行 -->
						<tr style="height:20px">
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center" colspan="7">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
						</tr>
						<!-- 第4行 -->
						<tr style="height:20px">
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center" colspan="7">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
						</tr>
						<!-- 加粗 -->
						<tr>
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
						<!-- 介绍人意见 -->
						<!-- 第1行 -->
						<tr style="height:20px">
							<td align="center" rowspan="6">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<br/>介<br/>
								绍<br/>
								人<br/>
								意<br/>
								见<br/>
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								贷款介绍人
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								联系电话
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
						</tr>
						<!-- 第2行 -->
						<tr style="height:20px">
							<td align="center" colspan="4">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								学校学生处联系人
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								联系电话
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
						</tr>
						<!-- 第3行 -->
						<tr style="height:20px">
							<td align="center" colspan="4">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								借款人学校成绩
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="left" rowspan="4" colspan="5">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								借款申请人是我校就读学生，表内所填资料属实，特此证明 。<br/><br/>
								<bean:message key="lable.xb" />（签章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学校（签章）<br/><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日<br/>
								</span>
							</td>
						</tr>
						<!-- 第4行 -->
						<tr style="height:20px">
							<td align="center" rowspan="2">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								借&nbsp;款&nbsp;人<br/>品德表现
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								奖&nbsp;励
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
						</tr>
						<!-- 第5行 -->
						<tr style="height:20px">
							<td align="center" colspan="3">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								处&nbsp;分
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
						</tr>
						<!-- 第6行 -->
						<tr style="height:20px">
							<td align="center" colspan="4">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
						</tr>
						<!-- 加粗 -->
						<tr>
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
						<!-- 银行审查意见 -->
						<!-- 第1行 -->
						<tr style="height:50px">
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								银<br/>
								行<br/>
								审<br/>
								查<br/>
								意<br/>
								见
								</span>
							</td>
							<td align="center" colspan="5">
								<div align="left">
									<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										调查意见：
									</span>
								</div>
								<div align="left">
									<p style="height:25px">
										
									</p>
								</div>
								<div align="right">
									<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									经办人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</span>
								</div>
								<br/>
								<div align="right">
									<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									年&nbsp;&nbsp;
									月&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;
									</span>
								</div>
							</td>
							<td align="center" colspan="5">
								<div align="left">
									<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										审查意见：
									</span>
								</div>
								<div align="left">
									<p style="height:25px">
										
									</p>
								</div>
								<div align="right">
									<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									审查人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</span>
								</div>
								<br/>
								<div align="right">
									<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									年&nbsp;&nbsp;
									月&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;
									</span>
								</div>
							</td>
							<td align="center" colspan="3">
								<div align="left">
									<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										审批意见：
									</span>
								</div>
								<div align="left">
									<p style="height:25px">
										
									</p>
								</div>
								<div align="right">
									<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									审批人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</span>
								</div>
								<br/>
								<div align="right">
									<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									年&nbsp;&nbsp;
									月&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;
									</span>
								</div>
							</td>
						</tr>
						<!-- 加粗 -->
						<tr>
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
						<!-- 核准意见 -->
						<tr style="height:50px">
							<td align="center">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								核<br/><br/>
								准<br/><br/>
								意<br/><br/>
								见
								</span>
							</td>
							<td align="left" colspan="13">
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								贷款总额<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>元，贷款期限<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>年，其中：<br/><br/>
								学费贷款<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>元/学年。每学年发放一次，共发放<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>次；<br/><br/>
								生活费贷款<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>元/学年，每学年发放<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>次，共发放<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>次。<br/>
								</span>
							</td>
						<!-- 加粗 -->
						<tr>
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
					</table>
					<div align="left">
						<br/><br/>
						<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						注：<br/>
						借款人有下列情况之一的，银行有权停止发放借款：<br/>
						1、遵守国家法律、法律和学校规章制度，有刑事犯罪记录或学校严重警告以上处分记录；<br/>
						2、学习不认真，品德差，就读期间累计不合格课程1门（含）以上；<br/>
						3、有不良信用记录。
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
