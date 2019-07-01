function selectDy(){
    var url = "zhdj_dyssgl.do?method=selectDy";
    var title = "选择党员";
    showDialog(title, 800, 550, url);
}
function selectQs(){
    var url = "zhdj_dyssgl.do?method=selectQs";
    var title = "选择党员";
    showDialog(title, 800, 550, url);
}

function saveDy(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条党员记录！");
    }else {
        var o = rows[0];
        var api = frameElement.api;
        var parent = jQuery(api.get('parentDialog').document);
        parent.find("#dyxh").val(rows[0]["dyxh"]);
        parent.find("#dyxm").html(rows[0]["dyxm"]);
        parent.find("#xb").html(rows[0]["xb"]);
        parent.find("#lxdh").html(rows[0]["lxdh"]);
        parent.find("#sjhm").html(rows[0]["sjhm"]);
        parent.find("#dzbmc").html(rows[0]["dzbmc"]);
        parent.find("#dzbsjxm").html(rows[0]["dzbsjxm"]);
        Close();

    }
}

function saveQs(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条寝室记录！");
    }else {
        var o = rows[0];
        var api = frameElement.api;
        var parent = jQuery(api.get('parentDialog').document);
        jQuery.post("zhdj_dyssgl.do?method=showQscy", {lddm: rows[0]["lddm"],qsh:rows[0]["qsh"]}, function (dataList) {
            /*alert(JSON.stringify(dataList));
            alert(dataList.length);*/
            parent.find("#qscyTbody").find("tr").remove();
            if(dataList != null && dataList.length>0 && dataList[0].xm != undefined){

                var html = "";
                for(var i = 0;i<dataList.length;i++){

                    var o = dataList[i];
                    if(i%2 ==0){
                        html += "<tr>";
                        html += "<th width='16%'>"+o.cwh+"号床</th>";

                        if(i == dataList.length-1 ){
                            html += "<td colspan='3'>"+o.xm+"</td>";
                        }else{
                            html += "<td width='34%'>"+o.xm+"</td>";
                        }

                    }else{
                        html += "<th>"+o.cwh+"号床</th>";
                        html += "<td>"+o.xm+"</td>";
                        html += "</tr>"

                    }
                }
                parent.find("#qscyTbody").append(html);

            }
            parent.find("#qsxx").val(rows[0]["ldmc"]+rows[0]["qsh"]);
            parent.find("#lddm").val(rows[0]["lddm"]);
            parent.find("#qsh").val(rows[0]["qsh"]);
            Close();
        }, 'json');
    }
}

function save(type){
    if(!check()){
        showAlert("请填写所有必填项！");
        return ;
    }
    var url = "zhdj_dyssgl.do?method=save&type="+type;
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
function check(){
    var dyxh = jQuery("#dyxh").val();
    var lddm = jQuery("#lddm").val();
    var qsh = jQuery("input[name=qsh]").val();
    var ydaq = jQuery("input[name=ydaq]").val();
    var ssxf = jQuery("input[name=ssxf]").val();
    var ywdbxjxx = jQuery("input[name=ywdbxjxx]").val();
    var ywxyxx = jQuery("input[name=ywxyxx]").val();
    var ywmsscj = jQuery("#ywmsscj").val();
    var zgjhcs = jQuery("#zgjhcs").val();
    if(isNull(dyxh) || isNull(lddm) || isNull(qsh) ||  isNull(ydaq) ||
         isNull(ssxf) ||  isNull(ywdbxjxx) ||  isNull(ywxyxx) ||  isNull(ywmsscj) ||
         isNull(zgjhcs) ){
        return false;
    }else{
        return true;
    }
}

function save_zj(){
    if(!check()){
        showAlert("请填写所有必填项！");
        return ;
    }
    var ywxjssjcpp = jQuery("input[name=ywxjssjcpp]").val();
    var sfwmss = jQuery("input[name=sfwmss]").val();
    var ywxjssjcby = jQuery("input[name=ywxjssjcby]").val();

    if(isNull(ywxjssjcpp) || isNull(sfwmss) || isNull(ywxjssjcby)){
        showAlert("请填写所有必填项！");
        return ;
    }
    var url = "zhdj_dyssgl.do?method=save_zj";
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
function isNull(val){
    if(val == "" || val == null){
        return true;
    }else{
        return false;
    }
}