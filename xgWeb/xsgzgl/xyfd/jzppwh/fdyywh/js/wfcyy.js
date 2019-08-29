var checkId = 'xh-hdmc-dd-sj-zbdw-ddssx';
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}


function yyhLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='view(\""
        + rowObject["yyid"]+"\");'>" + cellValue
        + "</a>";
}
function view(yyid) {
    var height = jQuery(window).height();
    showDialog("预约信息查看", 800, height-250, "xyfd_fqyy.do?method=viewYy&yyid="+yyid+"&t="+new Date().getTime());
}

function jsLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='viewJs(\""
        + rowObject["fdjs"]+"\");'>" + cellValue
        + "</a>";
}
function viewJs(fdjs) {
    var height = jQuery(window).height();
    if(fdjs.substr(0,2)=='PB'){
        showDialog("课程信息查看", 800, height-250, "xyfd_pbjg.do?method=viewPbjg&djh="+fdjs+"&t="+new Date().getTime());
    }else {
        showDialog("课程信息查看", 800, height-250, "xyfd_fdjswh.do?method=fdjsView&djh="+fdjs+"&t="+new Date().getTime());
    }
}
function kcLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='viewKc(\""
        + rowObject["jgid"]+"\");'>" + cellValue
        + "</a>";
}
function viewKc(jgid) {
    var height = jQuery(window).height();
    showDialog("课程信息查看", 800, height-250, "xyfd_fdkcjg.do?method=viewFdkcjg&jgid="+jgid+"&t="+new Date().getTime());
}

function add(yysf) {
    var height = jQuery(window).height();
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要预约的课程！");
    }
    var url = "xyfd_fqyy.do?method=addYy&yysf="+yysf+"&fdkc="+rows[0]['jgid'];
    showDialog("辅导课程申请", 800, height-250, url);
}
function update() {
    var sqkg = jQuery("#sqkg").val();
    var rows = jQuery("#dataTable").getSeletRow();
    var height = jQuery(window).height();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要修改的记录！");
    } else {
        var userName = jQuery("#userName").val();
        if(rows[0]['yyr']=='本人'){
            if(rows[0]['xh']!=userName){
                showAlertDivLayer("不是您发起的预约！");
                return false;
            }
        }else{
            if(rows[0]['yhm']!=userName){
                showAlertDivLayer("不是您发起的预约！");
                return false;
            }
        }
        var shzt = rows[0]["zt"];
        if ("0" != shzt&&"3" != shzt) {
            showAlertDivLayer("只有未提交和已退回的记录才能修改！");
            return false;
        }

        var url = 'xyfd_fqyy.do?method=updateYy&yyid=' + rows[0]["yyid"];
        var title = "修改辅导预约";
        showDialog(title, 800, height-250, url);
    }

}

// 删除
function del() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length == 0) {
        showAlertDivLayer("请选择您要删除的记录！");
    } else if (rows[0]["zt"] != "0" && rows[0]["zt"] != "3") {
        showAlertDivLayer("只能删除未提交或者已退回的记录！");
        return false;
    } else {
        var userName = jQuery("#userName").val();
        if(rows[0]['yyr']=='本人'){
            if(rows[0]['xh']!=userName){
                showAlertDivLayer("不是您发起的预约！");
                return false;
            }
        }else{
            if(rows[0]['yhm']!=userName){
                showAlertDivLayer("不是您发起的预约！");
                return false;
            }
        }
        showConfirmDivLayer("您确定要删除选择的记录吗？", {
            "okFun" : function() {
                jQuery.post("xyfd_fqyy.do?method=yyDel", {
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
        showAlertDivLayer("请选择一条您要提交的预约！");
        return false;
    }
    var userName = jQuery("#userName").val();
    if(rows[0]['yyr']=='本人'){
        if(rows[0]['xh']!=userName){
            showAlertDivLayer("不是您发起的预约！");
            return false;
        }
    }else{
        if(rows[0]['yhm']!=userName){
            showAlertDivLayer("不是您发起的预约！");
            return false;
        }
    }
    if (rows[0]["zt"] != "0") {
        showAlertDivLayer("请选择未提交的预约！");
        return false;
    }
    showConfirmDivLayer("您确定要提交选择的预约吗？", {
        "okFun" : function() {
            jQuery.post("xyfd_fqyy.do?method=submit", {
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
        showAlertDivLayer("请选择您要取消的预约！");
    } else if (ids.length > 1) {
        showAlertDivLayer("请选择一条您要取消的预约！");
    } else {
        var rows = jQuery("#dataTable").getSeletRow();
        for ( var i = 0; i < ids.length; i++) {
            if (rows[i]['zt'] != '5'&&rows[i]['zt'] != '1') {
                showAlertDivLayer("只有预约中或已预约的记录才能被取消！");
                return false;
            }
        }
        var userName = jQuery("#userName").val();
        if(rows[0]['yyr']=='本人'){
            if(rows[0]['xh']!=userName){
                showAlertDivLayer("不是您发起的预约！");
                return false;
            }
        }else{
            if(rows[0]['yhm']!=userName){
                showAlertDivLayer("不是您发起的预约！");
                return false;
            }
        }
        if(rows[0]['zt']=='5') {
            showConfirmDivLayer("您确定要取消选择的预约吗？", {
                "okFun": function () {
                    jQuery.post("xyfd_fqyy.do?method=cancel&t=" + new Date().getTime(), {
                        values: ids.toString()
                    }, function (data) {
                        if(data["message"]=='1'){
                            var height = jQuery(window).height();
                            var url = 'xyfd_fqyy.do?method=qxYy&yyid=' + rows[0]["yyid"];
                            showDialog('取消预约原因', 600, height-250, url);
                        }else {
                            showAlertDivLayer(data["message"]);
                            jQuery("#dataTable").reloadGrid();
                        }
                    }, 'json');
                }
            });
        }else if(rows[0]['zt'] == '1'){
            var height = jQuery(window).height();
            var url = 'xyfd_fqyy.do?method=qxYy&yyid=' + rows[0]["yyid"];
            showDialog('取消预约原因', 600, height-250, url);
        }
    }

}
//评价课程
function pjkc() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    var userName = jQuery("#userName").val();
    if (ids.length != 1) {
        showAlertDivLayer("请选择一条您要评价的预约！");
        return false;
    }
    if(rows[0]['zt']!='4'&&rows[0]['zt']!='6'){
        showAlertDivLayer("该辅导未完成，无法评价！");
        return false;
    }
    if(rows[0]['xh']!=userName){
        showAlertDivLayer("您不是该预约的学生，无法评价！");
        return false;
    }
    var height = jQuery(window).height();
    var url = 'xyfd_fqyy.do?method=pjkc&yyh=' + rows[0]["yyh"];
    showDialog('取消预约原因', 600, height-250, url);
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

