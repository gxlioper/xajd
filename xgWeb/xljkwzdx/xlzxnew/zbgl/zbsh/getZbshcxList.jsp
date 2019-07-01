<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xljkwzdx/xlzxnew/zbgl/zbsh/js/zbsh.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				pager : "pager",
				url : "xlzxnew_zbsh.do?method=searchForzbsh",
				colList : [
						{ label : 'sbsqid', name : 'sbsqid', index : 'sbsqid',key : true, hidden : true },
						{ label : '学年', name : 'xn', index : 'xn', width : '15%'},
						{ label : '学期', name : 'xqmc', index : 'xqmc', width : '10%' },
						{ label : '周次', name : 'zbzc', index : 'zbzc', width : '10%' , formatter : link},
						{ label : '开始日期', name : 'zbksrq', index : 'zbksrq', width : '10%' },
						{ label : '结束日期', name : 'zbjsrq', index : 'zbjsrq', width : '10%' },
						{ label : '上报时间', name : 'sbsj', index : 'sbsj', width : '10%' },
						{ label : '审核状态', name : 'shztmc', index : 'shztmc', width : '8%' },
						{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true },
						{ label : 'splcid', name : 'splcid', index : 'splcid', hidden : true },
						{ label : 'zbid', name : 'zbid', index : 'zbid', hidden : true },
						{ label : 'xh', name : 'xh', index : 'xh', hidden : true },
						{label:'审核Id',name:'shid', index: 'shid',hidden:true},
						{label:'岗位Id',name:'gwid', index: 'gwid',hidden:true}
						]
				 };

		var gridSetting2 = {
				pager : "pager",
				url : "xlzxnew_zbsh.do?method=searchForzbsh",
				colList : [
						{ label : 'sbsqid', name : 'sbsqid', index : 'sbsqid',key : true, hidden : true },
						{ label : '学年', name : 'xn', index : 'xn', width : '15%'},
						{ label : '学期', name : 'xqmc', index : 'xqmc', width : '10%' },
						{ label : '周次', name : 'zbzc', index : 'zbzc', width : '10%' , formatter : link},
						{ label : '开始日期', name : 'zbksrq', index : 'zbksrq', width : '10%' },
						{ label : '结束日期', name : 'zbjsrq', index : 'zbjsrq', width : '10%' },
						{ label : '上报时间', name : 'sbsj', index : 'sbsj', width : '10%' },
						{ label : '审核状态', name : 'shztmc', index : 'shztmc', width : '8%' },
						{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true },
						{ label : 'splcid', name : 'splcid', index : 'splcid', hidden : true },
						{ label : 'zbid', name : 'zbid', index : 'zbid', hidden : true },
						{ label : 'xh', name : 'xh', index : 'xh', hidden : true },
						{label:'审核Id',name:'shid', index: 'shid',hidden:true},
						{label:'岗位Id',name:'gwid', index: 'gwid',hidden:true}
						],
						radioselect:true
				 };
			
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
		<html:form action="/xlzxnew_zbsh">
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
				
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>待处理</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>已处理</span></a></li>
			      </ul>
			    </div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>周报上报审核列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
