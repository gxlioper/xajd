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
						caption:"��ҵ��ѵ��Ϣ�б�",
						pager:"pager",
						url:"jyglnew_jygl_cypxgl.do?method=cypxManage&type=query",
						colList:[
							{label:'key',name:'id', index: 'id',key:true ,hidden:true},
						 	{label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:xhLink},
						   {label:'����',name:'xm', index: 'xm',width:'10%'},
						   {label:'�Ա�',name:'xb', index: 'xb',width:'6%'},
						   {label:'רҵ',name:'zymc', index: 'zymc',width:'14%'},
						   {label:'��ѵ����',name:'pxlxmc', index: 'pxlxmc',width:'17%'},
						   {label:'�Ƿ�ȡ��֤��',name:'sfqdzsmc', index: 'sfqdzsmc',width:'10%'},
						   {label:'��ѵʱ��',name:'pxsj', index: 'pxsj',width:'12%'},
						   {label:'��ҵ��ʽ',name:'jyxsmc', index: 'jyxsmc',width:'17%'},
						   {label:'��ѵ����dm',name:'pxlx', index: 'pxlx',hidden:true},
						   {label:'�Ƿ�ȡ��֤��dm',name:'sfqdzs', index: 'sfqdzs',hidden:true}
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
			function xhView(id, xh) {
				showDialog("�鿴��ҵ��ѵ��Ϣ", 750,395, "jyglnew_jygl_cypxgl.do?method=viewCypx&id=" + id + "&xh=" + xh);
			}
			function xhLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='xhView(\"" + rowObject["id"] + "\", \"" + rowObject["xh"] + "\");'>" + cellValue + "</a>";
			}
			function add(){
				var url = "jyglnew_jygl_cypxgl.do?method=addCypx";
				var title = "���Ӵ�ҵ��ѵ��Ϣ";
				showDialog(title,750,395,url);
			}
			function update() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer(jQuery("#lable_one_xg").val());
				} else {
					var url = 'jyglnew_jygl_cypxgl.do?method=updateCypx&xh=' + rows[0]["xh"] + '&id=' + rows[0]["id"];
					var title = "�޸Ĵ�ҵ��ѵ��Ϣ";
					showDialog(title,750,415,url);
				}
			}
			function del() {
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length == 0) {
					showAlertDivLayer(jQuery("#lable_some_sc").val());
				} else {
					showConfirmDivLayer(jQuery("#lable_confirm_sc").val(), {
						"okFun" : function() {
						jQuery.post("jyglnew_jygl_cypxgl.do?method=delCypx", { values : ids.toString() },
								function(data) {
									showAlertDivLayer(data["message"]);
									jQuery("#dataTable").reloadGrid();
								}, 'json');
						}
					});
				}
			}
			//�Զ��嵼�� ����
			function exportConfig() {
				var exportBh = "jyglnew_jygl_cypx.do";
				//DCCLBH�������ܱ��,ִ�е������� 
				customExport(exportBh, exportData);
			}
			// ��������
			function exportData() {
				setSearchTj();//���ø߼���ѯ����
				var exportBh = "jyglnew_jygl_cypx.do";
				var url = "jyglnew_jygl_cypxgl.do?method=exportData&dcclbh="+exportBh;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			// �°浼��
			function drxxNew(){
				toImportDataNew("IMPORT_N790105_NEW");
				return false;
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/jyglnew_jygl_cypxgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">����</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">ɾ��</a>
							</li>
							<li><a href="javascript:void(0);" onclick="drxxNew();return false;" id="btn_dr" class="btn_dr">����</a></li>
						</logic:equal>
						<li>
							<a href="#" class="btn_dc" id="btn_dc" onclick="exportConfig();return false;">����</a>
						</li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��ҵ��ѵ��Ϣ�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
