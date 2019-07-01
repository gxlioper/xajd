<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">

			jQuery(function(){
				
			});
			function saveForm(){
				var checks = document.getElementsByName("sfqy");
				var params = "";
				for (var i=0;i<checks.length;i++){
					if(checks[i].checked){
						//alert(checks[i].value);
						params+="'"+checks[i].value+"',";
					}
					
				}
				if(params.length>0){
					params = params.substring(0,params.length-1);
				}
				//alert(params);

				var url = "jxgl_jxxxwh.do?method=saveJzdj&params="+params;
				ajaxSubFormWithFun("jtqkdcForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
				});


				
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xszz_jtqkdc" method="post" styleId="jtqkdcForm">
			
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					
					<logic:iterate name="cjnjList" id="e" indexId="i">
					<tr>
						<th width="25%">
							<bean:write name="e" property="mc" />
						</th>
						<td width="75%">
							<logic:notEqual value="1" name="e" property="sfqy">
								<input type="checkbox" name="sfqy" value="<bean:write name="e" property="dm" />">启用
							</logic:notEqual>
							<logic:equal value="1" name="e" property="sfqy">
								<input type="checkbox" name="sfqy" value="<bean:write name="e" property="dm" />" checked>启用
							</logic:equal>
						</td>
					</tr>
					</logic:iterate>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
										保 存
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

