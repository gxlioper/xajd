function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

var DCCLBH = "zjly_xsqj_jscx.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ExportDatas);
}

//��������
function ExportDatas() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xsqj_jscx.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//�鿴ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='qjjgView(\""
			+ rowObject["id"] + "\",\"" + cellValue + "\",\""
			+ rowObject["sjly"] + "\",\""
			+ rowObject["actinstid"] + "\",\""
			+ rowObject["qsl"] + "\");'>" + cellValue
			+ "</a>";
}

//�鿴ѧ��ajaxurl��ת
function qjjgView(id, xh,sjly,actinstid,gjxxid) {

	if(sjly=='gj'){
		showDialog('ѧ��������Ϣ',600,500,'xsgjgl.do?method=gjxxView&gjxxid='+gjxxid+"&xh="+xh,null);
	}else{
		showDialog("��ٲ鿴", 800, 500, "xsqj_jscx.do?method=Qjsqck&id="
				+ id + "&xh=" + xh+"&actinstid="+actinstid);
	}
}
//�Զ��嵼�� ����
function exportConfig_tjcx() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ExportDatas_tjcx);
}

//��������
function ExportDatas_tjcx() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xsqj_jscx.do?method=exportData&flag=tjcx&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}