/* ���� */
function dr(){
	toImportDataNew("IMPORT_N441201_HMDGL");
	return false;
}
var DCCLBH = "qgzx_hmdgl.do";// dcclbh,�������ܱ��
function exportConfig() {
	customExport(DCCLBH, xfExportData);
}
//��������
function xfExportData() {
	setSearchTj();
	var url = "qgzxhmdgl.do?method=exportData&dcclbh=" + DCCLBH;
	url = addSuperSearchParams(url);
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
//����
function addHmd() {
	var url = "qgzxhmdgl.do?method=addHmd";
	var title = "����������";
	showDialog(title, 500, 300, url);
}

//���ӱ���
function saveHmd() {

	if(jQuery("#dwid").val() == ""){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	var url = "qgzxhmdgl.do?method=addHmd&type=save";
	ajaxSubFormWithFun("hmdglForm", url, function(data) {
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


//�޸�
function xgHmd() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else {
		var url = 'qgzxhmdgl.do?method=xgHmd&id=' + rows[0]["id"]+'&xh=' + rows[0]["xh"];
		showDialog( "�������޸�", 700, 500, url);
	}

}

//�޸ı���
function saveXgHmd() {
	var checkId ="xh-gwmc-yrdw-sgsj-lgsj";
	if(!check(checkId)){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	var url = "qgzxhmdgl.do?method=xgHmd&type=update";
	ajaxSubFormWithFun("hmdglForm", url, function(data) {
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

//�����鿴
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onClick='ckHmd(\""+rowObject["id"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}
function ckHmd(id,xh){
	showDialog('�������鿴',700,430,'qgzxhmdgl.do?method=ckHmd&id='+id+ "&xh=" + xh,null);
}


function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var cs=jQuery("#"+id[i]).val();
		if(cs==null||""==cs){
			return false;
		}
	}
	return true;
}

// ɾ��
function delHmd() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("qgzxhmdgl.do?method=delHmd", {
					values : ids.toString()
				},
						function(data) {
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
			}
		});
	}
}
