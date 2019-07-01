<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>		
		<script type="text/javascript">

		jQuery(function(){															
			//取得数据表：dmk_xx; 字段：xxmc														
			var autoSetting = {							
				dataTable:"xg_zhcp_cpzb",						
				dataField:"cpzmc",
				dataFieldKeyId:"pmzdm"
			}

			// 模糊搜索下拉											
			jQuery("#pmz").setAutocomplete(autoSetting);
		})
			
		
			function aginCpz(){
				var pmz = jQuery("#pmz").val();
				var bjdm = jQuery("input[name='bjdm']").map(function(){
					return jQuery(this).val();
				}).get().join(',');
				
				if(bjdm.length == "0" ){
					showAlert("班级不能为空");
					return false;				
				}if(pmz.length == "0")
				{
					showAlert("排名组不能为空");
					return false;
				}
				var pmzdm = jQuery("#pmzdm").val();
				if(pmzdm == null || pmzdm == ""){
					pmzdm = pmz;
				}
				showConfirm("您确定将选定班级调整到“"+pmz+"”排名组吗？",{"okFun":function(){
					var url = "xpj_cpxz.do?method=saveCpxs&bjdm="+bjdm;
					jQuery.post(url,{
							pmz:pmzdm
						},function(data){
						showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}})
					},"json");
				}});
			}
		
		</script>
	</head>
	
	<body>
		<table class="formlist">
			<thead>
				<tr>
					<th colspan="2">
						<span></span>
					</th>
				</tr>
			</thead>
			<tbody>
					<tr>
						<th width="100px">
							班级
						</th>
						<td>
							<div class="search_advanced">
								<div class="selected-attr">
										<logic:present name="bjInfo">
											<logic:iterate id="bj" name="bjInfo" indexId="i">
												<dd>
													<a href="javascript:void(0);" >
													${bj.bjmc }
													<input type="hidden" name="bjdm" value="${bj.bjdm }"/>
													<span class="close-icon" onclick="jQuery(this).parent().remove();"></span></a>
												</dd>
											</logic:iterate>
										</logic:present>
								</div>
							</div>
						</td>
					</tr>
					<tr>
					<th>
						排名组
					</th>
						<td>
							<input type="text" id="pmz" name="pmz" maxlength="20" />
							<input type="hidden" id="pmzdm" name="pmzdm" />
						</td>
					</tr>
				</tbody>
			<tfoot>
				<td colspan="2">
					<div class="btn">
						<button type="button" onclick="aginCpz();">
							保 存
						</button>
						<button type="button"
							onclick="closeDialog();">
							关 闭
						</button>
					</div>
				</td>
				</tr>
			</tfoot>
		</table>
	</body>
</html>
