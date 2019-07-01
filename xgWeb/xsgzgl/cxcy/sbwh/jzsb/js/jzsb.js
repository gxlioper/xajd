function view(id) {
    showDialog("�����ϱ��鿴", 550, 450, "cxcy_jzsb.do?method=jzsbView&id="+id);
}
function add() {
    var url = "cxcy_jzsb.do?method=jzsbAdd";
    var title = "�����ϱ�����";
    showDialog(title, 550, 450, url);
}

function update() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
    }else {
        var url = 'cxcy_jzsb.do?method=jzsbUpdate&id=' + rows[0]["id"];
        var title = "�����ϱ��޸�";
        showDialog(title, 550, 450, url);
    }

}

// ɾ��
function del() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length == 0) {
        showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
    } else {
        showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
            "okFun" : function() {
                jQuery.post("cxcy_jzsb.do?method=jzsbDel", {
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
function save(type) {
    var ids = "jzmc-zjr-jzsj-jzdd-sknr";
    if(check(ids) == false){
        showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
        return false;
    }
    var url = "cxcy_jzsb.do?method=save&type=" + type;
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


/**
 * ��֤�Ƿ���ڿ���
 * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function check(ids){
    var id=ids.split("-");
    for(var i=0;i<id.length;i++){
        var lddm=jQuery("#"+id[i]).val();
        if(lddm==null||""==lddm){
            return false;
        }
    }
    return true;
}
function exportConfig(){
    var DCCLBH='cxcy_sbwh_jzsb.do';
    customExport(DCCLBH, exportData);
}
function exportData(){
    var DCCLBH='cxcy_sbwh_jzsb.do';
    setSearchTj();//���ø߼���ѯ����
    var url = "cxcy_jzsb.do?method=exportData&dcclbh=" + DCCLBH+
        "&pkValue="+jQuery("#pkValue").val();//dcclbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}
