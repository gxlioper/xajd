<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/newqjgl/xsqjcx/js/xsjqcx.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "ѧ����ٽ���б�",
				pager : "pager",
				url : "xsqj_jscx.do?method=getQjcxList&type=query",
				colList : [ {
					label : 'key',
					name : 'id',
					index : 'id',
					key : true,
					hidden : true
				}, {
					label : 'ѧ��',
					name : 'xh',
					index : 'xh',
					width : '10%',
					formatter : xhLink
				},{
					label : 'sjly',
					name : 'sjly',
					index : 'sjly',
					hidden : true
				},{
					label : 'gjxxid',
					name : 'qsl',
					index : 'qsl',
					hidden : true
				}, {
					label : '����',
					name : 'xm',
					index : 'xm',
					width : '6%'
				},{
					label : '�������',
					name : 'qjlxmc',
					index : 'qjlx',
					width : '8%'
				},{
					label : '������ʷID',
					name : 'actinstid',
					index : 'actinstid',
					hidden : true
				},{
					label : '��ٿ�ʼʱ��',
					name : 'qjkssj',
					index : 'qjkssj',
					width : '15%'
				},{
					label : '��ٽ���ʱ��',
					name : 'qjjssj',
					index : 'qjjssj',
					width : '17%'
				},{
					label : '�����������',
					name : 'qjtslx',
					index : 'qjtslx',
					width : '17%'
				},{
					label : '�������',
					name : 'qjts',
					index : 'qjts',
					width : '8%'
				},{
					label : '��ٽڴ�',
					name : 'qjjc',
					index : 'qjjc',
					width : '8%'
				},{
					label : '�Ƿ񲹼�',
					name : 'sfbj',
					index : 'sfbj',
					width : '8%'
				}],
				sortname : "qjkssj",
				sortorder : "desc",
				multiselect: false
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>
	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xsqj_jscx">
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
				<span>ѧ����ٽ���б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
