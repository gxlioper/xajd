<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/hmdgl/js/hmdgl.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
		var gridSetting = {
			caption:"用人单位列表",
			pager:"pager",
			url:"qgzxhmdgl.do?method=selectDw&type=query&dwlx="+jQuery("#dwlx").val(),
			colList:[
			   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
			   {label:'单位/家长名称',name:'yrdwmc', index: 'yrdwmc',width:'8%'},
               {label:'类型',name:'dwlb', index: 'dwlb',width:'8%',formatter:function(value,cell){
                   if(value == "01"){
                       return "校内单位";
				   } else if(value == "02"){
                       return "校外单位";
				   } else if(value == "03"){
                       return "家长用户";
				   } else {
                       return value;
				   }
			   }},
               {label:'单位负责人',name:'xm', index: 'xm',width:'10%'},
               {label:'操作',name:'', index: '',width:'15%',formatter:function(value,cell){
                   return "<label class='btn_01' onclick=\"select('"+cell["id"]+"','"+cell["yrdwmc"]+"');\">选择</label>";
			   }},
			],
			sortname: "yrdwmc",
			sortorder: "desc"
		}
		gridSetting["params"]=getSuperSearch();
		jQuery("#dataTable").initGrid(gridSetting);
});
		function select(id,mc){
            var api = frameElement.api;
            var parent = api.get('parentDialog');
            parent.jQuery("#dwid").val(id);
            parent.jQuery("#mc").html(mc);
            iFClose();
		}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/qgzxhmdgl">
		<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" value="${dwlx}" id="dwlx">
			<div class="toolbox">
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>用人单位列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
