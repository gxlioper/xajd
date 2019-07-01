jQuery(function(){
    var gridSetting = {
        caption : "���¼�б�",
        pager : "pager",
        url : "cxcy_bzsbwhjg.do?method=getList&type=query",
        colList : [
            { label : 'jgid', name : 'jgid', index : 'jgid',key : true, hidden : true },
            { label : 'ѧ��', name : 'xh', index : 'xh', width : '10%',formatter:xhLink },
            { label : '����', name : 'xm', index : 'xm', width : '8%' },
            { label : '��Ŀ����', name : 'xmmc', index : 'xmmc', width : '14%' },
            { label : '������Ԫ��', name : 'bzje', index : 'bzje', width : '9%' },
            { label : 'ѧ��', name : 'xn', index : 'xn', width : '9%' },
            { label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '7%' },
            { label : '�������', name : 'xymc', index : 'xymc', width : '12%' },
            { label : '���', name : 'tbrmc', index : 'tbrmc', width : '8%' },
            { label : '��¼ʱ��', name : 'lrsj', index : 'lrsj', width : '15%' },
            { label : '������Դ', name : 'sjly', index : 'sjly', hidden : true}]
    };
    var map = getSuperSearch();
    gridSetting["params"] = map;
    jQuery("#dataTable").initGrid(gridSetting);
});
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='view(\""
        + rowObject["jgid"]+"\");'>" + cellValue
        + "</a>";
}

function view(jgid) {
    showDialog("���´�ҵ�����걨�鿴", 700, 450, "cxcy_bzsbwhjg.do?method=bzsbwhjgView&jgid="+jgid);
}
function add() {
    var url = "cxcy_bzsbwhjg.do?method=bzsbwhjgAdd";
    var title = "�������´�ҵ��������";
    showDialog(title, 700, 550, url);
}

function update() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
    }else {
        if(rows[0]['sjly'] == '1' ){
            showAlertDivLayer("����������ݲ����޸ģ�");
            return false;
        }
        var url = 'cxcy_bzsbwhjg.do?method=bzsbwhjgUpdate&jgid=' + rows[0]["jgid"];
        var title = "���´�ҵ���������޸�";
        showDialog(title, 800, 550, url);
    }

}

// ɾ��
function del() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length == 0) {
        showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
    } else {
        var flag = true;
        jQuery(rows).each(function(i,row){
            if(row["sjly"] == "1"){
                flag = false;
                return;
            }
        });
        if(!flag){
            showAlertDivLayer("����������ݲ���ɾ����");
            return false;
        }
        showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
            "okFun" : function() {
                jQuery.post("cxcy_bzsbwhjg.do?method=bzsbwhjgDel", {
                        values : ids.toString()
                    },
                    function(data) {
                        showAlertDivLayer(data["message"]);
                        jQuery("#dataTable").reloadGrid();
                    }, 'json');
            }
        });
    }
}

function exportConfig(){
    var DCCLBH='cxcy_bzsbwh_jg.do';
    customExport(DCCLBH, exportData);
}
function exportData(){
    var DCCLBH='cxcy_bzsbwh_jg.do';
    setSearchTj();//���ø߼���ѯ����
    var url = "cxcy_bzsbwhjg.do?method=exportData&dcclbh=" + DCCLBH+
        "&pkValue="+jQuery("#pkValue").val();//dcclbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}
