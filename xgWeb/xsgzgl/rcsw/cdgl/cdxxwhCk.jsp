<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/cdgl/js/cdxxwh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript" defer="defer">
			jQuery(function(){
				});
		</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display: none" onclick="reloadWindow();"></button>
		<html:form action="/rcsw_cdgl_cdxxwh" method="post" styleId="rcswCdxxwhForm">
			<html:hidden property="cdid"/>
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="17%">
								��������
							</th>
							<td colspan="3">
								${cdxx.cdmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								¥��
							</th>
							<td width="34%">
								${cdxx.ld}
							</td>
							<th width="16%">
								����
							</th>
							<td width="34%">
								${cdxx.fj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								��������
							</th>
							<td width="34%">
								${cdxx.rnrs}
							</td>
							<th width="16%">
								�շѱ�׼
							</th>
							<td width="34%">
								${cdxx.sfbz}
							</td>
						</tr>
						<tr>
							<th width="16%">
								��ϵ��
							</th>
							<td width="34%">
								${cdxx.lxr}
							</td>
							<th width="16%">
								��ϵ��ʽ
							</th>
							<td width="34%">
								${cdxx.lxfs}
							</td>
						</tr>
						<tr>
							<th width="16%">
								���⿪��ʱ��
							</th>
							<td colspan="3">
								${cdxx.dwkfsjkssj}
								��
								${cdxx.dwkfsjjssj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								��;
							</th>
							<td colspan="3">
								${cdxx.yt}
							</td>
						</tr>
						<tr>
							<th width="16%">
								�����豸����
							</th>
							<td colspan="3" style="word-break: break-all;">
								${cdxx.jbsbjs}
							</td>
						</tr>
						<logic:equal value="10351" name="xxdm">
						<tr>
							<th width="16%">
								�Ҹ�����ʹ��Э��
							</th>
							<td colspan="3" style="word-break: break-all;">
								${cdxx.xfgfsyxy}
							</td>
						</tr>
						</logic:equal>
						<logic:equal name="xxdm" value="10351">
						<tr>
							<th align="right" width="10%">
								������Ϣ
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										var gid = "${cdxx.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
											});
									});
								</script>
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th align="right" width="10%">
								��������Э��
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-1" style="padding: 5px;"></div>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										var gid = "${cdxx.xysfilepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-1'
											});
									});
								</script>
							</td>
						</tr>
						<tr>
							<th width="16%">
								�Ƿ񿪷�����
							</th>
							<td colspan="3">
								${cdxx.sfkfsqmc}
							</td>
						</tr>
						<tr id="splcTr">
							<th width="16%">
								�������
							</th>
							<td colspan="3">
								${cdxx.lcxx}
							</td>
						</tr>
					</tbody>
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

