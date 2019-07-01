<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"寝室信息列表",
				pager:"pager",
				multiselect:false,
				rowNum:10,
				url:"gyjc_ccjgcx.do?method=showQsxx&type=query",
				colList:[
				   {label:'楼栋',name:'ldmc', index: 'ldmc',width:'10%'},
				   {label:'楼栋代码',name:'lddm', index: 'lddm',width:'10%'},
				   {label:'层号',name:'ch', index: 'ch',width:'5%'},
				   {label:'寝室号',name:'qsh', index: 'qsh',width:'5%'},
				   {label:'学院代码',name:'xydm', index: 'xydm',width:'10%'},
				   {label:'学院',name:'xymc', index: 'xymc',width:'10%'},
				   {label:'操作',name:'xh', index: '',width:'5%',noSort:true,formatter:dcmcLink}
				],
				sortname: "ldmc",
			 	sortorder: "desc"
			}

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function dcmcLink(cellValue, rowObject) {
				var rowData = JSON.stringify(rowObject);
				return '<button type=\'button\' onclick=\'show('+rowData+');\' class=\'btn_01\' >选择</button>';
			}
			function show(rowData){
				var api = frameElement.api;
				var W = api.get('parentDialog');
				W.showQsxxNotF5CallBack(rowData);
				api.close();
			}
			
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>

	<body>
		<input type="hidden" value="${gotoPath}" id="gotoPath"/>
	
		<html:form action="/gyjc_ccjgcx">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div>
					<!--标题start-->
			<h3 class="datetitle_01">
				<span> 寝室信息列表
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
