<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/zjcm/wsjc/pfz/js/pfz.js"></script>	
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		function initGridSetting(){
			var gridSetting = {
					caption:"查询结果",
					pager:"pager",
					rowNum:10,
					url:"cjWsfPfz.do?method=viewPfzList&type=query&pfzid="+'${pfzid}',
					colList:[
							   {label:'用户名',name:'zgh', index: 'zgh',width:'10%',key:true},
							   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
							   {label:'性别',name:'xbmc', index: 'xbmc',width:'5%'},
							   {label:'所属部门',name:'bmmc', index: 'bmmc',width:'20%'}
							],
					sortname: "zgh",
				 	sortorder: "asc",
				}
			return gridSetting;
		}
		jQuery(function(){
			var gridSetting=initGridSetting();
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});

		//高级查询
		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}
			
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/cjWsfPfz">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id ="pfzid" value="${pfzid}"/>
			   
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="toolbox">
					<!--标题start-->
			<h3 class="datetitle_01">
				<span> 查询结果
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
