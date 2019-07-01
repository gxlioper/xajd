<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript">
			function saveForm(){

				var xhArray = jQuery("textarea[name=xhArray]");
				var textareaFlg = true;
				jQuery.each(xhArray,function(i,e){
					var textareaVal = jQuery(e).val();
					if (textareaVal == null || textareaVal == ''){
						textareaFlg = false;
						return;
					}
				});
				if (!textareaFlg){

					showAlert("请所有输入项填写完整！");
					return false;
				}
				
				ajaxSubFormWithFun("cjtdForm","tdjs.do?method=saveFpxs",function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						refershParent();
					}});
				});
			}

			jQuery(function(){

				var demoHtml = "请按如下格式输入学生的学号\r\n\r\n";
					demoHtml+="例如：\r\n";
					demoHtml+="20110019\r\n20100019\r\n20090026";
					demoHtml+="\r\n或者：\r\n";
					demoHtml+="20110019 20100019 20090026";

				jQuery("textarea").val(demoHtml);
				jQuery("body").focus();
				
				jQuery("textarea").bind("focus",function(){
					if (jQuery(this).css("color") == "#999"){
						jQuery(this).val("");
						jQuery(this).css("color","");
					}
				});

				jQuery("textarea").bind("blur",function(){
					if (jQuery(this).val() == ""){
						jQuery(this).val(demoHtml);
						jQuery(this).css("color","#999");
					}
				});
			})
			
		</script>
	</head>
	<body style="width:97%">
		<input type="text" style="display:none;" name="temp"/>
		<html:form action="/tdjs" method="post" styleId="cjtdForm">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th>
								<span>分配学生</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:present name="tdInfoList">
							<logic:iterate id="t" name="tdInfoList">
								<tr>
									<td>
										年级：<font color="blue">${t.nj }</font>&nbsp;
										<bean:message key="lable.xb" />：<font color="blue">${t.bmmc }</font>&nbsp;
										专业：<font color="blue">${t.zymc }</font>&nbsp;
										团队：<font color="blue">${t.bjmc }</font>&nbsp;
										指导老师：<font color="blue">${t.xm }</font>&nbsp;
									</td>
								</tr>
								<tr>
									<td>
										<html:hidden property="tdArray" value="${t.bjdm}"/>
										<html:textarea property="xhArray" rows="5" style="width:98%;color:#999;"></html:textarea>
									</td>
								</tr>
							</logic:iterate>						
						</logic:present>
					</tbody>
					<tfoot>
						<tr>
							<td>
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
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

