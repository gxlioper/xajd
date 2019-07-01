<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/head.ini"%>
<title>武汉职业技术学院学生登记表</title>
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
						<u>${xxmc }学生登记表</u>
					</span>
					<br/>
					</b>
					<br/><br/><br/>
					<div align="left">
						<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<b>院系：</b>${jbxx.xymc }&nbsp;&nbsp;<b>专业：</b>${jbxx.zymc }&nbsp;&nbsp;<b>班级：</b>${jbxx.bjmc }&nbsp;&nbsp;<b>学号：</b>${jbxx.xh }
						</span>
					</div>
					<div align="left">
						<span style='font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<br/>（加盖公章）<br/>
						</span>
					</div>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="font_style" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" style='border-collapse:collapse;border:none;'>
						<tr>
							<td width="8%"></td>
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
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="8%"></td>
						</tr>
						<tr style="height: 25px">
							<td align="center" colspan="2">
								<b>姓名</b>
							</td>
							<td align="center" colspan="4">
								${jbxx.xm }
							</td>
							<td align="center" colspan="3">
								<b>曾用名</b>
							</td>
							<td align="center" colspan="4">
								${jbxx.cym }
							</td>
							<td align="center" colspan="3">
								<b>性别</b>
							</td>
							<td align="center" colspan="4">
								${jbxx.xb }
							</td>
							<td align="center" colspan="3" rowspan="6">
								<img style="width:100px;height:130px;" src="xsxx_xsgl.do?method=showPhoto&xh=${jbxx.xh}" border="0"/>
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" colspan="2">
								<b>学籍类别</b>
							</td>
							<td align="center" colspan="18">
								○普通本科&nbsp;&nbsp;&nbsp;&nbsp;
								○普通专科&nbsp;&nbsp;&nbsp;&nbsp;
								○其他<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" colspan="2">
								<b>出生</b>
							</td>
							<td align="center" colspan="4">
								${jbxx.csrq }
							</td>
							<td align="center" colspan="3">
								<b>籍贯</b>
							</td>
							<td align="center" colspan="11">
								${jbxx.jgmc}
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" colspan="2">
								<b>民族</b>
							</td>
							<td align="center" colspan="4">
								${jbxx.mzmc }
							</td>
							<td align="center" colspan="3">
								<b>政治面貌</b>
							</td>
							<td align="center" colspan="3">
								${jbxx.zzmmmc}
							</td>
							<td align="center" colspan="5">
								<b>入党（团）时间</b>
							</td>
							<td align="center" colspan="3">
								&nbsp;
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" colspan="2">
								<b>身份证号</b>
							</td>
							<td align="center">
								${jbxx.sfzh0 }
							</td>
							<td align="center">
								${jbxx.sfzh1 }
							</td>
							<td align="center">
								${jbxx.sfzh2 }
							</td>
							<td align="center">
								${jbxx.sfzh3 }
							</td>
							<td align="center">
								${jbxx.sfzh4 }
							</td>
							<td align="center">
								${jbxx.sfzh5 }
							</td>
							<td align="center">
								${jbxx.sfzh6 }
							</td>
							<td align="center">
								${jbxx.sfzh7 }
							</td>
							<td align="center">
								${jbxx.sfzh8 }
							</td>
							<td align="center">
								${jbxx.sfzh9 }
							</td>
							<td align="center">
								${jbxx.sfzh10 }
							</td>
							<td align="center">
								${jbxx.sfzh11 }
							</td>
							<td align="center">
								${jbxx.sfzh12 }
							</td>
							<td align="center">
								${jbxx.sfzh13 }
							</td>
							<td align="center">
								${jbxx.sfzh14 }
							</td>
							<td align="center">
								${jbxx.sfzh15 }
							</td>
							<td align="center">
								${jbxx.sfzh6 }
							</td>
							<td align="center">
								${jbxx.sfzh7 }
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" colspan="2">
								<b>身高</b>
							</td>
							<td align="center" colspan="4">
								${jbxx.sg }
							</td>
							<td align="center" colspan="4">
								<b>本人特长爱好</b>
							</td>
							<td align="center" colspan="10">
								${jbxx.tc }
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" colspan="3">
								<b>个人联系电话</b>
							</td>
							<td align="center" colspan="3">
								${jbxx.sjhm }
							</td>
							<td align="center" colspan="3">
								<b>寝室号</b>
							</td>
							<td align="center" colspan="4">
								${zsxx.qsh }
							</td>
							<td align="center" colspan="3">
								<b>寝室电话</b>
							</td>
							<td align="center" colspan="7">
								${zsxx.qsdh }
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" colspan="4">
								<b>最后学历毕业学校</b>
							</td>
							<td align="center" colspan="5">
								&nbsp;
							</td>
							<td align="center" colspan="3">
								<b>学校所在地</b>
							</td>
							<td align="center" colspan="11">
								&nbsp;
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" colspan="3">
								<b>家庭详细地址</b>
							</td>
							<td align="center" colspan="13">
								${jbxx.jtszd}
							</td>
							<td align="center" colspan="3">
								<b>邮政编码</b>
							</td>
							<td align="center" colspan="4">
								${jbxx.jtyb}
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" rowspan="6">
								<b>本<br/>人<br/>简<br/>历<br/></b>
							</td>
							<td align="center" colspan="4">
								<b>自何年何月</b>
							</td>
							<td align="center" colspan="4">
								<b>至何年何月</b>
							</td>
							<td align="center" colspan="10">
								<b>在何地学习或工作</b>
							</td>
							<td align="center" colspan="4">
								<b>证明人</b>
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" colspan="4">
								&nbsp;
							</td>
							<td align="center" colspan="4">
								&nbsp;
							</td>
							<td align="center" colspan="10">
								&nbsp;
							</td>
							<td align="center" colspan="4">
								&nbsp;
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" colspan="4">
								&nbsp;
							</td>
							<td align="center" colspan="4">
								&nbsp;
							</td>
							<td align="center" colspan="10">
								&nbsp;
							</td>
							<td align="center" colspan="4">
								&nbsp;
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" colspan="4">
								&nbsp;
							</td>
							<td align="center" colspan="4">
								&nbsp;
							</td>
							<td align="center" colspan="10">
								&nbsp;
							</td>
							<td align="center" colspan="4">
								&nbsp;
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" colspan="4">
								&nbsp;
							</td>
							<td align="center" colspan="4">
								&nbsp;
							</td>
							<td align="center" colspan="10">
								&nbsp;
							</td>
							<td align="center" colspan="4">
								&nbsp;
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" colspan="4">
								&nbsp;
							</td>
							<td align="center" colspan="4">
								&nbsp;
							</td>
							<td align="center" colspan="10">
								&nbsp;
							</td>
							<td align="center" colspan="4">
								&nbsp;
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" rowspan="6">
								<b>家<br/>庭<br/>情<br/>况<br/></b>
							</td>
							<td align="center" colspan="4">
								<b>姓名</b>
							</td>
							<td align="center" colspan="4">
								<b>与本人关系</b>
							</td>
							<td align="center" colspan="10">
								<b>工作单位和职务</b>
							</td>
							<td align="center" colspan="4">
								<b>联系电话</b>
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" colspan="4">
								${jbxx.jtcy1_xm }&nbsp;
							</td>
							<td align="center" colspan="4">
								${jbxx.jtcy1_gx }&nbsp;
							</td>
							<td align="center" colspan="10">
								${jbxx.jtcy1_gzdz }&nbsp;
							</td>
							<td align="center" colspan="4">
								${jbxx.jtcy1_lxdh1 }&nbsp;
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" colspan="4">
								${jbxx.jtcy2_xm }&nbsp;
							</td>
							<td align="center" colspan="4">
								${jbxx.jtcy2_gx }&nbsp;
							</td>
							<td align="center" colspan="10">
								${jbxx.jtcy2_gzdz }&nbsp;
							</td>
							<td align="center" colspan="4">
								${jbxx.jtcy2_lxdh1 }&nbsp;
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" colspan="4">
								${jbxx.jtcy3_xm }&nbsp;
							</td>
							<td align="center" colspan="4">
								${jbxx.jtcy3_gx }&nbsp;
							</td>
							<td align="center" colspan="10">
								${jbxx.jtcy3_gzdz }&nbsp;
							</td>
							<td align="center" colspan="4">
								${jbxx.jtcy3_lxdh1 }&nbsp;
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" colspan="4">
								&nbsp;
							</td>
							<td align="center" colspan="4">
								&nbsp;
							</td>
							<td align="center" colspan="10">
								&nbsp;
							</td>
							<td align="center" colspan="4">
								&nbsp;
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" colspan="4">
								&nbsp;
							</td>
							<td align="center" colspan="4">
								&nbsp;
							</td>
							<td align="center" colspan="10">
								&nbsp;
							</td>
							<td align="center" colspan="4">
								&nbsp;
							</td>
						</tr>
						<tr style="height: 200px">
							<td align="left" colspan="4">
								<b>近三年受到奖励、参加竞赛获奖情况</b>
							</td>
							<td align="center" colspan="8">
								&nbsp;
							</td>
							<td align="left" colspan="4">
								<b>近三年受到处分情况</b>
							</td>
							<td align="center" colspan="7">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="right">
				<br/>
					<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
					填表人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					年&nbsp;&nbsp;&nbsp;&nbsp;
					月&nbsp;&nbsp;&nbsp;&nbsp;
					日&nbsp;&nbsp;
					</span>
				</td>
			</tr>
		</table>
	</html:form>
	</div>
</body>
</html>