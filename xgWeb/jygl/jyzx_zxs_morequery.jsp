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
								<bean:write name="rs" property="num" />
							</td>
							<th width="16%">
								��ѯʦ����
							</th>
							<td width="34%">
								<bean:write name="rs" property="name" />
							</td>
						</tr>
	
				<tr>
					<th>
						��ѯʦ����
					</th>
					<td>
						<bean:write name="rs" property="age" />
					</td>
					<th>
						��ѯʦ�Ա�
					</th>
					<td>
						<bean:write name="rs" property="xb"  />
					</td>
				</tr>
				<tr>
					<th>
						��ѯʦ�ʸ�
					</th>
					<td>
						<bean:write name="rs" property="zxszg"  />
					</td>
					<th>
					    ��������
					</th>
					<td>
						<bean:write name="rs" property="email"  />
					</td>
				</tr>
				<tr>
					<th>
						��ϵ�绰
					</th>
					<td>
						<bean:write name="rs" property="lxdh" />
					</td>
					<th>
					
					</td>
					<td>
						
					</td>
				</tr>
				<tr>
					<th>
						��ѯʦ���
					</th>
					<td colspan="3" style="word-break:break-all;">
						<html:textarea name="rs" property="zxsjj" rows="6"
							style="width:100%" readonly="true"/>
					</td>
				</tr>
				</tbody>
			</table>
			</div>
	</body>
</html>

