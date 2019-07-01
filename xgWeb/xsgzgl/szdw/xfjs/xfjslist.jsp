<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/xfjs/js/xfjs.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "学风建设列表",
				pager : "pager",
				url : "szdw_xfjsgl.do?method=xfjsList&type=query",
				colList : [
							{ label : 'id', name : 'id', index : 'id',key : true, hidden : true },
							{ label : '学院', name : 'xymc', index : 'xymc', width : '10%' },
							{ label : '辅导员', name : 'fdy', index : 'fdy', width : '5%' },
							{ label : '学生类型', name : 'pyccmc', index : 'pyccmc', width : '5%' },
							{ label : '校区', name : 'yxmc', index : 'yxmc', width : '10%' },
							{ label : '班级', name : 'bjmc', index : 'bjmc', width : '10%',formatter:bjLink },
							{ label : '应到人数', name : 'ydxsrs', index : 'ydxsrs', width : '5%'},
							{ label : '出勤人数', name : 'sjcqrs', index : 'sjcqrs', width : '5%' },
							{ label : '出勤率', name : 'cql', index : 'cql', width : '8%' },
							{ label : '检查时间', name : 'jcsj', index : 'jcsj', width : '10%' },
							{ label : '检查节次', name : 'jcjc', index : 'jcjc', width : '10%' }
						  ]
			};
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
		<html:form action="/szdw_xfjsgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;">增加</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="importXf();return false;" class="btn_dr" >导入</a>
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
				<span>班风建设列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
