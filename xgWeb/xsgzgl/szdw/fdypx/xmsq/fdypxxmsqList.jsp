<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/fdypx/js/fdypxsq.js"></script>
		<script type="text/javascript">
		
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				//为button注册事件
				jQuery("#btn_zj").click(add);
				jQuery("#btn_sc").click(qx_sh);
				jQuery("#btn_xg").click(update);
				jQuery("#btn_cz").click(function(){searchReset()});
				jQuery("#btn_cs").click(lcgz);
			});
			
			function submitBusi(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0) {
					showAlertDivLayer("请选择您要提交的记录！");
				} else if (ids.length >1 ) {
					showAlertDivLayer("请选择一条您要提交的记录！");
				} else {
					var rows = jQuery("#dataTable").getSeletRow();
					for(var i=0;i<ids.length;i++){
						if(rows[i]['shzt']!='未提交' && rows[i]['shzt']!='退回' ){
							showAlertDivLayer("请选择未提交或者退回的记录！");
							return false;
						}
					}
					
					
					showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
						jQuery.post("szdw_fdypxxmsq.do?method=submitFdypxsq",
							{values:ids.toString(),
							 fbr : rows[0]['fbr'],
							 splcid : rows[0]['splc'],
							 shzt : rows[0]['shztdm'],
							 xmdm : rows[0]['xmdm'],
							 shzt : rows[0]['shzt']
							},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}

			function cancel(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0) {
					showAlertDivLayer("请选择您要撤销的记录！");
				} else if (ids.length >1 ) {
					showAlertDivLayer("请选择一条您要撤销的记录！");
				} else {
					var rows = jQuery("#dataTable").getSeletRow();
					for(var i=0;i<ids.length;i++){
						if(rows[i]['shzt']!='审核中'){
							showAlertDivLayer("只有审核中的记录才能被撤销！");
							return false;
						}
					}
					showConfirmDivLayer("您确定要撤销选择的记录吗？",{"okFun":function(){
						jQuery.post("szdw_fdypxxmsq.do?method=qxsq",
							{
							 values:ids.toString(),
							 splcid : rows[0]['splc'] 
							},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/szdw_fdypxxmwh.do">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
						
							<li>
							  <a href="javascript:void(0);" id="btn_zj" class="btn_zj">申请</a>
							</li>
							
							<li>
							  <a href="javascript:void(0);" id="btn_xg" class="btn_xg">修改</a>
							</li>
							
							<li>
							  <a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">提交</a>
							</li>
							
							<li>
							  <a href="javascript:void(0);" id="btn_sr"  class="btn_sr" onclick="cancel();return false;">撤销</a>
							</li>
							
							<li>
							  <a href="javascript:void(0);" id="btn_cs" class="btn_cs">流程跟踪</a>
							</li>		
							
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>辅导员培训项目申请</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
