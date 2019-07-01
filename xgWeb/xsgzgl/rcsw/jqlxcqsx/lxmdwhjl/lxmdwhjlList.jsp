<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"留校名单维护记录列表",
				pager:"pager",
				url:"jqlx_lxmdwhjl.do?method=getLxmdwhjlData",
				colList:[
				   {label:'记录id',name:'jlid', index: 'jlid',key:true,hidden:true},
				   {label:'留校项目',name:'xmmc', index: 'xmmc',width:'28%'},
				   {label:'学号',name:'xh', index: 'xh',width:'13%'},
				   {label:'姓名',name:'xm', index: 'xm',width:'13%'},
				   {label:'操作人',name:'czr', index: 'czr',width:'13%'},
				   {label:'操作时间',name:'czsj', index: 'czsj',width:'20%'},
				   {label:'操作类型',name:'czlxmc', index: 'czlx',width:'13%'}
				],
				sortname: "czsj",
			 	sortorder: "desc"
			}

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			//搜索
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			var DCGLBH = "jqlx_lxmdwhjl.do";//dcglbh,导出功能编号

			//自定义导出 功能
			function exportConfig() {
				//DCCLBH导出功能编号,执行导出函数 
				customExport(DCGLBH, xshdglExportData);
			}

			//导出方法
			function xshdglExportData() {
				setSearchTj();//设置高级查询条件
				var url = "jqlx_lxmdwhjl.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			
			//查看
			function lxmdwhjlShow(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("请选择一条您要查看的记录！");
				} else {
					var jlid=rows[0]["jlid"];
					var url = "jqlx_lxmdwhjl.do?method=lxmdwhjlShow&jlid="+jlid;
					var title = "留校名单维护记录信息";
					showDialog(title, 800, 400, url);
				}
			}

		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/jqlx_lxmdwhjl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" class="btn_ck" onclick="lxmdwhjlShow();">查看</a></li>
						<li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();">导出</a></li>						
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
				<span> 留校名单维护记录列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
