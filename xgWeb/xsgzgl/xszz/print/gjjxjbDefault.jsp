<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/head.ini"%>
<title>�ߵ�ѧУ���ҽ�ѧ������������</title>
<style>
.font_style td{font-size:14px;font-family:����; }
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
					<span style='font-size:18.0pt;font-family:����С���μ���;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<logic:empty name="xmxx" property="xn">��&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;ѧ�꣩</logic:empty><logic:notEmpty name="xmxx" property="xn">��${xmxx.xn }ѧ�꣩</logic:notEmpty>���ҽ�ѧ������������
					</span>
					</b>
					<br/><br/><br/>
					<div align="left">
						<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<b>ѧУ��</b>${xxmc}&nbsp;&nbsp;<b>Ժϵ��</b>${jbxx.xymc }&nbsp;&nbsp;<b>ѧ�ţ�</b>${jbxx.xh }
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
						<tr height="35px">
							<td align="center" rowspan="4">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>��<br/>��<br/>��<br/>��</b>
								</span>
							</td>
							<td align="center">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								����
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.xm }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								�Ա�
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.xb }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��������
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.csrq }
								</span>
							</td>
						</tr>
						<tr height="35px">
							<td align="center">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								����<br/>��ò
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.zzmmmc }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								����
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.mzmc }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ѧʱ��
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.rxrq }
								</span>
							</td>
						</tr>
						<tr height="35px">
							<td align="center">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								רҵ
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.zymc }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								ѧ��
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.xz }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ϵ�绰
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.sjhm }
								</span>
							</td>
						</tr>
						<tr height="35px">
							<td align="center"><span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>���֤��</span></td>
							<td align="center"><span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh0 }</span></td>
							<td align="center"><span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh1 }</span></td>
							<td align="center"><span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh2 }</span></td>
							<td align="center"><span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh3 }</span></td>
							<td align="center"><span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh4 }</span></td>
							<td align="center"><span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh5 }</span></td>
							<td align="center"><span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh6 }</span></td>
							<td align="center"><span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh7 }</span></td>
							<td align="center"><span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh8 }</span></td>
							<td align="center"><span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh9 }</span></td>
							<td align="center"><span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh10 }</span></td>
							<td align="center"><span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh11 }</span></td>
							<td align="center"><span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh12 }</span></td>
							<td align="center"><span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh13 }</span></td>
							<td align="center"><span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh14 }</span></td>
							<td align="center"><span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh15 }</span></td>
							<td align="center"><span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh16 }</span></td>
							<td align="center"><span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh17 }</span></td>
						</tr>
						<tr height="55px">
							<td align="center" rowspan="2">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>ѧϰ<br/>���</b>
								</span>
							</td>
							<td align="left" colspan="9">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								�ɼ�������<u>&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;</u>������/��������
								</span>
							</td>
							<td align="left" colspan="10">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								ʵ���ۺϿ����������ǡ� �����
								</span>
							</td>
						</tr>
						<tr height="55px">
							<td align="left" colspan="9">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								���޿�<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�ţ����м�������<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
								</span>
							</td>
							<td align="left" colspan="10">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								���ǣ�������<u>&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;</u>������/��������
								</span>
							</td>
						</tr>
						<tr height="35px">
							<td align="center" rowspan="5">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>��ѧ<br/>�ڼ�<br/>��Ҫ<br/>��<br/>���</b>
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								����
								</span>
							</td>
							<td align="center" colspan="7">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��������
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								�佱��λ
								</span>
							</td>
						</tr>
						<tr height="35px">
							<td align="center" colspan="2">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center" colspan="7">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
						</tr>
						<tr height="35px">
							<td align="center" colspan="2">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center" colspan="7">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
						</tr>
						<tr height="35px">
							<td align="center" colspan="2">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center" colspan="7">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
						</tr>
						<tr height="35px">
							<td align="center" colspan="2">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center" colspan="7">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
						</tr>
						<tr height="300px" align="center">
							<td>
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>����<br/>����</b>
								</span>
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<br/>(200 ��)
								</span>
							</td>
							<td colspan="19">
								<p style="height:290px" align="left">
									<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;${xmxx.sqly }
									</span>
								</p>
								<div align="right">
									<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									������ǩ������ǩ����
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>
									��&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>
									</span>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!--��ҳ-->
		<div class="PageNext"><br/></div>
		<br/><br/><br/>
		<table width="652px" border="0" align="center">
			<tr>
				<td align="center">
				<br/><br/><br/><br/><br/>
					<table width="100%" class="font_style" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" style='border-collapse:collapse;border:none;'>
						<tr>
							<td width="10%"></td>
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
						<tr height="180px" align="center">
							<td>
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>�Ƽ�<br/>����</b>
								</span>
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<br/>(200 ��)
								</span>
							</td>
							<td colspan="19">
								<p style="height:170px" align="left">
									<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;
									</span>
								</p>
								<div align="right">
									<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>�Ƽ��ˣ�����Ա������Σ�ǩ����
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>
									��&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>
									</span>
								</div>
							</td>
						</tr>
						<tr height="160px" align="center">
							<td>
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>Ժ<br/><br/>��ϵ��<br/><br/>��<br/><br/>��</b>
								</span>
							</td>
							<td colspan="19">
								<p style="height:150px" align="left">
									<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
									</span>
								</p>
								<div align="right">
									<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									Ժϵ����ѧ�������쵼ǩ����
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>��Ժϵ���£�
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>
									��&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>
									</span>
								</div>
							</td>
						</tr>
						<tr height="200px" align="center">
							<td>
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>ѧ<br/><br/>У<br/><br/>��<br/><br/>��<br/></b>
								</span>
							</td>
							<td colspan="19">
								<p style="height:190px" align="left">
									<br/><br/><br/><br/>
									<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;�����󣬲���У�ڹ�ʾ<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�������գ������飬�ֱ�����׼��ͬѧ��ù��ҽ�ѧ��
									</span>
								</p>
								<div align="right">
									<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									��ѧУ���£�
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>
									��&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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