<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/jskp/lxsh/js/lxsh.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"",
				pager:"pager",
				url:"jskp_lxsh.do?method=searchForLxshCx",
				colList:[
					 {
					label : 'key',
					name : 'sqid',
					index : 'sqid',
					key : true,
					hidden : true
				}, {
					label : '项目名称',
					name : 'xmmc',
					index : 'xmmc',
					width : '10%'
					,formatter : xmmcLink
				}, {
					label : '项目类别',
					name : 'xmlbmc',
					index : 'xmlbmc',
					width : '10%'
				}, {
					label : '指导部门',
					name : 'bmmc',
					index : 'bmmc',
					width : '10%'
				}, {
					label : '负责人',
					name : 'fzrxm',
					index : 'fzrxm',
					width : '10%'
				},{
					label : 'fzr',
					name : 'fzr',
					index : 'fzr',
					hidden : true

				},{
					label : '立项时间',
					name : 'lxsj',
					index : 'lxsj',
					hidden : true
				},{
					label : '审核状态',
					name : 'shztmc',
					index : 'shztmc',
					width : '10%'
				},{
					label : 'shzt',
					name : 'shzt',
					index : 'shzt',
					hidden : true
				},{
					name : 'splcid',
					index : 'splcid',
					hidden : true
				},{label:'审核Id',name:'shid', index: 'shid',hidden:true},
				{label:'岗位Id',name:'gwid', index: 'gwid',hidden:true}
				],
				params:{shzt:"dsh"},
			 	radioselect:false
		}

		var gridSetting2 = {
				caption:"",
				pager:"pager",
				url:"jskp_lxsh.do?method=searchForLxshCx",
				colList:[
				{
					label : 'key',
					name : 'sqid',
					index : 'sqid',
					key : true,
					hidden : true
				}, {
					label : '项目名称',
					name : 'xmmc',
					index : 'xmmc',
					width : '10%'
					,formatter : xmmcLink
				}, {
					label : '项目类别',
					name : 'xmlbmc',
					index : 'xmlbmc',
					width : '10%'
				}, {
					label : '指导部门',
					name : 'bmmc',
					index : 'bmmc',
					width : '10%'
				}, {
					label : '负责人',
					name : 'fzrxm',
					index : 'fzrxm',
					width : '10%'
				},{
					label : '立项时间',
					name : 'lxsj',
					index : 'lxsj',
					hidden : true
				},{
					label : '审核状态',
					name : 'shztmc',
					index : 'shztmc',
					width : '10%'
				},{
					label : 'shzt',
					name : 'shzt',
					index : 'shzt',
					hidden : true
				},{
					name : 'splcid',
					index : 'splcid',
					hidden : true
				},{label:'审核Id',name:'shid', index: 'shid',hidden:true},
				{label:'岗位Id',name:'gwid', index: 'gwid',hidden:true}
				],
				params:{shzt:"ysh"},
			 	radioselect:true
		}
			
		jQuery(function(){
			var map = getSuperSearch();
			map["shzt"]="dsh";
			gridSetting["params"] = map;
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
		<html:form action="/gygl_xyzsshgl">
			<input type="hidden" name="shkg" id="shkg" value="${shkg}"/>
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
				<span>立项审核列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
