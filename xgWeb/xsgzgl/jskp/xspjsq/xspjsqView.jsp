<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${xspjsqForm.sqid}&tt="+new Date().getTime());
			})
		</script>
	</head>
	<body>
		<html:form action="/xspj_xspjsq" method="post" styleId="xspjsqForm">
			<div style='tab;width:100%; overflow-x:hidden;overflow-y:auto;'>
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
								<span>����������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>��Ŀ����</th>
							<td>${rs.xmmc}</td>
							<th>ָ������</th>
							<td>${rs.zdbmmc}</td>
						</tr>
							<th>��Ŀ���</th>
							<td>${rs.xmlbmc}</td>
							<th>����ʱ��</th>
							<td>${rs.cysj}</td>
						<tr>
							<th>������(ѧ��)</th>
							<td>${rs.xh}</td>
							<th>ѧ��</th>
							<td>${rs.xn}</td>
						</tr>
						<tr>
							<th>��ѧ��</th>
							<td>${rs.dxqmc}</td>
							<th>��ֵ</th>
							<td>${rs.fz}</td>
						</tr>
						<!--  
						<tr>
							<th>������</th>
							<td>${rs.fzrxm}</td>
							<th>������<br/>��ϵ��ʽ</th>
							<td>${rs.fzrlxfs}</td>
						</tr>
						<tr>
							<th>ָ����ʦ</th>
							<td>${rs.zdlsxm}</td>
							<th>ָ����ʦ<br/>��ϵ��ʽ</th>
							<td>${rs.zdlslxfs}</td>
						</tr>
						-->
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
						<tr>
							<th>
								��������
							</th>
							<td colspan="3">
								${rs.sqly}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
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
			</div>
			<div style="height: 30px"></div>
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