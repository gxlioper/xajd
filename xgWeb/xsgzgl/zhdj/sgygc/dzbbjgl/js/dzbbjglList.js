var gridSetting = {
    caption: "��б�",
    pager: "pager",
    url: "zhdj_dzbbjgl.do?method=getList&type=query",
    colList: [
        {label: 'id', name: 'id', index: 'id',hidden:true,key:true},
        {label: '��֧��id', name: 'dzbid', index: 'dzbid',hidden:true},
        {label: '�༶����', name: 'lxbjdm', index: 'lxbjdm',hidden:true},
        {label: '֧������', name: 'dzbmc', index: 'dzbmc', width: '10%'},
        {label: '��ϵ�༶', name: 'lxbjmc', index: 'lxbjmc', width: '10%'},
        {label: '֧�����', name: 'dzbsjxm', index: 'dzbsjxm', width: '10%'},
        //{label: '�μ���Ա', name: 'cjry', index: 'cjry', width: '10%'},
        {label: '����ίԱ', name: 'xcwyxm', index: 'xcwyxm', width: '10%'},
        {label: '����ίԱ', name: 'jlwyxm', index: 'jlwyxm', width: '10%'},
        {label: '��֯ίԱ',name: 'zzwyxm', index: 'zzwyxm', width: '10%'},
        {label: '����ʱ��',name: 'cjsj', index: 'cjsj', width: '12%'}

    ],
    sortname: "cjsj",
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
    var url = "zhdj_dzbbjgl.do?method=dzbbjglAdd";
    var title = "������ϵ�༶";
    showDialog(title, 400, 270, url);

}
function update(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����¼��");
        return;
    }
    var url = "zhdj_dzbbjgl.do?method=dzbbjglUpdate&id="+rows[0]["id"];
    var title = "�޸�";
    showDialog(title, 400, 270, url);

}
function del(){
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length == 0) {
        showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
    } else {
        showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
            "okFun": function () {
                jQuery.post("zhdj_dzbbjgl.do?method=del", {values: ids.toString(),type:jQuery("#type").val()}, function (data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
            }
        });

    }
}




function importConfig(){
    toImportDataNew("IMPORT_ZHDJ_DZBBJGL");
    return false;
}

//����
function exportConfig(){
    var DCCLBH='zhdj_dzbbjgl.do';
    customExport(DCCLBH, exportData);
}
function exportData(){
    var DCCLBH='zhdj_dzbbjgl.do';
    setSearchTj();//���ø߼���ѯ����
    var url = "zhdj_dzbbjgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}