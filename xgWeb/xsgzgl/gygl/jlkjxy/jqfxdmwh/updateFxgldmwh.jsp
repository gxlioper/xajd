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
			  var jqglfxmc=jQuery("#fxmc").val();			 
			  if(jQuery.trim(jqglfxmc)==""){
				  showAlert("请输入返校类别名称！");
				  return false;
			  }
			  
		     var url = "jlkjxy_jqfxdmwh.do?method=updateFxglDmwh&type=update";
		     ajaxSubFormWithFun("jlkjxy_jqfxdmwhForm",url,function(data){
		    	 if(data["message"]=="保存成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
		    			 	var api = frameElement.api,W = api.opener;
		    			 	W.jQuery('#dataTable',W.document).reloadGrid();
		    				closeDialog();
		    			}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	 }
				});
		  }	

		</script>
		
		
	</head>
	<body >
		<html:form action="/jlkjxy_jqfxdmwh" method="post" styleId="jlkjxy_jqfxdmwhForm"  onsubmit="return false;">
			<html:hidden property="fxdm"  styleId="fxdm"/>
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
							<tr>
								<th>
									<span class="red">*</span>返校类别代码
								</th>
								<td>
									<html:text property="fxdm" styleId="fxdm" maxlength="25" style="width:273px;" styleClass="text_nor" disabled="true"  />
								</td>
							</tr>
							<tr>
									<th>
											返校类别名称
										<br/><font color="red">&lt;限200字&gt;</font>
									</th>
									<td colspan="3">
										<html:textarea property='fxmc' style="width:98%" styleId="fxmc" rows='5' onblur="checkLen(this,200);"/>
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

