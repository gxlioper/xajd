var DCCLBH = "cjWsf_wsftj.do";//dcclbh,�������ܱ��

//��������
function wsftjExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "cjWsftj.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, wsftjExportData);
}



