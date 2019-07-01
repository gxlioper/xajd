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
			  /*var lbdm=jQuery("#lbdm").val();
			  if(jQuery.trim(lbdm)==""){
				  showAlert("科研类别代码不能为空！");
					return false;
			  }*/
			  var lbmc=jQuery("#lbmc").val();
			  if(jQuery.trim(lbmc)==""){
				  showAlert("科研类别名称不能为空！");
					return false;
			  }
		      var url = "kycxgl_kycxxm_kycxxmwhgl.do?method=updateKycxxmwh&type=update";
		      ajaxSubFormWithFun("kycxxmwhForm",url,function(data){
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
				setTimeout(function(){
					jQuery("#splc").children("option").each(function(){
						jQuery(this).attr("title",jQuery(this).text());
					});
				},500);
				jQuery("#lbdm_td").html(jQuery("#lbdm").val());
			})
		</script>
	</head>
	<body >
		<html:form action="/kycxgl_kycxxm_kycxxmwhgl" method="post" styleId="kycxxmwhForm" onsubmit="return false;">
			<html:hidden property="lbdm" styleId="lbdm"/>
			<div class="open_win01">
				<table align="center" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>修改科研类别</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								科研类别代码
							</th>
							<td id="lbdm_td">
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>科研类别名称
							</th>
							<td>
								<html:text property="lbmc" styleId="lbmc" maxlength="25" styleClass="text_nor" />
							</td>
						</tr>
						
						<tr>
							<th><span class="red">*</span>审核流程</th>
							<td>
								<html:select property="splc" styleId="splc" disabled="false" style="width:280px;">
									<html:options collection="shlcList" property="splc" labelProperty="lcxx" />
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

