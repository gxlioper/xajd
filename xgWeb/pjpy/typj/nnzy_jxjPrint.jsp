<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- ͷ�ļ� -->
<html:html>
<body>
	<html:form action="/typj" method="post">
		<table width="95%" border="0" id="theTable" align="center">
			<tr>
				<td align="left">
					<h3>
						����1
					</h3>
				</td>
			</tr>
			<tr>
				<td align="center">
					<h2>
						${rs.jxjmc }�����
					</h2>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr height="40px">
							<td rowspan="5" align="center" style="width:25px">����<br/>���</td>
							<td align="center">����</td>
							<td align="center" colspan="2"  width="70px">${rs.xm }</td>
							<td align="center" >�Ա�</td>
							<td align="center" colspan="2">${rs.xb }</td>
							<td align="center" colspan="2" style="width:80px">��������</td>
							<td align="center">${stuMap.csrq }</td>
							<td rowspan="4" align="center" style="width:110px">
								<img
									src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" style="width:140;height:160" />
							</td>
						</tr>
						<tr height="40px">
							<td align="center" >����</td>
							<td align="center" colspan="2">${stuMap.mzmc }</td>
							<td align="center" >������ò</td>
							<td align="center" colspan="2">${stuMap.zzmmmc }</td>
							<td align="center" colspan="2" style="width:80px">��ѧʱ��</td>
							<td align="center">${stuMap.rxrq }</td>
							
						</tr>
						<tr height="40px">
							<td align="center">���֤��</td>
							<td align="center" colspan="4">${stuMap.sfzh }</td>
							<td align="center" colspan="2">��ϵ�绰</td>
							<td align="center" colspan="2">${stuMap.lxdh }</td>
						</tr>
						<tr height="40px">
							<td colspan="9" align="center">
							&nbsp;&nbsp;&nbsp;&nbsp;${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp; <bean:message key="lable.xb" />��������
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							${rs.bjmc } &nbsp;&nbsp;&nbsp;&nbsp;��
							</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">������ֽ���</td>
							<td colspan="8" >
								&nbsp;&nbsp;&nbsp;&nbsp;${rs.hjqk }
							</td>
						</tr>
						<tr height="40px">
							<td align="center" rowspan="3" style="width:25px">��ͥ<br/>����<br/>���</td>
							<td align="center" colspan="2">
								��ͥ����
							</td>
							<td colspan="6" align="center">
								${rs.jthk }
							</td>
							<td align="center" >
								��ͥ���˿���
							</td>
							<td align="center">
								${rs.rkzs }
							</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">
								��ͥ������
							</td>
							<td colspan="2" align="center">
								${rs.jtyzsr }
							</td>
							<td align="center" colspan="2">
								�˾�����
							</td>
							<td colspan="2" align="center">
								${rs.rjsr }
							</td>
							<td align="center" >
								������Դ
							</td>
							<td align="center">
								${rs.srly }
							</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">
								��ͥ��ϸסַ
							</td>
							<td colspan="6" align="center">
								${rs.jtxxdz }
							</td>
							<td align="center" >
								��������
							</td>
							<td align="center">
								${rs.yzbm }
							</td>
						</tr>
						<tr>
							<td colspan="11">
								<p style="height:140px">ѧϰ�ɼ���
									<logic:present  name="cjxx">
										<table class="tbstyle" width="100%"  >
											<tr>
												<td>ѧ��</td>
												<td>�γ�����</td>
												<td>�γ�����</td>
												<td>�ɼ�</td>
												<td>�����ɼ�</td>
											</tr>
											<logic:iterate id="s" name="cjxx">
												<tr>
													<logic:iterate id="v" name="s" offset="2">
														<td>
															<bean:write name="v"/>
														</td>
													</logic:iterate>
												</tr>
											</logic:iterate>
										</table>
									</logic:present>
								</p>
							</td>
						</tr>
						<tr>
							<td colspan="11">
								<p style="height:90px">�������ɣ�
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
									<div align="right">
										������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									    ������&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
									</div>
								</p>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<p style="height:90px">
									��ί���������
									<br/><br/><br/><br/>
									<div align="right">
										ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<br/>
										 ������&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
									</div>
								</p>
							</td>
							<td colspan="5">
								<p style="height:90px">
									����Ա�����
									${rs.fdyyj }
	
									<div align="right" style="margin-bottom: 0px">
										ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<br/>
										  ������&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
									</div>
								</p>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<p style="height:90px">
									����<bean:message key="lable.xb" />��������
									${rs.xyyj }
									<div align="right" style="margin-bottom: 0px">
										������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<br/>
										 ������&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
									</div>
								</p>
							</td>
							<td colspan="5">
								<p style="height:90px">
									<bean:message key="lable.xb" />��������������ˣ�
									<br/><br/><br/><br/>
									<div align="right">
										�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<br/>
										  ������&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
									</div>
								</p>
							</td>
						</tr>
						<tr>
							<td colspan="11">
								<p style="height:100px">
									ѧУ������
									<br/><br/><br/><br/>
									<div align="right">
										�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<br/>
										 ������&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
									</div>
								</p>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
