<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/stglzjsr/js/stgl.js""></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form method="post" styleId="form"	action="/stgl_zjsr">
			<html:hidden property="id" />
			<div	style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<!-- <thead>
						<tr>
							<th colspan="4">
								<span>���Ź���</span>
							</th>
						</tr>
					</thead> -->
					
					<tbody>
						<tr>
							<th width="20%">
								��������
							</th>
							<td colspan="3">
								${stglMap.stmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								���ű��
							</th>
							<td width="30%">
								${stglMap.bh}
							</td>
							<th width="20%">
								��Ч״̬
							</th>
							<td width="30%">
								${stglMap.yxztmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								�������
							</th>
							<td width="30%">
								${stglMap.stlbmc}
							</td>
							<th width="20%">
								ָ����ʦ
							</th>
							<td width="30%">
								${stglMap.zdls}
							</td>
						</tr>
						<tr>
							<th width="20%">
								�糤
							</th>
							<td width="30%">
								${stglMap.szxm}
							</td>
							<th width="20%">
								��������
							</th>
							<td width="30%">
								${stglMap.cwfzrxm}
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td colspan="3">
								${stglMap.zd1}
							</td>
						</tr>
						<tr id="fjtr">
							<th>
								����
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden property="filepath" styleId="filepath" value="${stglMap.filepath}"/>
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
						<tr>
							<th>
								��ע
								<br><font color="red">��500������</br></font>
							</th>
							<td colspan="3"  style="height:120px">
								${stglMap.bz}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
								</div>
								<div class="btn">
									<button type="button" onclick="iFClose();"  id="buttonClose">
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