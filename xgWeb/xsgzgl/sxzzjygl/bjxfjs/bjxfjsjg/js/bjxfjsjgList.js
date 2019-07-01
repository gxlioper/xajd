var gridSetting = {
    caption: "活动列表",
    pager: "pager",
    url: "sxzzjy_bjxfjsjg.do?method=getList&type=query",
    colList: [
        {label: 'jgid', name: 'jgid', index: 'jgid',hidden:true,key:true},
        {label: '数据来源',name:'sjly', index: 'sjly',hidden:true},
        {label: 'bjdm',name:'bjdm', index: 'bjdm',hidden:true},
        {label: '班级', name: 'bjmc', index: 'bjmc', width: '10%',formatter:bjLink},
        {label: '学院', name: 'xymc', index: 'xymc', width: '10%'},
        {label: '名称', name: 'xfjsmc', index: 'xfjsmc', width: '10%'},
        {label: '申报类型', name: 'sblxmc', index: 'sblxmc', width: '10%'},
        {label: '学年学期', name: 'xnxq', index: 'xnxq', width: '10%'},
        {label: '中期汇报', name: 'sfnzhb', index: 'sfnzhb', width: '10%'},
        {label: '年终总结', name: 'sfnzzj', index: 'sfnzzj', width: '10%'},
        {label: 'nzzjid', name: 'nzzjid', index: 'nzzjid', hidden:true},
        {label: 'nzhbid', name: 'nzhbid', index: 'nzhbid', hidden:true},
        {label: '录入时间', name: 'lrsj', index: 'lrsj',hidden:true}

    ],
    sortname: "lrsj",
    sortorder: "desc"
};
jQuery(function () {
    jQuery("#dataTable").initGrid(gridSetting);

});

function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}

function view(jgid) {
    showDialog("查看", 700,550, "sxzzjy_bjxfjsjg.do?method=bjxfjsjgView&jgid=" + jgid);
}

function bjLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='view(\"" + rowObject["jgid"] + "\");'>" + cellValue
        + "</a>";
}
function add(){
    var url = "sxzzjy_bjxfjsjg.do?method=bjxfjsjgAdd";
    var title = "新增班级学风建设";
    showDialog(title,  700,550, url);

}
function update(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条记录！");
        return;
    }
    var url = "sxzzjy_bjxfjsjg.do?method=bjxfjsjgUpdate&jgid="+rows[0]["jgid"]+"&sjly"+rows[0]["sjly"];
    var title = "修改";
    showDialog(title, 700,550, url);

}
function del(){
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length == 0) {
        showAlertDivLayer("请选择您要删除的记录！");
    } else {
        showConfirmDivLayer("您确定要删除选择的记录吗？", {
            "okFun": function () {
                jQuery.post("sxzzjy_bjxfjsjg.do?method=bjxfjsjgDel", {values: ids.toString()}, function (data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
            }
        });

    }
}



//导出
function exportConfig(){
    var DCCLBH='sxzzjy_bjxfjsjg.do';
    customExport(DCCLBH, exportData);
}
function exportData(){
    var shzt=jQuery("#shzt").val();
    var DCCLBH='sxzzjy_bjxfjsjg.do';
    setSearchTj();//设置高级查询条件
    var url = "sxzzjy_bjxfjsjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}

function printWord(hblx){
    var rows = jQuery("#dataTable").getSeletRow();
    var ids = jQuery("#dataTable").getSeletIds();
    var len = rows.length;
    var url = "";
    if (len == 1) {
        if(hblx != "sq" && (rows[0][hblx+"id"] == "" || rows[0][hblx+"id"]==null)){
            showAlertDivLayer("请选择已完成的记录！");
            return false;
        }
        url = "sxzzjy_bjxfjsjg.do?method=getPrint&hblx="+hblx;
        url += "&bjdm=" + rows[0]["bjdm"] + "&jgid=" + rows[0]["jgid"];
        window.open(url);
    } else if (len == 0) {
        showAlertDivLayer("请选择您要下载的记录！");
        return false;
    } else {
        if(hblx != "sq"){
            for(var i=0;i<rows.length;i++){
                if(rows[i][hblx+"id"] == "" || rows[i][hblx+"id"]==null){
                    showAlertDivLayer("请选择已完成的记录！");
                    return false;
                }
            }
        }

        url = "sxzzjy_bjxfjsjg.do?method=getPrintZip&hblx="+hblx;
        url += "&value=" + ids;
        window.open(url);
    }
}