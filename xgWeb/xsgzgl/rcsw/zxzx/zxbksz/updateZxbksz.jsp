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
				var bkmc = jQuery("#bkmc").val();
				if (jQuery.trim(bkmc) == ""){
					showAlert("版块名称不能为空！");
					return false;
				}	
				var xssx = jQuery("#xssx").val();
				if (jQuery.trim(xssx) == ""){
					showAlert("显示顺序不能为空！");
					return false;
				}
				var url = "rcsw_zxzx_zxbkszgl.do?method=updateZxbksz&type="+type;
		      	ajaxSubFormWithFun("zxbkszForm",url,function(data){
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
		<html:form action="/rcsw_zxzx_zxbkszgl" method="post" styleId="zxbkszForm">
			<html:hidden property="bkid"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>咨询版块信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width=""><font class="red">*</font>版块名称</th>
							<td width="">
								<html:text property="bkmc" styleId="bkmc" maxlength="25" style="width:273px;"/>
							</td>
					    </tr>
						<tr>
							<th width=""><font class="red">*</font>显示顺序</th>
							<td width="">
								<html:text property="xssx" styleId="xssx" maxlength="3" style="width:273px;" onkeyup="checkInputData(this);"/>
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

