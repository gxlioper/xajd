<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/stglzjsr/js/stgl.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "stgl_zjsr.do?method=getStglList",
				colList : [
					{label:'id',name:'id',index :'id',key:true,hidden:true},
					{label:'社团编号',name:'bh',index:'stbh',width:'10%'},
					{label:'社团名称',name:'stmc',index:'stmc',width:'15%',formatter:stmcLink},
					{label:'社团类别',name:'stlbmc',index:'stlbmc',width:'10%'},
					{label:'归属部门',name:'zd1',index:'zd1',width:'15%'},
					{label:'指导老师',name:'zdls',index:'zdls',width:'10%'},
					{label:'社长',name:'szxm',index:'szxm',width:'15%'},
					{label:'社长长号',name:'szch',index:'szch',width:'15%'},
					{label:'有效状态',name:'yxztmc',index:'yxztmc',width:'10%'}
				],
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
		<html:form action="/xlzx_pxwh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" onclick="add()" class="btn_zj">增加</a></li>
						<li><a href="javascript:void(0);" onclick="edit();" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" onclick="del();" class="btn_sc" >删除</a></li>
						<li><a href="javascript:void(0);" onclick="importConfig();" class="btn_dr" >导入</a></li>
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfig();">导出</a></li>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>社团管理</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
