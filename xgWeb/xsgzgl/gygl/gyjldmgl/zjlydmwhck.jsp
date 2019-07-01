<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/comm/message.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
		
		</script>
	</head>
	<body>
		<html:form action="/gyglnew_gyjldmgl" method="post"
			styleId="GyjldmglForm" onsubmit="return false;">

			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table align="center" class="formlist">
					<tbody>
						<tr>
							<th width="40%">
								奖惩类别代码
							</th>
							<td width="60%">
							   ${jg.gyjllbdldm}
							</td>
						</tr>
						<tr>
							<th width="40%">
								奖惩类别名称
							</th>
							<td width="60%">
								 ${jg.gyjllbdlmc}
							</td>
						</tr>
						<tr>
							<th width="40%">
								类别
							</th>
							<td width="60%">
								 ${jkf}
							</td>
						</tr>
						<tr>
							<th width="40%">
								分值
							</th>
							<td width="60%">
								 ${jg.fz}
							</td>
						</tr>
					</tbody>
					</table>
			      </div>
			      <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<button type="button" type="button"
										onclick="iFClose();return false;">
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

