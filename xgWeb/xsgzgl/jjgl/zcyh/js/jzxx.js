function zjznxx() {
    var len = jQuery(".tr_znxx").length;
    if(len > 6){
        showAlertDivLayer("��Ů��Ϣ���࣡");
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
        showAlertDivLayer("������дһ����Ů��Ϣ��");
        return false;
    }

    jQuery(obj).parent().parent().remove();
}

function save(type) {
    var checkId = "yhm-xm-sfzh-jtzz";
    if(!checkNotNull(checkId)){
        showAlertDivLayer("�뽫��������д������");
        return false;
    }

    if(jQuery("input[name=xb]:checked").length == 0){
        showAlert("��ѡ���Ա�");
        return false;
    }

    if(!isTelephone("lxdh")){
        showAlert("��ϵ�绰��ʽ���Ϸ���");
        return false;
    }

    //������дһ����Ů��Ϣ�������д��һ���ֶΣ�������дһ����Ϣ�е����б����ֶ�
    var result = false;
    jQuery(".tr_znxx").each(function () {
        if(jQuery(this).find("input[name$='xm']").val() == ""){
            showAlert("����д��Ů����");
            result = true;
            return false;
        }

        if(jQuery(this).find("input[name$='zdxx']").val() == ""){
            showAlert("����д��Ů�ڶ�ѧУ");
            result = true;
            return false;
        }

        if(jQuery(this).find("select[name$='nj']").val() == ""){
            showAlert("����д��Ů�꼶");
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
        if(data["message"]=="����ɹ���"){
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