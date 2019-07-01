function add(hblx){
    var isopen_nzhb = jQuery("#isopen_nzhb").val();
    var isopen_nzzj = jQuery("#isopen_nzzj").val();
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要汇报的记录！");
        return false;
    }
    if("nzhb" == hblx){
        if(isopen_nzhb==null||isopen_nzhb==''){
            showAlertDivLayer('中期汇报基础设置未初始化，请联系管理员！');
            return false;
        }
        if ("false" == isopen_nzhb){
            showAlertDivLayer("当前中期汇报未开放申请，请联系管理员！");
            return false;
        }
    }
    if("nzzj" == hblx){
        if(isopen_nzzj==null||isopen_nzzj==''){
            showAlertDivLayer('年终总结基础设置未初始化，请联系管理员！');
            return false;
        }
        if ("false" == isopen_nzzj){
            showAlertDivLayer("当前年终总结未开放申请，请联系管理员！");
            return false;
        }
    }
    var url = "sxzzjy_bjxfjshb.do?method=bjxfjshbAdd&hblx="+hblx+
        "&jgid="+rows[0]["jgid"];
    var title = "申请";
    showDialog(title,700,550,url);
}

function update(hblx) {
    var isopen_nzhb = jQuery("#isopen_nzhb").val();
    var isopen_nzzj = jQuery("#isopen_nzzj").val();
    var rows = jQuery("#dataTable").getSeletRow();
    var sqid="";
    var shzt = "";
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要汇报的记录！");
        return false;
    }
    if("nzhb" == hblx){
        if(isopen_nzhb==null||isopen_nzhb==''){
            showAlertDivLayer('中期汇报基础设置未初始化，请联系管理员！');
            return false;
        }
        if ("false" == isopen_nzhb){
            showAlertDivLayer("当前中期汇报未开放申请，请联系管理员！");
            return false;
        }
        sqid = rows[0]["nzhbid"];
        shzt = rows[0]["nzhbshzt"];
    }
    if("nzzj" == hblx){
        if(isopen_nzzj==null||isopen_nzzj==''){
            showAlertDivLayer('年终总结基础设置未初始化，请联系管理员！');
            return false;
        }
        if ("false" == isopen_nzzj){
            showAlertDivLayer("当前年终总结未开放申请，请联系管理员！");
            return false;
        }
        sqid = rows[0]["nzzjid"];
        shzt = rows[0]["nzzjshzt"];
    }

    if ("0" != shzt&&"3" != shzt) {
        showAlertDivLayer("只有未提交和已退回的记录才能修改！");
        return false;
    }
    var url = 'sxzzjy_bjxfjshb.do?method=bjxfjshbUpdate&sqid=' + sqid;
    var title = "修改";
    showDialog(title,700,550,url);

}

function selectHblx(type) {
    var ids = jQuery("#dataTable").getSeletIds();
    if("del" == type) {
        if (ids.length == 0) {
            showAlertDivLayer("请选择您要删除的记录!");
            return  false;
        }
    }else{
        if (ids.length != 1) {
            showAlertDivLayer("请选择一条您要操作的记录！");
            return  false;
        }
    }
    var url = 'sxzzjy_bjxfjshb.do?method=selectHblx&type='+type+'&values='+ids.toString() ;
    var title = "";
    showDialog(title,300,150,url);
}

//删除
function del(hblx) {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
        var hblxs = hblx.split(",");
        for(var a=0;a<hblxs.length;a++){
            if("nzhb" == hblxs[a]){
                for (var i = 0; i < ids.length; i++) {
                    if (rows[i]["nzhbshzt"] != "0" && rows[i]["nzhbshzt"] != "3") {
                        showAlertDivLayer("只能删除未提交或者已退回的记录！");
                        return false;
                    }
                }
            }
            if("nzzj" == hblxs[a]){
                for (var i = 0; i < ids.length; i++) {
                    if (rows[i]["nzzjshzt"] != "0" && rows[i]["nzzjshzt"] != "3") {
                        showAlertDivLayer("只能删除未提交或者已退回的记录！");
                        return false;
                    }
                }
            }
        }

        showConfirmDivLayer("您确定要删除选择的记录吗？", {
            "okFun" : function() {
                jQuery.post("sxzzjy_bjxfjshb.do?method=bjxfjshbDel", { values : ids.toString(),hblxs:hblx },
                    function(data) {
                        var mes = "成功删除了<font color='green'>&nbsp;" + data["num"] + "&nbsp;</font>条数据";
                        showAlertDivLayer(mes);
                        jQuery("#dataTable").reloadGrid();
                    }, 'json');
            }
        });
}

function view(jgid) {
    showDialog("查看", 700,550, "sxzzjy_bjxfjshb.do?method=bjxfjshbView&jgid=" + jgid);
}

function bjLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='view(\"" + rowObject["jgid"] + "\");'>" + cellValue
        + "</a>";
}

