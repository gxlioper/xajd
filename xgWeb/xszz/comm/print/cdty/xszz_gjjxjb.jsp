<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- ͷ�ļ� -->
<html:html>
<body>
		<!-- ��ӡ�ؼ�begin -->
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
						��&nbsp;${rs.xn }&nbsp;ѧ�꣩���ҽ�ѧ������������
					</h2>
					<br/>
					<div align="left">
						<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;&nbsp;<b>ѧУ��</b>${rs.xxmc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Ժϵ��</b>${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>ѧ�ţ�</b>${rs.xh }
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
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>����<br/>���</b>
								</span>
							</td>
							<td align="center">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								����
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xm }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								�Ա�
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xb }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��������
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.csrq }
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="center">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								������ò
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.zzmmmc }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								����
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.mzmc }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ѧʱ��
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.rxrq }
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="center">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								רҵ
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.zymc }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								ѧ��
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xz }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ϵ�绰
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.lxdh }
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="center"><span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>���֤��</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh0 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh1 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh2 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh3 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh4 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh5 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh6 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh7 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh8 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh9 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh10 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh11 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh12 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh13 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh14 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh15 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh16 }</span></td>
							<td align="center"><span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh17 }</span></td>
						</tr>
						<tr height="45px">
							<td align="center" rowspan="2">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>ѧϰ<br/>���</b>
								</span>
							</td>
							<td align="left" colspan="8">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								�ɼ�������<u>&nbsp;<logic:notEmpty name="rs" property="xnzypm">${rs.xnzypm}/${rs.zyzrs}</logic:notEmpty ><logic:empty name="rs" property="xnzypm">&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;</logic:empty></u>������/��������
								</span>
							</td>
							<td align="left" colspan="11">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								ʵ���ۺϿ����������ǡ������      						
								</span>
							</td>
						</tr>
						<tr height="45px">
							<td align="left" colspan="8">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								���޿�<u>&nbsp;&nbsp;&nbsp;${rs.bxkms}&nbsp;&nbsp;</u>�ţ����м�������<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.jgkms }</u>��
								</span>
							</td>
							<td align="left" colspan="11">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								���ǣ�������<u>&nbsp;<logic:notEmpty name="rs" property="zhzfpm">${rs.zhzfpm}/${rs.zrs}</logic:notEmpty ><logic:empty name="rs" property="zhzfpm">&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;</logic:empty>&nbsp;</u>������/��������
								</span>
							</td>
						</tr>
						<tr height="37px">
							<td align="center" rowspan="5">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>��ѧ<br/>�ڼ�<br/>��Ҫ<br/>��<br/>���</b>
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								����
								</span>
							</td>
							<td align="center" colspan="7">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��������
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								�佱��λ
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
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>����<br/>����<br/></b></span>
								<span style='font-size:10pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>(200��)
								</span>
							</td>
							<td colspan="19" align="left">
								<p style="height:250px">
									<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
									</span>
								</p>
								<div align="right">
									<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									������ǩ������ǩ����
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>
									��&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>
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
		<table width="100%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
					<table width="100%" class="printStyle">
						<tr style="height:320px" >
							<td width="10%" align="center">
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>�Ƽ�<br/>����</b>
								<br/></span>
								<span style='font-size:10pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>(100��)
								</span>
							</td>
							<td width="88%" align="left">
								<p style="height:230px">
									<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.tjly }
									</span>
								</p>
								<div align="right">
									<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									�Ƽ��ˣ�����Ա������Σ�ǩ����
									&nbsp;&nbsp;&nbsp;&nbsp;
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
						<tr style="height:320px" align="center">
							<td>
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>Ժ<br/><br/>��ϵ��<br/><br/>��<br/><br/>��</b>
								</span>
							</td>
							<td align="left">
								<p style="height:150px">
									<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										&nbsp;&nbsp;&nbsp;&nbsp;${rs.shzt1yj }
									</span>
								</p>
								<div align="right">
									<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
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
						<tr style="height:320px" align="center">
							<td>
								<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>ѧ<br/><br/>У<br/><br/>��<br/><br/>��<br/></b>
								</span>
							</td>
							<td>
								<p style="height:190px" align="left">
									<br/><br/><br/><br/>
									<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;�����󣬲���У�ڹ�ʾ<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�������գ������飬�ֱ�����׼��ͬѧ��ù��ҽ�ѧ��
									</span>
								</p>
								<div align="right">
									<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
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
					<div align="right">
						<br/>
						<span style='font-size:11.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						�Ʊ�ȫ��ѧ��������������&nbsp;2010���
						</span>
					</div>
				</td>
			</tr>
		</table>
		
	 <div align="" style='layout-grid:15.6pt;line-height:28.0pt;margin-left:20px'> <br/>
  <p align=center style='
text-align:center;
line-height:150%;layout-grid-mode:char;'><b><span style='font-size:16.0pt;line-height:
150%;font-family:����_GB2312;color:#333333'>�����ҽ�ѧ��������������д˵��</span></b></p> 

