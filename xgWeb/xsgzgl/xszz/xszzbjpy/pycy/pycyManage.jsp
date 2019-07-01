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
						caption:"班级评议小组成员列表",
						pager:"pager",
						url:"xszz_xszzbjpy_pycygl.do?method=pycyManage&type=query",
						colList:[      
					         {label:'key',name:'guid', index: 'guid',hidden:true,key:true},
							   {label:'学号',name:'xh', index: 'xh',width:'10%'},
							   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
							   {label:'年级',name:'nj', index: 'nj',width:'7%'},
							   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'14%'},
							   {label:'专业',name:'zymc', index: 'zydm',width:'16%'},
							   {label:'班级',name:'bjmc', index: 'bjdm',width:'20%'},
							   {label:'是否学生代表',name:'sfxsdbmc', index: 'sfxsdbmc',width:'10%'},
							   {label:'是否学生代表',name:'sfxsdb', index: 'sfxsdb',hidden:true},
							   {label:'班级',name:'bjdm', index: 'bjdm',hidden:true}
							],
							sortname: "xh",
						 	sortorder: "asc"
					};
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});
	
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			var DCCLBH = "xszz_xszzbjpy_pycy.do";//dcclbh,导出功能编号

			// 自定义导出 功能
			function exportConfig() {
				//DCCLBH导出功能编号,执行导出函数 
				customExport(DCCLBH, exportData);
			}

			// 导出方法
			function exportData() {
				setSearchTj();//设置高级查询条件
				var url = "xszz_xszzbjpy_pycygl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
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
		<html:form action="/xszz_xszzbjpy_pycygl">
			<input type="hidden" id="xymc" value="<bean:message key="lable.xy" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>										
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;" >导出</a></li>						
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>班级评议小组成员列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
