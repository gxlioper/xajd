<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/Basic.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="xsgzgl/rcsw/newqjgl/qjxysz/js/qjxysz.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/newqjgl/qjsq/js/qjsq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
		  
		</script>
		</head>
	<body>
		<html:form action="/qjxysz"  method="post" styleId="QjXySzForm">
		<input type="hidden" name="id" id="id" value="${data.id}"/>
		<div class="tab" style="width:100%;overflow-x:hidden;overflow-y:auto;">
			<table width="100%" border="0" class="formlist">
				<thead>
						<tr>
							<th colspan="4">
								<span>请假承诺书</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="4">
							  ${data.content}
					    </td>
					</tr>
				</tbody>
			</table>
		</div>
		<div style="height:30px"></div>
		<table width="100%" border="0" class="formlist" style="position:fixed;bottom:0;margin-bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="同意"  onclick="go_Forward();return false;">
									同意
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		</html:form>
	</body>
</html>