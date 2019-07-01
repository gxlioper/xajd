/**
 * ��ѯ
 */
function searchRs() {
    var map = getSuperSearch();
    var sffp = jQuery("#sffp").val();
    if (null!=sffp&&sffp != "") {
        map["sffp"] = sffp;
    }else{
        map["sffp"] = "0";
    }
    var checkVal = new Array();
    var checkValObj = jQuery("[name='checkVal']");
    for(var i=0;i<checkValObj.length;i++){
        checkVal.push(jQuery(checkValObj[i]).val());
    }
    map["ids"]=checkVal;
    jQuery("#dataTable").reloadGrid(map);
}

/**
 * ҳǩѡ��
 */
function selectTab(obj, shzt) {
    jQuery("#sffp").val(shzt);
    if (shzt == "0") {
        jQuery("#li_bc").css("display", "");
        jQuery("#li_qx").css("display", "none");
        var map = getSuperSearch();
        map["sffp"]="0";
        var checkVal = new Array();
        var checkValObj = jQuery("[name='checkVal']");
        for(var i=0;i<checkValObj.length;i++){
            checkVal.push(jQuery(checkValObj[i]).val());
        }
        map["ids"]=checkVal;
        gridSetting["params"] = map;
        jQuery("#dataTable").initGrid(gridSetting);
    } else {
        jQuery("#li_bc").css("display", "none");
        jQuery("#li_qx").css("display", "");
        var map = getSuperSearch();
        map["sffp"]="1";
        var checkVal = new Array();
        var checkValObj = jQuery("[name='checkVal']");
        for(var i=0;i<checkValObj.length;i++){
            checkVal.push(jQuery(checkValObj[i]).val());
        }
        map["ids"]=checkVal;
        gridSetting2["params"] = map;
        jQuery("#dataTable").initGrid(gridSetting2);
    }
    jQuery(".ha").removeClass("ha");
    jQuery(obj).parent().addClass("ha");
}

/**
 * �������
 */
function saveFp(){
    var yhms = jQuery("#dataTable").getSeletIds();
    var jdid = jQuery("#jdid").val();
    var url = "hdgl_hdgl.do?method=zjtcyFp";
    if(yhms.length == 0){
        showAlert("��ѡ��Ҫ�������Ա��");
        return false;
    }
    var checkVal = new Array();
    var checkValObj = jQuery("[name='checkVal']");
    for(var i=0;i<checkValObj.length;i++){
        checkVal.push(jQuery(checkValObj[i]).val());
    }

    jQuery.post(url, {
        ids : checkVal,
        zghs : yhms,
        jdid : jdid
    }, function(data) {
        showAlertDivLayer(data["message"], {}, {
            "clkFun" : function() {
                jQuery("#dataTable").reloadGrid();
            }
        });
    }, 'json');
}

/**
 * ȡ������
 */
function cancelFp(){
    var yhms = jQuery("#dataTable").getSeletIds();
    var jdid = jQuery("#jdid").val();
    var url = "hdgl_hdgl.do?method=zjtcyQxfp";
    if(yhms.length == 0){
        showAlert("��ѡ��Ҫȡ���������Ա��");
        return false;
    }
    var hdid = jQuery("[name='checkVal']").val();

    jQuery.post(url, {
        hdid : hdid,
        zghs : yhms,
        jdid : jdid
    }, function(data) {
        showAlertDivLayer(data["message"], {}, {
            "clkFun" : function() {
                jQuery("#dataTable").reloadGrid();
            }
        });
    }, 'json');
}
