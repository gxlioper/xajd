function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

var DCCLBH = "xg_qgzx_jtff.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ExportDatas);
}

//��������
function ExportDatas() {
	setSearchTj();//���ø߼���ѯ����
	var url = "qgzx_jtff.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


//ɾ��
function del() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	 }
	var flag = false;
	var ids = jQuery("#dataTable").getSeletIds();
	showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
		jQuery.post("qgzx_jtff.do?method=jtffDel",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	
}

//����ҳ����ת
function add(){
	showDialog('������������',600,450,'qgzx_jtff.do?method=jtffAdd');
}


//�༭ҳ����ת
function edit(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		showDialog('���������޸�',600,450,'qgzx_jtff.do?method=jtffEdit&id=' + rows[0]["id"]);
	}
}

//����
function save(url,checkId) {
	if (!checkNull(checkId)) {
		return false;
	}
	lock();
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data["success"] == "false"){
				showAlert(data["message"]);
			} else {
				showAlert(data["message"], {}, {	"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}});
			}
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
	});
	unlock();
}

//����
function importConfig(){
	toImportDataNew("IMPORT_JTFF");
	return false;
}

function splcEdit(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'dekt_xmwh.do?method=splcEdit&xmid='+rows[0]["xmid"]+'&splc='+rows[0]["splc"];
		var title = "���������޸�";
		showDialog(title,400,200,url);
	}
}

