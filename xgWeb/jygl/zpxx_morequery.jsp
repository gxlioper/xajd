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
				<em>���ĵ�ǰλ��:</em><a>��Ƹ��ϸ��Ϣ</a>
			</p>
		</div>

		<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>��Ƹ��ϸ��Ϣ</span>
						</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="reset" onclick="Close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				<tbody>
					<tr>
						<th width="20%">
							��Ƹְλ
						</th>
						<td>
							<font color="blue" size="2"><bean:write name="rs"
									property="zpzw" /> </font>
						</td>
						<th width="20%">
							��˾����
						</th>
						<td>
							<bean:write name="rs" property="gsmc" />
						</td>
					</tr>
					<tr>
						<th>
							��������
						</th>
						<td>
							<bean:write name="rs" property="email" />
						</td>
						<th>
							��ϵ�绰
						</th>
						<td>
							<bean:write name="rs" property="lxdh" />
						</td>
					</tr>
					<tr>
						<th>
							�����ص�
						</th>
						<td>
							<bean:write name="rs" property="gzdd" />
						</td>
						<th>
							��Ƹ����
						</th>
						<td>
							<bean:write name="rs" property="zprs" />
						</td>
					</tr>
					<tr>
						<th>
							��ҵ����
						</th>
						<td>
							<bean:write name="rs" property="hyfl" />
						</td>
						<th>
							����Ҫ��
						</th>
						<td>
							<bean:write name="rs" property="wyyq" />
						</td>
					</tr>
					<tr>
						<th>
							������нˮ
						</th>
						<td>
							<bean:write name="rs" property="syxs" />
						</td>
						<th>
							ת����нˮ
						</th>
						<td>
							<bean:write name="rs" property="zzxs" />
						</td>
					</tr>
					<tr>
						<th>
							ѧ��Ҫ��
						</th>
						<td>
							<bean:write name="rs" property="xlyq" />
						</td>
						<th>
							����ʱ��
						</th>
						<td>
							<bean:write name="rs" property="mssj" />
						</td>
					</tr>
					<tr>
						<th>
							����Я��
						</th>
						<td>
							<bean:write name="rs" property="msxd" />
						</td>
						<th>
							���Եص�
						</th>
						<td>
							<bean:write name="rs" property="msdd" />
						</td>
					</tr>
					<logic:notEqual name="xxdm" value="10690" scope="session">
						<tr>
							<th>
								��λְ��
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="gwzz" rows="8" cols="75%"
									readonly="true" />
							</td>
						</tr>
					</logic:notEqual>
					<tr>
						<th>
							ְλҪ��
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="zwyq" rows="8" cols="75%"
								readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							��˾���
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="gsjj" rows="8" cols="75%"
								readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							����ʱ��
						</th>
						<td>
							<bean:write name="rs" property="fbsj" />
						</td>
						<th>
						</th>
						<td>
						</td>
					</tr>
					</tbody>
			</table>
	</body>
</html>

