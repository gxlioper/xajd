// ============= ά��ѧ�� beign =============
// ȫѡ
function selectAllXs(obj) {
	jQuery('input[type=checkbox]').attr('checked', jQuery(obj).attr('checked'));
}
// ����
function addXs() {
	var xhArr = new Array();
	jQuery.each(jQuery("#tbody_xs tr"),function(i,n){
		var xh= jQuery(n).find("input[type=hidden][name=xhArr]").eq(0).val();
		xhArr.push(xh);
	});
	var url = 'jyglnew_jygl_cyyqglgl.do?method=getStu&xhs='+xhArr.join(",");
	showDialog('��ѡ��ѧ��', 800, 550, url);
}
// ɾ��
function delXs() {
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

