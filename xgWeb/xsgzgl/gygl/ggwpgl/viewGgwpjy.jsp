<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	</head>
	<body>
		<html:form action="/xgygl_ggwpjydj" method="post" styleId="form">
			<html:hidden property="id" />		
			<div style='overflow-x:hidden;overflow-y:auto;height:360px;margin-bottom: 0px;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ʒ������Ϣ
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>�豸����</th>
							<td>
								${rs.flmc }
							</td>
							<th>
								�豸����
							</th>
							<td>
								${rs.sbmc }
							</td>
						</tr>
						<tr>
							<th>����ʱ��</th>
							<td>
								${rs.jysj }
							</td>
							<th>
								���������
							</th>
							<td>
								${rs.jbrxm }
							</td>
						</tr>
						<tr>
							<th>��ע
							</th>
							<td colspan="3">
								${rs.bz }
							</td>
						</tr>
						<tr>
							<th>����ԭ��
							</th>
							<td colspan="3">
								${rs.jyyy }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ʒ�黹��Ϣ
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>�黹ʱ��</th>
							<td>
								${rs.ghsj }
							</td>
							<th>
								�黹������
							</th>
							<td>
								${rs.ghjbrxm }
							</td>
						</tr>
						<tr>
							<th>�黹��ע
							</th>
							<td colspan="3">
								${rs.ghbz }
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

