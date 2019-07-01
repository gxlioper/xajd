<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
		
			function selectZxs(rowData){
				var api = frameElement.api;
				
				if (api){
					if (api.get('childDialog')){
						var w = api.get('parentDialog');
						updateElementContent(rowData,w);
					} else {
						var w = api.opener;
						updateElementContent(rowData,w);
					}
				} else if (parent.window){
					var w = parent.window;
					updateElementContent(rowData,w);
					
				}
				
				iFClose();
			}
			
			var gridSetting = {
				caption:"教职工信息列表",
				pager:"pager",
				multiselect:false,
				rowNum:10,
				url:"qgzx_gwglnew.do?method=showFdys&type=query",
				colList:[
				   {label:'职工号',name:'zgh', index: 'zgh',width:'20%',key:true},
				   {label:'姓名',name:'xm', index: 'xm',width:'20%'},
				   {label:'性别',name:'xbmc', index: 'xbmc',width:'15%'},
				   {label:'部门',name:'bmmc', index: 'bmmc',width:'30%'},
				    {label:'操作',name:'xh', index: '',width:'58px',noSort:true,formatter:function(cell,rowObject){
					   var rowData = JSON.stringify(rowObject);
						return '<button type=\'button\' onclick=\'selectZxs('+rowData+');\' class=\'btn_01\' >选择</button>';
				   }}
				],
			
				sortname: "zgh",
			 	sortorder: "asc"
			}

			function updateElementContent(rowData,w){
				jQuery("#gwshr_"+jQuery("#idxh").val(),w.document).val(rowData["zgh"]);
				jQuery("#gwshrxm_"+jQuery("#idxh").val(),w.document).val(rowData["xm"]);
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
		<input type="hidden" value="${gotoPath}" id="gotoPath"/>
		<input type="hidden" value="${idxh}" id="idxh"/>
	
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
				<span> 教职工信息列表${gotoPath}
				 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
