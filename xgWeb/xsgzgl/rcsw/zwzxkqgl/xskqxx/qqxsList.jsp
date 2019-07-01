<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zwzxkqgl/js/kqjg.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "学生考勤信息列表",
				pager : "pager",
				url : "zwzxkqKqjg.do?method=getQqxsList&type=query",
				colList : [
							{ label : 'key', name : 'id', index : 'id',key : true, hidden : true },
							{ label : 'bjdm', name : 'bjdm', index : 'bjdm',hidden : true },
							{ label : '学号', name : 'xh', index : 'xh', width : '10%',formatter : xhLink },
							{ label : '姓名', name : 'xm', index : 'xm', width : '10%' },
							{ label : '性别', name : 'xb', index : 'xb', width : '5%' },
							{ label : '学年', name : 'xn', index : 'xn', width : '10%' },
							{ label : '学期', name : 'xqmc', index : 'xqmc', width : '5%' },
							{ label : '抽查类型', name : 'cclxmc', index : 'cclxmc', width : '10%' },
							{ label : '缺勤类型', name : 'qqlxmc', index : 'qqlxmc', width : '10%' },
							{ label : '旷课节数', name : 'kkjs', index : 'kkjs', width : '5%' },
							{ label : '抽查日期', name : 'ccrq', index : 'ccrq', width : '10%' }],
					sortname : "ccrq",
				    sortorder : "desc" }
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		function searchRs() {
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
			}
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
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						 <li>
                            <a href="javascript:;" onclick="qqxsDr();return false;" id="btn_dr" class="btn_dr">导入</a>
                       </li>
                       <li>
                            <a href="javascript:;" onclick="kqxxTb();return false;"  class="btn_sx">考勤信息同步</a>
                       </li>
						</logic:equal>
							<li><a href="#" class="btn_dc" onclick="qqxsExportConfig();return false;">导出</a></li>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>学生考勤信息列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
