<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		</script>
		</head>
	<body>
		<html:form action="/xtwh_mobileMsg"  method="post" styleId="mobileMessageForm">
		<div style='tab;width:100%;height:310px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
				<tbody>
					
					<tr>
						<th width="25%">
							收件人
						</th>
						<td colspan="3">
						   ${model.sxr}
						</td>
					</tr>
					<tr>
						<th width="25%">
							收件失败人
						</th>
						<td colspan="3">
						   ${model.failsxr}
						</td>
					</tr>
					<tr>
						<th width="25%">
							发送内容
						</th>
						<td colspan="3">
							${model.fsnr}
					    </td>
					</tr>
				</tbody>
			</table>
			
		</div>
		<div style="height:5px;"></div>
		<div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									
									<button type="button" name="关闭" onclick="iFClose();">
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