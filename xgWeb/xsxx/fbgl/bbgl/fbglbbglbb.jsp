<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/bbgl/js/fbglbbgl.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
			     var gridSetting = {
							caption:"查询结果",
							pager:"pager",
							url:"fbglbbgl.do?method=fbglbb&type=query",
							params:getSuperSearch(),
							multiselect:false,
							colList:[
							   {label:'pk',name:'pk', index: 'pk',key:true,hidden:true},
							   {label:'年级',name:'nj', index: 'nj'},
							   {label:'<bean:message key="lable.xb" />',name:'bmmc', index: 'bmmc'},
							   {label:'专业',name:'zymc', index: 'zymc'},
							   {label:'人数',name:'rs', index: 'rs'},
							   {label:'层次',name:'pyccmc', index: 'pyccmc'},
							   {label:'学制',name:'xz', index: 'xz'},
							   ${autoGrid}
							   {label:'<font color=\'red\'>*</font>班级个数',name:'bjgs', index: 'bjgs',formatter:bjgsFormatter},
							   {label:'流水号',name:'lsh', index: 'lsh',formatter:lshFormatter},
							   {label:'自动分班人数上限',name:'rssx', index: 'rssx',formatter:rssxFormatter}
							],
							sortname: "nj",
						 	sortorder: "asc"
						}
					//默认查询条件，我看起来丑但我很温柔。
					var pk=jQuery("#pk").val();
					var pzgzid=jQuery("#pzgzid").val();
			    	 var map = getSuperSearch();
			    	 map["pk"]=pk;
			    	 map["pzgzid"]=pzgzid;
			    	 gridSetting["params"] = map;
					jQuery("#dataTable").initGrid(gridSetting);
					//隐藏页面高级查询、分页
					jQuery("#gjcx").hide();
					jQuery("#pager").hide();
			});
		</script>
	</head>
	<body>
		<div>
			<html:form action="/fbglbbgl.do?method=add&type=query">
				<input type="hidden" id="pzgzid" value="${pzgzid}"/>
				<input type="hidden" id="bbzt" value="${bbzt}"/>
				<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
				<%@ include file="/comm/hiddenValue.jsp"%>
				<div id="gjcx" style="display: none">
					<!-- 过滤条件 -->
					<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
				<!-- 过滤条件 end-->
			</html:form>
			<div class="toolbox">
				<table id="dataTable"></table>
				<div id="pager" style="display: none"></div>
			</div>
		</div>
	</body>
</html>
