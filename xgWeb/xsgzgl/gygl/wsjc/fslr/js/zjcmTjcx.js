/**
 * @author ����Դ 
 * @����:ס�޽����js
 * @develop-date:2015-05-19
 * @lastupdate-date:2015-05-22
 */


function searchRs() {
	if(jQuery("[name='tj_nd'].selectedValue").length != 1){
		return showAlertDivLayer("��ѡ�񵥶�ѧ����в�ѯ��");
	}
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

var DCCLBH = "xg_gygl_zjcm_tjcx.do";

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	if(jQuery("[name='tj_nd'].selectedValue").length != 1){
		return showAlertDivLayer("��ѡ�񵥶�ѧ����е�����");
	}
	customExport(DCCLBH, tjjgExportData);
}

//��������
function tjjgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "gyglnew_fslr_ajax.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

