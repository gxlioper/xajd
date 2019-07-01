function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
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
    var url = "dzdy_dmpz.do?method=saveDm";
    ajaxSubFormWithFun("dzbdmpzForm", url, function (data) {
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

function updateSavaDm() {
    var ids = "dm" + "-" + "mc";
    if (!checkNotNull(ids)) {
        return showAlert("请将带<font color='red'>*</font>项目填写完整！");
    }
    var url = "dzdy_dmpz.do?method=updateSavaDm";
    ajaxSubFormWithFun("dzbdmpzForm", url, function (data) {
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
 * 删除评分标准
 * @return
 */
function del() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("请选择您要删除的记录！");
    } else {
        var dms = new Array();
        for (var i = 0; i < rows.length; i++) {
            dms.push(rows[i]['dm']);
        }
        var para = {
            dms: dms
        };
        showConfirmDivLayer("您确定要删除选择的记录吗？", {
            "okFun": function () {
                jQuery.post("dzdy_dmpz.do?method=delDm", para, function (data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
            }
        });
    }
}

/**
 * 返回基础设定主查询页面
 * @return
 */
function fhjcsd() {
    document.location.href = "xg_gyjc_jcsd.do";
}

/**
 * 增加
 * @return
 */
function addPage() {
    var url = "dzdy_dmpz.do?method=addDmpz";
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
    var url = "dzdy_dmpz.do?method=update&dm=" + rows[0]['dm'];
    showDialog("增加项目", 550, 400, url);
}


jQuery("#selfzr").click(function () {


});

function getSy() {
    var url = "dzdy_dmpz.do?method=getSy";
    var title = "书院选择";
    showDialog(title, 770, 520, url);
}


function choseSy() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("请选择一个书院！");
        return false;
    }
    var api = frameElement.api;
    var parentsW = api.get('parentDialog');
    parentsW.jQuery("#sydm").val(rows[0]['sydm']);
    parentsW.jQuery("#symc").val(rows[0]['symc']);
    closeDialog();
}


//dcglbh,导出功能编号
var DCGLBH = "zhdj_dzdy_dmpz.do";

//自定义导出 功能
function exportConfig() {
    //DCCLBH导出功能编号,执行导出函数
    customExport(DCGLBH, exprotData);
}

//导出方法
function exprotData() {
    var url = "dzdy_dmpz.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,导出功能编号
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}

function hjDzz() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("请选择一个记录！");
        return false;
    }
    var url = "dzdy_dmpz.do?method=hjDzz&dm=" + rows[0]["dm"];
    var title = "党总支换届";
    showDialog(title, 770, 552, url);
}

function hjAddDzz() {
    var hjsjOld = jQuery("#hjsjOld").val();
    var hjsj = jQuery("#hjsj").val();
    if (hjsjOld == hjsj) {
        showAlertDivLayer("不能跟之前换届时间相同！");
        return false;
    }
    var url = "dzdy_dmpz.do?method=hjAddDzz";
    ajaxSubFormWithFun("dzbdmpzForm", url, function (data) {
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

function ljDzb() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("请选择一个记录！");
        return false;
    }
    var url = "dzdy_dmpz.do?method=ljDzz&dm=" + rows[0]["dm"];
    var title = "历届信息";
    showDialog(title, 770, 552, url);
}
