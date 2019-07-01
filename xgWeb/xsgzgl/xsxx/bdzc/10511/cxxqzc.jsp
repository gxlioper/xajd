<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript">
		jQuery(function(){
			var api = frameElement.api;
			W = api.opener;
			var grid = W.jQuery("#dataTable");
			var rows = grid.getSeletRow();
			
			var type = jQuery("input[name='type']").val();

			var hidXhs = jQuery("input[name='plIds']");
			
			if(type == 'cx_select'){
				var plIds = [];
				for(var i = 0 ; i < rows.length; i++){
					plIds[i] = rows[i]['id'];
				}

				hidXhs.val(plIds.join(','));
			}
	
			
		});
		
	function cxXqzc(){
				var url="xsxx_xqbdzcgl.do?method=doCxXqbdzc";
				var api = frameElement.api,W = api.opener;
				
				var rows = W.jQuery('#dataTable').getSeletRow();

				var plIds = [];
				
				for(var i = 0 ; i < rows.length; i++){
					plIds[i] = rows[i]['id'];
				}
				if(0==rows.length){
				var map = W.getSuperSearch();
				//高级查询
//				url +="&searchTj=";
//				url +=map["searchTj"];
//				url +="&searchTjz=";
//				url +=map["searchTjz"];
//				url +="&mhcx_lx=";
//				url +=map["mhcx_lx"];
//				url +="&searchLx=";
//				url +=map["searchLx"];

				//模糊查询
//				url +="&input_mhcx=";
//				url +=map["input_mhcx"];
//				url +="&mhcx_lx=";
//				url +=map["mhcx_lx"];
				jQuery.post(url,map,
					function(data){
					showAlert(data["message"] , {} ,  {'clkFun' : function(){
						W.jQuery("#dataTable").reloadGrid();
						closeDialog();
						}});
					
				},'json');
				}
				else{
					jQuery.post(url,{plIds:plIds.join(",")},function(data){
						showAlert(data["message"] , {} ,  {'clkFun' : function(){
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
									<button type="button" type="button" onclick="cxXqzc();">
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
								确定要撤销选择的 <span id="plcounts" style="color: blue">${rownum_y}</span> 个学生的<bean:message key="lable.bdzc"/>状态吗？<br/>
								(本次操作共选择:<span style="color: blue">${rownum_t}</span>人, 其中<span style="color: blue">${rownum_w}</span>人未处理,<span style="color: blue">${rownum_x}</span>人未<bean:message key="lable.bdzc"/>.)
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>

