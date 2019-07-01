<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/comm/searchTj.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
			jQuery(document).ready(function(){
				jQuery('#tggw').html("当前共选<font class='red'>${len }</font>个岗位");

				// ===== 获取高级查询条件 begin========
				var api = frameElement.api, W = api.opener;
				var html = "<div class='adv_filter' style='display: none;'>" + jQuery("div[class=adv_filter]", W.document).eq(0).html() + "</div>";
				html += "<div id='searchTjDiv' style='display: none;'>" + jQuery("#searchTjDiv", W.document).html() + "</div>";
				jQuery("#cxtjPlHidden").html(html);
				// ===== 获取高级查询条件 end========
			});

			//保存岗位批量退岗
			function bcTggwxx(){
				if($("tgyy") && $("tgyy").value==""){
					showAlert("退岗原因不能为空！");
			 		return false;
				}
				// 获取高级查询条件，并隐藏，便于【根据勾选记录进行批量操作】使用
				var api = frameElement.api, W = api.opener;
				var gwdmPlHidden = jQuery("#gwdmPlHidden", W.document).val();
				jQuery("#gwdmPlHidden").val(gwdmPlHidden);
				var url = "qgzx_gwglnew_ajax.do?method=bcTggwxx";
				// 如果为空，那么【根据查询结果进行批量操作】
				if(gwdmPlHidden == ""){
					//setSearchTj();//设置高级查询条件
					url = addSuperSearchParams(url);//设置高级查询参数
				}
				ajaxSubFormWithFun("gwxxTgWinForm",url,function(data){
				   	if(data["message"]=="操作成功"){
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
		<html:form action="/qgzx_gwglnew" method="post" styleId="gwxxTgWinForm">
			<div id="cxtjPlHidden" style="height: 0px;"></div>
			<input type="hidden" name="gwdm" id="gwdmPlHidden" value="" />
			<input type="hidden" id="path" value="${path }" />
			<div style="height:190px;overflow-x:hidden;overflow-y:auto;">
				<table align="center" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>批量退岗</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								已选岗位
							</th>
							<td>
								<font id="tggw"></font>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>退岗原因<br/><font color="red">(限1000字)</font>
							</th>
							<td>
								<textarea name='tgyy' id="tgyy" style="word-break:break-all;width:97%" onblur="chLeng(this,1000);"
									rows='5'></textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" name="保存" onclick="bcTggwxx();">
										保存
									</button>
									<button type="button" name="关 闭" onclick="iFClose();">
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

