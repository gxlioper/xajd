
function view(id) {
    showDialog("����Σ����Ԥ�鿴", 700, 500,
        "xlzx_xlwjgy.do?method=xlwjgyView&id="+id);
}
function add() {
    showDialog("����", 700, 500, "xlzx_xlwjgy.do?method=xlwjgyAdd");

}

function update() {
    var rowsValue = jQuery("#dataTable").getSeletRow();
    if (rowsValue.length != 1) {
        showAlert("��ѡ��һ����Ҫ�޸ĵļ�¼��");
        return false;
    }
     showDialog("�޸�", 700, 500,
            "xlzx_xlwjgy.do?method=xlwjgyUpdate&id=" + rowsValue[0]["id"]);
}

function save(type) {
    var checkId = "bgsj-bgr-fxtj-wjcd-wjfzgc-wjgysj-wjgyry-wjgyfs-xtbm-wjgyjg-wjclgc";
    if(type == "add"){
        checkId += "-xh"
    }
    if(!checkNotNull(checkId)){
        showAlertDivLayer("�뽫��������д������");
        return false;
    }
    var url = "xlzx_xlwjgy.do?method=xlwjgySave&type=" + type;
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
                jQuery.post("xlzx_xlwjgy.do?method=xlwjgyDel", {
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
    customExport("xlzx_xlwjga_xlwjgy.do", exportData, 700, 500);
}

// ��������
function exportData() {
    setSearchTj();// ���ø߼���ѯ����
    var url = "xlzx_xlwjgy.do?method=exportData&dcclbh="
        + "xlzx_xlwjga_xlwjgy.do";// dcclbh,�������ܱ��
    url = addSuperSearchParams(url);// ���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}
//����
function drxx(){
    toImportData("IMPORT_N10220");
    return false;
}