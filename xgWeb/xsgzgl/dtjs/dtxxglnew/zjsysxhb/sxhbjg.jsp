<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		        <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		        <script type="text/javascript" src="js/calendar/calendar.js"></script>
				<script type="text/javascript" src="xsgzgl/dtjs/dtxxglnew/js/sxhb.js"></script>
				<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
				<script type="text/javascript">
				var gridSetting = {
						caption:"党团结果列表",
						pager:"pager",
						url:"zjsy_sxhb.do?method=list&type=query",
						colList:[
						   {label:'结果id',name:'sxhbid', index: 'sxhbid',key:true,hidden:true},
						   {label:'学号',name:'xh', index: 'xh',width:'12%'},
						   {label:'姓名',name:'xm', index: 'xm'},
						   {label:'年级',name:'nj', index: 'nj'},
						   {label:'班级',name:'bjmc', index: 'bjmc',width:'18%'},
						   {label:'上交阶段名称',name:'jdmc', index: 'jdmc'},
						   {label:'上交份数',name:'sjfs', index: 'sjfs',width:'8%'},
						   {label:'上交时间',name:'sjsj', index: 'sjsj',width:'12%'},
						   {label:'阶段上交总数',name:'jdzs', index: 'jdzs',width:'8%'}
						],
						sortname: "sjsj",
					 	sortorder: "asc"
					}
					jQuery(function(){
						gridSetting["params"]=getSuperSearch();
						jQuery("#dataTable").initGrid(gridSetting);
					});
				//查询
				function searchRs(){
					var map = getSuperSearch();
					jQuery("#dataTable").reloadGrid(map);
				}
				
				</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/zjsy_sxhb">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<logic:equal value="yes" name="writeAble">
						<li>
							<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="mod();return false;" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
						</li>
						<li><a href="#" onclick="importConfig();return false;" class="btn_dr">导入数据</a></li>
						<li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
					</logic:equal>
				</ul>
			</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->		</div>
		</html:form>
		<div class="toolbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>思想汇报材料列表</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
