
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}

function xqLink(cellValue, rowObject) {
    return "<a href = 'javascript:void(0);'  onclick=\"showDialog('�鿴' , 600,440 , 'xszbhd_hdfb.do?method=hdDetail&doType=view&hdid=" + rowObject['hdid'] + "');return false;\" >�鿴</a>";
}
function jdLink(cellValue, rowObject) {
    return "<a href = 'javascript:void(0);' class='name' onclick=\"showDialog('����' , 600,440 , 'xszbhd_hdfb.do?method=hdjdDetail&doType=view&hdid=" + rowObject['hdid'] + "');return false;\" >����</a>";
}
function query() {
    var map = {};
    map["xmlbmc"] = jQuery("#xmlbmc").val();
    jQuery("#dataTable").reloadGrid(map);
}

function add() {
    var url = "xszbhd_hdfb.do?method=add";
    var title = "�����";
    showDialog(title, 600, 480, url);
}

function update() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
    }else {
        jQuery.post("xszbhd_hdfb.do?method=checkEdit", {hdid: rows[0]["hdid"]}, function (data) {
            var message="";
            if(data == 1){
                showAlertDivLayer("���������ύ�������������");
                jQuery("#dataTable").reloadGrid();
            }else if(data == 2){
                showAlertDivLayer("��ѿ�ʼ��ʼ�������������");
                jQuery("#dataTable").reloadGrid();
            }else  if(data == 0){
                var url = 'xszbhd_hdfb.do?method=update&hdid=' + rows[0]["hdid"];
                var title = "�޸ķ����";
                showDialog(title, 600, 480, url);
            }else{
                showAlert("����ʧ�ܣ�");
            }

        });


    }
}
function del() {
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length == 0) {
        showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
    } else {
        jQuery.post("xszbhd_hdfb.do?method=checkEdit&type=del", {values: ids.toString()}, function (data) {
            if(data == 1){
                showAlertDivLayer("���������ύ�������������");
                jQuery("#dataTable").reloadGrid();
            }else if(data == 2){
                showAlertDivLayer("��ѿ�ʼ��ʼ�������������");
                jQuery("#dataTable").reloadGrid();
            }else if(data == 0){
                showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
                    "okFun": function () {
                        jQuery.post("xszbhd_hdfb.do?method=del", {values: ids.toString()}, function (data) {
                            showAlertDivLayer(data["message"]);
                            jQuery("#dataTable").reloadGrid();
                        }, 'json');
                    }
                });
            }else{
                showAlertDivLayer("����ʧ�ܣ�");
                //jQuery("#dataTable").reloadGrid();
            }

        });

    }
}

function selectDzb(){
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length > 1 || ids.length == 0) {
        showAlertDivLayer("��ѡ��һ����Ҫ����Ļ��");
    } else {
        var url = "xszbhd_hdfb.do?method=selectDzb&hdid="+ids[0].toString();
        var title = "ѡ������֧��";
        showDialog(title, 750, 550, url);
    }
}