<span  style='text-indent:28.0pt;line-height:25.0pt;'><span
style='font-size:14.0pt;font-family:����_GB2312'>����У��ȫ��ѧ����������������վ</span><span
style='font-size:12.0pt;font-family:����_GB2312'>��<span lang=EN-US><a
href="http://www.xszz.cee.edu.cn/"><span style='font-family:"Times New Roman";
color:windowtext;text-decoration:none'>http://www.xszz.cee.edu.cn</span></a></span>��</span><span
style='font-size:14.0pt;font-family:����_GB2312'>���ػ�ӡ�����ҽ�ѧ����������������֯��Ա������д��</span></span><br/>
<span  style='text-indent:28.0pt;line-height:25.0pt;'><span
lang=EN-US style='font-size:14.0pt;font-family:����_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;1. </span><span
style='font-size:14.0pt;font-family:����_GB2312'>���Ϊһҳ���������棬������������ҳ����</span><span
style='font-size:14.0pt;font-family:����_GB2312'>�����дӦ���ּ���������Ϣ������</span><span
style='font-size:14.0pt;font-family:����_GB2312'>����Ϳ�����ݻ���ֿհ��</span></span><br/>
<span  style='text-indent:28.0pt;line-height:25.0pt;'><span
lang=EN-US style='font-size:14.0pt;font-family:����_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;2. </span><span
style='font-size:14.0pt;font-family:����_GB2312'>��������ѧ�����дΪ��������ʼ����ѧ�����һѧ�ꡣ��<span
lang=EN-US>2010</span>���＾ѧ�����Ӧ��д��<span lang=EN-US>2009</span>��<span
lang=EN-US>2010</span>ѧ�ꡱ���Դ����ơ�</span></span><br/>
<span  style='text-indent:28.0pt;line-height:25.0pt;'><span
lang=EN-US style='font-size:14.0pt;font-family:����_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;3. </span><span
style='font-size:14.0pt;font-family:����_GB2312'>����С�����������͡��������ɡ�����ѧ��������д���������������ѧУ�йز�����д��</span></span><br/>
<span  style='text-indent:28.0pt;line-height:25.0pt;'><span
lang=EN-US style='font-size:14.0pt;font-family:����_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;4. </span><span
style='font-size:14.0pt;font-family:����_GB2312'>�����ѧϰ�ɼ����ۺϿ����ɼ������ķ�Χ�ɸ���У����ȷ����ѧУ��Ժϵ���꼶��רҵ���༶�������ɣ�������ע����ѡ��Χ����������</span></span><br/>
<span  style='text-indent:28.0pt;line-height:25.0pt;'><span
lang=EN-US style='font-size:14.0pt;font-family:����_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;5. </span><span
style='font-size:14.0pt;font-family:����_GB2312'>����С��������ɡ�������дӦ��ȫ����ʵ��</span><span
style='font-size:14.0pt;font-family:����_GB2312'>�ܹ���ʵ��ӳѧ��ѧϰ</span><span
style='font-size:14.0pt;font-family:����_GB2312'>�ɼ�����</span><span
style='font-size:14.0pt;font-family:����_GB2312'>�����ʵ���������������ۺ����ʵȷ����ر�ͻ����</span><span
style='font-size:14.0pt;font-family:����_GB2312'>����������<span lang=EN-US>200</span>�����ҡ�</span></span><br/>
<span  style='text-indent:28.0pt;line-height:25.0pt;'><span
lang=EN-US style='font-size:14.0pt;font-family:����_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;6. </span><span
style='font-size:14.0pt;font-family:����_GB2312'>����С��Ƽ������������дӦ��������Ҫ������������<span
lang=EN-US>100</span>�����ҡ��Ƽ��˱���������ѧ���ĸ���Ա������Σ���������Ȩ�Ƽ���</span></span><br/>
<span style='text-indent:28.0pt;line-height:25.0pt;'><span
lang=EN-US style='font-size:14.0pt;font-family:����_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;7. </span><span
style='font-size:14.0pt;font-family:����_GB2312'>����������ѧУ�������ŵ�������Ƽ��˺�ѧУ��Ժϵ����ѧ���������쵼ͬ־����ǩ�������������˴�д�Ƽ������ǩ����</span></span><br/>
<span  style='text-indent:28.0pt;line-height:25.0pt;'><span
lang=EN-US style='font-size:14.0pt;font-family:����_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;8. </span><span
style='font-size:14.0pt;font-family:����_GB2312'>����С�ѧУ�����������Ӹ�ѧУ���¡�����Ժ��ϵ����ѧУ����Ӹ�Ժ��ϵ�����£�������Ժ��ϵ����ѧУ�������ڡ�Ժ��ϵ�����������˵��������з���ǩ��֮���������������Ա����ǩд��</span></span><br/>
<span  style='text-indent:28.0pt;line-height:25.0pt;'><span
lang=EN-US style='font-size:14.0pt;font-family:����_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;9. </span><span
style='font-size:14.0pt;font-family:����_GB2312'>����ϱ�һ��ʹ��ԭ��������ʹ�ø�ӡ����ѧ���ɼ�������֤���֤������ֻ�辭��ѧУ��飬��������͡��ϱ����Ͼ���������˻أ�����У������Ҫ����׼���浵���ϡ�</span></span><br/>

</div> 
	</html:form>
</body>
</html:html>