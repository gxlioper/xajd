<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
<html:html>
<body>
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;};
			.PageNext{
			page-break-after: always;
		}
		</style>
		<!-- end -->
	<html:form action="/typj" method="post">
		<table width="85%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
				<br/><br/><br/><br/><br/><br/>
					<b>
					<span style='font-size:16.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						（${rs.xn }学年）国家励志奖学金申请审批表
					</span>
					</b>
					<br/><br/><br/>
					<div align="left">
						<span style='font-size:11.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<b>学校：</b>${rs.xxmc }&nbsp;&nbsp;<b>院系：</b>${rs.xymc }&nbsp;&nbsp;<b>专业：</b>${rs.zymc }&nbsp;&nbsp;<b>班级：</b>${rs.bjmc }
						</span>
					</div>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="printstyle">
						<tr>
							<td width="7%"></td>
							<td width="10%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
						</tr>
						<tr height="45px">
							<td align="center" rowspan="4">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>基<br/>本<br/>情<br/>况</b>
								</span>
							</td>
							<td align="center">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								姓名
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xm }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								性别
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xb }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								出生年月
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.csrq }
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								学号
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xh }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								民族
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.mzmc }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								入学时间
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.rxrq }
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								政治<br/>面貌
								</span>
							</td>
							<td align="center" colspan="8">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.zzmmmc }
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								联系电话
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.sjhm }
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="center"><span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>身份<br/>证号</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh0 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh1 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh2 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh3 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh4 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh5 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh6 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh7 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh8 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh9 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh10 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh11 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh12 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh13 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh14 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh15 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh16 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh17 }</span></td>
						</tr>
						<tr height="55px">
							<td align="center" rowspan="2">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>学<br/>习<br/>情<br/>况</b>
								</span>
							</td>
							<td align="left" colspan="8">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								成绩排名：<u>&nbsp;<logic:notEmpty name="rs" property="xnzypm">${rs.xnzypm}/${rs.zyzrs}</logic:notEmpty ><logic:empty name="rs" property="xnzypm">&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;</logic:empty>&nbsp;</u>（名次/总人数）
								</span>
							</td>
							<td align="left" colspan="11">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								实行综合考评排名：
								<logic:notEmpty name="rs" property="zhzfpm">是<img src="/xgxt/pictures/xszz/gou.jpg"></img> ；否□ </logic:notEmpty >
        						<logic:empty name="rs" property="zhzfpm">是□ ；否<img src="/xgxt/pictures/xszz/gou.jpg"></img></logic:empty>
								</span>
							</td>
						</tr>
						<tr height="55px">
							<td align="left" colspan="8">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								必修课<u>&nbsp;&nbsp;&nbsp;${rs.bxkms}&nbsp;&nbsp;</u>门，其中及格以上<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.jgkms }</u>门
								</span>
							</td>
							<td align="left" colspan="11">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								如是，排名：<u>&nbsp;<logic:notEmpty name="rs" property="zhzfpm">${rs.zhzfpm}/${rs.zrs}</logic:notEmpty ><logic:empty name="rs" property="zhzfpm">&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;</logic:empty>&nbsp;</u>（名次/总人数）
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="center" rowspan="5">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>大学期<br/>间主要<br/>获奖<br/>情况</b>
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								日期
								</span>
							</td>
							<td align="center" colspan="7">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								奖项名称
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								颁奖单位
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;${rs.hjrq1 }
								</span>
							</td>
							<td align="center" colspan="7">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;${rs.hjmc1 }
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;${rs.bjdw1 }
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;${rs.hjrq2 }
								</span>
							</td>
							<td align="center" colspan="7">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;${rs.hjmc2 }
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;${rs.bjdw2 }
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;${rs.hjrq3 }
								</span>
							</td>
							<td align="center" colspan="7">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;${rs.hjmc3 }
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;${rs.bjdw3 }
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;${rs.hjrq4 }
								</span>
							</td>
							<td align="center" colspan="7">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;${rs.hjmc4 }
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;${rs.bjdw4 }
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="center" rowspan="4">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>家<br/>庭<br/>经<br/>济<br/>情<br/>况</b>
								</span>
							</td>
							<td align="center">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								家庭户口
								</span>
							</td>
							<td align="center" colspan="10">
								<logic:present name="rs" property="jthk">
									<logic:equal value="城镇" property="jthk" name="rs">
										<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										城镇
										</span>
									</logic:equal>
									<logic:equal value="农村" property="jthk" name="rs">
										<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										农村
										</span>
									</logic:equal>
								</logic:present>
								<logic:notPresent name="rs" property="jthk">
									<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									A、城镇 B、农村
									</span>
								</logic:notPresent>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								收入来源
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.srly }
								</span>
							</td>
						</tr>
						<tr height="55px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								家庭<br/>月总<br/>收入
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtyzsr }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								家庭人口<br/>总数
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtrs }
								</span>
							</td>
						</tr>
						<tr height="50px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								家庭住址
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtdz }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								邮政编码
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtyb }
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								认定情况
								</span>
							</td>
							<td align="center" colspan="18">
								<logic:present name="rs" property="knjb">
									<logic:equal value="一般困难" property="knjb" name="rs">
										<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										家庭经济一般困难
										</span>
									</logic:equal>
									<logic:equal value="特殊困难" property="knjb" name="rs">
										<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										家庭经济特别困难
										</span>
									</logic:equal>
									<logic:equal value="" property="knjb" name="rs">
										<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										A、家庭经济特别困难 B、家庭经济一般困难
										</span>
									</logic:equal>
								</logic:present>
								<logic:notPresent name="rs" property="knjb">
									<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									A、家庭经济特别困难 B、家庭经济一般困难
									</span>
								</logic:notPresent>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!--分页-->
		<div class="PageNext"><br/></div>
		<br/>
		<table width="85%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
				<br/><br/><br/><br/><br/>
					<table width="100%" class="printstyle">
						<tr>
							<td width="8%"></td>
							<td width="10%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
						</tr>
						<tr height="240px" align="center">
							<td>
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>申请<br/>理由</b>
								</span>
								
								<span style='font-size:9pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<br/>(200 字)
								</span>
							</td>
							<td colspan="19">
								<p style="height:230px" align="left">
									<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
									</span>
								</p>
								<div align="right">
									<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									申请人签名（手签）：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>
									年&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>
									</span>
								</div>
							</td>
						</tr>
						<tr height="160px" align="center">
							<td>
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>院<br/>（系）<br/>意<br/>见</b>
								</span>
							</td>
							<td colspan="19">
								<p style="height:150px" align="left">
									<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
									</span>
								</p>
								<div align="right">
									<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									院系主管学生工作领导签名：
									&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>（院系公章）
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>
									年&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>
									</span>
								</div>
							</td>
						</tr>
						<tr height="200px" align="center">
							<td>
								<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>学<br/>校<br/>意<br/>见</b>
								</span>
							</td>
							<td colspan="19">
								<p style="height:190px" align="left">
									<br/><br/><br/><br/>
									<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;经评审，并在校内公示<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>个工作日，无异议，现报请批准该同学获得国家励志奖学金。
									</span>
								</p>
								<div align="right">
									<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									（学校公章）
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>
									年&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>
									</span>
								</div>
							</td>
						</tr>
					</table>
					<div align="right">
						<br/>
						<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						制表：浙江省学生资助管理中心&nbsp;2010年版
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
