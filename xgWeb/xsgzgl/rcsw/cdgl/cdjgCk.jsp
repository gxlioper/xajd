<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/cdgl/js/cdjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" defer="defer">
			jQuery(function(){});
		</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		<html:form action="/rcsw_cdgl_cdjg" method="post" styleId="rcswCdjgForm">
			<html:hidden property="sqid"/>
			<div style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
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
								<span>����������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								��������
							</th>
							<td colspan="3">
								${cdjgxx.cdmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								¥��
							</th>
							<td width="34%">
								${cdjgxx.ld}
							</td>
							<th width="16%">
								����
							</th>
							<td width="34%">
								${cdjgxx.fj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								��������
							</th>
							<td width="34%">
								${cdjgxx.rnrs}
							</td>
							<th width="16%">
								�շѱ�׼
							</th>
							<td width="34%">
								${cdjgxx.sfbz}
							</td>
						</tr>
						<tr>
							<th width="16%">
								��ϵ��
							</th>
							<td>
								${cdjgxx.lxr}
							</td>
							<th width="16%">
								��ϵ��ʽ
							</th>
							<td>
								${cdjgxx.lxfs}
							</td>
						</tr>
						<tr>
							<th width="16%">
								���⿪��ʱ��
							</th>
							<td colspan="3">
								${cdjgxx.dwkfsjkssj}
									��
								${cdjgxx.dwkfsjjssj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								��;
							</th>
							<td colspan="3">
								${cdjgxx.yt}
							</td>
						</tr>
						<tr>
							<th width="16%">
								�����豸����
							</th>
							<td colspan="3" style="word-break: break-all;">
								${cdjgxx.jbsbjs}
							</td>
						</tr>
						<logic:equal value="10351" name="xxdm">
						<tr>
							<th width="16%">
								�Ҹ�����ʹ��Э��
							</th>
							<td colspan="3" style="word-break: break-all;">
								${cdjgxx.xfgfsyxy}
							</td>
						</tr>
						</logic:equal>
						<logic:equal name="xxdm" value="10351">
						<tr>
							<th align="right" width="10%">
								������Ϣ
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden property="filepath" name="cdjgxx" styleId="fjid"/>
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
						</logic:equal>
						<tr>
							<th width="16%">
								����ʹ��ʱ���
							</th>
							<td colspan="3">
								${cdjgxx.sqsjdkssj}
								��
								${cdjgxx.sqsjdjssj}
							</td>
						</tr>
						<tr>
							<th width="16%">ʹ�ò���</th>
							<td width="34%">
								${cdjgxx.bmmc}
							</td>
							<th width="16%">����ʱ��</th>
							<td width="34%">
								${cdjgxx.sqsj}
							</td>
						</tr>
						<tr>
							<th width="16%">��������</th>
							<td width="34%">
								${cdjgxx.cyrs}
							</td>
							<th width="16%">������</th>
							<td width="34%">
								${cdjgxx.fzrxm}
							</td>
						</tr>
						<tr>
							<th width="16%">��������ϵ��ʽ</th>
							<td colspan="3">
								${cdjgxx.fzrlxfs}
							</td>
						</tr>
						<tr>
							<th width="16%">
								��������
							</th>
							<td colspan="3" style="word-break: break-all;">
								${cdjgxx.sqly}
							</td>
						</tr>
						<logic:equal name="xxdm" value="10346">
						<tr>
							<th width="16%">����</th>
							<td colspan="3">
								${cdjgxx.pj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								���۱�ע
							</th>
							<td colspan="3" style="word-break: break-all;">
								${cdjgxx.pjbz}
							</td>
						</tr>
						</logic:equal>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
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
			</div>
		</html:form>
	</body>
</html>

