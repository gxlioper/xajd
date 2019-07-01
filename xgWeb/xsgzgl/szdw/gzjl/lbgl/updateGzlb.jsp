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
			  var lbmc=jQuery("#lbmc").val();
			  var xssx=jQuery("#xssx").val();
			  if(jQuery.trim(lbmc)==""){
				  showAlert("请输入工作类别名称！");
					return false;
			  }
			  if(jQuery.trim(xssx)==""){
				  showAlert("请输入显示顺序！");
					return false;
			  }
		     var url = "gzjllb.do?method=updateGzlb&type=update";
		      ajaxSubFormWithFun("GzjlLbglForm",url,function(data){
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
	<body>
		<html:form action="/gzjllb" method="post" styleId="GzjlLbglForm"
			onsubmit="return false;">
			<html:hidden property="lbdm" styleId="lbdm" />
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table align="center" class="formlist">
					<tbody>
						<tr>
							<th>
								<span class="red">*</span>工作类别名称
							</th>
							<td>
								<html:text property="lbmc" styleId="lbmc" maxlength="20"
									styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>显示顺序
							</th>
							<td>
								<html:text property="xssx" styleId="xssx" maxlength="20"
									styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th>
								工作类别说明
							</th>
							<td>
								<html:textarea property="lbsm" styleId="lbsm" style="width:95%;" rows="5" onkeypress="checkLen(this,500);"></html:textarea>
							</td>
						</tr>

					</tbody>
					</table>
			</div>
					 <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" type="button"
										onclick="saveForm();return false;">
										保 存
									</button>
									<button type="button" type="button"
										onclick="iFClose();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>

		</html:form>
	</body>
</html>

