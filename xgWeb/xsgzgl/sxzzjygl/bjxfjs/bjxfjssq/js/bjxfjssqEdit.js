function selectBj(){
    var url = "sxzzjy_bjxfjssq.do?method=selectBj";
    var title = "选择班级";
    showDialog(title,700,550,url);
}
function saveBj(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条班级记录！");
    }else {
        var o = rows[0];
        var api = frameElement.api;
        var parent = jQuery(api.get('parentDialog').document);
        jQuery.post("sxzzjy_bjxfjssq.do?method=getBjInfo", {bjdm: o["bjdm"]}, function (dataList) {
            var data = dataList[0];
            var dybl = 0;
            if(data.dyrs > 0)
                dybl = (data.dyrs*100/data.bjzrs).toFixed(2)+"%";
            parent.find("#xyTd").html(data.xymc);
            parent.find("#bjrsTd").html("共"+data.bjzrs+"人（男"+data.nansrs+"人，女"+data.nvsrs+"人）");
            parent.find("#dysTD").html(data.dyrs);
            parent.find("#dyblTD").html(dybl);
            parent.find("#fdyTD").html(data.fdyxm);
            parent.find("#bzrTD").html(data.bzrxm );
            parent.find("#bzTD").html(data.bzxm );
            parent.find("#tzsTD").html(data.tzsxm );

            parent.find("#bjmc").val(o["bjmc"]);
            parent.find("#bjdm").val(o["bjdm"]);
            Close();
        }, 'json');


    }
}
function saveForm_add(type){
    if(!check()){
        showAlert("请填写所有必填项！");
        return false;
    }
    jQuery("#bxnmbTable").find("input").each(function(obj){
        if(isNull(jQuery(this).val())){
            showAlert("请将本学年目标填写完成！");
            return false;
        }
    });
    var url = "sxzzjy_bjxfjssq.do?method=bjxfjssqAdd&type="+type;
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
    var url = "sxzzjy_bjxfjssq.do?method=bjxfjssqUpdate&type="+type;
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
    var bjmc = jQuery("#bjmc").val();
    var xfjsmc = jQuery("#xfjsmc").val();
    var sblx = jQuery("#sblx").val();
    var jssl = jQuery("#jssl").val();
    if(isNull(bjmc) || isNull(xfjsmc) || isNull(sblx) ||  isNull(jssl) ){
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