<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/knsjgwh/js/knsjgwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "困难生调查结果维护列表",
				pager : "pager",
				url : "xszz_knsdcwh.do?method=getKnsWhList&type=query",
				colList : [
							{ label : 'id', name : 'id', index : 'id',key : true, hidden : true },
							{ label : '年度', name : 'nd', index : 'nd', width : '4%' },
							{ label : '院系名称', name : 'xymc', index : 'xymc', width : '10%' },
							{ label : '专业名称', name : 'zymc', index : 'zymc', hidden : true },
							{ label : '班级名称', name : 'bjmc', index : 'bjmc', width : '10%' },
							{ label : '姓名', name : 'xm', index : 'xm', width : '5%' },
							{ label : '身份证号', name : 'sfzh', index : 'sfzh', width : '13%'},
							{ label : '学号', name : 'xh', index : 'xh', width : '8%',formatter:bjLink },
							{ label : '总分', name : 'zf', index : 'zf', width : '4%' },
							{ label : '状态', name : 'zt', index : 'zt', width : '8%' }
						  ],
						  sortname: "nd,xymc,zymc,bjmc",
					 	  sortorder: "asc"
			};
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
		<html:form action="/xszz_knsdcwh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="importXf();return false;" class="btn_dr" >导入</a>
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
			<h3 class=datetitle_01>
				<span>困难生调查结果维护列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>