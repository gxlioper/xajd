<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	</head>
	<body>
		<html:form action="/rcswSbglJyjl" method="post" styleId="form">
			<html:hidden property="id" />
		
			<div style='overflow-x:hidden;overflow-y:auto;height:360px;margin-bottom: 0px;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��ְ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:15%">ְ����</th>
							<td style="width:35%">
								${jbxx.zgh }
							</td>
							<th style="width:15%">����</th>
							<td style="width:35%">
								${jbxx.xm }
							</td>
						</tr>
						
						<tr>
							<th>�Ա�</th>
							<td>
								${jbxx.xbmc }
							</td>
							<th>��ϵ�绰</th>
							<td>
								${jbxx.lxdh }
							</td>
						</tr>
						<tr>
							<th>����</th>
							<td>
								${jbxx.bmmc }
							</td>
							<th></th>
							<td>
								
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����豸��Ϣ
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>�豸����</th>
							<td>
								${jyjlForm.flmc }
							</td>
							<th>
								�豸����
							</th>
							<td>
								${jyjlForm.sbmc }
							</td>
						</tr>
						<tr>
							<th>����ʱ��</th>
							<td>
								${jyjlForm.jysj }
							</td>
							<th>
								���������
							</th>
							<td>
								${jyjlForm.jbrxm }
							</td>
						</tr>
						<tr>
							<th>��ע
							</th>
							<td colspan="3">
								${jyjlForm.bz }
							</td>
						</tr>
						<tr>
							<th>����ԭ��
							</th>
							<td colspan="3">
								${jyjlForm.jyyy }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�黹�豸��Ϣ
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>�黹ʱ��</th>
							<td>
								${jyjlForm.ghsj }
							</td>
							<th>
								�黹������
							</th>
							<td>
								${jyjlForm.ghjbrxm }
							</td>
						</tr>
						<tr>
							<th>�黹��ע
							</th>
							<td colspan="3">
								${jyjlForm.ghbz }
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" type="button" onclick="iFClose();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>

