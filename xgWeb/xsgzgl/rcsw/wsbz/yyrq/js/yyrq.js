function add(){
	var url = "wsbz_yyrq.do?method=add";
	var title = "增加";
	showDialog(title,380,250,url);
}
		
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
			var url = 'wsbz_yyrq.do?method=update&id='+rows[0]["id"];
			var title = "修改";
			showDialog(title,400,250,url);
	}
}

function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	}else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
		jQuery.post("wsbz_yyrq.do?method=del",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
			},'json');
		}});
}
}


