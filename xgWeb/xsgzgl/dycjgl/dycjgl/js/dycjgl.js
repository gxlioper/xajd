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
function bjmdfswh() {
    var rows = jQuery("#dataTable").getSeletRow();
    document.location.href="dycjwh_dycjgl.do?method=bjmdfswh&bjdm="+rows[0]["bjdm"];
}

/**
 * �鿴
 */
function viewBjmd() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
    } else {
        var url = "dycjwh_dycjgl.do?method=viewBjmd&bjdm=" + rows[0]["bjdm"];
        showDialog("�鿴", 1043., 638, url);
    }
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

		var url = 'ztbhgl_ztbhsq.do?method=ztbhSqEdit&sqid=' + rows[0]["sqid"];
		var title = "�������޸�";
		showDialog(title, 800, 550, url);
	}

}




//��鿴
function hdLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='View(\""
        + rowObject['sqid'] + "\");'>" + cellValue
        + "</a>";
}


function View(sqid) {
    showDialog("���Ϣ", 900, 450, "ztbhgl_ztbhsq.do?method=getHdInfo&sqid=" + sqid );
}

/**
 * ���ص����ɼ�����ģ��
 */
function downloadExcl(){
    var bjdm = jQuery("#bjdm").val();
    var url = "dycjwh_dycjgl.do?method=downloadExcel&bjdm="+bjdm;
    jQuery("#DycjglForm").attr("target","_blank");
    jQuery("#DycjglForm").attr("action",url);
    jQuery("#DycjglForm").submit();

}



/**
 * ���ز����ɼ�����ģ��
 */
function downloadExclBhg(){
    var bjdm = jQuery("#bjdm").val();
    var url = "dycjwh_dycjgl.do?method=downloadExclBhg&bjdm="+bjdm;
    jQuery("#DycjglForm").attr("target","_blank");
    jQuery("#DycjglForm").attr("action",url);
    jQuery("#DycjglForm").submit();

}


/**
 * ���� �����ɼ�--�ϴ��ļ�
 * @returns {Boolean}
 */
function uploadDycj(){
    var file = jQuery("#importFile").val();
    if (file == "")
        return false;
    if (file.substring(file.length-4,file.length) != ".xls"){
        showAlert("�����ļ�ֻ��Ϊ.xls��ʽ,��ȷ�ϣ�");
        return false;
    }
    url = "dycjwh_dycjgl.do?method=importDycj";
    jQuery("#DycjglForm").attr("target","");
    jQuery("#DycjglForm").attr("action",url).submit();
    if (parent.window){
        var api = frameElement.api,W = api.opener;
        W.jQuery("#dataTable").reloadGrid();
    }
}

/**
 * ���� �����ɼ����ϸ�--�ϴ��ļ�
 * @returns {Boolean}
 */
function uploadBhg(){
    var file = jQuery("#importFile").val();
    if (file == "")
        return false;
    if (file.substring(file.length-4,file.length) != ".xls"){
        showAlert("�����ļ�ֻ��Ϊ.xls��ʽ,��ȷ�ϣ�");
        return false;
    }
    url = "dycjwh_dycjgl.do?method=importDycjBhg";
    jQuery("#DycjglForm").attr("target","");
    jQuery("#DycjglForm").attr("action",url).submit();
    if (parent.window){
        var api = frameElement.api,W = api.opener;
        W.jQuery("#dataTable").reloadGrid();
    }
}





