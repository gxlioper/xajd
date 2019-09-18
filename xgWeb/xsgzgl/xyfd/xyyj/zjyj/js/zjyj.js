function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}
//接收
function jsyj() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要接收的转介！");
        return false;
    }
    if(rows[0]["qrsj"]!=null&&rows[0]["qrsj"]!=""){
        showAlertDivLayer("该转介已接收！");
        return false;
    }
    showConfirmDivLayer("接收由" + rows[0]["zjrxm"] + "转介的预警信息？", {
        "okFun" : function() {
            jQuery.post("xyfd_zjyj.do?method=jszj", {
                    zjid : rows[0]["zjid"]
                },
                function(data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
        }
    });
}
//返回预警
function fhyj() {

}