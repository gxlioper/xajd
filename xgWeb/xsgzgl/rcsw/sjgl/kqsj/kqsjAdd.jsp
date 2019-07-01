<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript">
		function saveForm(){
			var xn = jQuery("#xn").val();
			var kkxs = jQuery("#kkxs").val();
			var xh = jQuery("#xh").val();
			var cdztcs = jQuery("#cdztcs").val();
			if (jQuery.trim(xh) == ""){
				showAlert("请输入学号！");
				return false;
			}
			if (jQuery.trim(kkxs) == ""){
				showAlert("请输入旷课学时！");
				return false;
			}	
			if (jQuery.trim(xn) == ""){
				showAlert("请输入学年！");
				return false;
			}
			if (jQuery.trim(cdztcs) == ""){
				showAlert("请输入迟到早退次数！");
				return false;
			}
			var url = "kqsj.do?method=addKqsj&type=save";
	      	ajaxSubFormWithFun("KqsjForm",url,function(data){
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
	<body style="width: 100%">
		<html:form action="/kqsj" method="post" styleId="KqsjForm" onsubmit="return false;">
			<input type="hidden" id="type" value="${type }" />
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>

					<%@ include file="/xsgzgl/xszz/bdpz/selectStudent.jsp"%>

					<thead>
						<tr>
							<th colspan="4">
								<span>考勤数据维护 </span>
							</th>
						</tr>
					</thead>					
					    <tr>
							<th><span class="red">*</span>学年</th>
							<td colspan="3">
								<html:select  property="xn" styleId="xn" style="width:200px">
									<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>旷课学时</th>
							<td>
								<html:text property="kkxs" styleId="kkxs" style="width:200px;" onkeyup="value=value.replace(/[^\d]/g,'');"/>
							</td>
							<th><span class="red">*</span>迟到早退次数</th>
							<td>
								<html:text property="cdztcs" styleId="cdztcs" style="width:200px;" onkeyup="value=value.replace(/[^\d]/g,'');"/>				
							</td>
						</tr>
						<tr>
							<th>第一学期事假天数</th>
							<td>
								<html:text property="dyxqsjts" styleId="dyxqsjts" style="width:200px;" onkeyup="value=value.replace(/[^\d]/g,'');"/>
							</td>
							<th>第二学期事假天数</th>
							<td>
								<html:text property="dexqsjts" styleId="dexqsjts" style="width:200px;" onkeyup="value=value.replace(/[^\d]/g,'');"/>				
							</td>
						</tr>						
						<tr>
							<th>第一学期病假天数</th>
							<td>
								<html:text property="dyxqbjts" styleId="dyxqbjts" style="width:200px;" onkeyup="value=value.replace(/[^\d]/g,'');"/>
							</td>
							<th>第二学期病假天数</th>
							<td>
								<html:text property="dexqbjts" styleId="dexqbjts" style="width:200px;" onkeyup="value=value.replace(/[^\d]/g,'');"/>				
							</td>
						</tr>	
				</table>
			</div>
			<div>	
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" id="save_button" type="button"
										onclick="saveForm();">
										保存
									</button>
									<button type="button" name="关 闭" onclick="iFClose();">
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

