jQuery(function(){
	var map = getSuperSearch();
	gridSetting["params"] = map;
	jQuery("#dataTable").initGrid(gridSetting);
});

//高级查询
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//定义DCCLBH
var DCCLBH='xg_pjpy_tjgl_pjxmhz.do';

//自定义导出 功能
function exportConfig() {
	// DCCLBH导出功能编号,执行导出函数
	customExport(DCCLBH, pjxmhzExportData, 1000, 500);
}

// 导出方法
function pjxmhzExportData() {
	setSearchTj();// 设置高级查询条件
	var url = "xpj_pjxmhz.do?method=pjxmhzExportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url+="&rowConut="+jQuery("#rowConut").html();
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//超链方法
function rsLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='rsView(\""+rowObject["xn"]+"\",\""+rowObject["lxdm"]+"\",\""+rowObject["xzdm"]+"\",\""+rowObject["xmmc"]+"\");'>"+rowObject["hjrs"]+"</a>";
}

//点击超链接查看
function rsView(xn,lxdm,xzdm,xmmc){
	var url = 'xpj_pjxmhz.do?method=viewRs&xn='+xn+'&lxdm='+lxdm+'&xzdm='+xzdm+'&xmmc='+xmmc;
	document.forms[0].action=url;
	document.forms[0].submit();
}