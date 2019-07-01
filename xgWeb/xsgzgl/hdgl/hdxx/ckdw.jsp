<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			
			var gridSetting = {
				caption:"����б�",
				pager:"pager",
				multiselect:false,
				rowNum:10,
				url:"hdgl_hdxx.do?method=ckdw&type=query&hdid="+'${hdid}'+"&dwid="+'${dwid}',
				colList:[
				   {label:'hdid',name:'hdid', index: 'hdid',hidden : true},
				   {label:'�������',name:'dwid', index: 'dwid',width:'20%',key : true},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'20%'},
				   {label:'����',name:'xm', index: 'xm',width:'20%'},
				   {label:'ְ��',name:'dwzwmc', index: 'dwzwmc',width:'20%'}
				]
			}

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
		</script>
	</head>

	<body>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ��ʦ��Ϣ�б�
				 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
