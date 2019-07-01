<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/jtqkdc/js/jtqkdc.js"></script>
		<script type="text/javascript">

			jQuery(function(){
				//加载下拉选项
				loadViewJtcySelectOptions();
				//加载家庭情况中下拉选项
				loadViewMkxxSelectOptions();
				//加载家庭情况中radio选项
				loadViewMkxxRadioOptions();
			});
		</script>
	</head>
	<body>
		<html:form action="/xszz_jtqkdc" method="post" styleId="jtqkdcForm">
		
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>家庭成员</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/jtcyView.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>家庭情况</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/mkxxView.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>附件</span>
							</th>
						</tr>
					</thead>
					<tr>
						<th>
							附件信息
						</th>
						<td colspan="3">
							<html:hidden property="ylzd3" styleId="fjidjtqk"/>
							<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
							<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
							<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
							<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
							<div id="fjidjtqkDiv"></div>
							<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										jQuery('#fjidjtqkDiv').multiUploader_q({
											gid : jQuery('#fjidjtqk').val(),
											uid : 'jtqk'
											});
									});
							</script>
						</td>
					</tr>
				</table>
			</div>
		</html:form>
	</body>
</html>

