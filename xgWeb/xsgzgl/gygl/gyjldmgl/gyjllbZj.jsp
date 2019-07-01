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
		

		function jllbDivSave(){
			var xxdm = jQuery("#xxdm").val();
			
			var doType = jQuery("#doType").val();
			
			if(doType!="update"){
				if($("jllbdm").value==""){
					if(xxdm == '11799'){
						alertInfo("请输入奖惩类别代码！");
					}else{
						alertInfo("请输入纪律类别代码！");
					}
					return false;
				}
			}
			if($("jllbmc").value.trim()==""){
				if(xxdm == '11799'){
					alertInfo("请输入奖惩类别名称！");
				}else{
					alertInfo("请输入纪律类别名称！");
				}
				return false;
			}
			if($("jllbkf").value.trim()==""){
				if(xxdm == '11799'){
					alertInfo("请输入奖惩类别扣分数！");
				}else{
					alertInfo("请输入纪律类别扣分数！");
				}
				return false;
			}
			if($("jldldm").value.trim()==""){
				if(xxdm == '11799'){
					alertInfo("请选择奖惩大类！");
				}else{
					alertInfo("请选择纪律大类！");
				}
				return false;
			}
			//allNotEmpThenGo('gyglnew_gyjldmgl.do?method=gyjllbManage&doType='+doType);
			 var url = "gyglnew_gyjldmgl.do?method=gyjllbManage&doType="+doType;
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
				jQuery("#jllbdm").readOnly="true";
				jQuery("#jllbdm").css("color","grey");
				jQuery("#jllbdm").focus(function(){
					  this.blur();
				});
			}
			jQuery("#jllbdm").bind("change",function(){
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
										奖惩类别代码
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										纪律类别代码
									</logic:notEqual>
								</th>
								<td>
									<%--<input type="text" id="jllbdm" name="jllbdm" maxlength="100"/>
									--%><html:text property="jllbdm"   styleId="jllbdm" maxlength="100" ></html:text>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>
									<logic:equal value="11799" name="xxdm">
										奖惩类别名称
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										纪律类别名称
									</logic:notEqual>
								</th>
								<td>
									<%--<input type="text" id="jllbmc" name="jllbmc" maxlength="100"/>
									--%><html:text property="jllbmc" onkeypress="if(event.keyCode==13 ||event.keyCode==8 ){return false;}" styleId="jllbmc" maxlength="100" ></html:text>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>
									<logic:equal value="11799" name="xxdm">
										奖惩类别扣分
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										纪律类别扣分
									</logic:notEqual>
								</th>
								<td>
									
									<html:text property="jllbkf"  styleId="jllbkf" onkeyup="clearNoNum(this)" maxlength="100" ></html:text>
								</td>
							</tr>
							<tr id="tr_select_pfdx">
								<th>
									<span class="red">*</span>
									<logic:equal value="11799" name="xxdm">
										所属奖惩大类
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">									
										所属纪律大类
									</logic:notEqual>
								</th>
								<td>
									<html:select property="jldldm" styleId="jldldm" style="width:150px;">
										<html:option value=""></html:option>
										<html:options collection="jldlList" property="jldldm" labelProperty="jldlmc" />
									</html:select>
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
										<button type="button" name="保存" onclick="jllbDivSave()">
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
