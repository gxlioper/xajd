<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="/xpj_zhpxf" method="post" styleId="zhpxfForm">
			<div style="tab;overflow-x:hidden;overflow-y:hidden;margin-bottom: 0px;" >
				<table width="" border="0" class="formlist" disable>
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
								<span>Ʒ�з���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td>
								<bean:write property="xn" name="zhpxfmap"  />
							</td>
							<th>�ۺ�Ʒ�з�</th>
						    <td>
								<bean:write property="zhpxf" name="zhpxfmap" />					
							</td>
						</tr>
						<tr>
							<th>ѧ��ɼ�ƽ����</th>
							<td>
								<bean:write property="pjf" name="zhpxfmap"  />
							</td>
							<th>�ۺ����ʷ�</th>
						    <td>
								<bean:write property="zhszf" name="zhpxfmap" />					
							</td>
						</tr>
						<tr>
							<th>�ۺ����ʷְ༶����</th>
							<td>
								<bean:write property="zhszfpm" name="zhpxfmap"  />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
								</div>
								<div class="btn">
									<button type="button" onclick="iFClose();">
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