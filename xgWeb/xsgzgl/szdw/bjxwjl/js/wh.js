
/**
 * �߼���ѯ
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * ����
 * @return
 */
function addSq(){
	showDialog('�����༶��Ϊ��¼',700,550,'szdw_bjxwjlwh.do?method=sq');
}

/**
 * �鿴
 * @return
 */
function ckSq(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
		return false;
	}else{
		showDialog('�鿴�༶��Ϊ��¼',700,350,'szdw_bjxwjlwh.do?method=ck&guid=' + jQuery("#dataTable").getSeletIds()[0]);
	}
}


/**
 * ɾ����¼
 * @return
 */
function deleteSq(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0){
		showAlertDivLayer("��ѡ��һ����Ҫɾ���ļ�¼��");
		return false;
	} else{
		showConfirmDivLayer("��ȷ��Ҫɾ����ѡ��¼��",{"okFun":function(){
			jQuery.post("szdw_bjxwjlwh.do?method=deleteAction",{guids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * �޸�
 * @return
 */
function updateSq(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} else{
		showDialog('�༶��Ϊ��¼�޸�',700,450,'szdw_bjxwjlwh.do?method=updateSq&guid=' + rows[0]['guid']);
	}
}

var DCCLBH = "szdw_bjxwjlwh.do";//dcclbh,�������ܱ��


//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, exportData);
}

// ��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "szdw_bjxwjlwh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}