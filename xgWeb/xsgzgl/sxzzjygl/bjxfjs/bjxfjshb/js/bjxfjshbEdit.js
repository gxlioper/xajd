function saveForm_add(type){
    if(!check()){
        showAlert("����д���б����");
        return false;
    }
    var url = "sxzzjy_bjxfjshb.do?method=bjxfjshbAdd&type="+type;
    ajaxSubFormWithFun("form",url,function(data){
        if(data["message"]=="����ɹ���" || data["message"]=="�ύ�ɹ���"){
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
        showAlert("����д���б����");
        return false;
    }
    var url = "sxzzjy_bjxfjshb.do?method=bjxfjshbUpdate&type="+type;
    ajaxSubFormWithFun("form",url,function(data){
        if(data["message"]=="����ɹ���" || data["message"]=="�ύ�ɹ���"){
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
    var i = 0;
    jQuery("table input").each(function(obj){
        if(isNull(jQuery(this).val())){
            i++;
        }
    });
    if(isNull(zd1) || isNull(zd2) || isNull(zd3) || i>0){
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