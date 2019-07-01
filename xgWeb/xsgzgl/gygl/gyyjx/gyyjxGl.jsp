<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gyyjx/js/gyyjx.js"></script>
		<script type="text/javascript" >
		var gridSetting_gl = {
				caption : "查询结果",
				pager : "pager",
				url : "gygl_gyyjxstu.do?method=listGl&type=query",
				colList : [
						{ name : 'gyyjid', index : 'gyyjid', key : true , hidden : true},
						{ label : '学号', name : 'xh', index : 'xh',  width : '12%' ,formatter:xhLink_stu},
						{ label : '姓名', name : 'xm', index : 'xm', width : '12%' },
						{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '15%' },
						{ label : '所在楼栋', name : 'ldmc', index : 'ldmc', width : '13%' },
						{ label : '意见分类', name : 'yjflmc', index : 'yjflmc', width : '15%' },
						{ label : '提意见时间', name : 'yjsj', index : 'yjsj', width : '12%' },
						{ label : '反馈情况', name : 'fkqkmc', index : 'fkqkmc', width : '8%' },
						{ name : 'fkqk', index : 'fkqk',  hidden : true}],
				sortname : "yjsj", sortorder : "desc" };
		jQuery(function() {
			jQuery("#dataTable").initGrid(gridSetting_gl);
		});
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/gygl_gyyjxstu">

		
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">	
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="fk_gl();return false;" >反馈</a>
						</li>
						<li>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
						</li>
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
				<span>查询结果&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
