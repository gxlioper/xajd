jQuery(function(){
    var gridSetting = {
        caption : "活动补录列表",
        pager : "pager",
        url : "cxcy_bzsbwhjg.do?method=getList&type=query",
        colList : [
            { label : 'jgid', name : 'jgid', index : 'jgid',key : true, hidden : true },
            { label : '学号', name : 'xh', index : 'xh', width : '10%',formatter:xhLink },
            { label : '姓名', name : 'xm', index : 'xm', width : '8%' },
            { label : '项目名称', name : 'xmmc', index : 'xmmc', width : '14%' },
            { label : '补助金额（元）', name : 'bzje', index : 'bzje', width : '9%' },
            { label : '学年', name : 'xn', index : 'xn', width : '9%' },
            { label : '学期', name : 'xqmc', index : 'xqmc', width : '7%' },
            { label : '大队名称', name : 'xymc', index : 'xymc', width : '12%' },
            { label : '填报人', name : 'tbrmc', index : 'tbrmc', width : '8%' },
            { label : '记录时间', name : 'lrsj', index : 'lrsj', width : '15%' },
            { label : '数据来源', name : 'sjly', index : 'sjly', hidden : true}]
    };
    var map = getSuperSearch();
    gridSetting["params"] = map;
    jQuery("#dataTable").initGrid(gridSetting);
});
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='view(\""
        + rowObject["jgid"]+"\");'>" + cellValue
        + "</a>";
}

function view(jgid) {
    showDialog("创新创业补助申报查看", 700, 450, "cxcy_bzsbwhjg.do?method=bzsbwhjgView&jgid="+jgid);
}
function add() {
    var url = "cxcy_bzsbwhjg.do?method=bzsbwhjgAdd";
    var title = "新增创新创业补助申请";
    showDialog(title, 700, 550, url);
}

function update() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要修改的记录！");
    }else {
        if(rows[0]['sjly'] == '1' ){
            showAlertDivLayer("审核流程数据不能修改！");
            return false;
        }
        var url = 'cxcy_bzsbwhjg.do?method=bzsbwhjgUpdate&jgid=' + rows[0]["jgid"];
        var title = "创新创业补助申请修改";
        showDialog(title, 800, 550, url);
    }

}

// 删除
function del() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length == 0) {
        showAlertDivLayer("请选择您要删除的记录！");
    } else {
        var flag = true;
        jQuery(rows).each(function(i,row){
            if(row["sjly"] == "1"){
                flag = false;
                return;
            }
        });
        if(!flag){
            showAlertDivLayer("审核流程数据不能删除！");
            return false;
        }
        showConfirmDivLayer("您确定要删除选择的记录吗？", {
            "okFun" : function() {
                jQuery.post("cxcy_bzsbwhjg.do?method=bzsbwhjgDel", {
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

function exportConfig(){
    var DCCLBH='cxcy_bzsbwh_jg.do';
    customExport(DCCLBH, exportData);
}
function exportData(){
    var DCCLBH='cxcy_bzsbwh_jg.do';
    setSearchTj();//设置高级查询条件
    var url = "cxcy_bzsbwhjg.do?method=exportData&dcclbh=" + DCCLBH+
        "&pkValue="+jQuery("#pkValue").val();//dcclbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}
