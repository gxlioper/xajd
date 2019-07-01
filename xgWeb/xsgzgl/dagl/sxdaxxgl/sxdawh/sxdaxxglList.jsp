<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/dagl/sxdaxxgl/js/sxdaxxgl.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption : "信息列表",
				pager : "pager",
				url : "sxDaxxgl.do?method=getdalist&type=query",
				colList:[							
						    { label:'key',name:'bjid', index: 'bjid',hidden:true,key:true},
							{ label : '学院', name : 'xymc', index : 'xymc', width : '12%'},
							{ label : '专业', name : 'zymc', index : 'zymc', width : '10%'},
							{ label : '年级', name : 'nj', index : 'nj', width : '10%'},
							{ label : '班级', name : 'bjmc', index : 'bjmc', width : '10%'},
							{ label : '班级总人数', name : 'bjrs', index : 'bjrs', width : '8%',formatter : bjrsLink	 },
							{ label : '已维护人数', name : 'ywhrs', index : 'xymc', width : '8%'},
							{ label : '未维护人数', name : 'wwhrs', index : 'xymc', width : '8%'},
							{label : '班级',name : 'bjdm',index : 'bjdm',hidden : true}
							],
							sortname: "nj,zymc,xymc,bjmc",
					 		sortorder: "desc",
					 		radioselect:true
					 	}	
			jQuery(function(){
				var map = getSuperSearch();
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
		<html:form action="/sxDaxxgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_xg" onclick="wh();return false;"  >维护档案信息</a>
						</li>
						</logic:equal>
							<li>
							<a href="#" class="btn_dc" onclick="exportWH();return false;">导出</a>
						</li>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>班级列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
