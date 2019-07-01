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
			caption:"勤工助学黑名单列表",
			pager:"pager",
			url:"qgzxhmdgl.do?method=getHmdList&type=query",
			colList:[
			   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
			   {label:'单位/家长',name:'mc', index: 'mc',width:'8%'},
			   {label:'类型',name:'dwlx', index: 'dwlx',width:'8%',formatter:function(value,cell){
			       if(value == "01"){
			           return "校内单位";
				   } else if(value == "02"){
			           return "校外单位";
				   } else{
				       return "家教家长";
				   }
			   }},
			   {label:'拉黑时间',name:'lhrq', index: 'lhrq',width:'15%'},
               {label:'操作人',name:'xm', index: 'xm',width:'15%'},
			],
			sortname: "lhrq",
			sortorder: "desc"
		}
		gridSetting["params"]=getSuperSearch();
		jQuery("#dataTable").initGrid(gridSetting);
});

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
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
						<li>
							<a href="javascript:void(0);" class="btn_zj"
							   onclick="addHmd();return false;" >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="delHmd();return false;" class="btn_sc"
							>删除</a>
						</li>
						</logic:equal>
						<%--<li>
							<a href="javascript:void(0);" onclick="xgHmd();return false;" class="btn_xg"
							>修改</a>
						</li>--%>
						<%--<li>
							<a href="javascript:void(0);" onclick="dr();return false;" class="btn_dr"
							>导入</a>
						</li>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>--%>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>勤工助学黑名单列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
