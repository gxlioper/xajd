function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}


/**
 * 增加
 * @return
 */
function addCy() {
    url = "dzdy_cygl.do?method=add";
    showDialog("增加党支部成员", 770, 550, url, {
        close: function () {
            jQuery("#dataTable").reloadGrid();
        }
    });

}

/**
 * 修改
 * @return
 */
function update() {
    var rows = jQuery("#dataTable").getSeletRow();
    document.location.href = "gyjc_ccrcsz.do?method=update&ccid=" + rows[0]["ccid"];
}

function updateCy() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("请选择一个记录！");
        return false;
    }
    var url = "dzdy_cygl.do?method=updateCy&sfzh=" + rows[0]["sfzh"];
    var title = "修改成员信息";
    showDialog(title, 770, 552, url);
}


function ljDzb() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("请选择一个记录！");
        return false;
    }
    var url = "dzdy_dzbgl.do?method=ljDzb&dzbid=" + rows[0]["dzbid"];
    var title = "历届信息";
    showDialog(title, 770, 552, url);
}


/**
 * 只修改日期
 * @return
 */
function saveCcrcUpdate() {
    var url = "gyjc_ccrcsz.do?method=saveCcrcUpdate";
    ajaxSubFormWithFun("CcrcForm", url, function (data) {
        if (data["message"] == "保存成功！") {
            showAlert(data["message"], {}, {
                "clkFun": function () {
                    if (parent.window) {
                        refershParent();
                    }
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
function delCy() {
    var row = jQuery("#dataTable").getSeletRow();
    var dels = new Array();
    if (row.length == 0) {
        showAlertDivLayer("请选择您要删除的记录！");
        return false;
    }
    for (var i = 0; i < row.length; i++) {
        dels.push(row[i]["sfzh"]);
    }
    var para = {
        dels: dels

    };
    showConfirmDivLayer("您确定要删除选择的记录吗？", {
        "okFun": function () {
            jQuery.post("dzdy_cygl.do?method=delCy", para, function (data) {
                showAlertDivLayer(data["message"]);
                jQuery("#dataTable").reloadGrid();
            }, 'json');
        }
    });

}


jQuery(function () {
    onShow();
});

function onShow() {
    var url = "dzdy_dzbgl.do?method=add&type=query";
    jQuery.post(url, {}, function (data) {
        initTslx(data);//初始化特殊类型
    }, 'json');
}

//初始化特殊类型
function initTslx(data) {
    if (data == null || data.length == 0) {
        return;
    }
    var sHtml = "";


    for (var i = 0; i < data.length; i++) {
        var o = data[i];
        var dm = o.dm;
        var mc = o.mc;
        if (dm != null && dm != "") {
            sHtml += "<option value='" + dm + "'>" + mc + "</option>";
        }
    }
    jQuery("#jcdwdm").html(sHtml);

    jQuery("#jcdwdm").change(function () {//
        var jcdwdm = jQuery(this).val();
        if (jcdwdm === "") {
            jQuery("#xymc").html("");

        }
        for (var i = 0; i < data.length; i++) {
            var o = data[i];
            var dm = o.dm;
            var xymc = o.bmmc;
            if (dm != null && dm != "" && jcdwdm === dm) {

                jQuery("#xymc").html(xymc);
            }
        }
    });
    jQuery("#jcdwdm").change();
}


function choseXx() {
    var rows = jQuery("#dataTable").getSeletRow();

    if (rows.length == 0) {
        showAlertDivLayer("请选择一个学生！");
        return false;
    }
    var api = frameElement.api;
    var parentsW = api.get('parentDialog');
    parentsW.jQuery("#xh").val(rows[0]['xh']);
    parentsW.jQuery("#xm").html(rows[0]['xm']);
    parentsW.jQuery("#xb").html(rows[0]['xb']);
    parentsW.jQuery("#csrq").html(rows[0]['csrq']);
    parentsW.jQuery("#sfzh").val(rows[0]['sfzh']);
    parentsW.jQuery("#mzmc").html(rows[0]['mzmc']);
    parentsW.jQuery("#zymc").html(rows[0]['zymc']);
    parentsW.jQuery("#bjmc").html(rows[0]['bjmc']);
    parentsW.jQuery("#lxdh").html(rows[0]['lxdh']);
    parentsW.jQuery("#jtdh").html(rows[0]['jtdh']);
    parentsW.jQuery("#jtdz").html(rows[0]['jtdz']);
    parentsW.jQuery("#rxrq").html(rows[0]['rxrq']);
    parentsW.jQuery("#jg").html(rows[0]['jg']);
    parentsW.jQuery("#nj").html(rows[0]['nj']);
    parentsW.jQuery("#symc").html(rows[0]['symc']);
    parentsW.jQuery("#zybjmc").html(rows[0]['zybjmc']);
    parentsW.jQuery("#dzyx").html(rows[0]['dzyx']);
    closeDialog();
}


function saveCy() {
    var url = "dzdy_cygl.do?method=saveCy";
    ajaxSubFormWithFun("CyglForm", url, function (data) {
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


function updateSaveCy() {
    var url = "dzdy_cygl.do?method=updateSaveCy";
    ajaxSubFormWithFun("CyglForm", url, function (data) {
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


function updateSaveDzb() {
    var url = "dzdy_dzbgl.do?method=updateSaveDzb";
    ajaxSubFormWithFun("DzbglForm", url, function (data) {
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


function hjAddDzb() {
    var hjsjOld = jQuery("#hjsjOld").val();
    var hjsj = jQuery("#hjsj").val();
    if (hjsjOld == hjsj) {
        showAlertDivLayer("不能跟之前换届时间相同！");
        return false;
    }
    var url = "dzdy_dzbgl.do?method=hjAddDzb";
    ajaxSubFormWithFun("DzbglForm", url, function (data) {
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


function djztgb() {
    var djzt = jQuery("#djzt").val();
    if (djzt == "不正常") {
        document.getElementById("sl").style.display = "";
        document.getElementById("ld").style.display = "";
        djslgb();
        djldgb();
    } else {
        document.getElementById("sl").style.display = "none";
        document.getElementById("ld").style.display = "none";

    }
}

function djslgb() {
    var sfsl = jQuery("#sfsl").val();
    if (sfsl == "1") {
        document.getElementById("sl2").style.display = "";
        document.getElementById("sl3").style.display = "";
    } else {
        document.getElementById("sl2").style.display = "none";
        document.getElementById("sl3").style.display = "none";
        jQuery("#slsj").val("");
    }


}

function djldgb() {
    var sfld = jQuery("#sfld").val();
    if (sfld == "1") {
        document.getElementById("lc2").style.display = "";
        document.getElementById("lc3").style.display = "";
    } else {
        document.getElementById("lc2").style.display = "none";
        document.getElementById("lc3").style.display = "none";
        jQuery("#lcd").val("");
    }


}

//同步异动信息
function tb() {
    showConfirmDivLayer("您确定要同步选择的记录吗？", {
        "okFun": function () {
            jQuery.post("dzdy_cygl.do?method=tbydxx", function (data) {
                showAlertDivLayer(data["message"]);
                jQuery("#dataTable").reloadGrid();
            }, 'json');
        }
    });
}


//导入
function dr() {
    // 调用通用的导入function，参数是导入功能模块代码。
    toImportDataNew("IMPORT_DTJS_XSDZBCYDR");
    return false;

}

var DCCLBH = "zhdj_dzdy_cygl.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
    //DCCLBH导出功能编号,执行导出函数
    customExport(DCCLBH, xsdzbcyExportData);
}

//导出方法
function xsdzbcyExportData() {
    setSearchTj();//设置高级查询条件
    var url = "dzdy_cygl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}











