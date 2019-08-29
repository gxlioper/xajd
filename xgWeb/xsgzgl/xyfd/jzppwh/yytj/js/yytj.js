function searchRs() {
    var map = getSuperSearch();
    var cxmb = jQuery("#cxmb").val();
    if (null!=cxmb&&cxmb != "") {
        map["cxmb"] = cxmb;
    }else{
        map["cxmb"] = "yyjl";
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
function selectTab(obj, cxmb) {
    jQuery("#yyzt").val(cxmb);
    if(cxmb == "jspj"){
        jQuery("#li_fdjl").css("display", "none");
        jQuery("#li_pjtj").css("display", "");
        jQuery("#li_qxtj").css("display", "none");
        var map = getSuperSearch();
        map["cxmb"]="jspj";
        gridSetting2["params"] = map;
        jQuery("#dataTable").initGrid(gridSetting2);
    }else if(cxmb == "yyqx"){
        jQuery("#li_fdjl").css("display", "none");
        jQuery("#li_pjtj").css("display", "none");
        jQuery("#li_qxtj").css("display", "");
        var map = getSuperSearch();
        map["cxmb"]="yyqx";
        gridSetting3["params"] = map;
        jQuery("#dataTable").initGrid(gridSetting3);
    } else {
        jQuery("#li_fdjl").css("display", "");
        jQuery("#li_pjtj").css("display", "none");
        jQuery("#li_qxtj").css("display", "none");
        var map = getSuperSearch();
        map["cxmb"]="yyjl";
        gridSetting["params"] = map;
        jQuery("#dataTable").initGrid(gridSetting);
    }
    jQuery(".ha").removeClass("ha");
    jQuery(obj).parent().addClass("ha");
}
function qrfd() {
    // var ids = jQuery("#dataTable").getSeletIds();
    // var rows = jQuery("#dataTable").getSeletRow();
    // if (ids.length != 1) {
    //     showAlertDivLayer("��ѡ��һ����Ҫ��Ӹ�����¼��ԤԼ��");
    //     return false;
    // }
    // var height = jQuery(window).height();
    // var yyh = rows[0]['yyh'];
    // showDialog("��Ӹ�����¼", 600, height-250, "xyfd_fdjl.do?method=addFdjl&yyh="+yyh+"&t="+new Date().getTime());
}

function grxxpj() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ĸ�����ʦ����־Ը�ߣ�");
        return false;
    }
    var djh = rows[0]['djh'];
    var url =  "xyfd_yytj.do?method=grxxpj&djh="+djh+"&t="+new Date().getTime();
    window.open(url);
}

function grqvqk() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ĸ�����ʦ����־Ը�ߣ�");
        return false;
    }
    var djh = rows[0]['djh'];
    var url =  "xyfd_yytj.do?method=grqvqk&djh="+djh+"&t="+new Date().getTime();
    window.open(url);
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
