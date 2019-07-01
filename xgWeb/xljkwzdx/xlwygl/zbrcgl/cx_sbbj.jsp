<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSettingBj = {
					caption:"班级信息列表",
					pager:"pager",
					multiselect:false,
					rowNum:10,
					url:"xljk_xlwygl_zbrcglwh.do?method=cxSbbjQuery&zbid=${zbid}&sbbjlx=${sbbjlx}",
					colList:[
					   {label:'<bean:message key="lable.xb" />名称',name:'xymc', index: 'xymc',width:'13%'},
					   {label:'专业名称',name:'zymc', index: 'zymc',width:'13%'},
					   {label:'班级代码',name:'bjdm', index: 'bjdm',width:'10%',key:true},
					   {label:'班级名称',name:'bjmc', index: 'bjmc',width:'13%'},
					   {label:'年级',name:'nj', index: 'nj',width:'6%'},
					   {label:'学生数',name:'xss', index: 'xss',width:'6%'}
					],
					sortname: "bjdm",
				 	sortorder: "asc"
				}
			jQuery(function(){
				var map = getSuperSearch();
				gridSettingBj["params"] = map;
				jQuery("#dataTable").initGrid(gridSettingBj);
			});
			function searchRs(){
				var map = getSuperSearch();	
				gridSettingBj["params"] = map;
				jQuery("#dataTable").initGrid(gridSettingBj);
			}

		</script>
	</head>

	<body>
		<html:form action="/jcsj">
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div class="toolbox">
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div>
					<!--标题start-->
			<h3 class="datetitle_01">
				<span> 班级信息列表
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
