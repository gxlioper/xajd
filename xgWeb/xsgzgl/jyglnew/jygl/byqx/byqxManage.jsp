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
						caption:"��ҵȥ����Ϣ�б�",
						pager:"pager",
						url:"jyglnew_jygl_byqxgl.do?method=byqxManage&type=query",
						colList:[
							{label:'key',name:'xh', index: 'xh',key:true ,hidden:true},
						 	{label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:xhLink},
						   {label:'����',name:'xm', index: 'xm',width:'10%'},
						   {label:'�Ա�',name:'xb', index: 'xb',width:'6%'},
						   {label:'רҵ',name:'zymc', index: 'zymc',width:'14%'},
						   {label:'��ҵ��λ',name:'jydw', index: 'jydw',width:'17%'},
						   {label:'��ҵ��λ����',name:'jydwxzmc', index: 'jydwxzmc',width:'10%'},
						   {label:'��ǲ��λ',name:'pqdw', index: 'pqdw',width:'17%'},
						   {label:'��ҵ��ʽ',name:'jyxsmc', index: 'jyxsmc',width:'12%'},
						   {label:'��ҵ��λ����dm',name:'jydwxz', index: 'jydwxz',hidden:true},
						   {label:'��ҵ��ʽdm',name:'jyxs', index: 'jyxs',hidden:true}
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
			function xhView(xh) {
				showDialog("�鿴��ҵȥ����Ϣ", 750,350, "jyglnew_jygl_byqxgl.do?method=viewByqx&xh=" + xh);
			}
			function xhLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='xhView(\"" + rowObject["xh"] + "\");'>" + cellValue + "</a>";
			}
			function add(){
				var url = "jyglnew_jygl_byqxgl.do?method=addByqx";
				var title = "���ӱ�ҵȥ����Ϣ";
				showDialog(title,750,500,url);
			}
			function update() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer(jQuery("#lable_one_xg").val());
				} else {
					var url = 'jyglnew_jygl_byqxgl.do?method=updateByqx&xh=' + rows[0]["xh"];
					var title = "�޸ı�ҵȥ����Ϣ";
					showDialog(title,750,500,url);
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
						jQuery.post("jyglnew_jygl_byqxgl.do?method=delByqx", { values : ids.toString() },
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
				var exportBh = "jyglnew_jygl_byqx.do";
				//DCCLBH�������ܱ��,ִ�е������� 
				customExport(exportBh, exportData);
			}
			// ��������
			function exportData() {
				setSearchTj();//���ø߼���ѯ����
				var exportBh = "jyglnew_jygl_byqx.do";
				var url = "jyglnew_jygl_byqxgl.do?method=exportData&dcclbh="+exportBh;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			// �°浼��
			function drxxNew(){
				toImportDataNew("IMPORT_N790102_NEW");
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
		<html:form action="/jyglnew_jygl_byqxgl">
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
				<span>��ҵȥ����Ϣ�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
