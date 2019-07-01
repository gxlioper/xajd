var gridSetting = {
    caption: "工作记录列表",
    pager: "pager",
    url: "zhdj_djgzjl.do?method=getList&type=query",
    colList: [
        {label: 'id', name: 'id', index: 'id',hidden:true,key:true},
        {label: '学年', name: 'xn', index: 'xn', width: '10%'},
        {label: '学期', name: 'xqmc', index: 'xqmc', width: '8%'},
        {label: '学院', name: 'xymc', index: 'xymc', width: '17%'},
        {label: '党支部应<br>换届数', name: 'yhjs', index: 'yhjs', width: '8%'},
        {label: '党支部实<br>际换届数', name: 'sjhjs', index: 'sjhjs', width: '8%'},
        {label: '季度本科生<br>党员发展人数', name: 'jdbksdyfzrs', index: 'jdbksdyfzrs', width: '10%'},
        {label: '季度研究生<br>党员发展人数', name: 'jdyjsdyfzrs', index: 'jdyjsdyfzrs', width: '10%'},
        {label: '党支部是否<br>按时交纳党费', name: 'sfasjndfmc', index: 'sfasjndfmc', width: '10%'},
        {label: '上报时间', name: 'sbsj', index: 'sbsj', width: '18%'}

    ],
    sortname: "xymc",
    sortorder: "asc"
};
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}
jQuery(function () {
    jQuery("#dataTable").initGrid(gridSetting);
});
function view(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要查看的记录！");
        return;
    }
    var url = "zhdj_djgzjl.do?method=view&id="+rows[0]["id"];
    var title = "查看";
    showDialog(title,450, 320, url);
}
function add(){
    var url = "zhdj_djgzjl.do?method=add";
    var title = "上报";
    showDialog(title, 450, 320, url);
}
function update(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要修改的记录！");
        return;
    }
    var url = "zhdj_djgzjl.do?method=update&id="+rows[0]["id"];
    var title = "修改";
    showDialog(title, 450, 320, url);
}
function del() {
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length == 0) {
        showAlertDivLayer("请选择您要删除的记录！");
    } else {
        showConfirmDivLayer("您确定要删除选择的记录吗？", {
            "okFun": function () {
                jQuery.post("zhdj_djgzjl.do?method=del", {values: ids.toString()}, function (data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
            }
        });

    }
}
//导出
function exportConfig(){
    var DCCLBH='zhdj_djgzjl.do';
    customExport(DCCLBH, exportData);
}
function exportData(){
    var shzt=jQuery("#shzt").val();
    var DCCLBH='zhdj_djgzjl.do';
    setSearchTj();//设置高级查询条件
    var url = "zhdj_djgzjl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}