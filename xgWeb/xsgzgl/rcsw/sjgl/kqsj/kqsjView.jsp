<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript">
		
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/kqsj" method="post" styleId="KqsjForm" onsubmit="return false;">
			<input type="hidden" id="type" value="${type}" />
			<html:hidden property="id"  styleId="id"/> 
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;height:370px'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>

					<%@ include file="/xsgzgl/rcsw/hcyhkgl/comm/viewStudent.jsp" %>

					<thead>
						<tr>
							<th colspan="4">
								<span>��������ά�� </span>
							</th>
						</tr>
					</thead>					
						<tr>
							<th>ѧ��</th>
							<td colspan="3">
								${rs.xn}
							</td>
						</tr>
						<tr>
							<th>����ѧʱ</th>
							<td>
								${rs.kkxs}
							</td>
							<th>�ٵ����˴���</th>
							<td>
								${rs.cdztcs}
							</td>
						</tr>
						<tr>
							<th>��һѧ���¼�����</th>
							<td>
								${rs.dyxqsjts}
							</td>
							<th>�ڶ�ѧ���¼�����</th>
							<td>
								${rs.dexqsjts}
							</td>
						</tr>
						<tr>
							<th>��һѧ�ڲ�������</th>
							<td>
								${rs.dyxqbjts}
							</td>
							<th>�ڶ�ѧ�ڲ�������</th>
							<td>
								${rs.dexqbjts}
							</td>
						</tr>						
				</table>
			</div>
			<div>	
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="�� ��" onclick="iFClose();">
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

