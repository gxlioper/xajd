<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/khgl/jgcx/pfxq/js/pfxq.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" >
		</script>
	</head>
	<body>
	<html:form action="/khglPfxq" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
	<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="viewPf();return false;" class="btn_ck">�鿴</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">����</a>
						</li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
	</html:form>
			<div class="main_box">
					<h3 class=datetitle_01>
						<span>�����б�</span>
					</h3>
					<div class="con_overlfow">
						<table id="dataTable" ></table>
						<div id="pager"></div>
					</div>
				</div>		
	</body>
</html>
