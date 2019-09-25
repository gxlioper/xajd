var checkId = 'xh-hdmc-dd-sj-zbdw-ddssx';
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='view(\""
        + rowObject["sqid"]+"\");'>" + cellValue
        + "</a>";
}
function view(sqid) {
    showDialog("��־Ը������鿴", 600, 550, "xyfd_fdkcsq.do?method=fdkcsqView&sqid="+sqid+"&t="+new Date().getTime());
}

function add() {
    var url = "xyfd_fdkcsq.do?method=fdkcsqAdd";
    showDialog("�����γ�����", 800, 550, url);
}
function update() {
    var sqkg = jQuery("#sqkg").val();
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
    } else {
        var shzt = rows[0]["shzt"];
        if ("0" != shzt&&"3" != shzt) {
            showAlertDivLayer("ֻ��δ�ύ�����˻صļ�¼�����޸ģ�");
            return false;
        }

        var url = 'xyfd_fdkcsq.do?method=fdkcsqUpdate&sqid=' + rows[0]["sqid"];
        var title = "��־Ը�������޸�";
        showDialog(title, 600, 550, url);
    }

}

// ɾ��
function del() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length == 0) {
        showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
    } else if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
        showAlertDivLayer("ֻ��ɾ��δ�ύ�������˻صļ�¼��");
        return false;
    } else {
        showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
            "okFun" : function() {
                jQuery.post("xyfd_fdkcsq.do?method=fdkcsqDel", {
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

// �ύ
function submitBusi() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
        return false;
    }
    if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
        showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
        return false;
    }
    showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
        "okFun" : function() {
            jQuery.post("xyfd_fdkcsq.do?method=submit", {
                values : ids.toString()
            }, function(data) {
                showAlertDivLayer(data["message"]);
                jQuery("#dataTable").reloadGrid();
            }, 'json');
        }
    });



}
// ����
function cancel() {
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length == 0) {
        showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
    } else if (ids.length > 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
    } else {
        var rows = jQuery("#dataTable").getSeletRow();
        for ( var i = 0; i < ids.length; i++) {
            if (rows[i]['shzt'] != '5') {
                showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
                return false;
            }
        }

        showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
            "okFun" : function() {
                jQuery.post("xyfd_fdkcsq.do?method=cancel&t="+new Date().getTime(), {
                    values : ids.toString(),
                    splcid : rows[0]['splc']
                }, function(data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
            }
        });
    }

}
/*
 * ���̸���
 */
function lcgz() {
    var rows = jQuery("#dataTable").getSeletRow();

    if (1 != rows.length) {
        showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
        return false;
    }
    var shzt = rows[0]["shzt"];
    if ("0" == shzt) {
        showAlertDivLayer("�ü�¼Ϊδ�ύ״̬�������ύ��");
        return false;
    }
    showDialog("���Ż�Ǽ��������̸���",530,310, 'comm_spl.do?method=lcgz&sqid='
        + rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
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

