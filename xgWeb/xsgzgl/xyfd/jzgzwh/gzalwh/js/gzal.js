
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}
//
function addGzal() {
    var rows = jQuery("#dataTable").getSeletRow();
    if(rows.length==1){
        if(rows[0]["alzt"]=='0'){
            showAlertDivLayer("�ѳ�����");
            return false;
        }
        var url = "xyfd_gzaljl.do?method=toUpdateGzal&jdh=" + rows[0]["jdh"] + "&t=" + new Date().getTime();
        showDialog("�޸İ������", 800, 550, url);
    }else {
        var url = "xyfd_gzaljl.do?method=addGzal&t=" + new Date().getTime();
        showDialog("�½��������", 800, 550, url);
    }
}

function addGzjl() {
    var rows = jQuery("#dataTable").getSeletRow();
    if(rows.length!=1){
        showAlertDivLayer("��ѡ��һ����ӹ�����¼�İ�����");
        return false;
    }
    var url = "xyfd_gzaljl.do?method=addGzjl&jdh=" + rows[0]["jdh"] + "&t=" + new Date().getTime();
    showDialog("��ӹ�����¼", 800, 350, url);
}
//����
function cd() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
        return false;
    }
    if(rows[0]["alzt"]=='0'){
        showAlertDivLayer("�ѳ�����");
        return false;
    }
    var url = "xyfd_gzaljl.do?method=cd&jdh=" + rows[0]["jdh"] + "&t=" + new Date().getTime();
    showDialog("����", 600, 350, url);
}
//�޸ļ���
function xgjb() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
        return false;
    }
    if(rows[0]["alzt"]=='0'){
        showAlertDivLayer("�ѳ�����");
        return false;
    }
    var url = "xyfd_gzaljl.do?method=xgjb&jdh=" + rows[0]["jdh"] + "&t=" + new Date().getTime();
    showDialog("����", 400, 200, url);
}


// ɾ��
function delAl() {
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length == 0) {
        showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
    } else {
        showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
            "okFun" : function() {
                jQuery.post("xyfd_gzaljl.do?method=delAl", {
                        values : ids.toString()
                    },
                    function(data) {
                        showAlertDivLayer(data["message"]);
                        jQuery("#dataTable").reloadGrid();
                    }, 'json');
            }
        });
    }
}
//�鿴
function ckal() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
        return false;
    }
    var url = "xyfd_gzaljl.do?method=viewGzal&jdh=" + rows[0]["jdh"] + "&t=" + new Date().getTime();
    showDialog("�鿴����", 800, 550, url);
}
//ת��
function zj() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫת��İ�����");
        return false;
    }
    if(rows[0]["sfzj"]=="��"){
        showAlertDivLayer("��ת�飬�����ظ�������");
        return false;
    }
    var url = "xyfd_gzaljl.do?method=alzj&jdh=" + rows[0]["jdh"] + "&t=" + new Date().getTime();
    showDialog("ת��", 500, 250, url);
}
//����
function importConfig(){
    toImportDataNew("IMPORT_XYFD_GZAL");
    return false;
}

//��ӡ����
function printGzal(url){

    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length <=0) {
        showAlertDivLayer("��ѡ��һ����¼��");
    } else {
        var jdh = jQuery("#dataTable").getSeletIds();
        var url = url + "&jdh=" +jdh;
        window.open(url);
    }
}

var DCCLBH = "xyfd_xyfd_fdkcsq.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
    //DCCLBH�������ܱ��,ִ�е�������
    customExport(DCCLBH, exportData);
}

//��������
function exportData() {
    setSearchTj();//���ø߼���ѯ����
    var url = "xyfd_fdkcsq.do?method=export&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}

