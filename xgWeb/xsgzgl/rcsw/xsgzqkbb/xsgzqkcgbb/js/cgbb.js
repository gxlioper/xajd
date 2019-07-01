/** 常规报表js */

/**
 * 查询
 */
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}

/**
 * 增加
 */
function add() {
    var url = "rcsw_xsgzqkbb_cgbbgl.do?method=cgbbAdd";
    var title = jQuery("#gnmkmc").val()+"增加";
    showDialog(title, 800, 550, url);
}

/**
 *  增加的保存
 */
function saveForAdd() {
    var checkId = 'xn-xq-bszt-bsnr';
    if(!checkNotNull(checkId)){
        showAlertDivLayer("请将必填项填写完整！");
        return false;
    }
    if (jQuery("#bsnr").val().length>2000) {
        showAlert("报送内容最多输入2000字！");
        return false;
    }
    var url = "rcsw_xsgzqkbb_cgbbgl.do?method=cgbbSaveForAdd";
    ajaxSubFormWithFun("xsgzqkCgbbForm", url, function(data) {
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
        var url = 'rcsw_xsgzqkbb_cgbbgl.do?method=cgbbEdit&id=' + rows[0]["id"];
        var title = jQuery("#gnmkmc").val()+"修改";
        showDialog(title, 800, 550, url);
    }
}

/**
 *  修改的保存
 */
function saveForEdit() {
    var checkId = 'xn-xq-bszt-bsnr';
    if(!checkNotNull(checkId)){
        showAlertDivLayer("请将必填项填写完整！");
        return false;
    }
    if (jQuery("#bsnr").val().length>2000) {
        showAlert("报送内容最多输入2000字！");
        return false;
    }
    var url = "rcsw_xsgzqkbb_cgbbgl.do?method=cgbbSaveForEdit";
    ajaxSubFormWithFun("xsgzqkCgbbForm", url, function(data) {
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
 * 查看
 */
function view() {
    var rows = jQuery("#dataTable").getSeletRow();

    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要查看的记录！");
    } else {
        var url = 'rcsw_xsgzqkbb_cgbbgl.do?method=cgbbView&id=' + rows[0]["id"];
        var title = jQuery("#gnmkmc").val()+"查看";
        showDialog(title, 800, 550, url);
    }
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
            jQuery.post("rcsw_xsgzqkbb_cgbbgl.do?method=cgbbDel", {
                    values : ids.toString()
                },
                function(data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
        }
    });
}

//dcglbh,导出功能编号
var DCGLBH = "rcsw_xsgzqkbb_cgbb.do";

//自定义导出 功能
function exportConfig() {
    //DCCLBH导出功能编号,执行导出函数
    customExport(DCGLBH, exprotData);
}

//导出方法
function exprotData() {
    setSearchTj();//设置高级查询条件
    var url = "rcsw_xsgzqkbb_cgbbgl.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}

//导入
function importConfig(){
    toImportDataNew("IMPORT_XSGZQK_CGBB");
    return false;
}