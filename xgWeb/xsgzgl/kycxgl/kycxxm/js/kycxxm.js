// ѡ���ʦ�󣬻ص�����
function showFdysNotF5CallBack(rowData) {
	jQuery("#zdls").val(rowData["zgh"]);
	jQuery("#zdlsxm").val(rowData["xm"]);
}
// ============= ά��ѧ�� beign =============
// ȫѡ
function selectAllKycxxmcy(obj) {
	jQuery('input[type=checkbox]').attr('checked', jQuery(obj).attr('checked'));
}
// ����
function addKycxxmcy() {
	var xhArr = new Array();
	jQuery.each(jQuery("#tbody_kycxxm_xs tr"),function(i,n){
		var xh= jQuery(n).find("input[type=hidden][name=xhArr]").eq(0).val();
		xhArr.push(xh);
	});
	var url = 'kycxgl_kycxxm_kycxxmjggl.do?method=getStu&xhs='+xhArr.join(",");
	showDialog('��ѡ��ѧ��', 800, 550, url);
}
// ɾ��
function delKycxxmcy() {
	var checkbox = jQuery('input[type=checkbox]:checked[name!=selectAll]');
	if (checkbox.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ����ѧ����");
		return false;
	}
	for ( var i = 0; i < checkbox.length; i++) {
		jQuery(checkbox[i]).parents("tr:eq(0)").remove();
	}
	jQuery('input[type=checkbox][name=selectAll]').attr('checked', false);
}
//============= ά��ѧ�� beign =============

