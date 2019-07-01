function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}

/**
 * ����
 * @return
 */
function addPage() {
    var url = "dzdy_zwwh.do?method=addZw";
    showDialog("����", 550, 400, url);
}

/**
 * �޸�
 * @return
 */
function update() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        return showAlertDivLayer("����ֻ��ѡ��һ����¼��");
    }
    var url = "dzdy_zwwh.do?method=update&dm=" + rows[0]['dm'];
    showDialog("������Ŀ", 550, 400, url);
}


/**
 * �������ֱ�׼
 * @return
 */
function saveDm() {
    var ids = "dm" + "-" + "mc";
    if (!checkNotNull(ids)) {
        return showAlert("�뽫��<font color='red'>*</font>��Ŀ��д������");
    }
    var url = "dzdy_zwwh.do?method=saveDm";
    ajaxSubFormWithFun("DzbzwwhForm", url, function (data) {
        if (data["message"] == "����ɹ���") {
            showAlert(data["message"], {}, {
                "clkFun": function () {
                    var api = frameElement.api, W = api.opener;
                    W.jQuery("#dataTable").reloadGrid();
                    closeDialog();
                }
            });
        } else {
            showAlert(data["message"]);
        }
    });
}

function updateSaveDm() {
    var ids = "dm" + "-" + "mc";
    if (!checkNotNull(ids)) {
        return showAlert("�뽫��<font color='red'>*</font>��Ŀ��д������");
    }
    var url = "dzdy_zwwh.do?method=updateSavaDm";
    ajaxSubFormWithFun("DzbzwwhForm", url, function (data) {
        if (data["message"] == "����ɹ���") {
            showAlert(data["message"], {}, {
                "clkFun": function () {
                    var api = frameElement.api, W = api.opener;
                    W.jQuery("#dataTable").reloadGrid();
                    closeDialog();
                }
            });
        } else {
            showAlert(data["message"]);
        }
    });
}

/**
 * ɾ��
 * @return
 */
function delZw() {
    var row = jQuery("#dataTable").getSeletRow();
    if (row.length == 0) {
        showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
    } else {
        var dms = new Array();
        var mcs = new Array();
        for (var i = 0; i < row.length; i++) {
            dms.push(row[i]['dm']);
            mcs.push(row[i]['mc']);
        }
        var para = {
            dms: dms,
            mcs: mcs
        };
        showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
            "okFun": function () {
                jQuery.post("dzdy_zwwh.do?method=delZw", para, function (data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
            }
        });
    }
}
