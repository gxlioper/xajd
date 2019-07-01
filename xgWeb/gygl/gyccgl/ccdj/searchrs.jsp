<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="gygl/gyccgl/ccdj/js/ccdj.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "gyccgl_ccdj.do?method=searchRs&type=query",
				colList : [ {
					label : 'key',
					name : 'id',
					index : 'id',
					key : true,
					hidden : true
				}, {
					label : 'ѧ��',
					name : 'xn',
					index : 'xn',
					width : '10%'
				}, {
					label : 'ѧ��',
					name : 'xqmc',
					index : 'xqmc',
					width : '10%'
				}, {
					label : '¥��',
					name : 'ldmc',
					index : 'ldmc',
					width : '5%'
				}, {
					label : '���',
					name : 'ch',
					index : 'ch',
					width : '10%'
				}, {
					label : '���Һ�',
					name : 'qsh',
					index : 'qsh',
					width : '10%'
				},{
					label : '����',
					name : 'cnt',
					index : 'cnt',
					width : '10%'
				},{
					label : '�����ܼ�(Ԫ)',
					name : 'zje',
					index : 'zje',
					width : '10%'
				}, {
					label : 'xq',
					name : 'xq',
					index : 'xq',
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
		<html:form action="/gyccgl_ccdj">
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
						</logic:equal>
						<li>
							<a href="javascript:void(0);" class="btn_ck" onclick="view();return false;"  >�鿴</a>
						</li>
						<li><a href="#" class="btn_dr" onclick="dr();return false;">����</a></li>	
						<li><a href="#" class="btn_dc" onclick="jgExportData();return false;">����</a></li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�Ʋ��Ǽ��б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
