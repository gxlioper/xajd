<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zwzxkqgl/js/kqsh.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption : "${gnmkmc }列表",
				pager : "pager",
				url : "zwzxKqsh.do?method=getKqshList&type=query",
				colList : [
							{ label : 'key', name : 'sqid', index : 'sqid',key : true, hidden : true },
							{ label : 'bjdm', name : 'bjdm', index : 'bjdm',hidden : true },
							{ label : 'sjly', name : 'sjly', index : 'sjly',hidden : true },
							{ label : 'splc', name : 'splc', index : 'splc',hidden : true },
							{ label : '学年', name : 'xn', index : 'xn', width : '10%' },
							{ label : '学期', name : 'xqmc', index : 'xqmc', width : '5%' },
							{ label : '年级', name : 'nj', index : 'nj', width : '10%' },
							{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '18%' },
							{ label : '专业', name : 'zymc', index : 'zymc', width : '18%' },
							{ label : '班级', name : 'bjmc', index : 'bjmc', width : '15%',formatter : bjmcLink },
							{ label : '${cclxTitle}', name : 'cclxmc', index : 'cclxmc', width : '5%' },
							{ label : '${ccrqTitle}', name : 'ccrq', index : 'ccrq', width : '5%' },
							<logic:equal name="xxdm" value="2297">
								{ label : '应到人数', name : 'ydrsszly', index : 'ydrsszly', width : '5%' },
							</logic:equal>
							<logic:notEqual name="xxdm" value="2297">
								{ label : '应到人数', name : 'ydrs', index : 'ydrs', width : '5%' },
							</logic:notEqual>
							{ label : '实到人数', name : 'sdrs', index : 'sdrs', width : '5%' },
							<logic:equal name="xxdm" value="12970">
							{ label : '未返校人数', name : 'qqrs', index : 'qqrs', width : '5%' },
							</logic:equal>
							<logic:notEqual name="xxdm" value="12970">
							{ label : '缺勤人数', name : 'qqrs', index : 'qqrs', width : '5%' },
							</logic:notEqual>
							{ label : '纪律分', name : 'jlf', index : 'jlf',hidden : true },
							{ label : '申请时间', name : 'sqsj', index : 'sqsj', width : '15%' },
							{ label : '审核状态', name : 'shztmc', index : 'shztmc', width : '15%' },
							{ label : '审核状态', name : 'shzt', index : 'shzt', hidden : true},
							{label:'审核Id',name:'shid', index: 'shid',hidden:true},
							{label:'岗位Id',name:'gwid', index: 'gwid',hidden:true}
							],
					params:{shzt:"dsh"},
					sortname : "sqsj",
				    sortorder : "desc" 
					    };
			var gridSetting2 = {
					caption : "${gnmkmc }列表",
					pager : "pager",
					url : "zwzxKqsh.do?method=getKqshList&type=query",
					colList : [
								{ label : 'key', name : 'sqid', index : 'sqid',key : true, hidden : true },
								{ label : 'bjdm', name : 'bjdm', index : 'bjdm',hidden : true },
								{ label : 'sjly', name : 'sjly', index : 'sjly',hidden : true },
								{ label : 'splc', name : 'splc', index : 'splc',hidden : true },
								{ label : '学年', name : 'xn', index : 'xn', width : '10%' },
								{ label : '学期', name : 'xqmc', index : 'xqmc', width : '5%' },
								{ label : '年级', name : 'nj', index : 'nj', width : '10%' },
								{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '18%' },
								{ label : '专业', name : 'zymc', index : 'zymc', width : '18%' },
								{ label : '班级', name : 'bjmc', index : 'bjmc', width : '15%',formatter : bjmcLink },
								{ label : '${cclxTitle}', name : 'cclxmc', index : 'cclxmc', width : '5%' },
								{ label : '${ccrqTitle}', name : 'ccrq', index : 'ccrq', width : '5%' },
								<logic:equal name="xxdm" value="2297">
								   { label : '应到人数', name : 'ydrsszly', index : 'ydrsszly', width : '5%' },
							    </logic:equal>
							    <logic:notEqual name="xxdm" value="2297">
								   { label : '应到人数', name : 'ydrs', index : 'ydrs', width : '5%' },
							    </logic:notEqual>
								{ label : '实到人数', name : 'sdrs', index : 'sdrs', width : '5%' },
								{ label : '缺勤人数', name : 'qqrs', index : 'qqrs', width : '5%' },
								{ label : '纪律分', name : 'jlf', index : 'jlf',hidden : true },
								{ label : '申请时间', name : 'sqsj', index : 'sqsj', width : '15%' },
								{ label : '审核状态', name : 'shztmc', index : 'shztmc', width : '15%' },
								{ label : '审核状态', name : 'shzt', index : 'shzt', hidden : true},
								{ label:'审核Id',name:'shid', index: 'shid',hidden:true},
								{ label:'岗位Id',name:'gwid', index: 'gwid',hidden:true}
								],
						params:{shzt:"ysh"},
						sortname : "sqsj",
					    sortorder : "desc" 
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
		<html:form action="/zwzxKqsh">
		<input type="hidden" name="shkg" id="shkg" value="${shkg}"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<input type="hidden" id="gnmkmc" value="${gnmkmc}"/>
			<input type="hidden" id="shzt" value="dsh"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="kqsh();return false;" 
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
			<logic:equal name="xxdm" value="12970">
				<h3 class=datetitle_01>
					<span>假期返校学生汇总信息&nbsp;&nbsp; </span>
				</h3>
			</logic:equal>
			<logic:notEqual name="xxdm" value="12970">
				<h3 class=datetitle_01>
					<span>${gnmkmc }列表&nbsp;&nbsp; </span>
				</h3>
			</logic:notEqual>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
