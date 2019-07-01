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
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script language="javascript">
		

		function jldlDivSave(){
			var xxdm = window.parent.document.getElementById('xxdm').value;

			var doType = jQuery("#doType").val();
			
			if(doType!="update"){
				if($("jldldm").value==""){
					if(xxdm == '11799'){
						alertInfo("请输入奖惩大类代码！");
					}else{
						alertInfo("请输入纪律大类代码！");
					}
					return false;
				}
			}
			if($("jldlmc").value.trim()==""){
				if(xxdm == '11799'){
					alertInfo("请输入奖惩大类名称！");
				}else{
					alertInfo("请输入纪律大类名称！");
				}
				return false;
			}

			 var url = "gyglnew_gyjldmgl.do?method=gyjldlManage&doType="+doType;
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
				jQuery("#jldldm").readOnly="true";
				jQuery("#jldldm").css("color","grey");
				jQuery("#jldldm").focus(function(){
					  this.blur();
				});
			}
			jQuery("#jldldm").bind("change",function(){
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
									<span class="red">*</span>
									<logic:equal value="11799" name="xxdm">
										奖惩大类代码
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										纪律大类代码
									</logic:notEqual>
								</th>
								<td>
									<input type="text" id="jldldm"  name="jldldm" value="${jldldm}" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>
									<logic:equal value="11799" name="xxdm">
										奖惩大类名称
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										纪律大类名称
									</logic:notEqual>									
								</th>
								<td>
									<input type="text" id="jldlmc" name="jldlmc" value="${jldlmc}" maxlength="100"/>
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
										<button type="button" name="保存" onclick="jldlDivSave();return false;">
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
