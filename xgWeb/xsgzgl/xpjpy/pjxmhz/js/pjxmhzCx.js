
jQuery(function(){
	gridSetting["params"]={"xn":jQuery("#xn").val(),"lxdm":jQuery("#lxdm").val(),"xzdm":jQuery("#xzdm").val(),"xmmc":jQuery("#xmmc").val()};
	jQuery("#dataTable").initGrid(gridSetting);
});

//������ذ�ť�����ص���ҳ�沢ˢ��
function reBack(){
	refreshForm("/xgxt/xg_pjpy_tjgl_pjxmhz.do?method=viewPjxmhz");
}

//����DCCLBH
var DCCLBH='xg_pjpy_tjgl_pjxmhzRs.do';

//�������� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е����ĺ���
	customExport(DCCLBH, pjxmhzRsExportData);
}

// ��������
function pjxmhzRsExportData() {
	var xn = jQuery("#xn").val();
	var lxdm = jQuery("#lxdm").val();
	var xzdm = jQuery("#xzdm").val();
	var xmmc = jQuery("#xmmc").val();
	var url = "xpj_pjxmhz.do?method=pjxmhzRsExportData&dcclbh=" + DCCLBH + "&xn=" + xn + "&lxdm=" + lxdm + "&xzdm="+ xzdm + "&xmmc=" + xmmc;//dcclbh,�������ܱ��
	jQuery("form").eq(0).attr("action",url);
	jQuery("form").eq(0).submit();
}



function pjjgView(id,xh){
	var title = "���������ѯ";
	showDialog(title,650,480,"xpj_pjjg.do?method=pjxmjgView&id="+id+"&xh="+xh);
}

function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='pjjgView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}