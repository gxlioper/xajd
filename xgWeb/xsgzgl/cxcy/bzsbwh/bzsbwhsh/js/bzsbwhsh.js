function view(sqid) {
    showDialog("创新创业补助申报查看", 700, 450, "cxcy_bzsbwhsq.do?method=bzsbwhsqView&sqid="+sqid);
}

function sh(){

    var rows = jQuery("#dataTable").getSeletRow();
    var shzt = jQuery("#shzt").val();
    if(shzt=="ysh"){
        showAlertDivLayer("已处理记录不能再次审核");
        return false;
    } else if(rows.length == 0){
        showAlertDivLayer("请选择一条您要审核的记录！");
        return false;
    } else if (rows.length == 1){
        var url = "cxcy_bzsbwhsh.do?method=bzsbwhDgsh&sqid="+rows[0]["sqid"]+
            '&shid=' +rows[0]["shid"]+'&gwid=' +rows[0]["gwid"]+'&splc=' +rows[0]["splc"];
        showDialog("补助申报审核",700,520,url);
    } else{
        showDialog("补助申报批量审核",500,300,"cxcy_bzsbwhsh.do?method=bzsbwhPlsh");
    }
}
function saveSh(){
    if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""){
        showAlert("请将必填项填写完整！");
        return false;
    }
    var url = "cxcy_bzsbwhsh.do?method=saveDgsh";
    ajaxSubFormWithFun("form",url,function(data){
        if(data["message"]=="保存成功！"){
            showAlert(data["message"],{},{"clkFun":function(){
                if (parent.window){
                    refershParent();
                }
            }});
        }else{
            showAlert(data["message"]);
        }
    });
}
//批量审核
function savePlsh(shzt, shyj) {
    var rows = jQuery("#dataTable").getSeletRow();
    var sqid = new Array();
    var gwid = new Array();
    var xhs = new Array();
    var splcs = new Array();
    jQuery.each(rows, function(i, row) {
        sqid.push(row["sqid"]);
        gwid.push(row["gwid"]);
        xhs.push(row["xh"]);
        splcs.push(row["splc"]);
    });
    jQuery.post("cxcy_bzsbwhsh.do?method=savePlsh", {
        shzt : shzt,
        splcs : splcs,
        ids : sqid,
        gwids : gwid,
        xhs : xhs,
        shyj : shyj
    }, function(data) {

        showAlertDivLayer(data["message"], {}, {
            "clkFun" : function() {
                jQuery("#dataTable").reloadGrid();
            }
        });
    }, 'json');
}

//审核撤销
function cancelSh(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1){
        showAlertDivLayer("请选择一条您要撤消的审核记录！");
    } else {
        var splc = rows[0]["splc"];
        var shid = rows[0]["shid"];
        var sqid = rows[0]["sqid"];
        var shzt = rows[0]["shzt"];
        showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
            jQuery.post("cxcy_bzsbwhsh.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
                // 判断是否最后一级撤销(1:最后一级撤销成功）
                if("1" == data["cancelFlg"]){
                    jQuery.post("cxcy_bzsbwhsh.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
                        showAlertDivLayer(result["message"],{},{"clkFun":function(){
                            jQuery("#dataTable").reloadGrid();
                        }});
                    },'json');
                }else{
                    showAlertDivLayer(data["message"],{},{"clkFun":function(){
                        jQuery("#dataTable").reloadGrid();
                    }});
                }

            },'json');
        }});
    }
}

function lcgz(){
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (1!=ids.length){
        showAlertDivLayer("请选择一条流程跟踪记录！");
    } else {
        showDialog("审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
    }
}
function exportConfig(){
    var DCCLBH='cxcy_bzsbwh.do';
    customExport(DCCLBH, exportData);
}
function exportData(){
    var DCCLBH='cxcy_bzsbwh.do';
    setSearchTj();//设置高级查询条件
    var url = "cxcy_bzsbwhsq.do?method=exportData&dcclbh=" + DCCLBH+
        "&pkValue="+jQuery("#pkValue").val();//dcclbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}
