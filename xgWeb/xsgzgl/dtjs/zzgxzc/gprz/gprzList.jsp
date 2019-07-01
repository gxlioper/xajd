<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"改派日志列表",
				pager:"pager",
				url:"dtjs_gprz.do?method=gprzList&type=query",
				colList:[
				   {label:'修改id',name:'id', index: 'id',key:true,hidden:true},
				   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xgsjLink},
				   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
				   {label:'修改人',name:'xgr', index: 'xgr',width:'10%'},
				   {label:'修改时间',name:'xgsj', index: 'xgsj',width:'10%'},
				   {label:'修改前信息',name:'xgqjl', index: 'xgqjl',width:'30%'},
				   {label:'修改后信息',name:'xghjl', index: 'xghjl',width:'30%'}
				],
				sortname: "xgsj",
			 	sortorder: "desc"
			}

			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function xgsjLink(cellValue,rowObject){
				var id = rowObject["id"];
				var xh = rowObject["xh"];
				return "<a href='javascript:void(0);' onclick=\"gprzShow('"+id+"','"+xh+"')\" class='name'>"+cellValue+"</a>";
			}
			
			//搜索
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			/**
			 * 详情
			 */
			function gprzShow(id,xh){
				var url = "dtjs_gprz.do?method=gprzShow&id="+id+"&xh="+xh;
				var title = "改派日志信息";
				showDialog(title, 800, 500, url);
			}
			
			
			/**
			 * 导出
			 */
			var DCGLBH = "dtjs_gprz.do";//dcglbh,导出功能编号

			//自定义导出 功能
			function exportConfig() {
				//DCCLBH导出功能编号,执行导出函数 
				customExport(DCGLBH, xshdglExportData);
			}

			//导出方法
			function xshdglExportData() {
				setSearchTj();//设置高级查询条件
				var url = "dtjs_gprz.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}

		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xszz_zzkff">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>						
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 改派日志列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
