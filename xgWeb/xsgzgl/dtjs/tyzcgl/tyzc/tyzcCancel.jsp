<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/comm/searchTj.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				// ===== 获取高级查询条件 begin========
				var api = frameElement.api, W = api.opener;
				var html = "<div class='adv_filter' style='display: none;'>" + jQuery("div[class=adv_filter]", W.document).eq(0).html() + "</div>";
				html += "<div id='searchTjDiv' style='display: none;'>" + jQuery("#searchTjDiv", W.document).html() + "</div>";
				jQuery("#cxtjPlHidden").html(html);
				// ===== 获取高级查询条件 end========	
			});
		
			function saveForm(){
				// 获取高级查询条件，并隐藏，便于【根据勾选记录进行批量操作】使用
				var api = frameElement.api, W = api.opener;
				var pksPlHidden = jQuery("#pksPlHidden", W.document).val();
				jQuery("#pksPlHidden").val(pksPlHidden);
				var url = "dtjs_tyzc.do?method=tyzcCancel&type=save";
				// 如果为空，那么【根据查询结果进行批量操作】
				if(pksPlHidden == ""){
					//setSearchTj();//设置高级查询条件
					url = addSuperSearchParams(url);//设置高级查询参数
				}
				ajaxSubFormWithFun("tyzcForm",url,function(data){
		    		  showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
				});
			}
		</script>
	</head>
	<body>
		<html:form action="/dtjs_tyzc" method="post" styleId="tyzcForm">
			<div id="cxtjPlHidden" style="height: 0px;"></div>
			<input type="hidden" name="pks" id="pksPlHidden" value="" />
			<input type="hidden" id="path" value="${path }" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:31px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
							<th width="15%">
								提示：
							</th>
							<td width="85%">
								当前共选<font class='red'>${len }</font>个学生，您确定要撤销注册吗？
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
										确定 
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