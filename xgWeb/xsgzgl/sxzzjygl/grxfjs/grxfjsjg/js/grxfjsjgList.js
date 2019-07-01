var gridSetting = {
    caption: "��б�",
    pager: "pager",
    url: "sxzzjy_grxfjsjg.do?method=getList&type=query",
    colList: [
        {label: 'jgid', name: 'jgid', index: 'jgid',hidden:true,key:true},
        {label: '������Դ',name:'sjly', index: 'sjly',hidden:true},
        {label: 'ѧ��', name: 'xh', index: 'xh', width: '10%',formatter:xhLink},
        {label: '����', name: 'xm', index: 'xm', width: '10%'},
        {label: '�༶', name: 'bjmc', index: 'bjmc', width: '10%'},
        {label: 'ѧԺ', name: 'xymc', index: 'xymc', width: '10%'},
        {label: '����', name: 'xfjsmc', index: 'xfjsmc', width: '10%'},
        {label: '�걨����', name: 'sblxmc', index: 'sblxmc', width: '10%'},
        {label: 'ѧ��ѧ��', name: 'xnxq', index: 'xnxq', width: '10%'},
        {label: '���ڻ㱨', name: 'sfnzhb', index: 'sfnzhb', width: '10%'},
        {label: '�����ܽ�', name: 'sfnzzj', index: 'sfnzzj', width: '10%'},
        {label: 'nzzjid', name: 'nzzjid', index: 'nzzjid', hidden:true},
        {label: 'nzhbid', name: 'nzhbid', index: 'nzhbid', hidden:true},
        {label: '¼��ʱ��', name: 'lrsj', index: 'lrsj',hidden:true}

    ],
    sortname: "lrsj",
    sortorder: "desc"
};
jQuery(function () {
    var map = getSuperSearch();
    gridSetting["params"] = map;
    jQuery("#dataTable").initGrid(gridSetting);

});

function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}

function view(jgid) {
    showDialog("�鿴", 700,550, "sxzzjy_grxfjsjg.do?method=grxfjsjgView&jgid=" + jgid);
}

function xhLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='view(\"" + rowObject["jgid"] + "\");'>" + cellValue
        + "</a>";
}
function add(){
    var url = "sxzzjy_grxfjsjg.do?method=grxfjsjgAdd";
    var title = "��������ѧ�罨��";
    showDialog(title,  700,550, url);

}
function update(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����¼��");
        return;
    }
    if(rows[0]["sjly"] == "1"){
        showAlertDivLayer("��������ݣ������޸ģ�");
        return;
    }
    var url = "sxzzjy_grxfjsjg.do?method=grxfjsjgUpdate&jgid="+rows[0]["jgid"];
    var title = "�޸�";
    showDialog(title, 700,550, url);

}
function del(){
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length == 0) {
        showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
    } else {
        showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
            "okFun": function () {
                jQuery.post("sxzzjy_grxfjsjg.do?method=grxfjsjgDel", {values: ids.toString()}, function (data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
            }
        });

    }
}




//����
function exportConfig(){
    var DCCLBH='sxzzjy_grxfjsjg.do';
    customExport(DCCLBH, exportData);
}
function exportData(){
    var shzt=jQuery("#shzt").val();
    var DCCLBH='sxzzjy_grxfjsjg.do';
    setSearchTj();//���ø߼���ѯ����
    var url = "sxzzjy_grxfjsjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}

function printWord(hblx){
    var rows = jQuery("#dataTable").getSeletRow();
    var ids = jQuery("#dataTable").getSeletIds();
    var len = rows.length;
    var url = "";
    if (len == 1) {
        if(hblx != "sq" && (rows[0][hblx+"id"] == "" || rows[0][hblx+"id"]==null)){
            showAlertDivLayer("��ѡ������ɵļ�¼��");
            return false;
        }
        url = "sxzzjy_grxfjsjg.do?method=getPrint&hblx="+hblx;
        url += "&bjdm=" + rows[0]["bjdm"] + "&jgid=" + rows[0]["jgid"];
        window.open(url);
    } else if (len == 0) {
        showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
        return false;
    } else {
        if(hblx != "sq"){
            for(var i=0;i<rows.length;i++){
                if(rows[i][hblx+"id"] == "" || rows[i][hblx+"id"]==null){
                    showAlertDivLayer("��ѡ������ɵļ�¼��");
                    return false;
                }
            }
        }

        url = "sxzzjy_grxfjsjg.do?method=getPrintZip&hblx="+hblx;
        url += "&value=" + ids;
        window.open(url);
    }
}