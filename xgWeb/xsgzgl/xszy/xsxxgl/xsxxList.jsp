<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">

		jQuery(function(){
			var gridSetting = {
						caption:"������Ϣ�б�",
						pager:"pager",
						url:"xszyXsxxgl.do?method=getXsxxList&type=query",
						colList:[
							{label:'ѧ��',name:'xh', index: 'xh',width:'11%',formatter:xhLink},
							{label:'����',name:'xm', index: 'xm',width:'11%'},
							{label:'�Ա�',name:'xb', index: 'xb',width:'6%'},
							{label:'����',name:'dl', index: 'dl',width:'16%'},
							{label:'�༶',name:'bjmc', index: 'bjmc',width:'15%'},
							{label:'¥��',name:'ldmc', index: 'ldmc',width:'8%'},
							{label:'���Һ�',name:'qsh', index: 'qsh',width:'6%'},
							{label:'ѧ԰',name:'xymc', index: 'xymc',width:'11%'},
							{label:'����Ժϵ',name:'ssyxmc', index: 'ssyxmc',width:'15%'},
							{label:'����Ժϵ',name:'ssyxdm', index: 'ssyxdm',hidden:true}
						],
						sortname: "xh",
					 	sortorder: "asc"
			};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);			

			var btndr=jQuery("#btn_dr");
			//����
			if(btndr!=null){
				btndr.click(function () {
					//����ͨ�õĵ���function�������ǵ��빦��ģ����롣
					toImportDataNew("IMPORT_N950101_XSXXGL");
					return false;
				});
			}
		});

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		function update() {
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1) {
				showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			}else{
				var url = 'xszyXsxxgl.do?method=updateXszyXsxx&xh=' + rows[0]["xh"];
				var title = "�޸�������Ϣ";
				showDialog(title, 680, 458, url);
			}
		}

		function xszyXsxxView(xh) {
			showDialog("������Ϣ�鿴", 700, 450, "xszyXsxxgl.do?method=viewXszyXsxx&xh=" + xh);
		}

		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='xszyXsxxView(\"" + cellValue + "\");'>" + cellValue
					+ "</a>";
		}

		var DCCLBH = "xszy_xsxxgl.do";//dcclbh,�������ܱ��

		//�Զ��嵼�� ����
		function exportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			customExport(DCCLBH, xszyXsxxExportData);
		}

		// ��������
		function xszyXsxxExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "xszyXsxxgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
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
		<html:form action="/xszyXsxxgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:notEqual name="userType" value="stu">
					<logic:equal name="writeAble" value="yes">
						<!-- ��ť -->
						<div class="buttonbox">
							<ul>
								<li>
									<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
								</li>
								<li><a href="#" class="btn_dr" id="btn_dr">����</a></li>
								<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
							</ul>
						</div>
					</logic:equal>
				</logic:notEqual>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>������Ϣ�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
