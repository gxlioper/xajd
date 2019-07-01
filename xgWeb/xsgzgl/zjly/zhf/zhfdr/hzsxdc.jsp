<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zjly/zhf/zhfdr/js/zhfdr.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "zjly_zhfdr.do?method=getZhfdrCx&type=query&lb=hzdc",
				colList : [
							{ label : 'key', name : 'id', index : 'id',key : true, hidden : true },
							{ label : '学号', name : 'xh', index : 'xh', width : '10%',formatter:xhLink },
							{ label : '姓名', name : 'xm', index : 'xm', width : '8%' },
							{ label : '班级', name : 'bjmc', index : 'bjmc', width : '15%' },
							{ label : '所属项目', name : 'xmmkmc', index : 'xmmkmc', width : '10%' },
							{ label : '计分项目', name : 'jfxmmc', index : 'jfxmmc', width : '15%' },
							{ label : '分数', name : 'fs', index : 'fs', width : '5%' },
							{ label : '事项名称', name : 'sxsm', index : 'sxsm', width : '10%' },
							{ label : '事项得分', name : 'fs', index : 'fs', width : '5%' },
							{ label : '参与/获得时间', name : 'cysj', index : 'cysj', width : '5%' },
							{ label : '类别', name : 'lb', index : 'lb', width : '10%' },
							{ label : '申请状态', name : 'shztmc', index : 'shztmc', width : '5%' },
							{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true}					
							],
				sortname: "lrsj",
			 	sortorder: "desc",
			 	radioselect:false
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/gygl_xyzsjggl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
							<li><a href="javascript:void(0);" onclick="exportHzsx();return false;" class="btn_dc">导出</a></li>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>综合素质计分项目明细表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
