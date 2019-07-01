
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='view(\""
        + rowObject["id"]+"\");'>" + cellValue
        + "</a>";
}

function view(id) {
    showDialog("���γɼ��鿴",  600, 400, "dtjs_dkcj.do?method=dkcjView&id="+id);
}


function save(type) {
    var ids = "xh-dkcj-kssj";
    if(!checkNotNull(ids)){
        showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
        return false;
    }
    var url = "dtjs_dkcj.do?method=save&type=" + type;
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

function add(){
    var url = "dtjs_dkcj.do?method=dkcjAdd";
    var title = "����ѧ�����γɼ�";
    showDialog(title, 600, 400, url);
}
function update(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
    }else{
        var url = 'dtjs_dkcj.do?method=dkcjUpdate&id=' + rows[0]["id"];
        var title = "���γɼ��޸�";
        showDialog(title,  600, 400, url);
    }
}

// ɾ��
function del() {
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length == 0) {
        showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
    } else {
        showConfirmDivLayer("�Ƿ�ȷ��ɾ����ѡ�ļ�¼��", {
            "okFun" : function() {
                var url = "dtjs_dkcj.do?method=dkcjDel";
                jQuery.post(url, {
                    values : ids.toString()
                }, function(data) {
                    if (data["success"] == false) {
                        showAlertDivLayer(data["message"]);
                    } else {
                        showAlertDivLayer(data["message"], {}, {
                            "clkFun" : function(tag) {
                                jQuery("#dataTable").reloadGrid();
                            }
                        });
                    }
                }, 'json');

            }});
    }
}


var DCCLBH = "dtjs_rdjjfzpy_dkcj.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
    //DCCLBH�������ܱ��,ִ�е�������
    customExport(DCCLBH, exportData);
}

//��������
function exportData() {
    setSearchTj();//���ø߼���ѯ����
    var url = "dtjs_dkcj.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}


//����
function dr1() {
    // ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
    toImportDataNew("IMPORT_DTJS_DKCJ");
    return false;

}