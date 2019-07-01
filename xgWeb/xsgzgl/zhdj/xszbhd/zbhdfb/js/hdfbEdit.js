
function query(obj,lx){
   jQuery("#comp_title li").removeClass();
    jQuery(obj).parent().attr("class","ha");
    // jQuery("#shlx").val(shlx);
    if(lx =='yxz'){
        jQuery("#btn_sh").hide();
        jQuery("#btn_qxsh").show();
        jQuery("#dataTable").initGrid(gridSetting2);
    }else{
        jQuery("#btn_sh").show();
        jQuery("#btn_qxsh").hide();
        jQuery("#dataTable").initGrid(gridSetting);
    }
    searchRs();
}
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}


function save(type) {
    if(!check()){
        showAlertDivLayer("请填写所有必填项！");
        return ;
    }
    var qtdzb = jQuery("#qtdzb").is(':checked');
    var url = "xszbhd_hdfb.do?method=save&qtdzb="+qtdzb+"&type="+type;
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
    var flag = true;
    var hdzt = jQuery("#hdzt").val();
    var kssj = jQuery("#kssj").val();
    var jzsj = jQuery("#jzsj").val();
    var hdnr = jQuery("#hdnr").val();
    if(hdzt == "" || hdzt == null){
        flag = false;
    }
    if(kssj == "" || kssj == null){
        flag = false;
    }
    if(jzsj == "" || jzsj == null){
        flag = false;
    }
    if(hdnr == "" || hdnr == null){
        flag = false;
    }
    return flag;
}