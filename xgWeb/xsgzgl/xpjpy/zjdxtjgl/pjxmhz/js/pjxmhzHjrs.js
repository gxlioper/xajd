jQuery(function(){
	gridSetting["params"]={"xn":jQuery("#xn").val(),"lxdm":jQuery("#lxdm").val(),"xzdm":jQuery("#xzdm").val(),"xmmc":jQuery("#xmmc").val()};
	jQuery("#dataTable").initGrid(gridSetting);
});

/*������ذ�ť�����ص���ҳ�沢ˢ��*/
function reBack(){
	refreshForm("/xgxt/xpjpy_tjgl_pjxmhz.do?method=getPjxmhzList");
}

/*����DCCLBH*/
var DCCLBH='xg_pjpy_tjgl_pjxmhzRs.do';

/*�������� ����*/
function exportConfig() {
	/*DCCLBH�������ܱ��,ִ�е����ĺ���*/
	customExport(DCCLBH, pjxmhzRsExportData);
}

/*��������*/
function pjxmhzRsExportData() {
	var xn = jQuery("#xn").val();
	var lxdm = jQuery("#lxdm").val();
	var xzdm = jQuery("#xzdm").val();
	var xmmc = jQuery("#xmmc").val();
	var url = "xpj_pjxmhz.do?method=pjxmhzRsExportData&dcclbh=" + DCCLBH + "&xn=" + xn + "&lxdm=" + lxdm + "&xzdm="+ xzdm + "&xmmc=" + xmmc;
	jQuery("form").eq(0).attr("action",url);
	jQuery("form").eq(0).submit();
}

/*ѧ�ų�����*/
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='hjrsView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}

/*���ѧ��������ת*/
function hjrsView(id,xh){
	var title = "���������ѯ";
	showDialog(title,650,480,"xpjpy_pjjg.do?method=viewPjjg&id="+id+"&xh="+xh);
}