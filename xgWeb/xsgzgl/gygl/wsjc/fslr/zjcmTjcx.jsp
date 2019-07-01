<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/wsjc/fslr/js/zjcmTjcx.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "卫生分统计查询结果列表",
				pager : "pager",
				url : "gyglnew_fslr_ajax.do?method=searchForZjcmTjCx&type=query",
				colList : [{
					label : '学院',
					name : 'xymc',
					index : 'xymc',
					width : '20%'
				}, {
					label : '查寝个数',
					name : 'zs',
					index : 'zs',
					width : '10%'
				}, {
					label : '优秀寝室',
					name : 'yxrs',
					index : 'yxrs',
					width : '10%'
				}, {
					label : '百分比',
					name : 'yxbfb',
					index : 'yxbfb',
					width : '10%'
				}, {
					label : '合格寝室',
					name : 'hgrs',
					index : 'hgrs',
					width : '10%'
				},{
					label : '百分比',
					name : 'hgbfb',
					index : 'hgbfb',
					width : '10%'
				},{
					label : '不合格寝室',
					name : 'bhgrs',
					index : 'bhgrs',
					width : '10%'
				},{
					label : '百分比',
					name : 'bhgbfb',
					index : 'bhgbfb',
					width : '10%'
				}
				],
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/gygl_xyzsjggl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>卫生分统计查询结果列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
