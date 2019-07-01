<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "全校学生信息查询结果列表",
				pager : "pager",
				url : "xsxx_zjdx_qxxscxgl.do?method=getQxxscxList&type=query",
				colList : [
							{ label : '学号', name : 'xh', index : 'xh', width : '9%' },
							{ label : '姓名', name : 'xm', index : 'xm', width : '8%' },
							{ label : '性别', name : 'xb', index : 'xb', width : '2%' },
							{ label : '联系电话', name : 'sjhm', index : 'sjhm', width : '8%' },
							{ label : '年级', name : 'nj', index : 'nj', width : '2%' },
							{ label : '学院', name : 'xymc', index : 'xydm', width : '10%' },
							{ label : '专业', name : 'zymc', index : 'zymc', width : '12%' },
							{ label : '班级', name : 'bjmc', index : 'bjmc', width : '12%' }],
					sortname : "xh",
				    sortorder : "asc" }
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
			// 高级查询
			function searchRs() {
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		
		<html:form action="/xsxx_zjdx_qxxscxgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮  --> 
<!--				<div class="buttonbox">-->
<!--					<ul>										-->
<!--						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;" >导出</a></li>						-->
<!--					</ul>-->
<!--				</div>-->
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>全校学生信息查询结果列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
		
	</body>
</html>