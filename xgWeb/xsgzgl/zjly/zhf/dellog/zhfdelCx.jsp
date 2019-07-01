<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "zjly_dellog.do?method=getZhfDelList&type=query",
				colList : [
							{ label : 'key', name : 'logid', index : 'logid',key : true, hidden : true },
							{ label : '学号', name : 'xh', index : 'xh', width : '11%' },
							{ label : '姓名', name : 'xm', index : 'xm', width : '5%' },
							{ label : '所属项目', name : 'xmmkmc', index : 'xmmkmc', width : '8%' },
							{ label : '计分项目', name : 'jfxmmc', index : 'jfxmmc', width : '15%' },
							{ label : '事项名称', name : 'sxsm', index : 'sxsm', width : '10%' },
							{ label : '参与/获得时间', name : 'cysj', index : 'cysj', width : '5%' },
							{ label : '录入人姓名', name : 'lrrxm', index : 'lrrxm', hidden : true},
							{ label : '状态', name : 'shzt', index : 'shzt', width : '6%' },
							{ label : '录入时间', name : 'lrsj', index : 'lrsj', hidden : true},
							{ label : '删除人', name : 'scrxm', index : 'scrxm',width : '5%'},	
							{ label : '删除时间', name : 'scsj', index : 'scsj',width : '12%'}						
							],
			 	radioselect:false
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		function searchRs() {
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}
		
		function exportData(){
			customExport('xg_zjly_dellog.do', exportData);
		}
		function exportData(){
			setSearchTj();// 设置高级查询条件
			var url = "zjly_dellog.do?method=exportData&dcclbh=" + 'xg_zjly_dellog.do';// dcclbh,导出功能编号,数据表字段
			url = addSuperSearchParams(url);// 设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
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
		<html:form action="/zjly_dellog">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
							<li><a href="javascript:void(0);" onclick="exportData();return false;" class="btn_dc">导出</a></li>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>综合分删除明细表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
