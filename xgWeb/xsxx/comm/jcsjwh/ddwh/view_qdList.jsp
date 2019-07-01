<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"查询结果",
				pager:"pager",
				multiselect:true,
				rowNum:10,
				url:"zjjcddwh.do?method=viewQd&type=query",
				colList:[
				   {label:'大队代码',name:'bjdm', index: 'bjdm',width:'20%',key:true,hidden:true},
				   {label:'年级',name:'nj', index: 'nj',width:'20%'},			
				   {label:'学院名称',name:'xymc', index: 'xymc',width:'20%'},	
				   {label:'专业名称',name:'zymc', index: 'zymc',width:'20%'},
				   {label:'区队名称',name:'bjmc', index: 'bjmc',width:'20%'}
				],
				sortname: "bjdm",
			 	sortorder: "asc"
			}

			jQuery(function(){
				searchRs();
			});
			
			function searchRs(){
				var map = getSuperSearch();	
				var dddm = jQuery("#dddm").val();
				map["dddm"] = dddm;
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			}
		</script>
	</head>

	<body>
		<html:form action="/zjjcddwh">
	        <html:hidden property="dddm" styleId="dddm" />
			<!-- 过滤条件 -->	
			<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- 过滤条件 end-->
		</html:form>
		<div>
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 区队信息列表
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
		<div id="pager"></div>
	</body>
</html>
