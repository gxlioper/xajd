/**
 * ��ѯ
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * ������Ա
 * @param type
 * @return
 */
function fpjcr(type){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		return showAlertDivLayer("����ֻ��ѡ��һ����¼��");
	}
	document.location.href = "xg_gyjc_ryfp.do?xydm="+rows[0]['xydm']+"&jjlx="+type;
}

/**
 * ���ֱ�׼ά��
 * @return
 */
function pfbzwh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		return showAlertDivLayer("����ֻ��ѡ��һ����¼��");
	}
	document.location.href = "xg_gyjc_pfbz.do?xydm="+rows[0]['xydm']+"&jjlx="+1;

}

/**
 * ¥������formatter
 * @return
 */
function ldLink(cellValue, rowObject){
	return "����"+"<font style='color:#f59213'>"+cellValue+"</font>"+"��"+"<font style='color:#f59213'>"+rowObject['chnum']+"</font>"+"��"+"<font style='color:#f59213'>"+rowObject['qshnum']+"</font>"+"������";
}

/**
 * 
 * ���ֱ�׼formatter
 * @return
 */
function  pfLink(cellValue, rowObject){
	return "��"+"<font style='color:#f59213'>"+cellValue+"</font>"+"��";
}

