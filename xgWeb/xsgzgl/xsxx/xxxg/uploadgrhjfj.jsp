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
		<script type="text/javascript" src="xsgzgl/xsxx/xxxg/js/grhjfjsc.js"></script>
		<script type="text/javascript">
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/xsxx_xsxxgl" method="post" styleId="xsxxglModel">
		<html:hidden property="hjid" styleId="hjid" value="${hjxx.hjid}"/>
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
								<span>���˻���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>��������</th>
							<td>
								${hjxx.jxmc}
							</td>
							<th>�佱��λ����</th>
							<td>
								${hjxx.bjdwmc}
							</td>
						</tr>
						<tr>
							<th>�����</th>
							<td>
								${hjxx.jxjb}
							</td>
							<th>�������</th>
							<td>
								${hjxx.jxlb}
							</td>
						</tr>
						<tr>
							<th>��ʱ��</th>
							<td>
								${hjxx.hjsj}
							</td>
						</tr>
						
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����ϴ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>�����ϴ���Ϣ</th>
							<td colspan="3">
								<html:hidden property="gid" styleId="gid" value="${gid}"/>
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<script type="text/javascript">
											//���ø��� 
											jQuery(function(){
												jQuery.MultiUploader({
													maxcount : 3,
													//��׺
													accept : 'png|gif|jpg|zip|rar|doc|docx',
													//����ļ���С ��λM
													maxsize: 10,
													//��Ÿ������������id
													elementid : 'gid'
													});
											});
										</script>
										
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
									<button type="button" onclick="saveUpload();">
									          ����
									</button>
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