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
			var hdsc = jQuery("#hdsc").val();
			var id = jQuery("#id").val();
			if (jQuery.trim(xn) == ""){
				showAlert("请输入学年！");
				return false;
			}
			if (jQuery.trim(hdsc) == ""){
				showAlert("请输入活动时长！");
				return false;
			}	
			var url = "twsj.do?method=updateTwsj&type=update&id="+id;
	      	ajaxSubFormWithFun("TwsjForm",url,function(data){
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
		<html:form action="/twsj" method="post" styleId="TwsjForm" onsubmit="return false;">
			<input type="hidden" id="type" value="${type}" />
			<html:hidden property="id"  styleId="id"/>
			<html:hidden property="xh"  styleId="xh"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>

					<%@ include file="/xsgzgl/rcsw/hcyhkgl/comm/viewStudent.jsp" %>

					<thead>
						<tr>
							<th colspan="4">
								<span>团委数据维护 </span>
							</th>
						</tr>
					</thead>					
						<tr>
							<th><span class="red">*</span>学年</th>
							<td>
								<html:select  property="xn" styleId="xn" style="width:200px">
									<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th><span class="red">*</span>志愿者活动时长</th>
							<td>
								<html:text property="hdsc" styleId="hdsc" style="width:200px;" onkeyup="value=value.replace(/[^\d]/g,'');"/>天				
							</td>
						</tr>						
						<tr>
							<th>
								备注
								<br /><font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='bz' style="width:98%" styleId="bz" rows='9' onblur="checkLen(this,500);"/>
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

