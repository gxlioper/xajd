//Ԥ��ת��
function yjzj() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫת���Ԥ����");
        return false;
    }
    var url = "xyfd_yjgy.do?method=yjzj&xh=" + rows[0]["xh"] + "&t=" + new Date().getTime();
    showDialog("Ԥ��ת��", 800, 350, url);
}
//��Ԥ����
function gyjs() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ������Ԥ��Ԥ����");
        return false;
    }
    if(rows[0]["yjyy"]=="ת��Ԥ��"){
        showAlertDivLayer("ת��Ԥ�����а�����");
        return false;
    }
    showConfirmDivLayer("��ȷ��Ҫ������Ԥ�𣬸�Ԥ������Ϊ���ɹ���������",{"okFun":function(){
        jQuery.post("xyfd_yjgy.do?method=gyjs",
            {
                xh:rows[0]["xh"],
                yjyy:rows[0]["yjyy"]
            },
            function(data){
            showAlertDivLayer(data["message"]);
            jQuery("#dataTable").reloadGrid();
        },'json');
    }});
}