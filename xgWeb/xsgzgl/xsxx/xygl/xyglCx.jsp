<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/xygl/js/xyglCx.js"></script>
		<script type="text/javascript">
			var gridSetting = {
			caption : "У����Ϣ�б�",
			pager : "pager",
			url : "xsxx_xyglxx.do?method=xyglList&type=query",
			colList : [
					{ label : 'ѧ��', name : 'xh', index : 'xh', key : true, formatter : xhLink, width : '8%' },
					{ label : '����', name : 'xm', index : 'xm', width : '8%' },
					{ label : '�Ա�', name : 'xb', index : 'xb', width : '8%' },
					{ label : '��ϵ��ʽ', name : 'sjhm', index : 'sjhm', width : '8%' },
					{ label : '�꼶', name : 'nj', index : 'nj', width : '9%' },
					{ label : 'ѧԺ', name : 'xymc', index : 'xymc', width : '8%' },
					{ label : 'רҵ', name : 'zymc', index : 'zymc', width : '13%' }],
			sortname : "xh", sortorder : "asc" };


			jQuery(function() {
				
				//gridSetting["params"] = getSuperSearch();
				
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

		<html:form action="/xsxx_xyglxx" method="post">
			<input type="hidden" name="method" id="method" value="${method}"/>
	
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xhstr" id="xhstr" />
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- ��дȨ -->
						<logic:equal name="writeAble" value="yes">
							
							<li>
								<a href="#" onclick="showXyglAdd();return false;" id="btn_zj"
									class="btn_zj"> ���� </a>
							</li>
							<li>
								<a href="#" onclick="showXyglEdit();return false;" id="btn_xg"
									class="btn_xg"> �޸� </a>
							</li>
							<li>
								<a href="#" onclick="deleteXygl();return false;" id="btn_sc"
									class="btn_sc"> ɾ�� </a>
							</li>
							
						</logic:equal>
							
						
						<li>
							<a href="#" class="btn_dc" id="btn_dc"
								onclick="xyglExportConfig();return false;">����</a>
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
				<span>У����Ϣ�б�</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
