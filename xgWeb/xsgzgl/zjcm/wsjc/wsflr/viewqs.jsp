<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		
			jQuery(function(){
				var gridSetting = {
					caption:"学生信息列表",
					pager:"pager",
					rowNum:10,
					url:"cjWsflr.do?method=viewqs&type=query",
					colList:[
					   {label:'抽查年月',name:'ccny', index: 'ccny',width:'13%',key:true},
					   {label:'楼栋',name:'ldmc', index: 'ldmc',width:'15%'},
					   {label:'寝室号',name:'qsh', index: 'qsh',width:'10%'},
					   {label:'所属年级',name:'nj', index: 'nj',width:'10%'},
					   {label:'所属学院',name:'bmmc', index: 'bmmc',width:'20%'},
					   {label:'床位数',name:'cws', index: 'cws',width:'10%'},
					   {label:'入住人数',name:'rzrs', index: 'rzrs',width:'15%'},
					   {label:'分值',name:'fz', index: 'fz',width:'10%'},
					   {label:'等级',name:'dj', index: 'dj',width:'10%'}				   
					],
					params:{
						tjzt:jQuery("#tjzt").val(),
						ccny:jQuery("#ccny").val()
					}
				}
				jQuery("#dataTable").initGrid(gridSetting);
			});
	
			function searchRs(){
				var map = getSuperSearch();
				map["tjzt"] = jQuery("#tjzt").val();
				map["ccny"] = jQuery("#ccny").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/cjWsfDfgz">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id ="tjzt" name="tjzt" value="${tjzt}"/>
			<input type="hidden" id = "ccny" name="ccny" value="${ccny}"
			   
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
		<div class="formbox" style="width:100%;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
