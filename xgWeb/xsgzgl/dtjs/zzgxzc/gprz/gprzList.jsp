<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"������־�б�",
				pager:"pager",
				url:"dtjs_gprz.do?method=gprzList&type=query",
				colList:[
				   {label:'�޸�id',name:'id', index: 'id',key:true,hidden:true},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xgsjLink},
				   {label:'����',name:'xm', index: 'xm',width:'10%'},
				   {label:'�޸���',name:'xgr', index: 'xgr',width:'10%'},
				   {label:'�޸�ʱ��',name:'xgsj', index: 'xgsj',width:'10%'},
				   {label:'�޸�ǰ��Ϣ',name:'xgqjl', index: 'xgqjl',width:'30%'},
				   {label:'�޸ĺ���Ϣ',name:'xghjl', index: 'xghjl',width:'30%'}
				],
				sortname: "xgsj",
			 	sortorder: "desc"
			}

			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function xgsjLink(cellValue,rowObject){
				var id = rowObject["id"];
				var xh = rowObject["xh"];
				return "<a href='javascript:void(0);' onclick=\"gprzShow('"+id+"','"+xh+"')\" class='name'>"+cellValue+"</a>";
			}
			
			//����
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			/**
			 * ����
			 */
			function gprzShow(id,xh){
				var url = "dtjs_gprz.do?method=gprzShow&id="+id+"&xh="+xh;
				var title = "������־��Ϣ";
				showDialog(title, 800, 500, url);
			}
			
			
			/**
			 * ����
			 */
			var DCGLBH = "dtjs_gprz.do";//dcglbh,�������ܱ��

			//�Զ��嵼�� ����
			function exportConfig() {
				//DCCLBH�������ܱ��,ִ�е������� 
				customExport(DCGLBH, xshdglExportData);
			}

			//��������
			function xshdglExportData() {
				setSearchTj();//���ø߼���ѯ����
				var url = "dtjs_gprz.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,�������ܱ��
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
		<html:form action="/xszz_zzkff">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>						
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
				<span> ������־�б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
