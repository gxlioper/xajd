var DCCLBH = "jxgl_jxxxwh_qxjxzg.do";//dcclbh,导出功能编号

// 高级查询
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

// 自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, exportData);
}

// 导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "qxjxzg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='jxxxView(\""
			+ rowObject["jxid"] + '"'+","+'"' + rowObject["xh"] +"\");'>" + cellValue + "</a>";
}

function jxxxView(jxid,xh) {
	showDialog("结果查看", 800, 508, "qxjxzg.do?method=viewJxxx&jxid="
			+ jxid + "&xh=" + xh);
}