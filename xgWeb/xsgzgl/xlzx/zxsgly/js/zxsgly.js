/**
 * ��ѯ
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
function bcgygly(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length == 0){
		showAlert("����ѡ����Ա��");
		return false;
	}
	var zghs = new Array();
	for ( var i = 0; i < rows.length; i++) {
		zghs.push(rows[i]['zgh']);
	}
	jQuery.post("xlzx_zxsgly.do?method=saveAddGly",{zghs:zghs},function(data){
		 showAlert(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
	},'json');
}

/**
 * ɾ��
 * @return
 */
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("xlzx_zxsgly.do?method=delgly",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * ����
 * @return
 */
function add(){
	showDialog("�����ѯʦ����Ա", 770, 552, "xlzx_zxsgly.do?method=addZxsgly");
}



