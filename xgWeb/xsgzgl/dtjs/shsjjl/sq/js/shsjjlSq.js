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
    showDialog(jQuery("#gnmkmc").val()+"�鿴", 800, 500, "shsjjl_sq.do?method=shsjjlView&sqid="+sqid);
}
function saveAdd(type) {
    var flg=true;
    if(!checkNotNull(checkId)){
        showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����!");
        return false;
    }
    var url = "shsjjl_sq.do?method=saveAdd&type=" + type;
    ajaxSubFormWithFun("form", url, function(data) {
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

//�޸�
function saveUpdate(type) {
    if(!checkNotNull(checkId)){
        showAlertDivLayer("�뽫��������д������");
        return false;
    }
    var url = "shsjjl_sq.do?method=saveUpdate&type=" + type;
    ajaxSubFormWithFun("form", url, function(data) {
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

function add() {
    var sqkg = jQuery("#sqkg").val();
    if ("0" == sqkg) {
        showAlertDivLayer("��ǰ�ѹرգ��������Ա��ϵ��");
        return false;
    }
    var url = "shsjjl_sq.do?method=shsjjlSqAdd";
    var title = jQuery("#gnmkmc").val();
    showDialog(title, 800, 550, url);
}
function update() {
    var sqkg = jQuery("#sqkg").val();
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
    } else {

        var shzt = rows[0]["shzt"];
        if ('3' != rows[0]['shzt'] && "0" == sqkg) {
            showAlertDivLayer("��ǰ�����ѹرգ��������Ա��ϵ��");
            return false;
        }
        if ("0" != shzt&&"3" != shzt) {
            showAlertDivLayer("ֻ��δ�ύ�����˻صļ�¼�����޸ģ�");
            return false;
        }

        var url = 'shsjjl_sq.do?method=shsjjlSqUpdate&sqid=' + rows[0]["sqid"];
        var title = jQuery("#gnmkmc").val()+"�޸�";
        showDialog(title, 800, 550, url);
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
                jQuery.post("shsjjl_sq.do?method=del", {
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
    var flg=true;
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    var sqkg = jQuery("#sqkg").val();
    if (ids.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
        return false;
    }
    if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
        showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
        return false;
    }
    if ('3' != rows[0]['shzt'] && "0" == sqkg) {
        showAlertDivLayer("��ǰ�����ѹرգ��������Ա��ϵ��");
        return false;
    }
    showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
        "okFun" : function() {
            jQuery.post("shsjjl_sq.do?method=submit", {
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
                jQuery.post("shsjjl_sq.do?method=cancel", {
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


var DCCLBH = "dtjs_shsjjl_sq.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
    //DCCLBH�������ܱ��,ִ�е�������
    customExport(DCCLBH, hdsqExportData);
}

//��������
function hdsqExportData() {
    setSearchTj();//���ø߼���ѯ����
    var url = "shsjjl_sq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}