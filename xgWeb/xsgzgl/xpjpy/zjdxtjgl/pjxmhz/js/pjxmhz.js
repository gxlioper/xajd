jQuery(function(){
	var map = getSuperSearch();
	gridSetting["params"] = map;
	jQuery("#dataTable").initGrid(gridSetting);
});

/**高级查询*/
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
/**定义DCCLBH*/
var DCCLBH='xpjpy_tjgl_pjxmhz.do';

/**导出*/
function exportConfig(){
	customExport(DCCLBH, exportData);
}
/**导出方法*/
function exportData(){
	setSearchTj();//设置高级查询条件
	var url = "xpjpy_pjxmhz.do?method=exportData&dcclbh=" + DCCLBH;
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**超链接*/
function rsLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='rsView(\""+rowObject["xn"]+"\",\""+rowObject["lxdm"]+"\",\""+rowObject["xzdm"]+"\",\""+rowObject["xmmc"]+"\");'>"+rowObject["hjrs"]+"</a>";
}

/**点击超链接查看*/
function rsView(xn,lxdm,xzdm,xmmc){
	var url = 'xpjpy_pjxmhz.do?method=pjxmhzHjrsView&xn='+xn+'&lxdm='+lxdm+'&xzdm='+xzdm+'&xmmc='+xmmc;
	document.forms[0].action=url;
	document.forms[0].submit();
}