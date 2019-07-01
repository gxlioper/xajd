<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
			});
			function save(){
				if(!checkNull("tableName-key-name")){
					return false;
				}
				var url="import.do?method=autoset&type=save";
			 	jQuery("#form").ajaxSubmit({
					url:url,
					type:"post",
					dataType:"json",
					success:function(data){
			 			 showAlert(data["message"]);
					},
					contentType:"application/x-www-form-urlencoded;charset=utf-8"
				});	
			}
		</script> 
	</head>
	<body>
		<html:form styleId="form" action="/import.do?method=toImportData"
			method="post">
			<div class="tab">
				<table width="100%" border="0" class=" formlist" cellpadding="0"
					cellspacing="0">
					<thead>
						<tr>
							<td colspan="2">
								<span>导入自动配置</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<font color="red">*</font>对应表名
							</th>
							<td>
								<input type="text" name="tableName" id="tableName"/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>唯一标志key
							</th>
							<td>
								<input type="text" name="key" id="key"/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>模板名称
							</th>
							<td>
								<input type="text" name="name" id="name"/>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<button name="btn_tj" onclick="save();" type="button">
										确定
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
