<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/xsxxgl/cfxy/js/xsxxglCx.js"></script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/xsxx_xsxxgl" method="post">
			<input type="hidden" name="method" id="method" value="${method}"/>
	
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xhstr" id="xhstr" />
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="showzxsxxView();return false;" id="btn_ck"
								class="btn_ck"> �鿴 </a>
						</li>
					</ul>
				</div>
				<!-- ��ť end-->

				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>

		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>ѧ����Ϣ�б�</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
