<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	</head>
	<body>
		<html:form action="/xspj_xspjjg" method="post" styleId="xspjjgForm">
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
								<span>���۽����Ϣ</span>
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
						<tr>
							<th>������</th>
							<td>${rs.fzrxm}</td>
							<th>��������ϵ��ʽ</th>
							<td>${rs.fzrlxfs}</td>
						</tr>
						<tr>
							<th>ָ����ʦ</th>
							<td>${rs.zdlsxm}</td>
							<th>ָ����ʦ��ϵ��ʽ</th>
							<td>${rs.zdlslxfs}</td>
						</tr>
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
						<thead>
							<tr>
								<th colspan="4">
									<span>�÷ֻ���</span>
								</th>
							</tr>
						</thead>
						<table style="margin-bottom: 5px" width="100%" border="0" class="formlist" id="tab_rcxw">
							<tbody>
								<tr>
									<th><div align="center">ѧ��</div></th>
									<th><div align="center">��Ŀ���</div></th>
									<th><div align="center">�ܷ�</div></th>
								</tr>
								<logic:iterate name="singleSummary" id="s">
									<tr>
										<td>
											<div align="center"><bean:write name="s" property="xn"/></div>
										</td>
										<td>
											<div align="center"><bean:write name="s" property="xmlbmc"/></div>
										</td>
										<td>
											<div align="center"><bean:write name="s" property="fz"/></div>
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
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