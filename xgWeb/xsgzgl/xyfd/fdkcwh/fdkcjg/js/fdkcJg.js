var checkId = 'xh-hdmc-dd-sj-zbdw-ddssx-xn-xq';
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='view(\""
        + rowObject["jgid"] + "\");'>" + cellValue
        + "</a>";
}
//�鿴
function view(jgid) {
    showDialog("�����γ̽���鿴", 800, 550, "xyfd_fdkcjg.do?method=viewFdkcjg&jgid="+jgid);
}

//����
function add() {
    var height = jQuery(window).height();
    var url = "xyfd_fdkcjg.do?method=addFdkcjg";
    var title = "�����γ̽������";
    showDialog(title, 800, height-250, url);
}

//�޸�
function update() {
    var rows = jQuery("#dataTable").getSeletRow();
    var height = jQuery(window).height();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
    } else {
        if(rows[0]['sjly']=='1'){
            showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
            return false;
        }
        var url = 'xyfd_fdkcjg.do?method=updateFdkcjg&jgid=' + rows[0]["jgid"]
            + '&djh=' + rows[0]["djh"];
        var title = "�����γ̽���޸�";
        showDialog(title, 800, height-250, url);
    }
}
// ɾ��
function del() {
    var ids = jQuery("#dataTable").getSeletIds();

    if (ids.length == 0){
        showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
    } else {
        var rows = jQuery("#dataTable").getSeletRow();
        for(var i=0;i<ids.length;i++){
            if(rows[i]['sjly']=='1'){
                showAlertDivLayer("������̹����ļ�¼����ɾ����");
                return false;
            }
        }
        showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
            jQuery.post("xyfd_fdkcjg.do?method=del",{values:ids.toString()},function(data){
                showAlertDivLayer(data["message"]);
                jQuery("#dataTable").reloadGrid();
            },'json');
        }});
    }
}
//����
function dr() {
    // ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
    toImportDataNew("IMPORT_XYFD_PBJG");
    return false;

}
var DCCLBH = "xyfd_xyfd_pbjg.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
    //DCCLBH�������ܱ��,ִ�е�������
    customExport(DCCLBH, exportData);
}

//��������
function exportData() {
    setSearchTj();//���ø߼���ѯ����
    var url = "xyfd_pbjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}