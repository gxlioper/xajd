<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/xxxg/js/grhjfjsc.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("a").eq(0).removeAttr("onclick");
				jQuery("a").eq(0).removeAttr("class");
			})
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/xsxx_xsxxgl" method="post" styleId="xsxxglModel">
		<html:hidden property="hjid" styleId="hjid" value="${hjxx.hjid}"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>个人获奖信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>奖项名称</th>
							<td>
								${hjxx.jxmc}
							</td>
							<th>颁奖单位名称</th>
							<td>
								${hjxx.bjdwmc}
							</td>
						</tr>
						<tr>
							<th>奖项级别</th>
							<td>
								${hjxx.jxjb}
							</td>
							<th>奖项类别</th>
							<td>
								${hjxx.jxlb}
							</td>
						</tr>
						<tr>
							<th>获奖时间</th>
							<td>
								${hjxx.hjsj}
							</td>
						</tr>
						
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>附件上传信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>附件上传信息</th>
							<td colspan="3">
							
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden property="gid" styleId="gid" value="${gid}"/>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = jQuery('#gid').val();
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