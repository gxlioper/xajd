
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='byhkglSqView(\""
			+ rowObject["sqid"]+"\");'>" + cellValue
			+ "</a>";
}
function byhkglSqView(sqid) {
	
	if(sqid == "null") {
		showAlertDivLayer("�ü�¼Ϊδ����״̬���������룡");
		return false;
	}
	
	showDialog("����鿴", 800, 500, "byhkgl_sq.do?method=viewByhkglSq&sqid="+sqid);
}


function saveByhkgl(type) {
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
	var url = "byhkgl_sq.do?method=saveByhkgl&type=" + type +"&sfzq=" + sfzq;
	ajaxSubFormWithFun("byhkglSqForm", url, function(data) {
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
	var sqkg = jQuery("#sqkg").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if ("0" == sqkg) {
		showAlertDivLayer("��ǰ�ѹرգ��������Ա��ϵ��");
		return false;
	}else if(rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ����ļ�¼��");
		return false;
	}else {
		var shzt = rows[0]["shzt"];
		if(shzt != null) {
			showAlertDivLayer("��ѧ���Ѿ������,��ȷ�ϣ�");
			return false;
		}
	}
	var url = "byhkgl_sq.do?method=addByhkglSq&xh=" + rows[0]["xh"];
	var title = "����";
	showDialog(title, 800, 508, url);
}

function update() {
	var sqkg = jQuery("#sqkg").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else {

		var shzt = rows[0]["shzt"];
		if ('3' != rows[0]['shzt'] && "0" == sqkg) {
			showAlertDivLayer("��ǰ�����ѹرգ��������Ա��ϵ��");
			return false;
		}
		if ("0" != shzt&&"3" != shzt) {
			showAlertDivLayer("ֻ��δ�ύ�����˻صļ�¼�����޸ģ�");
			return false;
		}

		var url = 'byhkgl_sq.do?method=editByhkglSq&sqid=' + rows[0]["sqid"];
		var title = "�����޸�";
		showDialog(title, 800, 508, url);
	}

}

// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("ֻ��ɾ��δ�ύ�������˻صļ�¼��");
		return false;
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("byhkgl_sq.do?method=delByhkglSq", {
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

// �ύ
function submitBusi() {
	var flg=true;
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var sqkg = jQuery("#sqkg").val();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
		return false;
	}
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
			jQuery.post("byhkgl_sq.do?method=submitByhkglsq", {
				values : ids.toString()
			}, function(data) {
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			}, 'json');
		}
	});	

}
// ����
function cancel() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else if (ids.length > 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shzt'] != '5') {
				showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
				return false;
			}
		}

		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("byhkgl_sq.do?method=cancelByhkglsq", {
					values : ids.toString(),
					splcid : rows[0]['splc']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}

}
/*
 * ���̸���
 */
function lcgz() {
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (1 != rows.length) {
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
		return false;
	} 
	var shzt = rows[0]["shzt"];
	if ("0" == shzt) {
		showAlertDivLayer("�ü�¼Ϊδ�ύ״̬�������ύ��");
		return false;
	}
	if(shzt == null) {
		showAlertDivLayer("�ü�¼Ϊδ����״̬���������룡");
		return false;
	}
	
	showDialog("��Ŀ�걨�������̸���",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
}


var DCCLBH = "zxdk_byhkgl_byhksq.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, exportData);
}

//��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "byhkgl_sq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
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