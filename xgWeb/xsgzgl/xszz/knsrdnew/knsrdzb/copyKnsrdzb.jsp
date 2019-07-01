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

		function saveForm(obj){
			//困难生认定指标名称
			//var zbmc = jQuery("input[name=zbmc]").val();
			var zbmc = jQuery("#zbmc").val();
			if (jQuery.trim(zbmc) == ""){
				showAlert("困难生认定指标名称为必填项！");
				return false;
			}	

			//var zbid = jQuery("input[name=zbid]").val();
			url = "xg_xszz_knsrd_knzbgl.do?method=copyKnsrdzb&type=save";
	      	ajaxSubFormWithFun("knsrdzbForm",url,function(data){
		    	 if(data["message"]=="保存成功！" ){
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
	<body>
		<html:form action="/xg_xszz_knsrd_knzbgl" method="post" styleId="knsrdzbForm" onsubmit="return false;">
		
	
		<html:hidden property="zbid" styleId="zbid"/>
			<div style="tab">
				
				<!-- 提示信息 end-->	
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>复制困难生认定指标</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    
					    <tr>
							<th colspan="2">
								<font class="red">*</font><span>困难生认定指标名称</span>
							</th>
							<td >
							<html:text property="zbmc" styleId="zbmc" maxlength="50" style='width:250px'></html:text>
							</td>
			      		</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				</table>
				
				</div>
				
			
		</html:form>
	</body>
</html>

