
/**
 * ѧ�����ӣ���ʾԤԼ��ѯ������Ϣ��������ѯ��Ϣ����ѯʦ������Ϣ��
 * 
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLinkForZxcl(cellValue, rowObject) {
	var onclickfn = "onclick=\""
			+ "showDialog('�鿴������ѯ' , 750 , 360 , 'xljk_xlzxcl.do?method=viewXlzxcl&zxid="
			+ rowObject['zxid'] + "')" + "\"";

	var href = "href = 'javascript:void(0);'";

	var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
			+ "</a>";

	return el;
}

/**
 * ����������ѯ����
 */
function addXlzxcl(){
	showDialog('���������ѯ',750,440,'xljk_xlzxcl.do?method=addXlzxcl');
}

/**
 * ����������ѯ���������
 */
function addXlzxclAction(){
	if(!checkNotNull("xh-zzaprq")){
		showAlert("�뽫��������д������");
		return false;
	}
	var bz = jQuery('#bz').val();
	if(bz.length > 500){
		showAlertDivLayer("��ע�������������"+500+",��ȷ�ϣ�");
		return false;
	}
	var url = "xljk_xlzxcl.do?method=addXlzxclAction";
	ajaxSubFormWithFun("xlzxclForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParentXlzxcl();
			}
		}});
	});
}

/**
 * �޸�������ѯ����
 */
function updateXlzxcl(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} else{
		showDialog('�޸�������ѯ',750,440,'xljk_xlzxcl.do?method=updateXlzxcl&zxid=' + rows[0]['zxid']);
	}
}

/**
 * �޸�������ѯ���������
 */
function updateXlzxclAction(){
	if(!checkNotNull("xh-zzaprq")){
		showAlert("�뽫��������д������");
		return false;
	}
	var bz = jQuery('#bz').val();
	if(bz.length > 500){
		showAlertDivLayer("��ע�������������"+500+",��ȷ�ϣ�");
		return false;
	}
	var url = "xljk_xlzxcl.do?method=updateXlzxclAction";
	ajaxSubFormWithFun("xlzxclForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParentXlzxcl();
			}
		}});
	});
}

/**
 * ɾ��������ѯ����
 */
function deleteXlzxcl(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	} else{
		for(var i = 0; i<rows.length;i++){
			if(rows[i]['sqid']!=null&&rows[i]['sqid']!=""){
				showAlertDivLayer("ѧ��ԤԼ��������ѯ����ɾ������������ѡ�ļ�¼���Ƿ����ѧ��ԤԼ��������ѯ��");
				return false;
			}
			if(rows[i]['xspjzt']=="������"){
				showAlertDivLayer("ѧ�������۵ļ�¼����ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ����ѡ��¼��",{"okFun":function(){
			jQuery.post("xljk_xlzxcl.do?method=deleteXlzxcl",{zxids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
/**
 * 
 * ��ѯ����
 */
function viewZxFk(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} else{
		if(rows[0]['yyzt'] == '2' || rows[0]['sqid'] == null){
			showDialog('��ѯ����',750,440,'xljk_xlzxcl.do?method=viewZxFk&zxid=' + rows[0]['zxid']);
		}else{
			showAlertDivLayer("ֻ�ܶ�ԤԼ�ɹ����ѧ�������������ѯ��¼������ѯ������");
		}
	}
}
/**
 * ��ѯ�����������
 * 
 */
function zxFkAction(){
	var lfzzs = jQuery('#lfzzs').val();
	if(lfzzs.length > 500){
		showAlertDivLayer("�����������������������"+500+",��ȷ�ϣ�");
		return false;
	}
	var xlhd = jQuery('#xlhd').val();
	if(xlhd.length > 500){
		showAlertDivLayer("��ѯ���̼���Ҫ���������������������"+500+",��ȷ�ϣ�");
		return false;
	}
	var zxzj = jQuery('#zxzj').val();
	if(zxzj.length > 500){
		showAlertDivLayer("��ѯ����ܽ��������������"+500+",��ȷ�ϣ�");
		return false;
	}
	var url = "xljk_xlzxcl.do?method=zxFkAction";
	ajaxSubFormWithFun("xlzxclForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParentXlzxcl();
			}
		}});
	});
}

/**
 * �ڵ���������ѯ������Ϣ���/�޸Ĵ�����ˢ�¸�ҳ�棬���رմ���
 */
function refershParentXlzxcl(){
	if (frameElement.api){
		var api = frameElement.api,W = api.opener;
		W.reloadXlzxclDataTable();
		iFClose();
	} 
}