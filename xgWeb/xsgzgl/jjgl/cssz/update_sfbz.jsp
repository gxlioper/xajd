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
				var sfbz=jQuery("#sfbz").val();
				if(sfbz==""){
					showAlert("�뽫��*����Ŀ��д������");
					return false;
				}
				ajaxSubFormWithFun("jjglSfbzForm","jjgl_sfbz.do?method=add",function(data){
							refershParent();
						});
			}
		</script>
	</head>
	<body >
		<html:form action="/jjgl_sfbz" method="post" styleId="jjglSfbzForm" onsubmit="return false;">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>�����շѱ�׼</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>ѧ��
							</th>
							<td width="34%">
								<html:hidden property="id"  value="${dataModel.id}"/>
								<html:select property="jjxkdm" styleId="jjxkdm" value="${dataModel.jjxkdm}">
									<html:options collection="jjxkList" property="jjxkdm" labelProperty="jjxkmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>�꼶
							</th>
							<td width="34%">
								<html:select property="jjnjdm" styleId="jjnjdm"  value="${dataModel.jjnjdm}">
									<html:options collection="jjnjList" property="jjnjdm" labelProperty="jjnjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>�շѱ�׼
							</th>
							<td width="34%">
								<html:text property="sfbz" styleId="sfbz" maxlength="25"  value="${dataModel.sfbz}"/>
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

