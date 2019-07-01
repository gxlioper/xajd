var DCCLBH = "cjWsf_wsftj.do";//dcclbh,导出功能编号

//导出方法
function wsftjExportData() {
	setSearchTj();//设置高级查询条件
	var url = "cjWsftj.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, wsftjExportData);
}



