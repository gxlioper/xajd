//学生学号点击事件
function xhLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='xsxxView(\"" + cellValue + "\");'>" + cellValue
        + "</a>";
}

function xsxxView(xh) {
    showDialog("学生信息", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh);
}

//增加学生
function add() {

    showDialog('选择学生', 800, 600, 'qgzx_xsgl.do?method=getStu');
}

//删除
function del() {
    var ids = jQuery("#dataTable").getSeletIds();

    if (ids.length == 0) {

        showAlertDivLayer("请选择您要删除的记录！");
    } else {

        showConfirmDivLayer("您确定要删除选择的记录吗？", {
            "okFun": function () {
                jQuery.post("qgzx_xsgl.do?method=deleteQgxs", {values: ids.toString()}, function (data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
            }
        });
    }
}

//修改
function xg() {
    var rows = jQuery("#dataTable").getSeletRow();
    var xhs = jQuery("#dataTable").getSeletIds();
    if (rows.length == 0) {
        showAlertDivLayer("请选择一条您要修改的记录！");
    } else if (rows.length == 1) {
        var url = 'qgzx_xsgl.do?method=updateSfgmbx&xh=' + rows[0]["xh"];
        var title = "是否购买保险";
        showDialog(title, 400, 200, url);
    } else {
        showConfirmDivLayer("您确定要将所选学生的是否购买保险置为是？", {
            "okFun": function () {
                jQuery.post("qgzx_xsgl.do?method=plUpdate", {values: xhs.toString()}, function (data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
            }
        });
    }
}

var DCCLBH = "qgzx_xsgl_dcqgry.do";//dcclbh,导出功能编号
//导出
function dc(){
    customExport(DCCLBH, qgryExportData);
}
//导出方法
function qgryExportData() {
    setSearchTj();//设置高级查询条件
    var url = "qgzx_xsgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}

//导入
function importConfig(){
    toImportDataNew("IMPORT_QGZX_QGRY");
    return false;
}

//保存学生
function zjBcStu() {
    var ids = jQuery("#dataTable").getSeletIds();

    if (ids.length == 0) {

        alertInfo("请勾选需要添加的数据！", function (tag) {
            if (tag == "ok") {
                return false;
            }
        });
    } else {
        showConfirmDivLayer("您确定要添加选择的记录吗？", {
            "okFun": function () {
                jQuery.post("qgzx_xsgl.do?method=addQgxs", {values: ids.toString()}, function (data) {
                    showAlertDivLayer(data["message"], {}, {
                        "clkFun": function () {
                            if (parent.window) {
                                refershParent();
                            }
                        }
                    });
                }, 'json');
            }
        });
    }
}