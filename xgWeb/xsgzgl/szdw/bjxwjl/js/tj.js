
/**
 * �߼���ѯ
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


/**
 * �鿴
 * @return
 */
function ck(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
		return false;
	}else{
		showDialog('�鿴�༶��Ϊ��¼',700,500,'szdw_bjxwjltj.do?method=ck&bjdm=' + jQuery("#dataTable").getSeletIds()[0]);
	}
}
