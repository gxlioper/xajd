<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
					caption:"班级月考核得分列表",
					pager:"pager",
					url:"gygl_ntzd.do?method=bjydfManage&type=query",
					colList:[
					   {label:'年月',name:'ny', index: 'ny',width:'10%',key:true},
					   {label:'班级',name:'bjmc', index: 'bjmc',width:'20%'},
					   {label:'专业',name:'zymc', index: 'zymc',width:'20%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'20%'},
					   {label:'月考核得分',name:'ykhdf', index: 'to_number(ykhdf)',width:'10%'},
					   {label:'<bean:message key="lable.xb" />排名',name:'xypm', index: 'to_number(xypm)',width:'10%'},
					   {label:'学校排名',name:'xxpm', index: 'to_number(xxpm)',width:'10%'}
					],
					sortname: "ny desc , to_number(xxpm), to_number(xypm)",
				 	sortorder: "asc"
			}
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function exportConfig() {
				customExport("gygl_ntzd_bjydf.do", exportData,650,500);
			}
			// 导出方法
			function exportData() {
				setSearchTj();//设置高级查询条件
				var url = "gygl_ntzd.do?method=exportBjydfData&dcclbh='gygl_ntzd_bjydf.do'";//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				jQuery("#btn_dc").click(exportConfig);
			});
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/gygl_ntzd.do?method=bjydfManage">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<logic:equal value="yes" name="writeAble">
							<li><a href="javascript:void(0);" id="btn_dc" class="btn_dc">导出</a></li>
							</logic:equal>
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>班级月考核得分列表</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
