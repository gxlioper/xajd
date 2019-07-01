function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='tzbView(\""
			+ rowObject["jgid"]+"\");'>" + cellValue
			+ "</a>";
}

function tzbView(jgid) {
	showDialog("����֯�鿴", 600, 400, "tgbgl.do?method=viewTgb&jgid="+ jgid);
}

function selectTzz(type){
	var xh = jQuery("#xh").val();
	var gotopath = jQuery("#path").val()+'&xh='+xh;
	if(type=='update'){
		gotopath+='&jgid='+jQuery("#jgid").val();
	}
	if(!!xh == false){
		showAlert("������дѧ��");
		return false;
	}
	showDialog('��ѡ��һ������֯',800,500,'tgbgl.do?method=showTzz&gotopath='+gotopath);
}

function saveTgb(type){
	var flg = true;
	var ids = null;
	ids = "xh-zwdm-rzzz-rzsj";
	if(check(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	var url;
	if(type == 'save'){
		url = "tgbgl.do?method=saveTgbForAdd";
	}else{
		url = "tgbgl.do?method=saveTgbForUpdate";
	}
	ajaxSubFormWithFun("tgbForm", url, function(data) {
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
	var url = "tgbgl.do?method=addTgbJg";
	var title = "�Ÿɲ�����";
	showDialog(title, 800, 600, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else{
		var url = 'tgbgl.do?method=updateTgb&jgid=' + rows[0]["jgid"];
		var title = "�Ÿɲ��޸�";
		showDialog(title, 800, 600, url);
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
		var url = "tgbgl.do?method=delTgb";
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
	toImportDataNew("IMPORT_TYGL_TGBGL");
	return false;
}

var DCCLBH = "tygl_tgbgl_tgbjgList.do";// dcclbh,�������ܱ��

// �Զ��嵼�� ����
function exportConfig() {
	// DCCLBH�������ܱ��,ִ�е�������
	customExport(DCCLBH, tzbExportData);
}

// ��������
function tzbExportData() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "tgbgl.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,�������ܱ��
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