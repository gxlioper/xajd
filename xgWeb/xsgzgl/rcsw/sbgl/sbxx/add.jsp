<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript">
			function saveForm(){
				if (jQuery.trim(jQuery("#mc").val()) == "" 
					|| jQuery.trim(jQuery("#bh").val()) == ""
					|| jQuery.trim(jQuery("#fldm").val()) == ""){
					showAlertx("�뽫��������д������");
					return false;
				}
				
				jQuery.post("rcswSbglSbxx.do?method=checkSbbh",{bh:jQuery.trim(jQuery("#bh").val())},function(data){
					if (data == 0){
						var url = "rcswSbglSbxx.do?method=save";
						ajaxSubFormWithFun("form",url,function(data){
							showAlertx(data["message"]);
							refershParent();
						});
					} else {
						showAlertx("�豸�����Ѵ��ڡ�");
					}
				});
			}
		</script>
	</head>
	<body>
		<html:form action="/rcswSbglSbxx" method="post" styleId="form">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>�豸��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>�豸����
							</th>
							<td width="34%">
								<html:text property="bh" styleId="bh" maxlength="20"
								 onkeyup="value=value.replace(/[\u4e00-\u9fa5]/g,'')"
								 styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�豸����
							</th>
							<td>
								<html:text property="mc" styleId="mc" maxlength="20" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�豸����
							</th>
							<td>
								<html:select property="fldm" styleId="fldm">
									<html:option value=""></html:option>
									<html:options collection="sbflList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
										�� ��
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

