function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add() {
	var url = "xlzxnew_xsxlpc.do?method=add";
	var title = "���������ղ���";
	showDialog(title,770,550,url);
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'xlzxnew_xsxlpc.do?method=updateJg&id=' + rows[0]["id"];
		var title = "�޸������ղ���";
		showDialog(title, 770, 550, url);
	}
}

//�����޸Ľ������
function saveForm(){
	var ids = "xh-pcjg";
	if(!checkNotNull(ids)){
		return showAlert("�뽫��<font class='red'>*</font>����Ŀ��д������");
	}
	var url = "xlzxnew_xsxlpc.do?method=saveJg";
	ajaxSubFormWithFun("XsxlpcForm", url, function(data) {
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

//�鿴ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='jgView(\""
			+ rowObject["id"] + "\");'>" + cellValue
			+ "</a>";
}

//�鿴ѧ��ajaxurl��ת
function jgView(id) {
	showDialog("�鿴�����ղ���", 770, 500, "xlzxnew_xsxlpc.do?method=ck&id="
			+ id);
}
//ɾ��ס�޽��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("xlzxnew_xsxlpc.do?method=delJg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
var DCCLBH = "xg_xlzxnew_xsxlpc.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH,jgExportData);
}

//��������
function jgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xlzxnew_xsxlpc.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * ��ע��ȡ����ע
 * @return
 */
function sz(zt){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫ��ע��ȡ����ע�ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("xlzxnew_xsxlpc.do?method=sz",{ids:ids.toString(),sfgz:zt},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * ����
 * @return
 */
function dr(){
	// ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
	toImportDataNew("IMPORT_XLZX_XSPCJG");
	return false;
}


