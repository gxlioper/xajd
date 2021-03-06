function view(id) {
    showDialog("讲座上报查看", 550, 450, "cxcy_jzsb.do?method=jzsbView&id="+id);
}
function add() {
    var url = "cxcy_jzsb.do?method=jzsbAdd";
    var title = "讲座上报增加";
    showDialog(title, 550, 450, url);
}

function update() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要修改的记录！");
    }else {
        var url = 'cxcy_jzsb.do?method=jzsbUpdate&id=' + rows[0]["id"];
        var title = "讲座上报修改";
        showDialog(title, 550, 450, url);
    }

}

// 删除
function del() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length == 0) {
        showAlertDivLayer("请选择您要删除的记录！");
    } else {
        showConfirmDivLayer("您确定要删除选择的记录吗？", {
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
        showAlert("请将带<font color='red'>*</font>的项目填写完整");
        return false;
    }
    var url = "cxcy_jzsb.do?method=save&type=" + type;
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


/**
 * 验证是否存在空项
 * @param ids 要验证的控件id "-"分隔
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
    setSearchTj();//设置高级查询条件
    var url = "cxcy_jzsb.do?method=exportData&dcclbh=" + DCCLBH+
        "&pkValue="+jQuery("#pkValue").val();//dcclbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}
