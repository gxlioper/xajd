<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/ahgf_sztz" method="post" styleId="form">
			<html:hidden property="id" />
			<div style='tab'>
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
								<span>�鿴ѧ����ϸ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								ѧ��
							</th>
							<td>
								${sztzSzxfForm.xn }
							</td>
							<th>
								ѧ��
							</th>
							<td>
								${sztzSzxfForm.xqmc }
							</td>
						</tr>
						<tr>
							<th>
								������չ��Ŀ
							</th>
							<td>
								${sztzSzxfForm.tzxm }
							</td>
							<th>
								����
							</th>
							<td>
								${sztzSzxfForm.tzjb }
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td>
								${sztzSzxfForm.mc }
							</td>
							<th>
								ȷ��ѧ��
							</th>
							<td>
								${sztzSzxfForm.xf }
							</td>
						</tr>
						<tr>
							<th>
								ȷ����
							</th>
							<td>
								${sztzSzxfForm.qrr }
							</td>
							<th>
								ȷ������
							</th>
							<td>
								${sztzSzxfForm.qrsj }
							</td>
						</tr>
						<tr>
							<th>
								�����
							</th>
							<td>
								${sztzSzxfForm.shr }
							</td>
							<th>
								¼������
							</th>
							<td>
								${sztzSzxfForm.lrsj }
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="iFClose();">
										�ر�
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

