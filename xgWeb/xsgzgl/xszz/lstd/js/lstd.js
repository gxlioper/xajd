
function lstdsq(){
    /*var isopen = jQuery("#isopen").val();
    if ("false" == isopen || isopean == ''){
        showAlert("当前未开放困难生申请，请联系管理员！");
        return false;
    }*/
    showDialog('绿色通道申请',780,450,'xszz_lstd.do?method=lstdsqZj');
}

function lstdxg(){
    /*var isopen = jQuery("#isopen").val();
    if ("false" == isopen || isopean == ''){
        showAlert("当前未开放困难生申请，请联系管理员！");
        return false;
    }*/
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要修改的记录！");
        return false;
    }
    var shzt = rows[0]["shzt"];
    if ("0" != shzt&&"3" != shzt){
        showAlertDivLayer("审核中数据不能修改！");
        return false;
    }

    showDialog('绿色通道申请修改',780,450,'xszz_lstd.do?method=lstdsqXg&sqid='+ rows[0]["sqid"]);
}

function selectChange(target){
    if(target.value == '01'){
        jQuery("#dkjeTh").removeAttr("style");
        jQuery("#dkjeTd").removeAttr("style");
        jQuery("#sqhjje").attr("readonly","readonly");
        jQuery("#sqhjje").val("");
        jQuery("#sqhjje").val(jQuery("#dkje").val());
    } else if(target.value == '02'){
        jQuery("#dkjeTh").attr("style","display:none");
        jQuery("#dkjeTd").attr("style","display:none");
        jQuery("#dkje").val("");
        jQuery("#sqhjje").val("8000");
        jQuery("#sqhjje").attr("readonly","readonly");
    } else {
        jQuery("#dkjeTh").attr("style","display:none");
        jQuery("#dkjeTd").attr("style","display:none");
        jQuery("#dkje").val("");
        jQuery("#sqhjje").removeAttr("readonly");
    }
}
function jshjje() {
    var dkje = jQuery("#dkje").val();
    jQuery("#sqhjje").val(dkje);
}
function selectTab(obj,shzt){
    jQuery("#shzt").val(shzt);

    if (shzt == "dsh"){
        // jQuery("#dataTable").initGrid(gridSetting);
        jQuery("#li_sh").css("display","");
        jQuery("#li_qx").css("display","none");
    } else {
        // jQuery("#dataTable").initGrid(gridSetting2);
        jQuery("#li_sh").css("display","none");
        jQuery("#li_qx").css("display","");
    }

    jQuery(".ha").removeClass("ha");
    jQuery(obj).parent().addClass("ha");

    searchRs();
}

function lstdsqDelete() {
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length == 0){
        showAlertDivLayer("请选择您要删除的申请记录！");
    } else {
        var rows = jQuery("#dataTable").getSeletRow();

        for(var i=0;i<ids.length;i++){
            if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
                showAlertDivLayer("审核中数据不能删除！");
                return false;
            }
        }

        showConfirmDivLayer("您确定要删除该申请吗？",{"okFun":function(){
            jQuery.post("xszz_lstd.do?method=delLstdsq",{values:ids.toString()},function(data){
                showAlertDivLayer(data["message"]);
                jQuery("#dataTable").reloadGrid();
            },'json');
        }});
    }
}

function lstdsh(){
    var rows = jQuery("#dataTable").getSeletRow();
    if(rows.length == 0){
        showAlertDivLayer("请至少选定一条记录！");
        return false;
    } else if(rows.length == 1){
        var url = "xszz_lstd.do?method=lstdsh&sqid="+rows[0]["sqid"]+"&xh="+rows[0]["xh"]+"&gwid="+rows[0]["gwid"]+"&shid="+rows[0]["shid"]+"&shlc="+rows[0]["shlc"];
        showDialog("绿色通道审核",750,700,url);
    } else {
        showDialog("绿色通道批量审核",500,300,"xszz_lstd.do?method=lstdplsh");
    }
}

function lstdshSave(){
    if(jQuery("#shzt").val() != "1" && jQuery("#shzt").val() != "2"){
        showAlertDivLayer("请填写审核状态！");
        return false;
    }

    if(jQuery("#shyj").val() == ""){
        showAlertDivLayer("请填写审核意见！");
        return false;
    }
    var message;
    if(jQuery("#shzt").val() == "1"){
        message = "通过";
    }
    if(jQuery("#shzt").val() == "2"){
        message = "不通过";
    }
    showConfirmDivLayer("您确定" + message + "该申请吗？",{"okFun":function(){
        var url = "xszz_lstd.do?method=lstdsh&type=save";
        ajaxSubFormWithFun("lstdForm",url,function(data){
            showAlertDivLayer(data["message"],{},{"clkFun":function(){
                if (parent.window){
                    refershParent();
                }
            }});
        });
    }});
}

//撤销审核
function cancelSh(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1){
        showAlertDivLayer("请选择一条您要撤消的审核记录！");
    } else {
        var splc = rows[0]["shlc"];
        var shid = rows[0]["shid"];
        var sqid = rows[0]["sqid"];
        var shzt = rows[0]["shzt"];
        showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
            jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
                // 判断是否最后一级撤销(1:最后一级撤销成功）
                if("1" == data["cancelFlg"]){
                    jQuery.post("xszz_lstd.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
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

function cancel(){
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length == 0) {
        showAlertDivLayer("请选择您要撤销的记录！");
    } else if (ids.length >1 ) {
        showAlertDivLayer("请选择一条您要撤销的记录！");
    } else {
        var rows = jQuery("#dataTable").getSeletRow();
        for(var i=0;i<ids.length;i++){
            if(rows[i]['shzt']!='5'){
                showAlertDivLayer("只有审核中的记录才能被撤销！");
                return false;
            }
        }
        showConfirmDivLayer("您确定要撤销选择的记录吗？",{"okFun":function(){
            jQuery.post("xszz_lstd.do?method=cancel",
                {
                    values:ids.toString(),
                    splcid : rows[0]['shlc']
                },function(data){
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                },'json');
        }});
    }
}
function submit(){
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length != 1){
        showAlertDivLayer("请选择一条您要提交的记录！");
    }else{
        var rows = jQuery("#dataTable").getSeletRow();
        var url = "xszz_lstd.do?method=submit";
        for(var i=0;i<ids.length;i++){
            if(rows[i]['shzt']!='0' && rows[i]['shzt']!='3' ){
                showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
                return false;
            }
        }
        showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
            jQuery.post(url,
                {
                    values:ids.toString(),
                    xh : rows[0]['xh'],
                    shlc : rows[0]['shlc'],
                    shzt : rows[0]['shzt']
                },function(data){
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                },'json');
        }});
    }
}

function Lcinfo(){
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length != 1){
        showAlertDivLayer("请选择一条流程跟踪记录！");
    } else {
        var shzt = rows[0]["shzt"];
        if ("0" == shzt){
            showAlertDivLayer(jQuery("#lable_wxglcxx").val());
            return false;
        }
        showDialog("绿色通道审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['shlc']);
    }
}

function print() {
    var t = jQuery("#dataTable");
    var ids = t.getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length != 1) {
        showAlertDivLayer('请选定一条记录!');
        return false;
    }
    var url = "xszz_lstd.do?method=print&jgid=" + rows[0]['jgid'];
    if(rows[0]['sqid']!=null&&rows[0]['sqid']!=''){
        url += "&sqid=" + rows[0]['sqid'];
    }
    window.open(url);
}