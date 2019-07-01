/** ���汨��js */

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
    var url = "rcsw_xsgzqkbb_cgbbgl.do?method=cgbbAdd";
    var title = jQuery("#gnmkmc").val()+"����";
    showDialog(title, 800, 550, url);
}

/**
 *  ���ӵı���
 */
function saveForAdd() {
    var checkId = 'xn-xq-bszt-bsnr';
    if(!checkNotNull(checkId)){
        showAlertDivLayer("�뽫��������д������");
        return false;
    }
    if (jQuery("#bsnr").val().length>2000) {
        showAlert("���������������2000�֣�");
        return false;
    }
    var url = "rcsw_xsgzqkbb_cgbbgl.do?method=cgbbSaveForAdd";
    ajaxSubFormWithFun("xsgzqkCgbbForm", url, function(data) {
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
        var url = 'rcsw_xsgzqkbb_cgbbgl.do?method=cgbbEdit&id=' + rows[0]["id"];
        var title = jQuery("#gnmkmc").val()+"�޸�";
        showDialog(title, 800, 550, url);
    }
}

/**
 *  �޸ĵı���
 */
function saveForEdit() {
    var checkId = 'xn-xq-bszt-bsnr';
    if(!checkNotNull(checkId)){
        showAlertDivLayer("�뽫��������д������");
        return false;
    }
    if (jQuery("#bsnr").val().length>2000) {
        showAlert("���������������2000�֣�");
        return false;
    }
    var url = "rcsw_xsgzqkbb_cgbbgl.do?method=cgbbSaveForEdit";
    ajaxSubFormWithFun("xsgzqkCgbbForm", url, function(data) {
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
        var url = 'rcsw_xsgzqkbb_cgbbgl.do?method=cgbbView&id=' + rows[0]["id"];
        var title = jQuery("#gnmkmc").val()+"�鿴";
        showDialog(title, 800, 550, url);
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
            jQuery.post("rcsw_xsgzqkbb_cgbbgl.do?method=cgbbDel", {
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
var DCGLBH = "rcsw_xsgzqkbb_cgbb.do";

//�Զ��嵼�� ����
function exportConfig() {
    //DCCLBH�������ܱ��,ִ�е�������
    customExport(DCGLBH, exprotData);
}

//��������
function exprotData() {
    setSearchTj();//���ø߼���ѯ����
    var url = "rcsw_xsgzqkbb_cgbbgl.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}

//����
function importConfig(){
    toImportDataNew("IMPORT_XSGZQK_CGBB");
    return false;
}