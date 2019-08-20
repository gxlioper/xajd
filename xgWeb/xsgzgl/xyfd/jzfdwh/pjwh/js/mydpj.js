function searchRs() {
    var map = getSuperSearch();
    var pjzt = jQuery("#pjzt").val();
    if (null!=pjzt&&pjzt != "") {
        map["pjzt"] = pjzt;
    }else{
        map["pjzt"] = "dpj";
    }
    jQuery("#dataTable").reloadGrid(map);
}
function view(sqid) {
    var height = jQuery(window).height();
    showDialog("审核查询", 600, height-250, "xyfd_fdkcsq.do?method=fdkcsqView&sqid="+sqid+"&t="+new Date().getTime());
}

function xhLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='view(\""
        + rowObject["sqid"] + "\");'>" + cellValue
        + "</a>";
}

// 切换Tab页
function selectTab(obj, pjzt) {
    jQuery("#pjzt").val(pjzt);
    if (pjzt == "dpj") {
        jQuery("#li_tj").css("display", "");
        jQuery("#li_ck").css("display", "none");
        var map = getSuperSearch();
        map["pjzt"]="dpj";
        gridSetting["params"] = map;
        jQuery("#dataTable").initGrid(gridSetting);
    } else {
        jQuery("#li_tj").css("display", "none");
        jQuery("#li_ck").css("display", "");
        var map = getSuperSearch();
        map["pjzt"]="ypj";
        gridSetting2["params"] = map;
        jQuery("#dataTable").initGrid(gridSetting2);
    }
    jQuery(".ha").removeClass("ha");
    jQuery(obj).parent().addClass("ha");
}
function txpj() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length != 1) {
        showAlertDivLayer("请选择一条您要填写评价的记录！");
        return false;
    }
    var height = jQuery(window).height();
    var jlbh = rows[0]['jlbh'];
    showDialog("填写评价", 600, height-250, "xyfd_mydpj.do?method=txpj&jlbh="+jlbh+"&t="+new Date().getTime());
}

function ckpj() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length != 1) {
        showAlertDivLayer("请选择一条您要查看辅导记录的预约！");
        return false;
    }
    var height = jQuery(window).height();
    var jlbh = rows[0]['jlbh'];
    showDialog("查看辅导记录", 600, height-250, "xyfd_mydpj.do?method=viewPj&jlbh="+jlbh+"&t="+new Date().getTime());
}
function check() {
    var len = jQuery("#fdjl").val().length;
    if(len<20){
        showAlertDivLayer("请至少输入20个字符！");
        return false;
    }
    if(len>500){
        showAlertDivLayer("最多输入500个字符！");
        return false;
    }
}

function saveFdjl() {
    var checkId = 'fdjl';
    if (!checkNotNull(checkId)) {
        showAlertDivLayer("请将必填项填写完整！");
        return false;
    }
    check();
    showConfirmDivLayer("确认将该辅导预约置为已辅导？", {
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
