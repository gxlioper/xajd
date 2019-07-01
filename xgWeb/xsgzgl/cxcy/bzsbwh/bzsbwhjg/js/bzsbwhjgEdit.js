function save(type) {
    //var download = jQuery(".MultiFile-label").length;
    if(jQuery("#xh").val()==null||""==jQuery("#xh").val()){
        showAlert("请选择学生");
        return false;
    }
    var ids = "xmmc-bzje-sqly";
    if(check(ids) == false){
        showAlert("请将带<font color='red'>*</font>的项目填写完整");
        return false;
    }
    /*if(download < 1){
        showAlert("请上传附件");
        return false;
    }*/
    var url = "cxcy_bzsbwhjg.do?method=save&type=" + type;
    ajaxSubFormWithFun("form", url, function(data) {
        if(data["message"]=="保存成功！"){
            showAlert(data["message"],{},{"clkFun":function(){
                if (parent.window){
                    refershParent();
                }
            }});
        }else{
            showAlert(data["message"]);
        }
    });

}


/**
 * 验证是否存在空项
 * @param ids 要验证的控件id "-"分隔
 * @return
 */
function check(ids){
    var id=ids.split("-");
    for(var i=0;i<id.length;i++){
        var lddm=jQuery("#"+id[i]).val();
        if(lddm==null||""==lddm){
            return false;
        }
    }
    return true;
}