<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"��У����ά����¼�б�",
				pager:"pager",
				url:"jqlx_lxmdwhjl.do?method=getLxmdwhjlData",
				colList:[
				   {label:'��¼id',name:'jlid', index: 'jlid',key:true,hidden:true},
				   {label:'��У��Ŀ',name:'xmmc', index: 'xmmc',width:'28%'},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'13%'},
				   {label:'����',name:'xm', index: 'xm',width:'13%'},
				   {label:'������',name:'czr', index: 'czr',width:'13%'},
				   {label:'����ʱ��',name:'czsj', index: 'czsj',width:'20%'},
				   {label:'��������',name:'czlxmc', index: 'czlx',width:'13%'}
				],
				sortname: "czsj",
			 	sortorder: "desc"
			}

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			//����
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			var DCGLBH = "jqlx_lxmdwhjl.do";//dcglbh,�������ܱ��

			//�Զ��嵼�� ����
			function exportConfig() {
				//DCCLBH�������ܱ��,ִ�е������� 
				customExport(DCGLBH, xshdglExportData);
			}

			//��������
			function xshdglExportData() {
				setSearchTj();//���ø߼���ѯ����
				var url = "jqlx_lxmdwhjl.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			
			//�鿴
			function lxmdwhjlShow(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
				} else {
					var jlid=rows[0]["jlid"];
					var url = "jqlx_lxmdwhjl.do?method=lxmdwhjlShow&jlid="+jlid;
					var title = "��У����ά����¼��Ϣ";
					showDialog(title, 800, 400, url);
				}
			}

		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/jqlx_lxmdwhjl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" class="btn_ck" onclick="lxmdwhjlShow();">�鿴</a></li>
						<li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();">����</a></li>						
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
				<span> ��У����ά����¼�б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
