<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/jxgl/general/jxkqgl/jxkqjg/js/jxkqjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
				<script type="text/javascript">
				jQuery(function(){
				     var gridSetting = {
								caption:"军训请假列表",
								pager:"pager",
								url:"jxkqjg.do?method=jxkqjgList&type=query",
								params:getSuperSearch(),
								colList:[
								   {label:'考勤结果id',name:'kqid', index: 'kqid',key:true,hidden:true},
								   {label:'学号',name:'xh', index: 'xh',formatter:xhLink},
								   {label:'姓名',name:'xm', index: 'xm'},
								   {label:'性别',name:'xb', index: 'xb'},
								   {label:'学院',name:'xymc', index: 'xymc'},
								   {label:'专业',name:'zymc', index: 'zymc'},
								   {label:'班级',name:'bjmc', index: 'bjmc'},
								   {label:'军训名称',name:'jxmc', index: 'jxmc'},
								   {label:'考勤类别',name:'kqlbmc', index: 'kqlbmc'},
								   {label:'考勤类型',name:'kqlxmc', index: 'kqlxmc'},
								   {label:'考勤时间',name:'kqsj', index: 'kqsj'}
								],
								sortname: "kqsj",
							 	sortorder: "desc"
							}
						jQuery("#dataTable").initGrid(gridSetting);
						
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
	<html:form action="/jxkqjg?method=jxkqjgList&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">	
						<li>
							<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
						</li>
						<li><a href="javascript:void(0);" class="btn_dr" onclick="dr();return false;">导入</a></li>	
					
					</logic:equal>
						<li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
				</ul>
			</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
		</div>
		</html:form>
		<div class="toolbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 考勤结果列表 </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
