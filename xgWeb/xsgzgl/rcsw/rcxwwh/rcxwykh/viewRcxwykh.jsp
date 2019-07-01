<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				var gridSetting = {
						caption:"日常行为列表",
						pager:"pager",
						params:getSuperSearch(),
						url:"rcsw_rcxwwh_rcxwykhgl.do?method=viewRcxwykh&type=query&id=${rs.id}",
						colList:[
						   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
						   {label:'行为大类',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'22%'},
						   {label:'行为类别',name:'rcxwlbmc', index: 'rcxwlbmc',width:'24%'},
						   {label:'发生时间',name:'fssj', index: 'fssj',width:'10%'},
						   {label:'评定分值',name:'fz', index: 'fz',width:'8%'},
						   {label:'给分理由',name:'gfly', index: 'gfly',width:'35%',formatter:function(cellValue,rowObject){
							   if(!cellValue){
								   cellValue = "";
							   }
							   var cellValueTemp = cellValue;
							   if(cellValue.length > 20){
								   cellValueTemp = cellValue.substring(0,20)+"...";
							   }
							   return jQuery("<span title='"+cellValue+"'>"+cellValueTemp+"</span>");
							 }
							}
						],
						sortname: "rcxwjlsj",
					 	sortorder: "desc"
					}
				jQuery("#dataTable").initGrid(gridSetting);

				var parentD = queryParentDocumentJgcxTj();
				jQuery("#xm_nd_yf_span").html("<font color='#0000ff'>"+jQuery("#xm_parent_hid",parentD).val()+"&nbsp;&nbsp;"+jQuery("#nd_parent_hid",parentD).val()+"年度"+jQuery("#yfmc_parent_hid",parentD).val()+"</font>");
			});
			function queryParentDocumentJgcxTj(){
				if(frameElement.api){
					var api = frameElement.api,W = api.opener;
					return W.document;
				}
				return parent.window.document;
			}
			function searchRs() {
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
	
		</script>
	</head>

	<body>
		<html:form action="/rcsw_rcxwwh_rcxwykhgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span id="xm_nd_yf_span"></span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
