<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/head.ini"%>
<title>${xxmc }ѧ��������Ϣ��</title>
<style>
.font_style td{font-size:14px;font-family:����; }
</style>

</head>

<body lang=ZH-CN style='text-justify-trim:punctuation'>

<div align=center>
<html:form action="/xszz_sqsh" method="post" styleId="xszzSqshForm">
<table width="652px" border="0" id="theTable" align="center">
			<tr>
				<td align="center" height="30px">
					<b>
					<span style='font-size:16.0pt;font-family:����С���μ���;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<u>${xxmc }ѧ��������Ϣ��</u>
					</span>
					</b>
				</td>			
			</tr>
			<tr>
				<td align="center" >
					<div align="left">
						<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<b>ѧ����Ϣ</b>
						</span>
					</div>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="font_style" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" style='border-collapse:collapse;border:none;'>
						
						<tr style="height: 25px">
							<td align="center" colspan="2" width="10%">
								����
							</td>
							<td align="center" colspan="2" width="10%">
								${jbxx.xm }
							</td>
							
							<td align="center" colspan="2" width="10%">
								�Ա�
							</td>
							<td align="center" colspan="2" width="10%">
								${jbxx.xb }
							</td>
							<td align="center" colspan="2" width="8%">
								�꼶
							</td>
							<td align="center" colspan="2" width="10%">
								${jbxx.nj }
							</td>
							<td align="center" colspan="2" width="10%">
								ѧ��
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
								רҵ
							</td>
							<td align="center" colspan="7">
								${jbxx.zymc }
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" colspan="2">
								�༶
							</td>
							<td align="center" colspan="6">
								${jbxx.bjmc}
							</td>
							<td align="center" colspan="2">
								ѧ��
							</td>
							<td align="center" colspan="7">
								${jbxx.xh}
							</td>
						</tr>
						<tr style="height: 25px">
							<td align="center" colspan="2">
								��ѧ����
							</td>
							<td align="center" colspan="4">
								${jbxx.rxrq }
							</td>
							<td align="center" colspan="2">
								��ҵ����
							</td>
							<td align="center" colspan="4">
								${jbxx.bysj }
							</td>
							<td align="center" colspan="2">
								ѧ���춯
							</td>
							<td align="center" colspan="3">
								${jbxx.zd4}
							</td>
						</tr>
						</table>
						<br/>
						<div align="left">
						<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<b>������Ϣ</b>
						</span>
					</div>
					
					<table width="100%" class="font_style" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" style='border-collapse:collapse;border:none;'>
					
						<tr style="height: 25px">
							<td align="center" width="10%">
								����ƴ��
							</td>
							<td align="center" width="15%">
								${jbxx.xmpy }
							</td>
							<td align="center" width="10%">
								������
							</td>
							<td align="center" width="15%">
								${jbxx.cym }
							</td>
							<td align="center" width="10%">
								���֤������
							</td>
							<td align="center" width="15%">
								�������֤
							</td>
							<td align="center" width="10%">
								���֤����
							</td>
							<td align="center" width="15%">
								${jbxx.sfzh }
							</td>
							</tr>
							
							<tr style="height: 25px" >
							<td align="center" >
								��������
							</td>
							<td align="center" >
								${jbxx.csrq }
							</td>
							<td align="center" >
								����
							</td>
							<td align="center" >
								${jbxx.mzmc }
							</td>
						
							<td align="center" >
								��������
							</td>
							<td align="center">
								${jbxx.zd5 }
							</td>
							<td align="center">
								������ò
							</td>
							<td align="center" >
								${jbxx.zzmmmc }
							</td>
							</tr>
							
							<tr style="height: 25px">
							<td align="center" >
								��ϵ�绰
							</td>
							<td align="center" >
								${jbxx.lxdh }
							</td>
							<td align="center" >
								�ڽ�����
							</td>
							<td align="center" >
								${jbxx.zjmc }
							</td>
						
							<td align="center" >
								����״��
							</td>
							<td align="center">
								${jbxx.jkzk }
							</td>
							<td align="center">
								����
							</td>
							<td align="center" >
								${jbxx.jgmc }
							</td>
							</tr>
							
							<tr style="height: 25px">
							<td align="center" >
								����
							</td>
							<td align="center" >
								${jbxx.gj }
							</td>
							<td align="center" >
								����״̬
							</td>
							<td align="center" >
								${jbxx.sfjh}
							</td>
						
							<td align="center" >
								QQ ��
							</td>
							<td align="center">
								${jbxx.qqhm }
							</td>
							<td align="center">
								����
							</td>
							<td align="center" >
								${zsxx.ldmc }-${zsxx.qsh }-${zsxx.cwh }
							</td>
							</tr>
							
							
							<tr style="height: 25px">
							<td align="center" >
								�����ʼ�
							</td>
							<td align="center" >
								${jbxx.dzyx }
							</td>
							<td align="center" >
								������
							</td>
							<td align="center" colspan="2" >
								${jbxx.csd }
							</td>
						
							<td align="center" >
								��Դ���ڵ�
							</td>
							<td align="center" colspan="2">
								${jbxx.sydmc }
							</td>
							</tr>
							</table>
							<br/>
						<div align="left">
						<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<b>��ͥ��Ϣ</b>
						</span>
					</div>
					
					<table width="100%" class="font_style" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" style='border-collapse:collapse;border:none;'>
					<tr style="height: 25px">
							<td align="center" width="15%">
								��ͥסַ
							</td>
							<td align="center" width="40%">
								${jbxx.jtszd }
							</td>
							<td align="center" width="15%">
								�ʱ�
							</td>
							<td align="center" width="30%">
								${jbxx.jtyb  }
							</td>
							</tr>
							<tr style="height: 25px">
							<td align="center" >
								��סַ
							</td>
							<td align="center" >
								${jbxx.xwzsxxdz  }
							</td>
							<td align="center" >
								�ʱ�
							</td>
							<td align="center" >
								${jbxx.zd1}
							</td>
							</tr>
					</table>
							
						<br/>
						<div align="left">
						<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<b>��ͥ��Ա</b>
						</span>
					</div>
					
					<table width="100%" class="font_style" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" style='border-collapse:collapse;border:none;'>
					
						<tr style="height: 25px">
							<td align="center" width="15%">
								����
							</td>
							<td align="center" width="15%">
								�뱾�˹�ϵ
							</td>
							<td align="center" width="20%">
								������λ����ַ
							</td>
							<td align="center" width="20%">
								��λ�绰
							</td>
							<td align="center" width="15%">
								���˵绰
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
						<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<b>������Ϣ</b>
						</span>
					</div>
					
					<table width="100%" class="font_style" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" style='border-collapse:collapse;border:none;'>
					<tr style="height: 25px">
							<td align="center" width="20%">
								��������
							</td>
							<td align="center" width="30%">
								${jbxx.yhmc }
							</td>
							<td align="center" width="20%">
								���п���
							</td>
							<td align="center" width="30%">
								${jbxx.yhkh }
							</td>
					</tr>
					<tr style="height: 25px">
							<td align="center" width="20%">
								��ߣ�cm��
							</td>
							<td align="center" width="30%">
								${jbxx.sg }
							</td>
							<td align="center" width="20%">
								���أ�kg��
							</td>
							<td align="center" width="30%">
								${jbxx.tz }
							</td>
					</tr>
					<tr style="height: 25px">
							<td align="center" width="20%">
								�س�
							</td>
							<td align="center" width="30%">
								${jbxx.tc }
							</td>
							<td align="center" width="20%">
								Ѫ��
							</td>
							<td align="center" width="30%">
								${jbxx.xx }
							</td>
					</tr>
					<tr style="height: 25px">
							<td align="center" width="20%">
								ѧ�����
							</td>
							<td align="center" width="30%">
								${jbxx.pyccmc }
							</td>
							<td align="center" width="20%">
								�Ƿ��߶�
							</td>
							<td align="center" width="30%">
								${jbxx.sfzd }
							</td>
					</tr>
					<tr style="height: 25px">
							<td align="center" width="20%">
								�߿�������
							</td>
							<td align="center" width="30%">
								${jbxx.ksh}
							</td>
							<td align="center" width="20%">
								���б�ҵѧУ
							</td>
							<td align="center" width="30%">
								${jbxx.rxqdw}
							</td>
					</tr>
					<tr style="height: 25px">
							<td align="center" width="20%">
								�������
							</td>
							<td align="center" width="30%">
								${jbxx.kslbmc }
							</td>
							<td align="center" width="20%">
								��ѧ��ʽ
							</td>
							<td align="center" width="30%">
								${jbxx.rxfsmc }
							</td>
					</tr>
					<tr style="height: 25px">
							<td align="center" width="20%">
								������ʽ
							</td>
							<td align="center" width="30%">
								${jbxx.pyfsmc }
							</td>
							<td align="center" width="20%">
								��ͥ�ṹ
							</td>
							<td align="center" width="30%">
								${jbxx.zd2 }
							</td>
					</tr>
					<tr style="height: 25px">
							<td align="center" width="20%">
								�˳�����
							</td>
							<td align="center" width="30%">
								${jbxx.ccqj }
							</td>
							<td align="center" width="20%">
								���ΰ�ɲ�
							</td>
							<td align="center" width="30%">
								${jbxx.zw }
							</td>
					</tr>
					<tr style="height: 25px">
							<td align="center" width="20%">
								Ƿ�ѽ��
							</td>
							<td align="left" width="30%">
								${qfje }
							</td>
							<td align="center" width="20%">
								��ע
							</td>
							<td align="left" width="30%">
								${jbxx.bz }
							</td>
					</tr>
					</table>
				<div align="left">
						<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                ������Ϣ�Ѻ�ʵ����.&nbsp;&nbsp;
                                                ǩ��:<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
						</span>
			    </div>
				</td>
			</tr>
		</table>
			
		
		
	</html:form>
	</div>
</body>
</html>