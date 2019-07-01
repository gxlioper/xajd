function save(){
    if(!check()){
        showAlertDivLayer("请填写所有必填项！");
        return false;
    }
    var url = "zhdj_djgzjl.do?method=save";
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
    var yhjs = jQuery("#yhjs").val();
    var sjhjs = jQuery("#sjhjs").val();
    var jdbksdyfzrs = jQuery("#jdbksdyfzrs").val();
    var jdyjsdyfzrs = jQuery("#jdyjsdyfzrs").val();
    var sfasjndf = jQuery("#sfasjndf").val();
    if(isNull(yhjs) || isNull(sjhjs) || isNull(jdbksdyfzrs) ||
        isNull(jdyjsdyfzrs) || isNull(sfasjndf) ){
        return false;
    }else{
        return true;
    }

}
function isNull(value){
    if(value == "" || value == null){
        return true;
    }else {
        return false;
    }
}