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
								<span>社团管理</span>
							</th>
						</tr>
					</thead> -->
					
					<tbody>
						<tr>
							<th width="20%">
								社团名称
							</th>
							<td colspan="3">
								${stglMap.stmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								社团编号
							</th>
							<td width="30%">
								${stglMap.bh}
							</td>
							<th width="20%">
								有效状态
							</th>
							<td width="30%">
								${stglMap.yxztmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								社团类别
							</th>
							<td width="30%">
								${stglMap.stlbmc}
							</td>
							<th width="20%">
								指导老师
							</th>
							<td width="30%">
								${stglMap.zdls}
							</td>
						</tr>
						<tr>
							<th width="20%">
								社长
							</th>
							<td width="30%">
								${stglMap.szxm}
							</td>
							<th width="20%">
								财务负责人
							</th>
							<td width="30%">
								${stglMap.cwfzrxm}
							</td>
						</tr>
						<tr>
							<th>
								归属部门
							</th>
							<td colspan="3">
								${stglMap.zd1}
							</td>
						</tr>
						<tr id="fjtr">
							<th>
								附件
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden property="filepath" styleId="filepath" value="${stglMap.filepath}"/>
								<script type="text/javascript">
									//调用附件 
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
								备注
								<br><font color="red">限500字以内</br></font>
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
										关 闭
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