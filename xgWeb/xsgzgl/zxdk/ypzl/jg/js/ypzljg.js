var gnmkmc = "";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='YpzljgView(\""
			+ rowObject["jgid"] + "\");'>" + cellValue + "</a>";
}

function YpzljgView(jgid) {
	 gnmkmc = jQuery("#gnmkmc").val();
	showDialog(gnmkmc+"�鿴", 800, 508, "ypzl_jg.do?method=viewYpzljg&jgid="
			+ jgid);
}

function setColor(value, status) {
	var color;
	if (status == '1') {
		color = "#004400";
	} else {
		color = "red";
	}
	return value = "<font color='" + color + "'>" + value + "</font>";
}

function saveYpzljg(type) {
	var flg = true;
	var ids = null;
	var xxdm = jQuery("#xxdm").val();
	if(type=='save'||type=='submit'){
		if(xxdm == '10511'){
			ids = 'xh-sqje-sqly-ytlb';
		}else{
			ids = "xh-sqje-sqly";
		}
	}else{
		if(xxdm == '10511'){
			ids = 'sqje-sqly-ytlb';
		}else{
			ids = "sqje-sqly";
		}
	}
	if(check(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	if(xxdm == '10511'){
		if(checksqjesx() == false){
			return false;
		}
		
	}
	var url = "ypzl_jg.do?method=saveYpzljg&type="+type;
	ajaxSubFormWithFun("ypzljgForm", url, function(data) {
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
	 gnmkmc = jQuery("#gnmkmc").val();
	var url = "ypzl_jg.do?method=addYpzljg";
	var title = gnmkmc+"����";
	showDialog(title, 800, 508, url);
}
function update() {
	 gnmkmc = jQuery("#gnmkmc").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else if(rows[0]['sjly']=='1'){
			showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
	}else {
		var url = 'ypzl_jg.do?method=editYpzljg&jgid=' + rows[0]["jgid"];
		var title = gnmkmc+"�޸�";
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
		var url = "ypzl_jg.do?method=delYpzljg";
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
	toImportDataNew("IMPORT_YPZLJG");
	return false;

}

var DCCLBH = "zxdk_ypzl_ypzljg.do";// dcclbh,�������ܱ��

// �Զ��嵼�� ����
function exportConfig() {
	// DCCLBH�������ܱ��,ִ�е�������
	customExport(DCCLBH, ypzljgExportData);
}

// ��������
function ypzljgExportData() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "ypzl_jg.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,�������ܱ��
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

function printypzlsqb(url){
	var sqid="";
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <1) {
		showAlertDivLayer("������ѡ��һ����¼��");
	} else {
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				sqid +=rows[i]["jgid"];
			}else{
				sqid +=rows[i]["jgid"]+",";
			}
		}		
		var url = url + "&ylzd1=" +sqid;
		window.open(url);
	}
}

//��ʦ�����������Ϊ1000
function checksqjesx(){
	sqje =parseInt(jQuery("#sqje").val());
	if(sqje == 0){
		showAlert("��������Ϊ0��");
		return false;
	}
	if(sqje > 1000){
		showAlert("�����������Ϊ1000��");
		return false;
	}
}