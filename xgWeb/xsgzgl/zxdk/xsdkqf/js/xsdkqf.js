//var DCCLBH = "zxdk_hkcx_xswhk.do";//dcclbh,�������ܱ��

// �߼���ѯ
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

// �Զ��嵼�� ����
/*function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, exportData);
}*/

// ��������
/*function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "zxdk_whkxscx.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}*/
