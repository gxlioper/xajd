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
				var gsmc = jQuery("#gsmc").val();
				if (jQuery.trim(gsmc) == ""){
					showAlert("公司名称不能为空！");
					return false;
				}
				var gslx = jQuery("#gslx").val();
				if (jQuery.trim(gslx) == ""){
					showAlert("请选择公司类型！");
					return false;
				}
				var zcsj = jQuery("#zcsj").val();
				if (jQuery.trim(zcsj) == ""){
					showAlert("请选择注册时间！");
					return false;
				}
				var zczb = jQuery("#zczb").val();
				if (jQuery.trim(zczb) == ""){
					showAlert("注册资本不能为空！");
					return false;
				}
				var sshy = jQuery("#sshy").val();
				if (jQuery.trim(sshy) == ""){
					showAlert("请选择所属行业！");
					return false;
				}
				var url = "jyglnew_jygl_cyjyyzdgl.do?method=updateCyjyyzd&type="+type;
		      	ajaxSubFormWithFun("cyjyyzdForm",url,function(data){
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
		<html:form action="/jyglnew_jygl_cyjyyzdgl" method="post" styleId="cyjyyzdForm">
			<html:hidden property="jyid"/>
			<html:hidden property="xh"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:330px;margin-bottom: 0px;" >
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
								<span>就业创业教育与指导信息</span>
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
							<th width=""><font class="red">*</font>公司名称</th>
							<td width="" colspan="3">
								<html:text property="gsmc" styleId="gsmc" maxlength="80" style="width:521px;" />
							</td>
					    </tr>
						<tr>
							<th width=""><font class="red">*</font>公司类型</th>
							<td width="">
								<html:select property="gslx" styleId="gslx" style="width:150px;">
									<html:options collection="gslxList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
							<th width=""><font class="red">*</font>注册时间</th>
							<td width="">
								<html:text property="zcsj" styleId="zcsj" onclick="return showCalendar('zcsj','yyyy-MM-dd');" readonly="true" ></html:text>
							</td>
					    </tr>
						<tr>
							<th width=""><font class="red">*</font>注册资本</th>
							<td width="">
								<html:text property="zczb" styleId="zczb" maxlength="10" style="" onkeyup="checkInputData(this);"/>
							</td>
							<th width="">就业人数</th>
							<td width="">
								<html:text property="jyrs" styleId="jyrs" maxlength="10" style="" onkeyup="checkInputData(this);"/>
							</td>
					    </tr>
					    <tr>
					    	<th width=""><font class="red">*</font>所属行业</th>
							<td width="" colspan="3">
								<html:select property="sshy" styleId="sshy" style="width:150px;">
									<html:options collection="sshyList" property="lxdm" labelProperty="lxmc" />
								</html:select>
							</td>
					    </tr>
					</tbody>
				 </table>
				</div>
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

