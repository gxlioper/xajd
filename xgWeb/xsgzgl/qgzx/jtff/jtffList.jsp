<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/jtff/js/jtff.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "��������",
				pager : "pager",
				url : "qgzx_jtff.do?method=getJtffList",
				colList : [
					{label:'id',name:'id',index :'id',key:true,hidden:true},
					{label:'��������',name:'sj',index:'sj',width:'10%'},
					{label:'ѧ��',name:'xh',index:'xh',width:'13%'},
					{label:'����',name:'xm',index:'xm',width:'10%'},
					{label:'�꼶',name:'nj',index:'nj',width:'7%'},
					{label:'ѧԺ',name:'xymc',index:'xymc',width:'15%'},
					{label:'רҵ',name:'zymc',index:'zymc',width:'15%'},
					{label:'�༶',name:'bjmc',index:'bjmc',width:'15%'},
					{label:'�ù�����',name:'yrbm',index:'yrbm',width:'15%'},
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
		<html:form action="/xlzx_pxwh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						 <li><a href="javascript:void(0);" onclick="add()" class="btn_zj">����</a></li>
						<li><a href="javascript:void(0);" onclick="edit();" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >ɾ��</a></li>
						<li><a href="javascript:void(0);" onclick="importConfig();return false" class="btn_dr" >����</a></li>
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
				<span>��������</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
