var gridSetting = {
    caption: "�����б�",
    pager: "pager",
    url: "sxzzjy_xymxbzbgl.do?method=getList&type=query",
    colList: [
        {label: 'newsid', name: 'newsid', index: 'newsid',hidden:true,key:true},
        {label: 'fbbmdm', name: 'fbbmdm', index: 'fbbmdm',hidden:true},
        {label: '����',name:'newstitle', index: 'newstitle', width: '30%',formatter:titleLink},
        {label: '����ʱ��', name: 'fbsj', index: 'fbsj', width: '15%'},
        {label: '������', name: 'fbr', index: 'fbr', width: '12%'},
        {label: '��������', name: 'fbbmmc', index: 'fbbmmc', width: '15%'},
        {label: '�Ƿ񷢲�', name: 'sffb', index: 'sffb', width: '8%'},
        {label: '�Ƿ��ö�', name: 'sfzd', index: 'sfzd', width: '8%'},
        {label: '���Ķ�����', width: '12%',formatter:yydmdLink}
    ],
    sortname: "sfzd desc,sffb desc,fbsj",
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


function view(newsid) {
    var url="sxzzjy_xymxbzbgl.do?method=xymxbzbglView&newsid=" + newsid;
    document.forms[0].action = url;
    document.forms[0].target = "_blank";
    document.forms[0].submit();
    document.forms[0].target = "_self";
    //showDialog("�鿴", 700,550, "sxzzjy_xymxbzbgl.do?method=xymxbzbglView&newsid=" + newsid);
}

function titleLink(cellValue, rowObject) {
    var value = cellValue;
    if(cellValue.length > 18)
        value = value.substring(0,18)+"...";
    return "<a href='javascript:void(0);' class='name' title='"+cellValue+"' onclick='view(\""
        + rowObject["newsid"] + "\");'>" + value
        + "</a>";
}

function yydmdView(newsid){
    var url="sxzzjy_xymxbzbgl.do?method=getYydmd&newsid="+newsid;
    showDialog('���Ķ���Ա����',700,450,url);
}
function yydmdLink(cellValue, rowObject){
    return "<a href='javascript:void(0);' class='name' onclick='yydmdView(\""
        + rowObject["newsid"] + "\");'>���Ķ�����</a>";
}

//Ԥ��
function newsyl(){
    var ids = jQuery("#dataTable").getSeletIds();
    if(ids.length != 1){
        showAlertDivLayer("��ѡ��һ����ҪԤ���ļ�¼��");
        return false;
    }else{
        view(ids[0]);
    }

}
//ɾ��
function delNews(){
    var ids = jQuery("#dataTable").getSeletIds();
    if(ids.length == 0){
        showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
        return false;
    }else {
        var url = "sxzzjy_xymxbzbgl.do?method=xymxbzbglDel";
        showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
            "okFun": function () {
                jQuery.post(url, {values: ids.toString()}, function (data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
            }
        });
    }
}
function fb(){
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length == 0) {
        showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
    } else {
        for (var i = 0; i < ids.length; i++) {
            if (rows[i]["sffb"] == "��") {
                showAlertDivLayer("��¼�ѷ��������ܷ�����");
                return false;
            }
        }
        showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
            "okFun" : function() {
                jQuery.post("sxzzjy_xymxbzbgl.do?method=xymxbzbglFb", { values : ids.toString() },
                    function(data) {
                        showAlertDivLayer(data["message"]);
                        jQuery("#dataTable").reloadGrid();
                    }, 'json');
            }
        });
    }
}
function qxfb(){
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length == 0) {
        showAlertDivLayer("��ѡ����Ҫȡ�������ļ�¼��");
    } else {
        for (var i = 0; i < ids.length; i++) {
            if (rows[i]["sffb"] == "��") {
                showAlertDivLayer("��¼δ����������ȡ����");
                return false;
            }
        }
        showConfirmDivLayer("��ȷ��Ҫȡ������ѡ��ļ�¼��", {
            "okFun" : function() {
                jQuery.post("sxzzjy_xymxbzbgl.do?method=xymxbzbglQxfb", { values : ids.toString() },
                    function(data) {
                        showAlertDivLayer(data["message"]);
                        jQuery("#dataTable").reloadGrid();
                    }, 'json');
            }
        });
    }
}
function zd(){
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length == 0) {
        showAlertDivLayer("��ѡ����Ҫ�ö��ļ�¼��");
    } else {
        for (var i = 0; i < ids.length; i++) {
            if (rows[i]["sfzd"] == "��") {
                showAlertDivLayer("��¼���ö����������ö���");
                return false;
            }
        }
        showConfirmDivLayer("��ȷ��Ҫ�ö�ѡ��ļ�¼��", {
            "okFun" : function() {
                jQuery.post("sxzzjy_xymxbzbgl.do?method=xymxbzbglZd", { values : ids.toString() },
                    function(data) {
                        showAlertDivLayer(data["message"]);
                        jQuery("#dataTable").reloadGrid();
                    }, 'json');
            }
        });
    }
}
function qxzd(){
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length == 0) {
        showAlertDivLayer("��ѡ����Ҫȡ���ö��ļ�¼��");
    } else {
        for (var i = 0; i < ids.length; i++) {
            if (rows[i]["sfzd"] == "��") {
                showAlertDivLayer("��¼δ�ö�������ȡ����");
                return false;
            }
        }
        showConfirmDivLayer("��ȷ��Ҫȡ���ö�ѡ��ļ�¼��", {
            "okFun" : function() {
                jQuery.post("sxzzjy_xymxbzbgl.do?method=xymxbzbglQxzd", { values : ids.toString() },
                    function(data) {
                        showAlertDivLayer(data["message"]);
                        jQuery("#dataTable").reloadGrid();
                    }, 'json');
            }
        });
    }
}
function updateNews(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("��ѡ����Ҫ�޸ĵļ�¼��");
    }else{
        var url="sxzzjy_xymxbzbgl.do?method=xymxbzbglUpdate&newsid="+rows[0]["newsid"];
        showDialog('У԰���ǰ��а��޸�',900,519,url);
    }
}
function addNews(){
    var url="sxzzjy_xymxbzbgl.do?method=xymxbzbglAdd";
    showDialog('У԰���ǰ��а�����',900,519,url);
}

