<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function() {
				var url = "xpj_zcxm.do?method=getYf";
				jQuery.post(url, {
				}, function(data) {
					dataObj = data;
					initYf();
					setInit();//设置月份选中
					}, 'json');
		});
		function initYf(){
			var _tbody = jQuery("#tbody");
			for ( var i = 0; i < dataObj.length; i++) {
				var o = dataObj[i];
				var dm = o.szyf;
				var mc = o.szyfmc;
				var sHtml = "";
				sHtml += "<td>";
				sHtml += "<label>";
				sHtml += "<input type='checkbox' value='" + dm + "' name='zcyf'/>";
				sHtml += mc;
				sHtml += "</label>";
				sHtml += "</td>";
				var _tr = _tbody.find("tr:last");
				if (_tr.length == 0) {
					_tbody.append("<tr></tr>");
				} else if (_tr.find("td").length >= 3) {
					_tbody.append("<tr></tr>");
				}
				_tbody.find("tr:last").append(sHtml);
			}
			}
		function saveForm(){	  
		     var url = "xpj_zcxm.do?method=showYf&type=save";
		     if (jQuery("#tbody  input[name=zcyf]:checkbox:checked").length == 0) {
		    	 showAlert("请至少选择一个综测月份！");
		    	 return false;
			     }
		      ajaxSubFormWithFun("form1",url,function(data){
		    	 if(data["message"]=="保存成功！"){
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
		function setInit() {
			var yf = "${yf}";
			var yfArr = yf.substring(1,yf.length-1).replace(/[ ]/g,"").split(",");
			jQuery.each(yfArr, function(index, value) {
				jQuery("input[name='zcyf'][value='" + value + "']").attr("checked",
						"checked");
			});
			// 全选按钮
			jQuery("input:checkbox[name=grid_selectAll]").change(
					function() {
						jQuery("#tbody input:checkbox").attr("checked",
								jQuery(this).prop("checked"));
					});
			if (jQuery("#tbody  input:checkbox:not(:checked)").length == 0) {
				jQuery("input:checkbox[name=grid_selectAll]")
						.attr("checked", "checked");
			}
		}
		</script>
		<style>
			#tbody tr{ height:40px; }
		</style>
	</head>
	<body>
		<html:form action="/xpj_zcxm" method="post" styleId="form1">
		<input type="hidden" id="yf" name="yf" value="${yf}"/>
				<table width="100%" border="0" class="dateline">
					<tbody id="tbody">
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">							
							<div class="bz" align="left" id="qxDiv" >
								<label><input type="checkbox"  name="grid_selectAll">全选</label>
							</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										保 存
									</button>
									<button type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>

