<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script type="text/javascript"	src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var lddm=jQuery("#lddm").val();
				var qsh=jQuery("#qsh").val();
				var gridSetting = {
					pager:"pager",
					multiselect:false,
					url:"jtpjsq.do?method=getQsxsxxList&lddm="+lddm+"&qsh="+qsh,
					colList:[
					   {label:'床位号',name:'cwh', index: 'cwh',width:'10%'},
					   {label:'学号',name:'xh', index: 'xh',width:'25%'},
					   {label:'姓名',name:'xm', index: 'xm',width:'25%'},
					   {label:'学院',name:'xymc', index: 'xymc',width:'40%'}
					],
					sortname: "cwh",
				 	sortorder: "asc"
				}
				jQuery("#dataTable").initGrid(gridSetting);
			});
		</script>
	</head>
	<body>
		<div class="toolbox">
			<table id="dataTable"></table>
		</div>
	</body>
</html>
