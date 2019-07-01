
/**
 * ������������
 */
function addFdlx(){
	showDialog('��Ӹ�������',370,190,'xljk_fdlxwh.do?method=addFdlx');
}

/**
 * �����������ͱ������
 */
function addFdlxAction(){
	var fdlxdm = jQuery('#fdlxdm').val();
	if (jQuery.trim(fdlxdm)==""){
		showAlert("�뽫��������д������");
		return false;
	}
	var url = "xljk_fdlxwh.do?method=addFdlxAction";
	ajaxSubFormWithFun("fdlxwhForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParentFdlx();
			}
		}});
	});
}

/**
 * �޸ĸ�������
 */
function updateFdlx(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} else{
		showDialog('�޸ĸ�������',370,190,'xljk_fdlxwh.do?method=updateFdlx&fdlxdm=' + rows[0]['fdlxdm']);
	}
}

/**
 * �޸ĸ������ͱ������
 */
function updateFdlxAction(){
	var url = "xljk_fdlxwh.do?method=updateFdlxAction";
	ajaxSubFormWithFun("fdlxwhForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParentFdlx();
			}
		}});
	});
}

/**
 * ɾ����������
 */
function deleteFdlx(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	} else{
		showConfirmDivLayer("��ȷ��Ҫɾ����ѡ��¼��",{"okFun":function(){
			jQuery.post("xljk_fdlxwh.do?method=deleteFdlx",{fdlxdms:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * �ڵ��������������/�޸Ĵ�����ˢ�¸�ҳ�棬���رմ���
 */
function refershParentFdlx(){
	if (frameElement.api){
		var api = frameElement.api,W = api.opener;
		W.reloadFdlxDataTable();
		iFClose();
	} 
}