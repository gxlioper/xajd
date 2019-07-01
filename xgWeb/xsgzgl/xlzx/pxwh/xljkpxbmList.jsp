<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xlzx/pxwh/js/xljkpxbm.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting1 = {
				caption : "心理健康培训未报名",
				pager : "pager",
				url : "xlzx_pxwh.do?method=pxbmList&type=query",
				colList : [
					{label:'key',name:'pxid',index :'pxid',key:true,hidden:true},
					{label:'培训主题',name:'pxzt',index:'pxzt',width:'15%',formatter:pxztLink},
					{label:'培训地点',name:'pxdd',index:'pxdd',width:'15%'},
					{label:'培训时间',name:'pxsj',index:'pxsj',width:'10%'},
					{label:'讲师',name:'js',index:'js',width:'10%'},
					{label:'报名开放时间',name:'bmkfsj',index:'bmkfsj',width:'15%'},
					{label:'已报名/</br>上限人数',name:'rs',index:'rs',width:'5%'},
					{label:'报名时间已过',name:'bmyg',index:'bmyg',width:'5%',hidden:true},
					{label:'是否开放报名',name:'sfkfbm',index:'sfkfbm',width:'5%',hidden:true},
					{label:'操作',name:'cz',index:'cz',width:'10%',formatter:bmcz,noSort: true}
				],
				sortname: "pxsj",
		 		sortorder: "desc",
			}
			var gridSetting2 = {
				caption : "心理健康培训已报名",
				pager : "pager",
				url : "xlzx_pxwh.do?method=pxbmList&type=query",
				colList : [
					{label:'key',name:'pxid',index :'pxid',key:true,hidden:true},
					{label:'培训主题',name:'pxzt',index:'pxzt',width:'15%',formatter:pxztLink},
					{label:'培训地点',name:'pxdd',index:'pxdd',width:'15%'},
					{label:'培训时间',name:'pxsj',index:'pxsj',width:'10%'},
					{label:'讲师',name:'js',index:'js',width:'10%'},
					{label:'报名开放时间',name:'bmkfsj',index:'bmkfsj',width:'15%'},
					{label:'报名时间',name:'bmsj',index:'bmsj',width:'10%'},
					{label:'报名时间已过',name:'bmyg',index:'bmyg',width:'5%',hidden:true},
					{label:'操作',name:'cz',index:'cz',width:'10%',formatter:qxbmcz,noSort: true}
				],
				sortname: "pxsj",
		 		sortorder: "desc",
			}
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting1["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting1);
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
			<input type="hidden"  id="sfybm" value=""/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:70%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'0');"><span>未报名</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>已报名</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="formbox">
			<h3 class=datetitle_01>
				<span>心理健康培训维护&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
