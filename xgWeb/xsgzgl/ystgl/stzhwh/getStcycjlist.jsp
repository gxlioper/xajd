<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/ystgl/stzhwh/js/stzhwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
	jQuery(function(){
			var gridSetting = {
				caption : "艺术团成员成绩明细",
				pager : "pager",
				url : "ystglStzhwh.do?method=getStcycjList&type=query",
				colList : [
							{ label : 'key', name : 'id', index : 'id',key : true, hidden : true },
							{ label : 'xmlbdm', name : 'xmlbdm', index : 'xmlbdm', hidden : true },
							{ label : 'ystlbdm', name : 'ystlbdm', index : 'ystlbdm', hidden : true },
							{ label : 'ystid', name : 'ystid', index : 'ystid', hidden : true },
							{ label : '学号', name : 'xh', index : 'xh', width : '10%'},
							{ label : '姓名', name : 'xm', index : 'xm', width : '10%' },
							{ label : '班级', name : 'bjmc', index : 'bjdm', width : '15%' },
							{ label : '专业', name : 'zymc', index : 'zydm', width : '15%' },
							{ label : '学院', name : 'xymc', index : 'xydm', hidden : true },
							{ label : '艺术团类别', name : 'ystlbmc', index : 'ystlbmc', width : '10%' },
							{ label : '项目类别', name : 'xmlbmc', index : 'xmlbmc', width : '15%' },
							{ label : '艺术团项目名称', name : 'ystxmmc', index : 'ystxmmc', width : '15%'  },
							{ label : '指导老师', name : 'zdlsxm', index : 'zdlsxm', width : '12%' },
							{ label : '学年', name : 'xn', index : 'xn', width : '12%' },
							{ label : '考核结果', name : 'cjpd', index : 'cjpd', width : '10%'}
							],
					sortname : "xn",
				    sortorder : "desc" }
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<input type="hidden" name="usertype" value="${usertype}">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/ystglStzhwh.do?method=getStcycjList">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>艺术团成员成绩明细&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
