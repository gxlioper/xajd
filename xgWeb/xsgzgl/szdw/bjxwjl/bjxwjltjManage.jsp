<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/bjxwjl/js/tj.js"></script>
		<script type="text/javascript">
		
	var gridSetting = {
		caption : "�༶��Ϊ��¼ͳ��",
		pager : "pager",
		url : "szdw_bjxwjltj.do?method=query",
		colList : [
				{ label : 'key', name : 'bjdm', index : 'bjdm',key : true, hidden : true },
				{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xydm', width : '12%' },
				{ label : '�༶', name : 'bjmc', index : 'bjmc', width : '12%' },
				{ label : '����Ա', name : 'fdylbxx', index : 'fdylbxx', width : '30%' },
				{ label : '��¼��', name : 'fdyrs', index : 'fdyrs', width : '8%' },
				{ label : 'У�ڵ�ʦ', name : 'bzrlbxx', index : 'bzrlbxx', width : '30%' },
				{ label : '��¼��', name : 'bzrrs', index : 'bzrrs', width : '8%' }],
		sortname : "xymc" , sortorder : "asc"}

	jQuery(function() {
		gridSetting["params"] = getSuperSearch();
		jQuery("#dataTable").initGrid(gridSetting);
	});


	
</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/szdw_bjxwjlwh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="ck();return false;" class="btn_ck">�鿴</a>
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
				<span>�༶��Ϊ��¼�����༶��ѯ��&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
