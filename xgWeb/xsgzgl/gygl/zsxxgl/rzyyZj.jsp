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
		

		function jldlDivSave(){
			
			var doType = jQuery("#doType").val();
			
			if(doType!="update"){
				if($("rzyydm").value==""){
					alertInfo("请输入入住原因代码！");
					return false;
				}
			}
			if($("rzyymc").value.trim()==""){
				alertInfo("请输入入住原因名称！");
				return false;
			}

			 var url = "gyglnew_zsxxgl.do?method=zsxxgldmManage&doType="+doType;
		      ajaxSubFormWithFun("gyglnewZsxxglForm",url,function(data){
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
				jQuery("#rzyydm").readOnly="true";
				jQuery("#rzyydm").css("color","grey");
			}
		});
		</script>
	</head>
	<body >

		<html:form styleId="gyglnewZsxxglForm" action="/gyglnew_zsxxgl" method="post">
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
									<span class="red">*</span>入住原因代码
								</th>
								<td>
									<input type="text" id="rzyydm"  name="rzyydm" value="${rzyydm}" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>入住原因名称
								</th>
								<td>
									<input type="text" id="rzyymc" name="rzyymc" value="${rzyymc}" maxlength="100"/>
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
