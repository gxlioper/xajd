var DCCLBH = "zxdk_ypzl_ypzldxjhk.do";//dcclbh,�������ܱ��

// �߼���ѯ
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

// ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewHkxx(\""
			+ rowObject["jgid"]+"\");'>" + cellValue
			+ "</a>";
}

// �鿴
function viewHkxx(jgid) {
	showDialog("�鿴", 800, 500, "ypzl_hk.do?method=viewHkxx&jgid="+jgid);
}

// ����
function add() {
	var url = "ypzl_hk.do?method=addhkxx";
	var title = "����";
	showDialog(title, 800, 508, url);
}

// �޸�
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else {
		var url = 'ypzl_hk.do?method=edithkxx&jgid=' + rows[0]["jgid"];
		var title = "�޸�";
		showDialog(title, 800, 508, url);
	}

}

// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("ypzl_hk.do?method=delHk", {
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

// ����
function saveHkjg(type){
	var xh = jQuery("#xh").val();
	var hkzt = jQuery("#hkzt").val();
	var hksj = jQuery("#hksj").val();
	if(type=='save'){
		if("" == xh || "" == hkzt || "" == hksj) {
			showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
			return false;		
		}
	}else{
		if("" == hkzt || "" == hksj) {
			showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
			return false;		
		}
	}	
	
	var url = "ypzl_hk.do?method=saveHkjg&type=" + type;
	ajaxSubFormWithFun("ypzldxjhkForm", url, function(data) {
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

// �Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, exportData);
}

// ��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "ypzl_hk.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

// ����
function dr(){
	toImportDataNew("IMPORT_YPZLDXJHK");
	return false;
}
