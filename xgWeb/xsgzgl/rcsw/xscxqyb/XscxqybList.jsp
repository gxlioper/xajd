<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript" src="xsgzgl/rcsw/xscxqyb/js/xscxqyb.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>  <!-- 高级查询时间需要 -->
		<script type="text/javascript">
			var gridSetting = {
				caption:"学生处学情月报管理列表",
				pager:"pager",
				url:"rcsw_xscxqyb.do?method=XscxqybList&type=query",
				colList:[
				   {label:'jgid',name:'jgid', index: 'jgid',hidden:true,key:true},
				   {label:'学年',name:'xn', index: 'xn',width:'8%'},
				   {label:'学期',name:'xqmc', index: 'xq',width:'6%'},
				   {label:'月份',name:'yf', index: 'yf',width:'8%',formatter:xhLink},
				   {label:'填写人',name:'xm', index: 'xm',width:'10%'},
				   {label:'填写时间',name:'txsj', index: 'txsj',width:'10%'},	
				   {label:'记录存在人',name:'txr', index: 'txr', width:'10%',hidden:true}			   
				],
				sortname: "txsj",
			 	sortorder: "asc"
			}
			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onClick='XscxqybView(\""+rowObject["jgid"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";	
			}
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
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
		<html:form action="/demo">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>	
							<li><a href="javascript:void(0);" onclick="add()" class="btn_zj">填写</a></li>
							<li><a href="javascript:void(0);" onclick="update()" class="btn_xg">修改</a></li>
							<li><a href="javascript:void(0);" onclick="del()" class="btn_sc">删除</a></li>						
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 学生处学情月报管理列表 </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
