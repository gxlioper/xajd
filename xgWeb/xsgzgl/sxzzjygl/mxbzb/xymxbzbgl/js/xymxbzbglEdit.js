function save(type){
    editor.sync();
    if(!check()){
        showAlert("����д���б����");
        return false;
    }
    var html=editor.html();
    if(html==null||html==""){
        alertInfo("����д�������ģ�");
        return false;
    }
    jQuery("#test").val(html);
    var url = "sxzzjy_xymxbzbgl.do?method=xymxbzbglSave&type="+type;
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