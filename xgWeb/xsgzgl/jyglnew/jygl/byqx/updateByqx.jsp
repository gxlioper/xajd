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
		<script	type="text/javascript">
			function saveForm(type){
				var jydw = jQuery("#jydw").val();
				if (jQuery.trim(jydw) == ""){
					showAlert("就业单位不能为空！");
					return false;
				}
				var jydwxz = jQuery("#jydwxz").val();
				if (jQuery.trim(jydwxz) == ""){
					showAlert("请选择就业单位性质！");
					return false;
				}
				var pqdw = jQuery("#pqdw").val();
				if (jQuery.trim(pqdw) == ""){
					showAlert("派遣单位不能为空！");
					return false;
				}
				var jyxs = jQuery("#jyxs").val();
				if (jQuery.trim(jyxs) == ""){
					showAlert("请选择就业形式！");
					return false;
				}
				var url = "jyglnew_jygl_byqxgl.do?method=updateByqx&type="+type;
		      	ajaxSubFormWithFun("byqxForm",url,function(data){
		    	 if(data["message"]=="保存成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	 }});
		  	}
			jQuery(function(){
			});
		</script>
	</head>
	<body>
		<html:form action="/jyglnew_jygl_byqxgl" method="post" styleId="byqxForm">
			<html:hidden property="xh"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:430px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xszbb/comm/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>毕业去向信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th width="20%">手机号码</th>
							<td width="30%">
								<html:text property="sjhm" styleId="sjhm" maxlength="11" style="" onkeyup="checkInputData(this);"/>
							</td>
							<th width="20%">QQ号码</th>
							<td width="30%">
								<html:text property="qqhm" styleId="qqhm" maxlength="12" style="" onkeyup="checkInputData(this);"/>
							</td>
						</tr>
						<tr>
							<th width=""><font class="red">*</font>
							<logic:equal value="70002" name="xxdm">
								实际工作单位
							</logic:equal>
							<logic:notEqual value="70002" name="xxdm">							
								就业单位
							</logic:notEqual>
							</th>
							<td width="">
								<html:text property="jydw" styleId="jydw" maxlength="80" style="" />
							</td>
							<th width=""><font class="red">*</font>就业单位性质</th>
							<td width="">
								<html:select property="jydwxz" styleId="jydwxz" style="width:150px;">
									<html:options collection="jydwxzList" property="lxdm" labelProperty="lxmc" />
								</html:select>
							</td>
					    </tr>
						<tr>
							<th width=""><font class="red">*</font>派遣单位</th>
							<td width="">
								<html:text property="pqdw" styleId="pqdw" maxlength="80" style="" />
							</td>
							<th width=""><font class="red">*</font>就业形式</th>
							<td width="">
								<html:select property="jyxs" styleId="jyxs" style="width:150px;">
									<html:options collection="jyxsList" property="lxdm" labelProperty="lxmc" />
								</html:select>
							</td>
					    </tr>
					    <!-- 南京高等职业技术学校 -->
					    <logic:equal value="10874" name="xxdm">					    
						    <tr>
						    	<th>学历</th>
								<td>
									<html:text property="xl" styleId="xl" maxlength="20" style="" />
								</td>
								<th>学位</th>
								<td>
									<html:text property="xw" styleId="xw" maxlength="20" style="" />
								</td>
						    </tr>
					    </logic:equal>
					    
					     <!-- 徐州医药高等职业学校 -->
					    <logic:equal value="70002" name="xxdm">					    
						    <tr>
						    	<th>毕业去向</th>
								<td>
									<html:select property="byqx" styleId="byqx" style="width:150px;">
										<html:options collection="byqxList" property="lxdm" labelProperty="lxmc" />
									</html:select>
								</td>
								<th>就业类别</th>
								<td>
									<html:select property="jylb" styleId="jylb" style="width:150px;">
										<html:options collection="jylbList" property="lxdm" labelProperty="lxmc" />
									</html:select>
								</td>
						    </tr>
						    <tr>
						    	<th>就业状况</th>
						    	<td>
						    		<html:select property="jyzk" styleId="jyzk" style="width:150px;">
										<html:options collection="jyzkList" property="lxdm" labelProperty="lxmc" />
									</html:select>
						    	</td>
						    	<th>报到证号</th>
						    	<td>
						    		<html:text property="bdzh" styleId="bdzh" maxlength="20"></html:text>
						    	</td>
						    </tr>
						    <tr>
						    	<th>派遣时间</th>
						    	<td>
						    		<html:text property="pqsj" styleId="pqsj" onfocus="showCalendar('pqsj','y-mm-dd');"></html:text>
						    	</td>
						    	<th>学生档案邮递单位</th>
						    	<td>
						    		<html:text property="tddw" styleId="tddw" maxlength="20"></html:text>
						    	</td>
						    </tr>
						     <tr>
						    	<th>档案转递时间</th>
						    	<td>
						    		<html:text property="zdsj" styleId="zdsj" onfocus="showCalendar('zdsj','y-mm-dd');"></html:text>
						    	</td>
						    	<th></th>
						    	<td></td>
						    </tr>
						    <tr>
						    	<th>备注</th>
						    	<td colspan="3">
						    		<html:textarea property="bz" styleId="bz" rows="8" style="width:99%"></html:textarea>
						    	</td>
						    </tr>
					    </logic:equal>
					</tbody>
				 </table>
				</div>
				<div style="height: 20px"></div>
				<div>
					<table width="100%" border="0" class="formlist">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">"<span class="red">*</span>"为必填项</div>
									<div class="btn">
										<button type="button" type="button" onclick="saveForm('save');">
											保存
										</button>
										<button type="button" type="button" onclick="iFClose();">
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

