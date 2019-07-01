<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/hdkhgl/js/hdkhgl.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
					caption : "��Ŀ�б�",
					pager : "pager",
					url : "txhd_hdkhgl.do?method=getHdkhList&type=query",
					colList : [ {
						label : 'ѧ��',
						name : 'xn',
						index : 'xn',
						width : '12%'
					}, {
						label : '��Ŀ����',
						name : 'xmmc',
						index : 'xmmc',
						width : '23%',
						formatter:show
					}, {
						label : '�ʱ��',
						name : 'hdsj',
						index : 'hdsj',
						width : '25%'
					}, {
						label : '��ص�',
						name : 'hddd',
						index : 'hddd',
						width : '19%'
					}, {
						label : '����',
						name : 'lbmc',
						index : 'lbmc',
						width : '10%'
					}, {
						label : '����',
						name : 'hdgg',
						index : 'hdgg',
						width : '8%'
					}, {
						label : '����',
						name : 'xmdm',
						index : 'xmdm',
						hidden: true
					}],
					sortname : "xmmc",
					sortorder : "asc"
				};
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
		<html:form action="/txhd_hdkhgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_sh" onclick="cjpd();return false;"  >��ɼ�����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="importConfig();;return false;" class="btn_dr" >����</a>
						</li>
						</logic:equal>
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
				<span>���Ŀ��ϸ&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
