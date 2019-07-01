jQuery(function(){
    var gridSetting = {
        caption : "创新创业补助申报列表",
        pager : "pager",
        url : "cxcy_bzsbwhsq.do?method=getList&type=query",
        colList : [
            { label : 'sqid', name : 'sqid', index : 'sqid',key : true, hidden : true },
            { label : 'splc', name : 'splc', index : 'splc',hidden : true },
            { label : '学号', name : 'xh', index : 'xh', width : '10%',formatter:xhLink },
            { label : '姓名', name : 'xm', index : 'xm', width : '8%' },
            { label : '项目名称', name : 'xmmc', index : 'xmmc', width : '14%' },
            { label : '补助金额（元）', name : 'bzje', index : 'bzje', width : '9%' },
            { label : '学年', name : 'xn', index : 'xn', width : '9%' },
            { label : '学期', name : 'xqmc', index : 'xqmc', width : '7%' },
            { label : '大队名称', name : 'xymc', index : 'xymc', width : '12%' },
            { label : '填报人', name : 'tbrmc', index : 'tbrmc', width : '8%' },
            { label : '记录时间', name : 'sqsj', index : 'sqsj', width : '15%' },
            { label : '审核状态', name : 'shztmc', index : 'shztmc', width : '8%' },
            { label : '审核状态', name : 'shzt', index : 'shzt', hidden : true}]
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
        + rowObject["sqid"]+"\");'>" + cellValue
        + "</a>";
}

function view(sqid) {
    showDialog("创新创业补助申报查看", 700, 450, "cxcy_bzsbwhsq.do?method=bzsbwhsqView&sqid="+sqid);
}
function add() {
    if(isopen==null||isopen==''){
        showAlertDivLayer('基础设置未初始化，请联系管理员！');
        return false;
    }
    if ("false" == isopen){
        showAlertDivLayer("当前未开放申请，请联系管理员！");
        return false;
    }
    var url = "cxcy_bzsbwhsq.do?method=bzsbwhsqAdd";
    var title = "新增创新创业补助申请";
    showDialog(title, 700, 550, url);
}

function update() {
    var isopen = jQuery("#isopen").val();
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要修改的记录！");
    }else {
        var shzt = rows[0]["shzt"];
        if ("0" != shzt&&"3" != shzt) {
            showAlertDivLayer("只有未提交和已退回的记录才能修改！");
            return false;
        }
        if(isopen==null||isopen==''){
            showAlertDivLayer('基础设置未初始化，请联系管理员！');
            return false;
        }
        if ("false" == isopen){
            showAlertDivLayer("当前未开放申请，请联系管理员！");
            return false;
        }
        var url = 'cxcy_bzsbwhsq.do?method=bzsbwhsqUpdate&sqid=' + rows[0]["sqid"];
        var title = "创新创业补助申报修改";
        showDialog(title, 800, 550, url);
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
                jQuery.post("cxcy_bzsbwhsq.do?method=bzsbwhsqDel", {
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
function submit() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    var isopen = jQuery("#isopen").val();
    if (ids.length != 1) {
        showAlertDivLayer("请选择一条您要提交的记录！");
        return false;
    }
    if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
        showAlertDivLayer("请选择未提交或者已退回的记录！");
        return false;
    }
    if(isopen==null||isopen==''){
        showAlertDivLayer('基础设置未初始化，请联系管理员！');
        return false;
    }
    if ("false" == isopen){
        showAlertDivLayer("当前未开放申请，请联系管理员！");
        return false;
    }
    showConfirmDivLayer("您确定要提交选择的记录吗？", {
        "okFun" : function() {
            jQuery.post("cxcy_bzsbwhsq.do?method=bzsbwhsqSubmit", {
                sqid : ids.toString()
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
                jQuery.post("cxcy_bzsbwhsq.do?method=bzsbwhsqCancel", {
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
    showDialog("创新创业补助申报审批流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
        + rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
}

function exportConfig(){
    var DCCLBH='cxcy_bzsbwh.do';
    customExport(DCCLBH, exportData);
}
function exportData(){
    var DCCLBH='cxcy_bzsbwh.do';
    setSearchTj();//设置高级查询条件
    var url = "cxcy_bzsbwhsq.do?method=exportData&dcclbh=" + DCCLBH+
        "&pkValue="+jQuery("#pkValue").val();//dcclbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}
