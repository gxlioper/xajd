<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xshdgl/js/xshdgl.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				
				})
		</script>
		<style type = "text/css">
			
		</style>
	</head>
	<body>
		<html:form action="/xshdgl_xshdgl" method="post" styleId="XshdglForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">�����</th>
							<td  width="30%">
								${xshdmap.hdmc}
							</td >
							<th  width="20%">�ʱ��</th>
							<td  width="30%">
								${xshdmap.hdsj}
							</td>
						</tr>
						<tr>
							<th>��ص�</th>
							<td>
								${xshdmap.hddd}
							</td>
							
							<th>�����</th>
							<td>
								${xshdmap.hdlxmc}
							</td>
						</tr>
						<tr>
							<th>���쵥λ</th>
							<td>
								${xshdmap.zbdwdm}
							</td>
							<th>Э�쵥λ</th>
							<td>
								${xshdmap.xbdwdm}
							</td>
						</tr>
						<tr>
							<th>�а쵥λ</th>
							<td>
								${xshdmap.cbdwdm}
							</td>
							<th>�������</th>
							<td>${xshdmap.hdfzr}</td>
						</tr>
						<tr>
							<th>������Ա</th>
							<td colspan="3">
								${xshdmap.cyry}
							</td>
						</tr>
						<tr>
							<th>����</th>
							<td colspan="3">
								${xshdmap.hdjj}
							</td>
						</tr>
						<tr>
							<th>�����ϴ�</th>
							<td colspan="3">
						<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<html:hidden property="filepath" styleId="filepath" value="${xshdmap.filepath}" />
							<script type="text/javascript">
								//���ø��� 
								jQuery(function(){
									var gid = jQuery('#filepath').val();
									jQuery.MultiUploader_q({
										gid : gid
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