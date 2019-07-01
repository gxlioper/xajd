
/**
 * 高级查询
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


/**
 * 查看
 * @return
 */
function ck(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要查看的记录！");
		return false;
	}else{
		showDialog('查看班级行为记录',700,500,'szdw_bjxwjltj.do?method=ck&bjdm=' + jQuery("#dataTable").getSeletIds()[0]);
	}
}
