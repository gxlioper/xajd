function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}

//查看
function dzbydLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='View(\""
        + rowObject['xh'] + "\");'>" + cellValue
        + "</a>";
}

function View(xh) {
    showDialog("党支部异动信息", 900, 450, "dzdy_dzbyd.do?method=getDzbydInfo&xh=" + xh);
}

function updateDzbyd() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("请选择一个记录！");
        return false;
    }
    var url = "dzdy_dzbyd.do?method=updateDzbyd&xh=" + rows[0]["xh"];
    var title = "修改党支部异动信息";
    showDialog(title, 770, 552, url);
}

function bc() {
    var url = "dzdy_dzbyd.do?method=bc";
    ajaxSubFormWithFun("DzbydForm", url, function (data) {
        if (data["message"] == "保存成功！") {
            showAlert(data["message"], {}, {
                "clkFun": function () {
                    var api = frameElement.api, W = api.opener;
                    W.jQuery("#dataTable").reloadGrid();
                    closeDialog();
                }
            });
        } else {
            showAlert(data["message"]);
        }
    });
}

function delDzbyd() {
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length == 0) {
        showAlertDivLayer("请选择您要删除的记录！");
        return false;
    }
    showConfirmDivLayer("您确定要删除选择的记录吗？", {
        "okFun": function () {
            jQuery.post("dzdy_dzbyd.do?method=del", {values: ids.toString()}, function (data) {
                showAlertDivLayer(data["message"]);
                jQuery("#dataTable").reloadGrid();
            }, 'json');
        }
    });
}

//dcglbh,导出功能编号
var DCGLBH = "zhdj_dzdy_dzbyd.do";

//自定义导出 功能
function exportConfig() {
    //DCCLBH导出功能编号,执行导出函数
    customExport(DCGLBH, exprotData);
}

//导出方法
function exprotData() {
    setSearchTj();//设置高级查询条件
    var url = "dzdy_dzbyd.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}


//导入
function importConfig() {
    toImportDataNew("IMPORT_DZBYDXX");
    return false;
}


/**
 * 调用新框架导入功能入口
 *
 * @param drmkdm 导入功能代码
 * @return
 */
function toImportDataNew(drmkdm) {
    var _SSO_DR_PATH = 'out_access.do?gnbh=import&toPage=/xgweb/dr/out_login.html';
    if (drmkdm == null || drmkdm == undefined || jQuery.trim(drmkdm) == "") {
        alert("导入模块参数未配置!");
        return false;
    }
    var url = _SSO_DR_PATH + "&drmkdm=" + drmkdm;
    showDialog('导入', 720, 580, url, {
        close: function () {
            if (jQuery("#search_go")) {
                jQuery("#search_go").click();
            }
        }
    });
    return false;
}

