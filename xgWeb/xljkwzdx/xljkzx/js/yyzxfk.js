
/**
 * ѧ�����ӣ���ʾԤԼ��ѯ������Ϣ��������ѯ��Ϣ��
 * 
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLinkForYyfk(cellValue, rowObject) {
	var onclickfn = "onclick=\""
			+ "showDialog('�鿴������ѯ' , 750 , 360 , 'xljk_yyzxfk.do?method=viewXlzxYyfk&sqid="
			+ rowObject['sqid'] + "')" + "\"";

	var href = "href = 'javascript:void(0);'";

	var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
			+ "</a>";

	return el;
}

/**
 * ԤԼ����
 */
function xlzxyyfk(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����ҪԤԼ�����ļ�¼��");
		return false;
	} else{
		var zxzt = rows[0]['zxzt'];
		var yyzt = rows[0]['yyzt'];
		if(yyzt == '3'){
			showAlertDivLayer("�����ܶ�ԤԼ�У�ѧ��ȡ���������ݽ��в�����");
			return false;
		}else if(yyzt == '4'){
			showAlertDivLayer("�����ܶ�ԤԼ�ɹ���ѧ��ȡ���������ݽ��в�����");
			return false;
		}

		if(zxzt == '1' || zxzt == '2'){
			showAlertDivLayer("��ֻ�ܶ�δ���������ѷ�������ѯ�����ݽ���ԤԼ������");
			return false;
		}
		showDialog('ԤԼ����',750,440,'xljk_yyzxfk.do?method=xlzxYyfk&sqid=' + rows[0]['sqid'] + '&yyzt=' + rows[0]['yyzt']);
	}
}

/**
 * ԤԼ�����������
 */
function opYyzxfkAction(){
	var yyzt = jQuery("input[name='yyzt']:checked").val();
	var zxs = jQuery("input[name='zxs']:checked");
	if(yyzt == '2'){
		if(zxs.length!=1){
			showAlert("��ѡ��һ����ѯʦ��");
			return false;
		}
		if(!checkNotNull("zzaprq")){
			showAlert("�뽫��������д������");
			return false;
		}
		var bz = jQuery('#bz').val();
		if(bz.length > 500){
			showAlertDivLayer("������ע�������������"+500+",��ȷ�ϣ�");
			return false;
		}
	}else if(yyzt == '5'){
		if(!checkNotNull("yysbyy")){
			showAlert("�뽫��������д������");
			return false;
		}
		var yysbyy = jQuery('#yysbyy').val();
		if(yysbyy.length > 500){
			showAlertDivLayer("ԤԼʧ��ԭ���������������"+500+",��ȷ�ϣ�");
			return false;
		}
	}
	var url = "xljk_yyzxfk.do?method=xlzxYyfkAction";
	ajaxSubFormWithFun("yyzxfkForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParentYyzxfk();
			}
		}});
	});
}

function checkZxs(node){
	var lxdh = jQuery(node).parent().find(".lxdh").html();
	var address = jQuery(node).parent().find(".address").html();
	jQuery("#zxslxdh").val(lxdh=="null"?"":lxdh);
	jQuery("#zxdz").val(address=="null"?"":address);
}

/**
 * �ڵ���������ѯ������Ϣ���/�޸Ĵ�����ˢ�¸�ҳ�棬���رմ���
 */
function refershParentYyzxfk(){
	if (frameElement.api){
		var api = frameElement.api,W = api.opener;
		W.reloadYyzxfkDataTable();
		iFClose();
	} 
}