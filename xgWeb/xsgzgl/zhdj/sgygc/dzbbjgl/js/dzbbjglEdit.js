function selectDzb(){
    var url = "zhdj_dzbbjgl.do?method=selectDzb";
    var title = "选择党支部";
    showDialog(title,750, 550, url);
}
function saveDzb(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条党支部记录！");
    }else {
        var o = rows[0];
        var api = frameElement.api;
        var parent = jQuery(api.get('parentDialog').document);
        parent.find("#dzbmc").val(rows[0]["dzbmc"]);
        parent.find("#dzbid").val(rows[0]["dzbid"]);
        parent.find("#zbsjTd").html(rows[0]["dzbsjxm"]);
        parent.find("#xcwyTd").html(rows[0]["xcwyxm"]);
        parent.find("#zzwyTd").html(rows[0]["zzwyxm"]);
        parent.find("#jlwyTd").html(rows[0]["jlwyxm"]);
        Close();

    }
}
function selectBj(){
    var dzbid = jQuery("#dzbid").val();
    if(dzbid == ""){
        showAlert("请先选择党支部！");
        return;
    }
    var url = "zhdj_dzbbjgl.do?method=selectBj&dzbid="+dzbid;
    var title = "选择班级";
    showDialog(title,750, 550, url);
}
function saveBj(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条班级记录！");
    }else {
        var o = rows[0];
        var api = frameElement.api;
        var parent = jQuery(api.get('parentDialog').document);
        parent.find("#bjmc").val(rows[0]["bjmc"]);
        parent.find("#bjdm").val(rows[0]["bjdm"]);
        Close();

    }
}

function save(type){
    var btn_dzb = jQuery("#btn_dzb");
    if(btn_dzb.length == 0 && jQuery("#dzbmc").val() == ""  ){
        showAlert("请选择党支部");
        return;
    }
    if(jQuery("#bjmc").val() == "" ){
        showAlert("请选择班级");
        return;
    }
   var url = "zhdj_dzbbjgl.do?method=save&type="+type;
    ajaxSubFormWithFun("form", url, function(data) {
        if (data["success"] != undefined && !data["success"]) {
            showAlert(data["message"]);
        } else {
            showAlert(data["message"], {}, {
                "clkFun" : function(tag) {
                    if (tag == "ok") {
                        refershParent();
                    }
                }
            });
        }
    });
}