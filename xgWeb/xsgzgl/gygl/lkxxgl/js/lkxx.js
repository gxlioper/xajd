
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='lkxxView(\""
			+ rowObject["id"]+"\");'>" + cellValue
			+ "</a>";
}

function lkxxView(id) {
	showDialog("�ٿ�ס����Ϣ�鿴", 800, 450, "xgygl_lkxxdj.do?method=viewLkxx&id="+id);
}


function save(type) {
	var ids = null;
	if(type=='save'){
		ids = "xm-sfzh-rzld-rzfj-rzsj"
	}else{
		ids = "id-xm-sfzh-rzld-rzfj-rzsj"
	}
	if(check(ids) == false && flg == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	var url = null;
	if(type=='save'){
		url = "xgygl_lkxxdj.do?method=addLkxx&type=" + type
	}else{
		url = "xgygl_lkxxdj.do?method=editLkxx&type=" + type;
	}	
	ajaxSubFormWithFun("lkxxForm", url, function(data) {
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

function add() {
	var url = "xgygl_lkxxdj.do?method=addLkxx";
	var title = "�Ǽ�";
	showDialog(title, 800, 450, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else {
		var url = 'xgygl_lkxxdj.do?method=editLkxx&id=' + rows[0]["id"];
		var title = "�Ǽ��޸�";
		showDialog(title, 800, 450, url);
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
				jQuery.post("xgygl_lkxxdj.do?method=delLkxx", {
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

var DCCLBH = "xgygl_lkxxgl_zsdj.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, lkxxExportData);
}

//��������
function lkxxExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xgygl_lkxxdj.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * ��֤�Ƿ���ڿ���
 * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

function dr(){
	toImportDataNew("IMPORT_LKXX");
	return false;
}