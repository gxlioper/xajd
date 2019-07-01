var gridSetting = {
    caption: "������¼�б�",
    pager: "pager",
    url: "zhdj_djgzjl.do?method=getList&type=query",
    colList: [
        {label: 'id', name: 'id', index: 'id',hidden:true,key:true},
        {label: 'ѧ��', name: 'xn', index: 'xn', width: '10%'},
        {label: 'ѧ��', name: 'xqmc', index: 'xqmc', width: '8%'},
        {label: 'ѧԺ', name: 'xymc', index: 'xymc', width: '17%'},
        {label: '��֧��Ӧ<br>������', name: 'yhjs', index: 'yhjs', width: '8%'},
        {label: '��֧��ʵ<br>�ʻ�����', name: 'sjhjs', index: 'sjhjs', width: '8%'},
        {label: '���ȱ�����<br>��Ա��չ����', name: 'jdbksdyfzrs', index: 'jdbksdyfzrs', width: '10%'},
        {label: '�����о���<br>��Ա��չ����', name: 'jdyjsdyfzrs', index: 'jdyjsdyfzrs', width: '10%'},
        {label: '��֧���Ƿ�<br>��ʱ���ɵ���', name: 'sfasjndfmc', index: 'sfasjndfmc', width: '10%'},
        {label: '�ϱ�ʱ��', name: 'sbsj', index: 'sbsj', width: '18%'}

    ],
    sortname: "xymc",
    sortorder: "asc"
};
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}
jQuery(function () {
    jQuery("#dataTable").initGrid(gridSetting);
});
function view(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
        return;
    }
    var url = "zhdj_djgzjl.do?method=view&id="+rows[0]["id"];
    var title = "�鿴";
    showDialog(title,450, 320, url);
}
function add(){
    var url = "zhdj_djgzjl.do?method=add";
    var title = "�ϱ�";
    showDialog(title, 450, 320, url);
}
function update(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
        return;
    }
    var url = "zhdj_djgzjl.do?method=update&id="+rows[0]["id"];
    var title = "�޸�";
    showDialog(title, 450, 320, url);
}
function del() {
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length == 0) {
        showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
    } else {
        showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
            "okFun": function () {
                jQuery.post("zhdj_djgzjl.do?method=del", {values: ids.toString()}, function (data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
            }
        });

    }
}
//����
function exportConfig(){
    var DCCLBH='zhdj_djgzjl.do';
    customExport(DCCLBH, exportData);
}
function exportData(){
    var shzt=jQuery("#shzt").val();
    var DCCLBH='zhdj_djgzjl.do';
    setSearchTj();//���ø߼���ѯ����
    var url = "zhdj_djgzjl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}