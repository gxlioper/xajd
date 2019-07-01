/** 学院月报表js */

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
    var url = "rcsw_xsgzqkbb_xyybbgl.do?method=xyYbbAdd";
    var title = jQuery("#gnmkmc").val()+"增加";
    showDialog(title, 750, 300, url);
}

/**
 *  增加的保存
 */
function saveForAdd() {
    var checkId = 'xsgzfzrmc-xn-xq';
    if(!checkNotNull(checkId)){
        showAlertDivLayer("请将必填项填写完整！");
        return false;
    }

    var url = "rcsw_xsgzqkbb_xyybbgl.do?method=xyYbbSaveForAdd";
    ajaxSubFormWithFun("xsgzqkXyYbbForm", url, function(data) {
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
 * 填写
 */
function fillin() {
    var rows = jQuery("#dataTable").getSeletRow();

    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要填写的记录！");
    } else {
        var url = 'rcsw_xsgzqkbb_bjybbgl.do?method=bjYbbList&xyybbid=' + rows[0]["id"];
        window.open(url,"_self");
    }
}

/**
 * 填写页面链接
 */
function showFillin(id) {
    var url = 'rcsw_xsgzqkbb_bjybbgl.do?method=bjYbbList&xyybbid=' + id;
    window.open(url,"_self");
}

/**
 * 修改
 */
function edit() {
    var rows = jQuery("#dataTable").getSeletRow();

    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要修改的记录！");
    } else {
        var url = 'rcsw_xsgzqkbb_xyybbgl.do?method=xyYbbEdit&id=' + rows[0]["id"];
        var title = jQuery("#gnmkmc").val()+"修改";
        showDialog(title, 750, 300, url);
    }
}

/**
 *  修改的保存
 */
function saveForEdit() {
    var checkId = 'xsgzfzrmc-xn-xq';
    if(!checkNotNull(checkId)){
        showAlertDivLayer("请将必填项填写完整！");
        return false;
    }

    var url = "rcsw_xsgzqkbb_xyybbgl.do?method=xyYbbSaveForEdit";
    ajaxSubFormWithFun("xsgzqkXyYbbForm", url, function(data) {
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
            jQuery.post("rcsw_xsgzqkbb_xyybbgl.do?method=xyYbbDel", {
                    values : ids.toString()
                },
                function(data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
        }
    });
}

/**
 *  单位负责人自动补全初始化
 */
/*function initXsgzfzrmcAutocomplete() {
    //取得数据表：fdyxxb; 字段：xm
    var autoSetting = {
        dataTable:"fdyxxb",
        dataField:"xm",
        dataFieldKey:"zgh",
        dataFieldKeyId:"xsgzfzr",
        sqlTj: getSqlTj,
        scrollHeight:135
    }
    // 模糊搜索下拉【单位负责人】
    jQuery("#xsgzfzrmc").setAutocomplete(autoSetting);
}*/

/**
 * 转到班级月报表列表
 */
function ytbjrsbLink(cellValue,rowObject){
    var id = rowObject["id"];
    return "<a href='javascript:void(0);' onclick=\"showFillin('"+id+"')\" class='name'>"+cellValue+"</a>";
}

// 选择教师后，回调函数
function showFdysNotF5CallBack(rowData) {
    jQuery("#xsgzfzr").val(rowData["zgh"]);
    jQuery("#xsgzfzrmc").val(rowData["xm"]);
}