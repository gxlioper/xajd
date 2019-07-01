<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/hdkhgl/js/hdkhgl.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
					caption : "项目列表",
					pager : "pager",
					url : "txhd_hdkhgl.do?method=getHdkhList&type=query",
					colList : [ {
						label : '学年',
						name : 'xn',
						index : 'xn',
						width : '12%'
					}, {
						label : '项目名称',
						name : 'xmmc',
						index : 'xmmc',
						width : '23%',
						formatter:show
					}, {
						label : '活动时间',
						name : 'hdsj',
						index : 'hdsj',
						width : '25%'
					}, {
						label : '活动地点',
						name : 'hddd',
						index : 'hddd',
						width : '19%'
					}, {
						label : '活动类别',
						name : 'lbmc',
						index : 'lbmc',
						width : '10%'
					}, {
						label : '活动规格',
						name : 'hdgg',
						index : 'hdgg',
						width : '8%'
					}, {
						label : '活动规格',
						name : 'xmdm',
						index : 'xmdm',
						hidden: true
					}],
					sortname : "xmmc",
					sortorder : "asc"
				};
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/txhd_hdkhgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_sh" onclick="cjpd();return false;"  >活动成绩评定</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="importConfig();;return false;" class="btn_dr" >导入</a>
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
				<span>活动项目明细&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
