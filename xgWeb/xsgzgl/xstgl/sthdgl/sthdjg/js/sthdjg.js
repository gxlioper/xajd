var checkId = 'xh-hdmc-fwdd-fwsj-fwsc-fwddssx-zbdw';

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='SthdjgView(\""
			+ rowObject["hdid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

// ����
function saveSthdjg(type) {
	if(!checkNotNull(checkId)){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	var url = "sthdglSthdjg.do?method=saveSthdjg&type=" + type;
	ajaxSubFormWithFun("SthdjgForm", url, function(data) {
		 if(data["message"]=="����ɹ���"){
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
		});

}
//����
function add() {
	var url = "sthdglSthdjg.do?method=addSthdjg";
	var title = jQuery("#gnmkmc").val()+"��д";
	showDialog(title, 800, 550, url);
}

//�޸�
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if(rows[0]['sjly']=='1'){
			showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
			return false;
		}
		var url = 'sthdglSthdjg.do?method=editSthdjg&hdid=' + rows[0]["hdid"]
				+ '&xh=' + rows[0]["xh"];
		var title = jQuery("#gnmkmc").val()+"�޸�";
		showDialog(title, 800, 550, url);
	}
}
//�鿴
function SthdjgView(id, xh) {
	showDialog(jQuery("#gnmkmc").val()+"�鿴", 800, 550, "sthdglSthdjg.do?method=viewSthdjg&hdid="
			+ id + "&xh=" + xh);
}
// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("sthdglSthdjg.do?method=delSthdjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
//����
function dr() {
	// ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
	toImportDataNew("IMPORT_N980104_XTHDJG");
	return false;

}
var DCCLBH = "stgl_sthdgl_sthdcx.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, sthdglSthdjgExportData);
}

//��������
function sthdglSthdjgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "sthdglSthdjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}