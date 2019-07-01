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
	<html:form action="/stu_info_add" method="post">
		<table width="100%" border="0" id="theTable" align="center" >
			<tr>
				<td align="center">
					<h2>
						国家励志奖学金申请审批表（&nbsp;${rs.xn }&nbsp;学年）
					</h2>
					<br/>
					<div align="left">
						<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;&nbsp;<b>学校：</b>${rs.xxmc }&nbsp;&nbsp;<b>院系：</b>${rs.xymc }&nbsp;&nbsp;<b>专业：</b>${rs.zymc }&nbsp;&nbsp;<b>年级：</b>${rs.nj }&nbsp;&nbsp;<b>班级：</b>${rs.bjmc }
						</span>
					</div>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="printStyle">
						<tr>
							<td width="8%"></td>
							<td width="14%"></td>
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
						<tr height="41px">
							<td align="center" rowspan="4">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>基<br/>本<br/>情<br/>况</b>
								</span>
							</td>
							<td align="center">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								姓名
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xm }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								性别
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xb }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								出生年月
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.csrq }
								</span>
							</td>
						</tr>
						<tr height="41px">
							<td align="center">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								学号
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xh }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								民族
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.mzmc }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								入学时间
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.rxrq }
								</span>
							</td>
						</tr>
						<tr height="41px">
							<td align="center"><span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>身份证号</span></td>
							<td align="center"><span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh0 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh1 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh2 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh3 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh4 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh5 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh6 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh7 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh8 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh9 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh10 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh11 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh12 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh13 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh14 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh15 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh16 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh17 }</span></td>
						</tr>
						<tr height="41px">
							<td align="center">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								政治面貌
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.zzmmmc }
								</span>
							</td>
							<td align="center" colspan="6">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								联系电话
								</span>
							</td>
							<td align="center" colspan="8">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.lxdh }
								</span>
							</td>
						</tr>
						<tr height="56px">
							<td align="center" rowspan="3">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>家<br/>庭<br/>经<br/>济<br/>情<br/>况</b>
								</span>
							</td>
							<td align="center">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								家庭户口
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
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
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								家庭人口总数
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtrs}
								</span>
							</td>
						</tr>
						<tr height="56px">
							<td align="center">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								家庭月收入
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtyzsr }
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								人均月收入
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtrjysr}
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								收入来源
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.srly }
								</span>
							</td>
						</tr>
						<tr height="56px">
							<td align="center">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								家庭住址
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtdz }
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								邮政编码
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtyb }
								</span>
							</td>
						</tr>
						<tr height="115px">
							<td align="center">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>学<br/>习<br/>等<br/>情<br/>况</b>
								</span>
							</td>
							<td colspan="19"> 
							<p>
							<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							该学年必修课程数<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>门，其中，优秀<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>门，良好<u>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;</u>门<br/>
							</span>
							</p>
							<p> <span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							成绩排名（专业或年级）：<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>（名次/总人数）<br/>
							</span></p>
							<p><span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						          综合考评成绩<u>&nbsp;&nbsp;&nbsp;&nbsp; </u>分，排名<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>（名次/总人数）（如无此项，填写“无”）
						    </span></p>
						     </td> 
						</tr>
						<tr height="168px"> 
					      <th> <span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>获<br/>奖<br/>情<br/>况</b>
								</span>
					      </th> 
					      <td colspan="19"> 
							<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							<p>主要奖项：</p> 
							${rs.hjqk}
							</span>
							<br/>
							<br/>
							<br/>
							<br/>
							<p>
							<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
					        	其中，院级奖励<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.yjjlcs} </u>项；&nbsp;&nbsp;校级奖励<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${rs.xjjlcs}</u>项；&nbsp;&nbsp;省级以上奖励<u>&nbsp;&nbsp;&nbsp;&nbsp; ${rs.sjjlcs}</u>项
					        </span>
					        </p>
					       </td> 
					    </tr> 
						<tr height="168px" align="center">
							<td>
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>申<br/>请<br/>理<br/>由<br/>︵</br>全<br/>面</b>
								</span>
							</td>
							<td colspan="19" align="left">
								<p style="height:145px">
									<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
									</span>
								</p>
							</td>
						</tr>
					</table>
				</td>
			</tr>		
		</table>
		<!--分页-->
		<div class="PageNext"><br/></div>
		<table width="100%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
					<table width="100%" class="printStyle">
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
						<tr align="center">
							<td style="height:230px">
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>反<br/>映<br/>德<br/>智<br/>体<br/>美<br/>情<br/>况<br/>︶</b>
								</span>
							</td>
							<td colspan="19">
								<p style="height:180px">
									<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									
									</span>
								</p>
								<div align="right">
									<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									申请人签名：
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
						<tr align="center">
							<td>
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>年&nbsp;级<br/><br/>(专业)<br/><br/>推&nbsp;荐<br/><br/>意&nbsp;见</b>
								</span>
							</td>
							<td colspan="19" align="left">
								<p style="height:112px">
									<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.shzt1yj }
									</span>
								</p>
								<div align="left">
									<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;推荐人：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									行政职务：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</span>
								</div>
								<br/>
								<div align="right">
									<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									年&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>
									</span>
								</div>
							</td>
						</tr>
						<tr align="center">
							<td>
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>院<br/><br/>（系）<br/><br/>意<br/><br/>见</b>
								</span>
							</td>
							<td colspan="19" align="left">
								<p style="height:110px">
									<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.shzt2yj }
									</span>
								</p>
								<div align="right">
									<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									（公&nbsp;&nbsp;章）
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
						<tr align="center">
							<td>
								<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>学<br/><br/>校<br/><br/>意<br/><br/>见<br/></b>
								</span>
							</td>
							<td colspan="19">
								<p style="height:115px" align="left">
									<br/><br/>
									<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;经评审，并在<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>范围内公示<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>天，无异议，现报请同意该同学获得<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>学年度国家励志奖学金。
									</span>
								</p>
								<div align="right">
									<span style='font-size:12pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									（公&nbsp;&nbsp;章）
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
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>