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
				
				if (jQuery.trim(jQuery("#djmc").val()) == ""){
					showAlertx("请将必填项填写完整。");
					return false;
				}
				
				jQuery.post("xsxxDyxjZpdj.do?method=getCountByDjmc",{djmc:jQuery("#djmc").val()},function(data){
					
					if (Number(data) == 0){
						var url = "xsxxDyxjZpdj.do?method=save";
						ajaxSubFormWithFun("dmwhForm",url,function(data){
							showAlertx(data["message"]);
							refershParent();
						});
					} else {
						showAlertx("该自评等级已存在。");
					}
					
				},"json");
				
			}
		</script>
	</head>
	<body>
		<html:form action="/xsxxDyxjZpdj" method="post" styleId="dmwhForm">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>增加</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>评定等级
							</th>
							<td width="34%">
								<html:text property="djmc" styleId="djmc" maxlength="20" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th>
								项目说明
							</th>
							<td>
								<html:text property="xmsm" styleId="xmsm" maxlength="50" styleClass="text_nor" />
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

