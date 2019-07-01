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
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${HjsqForm.id}&tt="+new Date().getTime());
		});
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/jxsq" method="post" styleId="HjsqForm">
		<html:hidden property="id" styleId="id"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>综测信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						
						<tr>
							<th>学年</th>
							<td>${xn}</td>
							<th>学期</th>
							<td>${xqmc}</td>
						</tr>
						<tr>
							<th>奖项名称</th>
							<td>
								${HjsqForm.hjmc}
							</td>
							<th>获奖日期</th>
							<td>
								${HjsqForm.hjsj}
							</td>
						</tr>
						<tr>
							<th>颁奖单位</th>
							<td colspan="3">
								${HjsqForm.fjdw}
							</td>
						</tr>
					
						<tr>
							<th>
								附件
							</th>
							<td colspan="3">
								<html:hidden property="fj" styleId="fj"/>
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<script type="text/javascript">
										//调用附件 
										jQuery(function(){
											var gid = jQuery('#fj').val();
											jQuery.MultiUploader_q({
												gid : gid
												});
										});
								</script> 
							</td>
						</tr>
					
					</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>审批信息</span>
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