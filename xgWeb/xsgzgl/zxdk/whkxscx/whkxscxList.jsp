<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/whkxscx/js/whkxscx.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "未还款学生查询结果列表",
				pager : "pager",
				url : "zxdk_whkxscx.do?method=getWhkxscxList&type=query",
				colList : [
							{ label : '学号', name : 'xh', index : 'xh', width : '10%' },
							{ label : '姓名', name : 'xm', index : 'xm', width : '10%' },
							{ label : '性别', name : 'xb', index : 'xb', width : '5%' },
							{ label : '年级', name : 'nj', index : 'nj', width : '8%' },
							{ label : '学院', name : 'xymc', index : 'xydm', width : '25%' },
							{ label : '贷款项目', name : 'dkxm', index : 'dkxm', width : '20%' },
							{ label : '金额', name : 'sqje', index : 'sqje', width : '10%' },
							{ label : '申请时间', name : 'sqsj', index : 'sqsj', width : '15%' }],
					sortname : "sqsj",
				    sortorder : "asc" }
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
		
		<html:form action="/zxdk_whkxscx">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
			
				<logic:equal value="zf01" name="userName">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>										
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;" >导出</a></li>						
					</ul>
				</div>
				</logic:equal>
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>未还款学生查询结果列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
		
	</body>
</html>
