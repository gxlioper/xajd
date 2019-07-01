<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/dtjs/tsdzb/js/tsdzbsh.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"特色党支部审核列表",
				pager:"pager",
				url:"dtjs_tsdzbsh.do?method=getTsdzbShList&type=query",
				colList : [
							{ label : 'dzbid', name : 'dzbid', index : 'dzbid',key : true, hidden : true },
							{ label : '党支部', name : 'dzbmc', index : 'dzbmc', width : '15%',formatter:xhLink },
							{ label : '负责人', name : 'fzr', index : 'fzr', width : '10%' },
							{ label : '联系方式', name : 'lxfs', index : 'lxfs', width : '15%' },
							{ label : '创建时间', name : 'cjsj', index : 'cjsj', width : '15%' },
							{ label : '管辖班级', name : 'bmmc', index : 'bmmc', width : '35%' },
							{ label : '审核状态', name : 'shztmc', index : 'shztmc', width : '10%' },
							{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true },
							{ label : '评分', name : 'pf', index : 'pf', width : '5%' }
						  ],
				params:{shzt:"dsh"},
			 	radioselect:false
		}

		var gridSetting2 = {
				caption:"特色党支部审核列表",
				pager:"pager",
				url:"dtjs_tsdzbsh.do?method=getTsdzbShList&type=query",
				colList : [
							{ label : 'dzbid', name : 'dzbid', index : 'dzbid',key : true, hidden : true },
							{ label : '党支部', name : 'dzbmc', index : 'dzbmc', width : '15%',formatter:xhLink },
							{ label : '负责人', name : 'fzr', index : 'fzr', width : '10%' },
							{ label : '联系方式', name : 'lxfs', index : 'lxfs', width : '15%' },
							{ label : '创建时间', name : 'cjsj', index : 'cjsj', width : '15%' },
							{ label : '管辖班级', name : 'bmmc', index : 'bmmc', width : '35%' },
							{ label : '审核状态', name : 'shztmc', index : 'shztmc', width : '10%' },
							{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true },
							{ label : '评分', name : 'pf', index : 'pf', width : '5%' }
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
		<input type="hidden" id="shzt" value="dsh"/>
		<html:form action="/dtjs_tsdzbsh">		
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="sbsh();return false;" 
							   title="选中您要审核的记录，点击该按钮可以打开审核页面。"
							   class="btn_sh">审核</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelSh();return false;" 
							   title="选中一条记录，点击该按钮您可以撤消失误的审核操作。"
							   class="btn_qxsh">撤消</a>
						</li>	
						</logic:equal>					
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
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
				<span>特色党支部审核列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
