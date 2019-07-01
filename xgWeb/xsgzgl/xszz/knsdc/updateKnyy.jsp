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
			  var yymc=jQuery("#yymc").val();
			  if(yymc==""){
				  showAlert("请将带*的项目填写完整！");
				  return false;
			  }
			  if(yymc.length>200){
				  showAlert("最多输入200字！");
				  return false;
			  }
		     var url = "xszz_knsdc.do?method=updateKnyy&type=update";
		      ajaxSubFormWithFun("knsdcForm",url,function(data){
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
		function checkxuhao(obj) {
			var text = obj.value;
			if (null !== text && ''!= text) {
				if (/[^\d]/.test(text)) {
					showAlert("您输入的数字不符合规则!",{},{"clkFun":function(){
						obj.focus();
					}});
				}
			}
		}
		</script>
	</head>
	<body >
		<html:form action="/xszz_knsdc" method="post" styleId="knsdcForm" onsubmit="return false;">
		<html:hidden property="yydm" styleId="yydm" />
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>修改困难原因</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								序号
							</th>
							<td width="34%">
								<html:text property="xh" maxlength="4" styleId="xh" onkeyup="value=value.replace(/[^\d]/g,'')" onblur="checkxuhao(this);"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>原因名称
							</th>
							<td width="34%">
								<html:textarea property="yymc" styleId="yymc"  style="width:99%;"  onkeypress="checkLen(this,200);"></html:textarea>
							</td>
						</tr>
					</tbody>
					<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
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

