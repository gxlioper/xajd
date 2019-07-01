<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function saveForm(){
				if (jQuery.trim(jQuery("#xmmc").val()) == "" 
					|| jQuery.trim(jQuery("#jcdx").val()) == ""){
					showAlert("请将带*号的项目填写完整！");
					return false;
				}
				
				var url = "wsjcJcxm.do?method=save";
				ajaxSubFormWithFun("form",url,function(data){
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
		<html:form action="/wsjcJcxm" method="post" styleId="form">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>检查项目</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th>
								<font color="red">*</font>项目名称
							</th>
							<td>
								<html:text property="xmmc" styleId="xmmc" maxlength="25" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th>
								项目内容<br/>
								<font color="red">（限200字）</font>
							</th>
							<td>
								<html:textarea property="xmnr" styleId="xmnr"
											   onblur="checkLen(this,200);"
											   style="width:99%;" rows="4">
								</html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>检查对象
							</th>
							<td>
								<html:select property="jcdx" styleId="jcdx">
									<html:option value=""></html:option>
									<html:option value="0">寝室</html:option>
									<html:option value="1">床位</html:option>
								</html:select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
										保 存
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

