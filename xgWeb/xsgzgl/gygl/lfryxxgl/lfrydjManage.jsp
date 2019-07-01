<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/lfryxxgl/js/lfrydj.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		
		<script type="text/javascript">
			var gridSetting = {
			caption : "来访登记列表",
			pager : "pager",
			url : "gygl_lfrydj.do?method=lfrydjManage&type=query",
			colList : [
					{ name : 'lfrdjid', index : 'lfrdjid', hidden : true, key : true },
					{ label : '来访人姓名', name : 'lfrxm', index : 'lfrxm', width : '8%',formatter : xhLink },
					{ label : '来访事由', name : 'lfsymc', index : 'lfsydm', width : '8%' },
					{ label : '来访时间', name : 'lfsj', index : 'lfsj', width : '8%' },
					{ label : '离去时间', name : 'lqsj', index : 'lqsj', width : '8%' },
					{ label : '被访人学号', name : 'xh', index : 'xh', width : '9%' },
					{ label : '被访人姓名', name : 'xm', index : 'xm', width : '8%' },
					{ label : '楼栋名称', name : 'ldmc', index : 'ldmc', width : '13%' },
					{ label : '寝室号', name : 'qsh', index : 'qsh', width : '7%' },
					{ label : '值班人员', name : 'zbry', index : 'zbry', width : '8%' }
				     ],
			sortname : "lfsj", sortorder : "desc" };


			jQuery(function() {
				
				//gridSetting["params"] = getSuperSearch();
				
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
		<html:form action="/gygl_lfrydj">
			
		
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="addUpdateLfry('add');return false;" >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="addUpdateLfry('update');return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="deleteLfry();return false;" class="btn_sc"  >删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">导入</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">导出</a>
						</li>	
					</ul>
				</div>
				</logic:equal>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>来访登记列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
