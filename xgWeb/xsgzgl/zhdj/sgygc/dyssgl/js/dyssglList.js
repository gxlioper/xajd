var gridSetting = {
    caption: "��б�",
    pager: "pager",
    url: "zhdj_dyssgl.do?method=getList&type=query",
    colList: [
        {label: 'dyssid', name: 'dyssid', index: 'dyssid',hidden:true,key:true},
        {label: 'dyxh', name: 'dyxh', index: 'dyxh',hidden:true},
        {label: '��Ա', name: 'dyxm', index: 'dyxm', width: '10%'},
        {label: '��ϵ����', name: 'zsxx', index: 'zsxx', width: '10%'},
        {label: '������ϵʱ��', name: 'jllxsj', index: 'jllxsj', width: '10%'},
        {label: '��ϵ�����ܽ������', name: 'sftj', index: 'sftj', width: '10%'},
        {label: '����޸�ʱ��', name: 'zhxgsj', index: 'zhxgsj', width: '10%'}

    ],
    sortname: "jllxsj",
    sortorder: "desc"
};
jQuery(function () {
    jQuery("#dataTable").initGrid(gridSetting);
});
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}
function add(){
    var url = "zhdj_dyssgl.do?method=dyssglAdd";
    var title = "������ϵ����";
    showDialog(title, 550, 400, url);

}
function view(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����¼��");
        return;
    }
    var url = "zhdj_dyssgl.do?method=dyssglView&dyssid="+rows[0]["dyssid"];
    var title = "�鿴";
    showDialog(title, 550, 400, url);

}
function update(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����¼��");
        return;
    }
    var url = "zhdj_dyssgl.do?method=dyssglUpdate&dyssid="+rows[0]["dyssid"];
    var title = "�޸�";
    showDialog(title, 550, 400, url);

}

function tjzj(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����¼��");
        return;
    }
    if(jQuery("#userName").val() != rows[0]["dyxh"]){
        showAlertDivLayer("ֻ����ϵ�ĵ�Ա���ύ��");
        return;
    }
    var url = "zhdj_dyssgl.do?method=dyssglTjzj&dyssid="+rows[0]["dyssid"];
    var title = "�ύ�ܽ������";
    showDialog(title, 550, 400, url);

}
function del(){
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length == 0) {
        showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
    } else {
        showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
            "okFun": function () {
                jQuery.post("zhdj_dyssgl.do?method=del", {values: ids.toString()}, function (data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
            }
        });

    }
}




function importConfig(){
    toImportDataNew("IMPORT_ZHDJ_DYSSGL");
    return false;
}

//����
function exportConfig(){
    var DCCLBH='zhdj_dyssgl.do';
    customExport(DCCLBH, exportData);
}
function exportData(){
    var shzt=jQuery("#shzt").val();
    var DCCLBH='zhdj_dyssgl.do';
    setSearchTj();//���ø߼���ѯ����
    var url = "zhdj_dyssgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}