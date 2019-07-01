<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/bxgl/xsbxbx/js/xsbxbx.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
		function onShow(gndm) {
			var url = "rcswBxglXsbxbx.do?method=getBxbxxx";
			jQuery.ajax( {
				type : "post",
				async : false,
				url : url,
				data : {
					bxid : jQuery("#bxid").val()
				},
				dataType : "json",
				success : function(data) {
					zdybdInit(gndm, data);
				}
			});
		}
		jQuery(function(){
			//字段自定义表单加载 
			onShow("rcsw_bxbx_query");
		});
		</script>	
	</head>
	<body>
		<html:form method="post" styleId="demoForm" action="/rcswBxglXsbxbx"
			enctype="multipart/form-data">
			<html:hidden property="bxid" styleId="bxid" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:340px;margin-bottom: 0px;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp"%>
					</table>
					<div class=""  id="content" style="margin-top: 5px; overflow-x:hidden;" >
				    </div>
				    <table width="100%" border="0" class="formlist">
					<tr>
							<th align="right" width="15%">
								附件信息
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden property="filepath" styleId="filepath" value="${model.filepath}"/>
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
					</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">		
									<button type="button" onclick="iFClose();" name="关 闭" id="but_close" >
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
