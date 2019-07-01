jQuery(function(){
	var map = getSuperSearch();
	gridSetting["params"] = map;
	jQuery("#dataTable").initGrid(gridSetting);
});

//�߼���ѯ
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����DCCLBH
var DCCLBH='xg_pjpy_tjgl_pjxmhz.do';

//�Զ��嵼�� ����
function exportConfig() {
	// DCCLBH�������ܱ��,ִ�е�������
	customExport(DCCLBH, pjxmhzExportData, 1000, 500);
}

// ��������
function pjxmhzExportData() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "xpj_pjxmhz.do?method=pjxmhzExportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url+="&rowConut="+jQuery("#rowConut").html();
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//��������
function rsLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='rsView(\""+rowObject["xn"]+"\",\""+rowObject["lxdm"]+"\",\""+rowObject["xzdm"]+"\",\""+rowObject["xmmc"]+"\");'>"+rowObject["hjrs"]+"</a>";
}

//��������Ӳ鿴
function rsView(xn,lxdm,xzdm,xmmc){
	var url = 'xpj_pjxmhz.do?method=viewRs&xn='+xn+'&lxdm='+lxdm+'&xzdm='+xzdm+'&xmmc='+xmmc;
	document.forms[0].action=url;
	document.forms[0].submit();
}