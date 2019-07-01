<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gypyx/gypyjg/js/gypyjg.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "gypynew_gypyjg.do?method=searchForJgList",
				colList : [ {
					label : 'key',
					name : 'jgid',
					index : 'jgid',
					key : true,
					hidden : true
				}, {
					label : '¥��',
					name : 'ldmc',
					index : 'ldmc',
					width : '10%'
					,formatter : qshLink
				}, {
					label : '���Һ�',
					name : 'qsh',
					index : 'qsh',
					width : '10%'
				}, {
					label : '¥��',
					name : 'ch',
					index : 'ch',
					width : '10%'
				}, {
					label : '�����Ǽ�',
					name : 'sqxj',
					index : 'sqxj',
					width : '10%'
				}, {
					label : '����ʱ��',
					name : 'gxsj',
					index : 'gxsj',
					width : '10%'
				}, {
					label : '�Ƿ�Ϊ�ٴι���',
					name : 'sfzcgx',
					index : 'sfzcgx',
					width : '10%'
				},{
					label : '����ʱ��',
					name : 'sqsj',
					index : 'sqsj',
					width : '10%'
				},{
					label : '�Ƿ���',
					name : 'cxztmc',
					index : 'cxztmc',
					width : '10%'},
				{
					label : 'sjly',
					name : 'sjly',
					index : 'sjly',
					hidden : true
				},
				{
					label : 'cxzt',
					name : 'cxzt',
					index : 'cxzt',
					hidden : true
				}]
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
		<html:form action="/gypynew_gypysq">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >ɾ��</a>
						</li>
						<li><a href="#" class="btn_sr" onclick="cancelcx();return false;">����</a></li>		
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
				<span>�Ǽ����������б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
