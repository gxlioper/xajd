<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	</head>
	<body>



		<html:form action="/data_search" method="post">
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
				    alert("�������ѧ����Ч!");
				    </script>
				</logic:equal>
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />



				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>���ٽ���</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button type="button"
											onclick="refreshForm('/xgxt/SaveStuTrackTeach.do');alert('����ɹ���');Close();window.dialogArguments.document.getElementById('search_go').click();"
											id="buttonSave">
											�� ��
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" onclick="Close();return false;" id="buttonClose">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th width="16%">
									ѧ��
								</th>
								<td width="34%">
									<bean:write name="rs" property="xh" scope="request" />
								</td>
								<th width="16%">
									���
								</th>
								<td width="34%">
									<bean:write name="rs" property="nd" scope="request" />
								</td>
							</tr>
							<tr>
								<th>
									����
								</th>
								<td>
									<bean:write name="rs" property="xm" scope="request" />
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<bean:write name="rs" property="xn" scope="request" />
								</td>
							</tr>
							<tr>
								<th>
									�Ա�
								</th>
								<td>
									<bean:write name="rs" property="xb" scope="request" />
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<bean:write name="rs" property="xq" scope="request" />
								</td>
							</tr>
							<tr>
								<th>
									�꼶
								</th>
								<td>
									<bean:write name="rs" property="nj" scope="request" />
								</td>
								<th>
									�������
								</th>
								<td>
									<bean:write name="rs" property="cflb" scope="request" />
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<bean:write name="rs" property="xymc" scope="request" />
								</td>
								<th>
									��������
								</th>
								<td>
									<bean:write name="rs" property="cfyy" scope="request" />
								</td>
							</tr>
							<tr>
								<th>
									רҵ
								</th>
								<td>
									<bean:write name="rs" property="zymc" scope="request" />
								</td>
								<th>
									<font color="red">*</font>����ʱ��
								</th>
								<td>
									<bean:write name="rs" property="cfsj" scope="request" />
								</td>
							</tr>
							<tr>
								<th>
									�༶
								</th>
								<td>
									<bean:write name="rs" property="bjmc" scope="request" />
								</td>
								<th>
									<font color="red">*</font>�����ĺ�
								</th>
								<td>
									<bean:write name="rs" property="cfwh" scope="request" />
								</td>
							</tr>
						</tbody>
						<thead>
							<tr>
								<td colspan="4">
									<span>�������ټ�¼ &nbsp;&nbsp;<a
										onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none'">�鿴</a>
									</span>
								</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4">
									<div id="child2" style="display:none">
										<table width="100%" border="1" align="center" class="tbstyle">
											<thead>
												<tr>
													<td>
														<div align="center" class="style2">
															��һ���Ƚ��������¼
															<br />
															<font color="red">(������800������)</font>
														</div>
													</td>
												</tr>
											</thead>
											<tr>
												<td align="center">
													<html:textarea name='rs' property='xsbx1'
														style="width:500px" rows='6' onblur="checkLen(this,800)" />
												</td>
											</tr>
										</table>
										<table width="100%" border="1" align="center" class="tbstyle">
											<thead>
												<tr>
													<td>
														<div align="center" class="style2">
															�ڶ����Ƚ��������¼
															<br />
															<font color="red">(������800������)</font>
														</div>
													</td>
												</tr>
											</thead>
											<tr>
												<td align="center">
													<html:textarea name='rs' property='xsbx2'
														style="width:500px" rows='6' onblur="checkLen(this,800)" />
												</td>
											</tr>
										</table>
										<table width="100%" border="1" align="center" class="tbstyle">
											<thead>
												<tr>
													<td>
														<div align="center" class="style2">
															�������Ƚ��������¼
															<br />
															<font color="red">(������800������)</font>
														</div>
													</td>
												</tr>
											</thead>
											<tr>
												<td align="center">
													<html:textarea name='rs' property='xsbx3'
														style="width:500px" rows='6' onblur="checkLen(this,800)" />
												</td>
											</tr>
										</table>
										<table width="100%" border="1" align="center" class="tbstyle">
											<thead>
												<tr>
													<td>
														<div align="center" class="style2">
															���ļ��Ƚ��������¼
															<br />
															<font color="red">(������800������)</font>
														</div>
													</td>
												</tr>
											</thead>
											<tr>
												<td align="center">
													<html:textarea name='rs' property='xsbx4'
														style="width:500px" rows='6' onblur="checkLen(this,800)" />
												</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
						</tbody>
						<tr align="left">
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
								���
								<br />
								<font color="red">(������100������)</font>
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='xyyj' style="width:450px"
									rows='5' onblur="checkLen(this,100)" />
							</td>
						</tr>
					</table>
				</div>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
