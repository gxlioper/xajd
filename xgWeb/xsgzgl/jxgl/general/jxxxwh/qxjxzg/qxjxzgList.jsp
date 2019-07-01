<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/jxgl/general/jxxxwh/qxjxzg/js/nojx.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "毕业生未还款查询结果列表",
				pager : "pager",
				url : "qxjxzg.do?method=qxjxzgList&type=query",
				colList : [
							{ label : 'jxid', name : 'jxid', index : 'jxid', hidden : true },
							{ label : '学号', name : 'xh', index : 'xh', width : '15%', formatter:xhLink },
							{ label : '姓名', name : 'xm', index : 'xm', width : '10%' },
							{ label : '性别', name : 'xb', index : 'xb', width : '5%' },
							{ label : '学院', name : 'xymc', index : 'xydm', width : '20%' },
							{ label : '专业', name : 'zymc', index : 'zydm', width : '20%' },
							{ label : '班级', name : 'bjmc', index : 'bjdm', width : '25%' },
							{ label : '原参加军训名称', name : 'jxmc', index : 'jxmc', width : '20%' }],
					sortname : "xh",
				    sortorder : "asc" }
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
		<html:form action="/qxjxzg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>										
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;" >导出</a></li>						
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>军训取消资格查询结果列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
