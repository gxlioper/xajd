<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xshdgl/js/xshdgl.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "活动管理列表",
				pager : "pager",
				url : "xshdgl_xshdgl.do?method=getXshdglcx&type=query",
				colList : [ {
					label : 'key',
					name : 'sqid',
					index : 'sqid',
					key : true,
					hidden : true
				}, {
					label : '活动名称',
					name : 'hdmc',
					index : 'hdmc',
					width : '10%',
					formatter : hdmcLink
				}, {
					label : '活动时间',
					name : 'hdsj',
					index : 'hdsj',
					width : '6%'
				}, {
					label : '活动地点',
					name : 'hddd',
					index : 'hddd',
					width : '5%'
				}, {
					label : '活动类型',
					name : 'hdlxmc',
					index : 'hdlxmc',
					width : '5%'
				}, {
					label : '主办单位',
					name : 'zbdwdm',
					index : 'zbdwdm',
					width : '15%'
				}, {
					label : '协办单位',
					name : 'xbdwdm',
					index : 'xbdwdm',
					width : '15%'
				},{
					label : '承办单位',
					name : 'cbdwdm',
					index : 'cbdwdm',
					width : '15%'
				},{
					label : '活动负责人',
					name : 'hdfzr',
					index : 'hdfzr',
					width : '10%'
				}],
				sortname : "hdsj",
				sortorder : "desc"
			}
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
		<html:form action="/xshdgl_xshdgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >删除</a>
						</li>
						<li><a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">导入</a></li>
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
				<span>活动管理列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
