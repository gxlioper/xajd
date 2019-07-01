function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}

/**
 * 增加
 * @return
 */
function addPage() {
    var url = "dzdy_zwwh.do?method=addZw";
    showDialog("新增", 550, 400, url);
}

/**
 * 修改
 * @return
 */
function update() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        return showAlertDivLayer("有且只能选择一条记录！");
    }
    var url = "dzdy_zwwh.do?method=update&dm=" + rows[0]['dm'];
    showDialog("增加项目", 550, 400, url);
}


/**
 * 保存评分标准
 * @return
 */
function saveDm() {
    var ids = "dm" + "-" + "mc";
    if (!checkNotNull(ids)) {
        return showAlert("请将带<font color='red'>*</font>项目填写完整！");
    }
    var url = "dzdy_zwwh.do?method=saveDm";
    ajaxSubFormWithFun("DzbzwwhForm", url, function (data) {
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

function updateSaveDm() {
    var ids = "dm" + "-" + "mc";
    if (!checkNotNull(ids)) {
        return showAlert("请将带<font color='red'>*</font>项目填写完整！");
    }
    var url = "dzdy_zwwh.do?method=updateSavaDm";
    ajaxSubFormWithFun("DzbzwwhForm", url, function (data) {
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

/**
 * 删除
 * @return
 */
function delZw() {
    var row = jQuery("#dataTable").getSeletRow();
    if (row.length == 0) {
        showAlertDivLayer("请选择您要删除的记录！");
    } else {
        var dms = new Array();
        var mcs = new Array();
        for (var i = 0; i < row.length; i++) {
            dms.push(row[i]['dm']);
            mcs.push(row[i]['mc']);
        }
        var para = {
            dms: dms,
            mcs: mcs
        };
        showConfirmDivLayer("您确定要删除选择的记录吗？", {
            "okFun": function () {
                jQuery.post("dzdy_zwwh.do?method=delZw", para, function (data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
            }
        });
    }
}
