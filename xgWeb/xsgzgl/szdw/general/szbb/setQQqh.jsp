<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		
		//function onShow(){ 
		//	searchRs();
		//}
		//function savePlwh(){
		function checkNum(obj) {
			var text = obj.value;
			
			if (null !== text && ''!= text) {
				
				if (/[^\d]/.test(text)) {
					
					showAlertDivLayer("您输入的数字不符合规则!",{},{"clkFun":function(){
						obj.value = obj.value.replace(/[^\d]/g,"");
						obj.focus();
					}});
				}
			}
		}
		
		
		//}
		function savePlwh(){
			if(parseInt(jQuery("#qqqh").val()) == 0){
				showAlert("QQ群号必须是数字！");
				return false;
			}
			//ajaxSubFormWithFun("gyglnewQsglForm", "gyglnew_qsgl.do?method=saveplwhTypeOfQs", function(data) {
			//	 if(data["message"]=="保存成功！"){
		   //		 showAlert(data["message"],{},{"clkFun":function(){
			//				if (parent.window){
			//					refershParent();
			///				}
			//			}});
		    //	 }else{
		    //		 showAlert(data["message"]);
		    //		}
			//	});
			
			var url = "general_szdw.do?method=setQQqh&type=save";
		      ajaxSubFormWithFun("generalSzdwGeneralForm",url,function(data){
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
		<html:form action="/general_szdw" method="post" styleId="generalSzdwGeneralForm">
		<input type="hidden" name="bjdm" id="bjdm" value="${bjdm}" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>班级信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="25%">年级</th>
							<td width="25%" >
								<input type="text" name="nj" id="nj" class="text_nor" disabled ="true" value="${nj}">
							</td>
						</tr>
						<tr>
							<th width="25%">学院</th>
							<td width="25%" >
								<input type="text" name="xymc" id="xymc" class="text_nor" disabled ="true" value="${xymc}">
							</td>
						</tr>
						<tr>
							<th width="25%">专业</th>
							<td width="25%" >
								<input type="text" name="zymc" id="zymc" class="text_nor" disabled ="true" value="${zymc}">
							</td>
						</tr>
						<tr>
							<th width="25%">班级名称</th>
							<td width="25%" >
								<input type="text" name="bjmc" id="bjmc" class="text_nor" disabled ="true" value="${bjmc}">
							</td>
						</tr>
						<tr>
							<th width="25%">班级人数</th>
							<td width="25%" >
								<input type="text" name="rs" id="rs" class="text_nor" disabled ="true" value="${rs}">
							</td>
						</tr>
						<tr>
							<th width="25%">QQ群号</th>
							<td width="25%">
								<input type="text" name="qqqh"  onkeyup="checkNum(this);" id="qqqh" class="text_nor" value = "${qqqh}">
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="savePlwh();return false;">
										修   改
									</button>
									<button type="button" onclick="iFClose();return false;">
										关  闭
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