function submit(hblx){
    var isopen_nzhb = jQuery("#isopen_nzhb").val();
    var isopen_nzzj = jQuery("#isopen_nzzj").val();
    var rows = jQuery("#dataTable").getSeletRow();
    var values = new Array();
    var shzt = "";
    var hblxs = hblx.split(",");
    for(var a=0;a<hblxs.length;a++){
        var hblx = hblxs[a];
        if("nzhb" == hblx){
            if(isopen_nzhb==null||isopen_nzhb==''){
                showAlertDivLayer('中期汇报基础设置未初始化，请联系管理员！');
                return false;
            }
            if ("false" == isopen_nzhb){
                showAlertDivLayer("当前中期汇报未开放申请，请联系管理员！");
                return false;
            }
            values.push( "nzhb_"+rows[0]["nzhbid"]);
            shzt = rows[0]["nzhbshzt"];
        }
        if("nzzj" == hblx){
            if(isopen_nzzj==null||isopen_nzzj==''){
                showAlertDivLayer('年终总结基础设置未初始化，请联系管理员！');
                return false;
            }
            if ("false" == isopen_nzzj){
                showAlertDivLayer("当前年终总结未开放申请，请联系管理员！");
                return false;
            }
            values.push( "nzzj_"+rows[0]["nzzjid"]);
            shzt = rows[0]["nzzjshzt"];
        }

        if ("0" != shzt&&"3" != shzt) {
            showAlertDivLayer("只有未提交和已退回的记录才能修改！");
            return false;
        }
    }

    var url = "sxzzjy_bjxfjshb.do?method=bjxfjshbSubmit";
    showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
        jQuery.post(url,
            {values:values.toString()},function(data){
                showAlertDivLayer(data["message"]);
                jQuery("#dataTable").reloadGrid();
            },'json');
    }});
}

function cancel(hblx){
    var rows = jQuery("#dataTable").getSeletRow();
    var shzt = "";
    var splcs = new Array();
    var sqids = new Array();
    var hblxs = hblx.split(",");
    for(var a=0;a<hblxs.length;a++){
        var hblx = hblxs[a];
        if("nzhb" == hblx){
            sqids.push( rows[0]["nzhbid"]);
            splcs.push( rows[0]["nzhbsplc"]);
            shzt = rows[0]["nzhbshzt"];
        }
        if("nzzj" == hblx){
            sqids.push(rows[0]["nzzjid"]);
            splcs.push( rows[0]["nzzjsplc"]);
            shzt = rows[0]["nzzjshzt"];
        }

        if (shzt != '5') {
            showAlertDivLayer("只有审核中的记录才能被撤销！");
            return false;
        }

    }
    showConfirmDivLayer("您确定要撤销选择的记录吗？",{"okFun":function(){
        jQuery.post("sxzzjy_bjxfjshb.do?method=bjxfjshbCancel",
            {
                sqids:sqids.toString(),
                splcs : splcs.toString()
            },function(data){
                showAlertDivLayer(data["message"]);
                jQuery("#dataTable").reloadGrid();
            },'json');
    }});
}


function bjxfjshbLcinfo(){
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length != 1){
        showAlertDivLayer("请选择一条流程跟踪记录！");
    } else {
        var sqids = new Array();
        var splcs = new Array();
        var hblxs = new Array();
        var nzzjid = rows[0]["nzzjid"];
        var nzhbid = rows[0]["nzhbid"];
        var nzhbshztmc = rows[0]["nzhbshztmc"];
        var nzzjshztmc = rows[0]["nzzjshztmc"];
        if((nzhbshztmc == "未提交" || nzhbshztmc == "未汇报")
            && (nzzjshztmc == "未提交" || nzzjshztmc == "未汇报")){
            showAlertDivLayer("请选择一条已提交的记录！");
            return false;
        }
        if(nzhbid != "" && nzhbid !=null ){
            sqids.push(nzhbid);
            splcs.push(rows[0]["nzhbsplc"]);
            hblxs.push("nzhb");
        }
        if(nzzjid != "" && nzzjid !=null ){
            sqids.push(nzzjid);
            splcs.push(rows[0]["nzzjsplc"]);
            hblxs.push("nzzj");
        }
        showDialog("学生证补办审批流程跟踪",580,480,'comm_spl.do?method=lcgz_hb&sqids='
            +sqids.toString()+"&splcs="+splcs.toString()+"&hblxs="+hblxs.toString());
    }
}

function printWord(hblx){
    var rows = jQuery("#dataTable").getSeletRow();
    var ids = jQuery("#dataTable").getSeletIds();
    var len = rows.length;
    var url = "";
    if (len == 1) {
        if(rows[0][hblx+"id"] == "" || rows[0][hblx+"id"]==null){
            showAlertDivLayer("请选择已完成的记录！");
            return false;
        }
        url = "sxzzjy_bjxfjshb.do?method=getPrint&hblx="+hblx;
        url += "&jgid=" + rows[0]["jgid"];
        window.open(url);
    } else if (len == 0) {
        showAlertDivLayer("请选择您要下载的记录！");
        return false;
    } else {
        if(hblx != "sq"){
            for(var i=0;i<rows.length;i++){
                if(rows[i][hblx+"id"] == "" || rows[i][hblx+"id"]==null){
                    showAlertDivLayer("请选择已完成的记录！");
                    return false;
                }
            }
        }
        url = "sxzzjy_bjxfjshb.do?method=getPrintZip&hblx="+hblx;
        url += "&value=" + ids;
        window.open(url);
    }
}

