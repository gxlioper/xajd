<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true" ></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zdzm/js/zdzm_sq.js"></script>
		<script type="text/javascript">
		jQuery(function(){
				
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+jQuery("#sqid").val()+"&tt="+new Date().getTime());
				
				
			});
		</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		<html:form action="/rcsw_cdgl_cdsh" method="post" styleId="rcswCdshForm">
		<html:hidden property="sqid" styleId="sqid"/>
		<html:hidden property="sqsj" />
		<html:hidden property="splcid" styleId="splcid"/>
		<html:hidden property="shzt" />
		<html:hidden property="xh" />
		<html:hidden property="xtgwid" />
		<html:hidden property="shid" styleId="shid"/>
			<div style='width:100%;height:415px;overflow-x:hidden;overflow-y:auto;'>
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
								${cdsqxx.cdmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								¥��
							</th>
							<td width="34%">
								${cdsqxx.ld}
							</td>
							<th width="16%">
								����
							</th>
							<td width="34%">
								${cdsqxx.fj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								��������
							</th>
							<td width="34%">
								${cdsqxx.rnrs}
							</td>
							<th width="16%">
								�շѱ�׼
							</th>
							<td width="34%">
								${cdsqxx.sfbz}
							</td>
						</tr>
						<tr>
							<th width="16%">
								��ϵ��
							</th>
							<td width="34%">
								${cdsqxx.lxr}
							</td>
							<th width="16%">
								��ϵ��ʽ
							</th>
							<td width="34%">
								${cdsqxx.lxfs}
							</td>
						</tr>
						<tr>
							<th width="16%">
								���⿪��ʱ��
							</th>
							<td colspan="3">
								${cdsqxx.dwkfsjkssj}
									��
								${cdsqxx.dwkfsjjssj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								��;
							</th>
							<td colspan="3">
								${cdsqxx.yt}
							</td>
						</tr>
						<tr>
							<th width="16%">
								�����豸����
							</th>
							<td colspan="3" style="word-break: break-all;">
								${cdsqxx.jbsbjs}
							</td>
						</tr>
						<logic:equal value="10351" name="xxdm">
						<tr>
							<th width="16%">
								�Ҹ�����ʹ��Э��
							</th>
							<td colspan="3" style="word-break: break-all;">
								${cdsqxx.xfgfsyxy}
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
								<html:hidden property="filepath" name="cdsqxx" styleId="fjid"/>
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
								${cdsqxx.sqsjdkssj}
								��
								${cdsqxx.sqsjdjssj}
							</td>
						</tr>
						<tr>
							<th width="16%">ʹ�ò���</th>
							<td colspan="3">
								${cdsqxx.bmmc}
							</td>
						</tr>
						<tr>
							<th width="16%">��������</th>
							<td width="34%">
								${cdsqxx.cyrs}
							</td>
							<th width="16%">������</th>
							<td width="34%">
								${cdsqxx.fzrxm}
							</td>
						</tr>
						<tr>
							<th width="16%">��������ϵ��ʽ</th>
							<td colspan="3">
								${cdsqxx.fzrlxfs}
							</td>
						</tr>
						<tr>
							<th width="16%">
								��������
							</th>
							<td colspan="3" style="word-break: break-all;">
								${cdsqxx.sqly}
							</td>
						</tr>
					</tbody>
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

