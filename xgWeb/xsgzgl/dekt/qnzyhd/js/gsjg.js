//�������
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='zyhdView(\""
			+ rowObject["id"]+"\");'>" + cellValue
			+ "</a>";
}

function zyhdView(id) {
	showDialog("־Ը������鿴", 800, 500, "zyhdry.do?method=viewZyhd&id="+id);
}


var DCCLBH = "dekt_qnzyhd_gsjg.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, gsjgExportData);
}

//��������
function gsjgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "zyhdry.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function pf(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ���۵ļ�¼��");
		return false;
	}
	var url = 'zyhdry.do?method=pf&id=' + rows[0]["id"];
	var title = "����";
	showDialog(title, 400, 200, url);
}