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
            var xxdm="${xxdm}";
		function saveForm(){
			  var rcxwlbdlmc=jQuery("#rcxwlbdlmc").val();
			  if(jQuery.trim(rcxwlbdlmc)==""){
                  var msg = "请输入行为大类名称！";
                  if("13431" == xxdm) msg = "请输入加分大类名称！";
                  showAlert(msg);
				  return false;
			  }
		     var url = "rcsw_rcxwwh_rcxwdmwhgl.do?method=updateRcxwdl&type=update";
		      ajaxSubFormWithFun("rcxwdmwhForm",url,function(data){
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
		<html:form action="/rcsw_rcxwwh_rcxwdmwhgl" method="post" styleId="rcxwdmwhForm" onsubmit="return false;">
			<html:hidden property="rcxwlbdldm"  styleId="rcxwlbdldm"/>
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
							<%--<tr id="tr_select_xn">
								<th>
									<span class="red">*</span>行为大类代码
								</th>
								<td >
									<html:text property="rcxwlbdldm" styleId="rcxwlbdldm" maxlength="20" readonly="true" style="color:grey" styleClass="text_nor" />
								</td>
							</tr>
							--%><tr>
								<th>
									<span class="red">*</span>
									<logic:notEqual name="xxdm" value="13431">
										<span>行为大类名称</span>
									</logic:notEqual>
									<logic:equal name="xxdm" value="13431">
										<span>加分大类名称</span>
									</logic:equal>
								</th>
								<td>
									<html:text property="rcxwlbdlmc" styleId="rcxwlbdlmc" maxlength="20" styleClass="text_nor" />
								</td>
							</tr>
							
							<tr>
								<th><span class="red">*</span>审核流程&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
								<td>
									<html:select property="splc" styleId="splc" disabled="false">
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
									<html:option value="无需审核">无需审核</html:option>
								</html:select>
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

