<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function savePddjPl(){
				var pddj =jQuery("#pddj").val();
				var api = frameElement.api,W = api.opener;
				W.savePddjPl(pddj);
				closeDialog();
			}
		</script>
	</head>
	<body>
		<html:form action="/xsxwkhDjpd" method="post"onsubmit="return false;">
			<div style="overflow-x:hidden;overflow-y:auto;">
				<table class="formlist">
					<thead>
						<tr>
							<td colspan="4">
								<span>
									�����ȼ�����
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
						<td colspan="4" align="center" > <span style="font-size:14px">��ǰѡ�����ɿ��˵ȼ���ѧ����<font color="red">${num}</font>��</span></td>
						   </tr>
						   <tr>
						<th><font color="red">* </font>�����ȼ�</th>
							<td colspan="3">
								<html:select  property="pddj" styleId="pddj"  style="width:130px">
								<option value='����'>����</option>
								<option value='����'>����</option>
								<option value='�ϸ�'>�ϸ�</option>
								<option value='���ϸ�'>���ϸ�</option>
								</html:select>
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
									<button type="button" onclick="savePddjPl();">
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
