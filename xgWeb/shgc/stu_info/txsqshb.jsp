<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/stuinfoFunction.js"></script>
	</head>

	<body onload="checkWinType();document.all('buttonClose').focus()">

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ - ��ѧ - ��ѧ���</a>
			</p>
		</div>

		<html:form action="/data_search" method="post">
			<input type="hidden" name="realTable" id="realTable"
				value="stu_txsqsh" />

			<div class="tab" style="margin-top: 0px; padding-top: 0px">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td width="30%">
								<bean:write name="rs" property="xh" />
								<input type="hidden"
									value="<bean:write name="rs" property="xh" />" name="xh"
									id="xh" />
							</td>
							<th width="16%">
								���
							</th>
							<td width="30%">
								<bean:write name="rs" property="nd" />
								<input type="hidden"
									value="<bean:write name="rs" property="nd" />" name="nd"
									id="nd" />
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td align="left">
								<bean:write name="rs" property="xm" />
							</td>
							<th>
								�ֻ�����
							</th>
							<td align="left">
								<bean:write name="rs" property="sjhm" />
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td align="left">
								<bean:write name="rs" property="xb" />
							</td>
							<th>
								��ͥ�绰
							</th>
							<td align="left">
								<bean:write name="rs" property="qtdh" />
							</td>
						</tr>
						<tr>
							<th>
								�꼶
							</th>
							<td align="left">
								<bean:write name="rs" property="nj" />
							</td>
							<th>
								��������
							</th>
							<td align="left">
								<bean:write name="rs" property="sqrq" />
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<bean:write name="rs" property="xymc" />
							</td>
							<th rowspan="3">
								��������
							</th>
							<td align="left" rowspan="3">
								<bean:write name="rs" property="sqly" />
							</td>
						</tr>
						<tr>
							<th>
								רҵ
							</th>
							<td align="left">
								<bean:write name="rs" property="zymc" />
							</td>

						</tr>
						<tr>
							<th>
								�༶
							</th>
							<td align="left">
								<bean:write name="rs" property="bjmc" />
							</td>

						</tr>
						<tr>

							<th>
								���
							</th>
							<td align="left">
								<logic:equal value="xx" name="userType" scope="request">
									<html:select name="rs" styleId="yesNo" property="xxsh">
										<html:option value="δ���">δ���</html:option>
										<html:option value="ͨ��">ͨ��</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
									</html:select>
								</logic:equal>
								<logic:equal value="xy" name="userType" scope="request">
									<html:select name="rs" styleId="yesNo" property="xysh">
										<html:option value="δ���">δ���</html:option>
										<html:option value="ͨ��">ͨ��</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
									</html:select>
								</logic:equal>
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
								���
							</th>
							<td align="left" colspan="3">
								<logic:equal value="xy" name="userType" scope="request">
									<html:textarea property="xyyj" name="rs"
										style="height:80;width:100%" />
								</logic:equal>
								<logic:notEqual value="xy" name="userType" scope="request">
									<html:textarea property="xyyj" name="rs"
										style="height:80;width:100%;" disabled="true" />
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								���첿�����
							</th>
							<td align="left" colspan="3">
								<logic:equal value="xx" name="userType" scope="request">
									<html:textarea property="xxyj" name="rs"
										style="height:80;width:100%" />
								</logic:equal>
								<logic:notEqual value="xx" name="userType" scope="request">
									<html:textarea property="xxyj" name="rs"
										style="height:80;width:100%;" disabled="true" />
								</logic:notEqual>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button
										onclick="viewAdd('/xgxt/abroad_query.do?act=txsq_sh&doType=save','add');"
										id="buttonSave">
										�� ��
									</button>
									<button onclick="Close();return false;" id="buttonClose">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>

				</table>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
	</body>
</html>
