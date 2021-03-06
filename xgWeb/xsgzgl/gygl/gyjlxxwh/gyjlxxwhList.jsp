<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gyjlxxwh/js/gyjlxxwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript">
			jQuery(function() {
	var gridSetting = {
		caption : "公寓纪律信息列表",
		pager : "pager",
		url : "gyjl_gyjlwh.do?method=gyjlxxwhList&type=query",
		colList : [ {
			label : 'key',
			name : 'wjid',
			index : 'wjid',
			key : true,
			hidden : true
		},{
			label : '学号',
			name : 'xh',
			index : 'xh',
			width : '12%',
			formatter:xhLink
		},{
			label : '姓名',
			name : 'xm',
			index : 'xm',
			width : '8%'
		},{
			label : '性别',
			name : 'xb',
			index : 'xb',
			width : '8%'
		}, {
			label : '班级',
			name : 'bjmc',
			index : 'bjdm',
			width : '20%'
		}, {
			label : '住宿寝室',
			name : 'zsqs',
			index : 'zsqs',
			width : '18%'
		}, {
			label : '违纪时间',
			name : 'wjsj',
			index : 'wjsj',
			width : '10%'
		}, {
			label : '纪律类别',
			name : 'wjlb',
			index : 'wjlb',
			width : '8%'
		}],
		sortname : "wjsj",
		sortorder : "desc"
	}
	var map = getSuperSearch();
	gridSetting["params"] = map;
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
		<html:form action="/gyjl_gyjlwh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="dr();return false;" class="btn_dr" >导入</a>
						</li>
						</logic:equal>
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
				<span>公寓纪律信息列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
