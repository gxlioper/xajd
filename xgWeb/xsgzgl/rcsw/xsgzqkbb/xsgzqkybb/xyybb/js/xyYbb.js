/** ѧԺ�±���js */

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
    var url = "rcsw_xsgzqkbb_xyybbgl.do?method=xyYbbAdd";
    var title = jQuery("#gnmkmc").val()+"����";
    showDialog(title, 750, 300, url);
}

/**
 *  ���ӵı���
 */
function saveForAdd() {
    var checkId = 'xsgzfzrmc-xn-xq';
    if(!checkNotNull(checkId)){
        showAlertDivLayer("�뽫��������д������");
        return false;
    }

    var url = "rcsw_xsgzqkbb_xyybbgl.do?method=xyYbbSaveForAdd";
    ajaxSubFormWithFun("xsgzqkXyYbbForm", url, function(data) {
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
 * ��д
 */
function fillin() {
    var rows = jQuery("#dataTable").getSeletRow();

    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ��д�ļ�¼��");
    } else {
        var url = 'rcsw_xsgzqkbb_bjybbgl.do?method=bjYbbList&xyybbid=' + rows[0]["id"];
        window.open(url,"_self");
    }
}

/**
 * ��дҳ������
 */
function showFillin(id) {
    var url = 'rcsw_xsgzqkbb_bjybbgl.do?method=bjYbbList&xyybbid=' + id;
    window.open(url,"_self");
}

/**
 * �޸�
 */
function edit() {
    var rows = jQuery("#dataTable").getSeletRow();

    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
    } else {
        var url = 'rcsw_xsgzqkbb_xyybbgl.do?method=xyYbbEdit&id=' + rows[0]["id"];
        var title = jQuery("#gnmkmc").val()+"�޸�";
        showDialog(title, 750, 300, url);
    }
}

/**
 *  �޸ĵı���
 */
function saveForEdit() {
    var checkId = 'xsgzfzrmc-xn-xq';
    if(!checkNotNull(checkId)){
        showAlertDivLayer("�뽫��������д������");
        return false;
    }

    var url = "rcsw_xsgzqkbb_xyybbgl.do?method=xyYbbSaveForEdit";
    ajaxSubFormWithFun("xsgzqkXyYbbForm", url, function(data) {
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
            jQuery.post("rcsw_xsgzqkbb_xyybbgl.do?method=xyYbbDel", {
                    values : ids.toString()
                },
                function(data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
        }
    });
}

/**
 *  ��λ�������Զ���ȫ��ʼ��
 */
/*function initXsgzfzrmcAutocomplete() {
    //ȡ�����ݱ�fdyxxb; �ֶΣ�xm
    var autoSetting = {
        dataTable:"fdyxxb",
        dataField:"xm",
        dataFieldKey:"zgh",
        dataFieldKeyId:"xsgzfzr",
        sqlTj: getSqlTj,
        scrollHeight:135
    }
    // ģ��������������λ�����ˡ�
    jQuery("#xsgzfzrmc").setAutocomplete(autoSetting);
}*/

/**
 * ת���༶�±����б�
 */
function ytbjrsbLink(cellValue,rowObject){
    var id = rowObject["id"];
    return "<a href='javascript:void(0);' onclick=\"showFillin('"+id+"')\" class='name'>"+cellValue+"</a>";
}

// ѡ���ʦ�󣬻ص�����
function showFdysNotF5CallBack(rowData) {
    jQuery("#xsgzfzr").val(rowData["zgh"]);
    jQuery("#xsgzfzrmc").val(rowData["xm"]);
}