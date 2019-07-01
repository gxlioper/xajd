function selectStu(){
    var url = "sxzzjy_grxfjssq.do?method=selectStu";
    var title = "选择班级";
    showDialog(title,800,550,url);
}
function save(type){
    if(!check()){
        showAlert("请填写所有必填项！");
        return false;
    }
    var url = "sxzzjy_grxfjsjg.do?method=grxfjsjgSave&type="+type;
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
    var xh = jQuery.trim(jQuery("#xh").val());
    var xfjsmc = jQuery.trim(jQuery("#xfjsmc").val());
    var sblx = jQuery.trim(jQuery("#sblx").val());
    var jssl = jQuery.trim(jQuery("#jssl").val());
    var i = 0;
    jQuery("#bxnmbTable input").each(function(obj){
        if(isNull(jQuery(this).val())){
            i++;
        }
    });
    if(isNull(xh) || isNull(xfjsmc) || isNull(sblx) ||  isNull(jssl) || i>0 ){
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