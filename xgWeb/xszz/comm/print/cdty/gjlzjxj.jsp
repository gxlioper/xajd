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
		<table width="100%" border="0" id="theTable" align="center" >
			<tr>
				<td align="center">
					<h2>
						������־��ѧ������������&nbsp;${rs.xn }&nbsp;ѧ�꣩
					</h2>
					<br/>
					<div align="left">
						<span style='font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						&nbsp;&nbsp;<b>ѧУ��</b>${rs.xxmc }&nbsp;&nbsp;<b>Ժϵ��</b>${rs.xymc }&nbsp;&nbsp;<b>רҵ��</b>${rs.zymc }&nbsp;&nbsp;<b>�꼶��</b>${rs.nj }&nbsp;&nbsp;<b>�༶��</b>${rs.bjmc }
						</span>
					</div>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="printStyle">
						<tr>
							<td width="8%"></td>
							<td width="14%"></td>
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
						<tr height="41px">
							<td align="center" rowspan="4">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>��<br/>��<br/>��<br/>��</b>
								</span>
							</td>
							<td align="center">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								����
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xm }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								�Ա�
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xb }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��������
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.csrq }
								</span>
							</td>
						</tr>
						<tr height="41px">
							<td align="center">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								ѧ��
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xh }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								����
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.mzmc }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ѧʱ��
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.rxrq }
								</span>
							</td>
						</tr>
						<tr height="41px">
							<td align="center"><span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>���֤��</span></td>
							<td align="center"><span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh0 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh1 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh2 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh3 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh4 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh5 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh6 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh7 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh8 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh9 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh10 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh11 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh12 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh13 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh14 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh15 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh16 }</span></td>
							<td align="center"><span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${rs.sfzh17 }</span></td>
						</tr>
						<tr height="41px">
							<td align="center">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								������ò
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.zzmmmc }
								</span>
							</td>
							<td align="center" colspan="6">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ϵ�绰
								</span>
							</td>
							<td align="center" colspan="8">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.lxdh }
								</span>
							</td>
						</tr>
						<tr height="56px">
							<td align="center" rowspan="3">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>��<br/>ͥ<br/>��<br/>��<br/>��<br/>��</b>
								</span>
							</td>
							<td align="center">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ͥ����
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<logic:present name="rs" property="jthk">
									<logic:equal value="����" property="jthk" name="rs">
										����
									</logic:equal>
									<logic:equal value="ũ��" property="jthk" name="rs">
										ũ��
									</logic:equal>
									</logic:present>
									<logic:notPresent name="rs" property="jthk">
										A������ B��ũ��
									</logic:notPresent>	
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ͥ�˿�����
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtrs}
								</span>
							</td>
						</tr>
						<tr height="56px">
							<td align="center">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ͥ������
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtyzsr }
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								�˾�������
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtrjysr}
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								������Դ
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.srly }
								</span>
							</td>
						</tr>
						<tr height="56px">
							<td align="center">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ͥסַ
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtdz }
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��������
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtyb }
								</span>
							</td>
						</tr>
						<tr height="115px">
							<td align="center">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>ѧ<br/>ϰ<br/>��<br/>��<br/>��</b>
								</span>
							</td>
							<td colspan="19"> 
							<p>
							<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							��ѧ����޿γ���<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�ţ����У�����<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�ţ�����<u>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;</u>��<br/>
							</span>
							</p>
							<p> <span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							�ɼ�������רҵ���꼶����<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>������/��������<br/>
							</span></p>
							<p><span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						          �ۺϿ����ɼ�<u>&nbsp;&nbsp;&nbsp;&nbsp; </u>�֣�����<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>������/�������������޴����д���ޡ���
						    </span></p>
						     </td> 
						</tr>
						<tr height="168px"> 
					      <th> <span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>��<br/>��<br/>��<br/>��</b>
								</span>
					      </th> 
					      <td colspan="19"> 
							<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
							<p>��Ҫ���</p> 
							${rs.hjqk}
							</span>
							<br/>
							<br/>
							<br/>
							<br/>
							<p>
							<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
					        	���У�Ժ������<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.yjjlcs} </u>�&nbsp;&nbsp;У������<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${rs.xjjlcs}</u>�&nbsp;&nbsp;ʡ�����Ͻ���<u>&nbsp;&nbsp;&nbsp;&nbsp; ${rs.sjjlcs}</u>��
					        </span>
					        </p>
					       </td> 
					    </tr> 
						<tr height="168px" align="center">
							<td>
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>��<br/>��<br/>��<br/>��<br/>��</br>ȫ<br/>��</b>
								</span>
							</td>
							<td colspan="19" align="left">
								<p style="height:145px">
									<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
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
		<table width="100%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
					<table width="100%" class="printStyle">
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
						<tr align="center">
							<td style="height:230px">
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>��<br/>ӳ<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��</b>
								</span>
							</td>
							<td colspan="19">
								<p style="height:180px">
									<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									
									</span>
								</p>
								<div align="right">
									<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									������ǩ����
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
						<tr align="center">
							<td>
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>��&nbsp;��<br/><br/>(רҵ)<br/><br/>��&nbsp;��<br/><br/>��&nbsp;��</b>
								</span>
							</td>
							<td colspan="19" align="left">
								<p style="height:112px">
									<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.shzt1yj }
									</span>
								</p>
								<div align="left">
									<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;�Ƽ��ˣ�
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									����ְ��
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</span>
								</div>
								<br/>
								<div align="right">
									<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									��&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>
									</span>
								</div>
							</td>
						</tr>
						<tr align="center">
							<td>
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>Ժ<br/><br/>��ϵ��<br/><br/>��<br/><br/>��</b>
								</span>
							</td>
							<td colspan="19" align="left">
								<p style="height:110px">
									<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.shzt2yj }
									</span>
								</p>
								<div align="right">
									<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									����&nbsp;&nbsp;�£�
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
						<tr align="center">
							<td>
								<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>ѧ<br/><br/>У<br/><br/>��<br/><br/>��<br/></b>
								</span>
							</td>
							<td colspan="19">
								<p style="height:115px" align="left">
									<br/><br/>
									<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;&nbsp;&nbsp;&nbsp;�����󣬲���<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��Χ�ڹ�ʾ<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�죬�����飬�ֱ���ͬ���ͬѧ���<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>ѧ��ȹ�����־��ѧ��
									</span>
								</p>
								<div align="right">
									<span style='font-size:12pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									����&nbsp;&nbsp;�£�
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
</body>
</html:html>