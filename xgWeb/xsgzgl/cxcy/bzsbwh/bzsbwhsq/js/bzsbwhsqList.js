jQuery(function(){
    var gridSetting = {
        caption : "���´�ҵ�����걨�б�",
        pager : "pager",
        url : "cxcy_bzsbwhsq.do?method=getList&type=query",
        colList : [
            { label : 'sqid', name : 'sqid', index : 'sqid',key : true, hidden : true },
            { label : 'splc', name : 'splc', index : 'splc',hidden : true },
            { label : 'ѧ��', name : 'xh', index : 'xh', width : '10%',formatter:xhLink },
            { label : '����', name : 'xm', index : 'xm', width : '8%' },
            { label : '��Ŀ����', name : 'xmmc', index : 'xmmc', width : '14%' },
            { label : '������Ԫ��', name : 'bzje', index : 'bzje', width : '9%' },
            { label : 'ѧ��', name : 'xn', index : 'xn', width : '9%' },
            { label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '7%' },
            { label : '�������', name : 'xymc', index : 'xymc', width : '12%' },
            { label : '���', name : 'tbrmc', index : 'tbrmc', width : '8%' },
            { label : '��¼ʱ��', name : 'sqsj', index : 'sqsj', width : '15%' },
            { label : '���״̬', name : 'shztmc', index : 'shztmc', width : '8%' },
            { label : '���״̬', name : 'shzt', index : 'shzt', hidden : true}]
    };
    var map = getSuperSearch();
    gridSetting["params"] = map;
    jQuery("#dataTable").initGrid(gridSetting);
});
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
    showDialog("���´�ҵ�����걨�鿴", 700, 450, "cxcy_bzsbwhsq.do?method=bzsbwhsqView&sqid="+sqid);
}
function add() {
    if(isopen==null||isopen==''){
        showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
        return false;
    }
    if ("false" == isopen){
        showAlertDivLayer("��ǰδ�������룬����ϵ����Ա��");
        return false;
    }
    var url = "cxcy_bzsbwhsq.do?method=bzsbwhsqAdd";
    var title = "�������´�ҵ��������";
    showDialog(title, 700, 550, url);
}

function update() {
    var isopen = jQuery("#isopen").val();
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
    }else {
        var shzt = rows[0]["shzt"];
        if ("0" != shzt&&"3" != shzt) {
            showAlertDivLayer("ֻ��δ�ύ�����˻صļ�¼�����޸ģ�");
            return false;
        }
        if(isopen==null||isopen==''){
            showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
            return false;
        }
        if ("false" == isopen){
            showAlertDivLayer("��ǰδ�������룬����ϵ����Ա��");
            return false;
        }
        var url = 'cxcy_bzsbwhsq.do?method=bzsbwhsqUpdate&sqid=' + rows[0]["sqid"];
        var title = "���´�ҵ�����걨�޸�";
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
                jQuery.post("cxcy_bzsbwhsq.do?method=bzsbwhsqDel", {
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
function submit() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    var isopen = jQuery("#isopen").val();
    if (ids.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
        return false;
    }
    if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
        showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
        return false;
    }
    if(isopen==null||isopen==''){
        showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
        return false;
    }
    if ("false" == isopen){
        showAlertDivLayer("��ǰδ�������룬����ϵ����Ա��");
        return false;
    }
    showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
        "okFun" : function() {
            jQuery.post("cxcy_bzsbwhsq.do?method=bzsbwhsqSubmit", {
                sqid : ids.toString()
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
                jQuery.post("cxcy_bzsbwhsq.do?method=bzsbwhsqCancel", {
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
    showDialog("���´�ҵ�����걨�������̸���",530,310, 'comm_spl.do?method=lcgz&sqid='
        + rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
}

function exportConfig(){
    var DCCLBH='cxcy_bzsbwh.do';
    customExport(DCCLBH, exportData);
}
function exportData(){
    var DCCLBH='cxcy_bzsbwh.do';
    setSearchTj();//���ø߼���ѯ����
    var url = "cxcy_bzsbwhsq.do?method=exportData&dcclbh=" + DCCLBH+
        "&pkValue="+jQuery("#pkValue").val();//dcclbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}
