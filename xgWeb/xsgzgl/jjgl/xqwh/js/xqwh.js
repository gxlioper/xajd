/**
 * ���򣺼ҳ���Ϣѡ���б�
 */
function showJzxxList() {
    var url = "jjgl_xqwhgl.do?method=showJzxxList";
    var title = "�ҳ���Ϣ�б�";
    showDialog(title, 800, 500, url);
}

/**
 * �л���Ů��Ϣʱ����
 */
function changeZnxx() {
    var znxb = jQuery("#znid  option:selected").attr("znxb");
    jQuery("#znxb").text(znxb);
}

/**
 * �ҽ����󱣴�
 */
function save(type) {
    var checkId = 'sqr-znid-jjxk-jjnj-jjdd-jjsx-jjlsyq';

    if(!checkNotNull(checkId)){
        showAlertDivLayer("�뽫��������д������");
        return false;
    }

    if (jQuery("#jjlsyq").val().length>500) {
        showAlert("�ҽ�Ҫ���������500�֣�");
        return false;
    }

    if (jQuery("#bz").val().length>500) {
        showAlert("��ע�������500�֣�");
        return false;
    }

    var url = "jjgl_xqwhgl.do?method=xqwhSaveForAdd";
    if(type === 'edit'){
        url = "jjgl_xqwhgl.do?method=xqwhSaveForEdit";
    }
    ajaxSubFormWithFun("xqwhForm", url, function(data) {
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