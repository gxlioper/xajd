<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- ͷ�ļ� -->
<html:html>
<body>
	<html:form action="/typj" method="post">
		<table width="95%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
					<h2>
						������������չ�ɾͽ�ѧ��������
					</h2>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="5%"></td>
							<td width="5%"></td>
							<td width="10%"></td>
							<td width="5%"></td>
							<td width="7%"></td>
							<td width="7%"></td>
							<td width="10%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="8%"></td>
							<td width="3%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="12%"></td>
						</tr>
						<tr height="40px">
							<td colspan="2" align="center">����</td>
							<td align="center">
								${rs.xm }
							</td>
							<td colspan="2" align="center">ѧ��</td>
							<td colspan="2" align="center">
								${rs.xh }
							</td>
							<td colspan="2" align="center">�Ա�</td>
							<td colspan="2" align="center">
								${rs.xb }
							</td>
							<td align="center"><bean:message key="lable.xb" />��<br/>�༶</td>
							<td colspan="2" align="center">
								${rs.xymc }<br/>${rs.bjmc }
							</td>
						</tr>
						<tr height="40px">
							<td rowspan="4" align="center">
								��<br/>��<br/>��<br/>��
							</td>
							<td colspan="3">
								<logic:present property="hjdj" name="rs">
									&nbsp;&nbsp;&nbsp;&nbsp;�̾���
								</logic:present>
								<logic:notPresent property="hjdj" name="rs">
									&nbsp;&nbsp;&nbsp;&nbsp;������
								</logic:notPresent>
								
							</td>
							<td colspan="2" align="center">
								�񽱵ȼ�
							</td>
							<td colspan="2" align="center">
								${rs.hjdj }
							</td>
							<td colspan="2" align="center">
								������<br/>�쵥λ
							</td>
							<td colspan="2" align="center">
								${rs.zbdw }
							</td>
							<td align="center">
								֤���<br/>����λ
							</td>
							<td align="center">
								${rs.bfdw }
							</td>
						</tr>
						<tr height="40px">
							<td colspan="3" >
								<logic:present property="kwmckh" name="rs">
									&nbsp;&nbsp;&nbsp;&nbsp;������/����
								</logic:present>
								<logic:notPresent property="kwmckh" name="rs">
									&nbsp;&nbsp;&nbsp;&nbsp;������/����
								</logic:notPresent>
							</td>
							<td colspan="2" align="center">
								��������<br/>������
							</td>
							<td colspan="2" align="center">
								${rs.kwmckh }
							</td>
							<td colspan="2" align="center">
								����<br/>����
							</td>
							<td colspan="2" align="center">
								${rs.zzpm }
							</td>
							<td align="center">
								����<br/>ʱ��
							</td>
							<td align="center">
								${rs.fbsj }
							</td>
						</tr>
						<tr height="40px">
							<td colspan="3" >
								<logic:present property="cg" name="rs">
									&nbsp;&nbsp;&nbsp;&nbsp;��ר��������
								</logic:present>
								<logic:notPresent property="cg" name="rs">
									&nbsp;&nbsp;&nbsp;&nbsp;��ר��������
								</logic:notPresent>
							</td>
							<td colspan="2" align="center">
								�ɹ�
							</td>
							<td colspan="3" align="center">
								${rs.cg }
							</td>
							<td colspan="3" align="center">�ɹ�����</td>
							<td colspan="2" align="center">
								${rs.cgjb }
							</td>
						</tr>
						<tr height="60px">
							<td colspan="3" >
								<logic:present property="zysj" name="rs">
									&nbsp;&nbsp;&nbsp;&nbsp;������
								</logic:present>
								<logic:notPresent property="zysj" name="rs">
									&nbsp;&nbsp;&nbsp;&nbsp;������
								</logic:notPresent>
								
							</td>
							<td colspan="2" align="center">
								��Ҫ�¼�
							</td>
							<td colspan="8">
								<p style="height:55px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.zysj }
								</p>
							</td>
						</tr>
						<tr>
							<td colspan="14">
								<p style="height:160px">
									�����루�ۺϱ��֣���
								</p>
								<div align="right">
									ѧ��ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="7">
								<p style="height:160px">
									��֯����������������ࣩ��
								</p>
								<div>
									��֯��ǩ����<br/>
									��֯���Ÿ��£�
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;
								</div>
							</td>
							<td colspan="7">
								<p style="height:160px">
									ѧ������<bean:message key="lable.xb" />������:${rs.xyyj }
								</p>
								<div>
									Ժ�쵼ǩ��<br/>
									<bean:message key="lable.xb" />����
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="14">
								<p style="height:160px">
									ѧУ��������${rs.xxyj }
								</p>
								<div align="right">
									�����£�
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
					</table>
					<div align="left">��������֤�����ϸ�ԭ������ӡ��һ��</div>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
