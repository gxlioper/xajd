<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- ͷ�ļ� -->
<html:html>
<body>
	<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->
	<html:form action="/typj" method="post">
		<table width="85%" border="0" id="printstyle" align="center">
			<tr>
				<td align="center">
					<b>
					<span style='font-size:16.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<br/><br/>
						��${rs.xn }ѧ�꣩������ѧ������������
					</span>
					</b>
					<br/><br/><br/>
					<div align="left">
						<span style='font-size:11.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						<b>ѧУ��</b>${rs.xxmc }&nbsp;&nbsp;<b>Ժϵ��</b>${rs.xymc }&nbsp;&nbsp;<b>רҵ��</b>${rs.zymc }&nbsp;&nbsp;<b>�༶��</b>${rs.bjmc }
						</span>
					</div>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="printstyle">
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
								${rs.xm }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								�Ա�
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xb }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��������
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.csrq }
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
								${rs.xh }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								����
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.mzmc }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ѧʱ��
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.rxrq }
								</span>
							</td>
						</tr>
						<tr height="30px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								������ò
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.zzmmmc }
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ϵ�绰
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.sjhm }
								</span>
							</td>
						</tr>
						<tr height="30px">
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>���֤��</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh0 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh1 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh2 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh3 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh4 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh5 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh6 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh7 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh8 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh9 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh10 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh11 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh12 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh13 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh14 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh15 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh16 }</span></td>
							<td align="center"><span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh17 }</span></td>
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
								<logic:present name="rs" property="jthk">
									<logic:equal value="����" property="jthk" name="rs">
										<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										����
										</span>
									</logic:equal>
									<logic:equal value="ũ��" property="jthk" name="rs">
										<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										ũ��
										</span>
									</logic:equal>
								</logic:present>
								<logic:notPresent name="rs" property="jthk">
									<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									A������ B��ũ��
									</span>
								</logic:notPresent>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								������Դ
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.srly }
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
								${rs.jtyzsr }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ͥ�˿�����
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtrs }
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
								${rs.jtdz }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��������
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtyb }
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
								<logic:present name="rs" property="knjb">
									<logic:equal value="һ������" property="knjb" name="rs">
										<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										��ͥ����һ������
										</span>
									</logic:equal>
									<logic:equal value="��������" property="knjb" name="rs">
										<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										��ͥ�����ر�����
										</span>
									</logic:equal>
									<logic:equal value="" property="knjb" name="rs">
										<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										A����ͥ�����ر����� B����ͥ����һ������
										</span>
									</logic:equal>
								</logic:present>
								<logic:notPresent name="rs" property="knjb">
									<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									A����ͥ�����ر����� B����ͥ����һ������
									</span>
								</logic:notPresent>
							</td>
						</tr>
						<tr style="height:33px">
							<td align="center" rowspan="${cyNum+1 }">
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
							<tr height="25px">
								<td align="center">
									<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										${cy.cyxm }&nbsp;&nbsp;
									</span>
								</td>
								<td align="center" colspan="3">
									<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										${cy.cynl }&nbsp;&nbsp;
									</span>
								</td>
								<td align="center" colspan="3">
									<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										${cy.mc }&nbsp;&nbsp;
									</span>
								</td>
								<td align="center" colspan="12">
									<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										${cy.cygzdw }&nbsp;&nbsp;
									</span>
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
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
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
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
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
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxshyj }
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
					<div align="right">
						<br/>
						<span style='font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						�Ʊ��㽭ʡѧ��������������&nbsp;2010���
						</span>
					</div>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center" class='noPrin'>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			ҳ������
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			��ӡԤ��
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			ֱ�Ӵ�ӡ
		</button>
	</div>
</body>
</html:html>
