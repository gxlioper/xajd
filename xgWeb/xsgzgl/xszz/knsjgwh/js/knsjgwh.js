/* ���� */
function importXf(){
	toImportDataNew("IMPORT_N720208_KNSDCWH");
	return false;
}
var DCCLBH = "xszz_knsdcjgwh.do";// dcclbh,�������ܱ��
function exportConfig() {
	customExport(DCCLBH, xfExportData);
}
//��������
function xfExportData() {
	setSearchTj();
	var url = "xszz_knsdcwh.do?method=exportData&dcclbh=" + DCCLBH;
	url = addSuperSearchParams(url);
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}




//�����鿴
function bjLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onClick='view(\""+rowObject["id"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}
function view(id,cellValue){
	showDialog('ѧ��������Ϣ�鿴',700,430,'xszz_knsdcwh.do?method=view&id='+id+ "&xh=" + cellValue,null);
}


// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("xszz_knsdcwh.do?method=del", {
					values : ids.toString()
				},
						function(data) {
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
			}
		});
	}
}
