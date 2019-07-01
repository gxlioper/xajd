function view(id) {
    showDialog("��Ŀ�ϱ��鿴", 550, 350, "cxcy_xmsb.do?method=xmsbView&id="+id);
}
function add() {
    var url = "cxcy_xmsb.do?method=xmsbAdd";
    var title = "��Ŀ�ϱ�����";
    showDialog(title, 550, 350, url);
}

function update() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
    }else {
        var url = 'cxcy_xmsb.do?method=xmsbUpdate&id=' + rows[0]["id"];
        var title = "��Ŀ�ϱ��޸�";
        showDialog(title, 550, 350, url);
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
                jQuery.post("cxcy_xmsb.do?method=xmsbDel", {
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
    var ids = "xmmc-bgr-xmjj";
    if(check(ids) == false){
        showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
        return false;
    }
    var url = "cxcy_xmsb.do?method=save&type=" + type;
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
    var DCCLBH='cxcy_sbwh_xmsb.do';
    customExport(DCCLBH, exportData);
}
function exportData(){
    var DCCLBH='cxcy_sbwh_xmsb.do';
    setSearchTj();//���ø߼���ѯ����
    var url = "cxcy_xmsb.do?method=exportData&dcclbh=" + DCCLBH+
        "&pkValue="+jQuery("#pkValue").val();//dcclbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}
