<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var api = frameElement.api;
			W = api.opener;
			var grid = W.jQuery("#dataTable");
			var rows = grid.getSeletRow();

			var type = jQuery("input[name='type']").val();

			var hidXhs = jQuery("input[name='plIds']");
			
			if(type == 'zc_select'){
				var plIds = [];
				for(var i = 0 ; i < rows.length; i++){
					plIds[i] = rows[i]['xh'];
				}

				hidXhs.val(plIds.join(','));
			}
	
			
		});
		
		function plXqzc(){

				var api = frameElement.api,W = api.opener;

				var zcsj = jQuery('#zcsj').val();
				var url = "xsxx_xqbdzcgl.do?method=doPlXqbdzc";
				if('' == zcsj){
					showAlert("请填写<bean:message key="lable.bdzc"/>时间！");
					return false;
				}
				var rows = W.jQuery('#dataTable').getSeletRow();

				var plIds = [];
				
				for(var i = 0 ; i < rows.length; i++){
					plIds[i] = rows[i]['xh'];
				}
				if(0==rows.length){
				var map = W.getSuperSearch();
				//高级查询
				//url +="&searchTj=";
				//url +=map["searchTj"];
				//url +="&searchTjz=";
				//url +=map["searchTjz"];
				//url +="&mhcx_lx=";
				//url +=map["mhcx_lx"];
				//url +="&searchLx=";
				//url +=map["searchLx"];

				//模糊查询
				//url +="&input_mhcx=";
				//url +=map["input_mhcx"];
				//url +="&mhcx_lx=";
				//url +=map["mhcx_lx"];
				map["zcsj"] = jQuery('#zcsj').val();
				jQuery.post(url,map,
					function(data){
						showAlert(data["message"] , {} , {'clkFun' : function(){
						W.jQuery("#dataTable").reloadGrid();
						closeDialog();
						}});
				
				},'json');
				}else{
					jQuery.post(url,{ plIds:plIds.join(","),zcsj : jQuery('#zcsj').val()},function(data){
						showAlert(data["message"] , {} , {'clkFun' : function(){
							W.jQuery("#dataTable").reloadGrid();
							closeDialog();
							}});
					
					},'json');
					}
				
			}
		</script>
	</head>
	<body>
		<html:form action="/xsxx_xqbdzcgl" method="post" styleId="xqbdzcPlForm">
			<html:hidden property="type"/>
			<html:hidden property="plIds"/>
			<div class='tab'>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="plXqzc('<bean:message key="lable.bdzc"/>');">
										确定 
									</button>
									
									<button type="button" type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="30%">
								提示：
							</th>
							<td width="70%">
							当前对 <span id="plcounts" style="color: blue">${rownum_wx}</span> 个学生进行批量<bean:message key="lable.bdzc"/><br/>
							(其中未处理<span style="color: blue">${rownum_w}</span>人,未<bean:message key="lable.bdzc"/><span style="color: blue">${rownum_x}</span>人.)
							</td>
						</tr>
						<tr>
							<th width="30%">
								<span class="red">*</span><bean:message key="lable.bdzc"/>时间:
							</th>
							<td width="70%">
								<input type="text" name="zcsj" value="${plsqsj}" id="zcsj"  style="width:80%" 
								onclick="return showCalendar('zcsj','yyyy-MM-dd',true);" 
								readonly="true"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>

