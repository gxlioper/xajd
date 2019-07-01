<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/xygl/js/xyglCx.js"></script>
		<script type="text/javascript">
			var gridSetting = {
			caption : "校友信息列表",
			pager : "pager",
			url : "xsxx_xyglxx.do?method=xyglList&type=query",
			colList : [
					{ label : '学号', name : 'xh', index : 'xh', key : true, formatter : xhLink, width : '8%' },
					{ label : '姓名', name : 'xm', index : 'xm', width : '8%' },
					{ label : '性别', name : 'xb', index : 'xb', width : '8%' },
					{ label : '联系方式', name : 'sjhm', index : 'sjhm', width : '8%' },
					{ label : '年级', name : 'nj', index : 'nj', width : '9%' },
					{ label : '学院', name : 'xymc', index : 'xymc', width : '8%' },
					{ label : '专业', name : 'zymc', index : 'zymc', width : '13%' }],
			sortname : "xh", sortorder : "asc" };


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

		<html:form action="/xsxx_xyglxx" method="post">
			<input type="hidden" name="method" id="method" value="${method}"/>
	
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xhstr" id="xhstr" />
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 读写权 -->
						<logic:equal name="writeAble" value="yes">
							
							<li>
								<a href="#" onclick="showXyglAdd();return false;" id="btn_zj"
									class="btn_zj"> 增加 </a>
							</li>
							<li>
								<a href="#" onclick="showXyglEdit();return false;" id="btn_xg"
									class="btn_xg"> 修改 </a>
							</li>
							<li>
								<a href="#" onclick="deleteXygl();return false;" id="btn_sc"
									class="btn_sc"> 删除 </a>
							</li>
							
						</logic:equal>
							
						
						<li>
							<a href="#" class="btn_dc" id="btn_dc"
								onclick="xyglExportConfig();return false;">导出</a>
						</li>
						
					</ul>
				</div>
				<!-- 按钮 end-->

				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>

		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>校友信息列表</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
