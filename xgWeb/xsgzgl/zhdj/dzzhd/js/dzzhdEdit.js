//党组织活动编辑
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
//获取信息存储
function hdSave(){
    if(jQuery("#hdmc").val()==''||jQuery("#kssj").val()==''||jQuery("#jssj").val()==''){
        showAlert("请将必填信息，填写完整！");
        return false;
    }
    if(jQuery("#kssj").val() > jQuery("#jssj").val()){
        showAlert("结束时间不能大于开始时间");
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
    data.hdmc = jQuery("#hdmc").val();//活动名称
    data.fj = jQuery("#fj").val();//附件信息
    data.hdnr = editor.html();//活动内容
    if(data.hdnr == null || data.hdnr.length == 0){
        showAlert("活动内容不能为空");
        return false;
    }
    data.kssj = jQuery("#kssj").val();
    data.jssj = jQuery("#jssj").val();
    data.mxdx = mxdx;//面向对象 可为空
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