/** 班级月报表js */

/**
 * 查询
 */
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}

/**
 * 返回学院月报表列表
 */
function fhxyYbbList() {
    var url = "rcsw_xsgzqkbb_xyybb.do";
    window.open(url,"_self");
}

/**
 * 增加
 */
function add() {
    var xyybbid = jQuery("#xyybbid").val();
    var url = "rcsw_xsgzqkbb_bjybbgl.do?method=bjYbbAdd&xyybbid="+xyybbid;
    var title = jQuery("#gnmkmc").val()+"增加";
    showDialog(title, 800, 570, url);
}

/**
 *  增加的保存
 */
function saveForAdd() {
    var checkId = 'bjmc-mxss-wxss-zkbhcs-bjhdkzcs-srsscs-ssthcs-gbtkcs-yjzlxqk-tfsjclqk-xxrs-fxrs-txrs-qtrs';
    if(!checkNotNull(checkId)){
        showAlertDivLayer("请将必填项填写完整！");
        return false;
    }

    var url = "rcsw_xsgzqkbb_bjybbgl.do?method=bjYbbSaveForAdd";
    ajaxSubFormWithFun("xsgzqkBjYbbForm", url, function(data) {
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
 * 修改
 */
function edit() {
    var rows = jQuery("#dataTable").getSeletRow();

    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要修改的记录！");
    } else {
        var url = 'rcsw_xsgzqkbb_bjybbgl.do?method=bjYbbEdit&id=' + rows[0]["id"];
        var title = jQuery("#gnmkmc").val()+"修改";
        showDialog(title, 800, 570, url);
    }
}

/**
 *  修改的保存
 */
function saveForEdit() {
    var checkId = 'mxss-wxss-zkbhcs-bjhdkzcs-srsscs-ssthcs-gbtkcs-yjzlxqk-tfsjclqk-xxrs-fxrs-txrs-qtrs';
    if(!checkNotNull(checkId)){
        showAlertDivLayer("请将必填项填写完整！");
        return false;
    }

    var url = "rcsw_xsgzqkbb_bjybbgl.do?method=bjYbbSaveForEdit";
    ajaxSubFormWithFun("xsgzqkBjYbbForm", url, function(data) {
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
 * 删除
 */
function del() {
    var ids = jQuery("#dataTable").getSeletIds();

    if (ids.length == 0) {
        showAlertDivLayer("请选择您要删除的记录！");
        return false;
    }

    showConfirmDivLayer("您确定要删除选择的记录吗？", {
        "okFun" : function() {
            jQuery.post("rcsw_xsgzqkbb_bjybbgl.do?method=bjYbbDel", {
                    values : ids.toString(),
                },
                function(data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
        }
    });
}

//dcglbh,导出功能编号
var DCGLBH = "rcsw_xsgzqkbb_bjybb.do";

//自定义导出 功能
function exportConfig() {
    //DCCLBH导出功能编号,执行导出函数
    customExport(DCGLBH, exprotData);
}

//导出方法
function exprotData() {
    setSearchTj();//设置高级查询条件
    var xyybbid = jQuery("#xyybbid").val();
    var url = "rcsw_xsgzqkbb_bjybbgl.do?method=exportData&xyybbid="+xyybbid+"&dcglbh=" + DCGLBH;//dcglbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}
