var gridSetting = {
    caption: "���ҿ����б�",
    pager: "pager",
    url: "gyjc_qskq.do?method=getList&type=query",
    colList: [
        {label: 'lddm', name: 'lddm', index: 'lddm',hidden:true},
        {label: 'jcrq', name: 'jcrq', index: 'jcrq',hidden:true},
        {label: 'ѧ��', name: 'xh', index: 'xh',width: '10%',formatter:xhLink},
        {label: '����', name: 'xm', index: 'xm', width: '7%'},
        {label: '�༶', name: 'bjmc', index: 'bjmc', width: '15%'},
        {label: 'ѧԺ', name: 'xymc', index: 'xymc', width: '12%'},
        {label: '¥��', name: 'ldmc', index: 'ldmc', width: '10%'},
        {label: '���Һ�', name: 'qsh', index: 'qsh', width: '5%'},
        {label: '��λ��', name: 'cwh', index: 'cwh', width: '5%'},
        {label: 'ѧ��', name: 'xn', index: 'xn', width: '8%'},
        {label: 'ѧ��', name: 'xqmc', index: 'xqmc', width: '7%'},
        {label: '�����������', name: 'kqlbmc', index: 'kqlbmc', width: '10%'},
        {label: '���ʱ��', name: 'jcsj', index: 'jcsj', width: '11%'}

    ]
    /* sortname: "lrsj",
     sortorder: "desc"*/
};
jQuery(function () {
    jQuery("#dataTable").initGrid(gridSetting);

});

function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}

function view(xh,jcrq) {
    showDialog("�鿴", 700,350, "gyjc_qskq.do?method=qskqView&xh=" + xh+"&jcrq="+jcrq);
}

function xhLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='view(\"" + rowObject["xh"] + "\",\""+rowObject["jcrq"]+"\");'>" + cellValue
        + "</a>";
}
function update(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
        return false;
    }
    var url = 'gyjc_qskq.do?method=qskqUpdate&xh=' + rows[0]["xh"]+"&jcrq="+rows[0]["jcrq"];
    var title = "�޸�";
    showDialog(title,700,350,url);
}
function save(){
    var url = "gyjc_qskq.do?method=qskqSave";
    ajaxSubFormWithFun("qskqForm", url, function(data) {
        if (data["message"] == "����ɹ���") {
            showAlert(data["message"], {}, {
                "clkFun" : function() {
                    if (parent.window) {
                        refershParent();
                    }
                }
            });
        } else {
            showAlert(data["message"]);
        }

    });
}
//����
var DCCLBH='gyjc_qskq.do';
function exportConfig(){     //����

    customExport(DCCLBH, exportData);
}
function exportData(){
    setSearchTj();//���ø߼���ѯ����
    var url = "gyjc_qskq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}