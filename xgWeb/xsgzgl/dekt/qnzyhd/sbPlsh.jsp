<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function savePlsh(){
				var shzt = jQuery("#shzt").val();
				var shyj = jQuery("#shyj").val();
				if (shyj == ""){
					showAlert("����д��������");
					return false;
				}
				if(shzt == "" || shzt == null){
					showAlert("��ѡ����˽����");
					return false;
				}
				var api = frameElement.api,W = api.opener;
				W.savePlsh(shzt,shyj);
				closeDialog();
			}
		</script>
	</head>
	<body>
		<html:form action="/zyhd" method="post"onsubmit="return false;">
			<div style="overflow-x:hidden;overflow-y:auto;">
				<table class="formlist">
					<thead>
						<tr>
							<td colspan="2">
								<span>
									�������
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<font color="red">*</font>
								��˽��
							</th>
							<td>
								<select id="shzt" name="shzt">
									<option value="1">ͨ��</option>
									<option value="2">��ͨ��</option>
									<option value="3">�˻�</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								 <font color="red">*&nbsp;</font>������
								 <br/>
								<font color="red">(��200��)</font>
							</th>
							<td>
								<textarea rows="5" id="shyj" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
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
									<button type="button" onclick="savePlsh();">
										����
									</button>
									<button type="button" name="�� ��" onclick="closeDialog();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
			</table>
		</html:form>
	</body>
</html>
