
/**
 * ����������������
 */
function addXlwtlx(){
	showDialog('���������������',370,190,'xljk_xlwtlxwh.do?method=addXlwtlx');
}

/**
 * ���������������ͱ������
 */
function addXlwtlxAction(){
	var lxdm = jQuery('#lxdm').val();
	if (jQuery.trim(lxdm)==""){
		showAlert("�뽫��������д������");
		return false;
	}
	var url = "xljk_xlwtlxwh.do?method=addXlwtlxAction";
	ajaxSubFormWithFun("xlwtlxwhForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParentXlwtlx();
			}
		}});
	});
}

/**
 * �޸�������������
 */
function updateXlwtlx(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} else{
		showDialog('�޸�������������',370,190,'xljk_xlwtlxwh.do?method=updateXlwtlx&lxdm=' + rows[0]['lxdm']);
	}
}

/**
 * �޸������������ͱ������
 */
function updateXlwtlxAction(){
	var url = "xljk_xlwtlxwh.do?method=updateXlwtlxAction";
	ajaxSubFormWithFun("xlwtlxwhForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParentXlwtlx();
			}
		}});
	});
}


/**
 * ɾ��������������
 */
function deleteXlwtlx(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	} else{
		showConfirmDivLayer("��ȷ��Ҫɾ����ѡ��¼��",{"okFun":function(){
			jQuery.post("xljk_xlwtlxwh.do?method=deleteXlwtlx",{lxdms:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * �ڵ������������������/�޸Ĵ�����ˢ�¸�ҳ�棬���رմ���
 */
function refershParentXlwtlx(){
	if (frameElement.api){
		var api = frameElement.api,W = api.opener;
		W.reloadXlwtlxDataTable();
		iFClose();
	} 
}