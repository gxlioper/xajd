/** �༶�±���js */

/**
 * ��ѯ
 */
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}

/**
 * ����ѧԺ�±����б�
 */
function fhxyYbbList() {
    var url = "rcsw_xsgzqkbb_xyybb.do";
    window.open(url,"_self");
}

/**
 * ����
 */
function add() {
    var xyybbid = jQuery("#xyybbid").val();
    var url = "rcsw_xsgzqkbb_bjybbgl.do?method=bjYbbAdd&xyybbid="+xyybbid;
    var title = jQuery("#gnmkmc").val()+"����";
    showDialog(title, 800, 570, url);
}

/**
 *  ���ӵı���
 */
function saveForAdd() {
    var checkId = 'bjmc-mxss-wxss-zkbhcs-bjhdkzcs-srsscs-ssthcs-gbtkcs-yjzlxqk-tfsjclqk-xxrs-fxrs-txrs-qtrs';
    if(!checkNotNull(checkId)){
        showAlertDivLayer("�뽫��������д������");
        return false;
    }

    var url = "rcsw_xsgzqkbb_bjybbgl.do?method=bjYbbSaveForAdd";
    ajaxSubFormWithFun("xsgzqkBjYbbForm", url, function(data) {
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
        var url = 'rcsw_xsgzqkbb_bjybbgl.do?method=bjYbbEdit&id=' + rows[0]["id"];
        var title = jQuery("#gnmkmc").val()+"�޸�";
        showDialog(title, 800, 570, url);
    }
}

/**
 *  �޸ĵı���
 */
function saveForEdit() {
    var checkId = 'mxss-wxss-zkbhcs-bjhdkzcs-srsscs-ssthcs-gbtkcs-yjzlxqk-tfsjclqk-xxrs-fxrs-txrs-qtrs';
    if(!checkNotNull(checkId)){
        showAlertDivLayer("�뽫��������д������");
        return false;
    }

    var url = "rcsw_xsgzqkbb_bjybbgl.do?method=bjYbbSaveForEdit";
    ajaxSubFormWithFun("xsgzqkBjYbbForm", url, function(data) {
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
            jQuery.post("rcsw_xsgzqkbb_bjybbgl.do?method=bjYbbDel", {
                    values : ids.toString(),
                },
                function(data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
        }
    });
}

//dcglbh,�������ܱ��
var DCGLBH = "rcsw_xsgzqkbb_bjybb.do";

//�Զ��嵼�� ����
function exportConfig() {
    //DCCLBH�������ܱ��,ִ�е�������
    customExport(DCGLBH, exprotData);
}

//��������
function exprotData() {
    setSearchTj();//���ø߼���ѯ����
    var xyybbid = jQuery("#xyybbid").val();
    var url = "rcsw_xsgzqkbb_bjybbgl.do?method=exportData&xyybbid="+xyybbid+"&dcglbh=" + DCGLBH;//dcglbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}
