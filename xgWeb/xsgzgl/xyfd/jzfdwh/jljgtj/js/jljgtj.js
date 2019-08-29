function searchRs() {
    var map = getSuperSearch();
    var cxmb = jQuery("#cxmb").val();
    if (null!=cxmb&&cxmb != "") {
        map["cxmb"] = cxmb;
    }else{
        map["cxmb"] = "fd";
    }
    jQuery("#dataTable").reloadGrid(map);
}

// 切换Tab页
function selectTab(obj, cxmb) {
    jQuery("#cxmb").val(cxmb);
    if (cxmb == "fd") {
        jQuery("#li_ck_fd").css("display", "");
        jQuery("#li_dr_fd").css("display", "");
        jQuery("#li_dc_fd").css("display", "");
        jQuery("#li_ck_zx").css("display", "none");
        jQuery("#li_dr_zx").css("display", "none");
        jQuery("#li_dc_zx").css("display", "none");
        var map = getSuperSearch();
        map["cxmb"]="fd";
        gridSetting["params"] = map;
        jQuery("#dataTable").initGrid(gridSetting);
    } else {
        jQuery("#li_ck_fd").css("display", "none");
        jQuery("#li_dr_fd").css("display", "none");
        jQuery("#li_dc_fd").css("display", "none");
        jQuery("#li_ck_zx").css("display", "");
        jQuery("#li_dr_zx").css("display", "");
        jQuery("#li_dc_zx").css("display", "");
        var map = getSuperSearch();
        map["cxmb"]="zx";
        gridSetting2["params"] = map;
        jQuery("#dataTable").initGrid(gridSetting2);
    }
    jQuery(".ha").removeClass("ha");
    jQuery(obj).parent().addClass("ha");
}
function ckfd() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length != 1) {
        showAlertDivLayer("请选择一条您要查看的辅导记录！");
        return false;
    }
    var height = jQuery(window).height();
    var yyh = rows[0]['yyh'];
    showDialog("查看辅导详情", 600, height-250, "xyfd_jljgtj.do?method=view&jlbh="+yyh+"&cxmb=fd&t="+new Date().getTime());
}

function ckzx() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length != 1) {
        showAlertDivLayer("请选择一条您要查看的咨询记录！");
        return false;
    }
    var height = jQuery(window).height();
    var zxid = rows[0]['zxid'];
    showDialog("查看咨询详情", 600, height-250, "xyfd_jljgtj.do?method=view&jlbh="+zxid+"&cxmb=zx&t="+new Date().getTime());
}
