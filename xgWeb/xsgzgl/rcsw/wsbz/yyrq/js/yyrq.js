function add(){
	var url = "wsbz_yyrq.do?method=add";
	var title = "����";
	showDialog(title,380,250,url);
}
		
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
			var url = 'wsbz_yyrq.do?method=update&id='+rows[0]["id"];
			var title = "�޸�";
			showDialog(title,400,250,url);
	}
}

function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	}else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
		jQuery.post("wsbz_yyrq.do?method=del",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
			},'json');
		}});
}
}


