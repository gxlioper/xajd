
function view(id) {
    showDialog("�鿴", 700, 500,
        "xlzx_zdgzxs.do?method=zdgzxsView&id="+id);
}
function add() {
    showDialog("����", 700, 500, "xlzx_zdgzxs.do?method=zdgzxsAdd");

}

function update() {
    var rowsValue = jQuery("#dataTable").getSeletRow();
    if (rowsValue.length != 1) {
        showAlert("��ѡ��һ����Ҫ�޸ĵļ�¼��");
        return false;
    }
    showDialog("�޸�", 700, 500,
        "xlzx_zdgzxs.do?method=zdgzxsUpdate&id=" + rowsValue[0]["id"]);
}

function save(type) {
    var checkId = "zxs-zxcs-wtlb-cljy-wtms";
    if(type == "add"){
        checkId += "-xh"
    }
    if(!checkNotNull(checkId)){
        showAlertDivLayer("�뽫��������д������");
        return false;
    }
    var sfxsty = jQuery("input[name=sfxsty]:checked").val();
    if(sfxsty == '' || sfxsty == null){
        showAlertDivLayer("�뽫��������д������");
        return false;
    }
    var url = "xlzx_zdgzxs.do?method=zdgzxsSave&type=" + type;
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

function del() {
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length == 0) {
        showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
    } else {
        showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
            "okFun" : function() {
                jQuery.post("xlzx_zdgzxs.do?method=zdgzxsDel", {
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

function exportConfig() {
    customExport("xlzx_xlwjga_zdgzxs.do", exportData, 700, 500);
}

// ��������
function exportData() {
    setSearchTj();// ���ø߼���ѯ����
    var url = "xlzx_zdgzxs.do?method=exportData&dcclbh="
        + "xlzx_xlwjga_zdgzxs.do";// dcclbh,�������ܱ��
    url = addSuperSearchParams(url);// ���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}
//����
function drxx(){
    toImportData("IMPORT_N10220");
    return false;
}