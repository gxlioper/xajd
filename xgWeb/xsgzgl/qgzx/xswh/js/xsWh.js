//ѧ��ѧ�ŵ���¼�
function xhLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='xsxxView(\"" + cellValue + "\");'>" + cellValue
        + "</a>";
}

function xsxxView(xh) {
    showDialog("ѧ����Ϣ", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh);
}

//����ѧ��
function add() {

    showDialog('ѡ��ѧ��', 800, 600, 'qgzx_xsgl.do?method=getStu');
}

//ɾ��
function del() {
    var ids = jQuery("#dataTable").getSeletIds();

    if (ids.length == 0) {

        showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
    } else {

        showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
            "okFun": function () {
                jQuery.post("qgzx_xsgl.do?method=deleteQgxs", {values: ids.toString()}, function (data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
            }
        });
    }
}

//�޸�
function xg() {
    var rows = jQuery("#dataTable").getSeletRow();
    var xhs = jQuery("#dataTable").getSeletIds();
    if (rows.length == 0) {
        showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
    } else if (rows.length == 1) {
        var url = 'qgzx_xsgl.do?method=updateSfgmbx&xh=' + rows[0]["xh"];
        var title = "�Ƿ�����";
        showDialog(title, 400, 200, url);
    } else {
        showConfirmDivLayer("��ȷ��Ҫ����ѡѧ�����Ƿ�������Ϊ�ǣ�", {
            "okFun": function () {
                jQuery.post("qgzx_xsgl.do?method=plUpdate", {values: xhs.toString()}, function (data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
            }
        });
    }
}

var DCCLBH = "qgzx_xsgl_dcqgry.do";//dcclbh,�������ܱ��
//����
function dc(){
    customExport(DCCLBH, qgryExportData);
}
//��������
function qgryExportData() {
    setSearchTj();//���ø߼���ѯ����
    var url = "qgzx_xsgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}

//����
function importConfig(){
    toImportDataNew("IMPORT_QGZX_QGRY");
    return false;
}

//����ѧ��
function zjBcStu() {
    var ids = jQuery("#dataTable").getSeletIds();

    if (ids.length == 0) {

        alertInfo("�빴ѡ��Ҫ��ӵ����ݣ�", function (tag) {
            if (tag == "ok") {
                return false;
            }
        });
    } else {
        showConfirmDivLayer("��ȷ��Ҫ���ѡ��ļ�¼��", {
            "okFun": function () {
                jQuery.post("qgzx_xsgl.do?method=addQgxs", {values: ids.toString()}, function (data) {
                    showAlertDivLayer(data["message"], {}, {
                        "clkFun": function () {
                            if (parent.window) {
                                refershParent();
                            }
                        }
                    });
                }, 'json');
            }
        });
    }
}