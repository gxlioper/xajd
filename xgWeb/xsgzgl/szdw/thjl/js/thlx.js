
/**
 * ����̸������
 */
function addThlx(){
	var url = "szdw_thlx.do?method=addThlx";
	if("10351" == jQuery("#xxdm").val()){
		url += "&type="+jQuery("#type").val();
	}
	showDialog('���̸������',600,350,url);
}

/**
 * ����̸�����ͱ������
 */
function addThlxAction(){
	var lxdm = jQuery('#lxdm').val();
	if (jQuery.trim(lxdm)==""){
		showAlert("�뽫��������д������");
		return false;
	}
    var lxmc = jQuery('#lxmc').val();
    if (jQuery.trim(lxmc)==""){
        showAlert("�뽫��������д������");
        return false;
    }
    var wttg = jQuery('#wttg').val();
    if(jQuery.trim(wttg)==""){
        showAlert("�뽫��������д������");
        return false;
    }
	var url = "szdw_thlx.do?method=addThlxAction";
	ajaxSubFormWithFun("thlxForm",url,function(data){
        if (data["message"] == "����ɹ���") {
            showAlert(data["message"], {}, {
                "clkFun" : function() {
                        refershParentThlx();
                }
            });
        } else {
            showAlert(data["message"]);
        }
	});
}

/**
 * �޸�̸������
 */
function updateThlx(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} else{
        var url = "szdw_thlx.do?method=updateThlx&lxdm=" + rows[0]['lxdm'];
        if("10351" == jQuery("#xxdm").val()){
            url += "&type="+jQuery("#type").val();
        }
		showDialog('�޸�̸������',600,350,url);
	}
}

/**
 * �޸�̸�����ͱ������
 */
function updateThlxAction(){
	if(jQuery.trim(jQuery("#lxmc").val())==""){
        showAlert("�뽫��������д������");
        return false;
	}
    var wttg = jQuery('#wttg').val();
    if(jQuery.trim(wttg)==""){
        showAlert("�뽫��������д������");
        return false;
    }
	var url = "szdw_thlx.do?method=updateThlxAction";
	ajaxSubFormWithFun("thlxForm",url,function(data){
        if (data["message"] == "����ɹ���") {
            showAlert(data["message"], {}, {
                "clkFun" : function() {
                    refershParentThlx();
                }
            });
        } else {
            showAlert(data["message"]);
        }
	});
}


/**
 * ɾ��̸������
 */
function deleteThlx(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	} else{
		showConfirmDivLayer("��ȷ��Ҫɾ����ѡ��¼��",{"okFun":function(){
			jQuery.post("szdw_thlx.do?method=deleteThlx",{lxdms:ids.toString(),type:jQuery("#type").val()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * �ڵ���̸���������/�޸Ĵ�����ˢ�¸�ҳ�棬���رմ���
 */

function refershParentThlx(){
	if (frameElement.api){
		var api = frameElement.api,W = api.opener;
		W.reloadThlxDataTable();
		iFClose();
	}
}
