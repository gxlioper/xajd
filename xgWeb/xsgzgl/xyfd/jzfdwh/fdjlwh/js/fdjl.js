function searchRs() {
    var map = getSuperSearch();
    var yyzt = jQuery("#yyzt").val();
    if (null!=yyzt&&yyzt != "") {
        map["yyzt"] = yyzt;
    }else{
        map["yyzt"] = "dfd";
    }
    jQuery("#dataTable").reloadGrid(map);
}
function view(sqid) {
    var height = jQuery(window).height();
    showDialog("��˲�ѯ", 600, height-250, "xyfd_fdkcsq.do?method=fdkcsqView&sqid="+sqid+"&t="+new Date().getTime());
}

function xhLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='view(\""
        + rowObject["sqid"] + "\");'>" + cellValue
        + "</a>";
}

// �л�Tabҳ
function selectTab(obj, yyzt) {
    jQuery("#yyzt").val(yyzt);
    if (yyzt == "dfd") {
        jQuery("#li_tj").css("display", "");
        jQuery("#li_ck").css("display", "none");
        var map = getSuperSearch();
        map["yyzt"]="dfd";
        gridSetting["params"] = map;
        jQuery("#dataTable").initGrid(gridSetting);
    } else {
        jQuery("#li_tj").css("display", "none");
        jQuery("#li_ck").css("display", "");
        var map = getSuperSearch();
        map["yyzt"]="yfd";
        gridSetting2["params"] = map;
        jQuery("#dataTable").initGrid(gridSetting2);
    }
    jQuery(".ha").removeClass("ha");
    jQuery(obj).parent().addClass("ha");
}
function qrfd() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ��Ӹ�����¼��ԤԼ��");
        return false;
    }
    var height = jQuery(window).height();
    var yyh = rows[0]['yyh'];
    showDialog("��Ӹ�����¼", 600, height-250, "xyfd_fdjl.do?method=addFdjl&yyh="+yyh+"&t="+new Date().getTime());
}

function ckfd() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�鿴������¼��ԤԼ��");
        return false;
    }
    var height = jQuery(window).height();
    var yyh = rows[0]['yyh'];
    showDialog("�鿴������¼", 600, height-250, "xyfd_fdjl.do?method=viewFdjl&yyh="+yyh+"&t="+new Date().getTime());
}
function check() {
    var len = jQuery("#fdjl").val().length;
    if(len<20){
        showAlertDivLayer("����������20���ַ���");
        return false;
    }
    if(len>500){
        showAlertDivLayer("�������500���ַ���");
        return false;
    }
}

function saveFdjl() {
    var checkId = 'fdjl';
    if (!checkNotNull(checkId)) {
        showAlertDivLayer("�뽫��������д������");
        return false;
    }
    check();
    showConfirmDivLayer("ȷ�Ͻ��ø���ԤԼ��Ϊ�Ѹ�����", {
        "okFun" : function() {
            var url = 'xyfd_fdjl.do?method=addFdjl&type=save';
            ajaxSubFormWithFun("demoForm",url,function(data){
                showAlertDivLayer(data["message"],{},{"clkFun":function(){
                    if (parent.window){
                        refershParent();
                    }
                    iFClose();
                }});
            });
        }
    });
}
