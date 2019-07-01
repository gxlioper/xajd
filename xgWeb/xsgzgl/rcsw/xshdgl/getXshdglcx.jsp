<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xshdgl/js/xshdgl.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "������б�",
				pager : "pager",
				url : "xshdgl_xshdgl.do?method=getXshdglcx&type=query",
				colList : [ {
					label : 'key',
					name : 'sqid',
					index : 'sqid',
					key : true,
					hidden : true
				}, {
					label : '�����',
					name : 'hdmc',
					index : 'hdmc',
					width : '10%',
					formatter : hdmcLink
				}, {
					label : '�ʱ��',
					name : 'hdsj',
					index : 'hdsj',
					width : '6%'
				}, {
					label : '��ص�',
					name : 'hddd',
					index : 'hddd',
					width : '5%'
				}, {
					label : '�����',
					name : 'hdlxmc',
					index : 'hdlxmc',
					width : '5%'
				}, {
					label : '���쵥λ',
					name : 'zbdwdm',
					index : 'zbdwdm',
					width : '15%'
				}, {
					label : 'Э�쵥λ',
					name : 'xbdwdm',
					index : 'xbdwdm',
					width : '15%'
				},{
					label : '�а쵥λ',
					name : 'cbdwdm',
					index : 'cbdwdm',
					width : '15%'
				},{
					label : '�������',
					name : 'hdfzr',
					index : 'hdfzr',
					width : '10%'
				}],
				sortname : "hdsj",
				sortorder : "desc"
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
		<html:form action="/xshdgl_xshdgl">
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
						<li><a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">����</a></li>
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
				<span>������б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
