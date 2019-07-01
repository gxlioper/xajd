<%@ page language="java" contentType="text/html; charset=GBK"%>
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
		function saveEcmm(){
			var ecmm = jQuery("#ecmm").val();
			if(null==ecmm||""==ecmm){
				showAlert("请输入新密码！");
				return false;
				}
			var api = frameElement.api,W = api.opener;
			W.saveEcmm(ecmm);
			closeDialog();
		}
		</script>
	</head>
	<body>
		<html:form action="/zdxljkEcmm" method="post" styleId="form">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
				<tbody>
				</br>
					<th><font color="red">*</font>新密码</th>
					<td>
						<input type="password" name="ecmm" id="ecmm" maxlength="20" onkeydown='if(event.keyCode==13){saveEcmm();}'/>
					</td>
				</tbody>
			</table>
		</div>
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
		       <tfoot>
					<tr>
						<td colspan="2">
							<div class="bz">"<span class="red">*</span>"为必填项</div>
							<div class="btn">
								<button type="button" type="button" onclick="saveEcmm();">
									保 存
								</button>
								<button type="button" type="button" onclick="iFClose();">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		    </tfoot>
			</table>
			</div>
		</html:form>
	</body>
</html>

