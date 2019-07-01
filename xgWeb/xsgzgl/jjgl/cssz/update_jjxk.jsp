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
				var jjxkmc=jQuery("#jjxkmc").val();
				if(jjxkmc==""){
					showAlert("请将带*的项目填写完整！");
					return false;
				}
				var url = "jjgl_jjxk.do?method=update";
				ajaxSubFormWithFun("jjglJJxkForm",url,function(data){
							refershParent();
						});
			}
		</script>
	</head>
	<body >
		<html:form action="/jjgl_jjxk" method="post" styleId="jjglJJxkForm" onsubmit="return false;">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>修改家教学科</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>学科名称
							</th>
							<td width="34%">
								<html:hidden property="jjxkdm" value="${dataModel.jjxkdm}"/>
								<html:text property="jjxkmc" styleId="jjxkmc" maxlength="25" value="${dataModel.jjxkmc}"/>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();return false;">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();return false;">
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

