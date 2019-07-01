<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gyyjx/js/gyyjx.js"></script>
		<script type="text/javascript" >
		jQuery(function() {
			jQuery("#dataTable").initGrid(gridSetting_dmwh);
		});
		
		</script>
	</head>

	<body>
		<button id="search_go" type="button" style="display:none" onclick="jQuery('#dataTable').reloadGrid();"></button>
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	
		<div class="toolbox">
			<!-- ��ť -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add_dmwh();" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" onclick="update_dmwh();" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="del_dmwh();" class="btn_sc">ɾ��</a></li>						
				</ul>
			</div>
			</logic:equal>
		</div>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ��������б� </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
