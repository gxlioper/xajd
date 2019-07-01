function saveShzt(){
    var shyj = jQuery("#shyj").val();
    if (jQuery.trim(shyj) == ""){
        showAlertDivLayer("«ÎÃÓ–¥…Û∫À“‚º˚£°");
        return false;
    }

    var url = "sxzzjy_grxfjssh.do?method=grxfjsSh&type=save";
    ajaxSubFormWithFun("GrxfjsshForm",url,function(data){
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
        showAlert("«ÎÃÓ–¥…Û∫À“‚º˚£°");
        return false;
    }
    var api = frameElement.api,W = api.opener;
    W.savePlsh(shzt,shyj);
    closeDialog();
}