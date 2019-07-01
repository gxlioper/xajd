<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/sybx/js/sybxList.js"></script>
			<script type="text/javascript">
			var gridSetting = {
					caption:"��ҵ���ս���б�",
					pager:"pager",
					url:"rcsw_sybx.do?method=getSybxList&type=query",
					colList:[
				       {label:'guid',name:'guid', index: 'guid',width:'5%',key:true,hidden:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'15%',formatter:xhLink},
					   {label:'����',name:'xm', index: 'xm',width:'13%'},
					   {label:'�Ա�',name:'xb', index: 'xb',width:'8%'},
					   {label:'�꼶',name:'nj', index: 'nj',width:'7%',hidden:true},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'10%',hidden:true},
					   {label:'רҵ',name:'zymc', index: 'zydm',width:'10%',hidden:true},
					   {label:'�༶',name:'bjmc', index: 'bjdm',width:'20%'},
					   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
					   {label:'�໤��',name:'jhrxm', index: 'jhrxm',width:'20%'},
					   {label:'���ս��',name:'bxje', index: 'bxje',width:'10%'}
					 
					],
					sortname: "xn,nj,xydm,zydm,bjdm,xh",
				 	sortorder: "desc"
				}
				jQuery(function(){
					jQuery("#dataTable").initGrid(gridSetting);

					var btndr=jQuery("#btn_dr");
					//����
					if(btndr!=null){
						btndr.click(function () {
							//����ͨ�õĵ���function�������ǵ��빦��ģ����롣
							toImportData("IMPORT_N154801_SYBX");
							return false;
						});
					}
				});
			
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_sybx">
			<input type="hidden" name="tableName" id="tableName" value="view_xg_rcsw_sybxxxb"/>
			<input type="hidden" name="realTable" id="realTable" value="xg_rcsw_sybxxxb"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" onclick="addSybx();return false;" class="btn_zj">����</a></li>
						<li><a href="javascript:void(0);" onclick="updateSybx();return false;" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" onclick="delSybx();return false;" class="btn_sc">ɾ��</a></li>
						<li><a href="#" class="btn_dr" id="btn_dr">����</a></li>
						</logic:equal>					
						<li><a href="#" class="btn_dc" onclick="syylbxwhExportConfig();return false;">����</a></li>
						<%--<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">��������</a></li>
					--%></ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ��ҵ���ս���б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>