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
			function updateForm(){
				if (jQuery.trim(jQuery("#lxmc").val()) == ""){
					showAlert("请将必填项填写完整。");
					return false;
				}

				 var url = "rcsw_hcyhkgl_hcyhklxgl.do?method=updateHcyhklx&type=update";
			      ajaxSubFormWithFun("hcyhklxForm",url,function(data){
			    	 if(data["message"]=="保存成功！"){
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
			 <html:hidden property="lxdm"  styleId="lxdm"/>
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>
										类型维护
									</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									类型代码
								</th>
								<td>
									<span>${model.lxdm}</span>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>类型名称
								</th>
								<td>
									<input type="text" id="lxmc" name="lxmc" maxlength="25" value="${model.lxmc}"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button type="button" name="保存" onclick="updateForm();return false;">
											保 存
										</button>
										<button type="button" name="关 闭" onclick="iFClose();return false;">
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

