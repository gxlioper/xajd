var checkId = 'xh-hdmc-dd-sj-zbdw-ddssx-xn-xq';
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='view(\""
        + rowObject["jgid"] + "\",\"" + rowObject["sjly"] + "\",\"" + cellValue + "\");'>" + cellValue
        + "</a>";
}
//�鿴
function view(id,sjly,xh) {
    showDialog(jQuery("#gnmkmc").val()+"�鿴", 800, 550, "llxxjl_jg.do?method=llxxjlJgView&jgid="
        + id + "&sjly=" + sjly + "&xh=" + xh );
}
// ����
function save(type) {
    if(!checkNotNull(checkId)){
        showAlert("�뽫��<font class='red'>*</font>����Ŀ��д������");
        return false;
    }
    var url = "llxxjl_jg.do?method=save&type=" + type;
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
//����
function add() {
    var url = "llxxjl_jg.do?method=llxxjlJgAdd";
    var title = jQuery("#gnmkmc").val()+"��д";
    showDialog(title, 800, 550, url);
}

//�޸�
function update() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
    } else {
        if(rows[0]['sjly']=='1'){
            showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
            return false;
        }else if(rows[0]['sjly']=='ek'||rows[0]['sjly']=='ekbl'){
            showAlertDivLayer("�ڶ����û�ļ�¼�����޸ģ�");
            return false;
        }
        var url = 'llxxjl_jg.do?method=llxxjlJgUpdate&jgid=' + rows[0]["jgid"]
            + '&xh=' + rows[0]["xh"];
        var title = jQuery("#gnmkmc").val()+"�޸�";
        showDialog(title, 800, 550, url);
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
            }else if(rows[0]['sjly']=='ek'||rows[0]['sjly']=='ekbl'){
                showAlertDivLayer("�ڶ����û�ļ�¼�����޸ģ�");
                return false;
            }
        }
        showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
            jQuery.post("llxxjl_jg.do?method=del",{values:ids.toString()},function(data){
                showAlertDivLayer(data["message"]);
                jQuery("#dataTable").reloadGrid();
            },'json');
        }});
    }
}
//����
function dr() {
    // ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
    toImportDataNew("IMPORT_SHSJJL_JG");
    return false;

}
var DCCLBH = "dtjs_llxxjl_jg.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
    //DCCLBH�������ܱ��,ִ�е�������
    customExport(DCCLBH, exportData);
}

//��������
function exportData() {
    setSearchTj();//���ø߼���ѯ����
    var url = "llxxjl_jg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}