<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript">
		jQuery(function() {
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${rs.sqid}&tt="+ new Date().getTime());
		});
		</script>
	</head>
	<body>
		<html:form action="/jskpXmsb" method="post" styleId="jskpXmsbForm">
		<input type="hidden" value="${sfsh}" name="sfsh" id="sfsh"/>
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
								<span>��Ŀ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:equal value="1" name="sfsh">
							<tr>
								<th>�걨��Ŀ</th>
								<td>
									${rs.xmmc}
								</td>
								<th>��ʱ��</th>
								<td>
									${rs.hjsj}
								</td>
							</tr>
							<tr>
								<th>�걨����</th>
								<td colspan="3">
								${rs.sbly}
								</td>
							</tr>
						</logic:equal>
						<logic:equal value="0" name="sfsh">
							<tr>
								<th>��Ŀ����</th>
								<td>
									${rs.xmmc}
								</td>
								<th>��ʱ��</th>
								<td>
									${rs.lxsj}
								</td>
							</tr>
							<tr>
								<th>��������</th>
								<td colspan="3">
									${rs.lxly}
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th align="right" width="10%">
								������Ϣ
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden name="rs" property="fjid" styleId="fjid"/>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										var gid = jQuery('#fjid').val();
										jQuery.MultiUploader_q({
											gid : gid
											});
									});
								</script>
							</td>
						</tr>
					</tbody>
				</table>
				<logic:notEqual value="�������" name="shztmc">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>�����Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4" id="shlccx">

								</td>
							</tr>

						</tbody>

					</table>
				</logic:notEqual>
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