function save(type) {
    //var download = jQuery(".MultiFile-label").length;
    if(jQuery("#xh").val()==null||""==jQuery("#xh").val()){
        showAlert("��ѡ��ѧ��");
        return false;
    }
    var ids = "xmmc-bzje-sqly";
    if(check(ids) == false){
        showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
        return false;
    }
    /*if(download < 1){
        showAlert("���ϴ�����");
        return false;
    }*/
    var url = "cxcy_bzsbwhsq.do?method=save&type=" + type;
    ajaxSubFormWithFun("form", url, function(data) {
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


/**
 * ��֤�Ƿ���ڿ���
 * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function check(ids){
    var id=ids.split("-");
    for(var i=0;i<id.length;i++){
        var lddm=jQuery("#"+id[i]).val();
        if(lddm==null||""==lddm){
            return false;
        }
    }
    return true;
}