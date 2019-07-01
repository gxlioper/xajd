/**
 * ־Ը�����������js
 */

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * ѧ�Ÿ�ʽ��
 */
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='zyfwSqShow(\""
			+ rowObject["fwid"]+"\");'>" + cellValue
			+ "</a>";
}

/**
 * ����ص������ȡ
 */
function fwddSubString(cellValue, rowObject){
	var cellValueNotNull = cellValue ? cellValue : "";
	var cellValueNew = cellValueNotNull.length > 20 ? cellValue.substring(0,20)+"..." : cellValueNotNull;
	return "<span title='"+cellValueNotNull+"' >" + cellValueNew + "</span>";
}

/**
 * �鿴
 */
function zyfwSqShow(fwid) {
	var title = jQuery("#gnmkmc").val()+"�鿴";
	var url = "xsxx_zyfwgl_sq.do?method=zyfwSqShow&fwid="+fwid;
	showDialog(title, 800, 500,url);
}

/**
 * �����ı���
 */
var checkId = 'hdmc-hdzt-hdrq-bjmc-hdfzrxm-hdfzrlxdh';

function ztbhSqSaveForAdd(type) {
	if(!checkNotNull(checkId)){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}

	var url = "ztbhgl_ztbhsq.do?method=ztbhSqSaveForAdd&type=" + type;
	ajaxSubFormWithFun("ZtbhSqForm", url, function(data) {
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
function ztbhSqSaveForEdit(type) {
	if(!checkNotNull(checkId)){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	var url = "ztbhgl_ztbhsq.do?method=ztbhSqSaveForEdit&type=" + type;
	ajaxSubFormWithFun("ZtbhSqForm", url, function(data) {
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
	var url = "ztbhgl_ztbhsq.do?method=ztbhSqAdd";
	showDialog("����������", 800, 550, url);
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

		var url = 'ztbhgl_ztbhsq.do?method=ztbhSqEdit&sqid=' + rows[0]["sqid"];
		var title = "�������޸�";
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
			jQuery.post("ztbhgl_ztbhsq.do?method=ztbhSqDel", {
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
			jQuery.post("ztbhgl_ztbhsq.do?method=ztbhSqSubmit", {
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
			jQuery.post("ztbhgl_ztbhsq.do?method=ztbhSqCancel", {
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


//��鿴
function hdLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='View(\""
        + rowObject['sqid'] + "\");'>" + cellValue
        + "</a>";
}


function View(sqid) {
    showDialog("���Ϣ", 900, 450, "ztbhgl_ztbhsq.do?method=getHdInfo&sqid=" + sqid );
}

function ztbhLcinfo(){
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length != 1){
        showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
    } else {
        showDialog("�������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
    }
}


