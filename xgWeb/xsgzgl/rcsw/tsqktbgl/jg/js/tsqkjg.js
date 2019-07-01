function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='TsqkjgView(\""
			+ rowObject["jgid"] + "\");'>" + cellValue + "</a>";
}

function TsqkjgView(jgid) {
	showDialog("�������ͨ������鿴", 800, 508, "tsqktbgl_jg.do?method=viewTsqkjg&jgid="
			+ jgid);
}

function saveTsqkjg(type) {
	var flg = true;
	var ids = null;
	if(type=='save'||type=='submit'){
		ids = 'xh-xqdm1-tbsj-tsxq-tsxqgyqk-clcj';
	}else {
		ids = 'xqdm1-tbsj-tsxq-tsxqgyqk-clcj';
	}
	if(jQuery("#xxdm").val() == "10026") 
		ids += "-wtjjcd";
	if(check(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	var xqdm1 = jQuery("#xqdm1").val();
	var xqdm2 = jQuery("#xqdm2").val();
	if(xqdm1 == xqdm2){
		showAlert("ѧ�����һ��ѧ�������ظ���������ѡ��");
		return false;
	}
	var url = "tsqktbgl_jg.do?method=saveTsqkjg&type="+type;
	ajaxSubFormWithFun("tsqkjgForm", url, function(data) {
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
	var url = "tsqktbgl_jg.do?method=addTsqkjg";
	var title = "�������ͨ���������";
	showDialog(title, 800, 508, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else if(rows[0]['sjly']=='1'){
			showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
	}else {
		var url = 'tsqktbgl_jg.do?method=editTsqkjg&jgid=' + rows[0]["jgid"];
		var title = "�������ͨ������޸�";
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
		var url = "tsqktbgl_jg.do?method=delTsqkjg";
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


var DCCLBH = "rcsw_tsqktbgl_jg.do";// dcclbh,�������ܱ��

// �Զ��嵼�� ����
function exportConfig() {
	// DCCLBH�������ܱ��,ִ�е�������
	customExport(DCCLBH, tsqkjgExportData);
}

// ��������
function tsqkjgExportData() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "tsqktbgl_jg.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,�������ܱ��
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