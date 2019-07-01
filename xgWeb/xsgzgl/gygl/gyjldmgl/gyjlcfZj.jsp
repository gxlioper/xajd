<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
		

		function cflbDivSave(){
			
			var doType = jQuery("#doType").val();
			
			if(doType!="update"){
				
				if($("cflbdm").value==""){
					alertInfo("请输入处分类别代码！");
					return false;
				}
			}
			
			if($("cflbmc").value.trim()==""){
				alertInfo("请输入处分类别名称！");
				return false;
			}
			//allNotEmpThenGo('gyglnew_gyjldmgl.do?method=gyjllbManage&doType='+doType);
			 var url = "gyglnew_gyjldmgl.do?method=gyjlcfManage&doType="+doType;
		      ajaxSubFormWithFun("gyjldmglForm",url,function(data){
		    	 if(data["message"]=="操作成功！"){
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
			var doType = jQuery("#doType").val();
			if(doType == "update"){
				jQuery("#cflbdm").readOnly="true";
				jQuery("#cflbdm").css("color","grey");
				jQuery("#cflbdm").focus(function(){
					  this.blur();
				});
			}
			jQuery("#cflbdm").bind("change",function(){
				var inValue=jQuery(this).val();
			    if(inValue!=""&&inValue.match(/^\d+\.{0,1}\d{0,3}$/)==null){			
			    	showAlert("代码只能为有效数字!");
			    	jQuery(this).val("");
			 	}
			});
		});
		</script>
	</head>
	<body >

		<html:form styleId="gyjldmglForm" action="/gyglnew_gyjldmgl" method="post">
			<input type="hidden" id="doType" name="doType" value="${doType}"/>
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
									<span class="red">*</span>处分类别代码
								</th>
								<td>
									<html:text property="cflbdm"  readonly="false" onkeypress="if(event.keyCode==13 ||event.keyCode==8 ){return false;}" styleId="cflbdm" maxlength="100" ></html:text>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>处分类别名称
								</th>
								<td>
									<html:text property="cflbmc" onkeypress="if(event.keyCode==13 ||event.keyCode==8 ){return false;}" styleId="cflbmc" maxlength="100" ></html:text>
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
										<button type="button" name="保存" onclick="cflbDivSave();return false;">
											保 存
										</button>
										<button type="button" name="取消" onclick="Close();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
		</html:form>
	</body>
</html>
