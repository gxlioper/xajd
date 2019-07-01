<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/jskp/lxjg/js/lxjg.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "jskp_lxjg.do?method=searchForJgCx&type=query",
				colList : [ {
					label : 'key',
					name : 'xmid',
					index : 'xmid',
					key : true,
					hidden : true
				}, {
					label : '��Ŀ����',
					name : 'xmmc',
					index : 'xmmc',
					width : '10%'
					,formatter : xmmcLink
				}, {
					label : '��Ŀ����',
					name : 'xmdlmc',
					index : 'xmdlmc',
					width : '10%'
				}, {
					label : '��Ŀ���',
					name : 'xmlbmc',
					index : 'xmlbmc',
					width : '10%'
				}, {
					label : 'ָ������',
					name : 'bmmc',
					index : 'bmmc',
					width : '10%'
				}, {
					label : '������',
					name : 'fzrxm',
					index : 'fzrxm',
					width : '10%'
				},{
					label : '��Ա����',
					name : 'rysz',
					index : 'rysz',
					width : '10%'
					,formatter :ryszLink
				},{
					label : '��������',
					name : 'jbsz',
					index : 'jbsz',
					width : '10%'
					,formatter :jbszLink
				},{
					name : 'sjly',
					index : 'sjly',
					hidden : true
				},{
					name : 'xmdl',
					index : 'xmdl',
					hidden : true
				}
				]
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
		<html:form action="/jskp_lxsq">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >ɾ��</a>
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
				<span>��Ŀ����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
