
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='cjtsxsView(\""
			+ rowObject["id"]+"\");'>" + cellValue
			+ "</a>";
}

function cjtsxsView(id) {
	showDialog("������������ѧ���鿴", 800, 550, "xljk_cjtsxs.do?method=viewCjTsxs&id="+id);
}

function save(type){
	var ids;
	if(type == 'save'){
		ids = "xh-wtms";
	}else{		
		ids = "wtms";
	}
	if(check(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	var url = "xljk_cjtsxs.do?method=saveJg&type=" + type;
	ajaxSubFormWithFun("cjtsxsForm", url, function(data) {
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
	var url = "xljk_cjtsxs.do?method=addCjTsxs";
	var title = "������������ѧ������";
	showDialog(title, 800, 550, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else {
		var url = 'xljk_cjtsxs.do?method=editCjTsxs&id=' + rows[0]["id"];
		var title = "������������ѧ���޸�";
		showDialog(title, 800, 550, url);
	}

}

// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	}else{
		showConfirmDivLayer("�Ƿ�ȷ��ɾ����ѡ�ļ�¼��", {"okFun" : function() {
		var url = "xljk_cjtsxs.do?method=delCjtsxs";
		jQuery.post(url, {
			values : ids.toString()
		}, function(data) {
			if (data["success"] == false) {
				showAlertDivLayer(data["message"]);
			} else {
				showAlertDivLayer(data["message"], {}, {
					"clkFun" : function(tag) {
						jQuery("#dataTable").reloadGrid();
					}
				});
			}
		}, 'json');	
	}});
  }
}

var DCCLBH = "xlzx_cjtsxs_cjtsxsgl.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, cjtsxsExportData);
}


//��������
function cjtsxsExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xljk_cjtsxs.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
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

function dr() {
	// ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
	toImportDataNew("IMPORT_CJTSXS");
	return false;

}
