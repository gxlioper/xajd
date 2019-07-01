/**
 * ��������
 * 
 * @return
 */
function addUpdateLfry(action) {
	if("add" === action)
		showDialog('�������õǼ�', 780, 470, 'gygl_lfrydj.do?method=lfrydjAdd');
	else if("update" === action){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1){
			showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			return false;
		}
		showDialog('�޸�', 780, 467, 'gygl_lfrydj.do?method=lfrydjUpdate&lfrdjid=' + rows[0]['lfrdjid']);
	}
}


/**
 * ����
 * 
 * @return
 */
function addLfrydjxx() {
	var bz = jQuery('#bz').val();
	var lfrxm = jQuery('#lfrxm').val();
	var lfrxb = jQuery('#lfrxb').val();
	var lfsj = jQuery('#lfsj').val();
	var lfrsfzh = jQuery('#lfrsfzh').val();
	var xh = jQuery('#xh').val();
	var zbry = jQuery('#zbry').val();
	
	
	if ((zbry==null || jQuery.trim(zbry)== '') || (lfrsfzh==null || jQuery.trim(lfrsfzh)== '')  || (lfrxm==null || jQuery.trim(lfrxm)== '') || (lfrxb==null || jQuery.trim(lfrxb)== '') || (lfsj==null || jQuery.trim(lfsj)== '')) {
		showAlertDivLayer("�뽫��<font color=\"red\"> * </font>����Ŀ��д������");
		return false;
	}

	if (bz.length > 500) {
		showAlertDivLayer("��ע�������������500,��ȷ�ϣ�");
		return false;
	}
	
	
	var url = "gygl_lfrydj.do?method=lfrydjAddAction";
	ajaxSubFormWithFun("lfrydjForm", url, function(data) {
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
function updateLfrydjxx() {
	var bz = jQuery('#bz').val();
	var lfrxm = jQuery('#lfrxm').val();
	var lfrxb = jQuery('#lfrxb').val();
	var lfsj = jQuery('#lfsj').val();
	var lqsj = jQuery('#lqsj').val();
	var lfrsfzh = jQuery('#lfrsfzh').val();
	var xh = jQuery('#xh').val();
	var zbry = jQuery('#zbry').val();
	
	
	if (!checkNull("zbry-lfrsfzh-lfrxm-lfrxb-lfsj")) {
		return false;
	}

	if (bz.length > 500) {
		showAlertDivLayer("��ע�������������500,��ȷ�ϣ�");
		return false;
	}
	
	
	var url = "gygl_lfrydj.do?method=lfrydjUpdateAction";
	ajaxSubFormWithFun("lfrydjForm", url, function(data) {
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
function deleteLfry(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0){
		showAlertDivLayer("��ѡ��һ����Ҫɾ���ļ�¼��");
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	showConfirmDivLayer("��ȷ��Ҫɾ����ѡ��¼��",{"okFun":function(){
		jQuery.post("gygl_lfrydj.do?method=lfrydjDeleteAction",{pks:ids.toString()},function(data){
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

var DCCLBH = "gygl_lfryxxgl.do";// dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	// DCCLBH�������ܱ��,ִ�е�������
	customExport(DCCLBH, exportData);
}

//��������
function exportData() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "gygl_lfrydj.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,�������ܱ��
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * ����
 */
function importConfig() {
	//IMPORT_N382101���ɵĵ���
	toImportDataNew("IMPORT_LFRYDJ");
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
		+ "showDialog('������Ϣ�鿴' , 720 , 400 , 'gygl_lfrydj.do?method=viewLfxx&lfrdjid="
		+ rowObject['lfrdjid'] + "')" + "\"";

	var href = "href = 'javascript:void(0);'";

	var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
			+ "</a>";

	return el;
}

/**
 * ѡ��ѧ��
 */
function chooseStudent(){
	var elementIds=['xh','xm','xymc','zymc','bjmc','ldmc','qsh'];
	var lddm = jQuery("#lddm").val();
	showDialog('��ѡ��һ��ѧ��',680,480,'gygl_lfrydj.do?method=showStudents&elementIds='
	+elementIds+'&lddm='+lddm);
}

/**
 * �ı�¥��ʱ����ձ�������Ϣ
 */
function emptyBfrxx(){
	var elementIds=['xh','xm','xymc','zymc','bjmc','ldmc','qsh'];
	jQuery("#"+elementIds[0]).val("");
	for(var i=1;i<elementIds.length;i++){
		jQuery("#"+elementIds[i]).empty();
	}
}

