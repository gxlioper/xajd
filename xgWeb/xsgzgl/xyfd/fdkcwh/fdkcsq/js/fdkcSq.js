var checkId = 'xh-hdmc-dd-sj-zbdw-ddssx';
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='view(\""
        + rowObject["sqid"]+"\");'>" + cellValue
        + "</a>";
}
function view(sqid) {
    showDialog("朋辈志愿者申请查看", 600, 550, "xyfd_fdkcsq.do?method=fdkcsqView&sqid="+sqid+"&t="+new Date().getTime());
}

function add() {
    var url = "xyfd_fdkcsq.do?method=fdkcsqAdd";
    showDialog("辅导课程申请", 800, 550, url);
}
function update() {
    var sqkg = jQuery("#sqkg").val();
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要修改的记录！");
    } else {
        var shzt = rows[0]["shzt"];
        if ("0" != shzt&&"3" != shzt) {
            showAlertDivLayer("只有未提交和已退回的记录才能修改！");
            return false;
        }

        var url = 'xyfd_fdkcsq.do?method=fdkcsqUpdate&sqid=' + rows[0]["sqid"];
        var title = "朋辈志愿者申请修改";
        showDialog(title, 600, 550, url);
    }

}

// 删除
function del() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length == 0) {
        showAlertDivLayer("请选择您要删除的记录！");
    } else if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
        showAlertDivLayer("只能删除未提交或者已退回的记录！");
        return false;
    } else {
        showConfirmDivLayer("您确定要删除选择的记录吗？", {
            "okFun" : function() {
                jQuery.post("xyfd_fdkcsq.do?method=fdkcsqDel", {
                        values : ids.toString()
                    },
                    function(data) {
                        showAlertDivLayer(data["message"]);
                        jQuery("#dataTable").reloadGrid();
                    }, 'json');
            }
        });
    }
}

// 提交
function submitBusi() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length != 1) {
        showAlertDivLayer("请选择一条您要提交的记录！");
        return false;
    }
    if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
        showAlertDivLayer("请选择未提交或者已退回的记录！");
        return false;
    }
    showConfirmDivLayer("您确定要提交选择的记录吗？", {
        "okFun" : function() {
            jQuery.post("xyfd_fdkcsq.do?method=submit", {
                values : ids.toString()
            }, function(data) {
                showAlertDivLayer(data["message"]);
                jQuery("#dataTable").reloadGrid();
            }, 'json');
        }
    });



}
// 撤销
function cancel() {
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length == 0) {
        showAlertDivLayer("请选择您要撤销的记录！");
    } else if (ids.length > 1) {
        showAlertDivLayer("请选择一条您要撤销的记录！");
    } else {
        var rows = jQuery("#dataTable").getSeletRow();
        for ( var i = 0; i < ids.length; i++) {
            if (rows[i]['shzt'] != '5') {
                showAlertDivLayer("只有审核中的记录才能被撤销！");
                return false;
            }
        }

        showConfirmDivLayer("您确定要撤销选择的记录吗？", {
            "okFun" : function() {
                jQuery.post("xyfd_fdkcsq.do?method=cancel&t="+new Date().getTime(), {
                    values : ids.toString(),
                    splcid : rows[0]['splc']
                }, function(data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
            }
        });
    }

}
/*
 * 流程跟踪
 */
function lcgz() {
    var rows = jQuery("#dataTable").getSeletRow();

    if (1 != rows.length) {
        showAlertDivLayer("请选择一条流程跟踪记录！");
        return false;
    }
    var shzt = rows[0]["shzt"];
    if ("0" == shzt) {
        showAlertDivLayer("该记录为未提交状态，请先提交！");
        return false;
    }
    showDialog("社团活动登记审批流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
        + rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
}

var DCCLBH = "xyfd_xyfd_fdkcsq.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
    //DCCLBH导出功能编号,执行导出函数
    customExport(DCCLBH, exportData);
}

//导出方法
function exportData() {
    setSearchTj();//设置高级查询条件
    var url = "xyfd_fdkcsq.do?method=export&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}

