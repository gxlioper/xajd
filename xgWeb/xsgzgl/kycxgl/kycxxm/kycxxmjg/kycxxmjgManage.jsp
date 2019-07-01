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
						caption:"������Ŀ�б�",
						pager:"pager",
						url:"kycxgl_kycxxm_kycxxmjggl.do?method=kycxxmjgManage&type=query",
						colList:[
							{label:'key',name:'jgid', index: 'jgid',key:true ,hidden:true},
						   {label:'������Դ',name:'sjly', index: 'sjly',hidden:true},
						   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
						   {label:'������Ŀ����',name:'xmmc', index: 'xmmc',width:'30%',formatter:xmmcLink},
						   {label:'�������',name:'lbmc', index: 'lbmc',width:'10%'},
						   {label:'����ʱ��',name:'xmsqsj', index: 'xmsqsj',width:'10%'},
						   {label:'��Ŀ������',name:'xmsqrxm', index: 'xmsqrxm',width:'10%'},
						   {label:'ָ����ʦ',name:'zdlsxm', index: 'zdlsxm',width:'10%'},
						   {label:'����������',name:'lbdm', index: 'lbdm',hidden:true}
						],
						sortname: "xmsqsj",
					 	sortorder: "desc"
				};
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);			
			});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function kycxxmjgView(jgid) {
				showDialog("�鿴������Ŀ", 750,415, "kycxgl_kycxxm_kycxxmjggl.do?method=viewKycxxmjg&jgid=" + jgid);
			}
			function xmmcLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='kycxxmjgView(\"" + rowObject["jgid"] + "\");'>" + cellValue + "</a>";
			}
			function add(){
				var url = "kycxgl_kycxxm_kycxxmjggl.do?method=addKycxxmjg";
				var title = "���ӿ�����Ŀ";
				showDialog(title,750,445,url);
			}
			function update() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer(jQuery("#lable_one_xg").val());
				} else {
					var sjly = rows[0]["sjly"];
					/*if (sjly=='1'){
						showAlertDivLayer("���������ݲ������޸ģ�");
						return false;
					}else{*/
						var url = 'kycxgl_kycxxm_kycxxmjggl.do?method=updateKycxxmjg&jgid=' + rows[0]["jgid"];
						var title = "�޸Ŀ�����Ŀ";
						if (sjly=='1'){
							showDialog(title,750,478,url);
						}else{
							showDialog(title,750,445,url);
						}
					//}
				}
			}
			function del() {
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length == 0) {
					showAlertDivLayer(jQuery("#lable_some_sc").val());
				} else {
					for (var i = 0; i < ids.length; i++) {
						if (rows[i]["sjly"] == "1") {
							showAlertDivLayer("��������ݲ���ɾ����");
							return false;
						}
					}
					showConfirmDivLayer(jQuery("#lable_confirm_sc").val(), {
						"okFun" : function() {
						jQuery.post("kycxgl_kycxxm_kycxxmjggl.do?method=delKycxxmjg", { values : ids.toString() },
								function(data) {
									var mes = "�ɹ�ɾ����<font color='green'>&nbsp;" + data["num"] + "&nbsp;</font>������";
									showAlertDivLayer(mes);
									jQuery("#dataTable").reloadGrid();
								}, 'json');
						}
					});
				}
			}
			// �°浼��
			function drxxNew(){
				toImportDataNew("IMPORT_N970104_NEW");
				return false;
			}
			var DCCLBH = "kycxgl_kycxxm_kycxxmjg.do";//dcclbh,�������ܱ��
			//�Զ��嵼�� ����
			function exportConfig() {
				//DCCLBH�������ܱ��,ִ�е������� 
				customExport(DCCLBH, kycxxmjgExportData);
			}
			// ��������
			function kycxxmjgExportData() {
				setSearchTj();//���ø߼���ѯ����
				var url = "kycxgl_kycxxm_kycxxmjggl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
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
		<html:form action="/kycxgl_kycxxm_kycxxmjggl">
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
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>������Ŀ�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
