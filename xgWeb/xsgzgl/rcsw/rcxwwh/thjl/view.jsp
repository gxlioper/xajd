<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			
			jQuery(function(){
				var gid = jQuery('#thid').val();
				jQuery.MultiUploader_q({
					gid : gid
				});
			});

		</script>
	</head>
	<body>
		<html:form action="/rcsw_thjl" method="post" styleId="form">
			<html:hidden property="thid" styleId="thid" />
		
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
								<span≯����¼��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td>
								${rcxwThjlForm.xn }
							</td>
							<th>ѧ��</th>
							<td>
								${rcxwThjlForm.xqmc }
							</td>
						</tr>
						<tr>
							<th≯��ʱ��</th>
							<td >
								${rcxwThjlForm.thsj }
							</td>
							<th≯����ʦ</th>
							<td>
								${rcxwThjlForm.jsxm }
							</td>
						</tr>
						<tr>
							<th>
								̸������
							</th>
							<td colspan="3">
								${rcxwThjlForm.thnr }
							</td>
						</tr>
						<tr>
							<th>
								̸�����ݸ���
							</th>
							<td colspan="3">
								<%@includefile="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							</td>
						</tr>
					</tbody>
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

