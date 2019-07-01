<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>	
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>	
		<script type="text/javascript">

		jQuery(function(){															
			//取得数据表：dmk_xx; 字段：xxmc														
			var autoSetting = {							
				dataTable:"xg_xtwh_bjdlb",						
				dataField:"lbmc",
				dataFieldKeyId:"lbdm",
				dataFieldKey:"lbdm",
<%--				mustMatch:true--%>
			};

			// 模糊搜索下拉											
			jQuery("#lbmc").setAutocomplete(autoSetting);
		});
		
		function saveBjdl(){
			
			var bjdmInput = jQuery(":input[name=bjdm]");
			
			if (bjdmInput.size() == 0){
				showAlert("请选择您要设置大类的班级！");
				return false;
			}
			
			var bjdl = jQuery("#lbmc").val();
			
			if (bjdl == ""){
				showAlert("请填写班级大类！");
				return false;
			}
			
			var url = "xtwh_bjdl.do?method=saveBjdl";
			ajaxSubFormWithFun("bjdlForm",url,function(data){
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
	
		<html:form action="/xtwh_bjdl" styleId="bjdlForm">
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
										<logic:present name="bjxxList">
											<logic:iterate id="bj" name="bjxxList">
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
						<font color="red">*</font>班级大类
					</th>
						<td>
							<input type="text" id="lbmc" name="lbmc" maxlength="25" />
							<input type="hidden" id="lbdm" name="lbdm" />
						</td>
					</tr>
				</tbody>
			<tfoot>
				<td colspan="2">
					<div class="btn">
						<button type="button" onclick="saveBjdl();">
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
	</html:form>
</html>
