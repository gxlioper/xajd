
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}
//
function addGzal() {
    var rows = jQuery("#dataTable").getSeletRow();
    if(rows.length==1){
        if(rows[0]["alzt"]=='0'){
            showAlertDivLayer("已撤档！");
            return false;
        }
        var url = "xyfd_gzaljl.do?method=toUpdateGzal&jdh=" + rows[0]["jdh"] + "&t=" + new Date().getTime();
        showDialog("修改帮扶案例", 800, 550, url);
    }else {
        var url = "xyfd_gzaljl.do?method=addGzal&t=" + new Date().getTime();
        showDialog("新建帮扶案例", 800, 550, url);
    }
}

function addGzjl() {
    var rows = jQuery("#dataTable").getSeletRow();
    if(rows.length!=1){
        showAlertDivLayer("请选择一条添加工作记录的案例！");
        return false;
    }
    var url = "xyfd_gzaljl.do?method=addGzjl&jdh=" + rows[0]["jdh"] + "&t=" + new Date().getTime();
    showDialog("添加工作记录", 800, 350, url);
}
//撤档
function cd() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要撤销的记录！");
        return false;
    }
    if(rows[0]["alzt"]=='0'){
        showAlertDivLayer("已撤档！");
        return false;
    }
    var url = "xyfd_gzaljl.do?method=cd&jdh=" + rows[0]["jdh"] + "&t=" + new Date().getTime();
    showDialog("撤档", 600, 350, url);
}
//修改级别
function xgjb() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要修改的记录！");
        return false;
    }
    if(rows[0]["alzt"]=='0'){
        showAlertDivLayer("已撤档！");
        return false;
    }
    var url = "xyfd_gzaljl.do?method=xgjb&jdh=" + rows[0]["jdh"] + "&t=" + new Date().getTime();
    showDialog("撤档", 400, 200, url);
}


// 删除
function delAl() {
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length == 0) {
        showAlertDivLayer("请选择您要删除的记录！");
    } else {
        showConfirmDivLayer("您确定要删除选择的记录吗？", {
            "okFun" : function() {
                jQuery.post("xyfd_gzaljl.do?method=delAl", {
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
//查看
function ckal() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要查看的记录！");
        return false;
    }
    var url = "xyfd_gzaljl.do?method=viewGzal&jdh=" + rows[0]["jdh"] + "&t=" + new Date().getTime();
    showDialog("查看档案", 800, 550, url);
}
//转介
function zj() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要转介的案例！");
        return false;
    }
    if(rows[0]["sfzj"]=="是"){
        showAlertDivLayer("已转介，请勿重复操作！");
        return false;
    }
    var url = "xyfd_gzaljl.do?method=alzj&jdh=" + rows[0]["jdh"] + "&t=" + new Date().getTime();
    showDialog("转介", 500, 250, url);
}
//导入
function importConfig(){
    toImportDataNew("IMPORT_XYFD_GZAL");
    return false;
}

//打印报表
function printGzal(url){

    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length <=0) {
        showAlertDivLayer("请选择一条记录！");
    } else {
        var jdh = jQuery("#dataTable").getSeletIds();
        var url = url + "&jdh=" +jdh;
        window.open(url);
    }
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

