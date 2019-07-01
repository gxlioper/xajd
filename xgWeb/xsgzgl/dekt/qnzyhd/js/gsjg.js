//搜索结果
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='zyhdView(\""
			+ rowObject["id"]+"\");'>" + cellValue
			+ "</a>";
}

function zyhdView(id) {
	showDialog("志愿活动分数查看", 800, 500, "zyhdry.do?method=viewZyhd&id="+id);
}


var DCCLBH = "dekt_qnzyhd_gsjg.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, gsjgExportData);
}

//导出方法
function gsjgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "zyhdry.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function pf(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要评价的记录！");
		return false;
	}
	var url = 'zyhdry.do?method=pf&id=' + rows[0]["id"];
	var title = "评价";
	showDialog(title, 400, 200, url);
}