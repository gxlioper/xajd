<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		        <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		        <script type="text/javascript" src="js/calendar/calendar.js"></script>
				<script type="text/javascript" src="js/xsgzgl/dtjs/dxbmgl/dxpxgl.js"></script>
				<script type="text/javascript">
				var gridSetting = {
						caption:"培训信息结果列表",
						pager:"pager",
						url:"dtjs_dxbmgl_dxpxglCx.do?type=query",
						colList:[
						   {label:'id',name:'id', index: 'id',key:true,hidden:true},
						   {label:'sjyl',name:'sjyl', index: 'sjyl',hidden:true},
						   {label:'培训期次',name:'pxqc', index: 'pxqc'},
						   {label:'培训时间',name:'pxsj', index: 'xm'},
						   {label:'报名开始时间',name:'bmkssj', index: 'bmkssj'},
						   {label:'报名结束时间',name:'bmjssj', index: 'bmjssj'},
						   {label:'发布人',name:'fbrxm', index: 'fbrxm'},
						   {label:'联系电话',name:'lxdh', index: 'lxdh'},
						   {label:'成绩比例设置',name:'', index: '',formatter:setCjbl}
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
	<html:form action="dxbmgl_dxpxgl.do">
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
					<li>
						<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a>
					</li>
					<li>
						<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
					</li>
				</ul>
			</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->		</div>
		</html:form>
		<div class="toolbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>培训信息结果列表</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
