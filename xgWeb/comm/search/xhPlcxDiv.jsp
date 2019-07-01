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
			var plcxxh = jQuery("#plcxxh").val();
			var api = frameElement.api,W = api.opener;
			if (jQuery.trim(jQuery("#plcxxh").val()) == "" || jQuery("#plcxxh").val() === W.demoHtml){
				showAlert("请输入学号！");
				return false;
			}
			W.creatClickedTjOfPlcx('xh','批量学号',plcxxh,plcxxh,'tj_xh_xh');
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
								批量查询学号
							</th>
							<td colspan="3">
								<textarea name="plcxxh" id="plcxxh" style="width:98%;height:400px" rows="25" >
								</textarea>
							</td>
						</tr>
					</tbody>
					</table>
					<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="commit();">
										确 定
									</button>
									<button type="button" type="button" onclick="iFClose();">
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

