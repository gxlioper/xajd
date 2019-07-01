<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript">
		function blSubmit(){
			var hmdyy = jQuery.trim(jQuery("#hmdyy").val());
			if(hmdyy == '' || hmdyy == null){
				showAlert('请填写原因！');
				return false;
			}

			if(hmdyy.length > 200){
				showAlert('字数超过200，请确认！');
				return false;
			}
			//提交
			showConfirm("您确定要提交？",{"okFun" : function(){
				var url = "jjgl_zcyhgl.do?method=hmdSubmit";
				ajaxSubFormWithFun("jjgZcyhForm",url,function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
				});
			}});
		}
	</script>
  </head>
  
  <body>
		<html:form action="/jjgl_zcyhgl" method="post" styleId="jjgZcyhForm">
			<html:hidden property="yhm"/>
			<div class='tab' style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>黑名单</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
					    	<th>家长编号</th>
					    	<td ><bean:write name="jjglZcyhForm" property="yhm"/></td>
					    </tr>
					    <tr>
					    	<th><span class="red">*</span>原因<br/><font color="red">(限200字)</font></th>
					    	<td >
					    		<html:textarea styleId="hmdyy" property="hmdyy" rows="3" style="width:90%"></html:textarea>
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
									<button type="button" id="btqd" onclick="blSubmit();">
										提交
									</button>
									<button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
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
