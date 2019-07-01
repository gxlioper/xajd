/**
 * 家教老师结果相关js
 */

function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}

/**
 * 学号格式化
 */
function xhLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='jjlsjgShow(\""
        + rowObject["xh"]+"\");'>" + cellValue
        + "</a>";
}

/**
 * 查看
 */
function jjlsjgShow(xh) {
    var title = jQuery("#gnmkmc").val()+"查看";
    var url = "jjgl_jjlsjggl.do?method=jjlsjgShow&xh="+xh;
    showDialog(title, 800, 500,url);
}

/**
 * 新增的保存
 */
var checkId = 'xh-jjnj-lxdh';

function jjlsjgSaveForAdd() {
    if(!checkNotNull(checkId)){
        showAlertDivLayer("请将必填项填写完整！");
        return false;
    }

    if(jQuery("input[name=sckm]:checked").length == 0){
        showAlert("请选择擅长科目！");
        return false;
    }

    if(!isTelephone("lxdh")){
        showAlert("联系电话格式不合法");
        return false;
    }

    if (jQuery("#jxxy").val().length>150) {
        showAlert("教学宣言最多输入150字！");
        return false;
    }
    var url = "jjgl_jjlsjggl.do?method=jjlsjgSaveForAdd";
    ajaxSubFormWithFun("jjlsjgForm", url, function(data) {
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
 * 编辑的保存
 */
function jjlsjgSaveForEdit() {
    if(!checkNotNull(checkId)){
        showAlertDivLayer("请将必填项填写完整！");
        return false;
    }

    if(jQuery("input[name=sckm]:checked").length == 0){
        showAlert("请选择擅长科目！");
        return false;
    }

    if(!isTelephone("lxdh")){
        showAlert("联系电话格式不合法");
        return false;
    }

    if (jQuery("#jxxy").val().length>150) {
        showAlert("教学宣言最多输入150字！");
        return false;
    }
    var url = "jjgl_jjlsjggl.do?method=jjlsjgSaveForEdit";
    ajaxSubFormWithFun("jjlsjgForm", url, function(data) {
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
 * 增加弹框页面
 */
function add() {
    var url = "jjgl_jjlsjggl.do?method=jjlsjgAdd";
    var title = jQuery("#gnmkmc").val()+"增加";
    showDialog(title, 800, 550, url);
}

/**
 * 修改弹框页面
 */
function edit() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要修改的记录！");
    } else {
        var url = 'jjgl_jjlsjggl.do?method=jjlsjgEdit&xh=' + rows[0]["xh"];
        var title = jQuery("#gnmkmc").val()+"修改";
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
            jQuery.post("jjgl_jjlsjggl.do?method=jjlsjgDel", {
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
 * 导出
 */
var DCCLBH = "jjgl_jjlsjg.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
    //DCCLBH导出功能编号,执行导出函数
    customExport(DCCLBH, exportData);
}

//导出方法
function exportData() {
    setSearchTj();//设置高级查询条件
    var url = "jjgl_jjlsjggl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}

/**
 * 导入
 */
function importConfig() {
    // 调用通用的导入function，参数是导入功能模块代码
    toImportDataNew("IMPORT_JJLSJG");
    return false;
}

