var DCCLBH = "zxdk_hkcx_byshk.do";//dcclbh,�������ܱ��

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
	var url = "zxdk_byshkcx.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function gbhkzt(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ<br/>�ı仹��״̬�ĵļ�¼��");
	}else if(rows[0]['dkxm']=='У԰�ش���'){
		var url = 'xyddk_hk.do?method=xyddkHkwhAdd&xh=' + rows[0]["xh"];
		var title = "У԰�ش����";
		showDialog(title, 800, 508, url);
	}else if(rows[0]['dkxm']=='��Դ�ش���'){
		var url = 'syddk_hk.do?method=addHkwh&xh=' + rows[0]["xh"];
		var title = "��Դ�ش����";
		showDialog(title, 800, 508, url);
	}else if(rows[0]['dkxm']=='У����Ϣ���'){
		var url = 'zxdk_jkhk.do?method=addJkhk&xh=' + rows[0]["xh"];
		var title = "У����Ϣ����";
		showDialog(title, 800, 508, url);
	}else if(rows[0]['dkxm']=='��ƽ��������'){
		var url = 'ypzl_hk.do?method=addhkxx&xh=' + rows[0]["xh"];
		var title = "��ƽ���������";
		showDialog(title, 800, 508, url);
	}
}