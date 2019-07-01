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
		
		<script	type="text/javascript">
		function saveForm(){

			if(!checkNull("xh-xn-xq-lxdm-sqly")){
				return false;
			}
			var	 url = "rcsw_lstd_jggl.do?method=addLstdsqjg&type=save";
		     ajaxSubFormWithFun("LstdjgForm",url,function(data){
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
		
		<body>
		
		<html:form action="/rcsw_lstd_jggl" method="post" styleId="LstdjgForm" onsubmit="return false">
			
			
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/lstd/comm/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						 <tr>
							<th><span class="red">*</span>学年</th>
							<td>
								<html:select  property="xn" styleId="xn" style="width:130px">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th><span class="red">*</span>学期</th>
							<td>
								<html:select  property="xq" styleId="xq" style="width:130px">
								<html:option value=""></html:option>
								<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>申请理由</th>
							<td>
								<html:select  property="lxdm" styleId="lxdm" style="width:130px">
								<option value=''></option>
								<html:options collection="lxwhList" property="lxdm" labelProperty="lxmc" />
								</html:select>
							</td>
							<th><span class="red">*</span>申请时间</th>
							<td >
								<html:hidden property="sqsj" />
								<bean:write name="lstdjgForm" property="sqsj"/>
							
							</td>
					    </tr>
					    <tr>
							<th>
								<span class="red">*</span>详情描述
								<br /><font color="red">(限1000字)</font>
							</th>
							<td colspan="3">
								<html:textarea property='sqly' style="width:98%" styleId="sqly" rows='7' onblur="checkLen(this,100);"/>
							</td>
			      </tr>
					</tbody>
				</table>
			</div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
										保  存
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

