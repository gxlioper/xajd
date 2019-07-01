<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gyjc/jcjglr/js/jcjglr.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "gyjc_jcjglr.do?method=getJcjgLrList&type=query",
				colList : [ {
					label : 'key',
					name : 'guid',
					index : 'guid',
					key : true,
					hidden : true
				}, {
					label : '��ɫ',
					name : 'jsmc',
					index : 'jsmc',
					width : '10%'
				}, {
					label : '�������',
					name : 'ccrq',
					index : 'ccrq',
					width : '10%'
				}, {
					label : '��ֹ����',
					name : 'jzrq',
					index : 'jzrq',
					width : '10%'
				}, {
					label : '�����',
					name : 'wsjc',
					index : 'wsjc',
					width : '20%',
					formatter:jcxLink
				}, {
					label : '���ύ�����',
					name : 'tjs',
					index : 'tjs',
					width : '10%',
					formatter :tjLink
				},
				{
					label : 'δ�ύ�����',
					name : 'wtjs',
					index : 'wtjs',
					width : '10%',
					formatter :wtjLink
				},
				{
					label : 'ls',
					name : 'ls',
					index : 'ls',
					hidden : true
				},
				{
					label : 'xydm',
					name : 'xydm',
					index : 'xydm',
					hidden : true
				},
				{
					label : 'aqjc',
					name : 'aqjc',
					index : 'aqjc',
					hidden : true
				},
				{
					label : 'jljc',
					name : 'jljc',
					index : 'jljc',
					hidden : true
				},
				{
					label : 'js',
					name : 'js',
					index : 'js',
					hidden : true
				}],
				radioselect:true
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
		<html:form action="/gyjc_jcjglr">
			<%@ include file="/comm/hiddenValue.jsp"%>
		    <html:hidden property="today" styleId="today" value="${today}"/>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="jc();return false;"  >���</a>
						</li>
						</logic:equal>
						
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>����ճ��б�&nbsp;&nbsp;<font color="blue">����������飬��ȫ��飬���ɼ��</font> </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
