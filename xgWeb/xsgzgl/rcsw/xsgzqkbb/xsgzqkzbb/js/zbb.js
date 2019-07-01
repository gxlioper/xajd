/** �ܱ���js */

/**
 * ��ѯ
 */
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}

/**
 * ����
 */
function add() {
    var zcListSize = jQuery("#zcListSize").val();
    if(zcListSize == "0"){
        showAlertDivLayer("ѧ������δ��ʼ��������ϵ����Ա��");
        return false;
    }
    var url = "rcsw_xsgzqkbb_zbbgl.do?method=zbbAdd";
    var title = jQuery("#gnmkmc").val()+"����";
    showDialog(title, 800, 570, url);
}

/**
 *  ���ӵı���
 */
function saveForAdd() {
    var checkId = 'lxdh-dwfzrmc-xn-xq-zc-xxzt-xxnr-clqk';
    if(!checkNotNull(checkId)){
        showAlertDivLayer("�뽫��������д������");
        return false;
    }
    if (jQuery("#xxnr").val().length>2000) {
        showAlert("��Ϣ�����������2000�֣�");
        return false;
    }
    if (jQuery("#clqk").val().length>2000) {
        showAlert("��������������2000�֣�");
        return false;
    }
    if (jQuery("#bz").val().length>2000) {
        showAlert("��ע�������2000�֣�");
        return false;
    }
    //��֤��ϵ�绰
    if(!isTelephone("lxdh")){
        showAlert("��ϵ�绰��ʽ���Ϸ���");
        return false;
    }
    var url = "rcsw_xsgzqkbb_zbbgl.do?method=zbbSaveForAdd";
    ajaxSubFormWithFun("xsgzqkZbbForm", url, function(data) {
        if(data["message"]=="����ɹ���"){
            showAlert(data["message"],{},{"clkFun":function(){
                if (parent.window){
                    refershParent();
                }
            }});
        }else{
            showAlert(data["message"]);
        }
    });
}

/**
 * �޸�
 */
function edit() {
    var rows = jQuery("#dataTable").getSeletRow();

    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
    } else {
        var url = 'rcsw_xsgzqkbb_zbbgl.do?method=zbbEdit&id=' + rows[0]["id"];
        var title = jQuery("#gnmkmc").val()+"�޸�";
        showDialog(title, 800, 560, url);
    }
}

/**
 *  �޸ĵı���
 */
function saveForEdit() {
    var checkId = 'lxdh-dwfzrmc-xn-xq-zc-xxzt-xxnr-clqk';
    if(!checkNotNull(checkId)){
        showAlertDivLayer("�뽫��������д������");
        return false;
    }
    if (jQuery("#xxnr").val().length>2000) {
        showAlert("��Ϣ�����������2000�֣�");
        return false;
    }
    if (jQuery("#clqk").val().length>2000) {
        showAlert("��������������2000�֣�");
        return false;
    }
    if (jQuery("#bz").val().length>2000) {
        showAlert("��ע�������2000�֣�");
        return false;
    }
    //��֤��ϵ�绰
    if(!isTelephone("lxdh")){
        showAlert("��ϵ�绰��ʽ���Ϸ���");
        return false;
    }
    var url = "rcsw_xsgzqkbb_zbbgl.do?method=zbbSaveForEdit";
    ajaxSubFormWithFun("xsgzqkZbbForm", url, function(data) {
        if(data["message"]=="����ɹ���"){
            showAlert(data["message"],{},{"clkFun":function(){
                if (parent.window){
                    refershParent();
                }
            }});
        }else{
            showAlert(data["message"]);
        }
    });
}

/**
 * �鿴
 */
function view() {
    var rows = jQuery("#dataTable").getSeletRow();

    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
    } else {
        var url = 'rcsw_xsgzqkbb_zbbgl.do?method=zbbView&id=' + rows[0]["id"];
        var title = jQuery("#gnmkmc").val()+"�鿴";
        showDialog(title, 800, 560, url);
    }
}

/**
 * ɾ��
 */
function del() {
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length == 0) {
        showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
        return false;
    }

    showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
        "okFun" : function() {
            jQuery.post("rcsw_xsgzqkbb_zbbgl.do?method=zbbDel", {
                    values : ids.toString()
                },
                function(data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
        }
    });
}

//dcglbh,�������ܱ��
var DCGLBH = "rcsw_xsgzqkbb_zbb.do";

//�Զ��嵼�� ����
function exportConfig() {
    //DCCLBH�������ܱ��,ִ�е�������
    customExport(DCGLBH, exprotData);
}

//��������
function exprotData() {
    setSearchTj();//���ø߼���ѯ����
    var url = "rcsw_xsgzqkbb_zbbgl.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}

//����
function importConfig(){
    toImportDataNew("IMPORT_XSGZQK_ZBB");
    return false;
}

/**
 *  ��λ�������Զ���ȫ��ʼ��
 */
/*function initDwfzrmcAutocomplete() {
    //ȡ�����ݱ�fdyxxb; �ֶΣ�xm
    var autoSetting = {
        dataTable:"fdyxxb",
        dataField:"xm",
        dataFieldKey:"zgh",
        dataFieldKeyId:"dwfzr",
        sqlTj: getSqlTj,
        scrollHeight:135
    }
    // ģ��������������λ�����ˡ�
    jQuery("#dwfzrmc").setAutocomplete(autoSetting);
}*/

/**
 * ѡ��λ������
 */
/*function changeDwfzrmc(){
    // ����
    jQuery("#dwfzrmc").val("");
    jQuery("#dwfzr").val("");

}*/

/**
 * �����ܴ���ʾ�ܴ���ֹ����
 */
function changeZcksjsrq(){
    var zcObj = jQuery("#zc").find("option:selected");
    var html = zcObj.attr("ksrq") + " �� " + zcObj.attr("jsrq");
    jQuery("#zcksjsrq_td").html(html);
    jQuery("#zcksjsrq_hidden").val(html);
}

// ѡ���ʦ�󣬻ص�����
function showFdysNotF5CallBack(rowData) {
    jQuery("#dwfzr").val(rowData["zgh"]);
    jQuery("#dwfzrmc").val(rowData["xm"]);
}