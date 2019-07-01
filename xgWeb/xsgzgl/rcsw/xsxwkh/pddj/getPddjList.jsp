<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xsxwkh/pddj/js/pddj.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "评定等级信息列表",
				pager : "pager",
				url : "xsxwkhDjpd.do?method=getPddjList&type=query",
				colList : [
							{ label : 'key', name : 'jbfid', index : 'jbfid',key : true, hidden : true },
							{ label : '学号', name : 'xh', index : 'xh', width : '10%',formatter : xhLink  },
							{ label : '学年', name : 'xn', index : 'xn', width : '8%' },
							{ label : '姓名', name : 'xm', index : 'xm', width : '8%' },
							{ label : '学院', name : 'xymc', index : 'xymc', width : '10%' },
							{ label : '班主任</br>辅导员测评', name : 'bzrcpdj', index : 'bzrcpdj', width : '5%' },
							{ label : '班级</br>学生测评', name : 'xscpdj', index : 'xscpdj', width : '10%' },
							{ label : '奖励分', name : 'jlf', index : 'jlf', width : '5%' },
							{ label : '处罚分', name : 'cff', index : 'cff', width : '5%' },
							{ label : '记实分', name : 'jsf', index : 'jsf', width : '5%' },
							{ label : '记实评定', name : 'jsdj', index : 'jsdj', width : '5%' },
							{ label : '附加分', name : 'fjf', index : 'fjf', width : '5%' },
							{ label : '总分', name : 'zf', index : 'zf', width : '5%' },
							{ label : '终评等级', name : 'pddj', index : 'pddj', width : '10%' }],
					sortname : "xn,xh",
				    sortorder : "desc" }
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
		<html:form action="/xsxwkhDjpd">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<logic:equal name="userType" value="stu">
				<logic:equal value="true" name="flag">
				<div class="prompt" id="pts" style="">
					<h3><span>提示：</span> </h3>
					<p>本月处罚分已达到10分，请注意！</p>
				</div>
				</logic:equal>
			</logic:equal>
			<div class="toolbox">
			
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<logic:equal value="true" property="kgzt" name="csszForm">
						<li>
							<a href="javascript:void(0);" onclick="getZpdj();return false;" class="btn_xg" >终评等级生成</a>
						</li>
						</logic:equal>
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
			<h3 class=datetitle_01>
				<span>评定等级信息列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
