

/**
 * ����ѧ��ԤԼ��ѯ����
 */
function addXsyysq(){
	showDialog('���ѧ��ԤԼ��ѯ����',750,440,'xljk_xsyyzx.do?method=addXsyysq');
}

/**
 * ����ѧ��ԤԼ��ѯ���뱣�����
 */
function addXsyysqAction(){
	if(!checkNotNull("yyzxsj-xslxdh-yyzxzt")){
		showAlert("�뽫��������д������");
		return false;
	}
	
	var yyzxzt = jQuery('#yyzxzt').val();
	if(yyzxzt.length > 500){
		showAlertDivLayer("�����Ҫ�����������������"+500+",��ȷ�ϣ�");
		return false;
	}
	var yyzxxq = jQuery('#yyzxxq').val();
	if(yyzxxq.length > 500){
		showAlertDivLayer("������ע�������������"+500+",��ȷ�ϣ�");
		return false;
	}
	var url = "xljk_xsyyzx.do?method=addXsyysqAction";
	ajaxSubFormWithFun("xsyysqForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParentXsyysq();
			}
		}});
	});
}

/**
 * �޸�ѧ��ԤԼ��ѯ����
 */
function updateXsyysq(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} else{
		if(rows[0]['yyzt'] != '1'){
			showAlertDivLayer("ֻ�ܶ�ԤԼ�еļ�¼�����޸ģ�");
			return false;
		}
		showDialog('�޸�ѧ��ԤԼ��ѯ����',750,440,'xljk_xsyyzx.do?method=updateXsyysq&sqid=' + rows[0]['sqid']);
	}
}

/**
 * �޸�ѧ��ԤԼ��ѯ���뱣�����
 */
function updateXsyysqAction(){
	if(!checkNotNull("yyzxsj-xslxdh-yyzxzt")){
		showAlert("�뽫��������д������");
		return false;
	}
	
	var yyzxzt = jQuery('#yyzxzt').val();
	if(yyzxzt.length > 500){
		showAlertDivLayer("�����Ҫ�����������������"+500+",��ȷ�ϣ�");
		return false;
	}
	var yyzxxq = jQuery('#yyzxxq').val();
	if(yyzxxq.length > 500){
		showAlertDivLayer("������ע�������������"+500+",��ȷ�ϣ�");
		return false;
	}
	var url = "xljk_xsyyzx.do?method=updateXsyysqAction";
	ajaxSubFormWithFun("xsyysqForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParentXsyysq();
			}
		}});
	});
}

/**
 * ȡ��ѧ��ԤԼ��ѯ����
 */
function cancelXsyysq(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} else{
		if((rows[0]['yyzt'] == '1' || rows[0]['yyzt'] == '2') && rows[0]['zxzt'] != '1'){
			showConfirmDivLayer("��ȷ��Ҫȡ��ԤԼ��ѯ���룿",{"okFun":function(){
				showDialog('ȡ��ԤԼ��ѯ����',550,190,'xljk_xsyyzx.do?method=cancelXsyysq&sqid='+rows[0]['sqid']+'&yyzt='+rows[0]['yyzt']);
			}});
		}else{
			showAlertDivLayer("ֻ�ܶ�ԤԼ�л�ԤԼ�ɹ�(����ѯ��δ��ѯ)�ļ�¼������");
		}
	}
}

/**
 * ��ѯ����
 */
function setZxpj(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ���۵�������ѯ��¼��");
		return false;
	} else{
		var zxzt = rows[0]['zxzt'];
		if(zxzt != '1'){
			showAlertDivLayer("����������ѯԤԼδʵ����ѯ���������ۡ�");
			return false;
		}
		if(rows[0]['sqid']!=null){
			showDialog('��ѯ����',530,210,'xljk_xsyyzx.do?method=setZxpj&sqid=' + rows[0]['sqid']);
		}else{
			showDialog('��ѯ����',530,210,'xljk_xsyyzx.do?method=setZxpj&zxid=' + rows[0]['zxid']);
		}
	}
}

function changeZxsjj(node){
	var zxsjj = jQuery(node).parent().find(".zxsjj").html();
	jQuery("#zxsjjTd").html(zxsjj=="null"?"":zxsjj);
}

/**
 * �ڵ���������ѯԤԼ��Ϣ���/�޸Ĵ�����ˢ�¸�ҳ�棬���رմ���
 */
function refershParentXsyysq(){
	if (frameElement.api){
		var api = frameElement.api,W = api.opener;
		W.reloadXsyysqDataTable();
		iFClose();
	} 
}