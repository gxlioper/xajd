/**
 * @author 喻鑫源 
 * @功能:住宿结果的js
 * @develop-date:2015-05-19
 * @lastupdate-date:2015-05-22
 */


function searchRs() {
	if(jQuery("[name='tj_nd'].selectedValue").length != 1){
		return showAlertDivLayer("请选择单独学年进行查询！");
	}
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

var DCCLBH = "xg_gygl_zjcm_tjcx.do";

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	if(jQuery("[name='tj_nd'].selectedValue").length != 1){
		return showAlertDivLayer("请选择单独学年进行导出！");
	}
	customExport(DCCLBH, tjjgExportData);
}

//导出方法
function tjjgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "gyglnew_fslr_ajax.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

