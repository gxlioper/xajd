jQuery(function(){
	var map = getSuperSearch();
	gridSetting["params"] = map;
	jQuery("#dataTable").initGrid(gridSetting);
});

/**�߼���ѯ*/
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
/**����DCCLBH*/
var DCCLBH='xpjpy_tjgl_pjxmhz.do';

/**����*/
function exportConfig(){
	customExport(DCCLBH, exportData);
}
/**��������*/
function exportData(){
	setSearchTj();//���ø߼���ѯ����
	var url = "xpjpy_pjxmhz.do?method=exportData&dcclbh=" + DCCLBH;
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**������*/
function rsLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='rsView(\""+rowObject["xn"]+"\",\""+rowObject["lxdm"]+"\",\""+rowObject["xzdm"]+"\",\""+rowObject["xmmc"]+"\");'>"+rowObject["hjrs"]+"</a>";
}

/**��������Ӳ鿴*/
function rsView(xn,lxdm,xzdm,xmmc){
	var url = 'xpjpy_pjxmhz.do?method=pjxmhzHjrsView&xn='+xn+'&lxdm='+lxdm+'&xzdm='+xzdm+'&xmmc='+xmmc;
	document.forms[0].action=url;
	document.forms[0].submit();
}