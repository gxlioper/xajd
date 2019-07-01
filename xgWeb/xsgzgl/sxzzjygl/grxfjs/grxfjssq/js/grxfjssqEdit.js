function selectStu(){
    var url = "sxzzjy_grxfjssq.do?method=selectStu";
    var title = "选择班级";
    showDialog(title,800,550,url);
}
function saveStu(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条学生记录！");
    }else {
        var o = rows[0];
        var api = frameElement.api;
        var parent = jQuery(api.get('parentDialog').document);
        var qsh = "";
        if(!isNull(o.qsh)) qsh += o.qsh + "寝室";
        var ldmc = "";
        if(!isNull(o.ldmc)) ldmc = o.ldmc;
        parent.find("#xyTd").html(o.xymc);
        parent.find("#bjTd").html(o.bjmc);
        parent.find("#xh").val(o.xh);
        parent.find("#xmTd").html(o.xm);
        parent.find("#xbTd").html(o.xb);
        parent.find("#mzTd").html(o.mz);
        parent.find("#ssTd").html(ldmc + qsh);
        parent.find("#zzmmTd").html(o.zzmmmc);
        parent.find("#lxdhTd").html(o.lxdh);
        Close();
    }
}
function saveForm_add(type){
    if(!check()){
        showAlert("请填写所有必填项！");
        return false;
    }
    var url = "sxzzjy_grxfjssq.do?method=grxfjssqAdd&type="+type;
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
    var url = "sxzzjy_grxfjssq.do?method=grxfjssqUpdate&type="+type;
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
    var xh = jQuery("#xh").val();
    var xfjsmc = jQuery("#xfjsmc").val();
    var sblx = jQuery("#sblx").val();
    var jssl = jQuery("#jssl").val();
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