<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script language="javascript" src="js/comm/searchTj.js"></script>
		<script type="text/javascript">
		function commit(){
			var plcxxm = jQuery("#plcxxm").val();
			var api = frameElement.api,W = api.opener;
			if (jQuery.trim(jQuery("#plcxxm").val()) == "" || jQuery("#plcxxm").val() === W.xmdemoHtml){
				showAlert("������������");
				return false;
			}
			W.creatClickedTjOfPlcx('xm','��������',plcxxm,plcxxm,'tj_xm_xm');
			closeDialog();
		}
		</script>
	</head>
	<body>
		<html:form action="/seachTjManage">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
						<tr>
							<th width="20%">
								������ѯ����
							</th>
							<td colspan="3">
								<textarea name="plcxxm" id="plcxxm" style="width:98%;height:400px" rows="25" >
								</textarea>
							</td>
						</tr>
					</tbody>
					</table>
					<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="commit();">
										ȷ ��
									</button>
									<button type="button" type="button" onclick="iFClose();">
										�� ��
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

