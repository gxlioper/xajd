function saveShzt(){
    var shyj = jQuery("#shyj").val();
    if (jQuery.trim(shyj) == ""){
        showAlertDivLayer("����д��������");
        return false;
    }

    var url = "sxzzjy_bjxfjssh.do?method=bjxfjsSh&type=save";
    ajaxSubFormWithFun("BjxfjsshForm",url,function(data){
        showAlertDivLayer(data["message"],{},{"clkFun":function(){
            if (parent.window){
                refershParent();
            }
        }});
    });
}

function savePlsh(shzt){
    var shyj = jQuery("#shyj").val();
    if (shyj == ""){
        showAlert("����д��������");
        return false;
    }
    var api = frameElement.api,W = api.opener;
    W.savePlsh(shzt,shyj);
    closeDialog();
}