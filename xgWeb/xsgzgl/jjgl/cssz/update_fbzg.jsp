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
				var fbzgmc=jQuery("#fbzgmc").val();
				var fbzgdm=jQuery("#fbzgdm").val();
				if(fbzgmc=="" || fbzgdm==""){
					showAlert("�뽫��*����Ŀ��д������");
					return false;
				}
				var url = "jjgl_fbzg.do?method=add";
				ajaxSubFormWithFun("jjglFbzgForm",url,function(data){
							refershParent();
						});
			}
		</script>
	</head>
	<body >
		<html:form action="/jjgl_fbzg" method="post" styleId="jjglFbzgForm" onsubmit="return false;">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>���ӷ����ʸ�</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>����
							</th>
							<td width="34%">
								<html:text property="fbzgdm" styleId="fbzgdm" maxlength="25" value="${dataModel.fbzgdm}"/>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>����
							</th>
							<td width="34%">
								<html:text property="fbzgmc" styleId="fbzgmc" maxlength="25" value="${dataModel.fbzgmc}"/>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>����
							</th>
							<td width="34%">
								<html:text property="fy" styleId="fy" maxlength="25" value="${dataModel.fy}"/>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>˵��
							</th>
							<td width="34%">
								<html:textarea property="fbzgms" styleId="fbzgms" rows="3" style="width:80%" value="${dataModel.fbzgms}"></html:textarea>
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

