<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- ͷ�ļ� -->
<html:html>
<body>
	<html:form action="/typj" method="post">
		<table width="95%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
					<font size="5">
						<strong>���ҽ�ѧ������������</strong>
					</font>
					<font size="4">(&nbsp;&nbsp;&nbsp;&nbsp;${rs.xn }&nbsp;&nbsp;&nbsp;&nbsp;ѧ��)</font>
				</td>
			</tr>
			<tr>
				<td align="left">
						<strong>ѧУ��${xxmc }
						Ժϵ��${rs.xymc }<logic:notPresent name="rs">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:notPresent>
						רҵ��${rs.zymc }<logic:notPresent name="rs">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:notPresent>
						�༶��${rs.bjmc }<logic:notPresent name="rs">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</strong>											 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:notPresent>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="7%"></td>
							<td width="10%"></td>
							<td width="4"></td>
							<td width="5%"></td>
							<td width="4%"></td>
							<td width="5%"></td>
							<td width="4%"></td>
							<td width="5%"></td>
							<td width="4%"></td> 
							<td width="5%"></td>
							<td width="4%"></td>
							<td width="5%"></td>
							<td width="4%"></td>
							<td width="5%"></td>
							<td width="4%"></td>
							<td width="5%"></td>
							<td width="4%"></td>
							<td width="5%"></td>
							<td width="4%"></td>
							<td width="5%"></td>
						</tr>
						<tr height="50px">
							<td align="center" rowspan="4"><strong>��<br/>��<br/>��<br/>��</strong></td>
							<td align="center">����</td>
							<td align="center" colspan="3">${rs.xm }</td>
							<td align="center" colspan="4">�Ա�</td>
							<td align="center" colspan="3">${rs.xb }</td>
							<td align="center" colspan="4">��������</td>
							<td align="center" colspan="4">${stuMap.csrq }</td>
						</tr>
						<tr height="50px">
							<td align="center">ѧ��</td>
							<td align="center" colspan="3">${rs.xh }</td>
							<td align="center" colspan="4">����</td>
							<td align="center" colspan="3">${stuMap.mzmc }</td>
							<td align="center" colspan="4">��ѧʱ��</td>
							<td align="center" colspan="4">${stuMap.rxrq }</td>
						</tr>
						<tr height="50px">
							<td align="center">���֤��</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr height="50px">
							<td align="center">������ò</td>
							<td align="center" colspan="3">${stuMap.zzmmmc }</td>
							<td align="center" colspan="7">
								��ϵ�绰
							</td>
							<td align="center" colspan="8">
								${stuMap.lxdh }
							</td>
						</tr>
						<tr height="150px">
							<td align="center"><strong>ѧ<br/>ϰ<br/>��<br/>��<br/>��</strong></td>
							<td colspan="19">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ѧ����޿γ���<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�ţ�
								���У�����<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�ţ�
								����<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��<br/><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ɼ�������רҵ���꼶����<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>������/��������<br/><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ۺϿ����ɼ�<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�֣�
								����<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>������/�������������޴����д���ޡ���
							</td>
						</tr>
						<tr height="150px">
							<td align="center"><strong>��<br/>��<br/>��<br/>��</strong></td>
							<td colspan="19">
								<p style="height:80px">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��Ҫ����:
								</p>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								���У�Ժ������<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    У������<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    ʡ�����Ͻ���<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
							</td>
						</tr>
						<tr height="400px">
							<td align="center">
							<br/><strong>��<br/>��<br/>��<br/>��<br/>��<br/>ȫ<br/>��<br/>��<br/>ӳ<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��</strong><br/>
							</td>
							<td colspan="19">
								
							</td>
						</tr>
					</table>
					<br/><br/>
					<table  width="100%" class="tbstyle">
						<tr>
							<Td width="7%"></Td>
							<Td>
								<p style="height:180px"/>
								<div align="right">
									������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>			
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								
							</Td>
						</tr>
						<tr>
							<Td align="center"><strong>��&nbsp;&nbsp;��<br/>(רҵ)<br/>��&nbsp;&nbsp;��<br/>��&nbsp;&nbsp;��</strong></Td>
							<Td>
								<p style="height:180px"/>
								<div align="right">
									�Ƽ��ˣ�
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									����ְ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>			
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								
							</Td>
						</tr>
						<tr>
							<Td align="center"><strong><br/>Ժ<br/><br/>��ϵ��<br/><br/>��<br/><br/>��<br/></strong></Td>
							<Td>
								<p style="height:180px"/>
								<div align="right">
									    �����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>			
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								
							</Td>
						</tr>
						<tr>
							<Td align="center"><strong><br/>ѧ<br/><br/>У<br/><br/>��<br/><br/>��<br/></strong></Td>
							<Td>
								<p style="height:180px">
									<br/><br/>
									&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;�����󣬲���<u>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
									��Χ�ڹ�ʾ<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</u>�죬�����飬�ֱ���<br/><br/><br/>
									ͬ���ͬѧ���<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</u>ѧ��ȹ��ҽ�ѧ��
								</p>
								<div align="right">
									    �����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>			
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								
							</Td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
