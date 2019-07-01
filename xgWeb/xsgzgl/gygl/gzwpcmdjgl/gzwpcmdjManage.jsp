<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gzwpcmdjgl/js/gzwpcmdj.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		
		<script type="text/javascript">
			var gridSetting = {
			caption : "贵重物品出门登记列表",
			pager : "pager",
			url : "gygl_gzwpcmdj.do?method=gzwpcmdjManage&type=query",
			colList : [
					{ name : 'gzwpdjid', index : 'gzwpdjid', hidden : true, key : true },
					{ label : '学号', name : 'xh', index : 'xh', width : '10%',formatter : xhLink },
					{ label : '姓名', name : 'xm', index : 'xm', width : '8%' },
					{ label : '楼栋名称', name : 'ldmc', index : 'ldmc', width : '15%' },
					{ label : '寝室号', name : 'qsh', index : 'qsh', width : '7%' },
					{ label : '物品名称', name : 'wpmc', index : 'wpmc', width : '10%' },
					{ label : '出门时间', name : 'cmsj', index : 'cmsj', width : '8%' }, 
					{ label : '值班人员', name : 'zbry', index : 'zbry', width : '8%' }, 
					{ label : '备注', name : 'bz', index : 'bz', width : '12%' }],
			sortname : "gzwpdjid", sortorder : "asc" };


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
		<html:form action="/gygl_gzwpcmdj">
			
		
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="addUpdateGzwp('add');return false;" >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="addUpdateGzwp('update');return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="deleteGzwp();return false;" class="btn_sc"  >删除</a>
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
				<span>贵重物品出门登记列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
