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
				<em>���ĵ�ǰλ��:</em><a>��ѯʦ��ϸ��Ϣ</a>
			</p>
		</div>
	
		<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��ѯʦ��ϸ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="window.close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								��ѯʦ���
							</th>
							<td width="34%">
								<html:text name="rs" property="num" style="100%" readonly="true" />
							</td>
							<th width="16%">
								��ѯʦ����
							</th>
							<td width="34%">
								<bean:write name="rs" property="zxsname" />
							</td>
						</tr>
					<tr>
						<th>
							ѧ��ѧ��
						</th>
						<td>
							<bean:write name="rs" property="xsxh" />
						</td>
						<th>
							ѧ������
						</th>
						<td>
							<bean:write name="rs" property="name" />
						</td>
					</tr>
				<tr>
					<th>
						ѧ���Ա�
					</th>
					<td>
						<bean:write name="rs" property="xb" />
					</td>
					<th>
						ѧ���꼶
					</th>
					<td>
						<bean:write name="rs" property="nj" />
					</td>
				</tr>
				<tr>
					<th>
						<bean:message key="lable.xsgzyxpzxy" />����
					</th>
					<td>
						<bean:write name="rs" property="xymc" />
					</td>
					<th>
						רҵ����
					</th>
					<td>
						<bean:write name="rs" property="zymc" />
					</td>
				</tr>
				<tr>
					<th>
						�Ƿ�Ҫ�����
					</th>
					<td>
						<bean:write name="rs" property="meet" />
					</td>

					<th>
						����Լ��ʱ��
					</th>
					<td>
						<bean:write name="rs" property="qwyjtime" />
					</td>
				</tr>
				<tr>
					<th>
						��ѯ�������
					</th>
					<td colspan="3" style="word-break:break-all;">
						<html:textarea name="rs" property="zxwtjs" rows="6"
							readonly="true" style="width:100%" />
					</td>
				</tr>
				<tr>
					<th>
						��ѯ����ظ�
					</th>
					<td colspan="3" style="word-break:break-all;">
						<html:textarea name="rs" property="zxwthf" rows="11"
							readonly="true" style="width:100%" />
					</td>
				</tr>
				<tr>
					<th>
						ѧ��ȷ��
					</th>
					<td>
						<bean:write name="rs" property="xsqr" />
					</td>

					<th>
						��ѯʦȷ��
					</th>
					<td>
						<bean:write name="rs" property="zxsqr" />
					</td>
				</tr>
				<tr>
					<th>
						ѧ��ȷ��ʱ��
					</th>
					<td>
						<bean:write name="rs" property="xsqrsj" />
					</td>

					<th>
						��ѯʦȷ��ʱ��
					</th>
					<td>
						<bean:write name="rs" property="zxsqrsj" />
					</td>
				</tr>
				<tr>
					<th>
						����Լ���ص�
					</th>
					<td>
						<bean:write name="rs" property="yjdd" />
					</td>

					<th>
						����Լ��ʱ��
					</th>
					<td>
						<bean:write name="rs" property="yjsj" />
					</td>
				</tr>
				</tbody>
			</table>
			</div>
	</body>
</html>
