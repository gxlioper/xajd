var checkId = 'xh-hdmc-dd-sj-zbdw-ddssx-xn-xq';
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='view(\""
        + rowObject["djh"] + "\");'>" + cellValue
        + "</a>";
}
//查看
function view(djh) {
    showDialog("朋辈志愿者结果查看", 800, 550, "xyfd_pbjg.do?method=viewPbjg&djh="+djh);
}

//增加
function add() {
    var url = "xyfd_pbjg.do?method=addPbjg";
    var title = "朋辈志愿者结果新增";
    showDialog(title, 800, 550, url);
}

//修改
function update() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要修改的记录！");
    } else {
        if(rows[0]['sjly']=='1'){
            showAlertDivLayer("审核流程过来的记录不能修改！");
            return false;
        }
        var url = 'xyfd_pbjg.do?method=updatePbjg&jgid=' + rows[0]["jgid"]
            + '&djh=' + rows[0]["djh"];
        var title = "朋辈志愿者结果修改";
        showDialog(title, 800, 550, url);
    }
}
// 删除
function del() {
    var ids = jQuery("#dataTable").getSeletIds();

    if (ids.length == 0){
        showAlertDivLayer("请选择您要删除的记录！");
    } else {
        var rows = jQuery("#dataTable").getSeletRow();
        for(var i=0;i<ids.length;i++){
            if(rows[i]['sjly']=='1'){
                showAlertDivLayer("审核流程过来的记录不能删除！");
                return false;
            }
        }
        showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
            jQuery.post("xyfd_pbjg.do?method=del",{values:ids.toString()},function(data){
                showAlertDivLayer(data["message"]);
                jQuery("#dataTable").reloadGrid();
            },'json');
        }});
    }
}
//导入
function dr() {
    // 调用通用的导入function，参数是导入功能模块代码。
    toImportDataNew("IMPORT_XYFD_PBJG");
    return false;

}
var DCCLBH = "xyfd_xyfd_pbjg.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
    //DCCLBH导出功能编号,执行导出函数
    customExport(DCCLBH, exportData);
}

//导出方法
function exportData() {
    setSearchTj();//设置高级查询条件
    var url = "xyfd_pbjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}