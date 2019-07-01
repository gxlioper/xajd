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
				url:"gyglnew_bxlbrywh.do?method=ViewBxlbYhList&type=query",
				colList:[
				   {label:'用户名',name:'yhm', index: 'yhm',width:'20%',key:true},
				   {label:'姓名',name:'xm', index: 'xm',width:'20%'},			
				   {label:'所在部门',name:'bmmc', index: 'bmmc',width:'20%'},
				   {label:'部门代码',name:'bmdm', index: 'bmdm',width:'20%',hidden:true}
				],
				sortname: "yhm",
			 	sortorder: "asc"
			}

			jQuery(function(){
				searchRs();
			});

			function searchRs(){
				var map = getSuperSearch();	
				gridSetting["params"] = map;
				var bxlb = jQuery("#bxlb").val();
				map["bxlb"] = bxlb;
				jQuery("#dataTable").initGrid(gridSetting);
			}
		</script>
	</head>

	<body>
		<html:form action="/gyglnew_bxlbrywh">
	        <html:hidden property="bxlb" styleId="bxlb" />
			<!-- 过滤条件 -->	
			<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- 过滤条件 end-->
		</html:form>
		<div>
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 人员信息列表
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:280px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
		<div id="pager"></div>
	</body>
</html>
