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
		<table width="100%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
				<br/>
					<h2>
						（&nbsp;${rs.xn }&nbsp;学年）国家奖学金申请审批表
					</h2>
					<br/>
					<div align="left">
						<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;&nbsp;<b>学校：</b>${rs.xxmc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>院系：</b>${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>学号：</b>${rs.xh }
						</span>
					</div>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="printStyle">
						<tr>
							<td width="9%"></td>
							<td width="12%"></td>
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
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>基本<br/>情况</b>
								</span>
							</td>
							<td align="center">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								姓名
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xm }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								性别
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xb }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								出生年月
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.csrq }
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="center">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								政治面貌
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.zzmmmc }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								民族
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.mzmc }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								入学时间
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.rxrq }
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="center">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								专业
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.zymc }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								学制
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xz }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								联系电话
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.lxdh }
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="center"><span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>身份证号</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh0 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh1 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh2 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh3 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh4 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh5 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh6 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh7 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh8 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh9 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh10 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh11 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh12 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh13 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh14 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh15 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh16 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh17 }</span></td>
						</tr>
						<tr height="45px">
							<td align="center" rowspan="2">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>学习<br/>情况</b>
								</span>
							</td>
							<td align="left" colspan="8">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								成绩排名：<u>&nbsp;<logic:notEmpty name="rs" property="xnzypm">${rs.xnzypm}/${rs.zyzrs}</logic:notEmpty ><logic:empty name="rs" property="xnzypm">&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;</logic:empty></u>（名次/总人数）
								</span>
							</td>
							<td align="left" colspan="11">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								实行综合考评排名：是□；否□      						
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="left" colspan="8">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								必修课<u>&nbsp;&nbsp;&nbsp;${rs.bxkms}&nbsp;&nbsp;</u>门，其中及格以上<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.jgkms }</u>门
								</span>
							</td>
							<td align="left" colspan="11">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								如是，排名：<u>&nbsp;<logic:notEmpty name="rs" property="zhzfpm">${rs.zhzfpm}/${rs.zrs}</logic:notEmpty ><logic:empty name="rs" property="zhzfpm">&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;</logic:empty>&nbsp;</u>（名次/总人数）
								</span>
							</td>
						</tr>
						<tr height="37px">
							<td align="center" rowspan="5">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>大学<br/>期间<br/>主要<br/>获奖<br/>情况</b>
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								日期
								</span>
							</td>
							<td align="center" colspan="7">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								奖项名称
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								颁奖单位
								</span>
							</td>
						</tr>
						 <tr style="height:45px">
						      <td align="center" colspan="2">&nbsp;${rs.hjrq1}</td> 
						      <td align="center" colspan=7 >&nbsp;${rs.hjmc1}</td> 
						      <td align="center" colspan=10 >&nbsp;${rs.bjdw1}</td> 
						    </tr> 
						    <tr style="height:45px"> 
						      <td align="center" colspan="2">&nbsp;${rs.hjrq2}</td> 
						      <td align="center" colspan=7 >&nbsp;${rs.hjmc2}</td> 
						      <td align="center" colspan=10 >&nbsp;${rs.bjdw2}</td> 
						    </tr> 
						    <tr style="height:45px"> 
						      <td align="center" colspan="2">&nbsp;${rs.hjrq3}</td> 
						      <td align="center"   colspan=7 >&nbsp;${rs.hjmc3}</td> 
						      <td align="center"  colspan=10 >&nbsp;${rs.bjdw3}</td> 
						    </tr> 
						    <tr style="height:45px"> 
						      <td align="center" colspan="2">&nbsp;${rs.hjrq4}</td> 
						      <td align="center" colspan=7 >&nbsp;${rs.hjmc4}</td> 
						      <td align="center" colspan=10 >&nbsp;${rs.bjdw4}</td> 
						    </tr> 
						
						
						<tr height="300px"  >
							<td align="center">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>申请<br/>理由<br/></b></span>
								<span style='font-size:10pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>(200字)
								</span>
							</td>
							<td colspan="19" align="left">
								<p style="height:250px">
									<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
									</span>
								</p>
								<div align="right">
									<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									申请人签名（手签）：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>
									年&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>
									</span>
								</div>
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
						<tr style="height:320px" >
							<td width="10%" align="center">
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>推荐<br/>理由</b>
								<br/></span>
								<span style='font-size:10pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>(100字)
								</span>
							</td>
							<td width="88%" align="left">
								<p style="height:230px">
									<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.tjly }
									</span>
								</p>
								<div align="right">
									<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									推荐人（辅导员或班主任）签名：
									&nbsp;&nbsp;&nbsp;&nbsp;
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
						<tr style="height:320px" align="center">
							<td>
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>院<br/><br/>（系）<br/><br/>意<br/><br/>见</b>
								</span>
							</td>
							<td align="left">
								<p style="height:150px">
									<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										&nbsp;&nbsp;&nbsp;&nbsp;${rs.shzt1yj }
									</span>
								</p>
								<div align="right">
									<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									院系主管学生工作领导签名：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
						<tr style="height:320px" align="center">
							<td>
								<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>学<br/><br/>校<br/><br/>意<br/><br/>见<br/></b>
								</span>
							</td>
							<td>
								<p style="height:190px" align="left">
									<br/><br/><br/><br/>
									<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;经评审，并在校内公示<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>个工作日，无异议，现报请批准该同学获得国家奖学金。
									</span>
								</p>
								<div align="right">
									<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
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
						<span style='font-size:11.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						制表：全国学生资助管理中心&nbsp;2010年版
						</span>
					</div>
				</td>
			</tr>
		</table>
		
	 <div align="" style='layout-grid:15.6pt;line-height:28.0pt;margin-left:20px'> <br/>
  <p align=center style='
