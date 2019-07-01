<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/jxgl/general/jxqjgl/js/jxqjjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
				<script type="text/javascript">
				jQuery(function(){
				     var gridSetting = {
								caption:"军训请假列表",
								pager:"pager",
								url:"jxqjjg.do?method=jxqjjgList&type=query",
								params:getSuperSearch(),
								colList:[
								   {label:'请假结果id',name:'qjid', index: 'qjid',key:true,hidden:true},
								   {label:'学号',name:'xh', index: 'xh',formatter:dcmcLink},
								   {label:'学年',name:'xn', index: 'xn'},
								   {label:'学期',name:'xqmc', index: 'xqmc'},
								   {label:'学院',name:'xymc', index: 'xymc'},
								   {label:'班级',name:'zymc', index: 'zymc'},
								   {label:'班级',name:'bjmc', index: 'bjmc'},
								   {label:'姓名',name:'xm', index: 'xm'},
								   {label:'性别',name:'xb', index: 'xb'},
								   {label:'请假类型',name:'qjlxmc', index: 'qjlxid'},
								   {label:'请假天数',name:'qjts', index: 'qjts'},
								   {label:'请假时间段',name:'qjsjd', index: 'qjsjd'},
								   {label:'申请时间',name:'sqsj', index: 'sqsj',hidden:true},
								   {label:'数据来源',name:'sjly', index: 'sjly',hidden:true}
								],
								sortname: "sqsj",
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
	<html:form action="/jxqjjg?method=jxqjjgList&type=query">
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
					<logic:notEqual name="xxdm" value="14073">
						<li><a href="javascript:void(0);" onclick="printjxqjjgb('jxqjjg.do?method=printQjjgb');return false;" class="btn_down">下载请假单</a></li>
					</logic:notEqual>  
					<logic:equal name="xxdm" value="14073">
						<li><a href="javascript:void(0);" onclick="printjxqjjgb('jxqjjg.do?method=printQjjgb');return false;" class="btn_down">下载请假单</a></li>
						<li><a href="javascript:void(0);" onclick="printjxqjBjd('jxqjjg.do?method=printBjd');return false;" class="btn_down">下载病假单</a></li>
					</logic:equal> 
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
				<span> 请假结果列表 </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
