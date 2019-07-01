function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='tzzView(\""
			+ rowObject["zzid"]+"\");'>" + cellValue
			+ "</a>";
}

function tzzView(zzid) {
	showDialog("����֯�鿴", 600, 400, "tzzgl.do?method=viewTzz&zzid="
			+ zzid);
}

function saveTzz(type) {
	var flg = true;
	var ids = null;
	ids = "zzmc-zddw-zzdz-fzr"
	if(check(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	var url;
	if(type == 'save'){
		url = "tzzgl.do?method=saveTzzForAdd";
	}else{
		url = "tzzgl.do?method=saveTzzForUpdate";
	}
	ajaxSubFormWithFun("tzzForm", url, function(data) {
		if (data["message"] == "����ɹ���") {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			showAlert(data["message"]);
		}
	});
	
}

function add() {
	var url = "tzzgl.do?method=addTzz";
	var title = "����֯����";
	showDialog(title, 600, 400, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else{
		var url = 'tzzgl.do?method=updateTzz&zzid=' + rows[0]["zzid"];
		var title = "����֯�޸�";
		showDialog(title, 600, 400, url);
	}

}

// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("�Ƿ�ȷ��ɾ����ѡ�ļ�¼��", {
		"okFun" : function() {
		var url = "tzzgl.do?method=delTzz";
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
//����
function dr() {
	// ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
	toImportDataNew("IMPORT_TYGL_TZZGL");
	return false;
}

var DCCLBH = "tygl_tzzgl_tzzList.do";// dcclbh,�������ܱ��

// �Զ��嵼�� ����
function exportConfig() {
	// DCCLBH�������ܱ��,ִ�е�������
	customExport(DCCLBH, tzzExportData);
}

// ��������
function tzzExportData() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "tzzgl.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,�������ܱ��
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
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