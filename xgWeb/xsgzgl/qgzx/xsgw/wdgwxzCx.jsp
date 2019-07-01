<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/xsgw/js/wdgwxz.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var map = getSuperSearch();
				var gwdm=jQuery("#gwdm").val();
				map["lx"]=jQuery("#lx").val();
				map["gwdm"]=gwdm;

				if(jQuery("#xxdm").val() == '10351'){//温州大学个性化
					map["xh"] = jQuery("#xh").val();
				}
				
				if(gwdm!=""){
					jQuery(".toolbox").hide();
				}
				gridSetting["params"]=map;
				jQuery("#dataTable").initGrid(gridSetting);
				
			});
		</script>
	</head>

	<body>
	
		<html:form action="/qgzx_jfcjgl_cjff.do?method=gjcxCjff">
			<input type="hidden" id="gotoPath" value="qgzx_wdgwsq.do?method=gwxx&xh=${model.xh}&lx=${lx}" />
			<input type="hidden" id="loadid" value="tbody_gwxx" />
			<input type="hidden" id="lx" value="${lx}" />
			<input type="hidden" id="gwdm" value="${model.gwdm}" />
			<input type="hidden" id="xh" value="${model.xh}" />
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox" style="width:100%;overflow-x:hidden;overflow-y:auto;">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>岗位选择<!---（<font color="red">提示：双击进行选择</font>）--></span>
			</h3>

			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
