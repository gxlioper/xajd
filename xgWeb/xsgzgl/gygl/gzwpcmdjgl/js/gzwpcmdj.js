/**
 * ��������
 * 
 * @return
 */
function addUpdateGzwp(action) {
	if("add" === action)
		showDialog('���ӹ�����Ʒ���ŵǼ�', 780, 410, 'gygl_gzwpcmdj.do?method=gzwpcmdjAdd');
	else if("update" === action){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1){
			showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			return false;
		}
		showDialog('�޸�', 780, 405, 'gygl_gzwpcmdj.do?method=gzwpcmdjUpdate&gzwpdjid=' + rows[0]['gzwpdjid']);
	}
}


/**
 * ����
 * 
 * @return
 */
function addGzwpcmdjxx() {
	var bz = jQuery('#bz').val();
	var xh = jQuery('#xh').val();
	var zbry = jQuery('#zbry').val();
	var cmsj = jQuery('#cmsj').val();
	var wpmc = jQuery('#wpmc').val();
	
	if ((zbry==null || jQuery.trim(zbry)== '') || (xh==null || jQuery.trim(xh)== '') || (cmsj==null || jQuery.trim(cmsj)== '') || (wpmc==null || jQuery.trim(wpmc)== '')) {
		showAlertDivLayer("�뽫��<font color=\"red\"> * </font>����Ŀ��д������");
		return false;
	}

	if (bz.length > 500) {
		showAlertDivLayer("��ע�������������" + 500 + ",��ȷ�ϣ�");
		return false;
	}
	
	
	var url = "gygl_gzwpcmdj.do?method=gzwpcmdjAddAction";
	ajaxSubFormWithFun("gzwpcmdjForm", url, function(data) {
		showAlertDivLayer(data["message"], {}, { "clkFun" : function() {
			if (parent.window) {
				refershParent();
			}
		} });
	});
}

/**
 * ����
 * 
 * @return
 */
function updateGzwpcmdjxx() {
	var bz = jQuery('#bz').val();
	var zbry = jQuery('#zbry').val();
	var cmsj = jQuery('#cmsj').val();
	var wpmc = jQuery('#wpmc').val();
	
	if (!checkNull("zbry-cmsj-wpmc")) {
		return false;
	}
	
	if (bz.length > 500) {
		showAlertDivLayer("��ע�������������" + 500 + ",��ȷ�ϣ�");
		return false;
	}
	
	
	var url = "gygl_gzwpcmdj.do?method=gzwpcmdjUpdateAction";
	ajaxSubFormWithFun("gzwpcmdjForm", url, function(data) {
		showAlertDivLayer(data["message"], {}, { "clkFun" : function() {
			if (parent.window) {
				refershParent();
			}
		} });
	});
}

/**
 * ɾ��
 * @return
 */
function deleteGzwp(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0){
		showAlertDivLayer("��ѡ��һ����Ҫɾ���ļ�¼��");
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	showConfirmDivLayer("��ȷ��Ҫɾ����ѡ��¼��",{"okFun":function(){
		jQuery.post("gygl_gzwpcmdj.do?method=gzwpcmdjDeleteAction",{pks:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
}

/**
 * �߼���ѯ
 * 
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

var DCCLBH = "gygl_gzwpcmdjgl.do";// dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	// DCCLBH�������ܱ��,ִ�е�������
	customExport(DCCLBH, exportData);
}

//��������
function exportData() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "gygl_gzwpcmdj.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,�������ܱ��
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * ����
 */
function importConfig() {
	toImportData("IMPORT_N382201");
	return false;
}

/**
 * �鿴��ϸ��Ϣ��������
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLink(cellValue, rowObject) {
	var onclickfn = "onclick=\""
		+ "showDialog('������Ʒ���ŵǼ���Ϣ�鿴' , 720 , 285 , 'gygl_gzwpcmdj.do?method=viewWpxx&gzwpdjid="
		+ rowObject['gzwpdjid'] + "')" + "\"";

	var href = "href = 'javascript:void(0);'";

	var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
			+ "</a>";

	return el;
}


