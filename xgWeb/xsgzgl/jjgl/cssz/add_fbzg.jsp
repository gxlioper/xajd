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
				var fbzgmc=jQuery("#fbzgmc").val();
				var fbzgdm=jQuery("#fbzgdm").val();
				if(fbzgmc=="" || fbzgdm==""){
					showAlert("请将带*的项目填写完整！");
					return false;
				}
				var url = "jjgl_fbzg.do?method=add";
				ajaxSubFormWithFun("jjglFbzgForm",url,function(data){
							refershParent();
						});
			}
		</script>
	</head>
	<body >
		<html:form action="/jjgl_fbzg" method="post" styleId="jjglFbzgForm" onsubmit="return false;">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>增加发布资格</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>代码
							</th>
							<td width="34%">
								<html:text property="fbzgdm" styleId="fbzgdm" maxlength="25" />
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>名称
							</th>
							<td width="34%">
								<html:text property="fbzgmc" styleId="fbzgmc" maxlength="25" />
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>费用
							</th>
							<td width="34%">
								<html:text property="fy" styleId="fy" maxlength="25" />
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>说明
							</th>
							<td width="34%">
								<html:textarea property="fbzgms" styleId="fbzgms" rows="3" style="width:80%"></html:textarea>
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

