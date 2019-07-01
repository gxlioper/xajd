<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/rwdj/js/rwdj.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			jQuery(function(){
			     var gridSetting = {
							pager:"pager",
							url:"rwdj.do?method=list&type=query",
							params:getSuperSearch(),
							colList:[
							   {label:'rwdjid',name:'rwdjid', index: 'rwdjid',key:true,hidden:true},
							   {label:'学号 ',name:'xh', index: 'xh',formatter:dcmcLink},
							   {label:'姓名',name:'xm', index: 'xm'},
							   {label:'性别',name:'xb', index: 'xb'},
							   {label:'年级',name:'nj', index: 'nj'},
							   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
							   {label:'专业',name:'zymc', index: 'zymc'},
							   {label:'班级',name:'bjmc', index: 'bjmc'},
							   {label:'入伍时间',name:'rwsj', index: 'rwsj'},
							   {label:'服役部队',name:'fybd', index: 'fybd'},
							    {label:'sjly',name:'sjly', index: 'sjly',hidden:true}
							],
						 	sortorder: "asc"
						}
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
		<html:form action="/rwdj.do?method=list&type=query">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="search_go" onclick="reload()" />
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
							<li>
								<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">增加</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
							</li>
							<li>
								<a href="#" onclick="drxx();return false;" class="btn_csh">导入</a>
							</li>
							<li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="toolbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span style="width: 50%">查询列表</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
