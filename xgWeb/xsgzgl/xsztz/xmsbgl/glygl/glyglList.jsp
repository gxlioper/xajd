<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xmsbgl/glygl/glygl.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "项目管理员列表",
				pager : "pager",
				url : "xmsbGlygl.do?method=glyglList&type=query",
				colList : [ {
					label : 'key',
					name : 'yhm',
					index : 'yhm',
					key : true,
					hidden : true
				},{
					label : '用户名',
					name : 'yhm',
					index : 'yhm',
					width : '10%'
				},{
					label : '姓名',
					name : 'xm',
					index : 'xm',
					width : '10%'
				}, {
					label : '所属组',
					name : 'zmc',
					index : 'zmc',
					width : '15%'
				}, {
					label : '所在部门',
					name : 'bmmc',
					index : 'bmmc',
					width : '15%'
				}],
				sortname : "bmmc",
				sortorder : "desc"
			}
			
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
		<html:form action="/xmsbGlygl">
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" class="btn_cx" id="search_go" onclick="searchRs();closeMore();return false;"/>
			<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>						
				</ul>
			</div>
			</logic:equal>
			<div style="display: none;">
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>项目管理员列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
