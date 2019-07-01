/**
 * ־Ը�����������js
 */

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}




/**
 * �����ı���
 */
var checkId = 'hdmc-hdzt-hdrq-bjmc-hdfzrxm-hdfzrlxdh';

function bjhdSqSaveForAdd(type) {
	if(!checkNotNull(checkId)){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}

	var url = "bjhdgl_bjhdsq.do?method=bjhdSqSaveForAdd&type=" + type;
	ajaxSubFormWithFun("BjhdSqForm", url, function(data) {
		 if(data["message"]=="����ɹ���"||data["message"]=="�ύ�ɹ���"){
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

/**
 * �༭�ı���
 */
function bjhdSqSaveForEdit(type) {
	if(!checkNotNull(checkId)){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	var url = "bjhdgl_bjhdsq.do?method=bjhdSqSaveForEdit&type=" + type;
	ajaxSubFormWithFun("BjhdSqForm", url, function(data) {
		 if(data["message"]=="����ɹ���"||data["message"]=="�ύ�ɹ���"){
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

/**
 * ���뵯��ҳ��
 */
function add() {
	var sqkg = jQuery("#sqkg").val();
	if ("0" == sqkg) {
		showAlertDivLayer("��ǰ�ѹرգ��������Ա��ϵ��");
		return false;
	}
	var url = "bjhdgl_bjhdsq.do?method=bjhdSqAdd";
	showDialog("�༶�����", 800, 550, url);
}

/**
 * �޸ĵ���ҳ��
 */
function edit() {
	var sqkg = jQuery("#sqkg").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {

		var shzt = rows[0]["shzt"];
		if ('3' != rows[0]['shzt'] && "0" == sqkg) {
			showAlertDivLayer("��ǰ�����ѹرգ��������Ա��ϵ��");
			return false;
		}
		if ("0" != shzt&&"3" != shzt) {
			showAlertDivLayer("ֻ��δ�ύ�����˻صļ�¼�����޸ģ�");
			return false;
		}

		var url = 'bjhdgl_bjhdsq.do?method=bjhdSqEdit&sqid=' + rows[0]["sqid"];
		var title = "�༶��޸�";
		showDialog(title, 800, 550, url);
	}

}

/**
 * ɾ��
 */
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	} 
	for(var i=0;i<rows.length;i++){
		if (rows[i]["shzt"] != "0" && rows[i]["shzt"] != "3") {
			showAlertDivLayer("ֻ��ɾ��δ�ύ�������˻صļ�¼��");
			return false;
		}
	}
	
	showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
		"okFun" : function() {
			jQuery.post("bjhdgl_bjhdsq.do?method=bjhdSqDel", {
				values : ids.toString()
			},
					function(data) {
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}, 'json');
		}
	});
}

/**
 * �ύ
 */
function submit() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
		return false;
	}
	var rows = jQuery("#dataTable").getSeletRow();
	var sqkg = jQuery("#sqkg").val();
	if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
		return false;
	}
	if ('3' != rows[0]['shzt'] && "0" == sqkg) {
		showAlertDivLayer("��ǰ�����ѹرգ��������Ա��ϵ��");
		return false;
	}
	showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
		"okFun" : function() {
			jQuery.post("bjhdgl_bjhdsq.do?method=bjhdSqSubmit", {
				sqid : ids.toString()

			}, function(data) {
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			}, 'json');
		}
	});
}

/**
 * ����
 */
function cancel() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
		return false;
	}
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows[0]['shzt'] != '5') {
		showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
		return false;
	}

	showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
		"okFun" : function() {
			jQuery.post("bjhdgl_bjhdsq.do?method=bjhdSqCancel", {
				values : ids.toString(),
				splcid : rows[0]['splc']
			}, function(data) {
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			}, 'json');
		}
	});
}

/**
 * ���̸���
 */
function lcgz() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (1 != rows.length) {
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
		return false;
	} 
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = rows[0]["shzt"];
	if ("0" == shzt) {
			showAlertDivLayer("�ü�¼Ϊδ�ύ״̬�������ύ��");
			return false;
		}
	var title = jQuery("#gnmkmc").val()+"�������̸���";
	var url = "comm_spl.do?method=lcgz&sqid="+ rows[0]['sqid'] + "&splc=" + rows[0]['splc']
	showDialog(title,530,380,url);
}

function bjhdLcinfo(){
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length != 1){
        showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
    } else {
        showDialog("�������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
    }
}


