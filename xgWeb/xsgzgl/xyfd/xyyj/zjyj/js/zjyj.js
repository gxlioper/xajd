function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}
//����
function jsyj() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ���յ�ת�飡");
        return false;
    }
    if(rows[0]["qrsj"]!=null&&rows[0]["qrsj"]!=""){
        showAlertDivLayer("��ת���ѽ��գ�");
        return false;
    }
    showConfirmDivLayer("������" + rows[0]["zjrxm"] + "ת���Ԥ����Ϣ��", {
        "okFun" : function() {
            jQuery.post("xyfd_zjyj.do?method=jszj", {
                    zjid : rows[0]["zjid"]
                },
                function(data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
        }
    });
}
//����Ԥ��
function fhyj() {

}