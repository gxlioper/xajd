<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var jc = jQuery("#jc").val();
            var rq = jQuery("#rq").val();
            var xydm = jQuery("#xydm").val();
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "xxwkqk_xxwktj.do?method=getxyrqInfo&type=query&jc="+jc+"&rq="+rq+"&xydm="+xydm,
				colList : [
							{ label : '学院名称', name : 'xymc', index : 'xymc', width : '11%'},
                    		{ label : '年级', name : 'nj', index : 'nj', width : '10%' },
							{ label : '班级名称', name : 'bjmc', index : 'bjmc', width : '10%' },
							{ label : '班级人数', name : 'totalnum', index : 'totalnum', width : '11%' },
							{ label : '无课人数', name : 'num', index : 'num', width : '6%' },
							{ label : '占比', name : 'zb', index : 'zb', width : '6%' }],
					sortname : "bjmc",
				    sortorder : "desc",
                multiselect:false

			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
		</script>
	</head>

	<body>

		
		<html:form action="/xxwkqk_xxwktj">
			<html:hidden property="jc" styleId="jc"></html:hidden>
			<html:hidden property="rq" styleId="rq"></html:hidden>
			<html:hidden property="xydm" styleId="xydm"></html:hidden>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<span style="font-size: 20px">${rq}第${jc}节课无课情况</span>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
