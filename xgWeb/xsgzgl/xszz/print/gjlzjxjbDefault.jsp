<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/head.ini"%>
<title>高等学校国家励志奖学金申请审批表</title>
<style>
.font_style td{font-size:14px;font-family:宋体; }
</style>

</head>

<body lang=ZH-CN style='text-justify-trim:punctuation'>

<div align=center>
<html:form action="/xszz_sqsh" method="post" styleId="xszzSqshForm">
<table width="652px" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
				<br/><br/><br/><br/>
					<b>
					<span style='font-size:18.0pt;font-family:方正小标宋简体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						国家励志奖学金申请审批表
					</span>
					<br/>
					<span style='font-size:14.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<logic:empty name="xmxx" property="xn">（&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;学年）</logic:empty><logic:notEmpty name="xmxx" property="xn">（${xmxx.xn }学年）</logic:notEmpty>
					</span>
					</b>
					<br/><br/><br/>
					<div align="left">
						<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<b>学校：</b>${xxmc}&nbsp;&nbsp;<b>院系：</b>${jbxx.xymc }&nbsp;&nbsp;<b>专业：</b>${jbxx.zymc }&nbsp;&nbsp;<b>班级：</b>${jbxx.bjmc }
						</span>
					</div>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="font_style" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" style='border-collapse:collapse;border:none;'>
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
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>基<br/>本<br/>情<br/>况</b>
								</span>
							</td>
							<td align="center">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								姓名
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.xm }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								性别
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.xb }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								出生年月
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.csrq }
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="center">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								学号
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.xh }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								民族
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.mzmc }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								入学时间
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.rxrq }
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="center">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								身份证号
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.sfzh }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								政治面貌
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.zzmmmc }
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="center">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								经济困难
								认定档次
								</span>
							</td>
							<td align="left" colspan="10">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<logic:present name="knsdc">
								  <logic:iterate id="j" name="knsdc" indexId="i">
								  	<logic:equal name="i"  value="0">A</logic:equal>
								  	<logic:equal name="i"  value="1">B</logic:equal>
								  	<logic:equal name="i"  value="2">C</logic:equal>
								  	<logic:equal name="i"  value="3">D</logic:equal>
								  	<logic:equal name="i"  value="4">E</logic:equal>
								  	<logic:equal name="i"  value="5">F</logic:equal>
								  	<logic:equal name="i"  value="6">G</logic:equal>
								  	<logic:equal name="i"  value="7">H</logic:equal>
								  	<logic:equal name="i"  value="8">I</logic:equal>
								  	<logic:equal name="i"  value="9">J</logic:equal>
								  	<logic:equal name="i"  value="10">K</logic:equal>
								  	<logic:equal name="i"  value="11">L</logic:equal>
								  	<logic:equal name="i"  value="12">M</logic:equal>
								  	<logic:equal name="i"  value="13">N</logic:equal>
								  	<logic:equal name="i"  value="14">O</logic:equal>
								  	、${j.dcmc }<logic:equal name="j" property="dcdm" value="${rddc }">（√）</logic:equal><logic:notEqual name="j" property="dcdm" value="${rddc }">（&nbsp;）</logic:notEqual>&nbsp;
								  </logic:iterate>
								  </logic:present>
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								联系电话
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.sjhm }
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="center" rowspan="3">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>家<br/>庭<br/>经<br/>济<br/>情<br/>况</b>
								</span>
							</td>
							<td align="center">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								家庭户口
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<logic:empty name="jtqk" property="jthk">
											A、城镇（&nbsp;&nbsp;&nbsp;&nbsp;）&nbsp;&nbsp;
											B、农村（&nbsp;&nbsp;&nbsp;&nbsp;）
									</logic:empty>
									<logic:equal name="jtqk" property="jthk" value="城镇">
											A、城镇（&nbsp;√&nbsp;）&nbsp;&nbsp;
											B、农村（&nbsp;&nbsp;&nbsp;&nbsp;）
									</logic:equal>
									<logic:equal name="jtqk" property="jthk" value="农村">
											A、城镇（&nbsp;&nbsp;&nbsp;&nbsp;）&nbsp;&nbsp;
											B、农村（&nbsp;√&nbsp;）
									</logic:equal>
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								邮政编码
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jtqk.jtyb}
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="center">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								家庭人口总数
								</span>
							</td>
							<td align="center" colspan="6">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jtqk.jtrs}
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								家庭总收入（元）
								</span>
							</td>
							<td align="center" colspan="8">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jtqk.jtnzsr}
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="center">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								家庭住址
								</span>
							</td>
							<td align="center" colspan="18">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jtqk.jtdz}
								</span>
							</td>
						</tr>
						<tr height="55px">
							<td align="center">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>综合<br/>测评<br/>情况</b>
								</span>
							</td>
							<td align="left" colspan="19">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<br/>
								&nbsp;&nbsp;本年级专业总人数：<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>；学习成绩排名：<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>  ；综合考评排名：<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>。（不得为空）
								<br/><br/><br/>&nbsp;&nbsp;上述采用的排名排序为&nbsp;&nbsp;□按专业  □按年级  □按班级
								<br/><br/>
								</span>
							</td>
						</tr>
						<tr height="55px">
							<td align="center">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>获<br/>奖<br/>情<br/>况</b>
								</span>
							</td>
							<td align="left" colspan="19">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<br/>
								&nbsp;&nbsp;主要奖项：<br/><br/><br/><br/><br/>
								&nbsp;&nbsp;其中，院级奖励<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>项；校级奖励<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>项；省级以上奖励 <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>项
								<br/><br/>
								</span>
							</td>
						</tr>
						<tr height="150px" align="center">
							<td>
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>申请<br/>理由<br/>︵<br/>家庭<br/>情况<br/>、<br/>在校<br/>表现<br/>500<br/>字<br/>以<br/>内<br/>︶<br/></b>
								</span>
							</td>
							<td colspan="19">
								<p style="height:140px" align="left">
									<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;${xmxx.sqly}
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
		<table width="652px" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
				<br/><br/><br/><br/><br/>
					<table width="100%" class="font_style" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" style='border-collapse:collapse;border:none;'>
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
						<tr height="400px" align="center">
							<td>
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>申<br/>请<br/>理<br/>由<br/>︵<br/>家<br/>庭<br/>情<br/>况<br/>、<br/>在<br/>校<br/>表<br/>现<br/>500<br/>字<br/>以<br/>内<br/>︶<br/></b>
								</span>
							</td>
							<td colspan="19">
								<p style="height:390px" align="left">
									<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;
									</span>
								</p>
								<div align="right">
									<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									申请人签名：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/><br/>
									年&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>
									</span>
								</div>
							</td>
						</tr>
						<tr height="160px" align="center">
							<td colspan="8">
								<div align="left">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								 <br/>&nbsp;院（系）意见
								</span>
								</div>
								<p style="height:150px" align="center">
									<br/><br/><br/><br/>
									<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									同&nbsp;&nbsp;&nbsp;&nbsp;意
									</span>
								</p>
								<div align="right">
									<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									（公&nbsp;&nbsp;&nbsp;&nbsp;章）
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>
									签名：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>
									年&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>
									</span>
								</div>
							</td>
							<td colspan="12">
								<div align="left">
								<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<br/>&nbsp;学校意见
								</span>
								</div>
								<p style="height:150px" align="center">
									<br/><br/><br/><br/>
									<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									同&nbsp;&nbsp;&nbsp;&nbsp;意
									</span>
								</p>
								<div align="right">
									<br/><br/>
									<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									（公&nbsp;&nbsp;&nbsp;&nbsp;章）
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
	</div>
</body>

</html>