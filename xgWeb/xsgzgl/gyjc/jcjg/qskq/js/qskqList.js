var gridSetting = {
    caption: "寝室考勤列表",
    pager: "pager",
    url: "gyjc_qskq.do?method=getList&type=query",
    colList: [
        {label: 'lddm', name: 'lddm', index: 'lddm',hidden:true},
        {label: 'jcrq', name: 'jcrq', index: 'jcrq',hidden:true},
        {label: '学号', name: 'xh', index: 'xh',width: '10%',formatter:xhLink},
        {label: '姓名', name: 'xm', index: 'xm', width: '7%'},
        {label: '班级', name: 'bjmc', index: 'bjmc', width: '15%'},
        {label: '学院', name: 'xymc', index: 'xymc', width: '12%'},
        {label: '楼栋', name: 'ldmc', index: 'ldmc', width: '10%'},
        {label: '寝室号', name: 'qsh', index: 'qsh', width: '5%'},
        {label: '床位号', name: 'cwh', index: 'cwh', width: '5%'},
        {label: '学年', name: 'xn', index: 'xn', width: '8%'},
        {label: '学期', name: 'xqmc', index: 'xqmc', width: '7%'},
        {label: '考勤类别名称', name: 'kqlbmc', index: 'kqlbmc', width: '10%'},
        {label: '检查时间', name: 'jcsj', index: 'jcsj', width: '11%'}

    ]
    /* sortname: "lrsj",
     sortorder: "desc"*/
};
jQuery(function () {
    jQuery("#dataTable").initGrid(gridSetting);

});

function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}

function view(xh,jcrq) {
    showDialog("查看", 700,350, "gyjc_qskq.do?method=qskqView&xh=" + xh+"&jcrq="+jcrq);
}

function xhLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='view(\"" + rowObject["xh"] + "\",\""+rowObject["jcrq"]+"\");'>" + cellValue
        + "</a>";
}
function update(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要修改的记录！");
        return false;
    }
    var url = 'gyjc_qskq.do?method=qskqUpdate&xh=' + rows[0]["xh"]+"&jcrq="+rows[0]["jcrq"];
    var title = "修改";
    showDialog(title,700,350,url);
}
function save(){
    var url = "gyjc_qskq.do?method=qskqSave";
    ajaxSubFormWithFun("qskqForm", url, function(data) {
        if (data["message"] == "保存成功！") {
            showAlert(data["message"], {}, {
                "clkFun" : function() {
                    if (parent.window) {
                        refershParent();
                    }
                }
            });
        } else {
            showAlert(data["message"]);
        }

    });
}
//导出
var DCCLBH='gyjc_qskq.do';
function exportConfig(){     //导出

    customExport(DCCLBH, exportData);
}
function exportData(){
    setSearchTj();//设置高级查询条件
    var url = "gyjc_qskq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}