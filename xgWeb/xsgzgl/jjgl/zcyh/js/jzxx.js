function zjznxx() {
    var len = jQuery(".tr_znxx").length;
    if(len > 6){
        showAlertDivLayer("子女信息过多！");
        return false;
    }

    var tr_znxx = jQuery("#tr_znxx0").clone(true);
    tr_znxx.removeAttr("id");
    tr_znxx.find("input,select").each(function () {
        var name = jQuery(this).attr("name");
        jQuery(this).attr("name","znxxModelList["+len+"]."+name.split(".")[1]);
    });

    jQuery("#tr_zjznxx").before(tr_znxx);
}

function scznxx(obj) {
    if(jQuery(".tr_znxx").length == 1){
        showAlertDivLayer("至少填写一条子女信息！");
        return false;
    }

    jQuery(obj).parent().parent().remove();
}

function save(type) {
    var checkId = "yhm-xm-sfzh-jtzz";
    if(!checkNotNull(checkId)){
        showAlertDivLayer("请将必填项填写完整！");
        return false;
    }

    if(jQuery("input[name=xb]:checked").length == 0){
        showAlert("请选择性别！");
        return false;
    }

    if(!isTelephone("lxdh")){
        showAlert("联系电话格式不合法！");
        return false;
    }

    //必须填写一条子女信息，如果填写了一个字段，必须填写一条信息中的所有必填字段
    var result = false;
    jQuery(".tr_znxx").each(function () {
        if(jQuery(this).find("input[name$='xm']").val() == ""){
            showAlert("请填写子女姓名");
            result = true;
            return false;
        }

        if(jQuery(this).find("input[name$='zdxx']").val() == ""){
            showAlert("请填写子女在读学校");
            result = true;
            return false;
        }

        if(jQuery(this).find("select[name$='nj']").val() == ""){
            showAlert("请填写子女年级");
            result = true;
            return false;
        }
    });

    if(result){
        return false;
    }

    var url = "jjgl_zcyhgl.do?method=jzxxSaveForAdd";
    if(type=="edit"){
        url = "jjgl_zcyhgl.do?method=jzxxSaveForEdit";
    }
    ajaxSubFormWithFun("zcyhForm", url, function(data) {
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