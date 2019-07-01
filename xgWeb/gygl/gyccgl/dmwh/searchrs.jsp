<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="gygl/gyccgl/dmwh/js/dmwh.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
		function searchRs() {
			var tabType = jQuery("#tabType").val();
			var map={};
			map["cxlx"] = tabType;
			jQuery("#dataTable").reloadGrid(map);
		}
		jQuery(function() {
			var map={};
			map["cxlx"] = "wp";
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		</script>
		
	</head>
	<body>
	<html:form action="/gyccgl_dmwh"  method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
	<input type="hidden" id="tabType" value="wp"/>
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
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>						
				</ul>
			</div>
			</logic:equal>
				
			
			<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'wp');"><span>��Ʒ����</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'shcd');"><span>�𻵳̶�</span></a></li>
			      </ul>
			</div>
			<div style="display:none">
				<button  type="button" class="btn_cx" id="search_go" onclick="searchRs();">
										�� ѯ
			    </button>
			</div>
			
		</div>
			<div class="formbox">
			<!--����start-->
				<h3 class="datetitle_01">
					<span>��ѯ���</span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
