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
		<script type="text/javascript">
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/xzhcp_zcjg" method="post" styleId="ZhcpJgForm">
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
								<span>�۲���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
						<logic:notEqual value="10364" name="xxdm">
							<th>ѧ��</th>
							<td>${xn}</td>
							<th>ѧ��</th>
							<td>${xqmc}</td>
						</logic:notEqual>
						<logic:equal value="10364" name="xxdm">
							<th>ѧ��</th>
							<td colspan="3">${xn}</td>
						</logic:equal>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td colspan="3">
								<html:hidden property="fjpath" styleId="fjpath"/>
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<script type="text/javascript">
										//���ø��� 
										jQuery(function(){
											var gid = jQuery('#fjpath').val();
											jQuery.MultiUploader_q({
												gid : gid
												});
										});
								</script> 
							</td>
						</tr>
						<tr>
							<th>���˲���
							<td colspan="3">
								${ZhcpJgForm.grpc}
							</td>
						</tr>
					
					</tbody>
				</table>
				</div>	
				<div style="height:50px;"></div>
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