<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/wsjc/fslr/js/zjcmTjcx.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "������ͳ�Ʋ�ѯ����б�",
				pager : "pager",
				url : "gyglnew_fslr_ajax.do?method=searchForZjcmTjCx&type=query",
				colList : [{
					label : 'ѧԺ',
					name : 'xymc',
					index : 'xymc',
					width : '20%'
				}, {
					label : '���޸���',
					name : 'zs',
					index : 'zs',
					width : '10%'
				}, {
					label : '��������',
					name : 'yxrs',
					index : 'yxrs',
					width : '10%'
				}, {
					label : '�ٷֱ�',
					name : 'yxbfb',
					index : 'yxbfb',
					width : '10%'
				}, {
					label : '�ϸ�����',
					name : 'hgrs',
					index : 'hgrs',
					width : '10%'
				},{
					label : '�ٷֱ�',
					name : 'hgbfb',
					index : 'hgbfb',
					width : '10%'
				},{
					label : '���ϸ�����',
					name : 'bhgrs',
					index : 'bhgrs',
					width : '10%'
				},{
					label : '�ٷֱ�',
					name : 'bhgbfb',
					index : 'bhgbfb',
					width : '10%'
				}
				],
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/gygl_xyzsjggl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>������ͳ�Ʋ�ѯ����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
