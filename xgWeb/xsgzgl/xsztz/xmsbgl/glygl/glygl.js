function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add() {
	var url = "xmsbGlygl.do?method=glyZj";
	var title = "添加管理员";
	showDialog(title, 750, 500, url);
}

//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("xmsbGlygl.do?method=delGly",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

