
var gnmkmc = "";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='ypzlsqView(\""
			+ rowObject["sqid"]+"\");'>" + cellValue
			+ "</a>";
}
function ypzlsqView(sqid) {
	 gnmkmc = jQuery("#gnmkmc").val();
	showDialog(gnmkmc+"�鿴", 800, 500, "ypzl_sq.do?method=viewYpzlsq&sqid="+sqid);
}


function saveYpzl(type) {
	var flg=true;
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
	var url = "ypzl_sq.do?method=saveSqjg&type=" + type;
	ajaxSubFormWithFun("ypzlsqForm", url, function(data) {
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
	gnmkmc = jQuery("#gnmkmc").val();
	var sqkg = jQuery("#sqkg").val();
	if ("0" == sqkg) {
		showAlertDivLayer("��ǰ�ѹرգ��������Ա��ϵ��");
		return false;
	}
	var url = "ypzl_sq.do?method=addYpzlsq";
	var title = gnmkmc;
	showDialog(title, 800, 508, url);
}

function update() {
	gnmkmc = jQuery("#gnmkmc").val();
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

		var url = 'ypzl_sq.do?method=editYpzlsq&sqid=' + rows[0]["sqid"];
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
	} else if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("ֻ��ɾ��δ�ύ�������˻صļ�¼��");
		return false;
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("ypzl_sq.do?method=delYpzlsq", {
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
			jQuery.post("ypzl_sq.do?method=submitYpzlsq", {
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
				jQuery.post("ypzl_sq.do?method=cancelYpzlsq", {
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
		showDialog("�������̸���",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
}


var DCCLBH = "zxdk_ypzl_ypzlsq.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ypzlsqExportData);
}

//��������
function ypzlsqExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "ypzl_sq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
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

function printypzlsqb(url){
	var sqid="";
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <1) {
		showAlertDivLayer("������ѡ��һ����¼��");
	} else {
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				sqid +=rows[i]["sqid"];
			}else{
				sqid +=rows[i]["sqid"]+",";
			}
		}		
		var url = url + "&sqid=" +sqid;
		window.open(url);
	}
}

//��ʦ������������Ϊ1000
function checksqjesx(){
	sqje =parseInt(jQuery("#sqje").val());
	if(sqje == 0){
		showAlert("�������Ϊ0��");
		return false;
	}
	if(sqje > 1000){
		showAlert("����������Ϊ1000��");
		return false;
	}
}