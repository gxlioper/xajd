<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/extend/zfsoft-dataExtend.js"></script>
		<script type="text/javascript">

			jQuery(function() {
				jQuery('#ExtendDataContent').dataExtend( {
					"mid"      : jQuery('[name="exendDateModuleId"]').val(),
					"dataId"   : jQuery('[name="jgid"]').val(),
					"dataType" : "1",
					"xh"       : jQuery('[name="xh"]').val(),
					"bdpzid"   : "xsxxgl",
					"naviBar"  : true,
					"mode"     : "view",
					"actionBar": false
				});
			});
		</script>
	</head>
	<body>
		<html:form action="/xsxx_kzxxjggl" method="post" styleId="xsxxKzxxJgForm">
		<html:hidden property="jgid"  styleId="jgid"/>
		<html:hidden property="exendDateModuleId" />
		<html:hidden property="xh" />
		<div style="tab;height:510px;overflow-x:hidden;overflow-y:auto;">
			<div id="ExtendDataContent"></div>
		</div>
		<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="¹Ø ±Õ" onclick="iFClose();">
										¹Ø ±Õ
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>
