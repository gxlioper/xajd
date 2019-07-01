
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}

function xqLink(cellValue, rowObject) {
    return "<a href = 'javascript:void(0);'  onclick=\"showDialog('查看' , 600,440 , 'xszbhd_hdfb.do?method=hdDetail&doType=view&hdid=" + rowObject['hdid'] + "');return false;\" >查看</a>";
}
function jdLink(cellValue, rowObject) {
    return "<a href = 'javascript:void(0);' class='name' onclick=\"showDialog('进度' , 600,440 , 'xszbhd_hdfb.do?method=hdjdDetail&doType=view&hdid=" + rowObject['hdid'] + "');return false;\" >进度</a>";
}
function query() {
    var map = {};
    map["xmlbmc"] = jQuery("#xmlbmc").val();
    jQuery("#dataTable").reloadGrid(map);
}

function add() {
    var url = "xszbhd_hdfb.do?method=add";
    var title = "发布活动";
    showDialog(title, 600, 480, url);
}

function update() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要修改的记录！");
    }else {
        jQuery.post("xszbhd_hdfb.do?method=checkEdit", {hdid: rows[0]["hdid"]}, function (data) {
            var message="";
            if(data == 1){
                showAlertDivLayer("已有数据提交，不允许操作！");
                jQuery("#dataTable").reloadGrid();
            }else if(data == 2){
                showAlertDivLayer("活动已开始开始，不允许操作！");
                jQuery("#dataTable").reloadGrid();
            }else  if(data == 0){
                var url = 'xszbhd_hdfb.do?method=update&hdid=' + rows[0]["hdid"];
                var title = "修改发布活动";
                showDialog(title, 600, 480, url);
            }else{
                showAlert("操作失败！");
            }

        });


    }
}
function del() {
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length == 0) {
        showAlertDivLayer("请选择您要删除的记录！");
    } else {
        jQuery.post("xszbhd_hdfb.do?method=checkEdit&type=del", {values: ids.toString()}, function (data) {
            if(data == 1){
                showAlertDivLayer("已有数据提交，不允许操作！");
                jQuery("#dataTable").reloadGrid();
            }else if(data == 2){
                showAlertDivLayer("活动已开始开始，不允许操作！");
                jQuery("#dataTable").reloadGrid();
            }else if(data == 0){
                showConfirmDivLayer("您确定要删除选择的记录吗？", {
                    "okFun": function () {
                        jQuery.post("xszbhd_hdfb.do?method=del", {values: ids.toString()}, function (data) {
                            showAlertDivLayer(data["message"]);
                            jQuery("#dataTable").reloadGrid();
                        }, 'json');
                    }
                });
            }else{
                showAlertDivLayer("操作失败！");
                //jQuery("#dataTable").reloadGrid();
            }

        });

    }
}

function selectDzb(){
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length > 1 || ids.length == 0) {
        showAlertDivLayer("请选择一条您要分配的活动！");
    } else {
        var url = "xszbhd_hdfb.do?method=selectDzb&hdid="+ids[0].toString();
        var title = "选择面向党支部";
        showDialog(title, 750, 550, url);
    }
}
