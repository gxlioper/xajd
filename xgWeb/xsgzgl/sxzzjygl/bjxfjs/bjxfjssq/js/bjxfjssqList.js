function add(){
    var isopen = jQuery("#isopen").val();
    if(isopen==null||isopen==''){
        showAlertDivLayer('基础设置未初始化，请联系管理员！');
        return false;
    }
    if ("false" == isopen){
        showAlertDivLayer("当前未开放申请，请联系管理员！");
        return false;
    }
    var url = "sxzzjy_bjxfjssq.do?method=bjxfjssqAdd";
    var title = "申请";
    showDialog(title,700,550,url);
}

function update() {
    var isopen = jQuery("#isopen").val();
    if(isopen==null||isopen==''){
        showAlertDivLayer('基础设置未初始化，请联系管理员！');
        return false;
    }
//			if ("false" == isopen){
//				showAlertDivLayer("当前未开放申请，请联系管理员！");
//				return false;
//			}
    var rows = jQuery("#dataTable").getSeletRow();

    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要修改的记录！");
    } else {
        var shzt = rows[0]["shzt"];
        if ("0" != shzt&&"3" != shzt) {
            showAlertDivLayer("只有未提交和已退回的记录才能修改！");
            return false;
        }
        var url = 'sxzzjy_bjxfjssq.do?method=bjxfjssqUpdate&sqid=' + rows[0]["sqid"] ;
        var title = "修改";
        showDialog(title,700,550,url);
    }
}

//删除
function del() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length == 0) {
        showAlertDivLayer("请选择您要删除的记录！");
    } else {
        for (var i = 0; i < ids.length; i++) {
            if (rows[i]["shzt"] != "0" && rows[i]["shzt"] != "3") {
                showAlertDivLayer("只能删除未提交或者已退回的记录！");
                return false;
            }
        }
        showConfirmDivLayer("您确定要删除选择的记录吗？", {
            "okFun" : function() {
                jQuery.post("sxzzjy_bjxfjssq.do?method=bjxfjssqDel", { values : ids.toString() },
                    function(data) {
                        var mes = "成功删除了<font color='green'>&nbsp;" + data["num"] + "&nbsp;</font>条数据";
                        showAlertDivLayer(mes);
                        jQuery("#dataTable").reloadGrid();
                    }, 'json');
            }
        });
    }
}

function view(sqid) {
    showDialog("查看", 700,550, "sxzzjy_bjxfjssq.do?method=bjxfjssqView&sqid=" + sqid);
}

function bjLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='view(\"" + rowObject["sqid"] + "\");'>" + cellValue
        + "</a>";
}

function submit(){
    var ids = jQuery("#dataTable").getSeletIds();
    if(ids.length != 1){
        showAlertDivLayer("请选择一条您要提交的记录！");
        return false;
    }else{
        var rows = jQuery("#dataTable").getSeletRow();
        var isopen = jQuery("#isopen").val();
        if(isopen==null||isopen==''){
            showAlertDivLayer('基础设置未初始化，请联系管理员！');
            return false;
        }
        if('3'!=rows[0]["shzt"]&&"true"!=isopen){
            showAlertDivLayer("当前未开放申请，请联系管理员！");
            return false;
        }
        var url = "sxzzjy_bjxfjssq.do?method=bjxfjssqSubmit";
        if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
            showAlertDivLayer("请选择未提交或者已退回的记录！");
            return false;
        }
        showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
            jQuery.post(url,
                {sqid:rows[0]["sqid"]},function(data){
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                },'json');
        }});
    }
}

function cancel(){
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length != 1) {
        showAlertDivLayer("请选择一条您要撤销的记录！");
    } else {
        var rows = jQuery("#dataTable").getSeletRow();
        if (rows[0]['shzt'] != '5') {
            showAlertDivLayer("只有审核中的记录才能被撤销！");
            return false;
        }
        showConfirmDivLayer("您确定要撤销选择的记录吗？",{"okFun":function(){
            jQuery.post("sxzzjy_bjxfjssq.do?method=bjxfjssqCancel",
                {
                    sqid:rows[0]['sqid'],
                    splc : rows[0]['splc']
                },function(data){
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                },'json');
        }});
    }
}


function bjxfjsshLcinfo(){
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length != 1){
        showAlertDivLayer("请选择一条流程跟踪记录！");
    } else {
        showDialog("学生证补办审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
    }
}

function printWord(){
    var rows = jQuery("#dataTable").getSeletRow();
    var ids = jQuery("#dataTable").getSeletIds();
    var len = rows.length;
    var url = "";
    if (len == 1) {
        url = "sxzzjy_bjxfjssq.do?method=getPrint";
        url += "&sqid=" + rows[0]["sqid"];
        window.open(url);
    } else if (len == 0) {
        showAlertDivLayer("请选择您要下载的记录！");
        return false;
    } else {
        url = "sxzzjy_bjxfjssq.do?method=getPrintZip";
        url += "&value=" + ids;
        window.open(url);
    }
}