<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xxgl/xsxxgl.js"></script>
		
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/xgservice.js"></script>
		<script type='text/javascript' src="js/moveDiv.js"></script>
		<script type='text/javascript' src="js/AjaxFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/messageFunction.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//为button注册事件
				jQuery("#but_save").click(function(){
					saveForm('xgXxgl');
				});
				jQuery("#but_close").click(btn_close);
			});
		</script>
	</head>
	<body style="width:100%"  >
		<html:form action="/rcsw_xsxxgl.do" method="post" styleId="demoForm">
			<div  style="height:300px;overflow-x:auto;overflow-y:auto;">
				<table width="100%" border="0" class="formlist" >
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
								<span>查看学生献血信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								学年
							</th>
							<td width="34%" colspan="3">
								${model.xn}
							</td>
						</tr>
						
						<tr>
							<th width="16%">
								献血时间
							</th>
							<td width="34%" colspan="3">
								${model.xxsj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								备注
							</th>
							<td width="34%" colspan="3">
								${model.bz }
							</td>
						</tr>
					</tbody>
					
				</table>
				</div>
				<div style="height: 15px"></div>
				<table class="formlist" width="100%">
				<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz"></div>
								<div class="btn">
									<button type="button" type="button" id="but_close" >
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
			</table>
			
		</html:form>
	</body>
</html>

