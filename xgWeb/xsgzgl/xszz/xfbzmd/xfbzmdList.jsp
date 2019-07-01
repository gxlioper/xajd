<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript" src="xsgzgl/xszz/xfbzmd/js/xfbzmd.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>  <!-- 高级查询时间需要 -->
		<script type="text/javascript">
			var gridSetting = {
				caption:"学费补助查询列表",
				pager:"pager",
				url:"xszz_zzxmjg_xfbz.do?method=xfbzmdList&type=query",
				colList:[
				   {label:'学号',name:'xh', index: 'xh',width:'10%'},
				   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
				   {label:'年级',name:'nj', index: 'nj',width:'5%'},
				   {label:'学制',name:'xz', index: 'xz',width:'5%'},
				   {label:'学院',name:'xymc', index: 'xymc',width:'8%'},
				   {label:'主修专业确认前学院',name:'zxzyqrqxy', index: 'zxzyqrqxy',width:'8%'},
				   {label:'备注',name:'bz', index: 'bz',width:'10%'}
				],
				sortname: "xh",
			 	sortorder: "asc"
			}
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/demo">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
			
				<logic:equal value="zf01" name="userName">
					<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
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
				<span> 学费补助名单查询列表 </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
