<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gyjc/ccjg/js/ccjg.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "gyjc_ccjgcx.do?method=ccjgList&type=query",
				colList : [ {
					label : 'key',
					name : 'guid',
					index : 'guid',
					key : true,
					hidden : true
				}, {
					label : 'ѧԺ',
					name : 'xymc',
					index : 'xymc',
					width : '10%'
				}, {
					label : '¥��',
					name : 'ldmc',
					index : 'ldmc',
					width : '10%'
				}, {
					label : '����',
					name : 'qsh',
					index : 'qsh',
					width : '5%'
				}, {
					label : '���',
					name : 'jclxmc',
					index : 'jclxmc',
					width : '10%'
				}, {
					label : '�������ҵȼ�',
					name : 'qsdj',
					index : 'qsdj',
					width : '10%'
				},
				{
					label : '�����˵��</br>��ο�����׼��',
					name : 'bhgsm',
					index : 'bhgsm',
					width : '10%'
				},
				{
					label : '�����',
					name : 'tjrxm',
					index : 'tjrxm',
					width : '10%'
				},
				{
					label : '���ʱ��',
					name : 'jcrq',
					index : 'jcrq',
					width : '10%'
				}],
				radioselect:false
			}
			var map = getSuperSearch();
			map["flag"] = jQuery("#flag").val();
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
		<html:form action="/gyjc_ccjgcx" styleId="CcjgForm">
			<html:hidden property="flag" styleId="flag"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<logic:equal  name="flag" value="cc">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="addCcjg();return false;"  >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >ɾ��</a>
						</li>
						</logic:equal>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="viewCcjg();return false" class="btn_ck" >�鿴</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="exportConfig();return false" class="btn_dc" >����</a>
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
				<span>������б�&nbsp;&nbsp;</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
