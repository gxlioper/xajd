function saveForm_add(type){
    if(!check()){
        showAlert("请填写所有必填项！");
        return false;
    }
    var url = "sxzzjy_grxfjshb.do?method=grxfjshbAdd&type="+type;
    ajaxSubFormWithFun("form",url,function(data){
        if(data["message"]=="保存成功！" || data["message"]=="提交成功！"){
            showAlert(data["message"],{},{"clkFun":function(){
                if (parent.window){
                    refershParent();
                }
            }});
        }else{
            showAlert(data["message"]);
        }});
}
function saveForm_update(type){
    if(!check()){
        showAlert("请填写所有必填项！");
        return false;
    }
    var url = "sxzzjy_grxfjshb.do?method=grxfjshbUpdate&type="+type;
    ajaxSubFormWithFun("form",url,function(data){
        if(data["message"]=="保存成功！" || data["message"]=="提交成功！"){
            showAlert(data["message"],{},{"clkFun":function(){
                if (parent.window){
                    refershParent();
                }
            }});
        }else{
            showAlert(data["message"]);
        }});
}




function check(){
    var zd1 = jQuery("#zd1").val();
    var zd2 = jQuery("#zd2").val();
    var zd3 = jQuery("#zd3").val();
    var zd4 = jQuery("#zd4").val();
    var i = 0;
    jQuery("table input").each(function(obj){
        if(isNull(jQuery(this).val())){
            i++;
        }
    });
    if(isNull(zd1) || isNull(zd2) || isNull(zd3) || isNull(zd4) || i>0){
        return false;
    }else{
        return true;
    }

}

function isNull(val){
    if(val == "" || val == null){
        return true;
    }else{
        return false;
    }
}