function save(flag){
    if(!check()){
        showAlertDivLayer("请填写所有必填项！");
        return ;
    }
    var type = jQuery("#type").val();
    if(type == "zxsb" && isNull(jQuery("#hdzt").val())){
        showAlertDivLayer("请填写所有必填项！");
        return ;
    }
    var url = "xszbhd_hdsb.do?method=save&flag="+flag;
    ajaxSubFormWithFun("form", url, function(data) {
        if (data["success"] != undefined && !data["success"]) {
            showAlert(data["message"]);
        } else {
            showAlert(data["message"], {}, {
                "clkFun" : function(tag) {
                    if (tag == "ok") {
                        refershParent();
                    }
                }
            });
        }
    });

}

function check(){

    var hdsj = jQuery("#hdsj").val();
    var hddd = jQuery("#hddd").val();
    var ydrs = jQuery("#ydrs").val();
    var sdrs = jQuery("#sdrs").val();
    var qjrs = jQuery("#qjrs").val();
    var wgbdrs = jQuery("#wgbdrs").val();
    var shykdrhd = jQuery("#shykdrhd").val();
    var hdlx = jQuery("#hdlx").val();
    //var hdzt = jQuery("#hdzt").val();
    var hdsc = jQuery("#hdsc").val();
    var tjsxbgrs = jQuery("#tjsxbgrs").val();
    var hyjl = jQuery("#hyjl").val();
    if(isNull(hdsj) || isNull(hddd) || isNull(ydrs) || isNull(sdrs)
       || isNull(qjrs) || isNull(wgbdrs) || isNull(shykdrhd) || isNull(hdlx)
       || isNull(hdsc) || isNull(tjsxbgrs) || isNull(hyjl) ){
        return false;
    }else{
        return true;
    }

}
function isNull(value){
    if(value == "" || value == null){
        return true;
    } else{
        return false;
    }
}