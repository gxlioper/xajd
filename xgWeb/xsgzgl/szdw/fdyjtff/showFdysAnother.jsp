<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			function dcmcLink(cellValue, rowObject) {
				var rowData = JSON.stringify(rowObject);
				return '<button type=\'button\' onclick=\'show('+rowData+');\' class=\'btn_01\' >选择</button>';
			}
			function show(rowData){
				var api = frameElement.api;
				var W = api.get('parentDialog');
				W.showFdysNotF5CallBackAnother(rowData);
				api.close();
			}
			var gridSetting = {
				caption:"教师信息列表",
				pager:"pager",
				multiselect:false,
				rowNum:10,
				url:"szdw_fdyjtff.do?method=showFdysNotF5&type=query",
				colList:[
				   {label:'职工号',name:'zgh', index: 'zgh',width:'20%',key:true},
				   {label:'姓名',name:'xm', index: 'xm',width:'20%'},
				   {label:'性别',name:'xbmc', index: 'xbmc',width:'15%'},
				   {label:'部门',name:'bmmc', index: 'bmmc',width:'30%'},
				   {label:'联系电话',name:'lxdh', index: 'lxdh',width:'30%',hidden:true},
				   {label:'操作',name:'cz', index: 'cz',width:'',formatter:dcmcLink}
				],
				dblclick:function(rowObject){
					show(rowObject);
				},
				sortname: "zgh",
			 	sortorder: "asc"
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
	
		<html:form action="/xsxx_xsgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 教师信息列表
				 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
