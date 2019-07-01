
function view(id) {
    showDialog("查看", 700, 500,
        "xlzx_zdgzxs.do?method=zdgzxsView&id="+id);
}
function add() {
    showDialog("新增", 700, 500, "xlzx_zdgzxs.do?method=zdgzxsAdd");

}

function update() {
    var rowsValue = jQuery("#dataTable").getSeletRow();
    if (rowsValue.length != 1) {
        showAlert("请选择一条您要修改的记录！");
        return false;
    }
    showDialog("修改", 700, 500,
        "xlzx_zdgzxs.do?method=zdgzxsUpdate&id=" + rowsValue[0]["id"]);
}

function save(type) {
    var checkId = "zxs-zxcs-wtlb-cljy-wtms";
    if(type == "add"){
        checkId += "-xh"
    }
    if(!checkNotNull(checkId)){
        showAlertDivLayer("请将必填项填写完整！");
        return false;
    }
    var sfxsty = jQuery("input[name=sfxsty]:checked").val();
    if(sfxsty == '' || sfxsty == null){
        showAlertDivLayer("请将必填项填写完整！");
        return false;
    }
    var url = "xlzx_zdgzxs.do?method=zdgzxsSave&type=" + type;
    ajaxSubFormWithFun("form", url, function(data) {
        if(data["message"]=="保存成功！"){
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
        showAlertDivLayer("请选择您要删除的记录！");
    } else {
        showConfirmDivLayer("您确定要删除选择的记录吗？", {
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

// 导出方法
function exportData() {
    setSearchTj();// 设置高级查询条件
    var url = "xlzx_zdgzxs.do?method=exportData&dcclbh="
        + "xlzx_xlwjga_zdgzxs.do";// dcclbh,导出功能编号
    url = addSuperSearchParams(url);// 设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}
//导入
function drxx(){
    toImportData("IMPORT_N10220");
    return false;
}