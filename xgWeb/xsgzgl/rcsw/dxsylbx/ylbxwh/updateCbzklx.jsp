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
			
			  var cbzkmc=jQuery("#cbzkmc").val();
			  if(jQuery.trim(cbzkmc)==""){
				  showAlert("请输入参保状况名称！");
				  return false;
			  }
			  
			  var url = "rcsw_dxsylbx_ylbxwhgl.do?method=updateCbzklx&type=update";  
		      ajaxSubFormWithFun("ylbxwhForm",url,function(data){
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
		<html:form action="/rcsw_dxsylbx_ylbxwhgl" method="post" styleId="ylbxwhForm" onsubmit="return false;">
		<html:hidden property="cbzkdm" styleId="cbzkdm"/>
			
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span></span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="tr_select_xn">
								<th>
									<span class="red">*</span>参保状况代码
								</th>
								<td >
									<span>${model.cbzkdm}</span>
								</td>
							</tr>
							
							<tr>
								<th>
									<span class="red">*</span>参保状况名称</br><font color="red" >(限500字以内)</font>
								</th>
								<td>
									<html:textarea property="cbzkmc" styleId="cbzkmc" style="width:100%;" rows="5" onblur="checkLen(this,500)"></html:textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();return false;">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();return false;">
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

