<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/sxzzjygl/ztbhgl/ztbhjg/js/ztbhJg.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/provicecitylocal.js"></script>
		<script type='text/javascript'>
            function savePlSfsfx(){

                var sfsfx = jQuery("#sfsfx").val();
                var api = frameElement.api,W = api.opener;
                W.saveForPlRd(sfsfx);
                closeDialog();
            }

		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/ztbhgl_ztbhjg" method="post" styleId="ZtbhJgForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�϶�ʾ����������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<span class="red">*</span>�Ƿ�Ϊʾ����������
							</th>
							<td width="30%">
								<select id ="sfsfx" name = "sfsfx">
										<option value="0">��</option>
										<option value="1">��</option>
								</select>
							</td>
						</tr>
					</tbody>
				 </table>
				</div>
			  <div style="height:35px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="savePlSfsfx();">
										����
									</button>
									<button type="button" onclick="iFClose();">
										�ر�
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

