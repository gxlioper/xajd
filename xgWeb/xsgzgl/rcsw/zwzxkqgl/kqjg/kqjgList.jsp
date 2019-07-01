<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zwzxkqgl/js/kqjg.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "${gnmkmc }列表",
				pager : "pager",
				url : "zwzxkqKqjg.do?method=getKqjgList&type=query",
				colList : [
							{ label : 'key', name : 'jgid', index : 'jgid',key : true, hidden : true },
							{ label : 'bjdm', name : 'bjdm', index : 'bjdm',hidden : true },
							{ label : 'sjly', name : 'sjly', index : 'sjly',hidden : true },
							{ label : '学年', name : 'xn', index : 'xn', width : '10%' },
							{ label : '学期', name : 'xqmc', index : 'xqmc', width : '5%' },
							{ label : '年级', name : 'nj', index : 'nj', width : '10%' },
							{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '14%' },
							{ label : '专业', name : 'zymc', index : 'zymc', width : '14%' },
							{ label : '班级', name : 'bjmc', index : 'bjmc', width : '12%',formatter : bjmcLink },
							{ label : '${cclxTitle}', name : 'cclxmc', index : 'cclxmc', width : '5%' },
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
							<logic:equal value="true" name="gnmkmcKq">
							{ label : '纪律分', name : 'jlf', index : 'jlf',width : '5%' },
							</logic:equal>
							{ label : '${ccrqTitle}', name : 'ccrq', index : 'ccrq', width : '10%' }],
					sortname : "ccrq", sortorder : "desc" };
			
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
		<html:form action="/zwzxkqKqjg">
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<input type="hidden" id="gnmkmc" value="${gnmkmc}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
						</li>
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
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
