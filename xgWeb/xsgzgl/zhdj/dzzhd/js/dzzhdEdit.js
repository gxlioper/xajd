//����֯��༭
jQuery(function(){
    init();
});

function init(){
    var id = jQuery("#id").val();
    var status = jQuery("#status").val();
    if(id != null && id.length > 0){
        var url = "zhdj_dzzhd.do?method=hdInfo&status=query&id="+id;
        jQuery.get(url, function (result) {
            var rst = JSON.parse(result);
            if (rst.code == 1) {
                var data = rst.data;
                jQuery("#hdmc").val(data.hdmc);
                jQuery("#kssj").val(data.kssj);
                jQuery("#jssj").val(data.jssj);
                if("view" != status){
                    if(data.hdnr!= null && data.hdnr.length > 0){
                        editor.html(data.hdnr);
                    }
                    var mxdxArray = data.mxdx.split(",");
                    if(mxdxArray!=null && mxdxArray.length > 0){
                        for(var i=0;i<mxdxArray.length;i++ ){
                            jQuery('input:checkbox[value='+mxdxArray[i]+']').prop('checked','true');
                        }
                    }
                }
            }
        });
    }
}
//��ȡ��Ϣ�洢
function hdSave(){
    if(jQuery("#hdmc").val()==''||jQuery("#kssj").val()==''||jQuery("#jssj").val()==''){
        showAlert("�뽫������Ϣ����д������");
        return false;
    }
    if(jQuery("#kssj").val() > jQuery("#jssj").val()){
        showAlert("����ʱ�䲻�ܴ��ڿ�ʼʱ��");
        return false;
    }
    var mxdx = "";
    jQuery("input:checkbox[name='mxdxs']:checked").each(function(){
        mxdx+=jQuery(this).val()+',';
    });
    if(mxdx.length >0){
        mxdx = mxdx.substring(0,mxdx.length-1);
    }
    var url = "zhdj_dzzhd.do?method=hdSave";
    var data = {};
    data.id = jQuery("#id").val();
    data.hdmc = jQuery("#hdmc").val();//�����
    data.fj = jQuery("#fj").val();//������Ϣ
    data.hdnr = editor.html();//�����
    if(data.hdnr == null || data.hdnr.length == 0){
        showAlert("����ݲ���Ϊ��");
        return false;
    }
    data.kssj = jQuery("#kssj").val();
    data.jssj = jQuery("#jssj").val();
    data.mxdx = mxdx;//������� ��Ϊ��
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