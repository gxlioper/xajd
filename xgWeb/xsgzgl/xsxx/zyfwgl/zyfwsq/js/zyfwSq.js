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
var checkId = 'xh-xn-xq-fwkssj-fwjssj-fwddssx-fwdd-jzr-fwxss-fwnr';

function zyfwSqSaveForAdd(type) {
	if(!checkNotNull(checkId)){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	if(jQuery("#fwddssx").val().endsWith("0000")){
		showAlert("�����ٽ�ʡ��������ѡ��������");
		return false;
	}
	if (jQuery("#fwnr").val().length>500) {
		showAlert("���������������500�֣�");
		return false;
	}
	var url = "xsxx_zyfwgl_sq.do?method=zyfwSqSaveForAdd&type=" + type;
	ajaxSubFormWithFun("zyfwSqForm", url, function(data) {
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
function zyfwSqSaveForEdit(type) {
	if(!checkNotNull(checkId)){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	if(jQuery("#fwddssx").val().endsWith("0000")){
		showAlert("�����ٽ�ʡ��������ѡ��������");
		return false;
	}
	if (jQuery("#fwnr").val().length>500) {
		showAlert("���������������500�֣�");
		return false;
	}
	var url = "xsxx_zyfwgl_sq.do?method=zyfwSqSaveForEdit&type=" + type;
	ajaxSubFormWithFun("zyfwSqForm", url, function(data) {
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
	var url = "xsxx_zyfwgl_sq.do?method=zyfwSqAdd";
	var title = jQuery("#gnmkmc").val();
	showDialog(title, 800, 550, url);
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

		var url = 'xsxx_zyfwgl_sq.do?method=zyfwSqEdit&fwid=' + rows[0]["fwid"];
		var title = jQuery("#gnmkmc").val()+"�޸�";
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
			jQuery.post("xsxx_zyfwgl_sq.do?method=zyfwSqDel", {
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
function submitBusi() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
		return false;
	}
	var rows = jQuery("#dataTable").getSeletRow();
	var xh = rows[0]["xh"];
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
			jQuery.post("xsxx_zyfwgl_sq.do?method=zyfwSqSubmit", {
				fwid : ids.toString(),
				xh:xh
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
			jQuery.post("xsxx_zyfwgl_sq.do?method=zyfwSqCancel", {
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
	var url = "comm_spl.do?method=lcgz&sqid="+ rows[0]['fwid'] + "&splc=" + rows[0]['splc']
	showDialog(title,530,380,url);
}


/**
 * ����
 */
var DCCLBH = "xsxx_zyfwgl_sq.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, exportData);
}

//��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xsxx_zyfwgl_sq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}