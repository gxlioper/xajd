<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=gb2312">
		<title>关于做好2009级家庭经济困难学生“绿色通道”工作的通知</title>
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
	</head>
	<body bgcolor="#FFFFFF" lang=ZH-CN>
		<div class=Section1 style='layout-grid: 15.6pt'>
			<p align=center>
				<b><span style='font-size: 18.0pt; font-family: 仿宋_GB2312;'>海南大学新生入学“绿色通道”申请表
				</span> </b>
			</p>
			<div align=center>
				<table class="tbstyle" width="90%">
					<tr>
						<td colspan=15 align="center">
							<p>
								<b>个人基本信息</b>
							</p>
						</td>
					</tr>
					<tr align="center">
						<td width=9%>
							姓名
						</td>
						<td width=9%>
							${rs.xm }
						</td>
						<td width=10% colspan=2>
							性别
						</td>
						<td width=10%>
							${rs.xb }
						</td>
						<td width=10%>
							民族
						</td>
						<td width=10% colspan=4>
							${rs.mzmc }
						</td>
						<td width=22% colspan=3>
							入学报到时间
						</td>
						<td width=20% colspan=2>
							${rs.rxrq }
						</td>
					</tr>
					<tr align="center">
						<td>
							<bean:message key="lable.xb" />
						</td>
						<td colspan=3>
							${rs.xymc }
						</td>
						<td>
							专业
						</td>
						<td colspan=5>
							${rs.zymc }
						</td>
						<td colspan=3>
							有无家庭经济贫困证明材料
						</td>
						<td colspan=2>
							${rs.ywzm }
						</td>
					</tr>
					<tr align=center>
						<td>
							学制
						</td>
						<td colspan=3>
							${rs.xz }&nbsp;&nbsp; 年 &nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td colspan=3>
							身份证号码
						</td>
						<td colspan=8>
							${rs.sfzh }
						</td>
					</tr>
					<tr align="center">
						<td colspan=15>
							<p>
								<b>家庭主要成员情况</b>
							</p>
						</td>
					</tr>

					<tr>
						<td>
							<p align=center>
								称 谓
							</p>
						</td>
						<td colspan=2>
							<p align=center>
								姓名
							</p>
						</td>
						<td colspan=8>
							<p align=center>
								家庭地址或工作单位地址
							</p>
						</td>
						<td width="8%">
							<p align=center>
								邮编
							</p>
						</td>
						<td colspan=2 width="14%">
							<p align=center>
								联系电话
							</p>
						</td>
						<td>
							<p align=center>
								月收入
							</p>
						</td>
					</tr>

					<logic:iterate name="cyList" id="cy">
						<tr>
							<td>
								<div align="center">
									${cy.mc }&nbsp;&nbsp;
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									${cy.cyxm }&nbsp;&nbsp;
								</div>
							</td>
							<td colspan=8>
								<div align="center">
									${cy.cygzdw }&nbsp;&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									${cy.cyyb }&nbsp;&nbsp;
								</div>
							</td>
							<td colspan=2>
								<div align="center">
									${cy.cydh }&nbsp;&nbsp;
								</div>
							</td>
							<td>
								<p align=right>
									${cy.cyysr }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;元
								</p>
							</td>
						</tr>
					</logic:iterate>

					<tr>
						<td>
							&nbsp;
						</td>
						<td colspan=2>
							&nbsp;
						</td>
						<td colspan=8>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td colspan=2>
							&nbsp;
						</td>
						<td>
							<p align=right>
								元
							</p>
						</td>
					</tr>

					<tr>
						<td>
							&nbsp;
						</td>
						<td colspan=2>
							&nbsp;
						</td>
						<td colspan=8>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td colspan=2>
							&nbsp;
						</td>
						<td>
							<p align=right>
								元
							</p>
						</td>
					</tr>

					<tr>
						<td>
							<p align=center>
								<b>家庭 <br /> 情况</b>
							</p>
						</td>
						<td colspan=14>
							<p align=left>
								&nbsp;&nbsp;孤儿（
								<logic:equal value="是" name="rs" property="sfge">√</logic:equal>
								） 单亲家庭（
								<logic:equal value="是" name="rs" property="sfdq">√</logic:equal>
								） 城乡低保户（
								<logic:equal value="是" name="rs" property="sfdb">√</logic:equal>
								） 烈士子女（
								<logic:equal value="是" name="rs" property="lszn">√</logic:equal>
								） 优抚家庭子女（
								<logic:equal value="是" name="rs" property="sfyfjt">√</logic:equal>
								） 残疾学生（
								<logic:equal value="是" name="rs" property="sfcj">√</logic:equal>
								） 贫困地区少数民族学生（
								<logic:equal value="是" name="rs" property="sfpkdqssmz">√</logic:equal>
								）
							</p>
						</td>
					</tr>
					<tr>
						<td colspan=15 valign=top>
							<p align=center>
								<b>缓交申请</b>
							</p>
							<p style='line-height: 15.0pt;'>
								&nbsp;&nbsp;&nbsp;&nbsp;由于
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>原因，本人申请暂缓缴交
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</u>等费用， 共计
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>元，本人承诺在
								<u> &nbsp;&nbsp;&nbsp;&nbsp;</u>年
								<u> &nbsp;&nbsp;&nbsp;&nbsp;</u>月
								<u>&nbsp; &nbsp;&nbsp;&nbsp;</u>日前，缴清上述费用，请学校先予以办理相关报到手续并安排住宿。
							</p>
						</td>
					</tr>
					<tr>
						<td>
							<p>
								<b>拟申请资助方式</b>
							</p>
						</td>
						<td colspan=14>
							<p>
								国家助学贷款（ ）勤工助学（ ）奖、助学金（ ）临时困难补助（ ）社会资助（ ）
							</p>
						</td>
					</tr>
					<tr>
						<td>
							<p align=center>
								个人
							</p>
							<p align=center>
								承诺
							</p>
						</td>
						<td colspan=14>
							<p>
								(1)以上所填内容真实，不存在虚假成分。
								<br />
								(2)如本人填写内容与事实不符，大学期间将不再享受任何资助，并承担由此带来的相应责任。
							</p>

							<p align=center>
								签名:
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</u>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<u>&nbsp;&nbsp;&nbsp;&nbsp; </u>年
								<u>&nbsp;&nbsp;&nbsp; </u>月
								<u>&nbsp;&nbsp;&nbsp; </u>日
							</p>
						</td>
					</tr>
					<tr height="200px">
						<td>
							<p align=center>
								学
								<br />
								院
								<br />
								意
								<br />
								见
							</p>
						</td>
						<td colspan=7>
							<p>
								&nbsp;&nbsp;
							</p>
							<p align="right">
								签字（盖章）:___________&nbsp;&nbsp;
								<br />
								_____年_____月_____日&nbsp;&nbsp;
							</p>
						</td>
						<td>
							<p align=center>
								学
								<br />
								校
								<br />
								审
								<br />
								批
								<br />
								意
								<br />
								见
						</td>
						<td colspan=6>
							<p>
								&nbsp;&nbsp;
							</p>
							<p align="right">
								签字:______________&nbsp;&nbsp;
								<br />
								_____年_____月_____日&nbsp;&nbsp;
							</p>
						</td>
					</tr>
				</table>
				<table border="0" width="90%">
					<tr height=0 >
						<td colspan="15" align="left">
								&nbsp;&nbsp;&nbsp;备注：1、 目前尚有生活费：
								<u>&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>元，有困难请及时与学校联系；<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								2、本表必须由申请人本人填写。
						</td>
					</tr>
				</table>
			</div>

		</div>

		<div align="center" class='noPrin'>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				页面设置
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				打印预览
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);">
				直接打印
			</button>
		</div>
	</body>
</html>
