//预警转介
function yjzj() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要转介的预警！");
        return false;
    }
    var url = "xyfd_yjgy.do?method=yjzj&xh=" + rows[0]["xh"] + "&t=" + new Date().getTime();
    showDialog("预警转介", 800, 350, url);
}
//干预结束
function gyjs() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要结束干预的预警！");
        return false;
    }
    if(rows[0]["yjyy"]=="转介预警"){
        showAlertDivLayer("转介预警已有案例！");
        return false;
    }
    showConfirmDivLayer("您确定要结束干预吗，干预结束将为生成工作案例？",{"okFun":function(){
        jQuery.post("xyfd_yjgy.do?method=gyjs",
            {
                xh:rows[0]["xh"],
                yjyy:rows[0]["yjyy"]
            },
            function(data){
            showAlertDivLayer(data["message"]);
            jQuery("#dataTable").reloadGrid();
        },'json');
    }});
}