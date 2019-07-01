<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/jskp/lxjg/js/lxjg.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "jskp_lxjg.do?method=searchForJgCx&type=query",
				colList : [ {
					label : 'key',
					name : 'xmid',
					index : 'xmid',
					key : true,
					hidden : true
				}, {
					label : '项目名称',
					name : 'xmmc',
					index : 'xmmc',
					width : '10%'
					,formatter : xmmcLink
				}, {
					label : '项目大类',
					name : 'xmdlmc',
					index : 'xmdlmc',
					width : '10%'
				}, {
					label : '项目类别',
					name : 'xmlbmc',
					index : 'xmlbmc',
					width : '10%'
				}, {
					label : '指导部门',
					name : 'bmmc',
					index : 'bmmc',
					width : '10%'
				}, {
					label : '负责人',
					name : 'fzrxm',
					index : 'fzrxm',
					width : '10%'
				},{
					label : '人员设置',
					name : 'rysz',
					index : 'rysz',
					width : '10%'
					,formatter :ryszLink
				},{
					label : '基本设置',
					name : 'jbsz',
					index : 'jbsz',
					width : '10%'
					,formatter :jbszLink
				},{
					name : 'sjly',
					index : 'sjly',
					hidden : true
				},{
					name : 'xmdl',
					index : 'xmdl',
					hidden : true
				}
				]
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);

		})
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/jskp_lxsq">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >删除</a>
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
				<span>项目结果列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
