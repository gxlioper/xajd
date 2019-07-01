<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/comm/message.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
			function saveForm(){	  
				  var mc=jQuery("#xmlbmc").val();
				  var dm=jQuery("#xmlbdm").val();
				  if(jQuery.trim(dm)==""){
					  showAlert("请输入项目类别代码！");
						return false;
				  }
				  if(jQuery.trim(mc)==""){
					  showAlert("请输入项目类别名称！");
						return false;
				  }
			   var url = "ystglDmwh.do?method=addXmlb&type=save";
			    ajaxSubFormWithFun("YstglDmwhForm",url,function(data){
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
		<html:form action="/ystglDmwh" method="post"
			styleId="YstglDmwhForm" onsubmit="return false;">

			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table align="center" class="formlist">
					<tbody>
						<tr>
							<th>
								<span class="red">*</span>艺术团类别名称
							</th>
							<td>
								<html:select property="ystlbdm" styleId="ystlbdm" style="width:150px">
									<html:options collection="ystlbList" property="ystlbdm"
										labelProperty="ystlbmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>项目类别代码
							</th>
							<td>
								<html:text property="xmlbdm" styleId="xmlbdm" maxlength="10" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')"
									styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>项目类别名称
							</th>
							<td>
								<html:text property="xmlbmc" styleId="xmlbmc" maxlength="20" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')"
									styleClass="text_nor" />
							</td>
						</tr>
					</tbody>
					</table>
			      </div>
			      <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" type="button"
										onclick="saveForm();return false;">
										保 存
									</button>
									<button type="button" type="button"
										onclick="iFClose();return false;">
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

