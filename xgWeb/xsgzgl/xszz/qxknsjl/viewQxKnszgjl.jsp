<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>

		<script type="text/javascript">
		
	
		</script>

	</head>
	<body>
		<html:form action="/xszz_qxknsjlgl" method="post"
			styleId="qxknsjlForm">
			
			<div style="height:460px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/viewStudent.jsp" %>

					<thead>
						<tr>
							<th colspan="4">
								<span>�������϶���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td>
								${knsjgrs.xn}						
							</td>
							<th>����ʱ��</th>
							<td>	
								${knsjgrs.sqsj}									
							</td>
					    </tr>
					    <tr>
							<th>�϶�����</th>
							<td colspan="3">
								${knsjgrs.dcmc}										
							</td>
					    </tr>					  
			      		<tr>
							<th>
							   	�϶�����
							</th>
							<td colspan="3">
								${knsjgrs.sqly}		
							</td>
			      		</tr>
					</tbody>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>ȡ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>ȡ����</th>
							<td>
								${knsqxjlrs.czrxm}	
							</td>
							<th>ȡ��ʱ��</th>
							<td>
								${knsqxjlrs.czsj}	
							</td>
					    </tr>
					    <tr>
							<th>ȡ��ԭ��</th>
							<td colspan="3">
								${knsqxjlrs.qxyy}	
							</td>
					    </tr>					 			      		
				  </tbody>
				</table>
				

				</div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
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
		</html:form>
	</body>
</html>

