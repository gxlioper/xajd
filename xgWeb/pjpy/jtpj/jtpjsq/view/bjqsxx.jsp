<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script type="text/javascript"	src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var bjdm=jQuery("#bjdm").val();
			     var gridSetting = {
							pager:"pager",
							multiselect:false,
							url:"jtpjsq.do?method=qsxxlist&type=query&bjdm="+bjdm,
							colList:[
							   {label:'寝室',name:'qs', index: 'qs'},
							   {label:'人数',name:'rs', index: 'rs'},
							   {label:'评优类型名称 ',name:'pylbmc', index: 'pylbmc'},
							   {label:'学年学期 ',name:'xnxq', index: 'xnxq'}
							],
							sortname: "qs",
						 	sortorder: "asc"
						}
				jQuery("#dataTable").initGrid(gridSetting);
			});
		</script>
	</head>
	<body>
		<input type="hidden" id="bjdm" value="${bjdm}"/>
		<div class="toolbox">
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
