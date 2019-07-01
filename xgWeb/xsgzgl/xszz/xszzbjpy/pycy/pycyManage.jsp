<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">

			jQuery(function(){
				var gridSetting = {
						caption:"�༶����С���Ա�б�",
						pager:"pager",
						url:"xszz_xszzbjpy_pycygl.do?method=pycyManage&type=query",
						colList:[      
					         {label:'key',name:'guid', index: 'guid',hidden:true,key:true},
							   {label:'ѧ��',name:'xh', index: 'xh',width:'10%'},
							   {label:'����',name:'xm', index: 'xm',width:'8%'},
							   {label:'�꼶',name:'nj', index: 'nj',width:'7%'},
							   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'14%'},
							   {label:'רҵ',name:'zymc', index: 'zydm',width:'16%'},
							   {label:'�༶',name:'bjmc', index: 'bjdm',width:'20%'},
							   {label:'�Ƿ�ѧ������',name:'sfxsdbmc', index: 'sfxsdbmc',width:'10%'},
							   {label:'�Ƿ�ѧ������',name:'sfxsdb', index: 'sfxsdb',hidden:true},
							   {label:'�༶',name:'bjdm', index: 'bjdm',hidden:true}
							],
							sortname: "xh",
						 	sortorder: "asc"
					};
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});
	
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			var DCCLBH = "xszz_xszzbjpy_pycy.do";//dcclbh,�������ܱ��

			// �Զ��嵼�� ����
			function exportConfig() {
				//DCCLBH�������ܱ��,ִ�е������� 
				customExport(DCCLBH, exportData);
			}

			// ��������
			function exportData() {
				setSearchTj();//���ø߼���ѯ����
				var url = "xszz_xszzbjpy_pycygl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}

		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xszz_xszzbjpy_pycygl">
			<input type="hidden" id="xymc" value="<bean:message key="lable.xy" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>										
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;" >����</a></li>						
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�༶����С���Ա�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
