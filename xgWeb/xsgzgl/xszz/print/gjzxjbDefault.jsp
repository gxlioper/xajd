<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/head.ini"%>
<title>�ߵ�ѧУ������ѧ������������</title>
<style>
.font_style td{font-size:14px;font-family:����; }
</style>

</head>

<body lang=ZH-CN style='text-justify-trim:punctuation'>

<div align=center>
<html:form action="/xszz_sqsh" method="post" styleId="xszzSqshForm">
	<table width="652px" align="center" border="0">
			<tr>
				<td align="center">
				<b>
					<span style='font-size:16.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<br/><br/>
						<logic:empty name="xmxx" property="xn">��&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;ѧ�꣩</logic:empty><logic:notEmpty name="xmxx" property="xn">��${xmxx.xn }ѧ�꣩</logic:notEmpty>������ѧ������������
					</span>
				</b>
				<br/><br/><br/>
				<div align="left">
					<span style='font-size:11.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
					<b>ѧУ��</b>${xxmc}&nbsp;&nbsp;<b>Ժϵ��</b>${jbxx.xymc }&nbsp;&nbsp;<b>רҵ��</b>${jbxx.zymc }&nbsp;&nbsp;<b>�༶��</b>${jbxx.bjmc }
					</span>
				</div>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="font_style" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" style='border-collapse:collapse;border:none;'>
						<tr>
							<td width="5%"></td>
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
						<tr height="30px">
							<td align="center" rowspan="4">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>��<br/>��<br/>��<br/>��</b>
								</span>
							</td>
							<td align="center">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								����
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.xm }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								�Ա�
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.xb }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��������
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.csrq }
								</span>
							</td>
						</tr>
						<tr height="30px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								ѧ��
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.xh }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								����
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.mzmc }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ѧʱ��
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.rxrq }
								</span>
							</td>
						</tr>
						<tr height="30px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								������ò
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.zzmmmc }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								ѧ��
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.xz }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ϵ�绰
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jbxx.sjhm }
								</span>
							</td>
						</tr>
						<tr height="30px">
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>���֤��</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh0 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh1 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh2 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh3 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh4 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh5 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh6 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh7 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh8 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh9 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh10 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh11 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh12 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh13 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh14 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh15 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh16 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${jbxx.sfzh17 }</span></td>
						</tr>
						<tr height="30px">
							<td align="center" rowspan="4">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>��<br/>ͥ<br/>��<br/>��<br/>��<br/>��</b>
								</span>
							</td>
							<td align="center">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ͥ����
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
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
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								������Դ
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jtqk.jtsrly}
								</span>
							</td>
						</tr>
						<tr height="30px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ͥ��������
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jtqk.jtrjysr}
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ͥ�˿�����
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jtqk.jtrs}
								</span>
							</td>
						</tr>
						<tr height="30px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ͥסַ
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jtqk.jtdz}
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��������
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${jtqk.jtyb}
								</span>
							</td>
						</tr>
						<tr height="30px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								�϶����
								</span>
							</td>
							<td align="center" colspan="18">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								  <logic:present name="knsdc">
								  <logic:iterate id="j" name="knsdc" indexId="i">
								  	<logic:equal name="i"  value="0">A��</logic:equal>
								  	<logic:equal name="i"  value="1">B��</logic:equal>
								  	<logic:equal name="i"  value="2">C��</logic:equal>
								  	<logic:equal name="i"  value="3">D��</logic:equal>
								  	<logic:equal name="i"  value="4">E��</logic:equal>
								  	<logic:equal name="i"  value="5">F��</logic:equal>
								  	<logic:equal name="i"  value="6">G��</logic:equal>
								  	<logic:equal name="i"  value="7">H��</logic:equal>
								  	<logic:equal name="i"  value="8">I��</logic:equal>
								  	<logic:equal name="i"  value="9">J��</logic:equal>
								  	<logic:equal name="i"  value="10">K��</logic:equal>
								  	<logic:equal name="i"  value="11">L��</logic:equal>
								  	<logic:equal name="i"  value="12">M��</logic:equal>
								  	<logic:equal name="i"  value="13">N��</logic:equal>
								  	<logic:equal name="i"  value="14">O��</logic:equal>
								  	${j.dcmc }<logic:equal name="j" property="dcdm" value="${rddc }">��&nbsp;��&nbsp;��</logic:equal><logic:notEqual name="j" property="dcdm" value="${rddc }">��&nbsp;&nbsp;&nbsp;&nbsp;��</logic:notEqual>
								  	&nbsp;&nbsp;&nbsp;&nbsp;
								  </logic:iterate>
								  </logic:present>
								</span>
							</td>
						</tr>
						<tr style="height:33px">
							<td align="center" rowspan="${cysize+1 }">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<b>
									��<br/>
									ͥ<br/>
									��<br/>
									Ա<br/>
									��<br/>
									��
									</b>
								</span>
							</td>
							<td align="center">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									����
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									����
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									�뱾�˹�ϵ
								</span>
							</td>
							<td align="center" colspan="12">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									������ѧϰ��λ
								</span>
							</td>
						</tr>
						<logic:iterate name="cyList" id="cy">
							<tr style="height:30px">
								<td align="center" >
										${cy.cyxm }&nbsp;&nbsp;
								</td>
								<td align="center"  colspan="3">
										${cy.cynl }&nbsp;&nbsp;
								</td>
								<td align="center" colspan="3">
										${cy.cygxmc }&nbsp;&nbsp;
								</td>
								<td align="center"colspan="12">
										${cy.cyxxdw }&nbsp;&nbsp;
								</td>
							</tr>
						</logic:iterate>
						<logic:iterate name="blankList" id="blank">
							<tr style="height:30px">
								<td align="center" >
										&nbsp;&nbsp;
								</td>
								<td align="center"  colspan="3">
										&nbsp;&nbsp;
								</td>
								<td align="center" colspan="3">
										&nbsp;&nbsp;
								</td>
								<td align="center"colspan="12">
										&nbsp;&nbsp;
								</td>
							</tr>
						</logic:iterate>
						<tr height="120px" align="center">
							<td>
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>��<br/>��<br/>��<br/>��</b>
								</span>
							</td>
							<td colspan="19">
								<p style="height:70px" align="left">
									<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;${xmxx.sqly }
									</span>
								</p>
								<div align="right">
									<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									������ǩ������ǩ����
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;
									��
									</span>
								</div>
							</td>
						</tr>
						<tr height="70px" align="center">
							<td>
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>Ժ<br/>��ϵ��<br/>��<br/>��</b>
								</span>
							</td>
							<td colspan="19">
								<p style="height:60px" align="left">
									<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;
									</span>
								</p>
								<div align="right">
									<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									��Ժϵ���£�
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;
									��
									</span>
								</div>
							</td>
						</tr>
						<tr height="100px" align="center">
							<td>
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>ѧ<br/>У<br/>��<br/>��</b>
								</span>
							</td>
							<td colspan="19">
								<p style="height:60px" align="left">
									<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;
									</span>
								</p>
								<div align="right">
									<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									��ѧУ���£�
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;
									��
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