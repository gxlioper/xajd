<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/head.ini"%>
<title>�ߵ�ѧУ������־��ѧ������������</title>
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
						������־��ѧ������������
					</span>
					<br/>
					<span style='font-size:14.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<logic:empty name="xmxx" property="xn">��&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;ѧ�꣩</logic:empty><logic:notEmpty name="xmxx" property="xn">��${xmxx.xn }ѧ�꣩</logic:notEmpty>
					</span>
					</b>
					<br/><br/><br/>
					<div align="left">
						<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<b>ѧУ��</b>${xxmc}&nbsp;&nbsp;<b>Ժϵ��</b>${jbxx.xymc }&nbsp;&nbsp;<b>רҵ��</b>${jbxx.zymc }&nbsp;&nbsp;<b>�༶��</b>${jbxx.bjmc }
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
						<tr height="45px">
							<td align="center">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								ѧ��
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.xh }
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
						<tr height="45px">
							<td align="center">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								���֤��
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.sfzh }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								������ò
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.zzmmmc }
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="center">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��������
								�϶�����
								</span>
							</td>
							<td align="left" colspan="10">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
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
								  	��${j.dcmc }<logic:equal name="j" property="dcdm" value="${rddc }">���̣�</logic:equal><logic:notEqual name="j" property="dcdm" value="${rddc }">��&nbsp;��</logic:notEqual>&nbsp;
								  </logic:iterate>
								  </logic:present>
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
						<tr height="45px">
							<td align="center" rowspan="3">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>��<br/>ͥ<br/>��<br/>��<br/>��<br/>��</b>
								</span>
							</td>
							<td align="center">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ͥ����
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<logic:empty name="jtqk" property="jthk">
											A������&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
											B��ũ�壨&nbsp;&nbsp;&nbsp;&nbsp;��
									</logic:empty>
									<logic:equal name="jtqk" property="jthk" value="����">
											A������&nbsp;��&nbsp;��&nbsp;&nbsp;
											B��ũ�壨&nbsp;&nbsp;&nbsp;&nbsp;��
									</logic:equal>
									<logic:equal name="jtqk" property="jthk" value="ũ��">
											A������&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
											B��ũ�壨&nbsp;��&nbsp;��
									</logic:equal>
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��������
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jtqk.jtyb}
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="center">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ͥ�˿�����
								</span>
							</td>
							<td align="center" colspan="6">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jtqk.jtrs}
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ͥ�����루Ԫ��
								</span>
							</td>
							<td align="center" colspan="8">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jtqk.jtnzsr}
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="center">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ͥסַ
								</span>
							</td>
							<td align="center" colspan="18">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jtqk.jtdz}
								</span>
							</td>
						</tr>
						<tr height="55px">
							<td align="center">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>�ۺ�<br/>����<br/>���</b>
								</span>
							</td>
							<td align="left" colspan="19">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<br/>
								&nbsp;&nbsp;���꼶רҵ��������<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��ѧϰ�ɼ�������<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>  ���ۺϿ���������<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��������Ϊ�գ�
								<br/><br/><br/>&nbsp;&nbsp;�������õ���������Ϊ&nbsp;&nbsp;����רҵ  �����꼶  �����༶
								<br/><br/>
								</span>
							</td>
						</tr>
						<tr height="55px">
							<td align="center">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>��<br/>��<br/>��<br/>��</b>
								</span>
							</td>
							<td align="left" colspan="19">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<br/>
								&nbsp;&nbsp;��Ҫ���<br/><br/><br/><br/><br/>
								&nbsp;&nbsp;���У�Ժ������<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�У������<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�ʡ�����Ͻ��� <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
								<br/><br/>
								</span>
							</td>
						</tr>
						<tr height="150px" align="center">
							<td>
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>����<br/>����<br/>��<br/>��ͥ<br/>���<br/>��<br/>��У<br/>����<br/>500<br/>��<br/>��<br/>��<br/>��<br/></b>
								</span>
							</td>
							<td colspan="19">
								<p style="height:140px" align="left">
									<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;${xmxx.sqly}
									</span>
								</p>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!--��ҳ-->
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
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>��<br/>��<br/>��<br/>��<br/>��<br/>��<br/>ͥ<br/>��<br/>��<br/>��<br/>��<br/>У<br/>��<br/>��<br/>500<br/>��<br/>��<br/>��<br/>��<br/></b>
								</span>
							</td>
							<td colspan="19">
								<p style="height:390px" align="left">
									<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;
									</span>
								</p>
								<div align="right">
									<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									������ǩ����
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/><br/>
									��&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>
									</span>
								</div>
							</td>
						</tr>
						<tr height="160px" align="center">
							<td colspan="8">
								<div align="left">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								 <br/>&nbsp;Ժ��ϵ�����
								</span>
								</div>
								<p style="height:150px" align="center">
									<br/><br/><br/><br/>
									<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									ͬ&nbsp;&nbsp;&nbsp;&nbsp;��
									</span>
								</p>
								<div align="right">
									<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									����&nbsp;&nbsp;&nbsp;&nbsp;�£�
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>
									ǩ����
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>
									��&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>
									</span>
								</div>
							</td>
							<td colspan="12">
								<div align="left">
								<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<br/>&nbsp;ѧУ���
								</span>
								</div>
								<p style="height:150px" align="center">
									<br/><br/><br/><br/>
									<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									ͬ&nbsp;&nbsp;&nbsp;&nbsp;��
									</span>
								</p>
								<div align="right">
									<br/><br/>
									<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									����&nbsp;&nbsp;&nbsp;&nbsp;�£�
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