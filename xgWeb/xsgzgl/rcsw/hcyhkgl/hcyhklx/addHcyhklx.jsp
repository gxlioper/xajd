<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
		
		function saveForm(){
			  if (jQuery.trim(jQuery("#lxmc").val()) == ""){
					showAlert("�뽫��������д������");
					return false;
				}

		     var url = "rcsw_hcyhkgl_hcyhklxgl.do?method=addHcyhklx&type=save";
		      ajaxSubFormWithFun("hcyhklxForm",url,function(data){
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
	<body >
		<html:form action="/rcsw_hcyhkgl_hcyhklxgl" method="post" styleId="hcyhklxForm" onsubmit="return false;">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>����ά��</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									���ʹ���
								</th>
								<td>
									<span>${model.lxdm}</span>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>��������
								</th>
								<td>
									<input type="text" id="lxmc" name="lxmc" maxlength="25"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button" name="����" onclick="saveForm();return false;">
											�� ��
										</button>
										<button type="button" name="�� ��" onclick="iFClose();return false;">
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

