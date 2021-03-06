<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/station/station.js"></script>
		<script type="text/javascript" src="js/station/city_name.js"></script>
		<script	type="text/javascript">
		function saveForm(obj){
			//先验证学号
			var xh = jQuery("#xh").val();
			var xn = jQuery("#xn").val();
			var xq = jQuery("#xq").val();
			var ccqdz = jQuery("#ccqdz").val();
			var cczdz = jQuery("#cczdz").val();
			//var bz = jQuery("#bz").val();
			if (jQuery.trim(xh) == ""){
				showAlert("请先选择学生！");
				return false;
			}	
			if (jQuery.trim(xn) == ""){
				showAlert("请先选择学年！");
				return false;
			}
			if (jQuery.trim(xq) == ""){
				showAlert("请先选择学期！");
				return false;
			}
			if (jQuery.trim(ccqdz) == ""){
				showAlert("请先输入乘车起点站！");
				return false;
			}
			if (jQuery.trim(cczdz) == ""){
				showAlert("请先输入乘车终点站！");
				return false;
			}
			if(jQuery("#xxdm").val() == "10351"){
				if (jQuery("#hcyhklx").val() == ""){
					showAlert("请选择申请原因！");
					return false;
				}
			}
			/*if (jQuery.trim(bz) == ""){
				showAlert("请先输入备注！");
				return false;
			}*/
			var url = "";
			if(obj == 'save'){
				 url = "rcsw_hcyhk_hcccqjtxgl.do?method=addHcccqjtx&type=save";
			}
			if(obj == 'submit'){
				 url = "rcsw_hcyhk_hcccqjtxgl.do?method=addHcccqjtx&type=submit";
			}
	      	ajaxSubFormWithFun("hcccqjtxForm",url,function(data){
		    	 if(data["message"]=="保存成功！" || data["message"]=="提交成功！"){
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
			citySelect("ccqdz");
			citySelect("cczdz");
		});
		</script>
		
	</head>
	<body>
		
		<html:form action="/rcsw_hcyhk_hcccqjtxgl" method="post" styleId="hcccqjtxForm">
		<input type="hidden" name="xn" id="xn" value="${xn}" />	
		<input type="hidden" name="xq" id="xq" value="${xq}" />
			<div style='tab;width:100%;height:415px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xszbb/comm/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>火车乘车区间信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th>学年</th>
							<td>
								${xn}
							</td>
							<th>学期</th>
							<td>
								${xqmc}
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>乘车区间</th>
							<td colspan="3">
								<html:text property="ccqdz" styleId="ccqdz" maxlength="15" ></html:text>
								～
								<html:text property="cczdz" styleId="cczdz" maxlength="15" ></html:text>
							</td>
					    </tr>
					    <logic:equal name="xxdm" value="10351">
					    	<th><span class="red">*</span>申请原因</th>
							<td colspan="3">
						    	<html:select property="hcyhklx" styleId="hcyhklx"  style="width: 200px" value="${hcyhklx}">
									<html:options collection="hcyhklxList" property="lxdm"
										labelProperty="lxmc" />
								</html:select>
							</td>
					    </logic:equal>
					      <tr>
							<th><span class="red">*</span>填写时间</th>
							<td colspan="3">
								<html:hidden property="txsj" />
								<bean:write name="hcccqjtxForm" property="txsj"/>
							
							</td>
					    </tr>
					    <tr>
							<th>
								备注
								<br /><font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='bz' style="width:98%" styleId="bz" rows='5' onblur="checkLen(this,500);"/>
							</td>
			      		</tr>
					</tbody>
				</table>
			</div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm('save');">
										保存草稿
									</button>
									
									<button type="button" type="button" onclick="saveForm('submit');">
										提交申请
									</button>
									
									<button type="button" type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>

