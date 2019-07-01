function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

var DCCLBH = "pj_zcfcx.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ExportDatas);
}

//导出方法
function ExportDatas() {
	setSearchTj();//设置高级查询条件
	var url = "zcfcx.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='zcfView(\""
			+ rowObject["xn"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//查看学生ajaxurl跳转
function zcfView(xn, xh) {
	showDialog("综测分信息查看", 770, 500, "zcfcx.do?method=Zcfck&xn="
			+ xn + "&xh=" + xh);
}
