<%@ page language="java" contentType="text/html; charset=GBK" import="java.util.*,xgxt.utils.String.StringUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function save(){
				var bz = jQuery("#bz").val();
				if(bz.length>500){
					showAlert("��ע����500�֣���ɾ����");
					return false;
				}
				var url = "gyglnew_zsxxgl.do?method=saveBzBatchForUpdate";
				ajaxSubFormWithFun("form", url, function(data) {
					 if(data["message"]=="����ɹ���"){
						 showAlert(data["message"],{},{"clkFun":function(){
								if (parent.window){
									refershParent();
								}
							}});
					 }else{
						 showAlert(data["message"]);
						}
				});
			}
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/gyglnew_zsxxgl">
		<input type="hidden" id="pkValues" name="pkValues" value="${pkValues}"/>
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
				<thead>
						<tr>
							<th colspan="4">
								<span>�����޸ı�ע</span>
							</th>
						</tr>
				</thead>
				<tbody>
						<th width="20%">
							��ע<br/>
							<font color="red">(������500����)</font>
						</th>
						<td colspan="3" style="word-break:break-all;width:100%">
							<textarea rows='5' style="width:99%" id="bz" name="bz">${rs.bz }</textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button"  onclick="save();return false;" id="buttonSave">
									�� ��
								</button>
								<button type="button"  onclick="iFClose();" id="buttonClose">
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
