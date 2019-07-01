<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xlzx/pxwh/js/xljkpxwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "��������ѵά��",
				pager : "pager",
				url : "xlzx_pxwh.do?method=pxwhList&type=query",
				colList : [
					{label:'key',name:'pxid',index :'pxid',key:true,hidden:true},
					{label:'��ѵ����',name:'pxzt',index:'pxzt',width:'15%',formatter:pxztLink},
					{label:'��ѵ�ص�',name:'pxdd',index:'pxdd',width:'15%'},
					{label:'��ѵʱ��',name:'pxsj',index:'pxsj',width:'10%'},
					{label:'��ʦ',name:'js',index:'js',width:'10%'},
					{label:'��������',name:'bmkg',index:'bmkg',width:'5%',hidden:true},
					{label:'��������',name:'kgzt',index:'bmkg',width:'5%'},
					{label:'��������ʱ��',name:'bmkfsj',index:'bmsj',width:'20%',noSort: true},
					{label:'�ѱ�������',name:'ybmrs',index :'ybmrs',hidden:true},
					{label:'�ѱ���/��������',name:'rs',index:'rs',width:'5%',formatter:ybmxsLink}
				],
				sortname: "pxsj",
			 	sortorder: "desc",
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
			<div id="div_help" class="prompt" >
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<span>
						�������أ�������ѵ����ѵ����Ϊ�������ҵ�ǰʱ�䴦�ڱ�������ʱ��֮�ڣ���������Ϊ������������</br>
						��ѵ���أ�ָ����ѵ�Ƿ񱻿�����
					</span>
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" onclick="add()" class="btn_zj">����</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >ɾ��</a></li>
						<!-- <li><a href="javascript:void(0);" onclick="import();return false" class="btn_dr" >����</a></li> -->
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">�ѱ���ѧ������</a></li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��������ѵά��&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
