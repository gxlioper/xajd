<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/xmsqjs/js/xmsqjs.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
	
		<html:form action="/rcsw_txhd_xmsq" method="post" styleId="TxhdXmsqJsForm" onsubmit="return false;">
			<html:hidden property="sqid"/>
			<html:hidden property="xmdm"/>
		
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xthd/comm/viewStudent.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>
								 	��ѧ�������Ϣ
								</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<th>��Ŀ����</th>
							<td>${txhdXmszForm.xmmc }</td>
							<th>�ʱ��</th>
							<td>${txhdXmszForm.hdkssj }&nbsp;��&nbsp;${txhdXmszForm.hdjssj }</td>
						</tr>
						<tr>
							<th>��ص�</th>
							<td colspan="3">${txhdXmszForm.hddd }</td>
						</tr>
						<tr>
							<th>�˵��</th>
							<td colspan="3">${txhdXmszForm.hdsm }</td>
						</tr>
					</tbody>
					<tbody>
						<th>
							��������
						</th>
						<td colspan="3">
							${TxhdXmsqJsForm.sqly}
						</td>
					</tbody>
				</table>
			</div>
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
		</html:form>
	</body>
</html>

