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
		<html:form action="/hqsj" method="post" styleId="HqsjForm" onsubmit="return false;">
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
							<td>
								${rs.xn}
							</td>
							<th>ѧ��</th>
							<td>
								${rs.xqmc}
							</td>
						</tr>
						<tr>							
							<th>�������Ҳ����</th>
							<td>
								${rs.qszlccs}
							</td>
							<th>�������Ҵ���</th>
							<td>
								${rs.yxqscs}
							</td>
						</tr>
						<tr>
							<th>�������Ҽӷ�</th>
							<td>
								${rs.yxqsjf}
							</td>
							<th>ʹ��Υ����������</th>
							<td>
								${rs.sywjdqcs}
							</td>
						</tr>
						<tr>
							<th>ҹ�����޴���</th>
							<td>
								${rs.ybgscs}
							</td>
							<th>�Ƿ����ҳ�</th>
							<td>
								${rs.qsz}
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

