//����֯��ĵ�

//��ĵñ���
function hdxdSave(){
    var data = {}
    data.id = jQuery("#id").val();
    data.hdid = jQuery("#hdid").val();
    data.xh = jQuery("#xh").val();
    data.fj = jQuery("#fj").val();
    data.hdxd = editor.html();//��ĵ�
    if(data.hdxd == null || data.hdxd.trim().length==0){
        showAlert("��ĵò���Ϊ��!");
        return false;
    }
    var url = "zhdj_dzzhd.do?method=hdXdInfo&status=save";
    jQuery.post(url,data,function(result){
        if(result.code== '1'){
            showAlert(result.msg,{},{"clkFun":function(){
                    if (parent.window){
                        refershParent();
                    }
                }});
        }else{
            showAlert(result.msg);
            return false;
        }
    },'json');
}
