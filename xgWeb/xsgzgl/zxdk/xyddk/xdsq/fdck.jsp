<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/xdsq/js/xdsq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/gjdk_xdsqnew" method="post" styleId="HsdxdForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ&nbsp;&nbsp;&nbsp;&nbsp;<font color="blue">��λ(Ԫ)</font></span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td>
									${rs.xn}																							
							</td>
							<th>ѧ��</th>
							<td>
									${xqmc}
							</td>
						</tr>
						<tr>
							<th>�ſ���</th>
							<td>
								${rs.fkje}
							</td>
							<th>�ſ�ʱ��</th>
							<td>
								${rs.fksj}
							</td>
						</tr>
						<tr>
							<th>�ſ�ƾ֤��</th>
							<td>
								${rs.fkpzh}
							</td>
							<th>�����˺�</th>
							<td>
								${rs.dkzh}
							</td>
						</tr>
						<tr>
							<th>�ͻ���</th>
							<td>
								${rs.khh}
							</td>
							<th></th>
							<td>
								
							
								
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
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