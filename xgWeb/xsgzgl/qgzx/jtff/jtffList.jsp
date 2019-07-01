<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/jtff/js/jtff.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "津贴发放",
				pager : "pager",
				url : "qgzx_jtff.do?method=getJtffList",
				colList : [
					{label:'id',name:'id',index :'id',key:true,hidden:true},
					{label:'发放年月',name:'sj',index:'sj',width:'10%'},
					{label:'学号',name:'xh',index:'xh',width:'13%'},
					{label:'姓名',name:'xm',index:'xm',width:'10%'},
					{label:'年级',name:'nj',index:'nj',width:'7%'},
					{label:'学院',name:'xymc',index:'xymc',width:'15%'},
					{label:'专业',name:'zymc',index:'zymc',width:'15%'},
					{label:'班级',name:'bjmc',index:'bjmc',width:'15%'},
					{label:'用工部门',name:'yrbm',index:'yrbm',width:'15%'},
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
						<li><a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >删除</a></li>
						<li><a href="javascript:void(0);" onclick="importConfig();return false" class="btn_dr" >导入</a></li>
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
				<span>津贴发放</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
