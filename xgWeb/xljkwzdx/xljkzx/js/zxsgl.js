
/**
 * ������ѯʦ
 */
function addZxsxx(){
	showDialog('�����ѯʦ��Ϣ',750,440,'xljk_zxsgl.do?method=addZxsxx');
}

/**
 * ������ѯʦ�������
 */
function addZxsxxAction(){
	var zgh = jQuery('#zgh').val();
	if (jQuery.trim(zgh)==""){
		showAlert("�뽫��������д������");
		return false;
	}
	var kjdrs = jQuery('#kjdrs').val();
	if(isNaN(kjdrs)){
		showAlert("��ԤԼ����ֻ����д���֣�");
		jQuery('#kjdrs').focus();
		return false;
	}
	var zxsjj = jQuery('#zxsjj').val();
	if(zxsjj.length > 500){
		showAlertDivLayer("��ע�������������"+500+",��ȷ�ϣ�");
		return false;
	}
	var url = "xljk_zxsgl.do?method=addZxsxxAction";
	ajaxSubFormWithFun("zxsxxForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParentZxsxx();
			}
		}});
	});
}

/**
 * �޸���ѯʦ
 */
function updateZxsxx(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} else{
		showDialog('�޸���ѯʦ��Ϣ',750,440,'xljk_zxsgl.do?method=updateZxsxx&zgh=' + rows[0]['zgh']);
	}
}

/**
 * �޸���ѯʦ�������
 */
function updateZxsxxAction(){
	var zgh = jQuery('#zgh').val();
	if (jQuery.trim(zgh)==""){
		showAlert("�뽫��������д������");
		return false;
	}
	var kjdrs = jQuery('#kjdrs').val();
	if(isNaN(kjdrs)){
		showAlert("��ԤԼ����ֻ����д���֣�");
		jQuery('#kjdrs').focus();
		return false;
	}
	var zxsjj = jQuery('#zxsjj').val();
	if(zxsjj.length > 500){
		showAlertDivLayer("��ע�������������"+500+",��ȷ�ϣ�");
		return false;
	}
	var url = "xljk_zxsgl.do?method=updateZxsxxAction";
	ajaxSubFormWithFun("zxsxxForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParentZxsxx();
			}
		}});
	});
}

/**
 * ɾ����ѯʦ
 */
function deleteZxsxx(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ����ѡ��¼��",{"okFun":function(){
			jQuery.post("xljk_zxsgl.do?method=deleteZxsxx",{zghs:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * �����ڸ�״̬
 */
function setZxsxxStatus(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫ���õļ�¼��");
		return false;
	} else {
		showDialog('�����ڸ�״̬',370,170,'xljk_zxsgl.do?method=setZxsxxStatus&zghs=' + ids.toString());
	}
}

/**
 * ��ѯԤԼ˵��
 */
function setZxyysm(){
	showDialog('��ѯԤԼ˵��',650,230,'xljk_zxsgl.do?method=setZxyysm');
}

/**
 * �ڵ�����ѯʦ��Ϣ���/�޸Ĵ�����ˢ�¸�ҳ�棬���رմ���
 */
function refershParentZxsxx(){
	if (frameElement.api){
		var api = frameElement.api,W = api.opener;
		W.reloadZxsxxDataTable();
		iFClose();
	} 
}