function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
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
    var url = "dzdy_dmpz.do?method=saveDm";
    ajaxSubFormWithFun("dzbdmpzForm", url, function (data) {
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

function updateSavaDm() {
    var ids = "dm" + "-" + "mc";
    if (!checkNotNull(ids)) {
        return showAlert("�뽫��<font color='red'>*</font>��Ŀ��д������");
    }
    var url = "dzdy_dmpz.do?method=updateSavaDm";
    ajaxSubFormWithFun("dzbdmpzForm", url, function (data) {
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
 * ɾ�����ֱ�׼
 * @return
 */
function del() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
    } else {
        var dms = new Array();
        for (var i = 0; i < rows.length; i++) {
            dms.push(rows[i]['dm']);
        }
        var para = {
            dms: dms
        };
        showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
            "okFun": function () {
                jQuery.post("dzdy_dmpz.do?method=delDm", para, function (data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
            }
        });
    }
}

/**
 * ���ػ����趨����ѯҳ��
 * @return
 */
function fhjcsd() {
    document.location.href = "xg_gyjc_jcsd.do";
}

/**
 * ����
 * @return
 */
function addPage() {
    var url = "dzdy_dmpz.do?method=addDmpz";
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
    var url = "dzdy_dmpz.do?method=update&dm=" + rows[0]['dm'];
    showDialog("������Ŀ", 550, 400, url);
}


jQuery("#selfzr").click(function () {


});

function getSy() {
    var url = "dzdy_dmpz.do?method=getSy";
    var title = "��Ժѡ��";
    showDialog(title, 770, 520, url);
}


function choseSy() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("��ѡ��һ����Ժ��");
        return false;
    }
    var api = frameElement.api;
    var parentsW = api.get('parentDialog');
    parentsW.jQuery("#sydm").val(rows[0]['sydm']);
    parentsW.jQuery("#symc").val(rows[0]['symc']);
    closeDialog();
}


//dcglbh,�������ܱ��
var DCGLBH = "zhdj_dzdy_dmpz.do";

//�Զ��嵼�� ����
function exportConfig() {
    //DCCLBH�������ܱ��,ִ�е�������
    customExport(DCGLBH, exprotData);
}

//��������
function exprotData() {
    var url = "dzdy_dmpz.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,�������ܱ��
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}

function hjDzz() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("��ѡ��һ����¼��");
        return false;
    }
    var url = "dzdy_dmpz.do?method=hjDzz&dm=" + rows[0]["dm"];
    var title = "����֧����";
    showDialog(title, 770, 552, url);
}

function hjAddDzz() {
    var hjsjOld = jQuery("#hjsjOld").val();
    var hjsj = jQuery("#hjsj").val();
    if (hjsjOld == hjsj) {
        showAlertDivLayer("���ܸ�֮ǰ����ʱ����ͬ��");
        return false;
    }
    var url = "dzdy_dmpz.do?method=hjAddDzz";
    ajaxSubFormWithFun("dzbdmpzForm", url, function (data) {
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

function ljDzb() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("��ѡ��һ����¼��");
        return false;
    }
    var url = "dzdy_dmpz.do?method=ljDzz&dm=" + rows[0]["dm"];
    var title = "������Ϣ";
    showDialog(title, 770, 552, url);
}
