<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- ͷ�ļ� -->
<html:html>
<body>
	<html:form action="/typj" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
						<h2>
							<strong> ��ͨ���Ƹ�У���ߵ�ְҵѧУ������־��ѧ������� </strong>
						</h2>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<br />
					<br />
					&nbsp;
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr height="40px">
							<td align="center" rowspan="5" width="6%">��<br />��<br />��<br />��</td>
							<td width="13%" align="center">����</td>
							<td width="13%" align="center">${rs.xm }</td>
							<td width="10%" align="center">�Ա�</td>
							<td width="14%" align="center">${rs.xb }</td>
							<td width="10%" align="center">��������</td>
							<td width="16%" align="center">${stuMap.csrq }</td>
							<td width="18%" rowspan="4" align="center">
								<img
									src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" style="width:160;height:160" />
							</td>
						</tr>
						<tr height="40px">
							<td align="center">����</td>
							<td align="center">${stuMap.mzmc }</td>
							<td align="center">����<br />��ò</td>
							<td align="center">${stuMap.zzmmmc }</td>
							<td align="center">��ѧʱ��</td>
							<td align="center">${stuMap.rxrq }</td>
						</tr>
						<tr height="40px">
							<td align="center">���֤����</td>
							<td colspan="3" align="center">${stuMap.sfzh }</td>
							<td align="center">��ϵ�绰</td>
							<td align="center">${stuMap.lxdh }</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="6">
									${xxmc }
									��ѧ
									${rs.xymc }
									<logic:notPresent name="rs">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:notPresent>
									<bean:message key="lable.xb" />
									${rs.zymc }<logic:notPresent name="rs">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:notPresent>
									ϵ
									${rs.bjmc }<logic:notPresent name="rs">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:notPresent>
									��
							</td>
						</tr>
						<tr height="50px">
							<td colspan="2" align="center">������ֽ���</td>
							<td colspan="5">
								&nbsp;&nbsp;&nbsp;&nbsp;${rs.hjqk }
							</td>
						</tr>
						<tr height="50px">
							<td rowspan="3" align="center">��<br />ͥ<br />��<br />��<br />��<br />��</td>
							<td align="center">��ͥ����</td>
							<td colspan="4">
								<div align="center">
									<logic:notPresent name="rs">
										A��
									����&nbsp;&nbsp;&nbsp;&nbsp;
										B��
									ũ��
									</logic:notPresent>
									<logic:present name="rs">
										<logic:equal value="����" name="rs" property="jthk">
											A����
												����&nbsp;&nbsp;&nbsp;&nbsp;
												B��
												ũ��
										</logic:equal>
										<logic:equal value="ũ��" name="rs" property="jthk">
											A��
												����&nbsp;&nbsp;&nbsp;&nbsp;
												B����
												ũ��
										</logic:equal>
										<logic:equal value="" name="rs" property="jthk">
												A��
												����&nbsp;&nbsp;&nbsp;&nbsp;
												B��
												ũ��
										</logic:equal>
									</logic:present>
								</div>
							</td>
							<td align="center">��ͥ�˿�����</td>
							<td align="center">${rs.rkzs }</td>
						</tr>
						<tr height="50px">
							<td align="center">��ͥ��������
							</td>
							<td align="center">${rs.jtyzsr }</td>
							<td colspan="2" align="center" >�˾�������</td>
							<td align="center">${rs.rjsr }</td>
							<td align="center">������Դ</td>
							<td align="center">${rs.srly }</td>
						</tr>
						<tr height="50px">
							<td align="center">��ͥסַ</td>
							<td colspan="4">${rs.jtxxdz }</td>
							<td align="center">��������</td>
							<td align="center">${rs.yzbm }</td>
						</tr>
						<tr>
							<td colspan="8" >
								ѧϰ�ɼ�
								<div style="height:80px">
									<logic:present  name="cjxx">
										<table class="tbstyle" width="100%"  >
											<tr>
												<td align="center">ѧ��</td>
												<td align="center">�γ�����</td>
												<td align="center">�γ�����</td>
												<td align="center">�ɼ�</td>
												<td align="center">�����ɼ�</td>
											</tr>
											<logic:iterate id="s" name="cjxx">
												<tr>
													<td align="center">${s.xn }</td>
													<td align="center">${s.kcmc }</td>
													<td align="center">${s.kcxz }</td>
													<td align="center">${s.cj }</td>
													<td align="center">${s.bkcj }</td>
												</tr>
											</logic:iterate>
										</table>
									</logic:present>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								��������
								<p style="height:120px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
								</p>
								<div align="right">
									������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								ѧУ��������
								<p style="height:140px">
								&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxyj }
								</p>
								<div align="right">
									�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
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
