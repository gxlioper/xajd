<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function savePlsh(shzt){
				var shyj = jQuery("#shyj").val();
				var clcj = jQuery("#clcj").val();
				if (shyj == ""){
					showAlert("����д��������");
					return false;
				}
				if (clcj == ""){
					showAlert("��ѡ����㼶��");
					return false;
				}
				var api = frameElement.api,W = api.opener;
				W.savePlsh(shzt,shyj,clcj);
				closeDialog();
			}
		</script>
	</head>
	<body>
		<html:form action="/tsqktbgl_sh" method="post"onsubmit="return false;">
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
						<tr>
							<th><span class="red">*</span>����㼶</th>
							<td>
								<html:select styleId="clcj" property="clcj">
										<html:option value="1">Ժϵ</html:option>
										<html:option value="2">ѧ����</html:option>
								</html:select>
							</td>
						</tr>
					<tbody>
						<tr>
							<th width="20%">
								<font color="red">*</font>
								������
								<br/>
								<font color="red">(��200��)</font>
							</th>
							<td>
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=tsqksh&id=shyj" />
								<html:textarea property="shyj" style="width:98%;margin-top:5px" rows="6"
											   onblur="checkLen(this,200);"  styleId="shyj"
								></html:textarea>
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
									<button type="button" onclick="savePlsh('1');">
										ͨ��
									</button>
									<button type="button" onclick="savePlsh('2');">
										��ͨ��
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
