<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>		
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src='xsgzgl/xlzx/zxzxthjl/js/zxzxthjlList.js'></script>
		<script type="text/javascript">
			var gridSetting = {
					caption:"谈话记录列表",
					pager:"pager",
					url:"xlzx_thjl_zxzxthjlgl.do?method=zxzxthjlList&type=query",
					colList:[
					   {label:'id',name:'id', index: 'id',hidden:true,key:true},
					   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
					   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
					   {label:'学院',name:'xymc', index: 'xymc',width:'10%'},
					   {label:'班级',name:'bjmc', index: 'bjmc',width:'10%'},
					   {label:'约谈人',name:'zgxm', index: 'zgxm',width:'10%'},
					   {label:'谈话时间',name:'thsj', index: 'thsj',width:'10%'}
					],
					sortname: "xh",
			 		sortorder: "asc"
				}
				function xhLink(cellValue,rowObject){
					return "<a href='javascript:void(0);' class='name' onClick='viewZxzxthjl(\""+rowObject["id"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
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
				<em>您的当前位置：</em><a>${title}</a>
			</p>
		</div>
		<html:form action="/demo">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" onclick="addZxzxthjl();return false;" class="btn_zj">增加</a></li>
						<li><a href="javascript:void(0);" onclick="updateZxzxthjl();return false;" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" onclick="deleteZxzxthjl();return false;" class="btn_sc">删除</a></li>
						</logic:equal>
						<li><a href="#" onclick="exportData();return false;" class="btn_dc">导出</a></li>
						<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">打印心理约谈记录表</a></li>
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
				<span> 心理普查谈话记录列表 </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
