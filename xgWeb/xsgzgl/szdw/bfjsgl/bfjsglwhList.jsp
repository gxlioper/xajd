<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/bfjsgl/js/bfjsgl.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "班风建设列表",
				pager : "pager",
				url : "bfjsgl_bfjsglwh.do?method=bfjsglwhList&type=query",
				colList : [
							{ label : 'jcid', name : 'jcid', index : 'jcid',key : true, hidden : true },
							{ label : '年级', name : 'nj', index : 'nj', width : '8%' },
							{ label : '学院', name : 'xymc', index : 'xymc', width : '25%' },
							{ label : '专业', name : 'zymc', index : 'zymc', width : '20%' },
							{ label : '班级', name : 'bjmc', index : 'bjmc', width : '20%' },
							{ label : '人数', name : 'bjrs', index : 'rs', width : '5%' },
							{ label : '早操到勤', name : 'zcs', index : 'zcs', width : '5%' },
							{ label : '早读到勤', name : 'zds', index : 'zds', width : '5%' },
							{ label : '上课到勤', name : 'sks', index : 'sks', width : '5%' },
							{ label : '晚自习到勤', name : 'wzxs', index : 'wzxs', width : '5%' },
							{ label : '检查日期', name : 'jcrq', index : 'jcrq', width : '15%' }
						  ]
							 }
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
		<html:form action="/bfjsgl_bfjsglwh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;">增加</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
							</li>
						</logic:equal>
							<li>
								<a href="#" onclick="showView(); return false;" class="btn_ck">查看</a>
							</li>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
<%--							<li><a href="javascript:void(0);" onclick="printzzdsqb('xgygl_zdsq.do?method=printzzdsqb');return false;" class="btn_down">下载登记表</a></li>	--%>
						
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>班风建设列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
