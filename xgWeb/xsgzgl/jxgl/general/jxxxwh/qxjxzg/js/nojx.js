var DCCLBH = "jxgl_jxxxwh_qxjxzg.do";//dcclbh,�������ܱ��

// �߼���ѯ
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

// �Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, exportData);
}

// ��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "qxjxzg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='jxxxView(\""
			+ rowObject["jxid"] + '"'+","+'"' + rowObject["xh"] +"\");'>" + cellValue + "</a>";
}

function jxxxView(jxid,xh) {
	showDialog("����鿴", 800, 508, "qxjxzg.do?method=viewJxxx&jxid="
			+ jxid + "&xh=" + xh);
}