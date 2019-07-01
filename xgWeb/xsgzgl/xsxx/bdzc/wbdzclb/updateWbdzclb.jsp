<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
		<script language="javascript">
			function updateForm(){ 
				if(!checkNull("wbdlbmc")){
					return false;
			  	}
	  				var url = "xsxx_wbdlbgl.do?method=updateWbdzclb&type=update"
	  				ajaxSubFormWithFun("wbdzclbForm",url,function(data){
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
	<body >
		<html:form action="/xsxx_wbdlbgl" method="post" styleId="wbdzclbForm" onsubmit="return false;">
			 <html:hidden property="wbdlbdm"  styleId="wbdlbdm"/>
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span></span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									类别代码
								</th>
								<td>
									<span>${model.wbdlbdm}</span>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>类别名称
								</th>
								<td>
									<html:text property="wbdlbmc" styleId="wbdlbmc" maxlength="25" styleClass="text_nor" />
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
										<button type="button" name="保存" onclick="updateForm();return false;">
											保 存
										</button>
										<button type="button" name="关 闭" onclick="iFClose();return false;">
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

