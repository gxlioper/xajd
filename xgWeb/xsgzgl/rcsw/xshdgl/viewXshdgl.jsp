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
								<span>活动信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">活动名称</th>
							<td  width="30%">
								${xshdmap.hdmc}
							</td >
							<th  width="20%">活动时间</th>
							<td  width="30%">
								${xshdmap.hdsj}
							</td>
						</tr>
						<tr>
							<th>活动地点</th>
							<td>
								${xshdmap.hddd}
							</td>
							
							<th>活动类型</th>
							<td>
								${xshdmap.hdlxmc}
							</td>
						</tr>
						<tr>
							<th>主办单位</th>
							<td>
								${xshdmap.zbdwdm}
							</td>
							<th>协办单位</th>
							<td>
								${xshdmap.xbdwdm}
							</td>
						</tr>
						<tr>
							<th>承办单位</th>
							<td>
								${xshdmap.cbdwdm}
							</td>
							<th>活动负责人</th>
							<td>${xshdmap.hdfzr}</td>
						</tr>
						<tr>
							<th>参与人员</th>
							<td colspan="3">
								${xshdmap.cyry}
							</td>
						</tr>
						<tr>
							<th>活动简介</th>
							<td colspan="3">
								${xshdmap.hdjj}
							</td>
						</tr>
						<tr>
							<th>附件上传</th>
							<td colspan="3">
						<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<html:hidden property="filepath" styleId="filepath" value="${xshdmap.filepath}" />
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
										关闭
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