/**
 * 弹框：家长信息选择列表
 */
function showJzxxList() {
    var url = "jjgl_xqwhgl.do?method=showJzxxList";
    var title = "家长信息列表";
    showDialog(title, 800, 500, url);
}

/**
 * 切换子女信息时调用
 */
function changeZnxx() {
    var znxb = jQuery("#znid  option:selected").attr("znxb");
    jQuery("#znxb").text(znxb);
}

/**
 * 家教需求保存
 */
function save(type) {
    var checkId = 'sqr-znid-jjxk-jjnj-jjdd-jjsx-jjlsyq';

    if(!checkNotNull(checkId)){
        showAlertDivLayer("请将必填项填写完整！");
        return false;
    }

    if (jQuery("#jjlsyq").val().length>500) {
        showAlert("家教要求最多输入500字！");
        return false;
    }

    if (jQuery("#bz").val().length>500) {
        showAlert("备注最多输入500字！");
        return false;
    }

    var url = "jjgl_xqwhgl.do?method=xqwhSaveForAdd";
    if(type === 'edit'){
        url = "jjgl_xqwhgl.do?method=xqwhSaveForEdit";
    }
    ajaxSubFormWithFun("xqwhForm", url, function(data) {
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