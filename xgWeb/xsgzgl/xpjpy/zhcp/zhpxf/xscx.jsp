<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zhcp/zhpxf/js/zhpxf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption : "信息列表",
				pager : "pager",
				url : "xpj_zhpxf.do?method=getZhpxf&type=query",
				colList:[							
						    { label:'key',name:'zcfid', index: 'zcfid',hidden:true,key:true},
							{ label : '学年', name : 'xn', index : 'xn', width : '12%'},
							{ label : '学号', name : 'xh', index : 'xh', width : '12%',formatter : xhLink},
							{ label : '姓名', name : 'xm', index : 'xm', width : '12%'},
							{ label : '综合品行分', name : 'zhpxf', index : 'zhpxf', width : '12%'},
							{ label : '学年成绩平均分', name : 'pjf', index : 'pjf', width : '12%'},
							{ label : '综合素质分', name : 'zhszf', index : 'zhszf', width : '12%'},
							{ label : '综合素质分班级排名', name : 'zhszfpm', index : 'zhszfpm', width : '12%'}
							],
							sortname: "zhszf",
					 		sortorder: "desc"
					 	}	
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xpj_zhpxf">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>综合品行分&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
