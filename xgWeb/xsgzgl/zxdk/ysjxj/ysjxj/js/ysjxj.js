var DCCLBH = "zxdk_ysjxj_ysjxjwh.do";//dcclbh,�������ܱ��

//�߼���ѯ
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

// ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewHkxx(\""
			+ rowObject["juid"]+"\");'>" + cellValue
			+ "</a>";
}

//�鿴
function viewHkxx(juid) {
	showDialog("�鿴", 800, 500, "ysjxj_ysjxjwh.do?method=viewYsjxj&juid="+juid);
}

// ����
function add() {
	var url = "ysjxj_ysjxjwh.do?method=addYsjxj";
	var title = "����";
	showDialog(title, 800, 508, url);
}

// �޸�
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else {
		var url = 'ysjxj_ysjxjwh.do?method=editYsjxj&juid=' + rows[0]["juid"];
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
				jQuery.post("ysjxj_ysjxjwh.do?method=delYsjxj", {
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
function saveYsjxj(type){
	var xh = jQuery("#xh").val();
	var xmmc = jQuery("#xmmc").val();
	var je = jQuery("#je").val();
	var ffsj = jQuery("#ffsj").val();
	var zjly = jQuery("#zjly").val();
	var xmlx = jQuery("#xmlx").val();
	if (!checkNull("xh-xmmc-je-ffsj-zjly-xmlx")) {
		return false;
	}
	if(type=='save'){
		if((xh==null || jQuery.trim(xh)== '') ||(xmmc==null || jQuery.trim(xmmc)== '') ||(je==null || jQuery.trim(je)== '')|| (ffsj==null || jQuery.trim(ffsj)== '') || (zjly==null || jQuery.trim(zjly)== '') || (xmlx==null || jQuery.trim(xmlx)== '')) {
			showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
			return false;		
		}
	}else{
		if((xh==null || jQuery.trim(xh)== '') ||(xmmc==null || jQuery.trim(xmmc)== '') ||(je==null || jQuery.trim(je)== '')|| (ffsj==null || jQuery.trim(ffsj)== '') || (zjly==null || jQuery.trim(zjly)== '')) {
			showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
			return false;		
		}
	}
	var url = "ysjxj_ysjxjwh.do?method=saveYsjxj&type=" + type;
	ajaxSubFormWithFun("ysjxjForm", url, function(data) {
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
	var url = "ysjxj_ysjxjwh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

// ����
function dr(){
	toImportDataNew("IMPORT_YSJXJJGWH");
	return false;
}