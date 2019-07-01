var DCCLBH = "zxdk_hkcx_byshk.do";//dcclbh,导出功能编号

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
	var url = "zxdk_byshkcx.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function gbhkzt(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要<br/>改变还款状态的的记录！");
	}else if(rows[0]['dkxm']=='校园地贷款'){
		var url = 'xyddk_hk.do?method=xyddkHkwhAdd&xh=' + rows[0]["xh"];
		var title = "校园地贷款还款";
		showDialog(title, 800, 508, url);
	}else if(rows[0]['dkxm']=='生源地贷款'){
		var url = 'syddk_hk.do?method=addHkwh&xh=' + rows[0]["xh"];
		var title = "生源地贷款还款";
		showDialog(title, 800, 508, url);
	}else if(rows[0]['dkxm']=='校内无息借款'){
		var url = 'zxdk_jkhk.do?method=addJkhk&xh=' + rows[0]["xh"];
		var title = "校内无息借款还款";
		showDialog(title, 800, 508, url);
	}else if(rows[0]['dkxm']=='永平自立贷款'){
		var url = 'ypzl_hk.do?method=addhkxx&xh=' + rows[0]["xh"];
		var title = "永平自立贷款还款";
		showDialog(title, 800, 508, url);
	}
}