function addTqhk(){
	showDialog("����",800,520,"zxdkHkjg.do?method=addTqhk");
}

function editTqhk(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		showDialog("�޸�",800,520,"zxdkHkjg.do?method=editTqhk&id="+rows[0]["id"]);
	}
}

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function delTqhk(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ����������",{"okFun":function(){
			jQuery.post("zxdkHkjg.do?method=delTqhk",{ids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}