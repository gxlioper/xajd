var action="fbglxsxx.do";
var title="�ְ����ѧ��";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
//��������ת
function dcmcLink(cellValue, rowObject) {
	var pk = rowObject["pk"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + pk
			+ "')\" class='name'>" + cellValue + "</a>";
}
//�鿴��Ϣ
function ckxx(id) {
	var query=jQuery("#query").val();
	var url = action+"?method=showView&pk=" + id;
	title += "��Ϣ";
	showDialog(title, 800, 500, url);
}
function drxx(){
	toImportDataNew("IMPORT_FBGL_XSXX");
	return false;
}
//ɾ��
function del() {
	var ids = jQuery("#dataTable").getRowCount();
	if(ids<=0){
		return showAlertDivLayer("������ѧ����Ϣ��");
	}
	var rows = jQuery("#dataTable").getSeletRow();
	for ( var int = 0; int < rows.length; int++) {
		if(null!=rows[int]["xh"]&&""!=rows[int]["xh"]){
			showAlertDivLayer("ѧ���ѱ�ѧ�ţ�������ɾ����");
			return false;
		}
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showConfirmDivLayer("��ȷ��Ҫɾ�����м�¼��", {
			"okFun" : function() {
				jQuery.post(action+"?method=del", {
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post(action+"?method=del", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}