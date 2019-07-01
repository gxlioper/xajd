<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript" src="xsgzgl/xlzx/xlsc/js/xlscjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>  <!-- 高级查询时间需要 -->
		<script type="text/javascript">
			var gridSetting = {
				caption:"心理筛查结果列表",
				pager:"pager",
				url:"xlzx_xlscjg.do?method=xlscjgManage&type=query",
				colList:[
				   {label:'id',name:'id', index: 'id',hidden:true,key:true},
				   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
				   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
				   {label:'性别',name:'xb', index: 'xb',width:'5%'},
				   {label:'年级',name:'nj', index: 'nj',width:'5%'},
				   {label:'学院',name:'xymc', index: 'xydm',width:'12%'},
				   {label:'专业',name:'zymc', index: 'zydm',width:'12%'},
				   {label:'班级',name:'bjmc', index: 'bjdm',width:'12%'},
				   {label:'筛查日期',name:'scrq', index: 'scrq',width:'8%'}
				],
				sortname: "xh",
			 	sortorder: "asc"
			}
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title}</a>
			</p>
		</div>
		<html:form action="/demo">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">	
						<li><a href="javascript:void(0);" onclick="addXlscjg()" class="btn_zj">增加</a></li>
						<li><a href="javascript:void(0);" onclick="updateXlscjg();" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" onclick="deleteXlscjg()" class="btn_sc">删除</a></li>						
						<li><a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr" >导入</a></li>
					</logic:equal>
						<li><a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc" >导出</a></li>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 心理筛查结果列表 </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>