<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/dyxj/zgk/js/zgk.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "列表",
				pager : "pager",
				url : "dyxj_dyzgk.do?method=getDyzgkCx&type=query",
				colList : [ {
					label : 'key',
					name : 'xh',
					index : 'xh',
					key : true,
					hidden : true
				}, {
					label : '学号',
					name : 'xh',
					index : 'xh',
					width : '10%'
					//,formatter : xhLink
				}, {
					label : '姓名',
					name : 'xm',
					index : 'xm',
					width : '6%'
				}, {
					label : '性别',
					name : 'xb',
					index : 'xb',
					width : '5%'
				}, {
					label : '学院',
					name : 'xymc',
					index : 'xydm',
					width : '10%'
				}, {
					label : '专业',
					name : 'zymc',
					index : 'zydm',
					width : '15%'
				},{
					label : '班级',
					name : 'bjmc',
					index : 'bjdm',
					width : '15%'
				}],
				sortname : "xh",
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
		</div>
		<html:form action="/dyxj_dyzgk" styleId="zgkForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="save();return false;" class="btn_zj">添加</a>
						</li>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>学生添加列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
