<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xstzxm/sh/js/xmsh.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"素质拓展项目审核列表",
				pager:"pager",
				url:"xmsqgl_xmsh.do?method=getXsXmShList&type=query",
				colList:[
					{
					label : 'key',
					name : 'sqid',
					index : 'sqid',
					key : true,
					hidden : true
				}, {
					label : '学号',
					name : 'xh',
					index : 'xh',
					width : '7%',
					formatter : xhLink
				}, {
					label : '姓名',
					name : 'xm',
					index : 'xm',
					width : '6%'
				}, {
					label : '性别',
					name : 'xb',
					index : 'xb',
					hidden : true
				},{
					label : '班级',
					name : 'bjmc',
					index : 'bjdm',
					width : '7%'
				}, {
					label : '学院',
					name : 'xymc',
					index : 'xydm',
					hidden : true
				}, {
					label : '专业',
					name : 'zymc',
					index : 'zydm',
					hidden : true
				},{
					label : '项目名称',
					name : 'xmmc1',
					index : 'xmmc1',
					width : '13%'
				},{
					label : '项目级别',
					name : 'xmjbmc',
					index : 'xmjbdm',
					width : '10%'
				},{
					label : '申报部门',
					name : 'sbbmmc',
					index : 'sbbmmc',
					width : '10%'
				},{
					label : '基础学分',
					name : 'jcxf',
					index : 'jcxf',
					width : '10%'
				},{
					label : '申请时间',
					name : 'sqsj',
					index : 'sqsj',
					width : '10%'
				},{
					label : '审核状态',
					name : 'shztmc',
					index : 'shzt',
					width : '3%'
				},{
					name : 'splc',
					index : 'splc',
					hidden : true
				},{
					name : 'nj',
					index : 'nj',
					hidden : true
				},{
					name : 'xb',
					index : 'xb',
					hidden : true
				},{
					name : 'xq',
					index : 'xq',
					hidden : true
				},{
					name : 'xn',
					index : 'xn',
					hidden : true
				},{
					name : 'sskmmc',
					index : 'sskmdm',
					hidden : true
				},{
					name : 'xmkssj',
					index : 'xmkssj',
					hidden : true
				},{
					name : 'xmkssj',
					index : 'xmkssj',
					hidden : true
				},{
					name : 'shzt',
					index : 'shzt',
					hidden : true
				},{label:'审核Id',
				   name:'shid', 
				   index: 'shid',
				   hidden:true
				},{label:'岗位Id',
				   name:'gwid', 
				   index: 'gwid',
				   hidden:true
				},{label:'xmdm',
				   name:'xmdm', 
				   index: 'xmdm',
				   hidden:true}
				],
				params:{shzt:"dsh"},
				sortname: "sqsj",
			 	sortorder: "desc",
			 	radioselect:false
		}

		var gridSetting2 = {
				caption:"素质拓展项目审核列表",
				pager:"pager",
				url:"xmsqgl_xmsh.do?method=getXsXmShList&type=query",
				colList:[
					{
					label : 'key',
					name : 'sqid',
					index : 'sqid',
					key : true,
					hidden : true
				}, {
					label : '学号',
					name : 'xh',
					index : 'xh',
					width : '7%',
					formatter : xhLink
				}, {
					label : '姓名',
					name : 'xm',
					index : 'xm',
					width : '6%'
				}, {
					label : '性别',
					name : 'xb',
					index : 'xb',
					hidden : true
				},{
					label : '班级',
					name : 'bjmc',
					index : 'bjdm',
					width : '7%'
				}, {
					label : '学院',
					name : 'xymc',
					index : 'xydm',
					hidden : true
				}, {
					label : '专业',
					name : 'zymc',
					index : 'zydm',
					hidden : true
				},{
					label : '项目名称',
					name : 'xmmc1',
					index : 'xmmc1',
					width : '13%'
				},{
					label : '项目级别',
					name : 'xmjbmc',
					index : 'xmjbdm',
					width : '10%'
				},{
					label : '申报部门',
					name : 'sbbmmc',
					index : 'sbbmmc',
					width : '10%'
				},{
					label : '基础学分',
					name : 'jcxf',
					index : 'jcxf',
					width : '10%'
				},{
					label : '申请时间',
					name : 'sqsj',
					index : 'sqsj',
					width : '10%'
				},{
					label : '审核状态',
					name : 'shztmc',
					index : 'shzt',
					width : '3%'
				},{
					name : 'splc',
					index : 'splc',
					hidden : true
				},{
					name : 'nj',
					index : 'nj',
					hidden : true
				},{
					name : 'xb',
					index : 'xb',
					hidden : true
				},{
					name : 'xq',
					index : 'xq',
					hidden : true
				},{
					name : 'xn',
					index : 'xn',
					hidden : true
				},{
					name : 'sskmmc',
					index : 'sskmdm',
					hidden : true
				},{
					name : 'xmkssj',
					index : 'xmkssj',
					hidden : true
				},{
					name : 'xmkssj',
					index : 'xmkssj',
					hidden : true
				},{
					name : 'shzt',
					index : 'shzt',
					hidden : true
				},{
					name : 'sqkg',
					index : 'sqkg',
					hidden : true
				},{label:'审核Id',
				   name:'shid', 
				   index: 'shid',
				   hidden:true
				},{label:'岗位Id',
				   name:'gwid', 
				   index: 'gwid',
				   hidden:true
				},{label:'xfrdsqzt',
					name:'xfrdsqzt', 
					index: 'xfrdsqzt',
					hidden:true				   
				},{label:'xfrdjgzt',
					name:'xfrdjgzt', 
					index: 'xfrdjgzt',
					hidden:true				   
				}
				],
				params:{shzt:"ysh"},
				sortname: "sqsj",
			 	sortorder: "desc",
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
