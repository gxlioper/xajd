<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
        <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
        <script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/xsgzgl/dtjs/dxbmgl/dxpxjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript">
			var gridSetting = {
				caption:"党团结果列表",
				pager:"pager",
				url:"dtjs_dxbmgl_dxpxjgCx.do?type=query",
				colList:[
				   {label:'结果id',name:'jgid', index: 'jgid',key:true,hidden:true},
				   {label:'学号',name:'xh', index: 'xh',formatter:dcmcLink},
				   {label:'姓名',name:'xm', index: 'xm'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
				   {label:'专业',name:'zymc', index: 'zymc'},
				   {label:'班级',name:'bjmc', index: 'bjmc'},
				   {label:'培训期次',name:'pxqc', index: 'pxqc'},
				   {label:'培训时间',name:'pxsj', index: 'pxsj'},
				   {label:'评价结果',name:'pjjg', index: 'pjjg'},
				   {label:'sjly',name:'sjly', index: 'sjly',hidden:true}
				],
				sortname: "pxsj",
			 	sortorder: "asc"
			}
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	<html:form action="dxbmgl_dxpxjg.do">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" id="search_go" onclick="reload()" />
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<li>
						<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">增加</a>
					</li>
					<!--<li>
						<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a>
					</li>-->
					<li>
						<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
					</li>
					<li>
						<a href="javascript:void(0);" onclick="pf();return false;" class="btn_xg">评分</a>
					</li>
				</ul>
			</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->		
			</div>
	</html:form>
		<div class="toolbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>党团信息结果列表</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
