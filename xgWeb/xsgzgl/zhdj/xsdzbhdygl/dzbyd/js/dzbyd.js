function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}

//�鿴
function dzbydLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='View(\""
        + rowObject['xh'] + "\");'>" + cellValue
        + "</a>";
}

function View(xh) {
    showDialog("��֧���춯��Ϣ", 900, 450, "dzdy_dzbyd.do?method=getDzbydInfo&xh=" + xh);
}

function updateDzbyd() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("��ѡ��һ����¼��");
        return false;
    }
    var url = "dzdy_dzbyd.do?method=updateDzbyd&xh=" + rows[0]["xh"];
    var title = "�޸ĵ�֧���춯��Ϣ";
    showDialog(title, 770, 552, url);
}

function bc() {
    var url = "dzdy_dzbyd.do?method=bc";
    ajaxSubFormWithFun("DzbydForm", url, function (data) {
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

function delDzbyd() {
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length == 0) {
        showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
        return false;
    }
    showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
        "okFun": function () {
            jQuery.post("dzdy_dzbyd.do?method=del", {values: ids.toString()}, function (data) {
                showAlertDivLayer(data["message"]);
                jQuery("#dataTable").reloadGrid();
            }, 'json');
        }
    });
}

//dcglbh,�������ܱ��
var DCGLBH = "zhdj_dzdy_dzbyd.do";

//�Զ��嵼�� ����
function exportConfig() {
    //DCCLBH�������ܱ��,ִ�е�������
    customExport(DCGLBH, exprotData);
}

//��������
function exprotData() {
    setSearchTj();//���ø߼���ѯ����
    var url = "dzdy_dzbyd.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}


//����
function importConfig() {
    toImportDataNew("IMPORT_DZBYDXX");
    return false;
}


/**
 * �����¿�ܵ��빦�����
 *
 * @param drmkdm ���빦�ܴ���
 * @return
 */
function toImportDataNew(drmkdm) {
    var _SSO_DR_PATH = 'out_access.do?gnbh=import&toPage=/xgweb/dr/out_login.html';
    if (drmkdm == null || drmkdm == undefined || jQuery.trim(drmkdm) == "") {
        alert("����ģ�����δ����!");
        return false;
    }
    var url = _SSO_DR_PATH + "&drmkdm=" + drmkdm;
    showDialog('����', 720, 580, url, {
        close: function () {
            if (jQuery("#search_go")) {
                jQuery("#search_go").click();
            }
        }
    });
    return false;
}

