<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/dekt/dektxmwh/js/xmwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "��Ŀά��",
				pager : "pager",
				url : "dekt_xmwh.do?method=getXmwhList",
				colList : [
					{label:'xmid',name:'xmid',index :'xmid',key:true,hidden:true},
					/* {label:'����ѧԺ',name:'xymc',index:'xymc',width:'10%'},
					{label:'��Ŀ����',name:'xmdl',index:'xmdl',width:'10%'}, */
					{label:'����',name:'lx',index:'lx',width:'14%'},
					{label:'�϶���Ŀ',name:'rdxm',index:'rdxm',width:'12%'},
					{label:'�϶����ݱ�׼',name:'rdnrbz',index:'rdnrbz',width:'23%'},
					{label:'�ȼ�',name:'dj',index:'dj',width:'9%'},
					{label:'ѧ��',name:'xf',index:'xf',width:'6%'},
					{label:'��������',name:'lcxx',index:'lcxx',width:'34%'},
					{label:'splc',name:'splc',index:'splc',hidden:true}
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
		<html:form action="/dekt_xmwh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="splcEdit();" class="btn_xg">�޸���������</a></li>
						<%-- <logic:equal name="writeAble" value="yes">
						 <li><a href="javascript:void(0);" onclick="add()" class="btn_zj">����</a></li>
						<li><a href="javascript:void(0);" onclick="edit();" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >ɾ��</a></li>
						<li><a href="javascript:void(0);" onclick="importConfig();return false" class="btn_dr" >����</a></li>
						</logic:equal> 
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	--%>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end--> 
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��Ŀά��</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
