var DCCLBH = "cjWsf_wsfcx.do";//dcclbh,�������ܱ��

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function ck() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
	}else {
		var url = 'cjWsfcx.do?method=viewWsfcx&wsfid=' + rows[0]["wsfid"];
		showDialog("�鿴", 800, 460, url);
	}
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else {
		var url = 'cjWsfcx.do?method=editWsfcx&wsfid=' + rows[0]["wsfid"];
		var title = "�޸�";
		showDialog(title, 800, 460, url);
	}

}

// ɾ��
function cancel() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("cjWsfcx.do?method=cxWsfcx", {
					ids : ids
				},
						function(data) {
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
			}
		});
	}
}

function savejg(type){
	var ids = null;
	if(type=='save'){
		ids = "fz";
		if(check(ids) == false){
			showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
			return false;
		}
	}
	var fz = jQuery("#fz").val();
	var reg = /^[0-9]+(.[0-9]{1})?$/;
	var result = reg.test(fz);
	if(!result){
		showAlert("��ֵֻ�������밢�������֣���ֻ������һλС��");		
		return false;
	}
	if(parseInt(fz) > 100){
		showAlert("��ֵ���Ϊ100����ȷ�ϣ�");		
		return false;
	}
	var url = "cjWsfcx.do?method=editWsfcx&type=" +type;
	ajaxSubFormWithFun("WsfcxForm", url, function(data) {
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

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, wsfcxExportData);
}

//��������
function wsfcxExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "cjWsfcx.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
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

//ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	alert(ids);
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("cjWsfcx.do?method=zjcmwsfcxDelete", {
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