text-align:center;
line-height:150%;layout-grid-mode:char;'><b><span style='font-size:16.0pt;line-height:
150%;font-family:仿宋_GB2312;color:#333333'>《国家奖学金申请审批表》填写说明</span></b></p> 

<span  style='text-indent:28.0pt;line-height:25.0pt;'><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>各高校从全国学生资助管理中心网站</span><span
style='font-size:12.0pt;font-family:仿宋_GB2312'>（<span lang=EN-US><a
href="http://www.xszz.cee.edu.cn/"><span style='font-family:"Times New Roman";
color:windowtext;text-decoration:none'>http://www.xszz.cee.edu.cn</span></a></span>）</span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>下载或复印《国家奖学金申请审批表》，组织人员认真填写。</span></span><br/>
<span  style='text-indent:28.0pt;line-height:25.0pt;'><span
lang=EN-US style='font-size:14.0pt;font-family:仿宋_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;1. </span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>表格为一页，正反两面，不得随意增加页数。</span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>表格填写应当字迹清晰、信息完整，</span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>不得涂改数据或出现空白项。</span></span><br/>
<span  style='text-indent:28.0pt;line-height:25.0pt;'><span
lang=EN-US style='font-size:14.0pt;font-family:仿宋_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;2. </span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>表格标题中学年的填写为评审工作开始所在学年的上一学年。如<span
lang=EN-US>2010</span>年秋季学期填表，应填写“<span lang=EN-US>2009</span>－<span
lang=EN-US>2010</span>学年”，以此类推。</span></span><br/>
<span  style='text-indent:28.0pt;line-height:25.0pt;'><span
lang=EN-US style='font-size:14.0pt;font-family:仿宋_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;3. </span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>表格中“基本情况”和“申请理由”栏由学生本人填写，其他各项必须由学校有关部门填写。</span></span><br/>
<span  style='text-indent:28.0pt;line-height:25.0pt;'><span
lang=EN-US style='font-size:14.0pt;font-family:仿宋_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;4. </span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>表格中学习成绩、综合考评成绩排名的范围由各高校自行确定，学校、院系、年级、专业、班级排名均可，但必须注明评选范围的总人数。</span></span><br/>
<span  style='text-indent:28.0pt;line-height:25.0pt;'><span
lang=EN-US style='font-size:14.0pt;font-family:仿宋_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;5. </span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>表格中“申请理由”栏的填写应当全面详实，</span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>能够如实反映学生学习</span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>成绩优异</span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>、社会实践、创新能力、综合素质等方面特别突出。</span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>字数控制在<span lang=EN-US>200</span>字左右。</span></span><br/>
<span  style='text-indent:28.0pt;line-height:25.0pt;'><span
lang=EN-US style='font-size:14.0pt;font-family:仿宋_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;6. </span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>表格中“推荐意见”栏的填写应当简明扼要，字数控制在<span
lang=EN-US>100</span>字左右。推荐人必须是申请学生的辅导员或班主任，其他人无权推荐。</span></span><br/>
<span style='text-indent:28.0pt;line-height:25.0pt;'><span
lang=EN-US style='font-size:14.0pt;font-family:仿宋_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;7. </span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>表格必须体现学校各级部门的意见，推荐人和学校各院系主管学生工作的领导同志必须签名，不得由他人代写推荐意见或签名。</span></span><br/>
<span  style='text-indent:28.0pt;line-height:25.0pt;'><span
lang=EN-US style='font-size:14.0pt;font-family:仿宋_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;8. </span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>表格中“学校意见”栏必须加盖学校公章。设立院（系）的学校必须加盖院（系）公章，不设立院（系）的学校，必须在“院（系）意见”栏中说明。表格中凡需签名之处，必须由相关人员亲手签写。</span></span><br/>
<span  style='text-indent:28.0pt;line-height:25.0pt;'><span
lang=EN-US style='font-size:14.0pt;font-family:仿宋_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;9. </span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>表格上报一律使用原件，不得使用复印件。学生成绩单、获奖证书等证明材料只需经过学校审查，不需随表报送。上报材料经评审后不予退回，各高校根据需要自行准备存档材料。</span></span><br/>

</div> 
	</html:form>
</body>
</html:html>