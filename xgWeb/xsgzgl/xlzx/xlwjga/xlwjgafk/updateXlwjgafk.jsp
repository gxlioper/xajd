<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xlzx/xlwjga/xlwjgafk/js/xlwjgafkComm.js"></script>
		<script language="javascript">
			function saveForm(){	
			  var zxfk=jQuery("#zxfk").val();
			  if(jQuery.trim(zxfk)==""){
				  showAlert("反馈学院参考建议不能为空！");
					return false;
			  }
					
		      var url = "xlzx_xlwjga_xlwjgafkgl.do?method=updateXlwjgafk&type=save";
		      ajaxSubFormWithFun("xlwjgafkForm",url,function(data){
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
			jQuery(function(){
			})
		</script>
	</head>
	<body >
		<html:form action="/xlzx_xlwjga_xlwjgafkgl" method="post" styleId="xlwjgafkForm" onsubmit="return false;">
			<html:hidden property="id" styleId="id"/>
			<input type="hidden" name="zzfk" value="1"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
						    <th>
								<span class="red">*</span>反馈学院参考建议</br><font color="red">(限500字)</font>
							</th>
							<td colspan="3">
								<html:textarea property="zxfk" styleId="zxfk" cols="50" rows="9" style="width:100%" onblur="chLeng(this,500)"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
						<td colspan="4">
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

