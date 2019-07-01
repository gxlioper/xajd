<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/head.ini"%>
<title>${xxmc }学生基本信息表</title>
<style>
.font_style td{font-size:14px;font-family:宋体; }
</style>

</head>

<body lang=ZH-CN style='text-justify-trim:punctuation'>

<div align=center>
<html:form action="/xszz_sqsh" method="post" styleId="xszzSqshForm">
<table width="652px" border="0" id="theTable" align="center">
			<tr>
				<td align="center" height="30px">
					<b>
					<span style='font-size:16.0pt;font-family:方正小标宋简体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<u>${xxmc }学生基本信息表</u>
					</span>
					</b>
				</td>			
			</tr>
			<tr>
				<td align="center" >
					<div align="left">
						<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<b>学籍信息</b>
						</span>
					</div>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="font_style" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" style='border-collapse:collapse;border:none;'>
						
						<tr style="height: 25px">
							<td align="center" colspan="2" width="10%">
								姓名
							</td>
							<td align="center" colspan="2" width="10%">
								${jbxx.xm }
							</td>
							
							<td align="center" colspan="2" width="10%">
								性别
							</td>
							<td align="center" colspan="2" width="10%">
								${jbxx.xb }
							</td>
							<td align="center" colspan="2" width="8%">
								年级
							</td>
							<td align="center" colspan="2" width="10%">
								${jbxx.nj }
							</td>
							<td align="center" colspan="2" width="10%">
								学制
							</td>
							<td align="center" colspan="3" width="15%">
								${jbxx.xz }
							</td>
							<td align="center" colspan="3" rowspan="6" width="25%">
								<img style="width:100px;height:130px;" src="xsxx_xsgl.do?method=showPhoto&xh=${jbxx.xh}" border="0"/>
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" colspan="2">
								<bean:message key="lable.xb" />
							</td>
							<td align="center" colspan="6">
								${jbxx.xymc }
							</td>
							
							<td align="center" colspan="2">
								专业
							</td>
							<td align="center" colspan="7">
								${jbxx.zymc }
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" colspan="2">
								班级
							</td>
							<td align="center" colspan="6">
								${jbxx.bjmc}
							</td>
							<td align="center" colspan="2">
								学号
							</td>
							<td align="center" colspan="7">
								${jbxx.xh}
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" colspan="2">
								入学日期
							</td>
							<td align="center" colspan="4">
								${jbxx.rxrq }
							</td>
							<td align="center" colspan="2">
								毕业日期
							</td>
							<td align="center" colspan="4">
								${jbxx.bysj }
							</td>
							<td align="center" colspan="2">
								学籍异动
							</td>
							<td align="center" colspan="3">
								${jbxx.zd4}
							</td>
						</tr>
						</table>
						<br/>
						<div align="left">
						<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<b>个人信息</b>
						</span>
					</div>
					
					<table width="100%" class="font_style" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" style='border-collapse:collapse;border:none;'>
					
						<tr style="height: 25px">
							<td align="center" width="10%">
								姓名拼音
							</td>
							<td align="center" width="15%">
								${jbxx.xmpy }
							</td>
							<td align="center" width="10%">
								曾用名
							</td>
							<td align="center" width="15%">
								${jbxx.cym }
							</td>
							<td align="center" width="10%">
								身份证件类型
							</td>
							<td align="center" width="15%">
								居民身份证
							</td>
							<td align="center" width="10%">
								身份证件号
							</td>
							<td align="center" width="15%">
								${jbxx.sfzh }
							</td>
							</tr>
							
							<tr style="height: 25px" >
							<td align="center" >
								出生日期
							</td>
							<td align="center" >
								${jbxx.csrq }
							</td>
							<td align="center" >
								民族
							</td>
							<td align="center" >
								${jbxx.mzmc }
							</td>
						
							<td align="center" >
								户籍性质
							</td>
							<td align="center">
								${jbxx.zd5 }
							</td>
							<td align="center">
								政治面貌
							</td>
							<td align="center" >
								${jbxx.zzmmmc }
							</td>
							</tr>
							
							<tr style="height: 25px">
							<td align="center" >
								联系电话
							</td>
							<td align="center" >
								${jbxx.lxdh }
							</td>
							<td align="center" >
								宗教信仰
							</td>
							<td align="center" >
								${jbxx.zjmc }
							</td>
						
							<td align="center" >
								健康状况
							</td>
							<td align="center">
								${jbxx.jkzk }
							</td>
							<td align="center">
								籍贯
							</td>
							<td align="center" >
								${jbxx.jgmc }
							</td>
							</tr>
							
							<tr style="height: 25px">
							<td align="center" >
								国籍
							</td>
							<td align="center" >
								${jbxx.gj }
							</td>
							<td align="center" >
								婚姻状态
							</td>
							<td align="center" >
								${jbxx.sfjh}
							</td>
						
							<td align="center" >
								QQ 号
							</td>
							<td align="center">
								${jbxx.qqhm }
							</td>
							<td align="center">
								宿舍
							</td>
							<td align="center" >
								${zsxx.ldmc }-${zsxx.qsh }-${zsxx.cwh }
							</td>
							</tr>
							
							
							<tr style="height: 25px">
							<td align="center" >
								电子邮件
							</td>
							<td align="center" >
								${jbxx.dzyx }
							</td>
							<td align="center" >
								出生地
							</td>
							<td align="center" colspan="2" >
								${jbxx.csd }
							</td>
						
							<td align="center" >
								生源所在地
							</td>
							<td align="center" colspan="2">
								${jbxx.sydmc }
							</td>
							</tr>
							</table>
							<br/>
						<div align="left">
						<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<b>家庭信息</b>
						</span>
					</div>
					
					<table width="100%" class="font_style" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" style='border-collapse:collapse;border:none;'>
					<tr style="height: 25px">
							<td align="center" width="15%">
								家庭住址
							</td>
							<td align="center" width="40%">
								${jbxx.jtszd }
							</td>
							<td align="center" width="15%">
								邮编
							</td>
							<td align="center" width="30%">
								${jbxx.jtyb  }
							</td>
							</tr>
							<tr style="height: 25px">
							<td align="center" >
								现住址
							</td>
							<td align="center" >
								${jbxx.xwzsxxdz  }
							</td>
							<td align="center" >
								邮编
							</td>
							<td align="center" >
								${jbxx.zd1}
							</td>
							</tr>
					</table>
							
						<br/>
						<div align="left">
						<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<b>家庭成员</b>
						</span>
					</div>
					
					<table width="100%" class="font_style" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" style='border-collapse:collapse;border:none;'>
					
						<tr style="height: 25px">
							<td align="center" width="15%">
								姓名
							</td>
							<td align="center" width="15%">
								与本人关系
							</td>
							<td align="center" width="20%">
								工作单位及地址
							</td>
							<td align="center" width="20%">
								单位电话
							</td>
							<td align="center" width="15%">
								个人电话
							</td>
						</tr>
						<logic:notEmpty name="jtcyxxList">
							<logic:iterate id="jtcy" name="jtcyxxList" length="2">
								<tr>
									<td align="center">
										${jtcy.jtcyxm }
									</td>
									<td align="center">
										${jtcy.jtcygxmc }
									</td>
									<td align="center">
										${jtcy.jtcygzdz }
									</td>
									<td align="center">
										${jtcy.jtcylxdh2 }
									</td>
									<td align="center">
										${jtcy.jtcylxdh1 }
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>						
					</table>
					
					<br/>
						<div align="left">
						<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<b>其他信息</b>
						</span>
					</div>
					
					<table width="100%" class="font_style" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" style='border-collapse:collapse;border:none;'>
					<tr style="height: 25px">
							<td align="center" width="20%">
								银行名称
							</td>
							<td align="center" width="30%">
								${jbxx.yhmc }
							</td>
							<td align="center" width="20%">
								银行卡号
							</td>
							<td align="center" width="30%">
								${jbxx.yhkh }
							</td>
					</tr>
					<tr style="height: 25px">
							<td align="center" width="20%">
								身高（cm）
							</td>
							<td align="center" width="30%">
								${jbxx.sg }
							</td>
							<td align="center" width="20%">
								体重（kg）
							</td>
							<td align="center" width="30%">
								${jbxx.tz }
							</td>
					</tr>
					<tr style="height: 25px">
							<td align="center" width="20%">
								特长
							</td>
							<td align="center" width="30%">
								${jbxx.tc }
							</td>
							<td align="center" width="20%">
								血型
							</td>
							<td align="center" width="30%">
								${jbxx.xx }
							</td>
					</tr>
					<tr style="height: 25px">
							<td align="center" width="20%">
								学历层次
							</td>
							<td align="center" width="30%">
								${jbxx.pyccmc }
							</td>
							<td align="center" width="20%">
								是否走读
							</td>
							<td align="center" width="30%">
								${jbxx.sfzd }
							</td>
					</tr>
					<tr style="height: 25px">
							<td align="center" width="20%">
								高考报名号
							</td>
							<td align="center" width="30%">
								${jbxx.ksh}
							</td>
							<td align="center" width="20%">
								高中毕业学校
							</td>
							<td align="center" width="30%">
								${jbxx.rxqdw}
							</td>
					</tr>
					<tr style="height: 25px">
							<td align="center" width="20%">
								考生类别
							</td>
							<td align="center" width="30%">
								${jbxx.kslbmc }
							</td>
							<td align="center" width="20%">
								入学方式
							</td>
							<td align="center" width="30%">
								${jbxx.rxfsmc }
							</td>
					</tr>
					<tr style="height: 25px">
							<td align="center" width="20%">
								培养方式
							</td>
							<td align="center" width="30%">
								${jbxx.pyfsmc }
							</td>
							<td align="center" width="20%">
								家庭结构
							</td>
							<td align="center" width="30%">
								${jbxx.zd2 }
							</td>
					</tr>
					<tr style="height: 25px">
							<td align="center" width="20%">
								乘车区间
							</td>
							<td align="center" width="30%">
								${jbxx.ccqj }
							</td>
							<td align="center" width="20%">
								担任班干部
							</td>
							<td align="center" width="30%">
								${jbxx.zw }
							</td>
					</tr>
					<tr style="height: 25px">
							<td align="center" width="20%">
								欠费金额
							</td>
							<td align="left" width="30%">
								${qfje }
							</td>
							<td align="center" width="20%">
								备注
							</td>
							<td align="left" width="30%">
								${jbxx.bz }
							</td>
					</tr>
					</table>
				<div align="left">
						<span style='font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                以上信息已核实无误.&nbsp;&nbsp;
                                                签名:<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						</span>
			    </div>
				</td>
			</tr>
		</table>
			
		
		
	</html:form>
	</div>
</body>
</html>