function save(type){
    editor.sync();
    if(!check()){
        showAlert("请填写所有必填项！");
        return false;
    }
    var html=editor.html();
    if(html==null||html==""){
        alertInfo("请填写新闻正文！");
        return false;
    }
    jQuery("#test").val(html);
    var url = "sxzzjy_xymxbzbgl.do?method=xymxbzbglSave&type="+type;
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
    var newstitle = jQuery("#newstitle").val();
    var newstype = jQuery("#newstype").val();
    var sffb = jQuery("input[name='sffb']:checked").val();
    var sfzd = jQuery("input[name='sfzd']:checked").val();
    if(isNull(newstitle) || isNull(newstype) || isNull(sffb) ||  isNull(sfzd) ){
        return false;
    }else{
        return true;
    }
}

function isNull(val){
    if(val == "" || val == null || val == undefined){
        return true;
    }else{
        return false;
    }
}