var checkId = 'xh-hdmc-dd-sj-zbdw-ddssx';
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}


function yyhLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='view(\""
        + rowObject["yyid"]+"\");'>" + cellValue
        + "</a>";
}
function view(yyid) {
    var height = jQuery(window).height();
    showDialog("ԤԼ��Ϣ�鿴", 800, height-250, "xyfd_fqyy.do?method=viewYy&yyid="+yyid+"&t="+new Date().getTime());
}

function jsLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='viewJs(\""
        + rowObject["fdjs"]+"\");'>" + cellValue
        + "</a>";
}
function viewJs(fdjs) {
    var height = jQuery(window).height();
    if(fdjs.substr(0,2)=='PB'){
        showDialog("�γ���Ϣ�鿴", 800, height-250, "xyfd_pbjg.do?method=viewPbjg&djh="+fdjs+"&t="+new Date().getTime());
    }else {
        showDialog("�γ���Ϣ�鿴", 800, height-250, "xyfd_fdjswh.do?method=fdjsView&djh="+fdjs+"&t="+new Date().getTime());
    }
}
function kcLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='viewKc(\""
        + rowObject["jgid"]+"\");'>" + cellValue
        + "</a>";
}
function viewKc(jgid) {
    var height = jQuery(window).height();
    showDialog("�γ���Ϣ�鿴", 800, height-250, "xyfd_fdkcjg.do?method=viewFdkcjg&jgid="+jgid+"&t="+new Date().getTime());
}

function add(yysf) {
    var height = jQuery(window).height();
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����ҪԤԼ�Ŀγ̣�");
    }
    var url = "xyfd_fqyy.do?method=addYy&yysf="+yysf+"&fdkc="+rows[0]['jgid'];
    showDialog("�����γ�����", 800, height-250, url);
}
function update() {
    var sqkg = jQuery("#sqkg").val();
    var rows = jQuery("#dataTable").getSeletRow();
    var height = jQuery(window).height();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
    } else {
        var userName = jQuery("#userName").val();
        if(rows[0]['yyr']=='����'){
            if(rows[0]['xh']!=userName){
                showAlertDivLayer("�����������ԤԼ��");
                return false;
            }
        }else{
            if(rows[0]['yhm']!=userName){
                showAlertDivLayer("�����������ԤԼ��");
                return false;
            }
        }
        var shzt = rows[0]["zt"];
        if ("0" != shzt&&"3" != shzt) {
            showAlertDivLayer("ֻ��δ�ύ�����˻صļ�¼�����޸ģ�");
            return false;
        }

        var url = 'xyfd_fqyy.do?method=updateYy&yyid=' + rows[0]["yyid"];
        var title = "�޸ĸ���ԤԼ";
        showDialog(title, 800, height-250, url);
    }

}

// ɾ��
function del() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length == 0) {
        showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
    } else if (rows[0]["zt"] != "0" && rows[0]["zt"] != "3") {
        showAlertDivLayer("ֻ��ɾ��δ�ύ�������˻صļ�¼��");
        return false;
    } else {
        var userName = jQuery("#userName").val();
        if(rows[0]['yyr']=='����'){
            if(rows[0]['xh']!=userName){
                showAlertDivLayer("�����������ԤԼ��");
                return false;
            }
        }else{
            if(rows[0]['yhm']!=userName){
                showAlertDivLayer("�����������ԤԼ��");
                return false;
            }
        }
        showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
            "okFun" : function() {
                jQuery.post("xyfd_fqyy.do?method=yyDel", {
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
        showAlertDivLayer("��ѡ��һ����Ҫ�ύ��ԤԼ��");
        return false;
    }
    var userName = jQuery("#userName").val();
    if(rows[0]['yyr']=='����'){
        if(rows[0]['xh']!=userName){
            showAlertDivLayer("�����������ԤԼ��");
            return false;
        }
    }else{
        if(rows[0]['yhm']!=userName){
            showAlertDivLayer("�����������ԤԼ��");
            return false;
        }
    }
    if (rows[0]["zt"] != "0") {
        showAlertDivLayer("��ѡ��δ�ύ��ԤԼ��");
        return false;
    }
    showConfirmDivLayer("��ȷ��Ҫ�ύѡ���ԤԼ��", {
        "okFun" : function() {
            jQuery.post("xyfd_fqyy.do?method=submit", {
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
        showAlertDivLayer("��ѡ����Ҫȡ����ԤԼ��");
    } else if (ids.length > 1) {
        showAlertDivLayer("��ѡ��һ����Ҫȡ����ԤԼ��");
    } else {
        var rows = jQuery("#dataTable").getSeletRow();
        for ( var i = 0; i < ids.length; i++) {
            if (rows[i]['zt'] != '5'&&rows[i]['zt'] != '1') {
                showAlertDivLayer("ֻ��ԤԼ�л���ԤԼ�ļ�¼���ܱ�ȡ����");
                return false;
            }
        }
        var userName = jQuery("#userName").val();
        if(rows[0]['yyr']=='����'){
            if(rows[0]['xh']!=userName){
                showAlertDivLayer("�����������ԤԼ��");
                return false;
            }
        }else{
            if(rows[0]['yhm']!=userName){
                showAlertDivLayer("�����������ԤԼ��");
                return false;
            }
        }
        showConfirmDivLayer("��ԤԼ�ģ�" + rows[0]['fdsj'] + "���ڣ�" + rows[0]['fdsdd'] + "���ɣ�" + rows[0]['fdjsxm'] + "�����еģ�" + rows[0]['kcmc'] + "�����Ƿ�ȷ��ȡ����", {
            "okFun": function () {
                if(rows[0]['zt']=='5') {
                    jQuery.post("xyfd_fqyy.do?method=cancel&t=" + new Date().getTime(), {
                        values: ids.toString()
                    }, function (data) {
                        if(data["message"]=='1'){
                            var height = jQuery(window).height();
                            var url = 'xyfd_fqyy.do?method=qxYy&yyid=' + rows[0]["yyid"];
                            showDialog('ȡ��ԤԼԭ��', 600, height-450, url);
                        }else {
                            showAlertDivLayer(data["message"]);
                            jQuery("#dataTable").reloadGrid();
                        }
                    }, 'json');
                }else if(rows[0]['zt'] == '1'){
                    var height = jQuery(window).height();
                    var url = 'xyfd_fqyy.do?method=qxYy&yyid=' + rows[0]["yyid"];
                    showDialog('ȡ��ԤԼԭ��', 600, height-450, url);
                }
            }
        });

    }

}
//���ۿγ�
function pjkc() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    var userName = jQuery("#userName").val();
    if (ids.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ���۵�ԤԼ��");
        return false;
    }
    if(rows[0]['zt']!='4'&&rows[0]['zt']!='6'){
        showAlertDivLayer("�ø���δ��ɣ��޷����ۣ�");
        return false;
    }
    if(rows[0]['xh']!=userName){
        showAlertDivLayer("�����Ǹ�ԤԼ��ѧ�����޷����ۣ�");
        return false;
    }
    var height = jQuery(window).height();
    var url = 'xyfd_fqyy.do?method=pjkc&yyh=' + rows[0]["yyh"];
    showDialog('ȡ��ԤԼԭ��', 600, height-250, url);
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

