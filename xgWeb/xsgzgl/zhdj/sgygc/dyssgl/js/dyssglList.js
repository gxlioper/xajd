var gridSetting = {
    caption: "活动列表",
    pager: "pager",
    url: "zhdj_dyssgl.do?method=getList&type=query",
    colList: [
        {label: 'dyssid', name: 'dyssid', index: 'dyssid',hidden:true,key:true},
        {label: 'dyxh', name: 'dyxh', index: 'dyxh',hidden:true},
        {label: '党员', name: 'dyxm', index: 'dyxm', width: '10%'},
        {label: '联系宿舍', name: 'zsxx', index: 'zsxx', width: '10%'},
        {label: '建立联系时间', name: 'jllxsj', index: 'jllxsj', width: '10%'},
        {label: '联系宿舍总结情况表', name: 'sftj', index: 'sftj', width: '10%'},
        {label: '最后修改时间', name: 'zhxgsj', index: 'zhxgsj', width: '10%'}

    ],
    sortname: "jllxsj",
    sortorder: "desc"
};
jQuery(function () {
    jQuery("#dataTable").initGrid(gridSetting);
});
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}
function add(){
    var url = "zhdj_dyssgl.do?method=dyssglAdd";
    var title = "新增联系宿舍";
    showDialog(title, 550, 400, url);

}
function view(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条记录！");
        return;
    }
    var url = "zhdj_dyssgl.do?method=dyssglView&dyssid="+rows[0]["dyssid"];
    var title = "查看";
    showDialog(title, 550, 400, url);

}
function update(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条记录！");
        return;
    }
    var url = "zhdj_dyssgl.do?method=dyssglUpdate&dyssid="+rows[0]["dyssid"];
    var title = "修改";
    showDialog(title, 550, 400, url);

}

function tjzj(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条记录！");
        return;
    }
    if(jQuery("#userName").val() != rows[0]["dyxh"]){
        showAlertDivLayer("只有联系的党员能提交！");
        return;
    }
    var url = "zhdj_dyssgl.do?method=dyssglTjzj&dyssid="+rows[0]["dyssid"];
    var title = "提交总结情况表";
    showDialog(title, 550, 400, url);

}
function del(){
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length == 0) {
        showAlertDivLayer("请选择您要删除的记录！");
    } else {
        showConfirmDivLayer("您确定要删除选择的记录吗？", {
            "okFun": function () {
                jQuery.post("zhdj_dyssgl.do?method=del", {values: ids.toString()}, function (data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
            }
        });

    }
}




function importConfig(){
    toImportDataNew("IMPORT_ZHDJ_DYSSGL");
    return false;
}

//导出
function exportConfig(){
    var DCCLBH='zhdj_dyssgl.do';
    customExport(DCCLBH, exportData);
}
function exportData(){
    var shzt=jQuery("#shzt").val();
    var DCCLBH='zhdj_dyssgl.do';
    setSearchTj();//设置高级查询条件
    var url = "zhdj_dyssgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}