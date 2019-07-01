<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>		
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src='xsgzgl/xlzx/zxzxthjl/js/zxzxthjlList.js'></script>
		<script type="text/javascript">
			var gridSetting = {
					caption:"̸����¼�б�",
					pager:"pager",
					url:"xlzx_thjl_zxzxthjlgl.do?method=zxzxthjlList&type=query",
					colList:[
					   {label:'id',name:'id', index: 'id',hidden:true,key:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
					   {label:'����',name:'xm', index: 'xm',width:'10%'},
					   {label:'ѧԺ',name:'xymc', index: 'xymc',width:'10%'},
					   {label:'�༶',name:'bjmc', index: 'bjmc',width:'10%'},
					   {label:'Լ̸��',name:'zgxm', index: 'zgxm',width:'10%'},
					   {label:'̸��ʱ��',name:'thsj', index: 'thsj',width:'10%'}
					],
					sortname: "xh",
			 		sortorder: "asc"
				}
				function xhLink(cellValue,rowObject){
					return "<a href='javascript:void(0);' class='name' onClick='viewZxzxthjl(\""+rowObject["id"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
				}
				jQuery(function(){
					jQuery("#dataTable").initGrid(gridSetting);
				});
				
				function searchRs(){
					var map = getSuperSearch();
					jQuery("#dataTable").reloadGrid(map);
				}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title}</a>
			</p>
		</div>
		<html:form action="/demo">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" onclick="addZxzxthjl();return false;" class="btn_zj">����</a></li>
						<li><a href="javascript:void(0);" onclick="updateZxzxthjl();return false;" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" onclick="deleteZxzxthjl();return false;" class="btn_sc">ɾ��</a></li>
						</logic:equal>
						<li><a href="#" onclick="exportData();return false;" class="btn_dc">����</a></li>
						<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">��ӡ����Լ̸��¼��</a></li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> �����ղ�̸����¼�б� </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
