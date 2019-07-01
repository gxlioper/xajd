<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>������ - ������ѯ - ��ѯʦ��Ϣ - �鿴��ѯʦ��Ϣ</a>
			</p>
		</div>
		<html:form action="/xljk_zxsxx_view">
			<logic:present name="list" scope="request">
				<div class="tab">
					<table width="100%" border="0" class="formlist">

						<logic:iterate id="li" name="list" scope="request">
							<tbody>
								<tr>
									<th>
										��ѯʦ���
									</th>
									<td>
										<html:text name="li" property="ZXXBH" readonly="true" />
									</td>
									<th>
										�Ա�
									</th>
									<td>
										<html:text name="li" property="ZXXXB" readonly="true" />
									</td>
								</tr>
								<tr>
									<th>
										��ѯʦ����
									</th>
									<td>
										<html:text name="li" property="ZXXXM" readonly="true" />
									</td>
									<logic:present name="showZxszc">
										<th>
											ר��
										</th>
										<td>
											<html:select property="zxszc" styleId="zxszc" value="${li.ZC}" style="width:70%">
												<html:options collection="zxszcList" property="dm"
													labelProperty="mc" />
											</html:select>
										</td>
									</logic:present>
									<logic:notPresent name="showZxszc">
										<th></th>
										<td></td>
									</logic:notPresent>
								</tr>
								<tr>
									<th>
										�ʸ�
									</th>
									<td colspan="3">
										<html:textarea name="li" property="ZXXZG" rows="8" cols="60"
											readonly="true"></html:textarea>
									</td>
								</tr>
								<tr>
									<th>
										�� ��
									</th>
									<td colspan="3">
										<html:textarea name="li" property="JJ" rows="8" cols="60"
											readonly="true"></html:textarea>
									</td>
								</tr>
							</tbody>
						</logic:iterate>

						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button id="Close" onclick="window.close();return false;">
											�ر�
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</logic:present>
		</html:form>
	</body>
	</html>