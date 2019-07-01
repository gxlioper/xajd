var gridSetting = {
    caption: "活动列表",
    pager: "pager",
    url: "zhdj_dzbbjgl.do?method=getList&type=query",
    colList: [
        {label: 'id', name: 'id', index: 'id',hidden:true,key:true},
        {label: '党支部id', name: 'dzbid', index: 'dzbid',hidden:true},
        {label: '班级代码', name: 'lxbjdm', index: 'lxbjdm',hidden:true},
        {label: '支部名称', name: 'dzbmc', index: 'dzbmc', width: '10%'},
        {label: '联系班级', name: 'lxbjmc', index: 'lxbjmc', width: '10%'},
        {label: '支部书记', name: 'dzbsjxm', index: 'dzbsjxm', width: '10%'},
        //{label: '参加人员', name: 'cjry', index: 'cjry', width: '10%'},
        {label: '宣传委员', name: 'xcwyxm', index: 'xcwyxm', width: '10%'},
        {label: '纪律委员', name: 'jlwyxm', index: 'jlwyxm', width: '10%'},
        {label: '组织委员',name: 'zzwyxm', index: 'zzwyxm', width: '10%'},
        {label: '创建时间',name: 'cjsj', index: 'cjsj', width: '12%'}

    ],
    sortname: "cjsj",
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
    var url = "zhdj_dzbbjgl.do?method=dzbbjglAdd";
    var title = "新增联系班级";
    showDialog(title, 400, 270, url);

}
function update(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条记录！");
        return;
    }
    var url = "zhdj_dzbbjgl.do?method=dzbbjglUpdate&id="+rows[0]["id"];
    var title = "修改";
    showDialog(title, 400, 270, url);

}
function del(){
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length == 0) {
        showAlertDivLayer("请选择您要删除的记录！");
    } else {
        showConfirmDivLayer("您确定要删除选择的记录吗？", {
            "okFun": function () {
                jQuery.post("zhdj_dzbbjgl.do?method=del", {values: ids.toString(),type:jQuery("#type").val()}, function (data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
            }
        });

    }
}




function importConfig(){
    toImportDataNew("IMPORT_ZHDJ_DZBBJGL");
    return false;
}

//导出
function exportConfig(){
    var DCCLBH='zhdj_dzbbjgl.do';
    customExport(DCCLBH, exportData);
}
function exportData(){
    var DCCLBH='zhdj_dzbbjgl.do';
    setSearchTj();//设置高级查询条件
    var url = "zhdj_dzbbjgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}