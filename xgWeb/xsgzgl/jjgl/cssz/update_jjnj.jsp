<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function saveForm(){
				var jjnjmc=jQuery("#jjnjmc").val();
				if(jjnjmc==""){
					showAlert("�뽫��*����Ŀ��д������");
					return false;
				}
				ajaxSubFormWithFun("jjglJJnjForm","jjgl_jjnj.do?method=add",function(data){
							refershParent();
						});
			}
		</script>
	</head>
	<body >
		<html:form action="/jjgl_jjnj" method="post" styleId="jjglJJnjForm" onsubmit="return false;">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>���Ӽҽ��꼶</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>�꼶����
							</th>
							<td width="34%">
								<html:hidden property="jjnjdm" value="${dataModel.jjnjdm}"/>
								<html:text property="jjnjmc" styleId="jjnjmc" maxlength="25" value="${dataModel.jjnjmc}"/>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();return false;">
										�� ��
									</button>
									<button type="button" type="button" onclick="iFClose();return false;">
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

