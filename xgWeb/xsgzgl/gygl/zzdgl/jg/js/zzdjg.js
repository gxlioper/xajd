function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='ZzdjgView(\""
			+ rowObject["jgid"] + "\");'>" + cellValue + "</a>";
}

function ZzdjgView(jgid) {
	showDialog("ѧ��ת�߶�����鿴", 800, 550, "xgygl_zdjg.do?method=viewZzdjg&jgid="
			+ jgid);
}

function saveZzdjg(type) {
	var flg = true;
	var ids = null;
	if(type=='save'||type=='submit'){
		ids = "xh-xn-xq-sqsj-sdyy"
	}else{
		ids = "xn-xq-sqsj-sdyy"
	}
	if(check(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	var url = "xgygl_zdjg.do?method=saveZzdjg&type="+type;
	ajaxSubFormWithFun("zzdjgForm", url, function(data) {
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
	var url = "xgygl_zdjg.do?method=addZzdjg";
	var title = "ѧ��ת�߶��������";
	showDialog(title, 800, 550, url);
}
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else if(rows[0]['sjly']=='1'){
			showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
	}else {
		var url = 'xgygl_zdjg.do?method=editZzdjg&jgid=' + rows[0]["jgid"];
		var title = "ѧ��ת�߶�����޸�";
		showDialog(title, 800, 550, url);
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
		var url = "xgygl_zdjg.do?method=delZzdjg";
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
	toImportDataNew("IMPORT_ZZDJG");
	return false;

}

var DCCLBH = "xgygl_zzdgl_zdjg.do";// dcclbh,�������ܱ��

// �Զ��嵼�� ����
function exportConfig() {
	// DCCLBH�������ܱ��,ִ�е�������
	customExport(DCCLBH, zzdjgExportData);
}

// ��������
function zzdjgExportData() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "xgygl_zdjg.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,�������ܱ��
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

function printzzdsqb(url){
	var zzdid="";
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <1) {
		showAlertDivLayer("������ѡ��һ����¼��");
	} else {
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				zzdid +=rows[i]["jgid"];
			}else{
				zzdid +=rows[i]["jgid"]+",";
			}
		}		
		var url = url + "&ylzd1=" +zzdid;
		window.open(url);
	}
}