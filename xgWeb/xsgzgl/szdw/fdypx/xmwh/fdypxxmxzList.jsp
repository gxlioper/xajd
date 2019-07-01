<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/fdypx/js/fdypxxmwh.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting_xz);
				//Ϊbuttonע���¼�
				//frameElement.api.get('parentDialog').iFClose();
				jQuery("#btn_cz").click(function(){searchReset()});
				jQuery("#btn_zj").click(xzxm);
				
			});
			
		</script>
	</head>

	<body>
	
		
		<html:form action="/szdw_fdypxxmwh.do">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
					<div class="buttonbox">
						<ul>
							<li><a href="javascript:void(0);" id="btn_zj" class="btn_zj">ȷ��</a></li>
						</ul>
					</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>����Ա��ѵ��Ŀ�б�</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
