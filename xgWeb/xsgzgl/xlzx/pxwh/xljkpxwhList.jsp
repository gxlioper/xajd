<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xlzx/pxwh/js/xljkpxwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "心理健康培训维护",
				pager : "pager",
				url : "xlzx_pxwh.do?method=pxwhList&type=query",
				colList : [
					{label:'key',name:'pxid',index :'pxid',key:true,hidden:true},
					{label:'培训主题',name:'pxzt',index:'pxzt',width:'15%',formatter:pxztLink},
					{label:'培训地点',name:'pxdd',index:'pxdd',width:'15%'},
					{label:'培训时间',name:'pxsj',index:'pxsj',width:'10%'},
					{label:'讲师',name:'js',index:'js',width:'10%'},
					{label:'报名开关',name:'bmkg',index:'bmkg',width:'5%',hidden:true},
					{label:'报名开关',name:'kgzt',index:'bmkg',width:'5%'},
					{label:'报名开放时间',name:'bmkfsj',index:'bmsj',width:'20%',noSort: true},
					{label:'已报名人数',name:'ybmrs',index :'ybmrs',hidden:true},
					{label:'已报名/上限人数',name:'rs',index:'rs',width:'5%',formatter:ybmxsLink}
				],
				sortname: "pxsj",
			 	sortorder: "desc",
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
			<div id="div_help" class="prompt" >
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<span>
						报名开关：当该培训的培训开关为开，并且当前时间处于报名开放时间之内，报名开关为开，允许报名。</br>
						培训开关：指该培训是否被开启。
					</span>
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" onclick="add()" class="btn_zj">增加</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >删除</a></li>
						<!-- <li><a href="javascript:void(0);" onclick="import();return false" class="btn_dr" >导入</a></li> -->
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">已报名学生导出</a></li>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
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
