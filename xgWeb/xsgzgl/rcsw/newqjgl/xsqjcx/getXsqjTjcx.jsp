<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/newqjgl/xsqjcx/js/xsjqcx.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "学生请假结果列表",
				pager : "pager",
				url : "xsqj_jscx.do?method=getQjcxList&type=query&flag=tjcx",
				colList : [ {
					label : 'key',
					name : 'id',
					index : 'id',
					key : true,
					hidden : true
				}, {
					label : '学号',
					name : 'xh',
					index : 'xh',
					width : '10%',
					formatter : xhLink
				},{
					label : 'sjly',
					name : 'sjly',
					index : 'sjly',
					hidden : true
				},{
					label : 'gjxxid',
					name : 'qsl',
					index : 'qsl',
					hidden : true
				},{
					label : '姓名',
					name : 'xm',
					index : 'xm',
					width : '6%'
				},{
					label : '班级',
					name : 'bjmc',
					index : 'bjmc',
					width : '6%'
				},{
					label : '请假类型',
					name : 'qjlxmc',
					index : 'qjlx',
					width : '8%'
				},{
					label : '审批历史ID',
					name : 'actinstid',
					index : 'actinstid',
					hidden : true
				},{
					label : '请假开始时间',
					name : 'qjkssj',
					index : 'qjkssj',
					width : '15%'
				},{
					label : '请假结束时间',
					name : 'qjjssj',
					index : 'qjjssj',
					width : '17%'
				},{
					label : '请假天数',
					name : 'qjts',
					index : 'qjts',
					width : '8%'
				},{
					label : '请假节次',
					name : 'qjjc',
					index : 'qjjc',
					width : '8%'
				},{
					label : '是否补假',
					name : 'sfbj',
					index : 'sfbj',
					width : '8%'
				}],
				sortname : "qjkssj",
				sortorder : "desc",
				multiselect: false
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
		<html:form action="/xsqj_jscx">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_dc" onclick="exportConfig_tjcx();return false;">导出</a></li>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>学生请假结果列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
