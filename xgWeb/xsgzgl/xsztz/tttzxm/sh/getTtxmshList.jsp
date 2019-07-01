<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/tttzxm/sh/js/ttxmsh.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/tttzxm/comm/js/comm.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption : "团体拓展项目审核列表",
				pager : "pager",
				url : "ttxm_sh.do?method=getTtxmshList&type=query",
				colList : [ {
					label : 'key',
					name : 'ttsqid',
					index : 'ttsqid',
					key : true,
					hidden : true
				}, {
					label : 'key',
					name : 'xmdm',
					index : 'xmdm',
					hidden : true
				}, {
					label : '团队名称',
					name : 'tdmc',
					index : 'tdmc',
					width : '10%',
					formatter:ttsqLink
				}, {
					label : '队长学号',
					name : 'xh',
					index : 'xh',
					width : '10%',
					formatter:dzxhLink
				},{
					label : '队长姓名',
					name : 'dzxm',
					index : 'dzxm',
					width : '10%'
				}, {
					label : '成员数',
					name : 'tdnum',
					index : 'tdnum',
					width : '6%'
				}, {
					label : '队长所在院系',
					name : 'xymc',
					index : 'xydm',
					width : '5%'
				}, {
					label : '项目名称',
					name : 'xmmc',
					index : 'xmmc',
					width : '15%'
				},{
					label : '活动级别',
					name : 'xmjbmc',
					index : 'xmjbmc',
					width : '15%'
				},{
					label : '申请时间',
					name : 'sqsj',
					index : 'sqsj',
					width : '10%'
				},
				{
					label : '审核状态',
					name : 'shztmc',
					index : 'shztmc',
					width : '10%'
				},
				{
					label : 'shzt',
					name : 'shzt',
					index : 'shzt',
					hidden : true
				},	
				{
					name : 'splc',
					index : 'splc',
					hidden : true
				},
				{
					name : 'sqkg',
					index : 'sqkg',
					hidden : true
				},{
					name : 'xfrdsqzt',
					index : 'xfrdsqzt',
					hidden : true
				},
				{
					name : 'xfrdjgzt',
					index : 'xfrdjgzt',
					hidden : true
				},{label:'审核Id',
					   name:'shid', 
					   index: 'shid',
					   hidden:true
				},{label:'岗位Id',
					   name:'gwid', 
					   index: 'gwid',
					   hidden:true
				},{label:'sqr',
					   name:'sqr', 
					   index: 'sqr',
					   hidden:true
				}
				],
				sortname : "sqsj",
				sortorder : "desc",
				params:{shzt:"dsh"},
				sortname: "sqsj",
			 	sortorder: "desc",
			 	radioselect:false
			};
		var gridSetting2 = {
				caption : "团体拓展项目审核列表",
				pager : "pager",
				url : "ttxm_sh.do?method=getTtxmshList&type=query",
				colList : [ {
					label : 'key',
					name : 'ttsqid',
					index : 'ttsqid',
					key : true,
					hidden : true
				}, {
					label : 'key',
					name : 'xmdm',
					index : 'xmdm',
					hidden : true
				}, {
					label : '团队名称',
					name : 'tdmc',
					index : 'tdmc',
					width : '10%',
					formatter:ttsqLink
				}, {
					label : '队长学号',
					name : 'xh',
					index : 'xh',
					width : '10%',
					formatter:dzxhLink
				},{
					label : '队长姓名',
					name : 'dzxm',
					index : 'dzxm',
					width : '10%'
				}, {
					label : '成员数',
					name : 'tdnum',
					index : 'tdnum',
					width : '6%'
				}, {
					label : '队长所在院系',
					name : 'xymc',
					index : 'xydm',
					width : '5%'
				}, {
					label : '项目名称',
					name : 'xmmc',
					index : 'xmmc',
					width : '15%'
				},{
					label : '活动级别',
					name : 'xmjbmc',
					index : 'xmjbmc',
					width : '15%'
				},{
					label : '申请时间',
					name : 'sqsj',
					index : 'sqsj',
					width : '10%'
				},
				{
					label : '审核状态',
					name : 'shztmc',
					index : 'shztmc',
					width : '10%'
				},
				{
					label : 'shzt',
					name : 'shzt',
					index : 'shzt',
					hidden : true
				},	
				{
					name : 'splc',
					index : 'splc',
					hidden : true
				},
				{
					name : 'sqkg',
					index : 'sqkg',
					hidden : true
				},{
					name : 'xfrdsqzt',
					index : 'xfrdsqzt',
					hidden : true
				},
				{
					name : 'xfrdjgzt',
					index : 'xfrdjgzt',
					hidden : true
				},{label:'审核Id',
					   name:'shid', 
					   index: 'shid',
					   hidden:true
				},{label:'岗位Id',
					   name:'gwid', 
					   index: 'gwid',
					   hidden:true
				},{label:'sqr',
					   name:'sqr', 
					   index: 'sqr',
					   hidden:true
				}
				],
				sortname : "sqsj",
				sortorder : "desc",
				params:{shzt:"ysh"},
				sortname: "sqsj",
			 	sortorder: "desc",
			 	radioselect:true
			};
		jQuery(function(){
			var map = getSuperSearch();
			map["shzt"]="dsh";
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
		<html:form action="/xmsqgl_xmsh">
			<input type="hidden" id="shzt" value="dsh"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="khsh();return false;" 
							   title="选中您要审核的记录，点击该按钮可以打开审核页面。"
							   class="btn_sh">审核</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelSh();return false;" 
							   title="选中一条记录，点击该按钮您可以撤消失误的审核操作。"
							   class="btn_qxsh">撤消</a>
						</li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="splcInfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>待处理</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>已处理</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>素质拓展项目审核列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
