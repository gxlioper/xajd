//dcclbh,�������ܱ��
var DCCLBH = "zxdk_jcjy_bcdc.do";

//�߼���ѯ
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
	}

//����
function add(){
	var url = "jcjy_bcdc.do?method=jcjyAdd";
	var title = "����";
	showDialog(title,800,500,url);
}

//�޸�
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else {
		var url = 'jcjy_bcdc.do?method=jcjyUpdate&juid=' + rows[0]["juid"];
		var title = "�޸�";
		showDialog(title,800,500,url);
	}

}

//����
function saveJcjy(type){
	var xh = jQuery("#xh").val();
	var dclb = jQuery("#dclb").val();
	if(type=='save'){
		if( (xh==null || jQuery.trim(xh)=='')){
			showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
			return false;
		}
	}else{
		if( (xh==null || jQuery.trim(xh)=='')){
				showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
				return false;
			}
	}
	var url = "jcjy_bcdc.do?method=saveJcjy&type=" + type;
	ajaxSubFormWithFun("jcjyModel", url, function(data) {
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

//ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("jcjy_bcdc.do?method=delJcjy", {
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

//�鿴
function jcjyView(juid){
	showDialog('�鿴',800,500,'jcjy_bcdc.do?method=jcjyView&juid='+juid);
}

//�Զ��嵼��
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, exportData);
}

// ��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "jcjy_bcdc.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//����
function importJcjy(){
	toImportDataNew("IMPORT_ZXDKJCJY");
	return false;
}