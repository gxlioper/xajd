/**
 * �ҽ���ʦ������js
 */

function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}

/**
 * ѧ�Ÿ�ʽ��
 */
function xhLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='jjlsjgShow(\""
        + rowObject["xh"]+"\");'>" + cellValue
        + "</a>";
}

/**
 * �鿴
 */
function jjlsjgShow(xh) {
    var title = jQuery("#gnmkmc").val()+"�鿴";
    var url = "jjgl_jjlsjggl.do?method=jjlsjgShow&xh="+xh;
    showDialog(title, 800, 500,url);
}

/**
 * �����ı���
 */
var checkId = 'xh-jjnj-lxdh';

function jjlsjgSaveForAdd() {
    if(!checkNotNull(checkId)){
        showAlertDivLayer("�뽫��������д������");
        return false;
    }

    if(jQuery("input[name=sckm]:checked").length == 0){
        showAlert("��ѡ���ó���Ŀ��");
        return false;
    }

    if(!isTelephone("lxdh")){
        showAlert("��ϵ�绰��ʽ���Ϸ�");
        return false;
    }

    if (jQuery("#jxxy").val().length>150) {
        showAlert("��ѧ�����������150�֣�");
        return false;
    }
    var url = "jjgl_jjlsjggl.do?method=jjlsjgSaveForAdd";
    ajaxSubFormWithFun("jjlsjgForm", url, function(data) {
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
 * �༭�ı���
 */
function jjlsjgSaveForEdit() {
    if(!checkNotNull(checkId)){
        showAlertDivLayer("�뽫��������д������");
        return false;
    }

    if(jQuery("input[name=sckm]:checked").length == 0){
        showAlert("��ѡ���ó���Ŀ��");
        return false;
    }

    if(!isTelephone("lxdh")){
        showAlert("��ϵ�绰��ʽ���Ϸ�");
        return false;
    }

    if (jQuery("#jxxy").val().length>150) {
        showAlert("��ѧ�����������150�֣�");
        return false;
    }
    var url = "jjgl_jjlsjggl.do?method=jjlsjgSaveForEdit";
    ajaxSubFormWithFun("jjlsjgForm", url, function(data) {
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
 * ���ӵ���ҳ��
 */
function add() {
    var url = "jjgl_jjlsjggl.do?method=jjlsjgAdd";
    var title = jQuery("#gnmkmc").val()+"����";
    showDialog(title, 800, 550, url);
}

/**
 * �޸ĵ���ҳ��
 */
function edit() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
    } else {
        var url = 'jjgl_jjlsjggl.do?method=jjlsjgEdit&xh=' + rows[0]["xh"];
        var title = jQuery("#gnmkmc").val()+"�޸�";
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
            jQuery.post("jjgl_jjlsjggl.do?method=jjlsjgDel", {
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
 * ����
 */
var DCCLBH = "jjgl_jjlsjg.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
    //DCCLBH�������ܱ��,ִ�е�������
    customExport(DCCLBH, exportData);
}

//��������
function exportData() {
    setSearchTj();//���ø߼���ѯ����
    var url = "jjgl_jjlsjggl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}

/**
 * ����
 */
function importConfig() {
    // ����ͨ�õĵ���function�������ǵ��빦��ģ�����
    toImportDataNew("IMPORT_JJLSJG");
    return false;
}

