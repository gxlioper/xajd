<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/hdkhgl/jlygx/js/jlygx.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function)(){
				
			})
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="/hdkhgl_jlygx" method="post" styleId="HdkhglForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
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
								<span>���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td>${data.xn}</td>
							<th>ѧ��</th>
							<td>${data.xqmc}</td>
						</tr>
						<tr>
							<th>�����</th>
							<td>${data.xmmc}</td>
							<th>�ʱ��</th>
							<td>${data.hdkssj}&nbsp;��&nbsp;${data.hdjssj }</td>
						</tr>
						<tr>
							<th>����</th>
							<td>${data.lbmc }</td>
							<th>��ص�</th>
							<td>${data.hddd}</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>��¼�����</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr >
							<th>����</th>
							<td colspan="3">
								${xsjlgxxx.jlygx}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
	
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
								</div>
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