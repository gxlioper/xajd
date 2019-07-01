/** 周报表js */

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
    var zcListSize = jQuery("#zcListSize").val();
    if(zcListSize == "0"){
        showAlertDivLayer("学期周数未初始化，请联系管理员！");
        return false;
    }
    var url = "rcsw_xsgzqkbb_zbbgl.do?method=zbbAdd";
    var title = jQuery("#gnmkmc").val()+"增加";
    showDialog(title, 800, 570, url);
}

/**
 *  增加的保存
 */
function saveForAdd() {
    var checkId = 'lxdh-dwfzrmc-xn-xq-zc-xxzt-xxnr-clqk';
    if(!checkNotNull(checkId)){
        showAlertDivLayer("请将必填项填写完整！");
        return false;
    }
    if (jQuery("#xxnr").val().length>2000) {
        showAlert("信息内容最多输入2000字！");
        return false;
    }
    if (jQuery("#clqk").val().length>2000) {
        showAlert("处理情况最多输入2000字！");
        return false;
    }
    if (jQuery("#bz").val().length>2000) {
        showAlert("备注最多输入2000字！");
        return false;
    }
    //验证联系电话
    if(!isTelephone("lxdh")){
        showAlert("联系电话格式不合法！");
        return false;
    }
    var url = "rcsw_xsgzqkbb_zbbgl.do?method=zbbSaveForAdd";
    ajaxSubFormWithFun("xsgzqkZbbForm", url, function(data) {
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
        var url = 'rcsw_xsgzqkbb_zbbgl.do?method=zbbEdit&id=' + rows[0]["id"];
        var title = jQuery("#gnmkmc").val()+"修改";
        showDialog(title, 800, 560, url);
    }
}

/**
 *  修改的保存
 */
function saveForEdit() {
    var checkId = 'lxdh-dwfzrmc-xn-xq-zc-xxzt-xxnr-clqk';
    if(!checkNotNull(checkId)){
        showAlertDivLayer("请将必填项填写完整！");
        return false;
    }
    if (jQuery("#xxnr").val().length>2000) {
        showAlert("信息内容最多输入2000字！");
        return false;
    }
    if (jQuery("#clqk").val().length>2000) {
        showAlert("处理情况最多输入2000字！");
        return false;
    }
    if (jQuery("#bz").val().length>2000) {
        showAlert("备注最多输入2000字！");
        return false;
    }
    //验证联系电话
    if(!isTelephone("lxdh")){
        showAlert("联系电话格式不合法！");
        return false;
    }
    var url = "rcsw_xsgzqkbb_zbbgl.do?method=zbbSaveForEdit";
    ajaxSubFormWithFun("xsgzqkZbbForm", url, function(data) {
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
        var url = 'rcsw_xsgzqkbb_zbbgl.do?method=zbbView&id=' + rows[0]["id"];
        var title = jQuery("#gnmkmc").val()+"查看";
        showDialog(title, 800, 560, url);
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
            jQuery.post("rcsw_xsgzqkbb_zbbgl.do?method=zbbDel", {
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
var DCGLBH = "rcsw_xsgzqkbb_zbb.do";

//自定义导出 功能
function exportConfig() {
    //DCCLBH导出功能编号,执行导出函数
    customExport(DCGLBH, exprotData);
}

//导出方法
function exprotData() {
    setSearchTj();//设置高级查询条件
    var url = "rcsw_xsgzqkbb_zbbgl.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}

//导入
function importConfig(){
    toImportDataNew("IMPORT_XSGZQK_ZBB");
    return false;
}

/**
 *  单位负责人自动补全初始化
 */
/*function initDwfzrmcAutocomplete() {
    //取得数据表：fdyxxb; 字段：xm
    var autoSetting = {
        dataTable:"fdyxxb",
        dataField:"xm",
        dataFieldKey:"zgh",
        dataFieldKeyId:"dwfzr",
        sqlTj: getSqlTj,
        scrollHeight:135
    }
    // 模糊搜索下拉【单位负责人】
    jQuery("#dwfzrmc").setAutocomplete(autoSetting);
}*/

/**
 * 选择单位负责人
 */
/*function changeDwfzrmc(){
    // 重置
    jQuery("#dwfzrmc").val("");
    jQuery("#dwfzr").val("");

}*/

/**
 * 根据周次显示周次起止日期
 */
function changeZcksjsrq(){
    var zcObj = jQuery("#zc").find("option:selected");
    var html = zcObj.attr("ksrq") + " 至 " + zcObj.attr("jsrq");
    jQuery("#zcksjsrq_td").html(html);
    jQuery("#zcksjsrq_hidden").val(html);
}

// 选择教师后，回调函数
function showFdysNotF5CallBack(rowData) {
    jQuery("#dwfzr").val(rowData["zgh"]);
    jQuery("#dwfzrmc").val(rowData["xm"]);
}