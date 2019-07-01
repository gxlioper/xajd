function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='ByhkgljgView(\""
			+ rowObject["jgid"] + "\");'>" + cellValue + "</a>";
}

function ByhkgljgView(jgid) {
	showDialog("����鿴", 800, 508, "byhkgl_jg.do?method=viewByhkgljg&jgid="
			+ jgid);
}

function saveByhkgljg(type) {
	var flg = true;
	var ids = null;
	var sfzq = jQuery("#sfzq").val();
	
	if("��" == sfzq){
		ids = 'hkje-sfzq-zqyy-zqqx';
	}else{
		ids = "hkje-sfzq";
	}
	if(check(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	var url = "byhkgl_jg.do?method=saveByhkgljg&type="+type;
	ajaxSubFormWithFun("byhkglJgForm", url, function(data) {
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
	var url = "byhkgl_jg.do?method=addByhkgljg";
	var title = "�������";
	showDialog(title, 800, 508, url);
}
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else if(rows[0]['sjly']=='1'){
			showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
	}else {
		var url = 'byhkgl_jg.do?method=editByhkgljg&jgid=' + rows[0]["jgid"];
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
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("�Ƿ�ȷ��ɾ����ѡ�ļ�¼��", {
		"okFun" : function() {
		var url = "byhkgl_jg.do?method=delByhkgljg";
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
	toImportDataNew("IMPORT_BYHKJG");
	return false;

}

var DCCLBH = "zxdk_byhkgl_byhkjg.do";// dcclbh,�������ܱ��

// �Զ��嵼�� ����
function exportConfig() {
	// DCCLBH�������ܱ��,ִ�е�������
	customExport(DCCLBH, exportData);
}

// ��������
function exportData() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "byhkgl_jg.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,�������ܱ��
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

//���У�鲻����0��ͷ
function ismoney(obj){
  if(obj.value.indexOf('0') == 0){
		 showAlert("������0��ͷ��",{},{"clkFun":function(){
				jQuery(obj).val("");
				jQuery(obj).focus();
			}});
  }
}

//�·�У�鲻����0��ͷ
function isyf(obj){
  if(obj.value.indexOf('0') == 0){
		 showAlert("�·ݲ�����0��ͷ��",{},{"clkFun":function(){
				jQuery(obj).val("");
				jQuery(obj).focus();
			}});
  }
}