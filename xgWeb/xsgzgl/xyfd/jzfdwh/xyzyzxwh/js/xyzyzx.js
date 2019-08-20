function searchRs() {
    var map = getSuperSearch();
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
//加载增加记录页面
function zjjl() {
    var height = jQuery(window).height();
    showDialog("增加新记录", 600, height-250, "xyfd_xyzyzxjl.do?method=addZxjl&t="+new Date().getTime());

}
function check() {
    var len = jQuery("#jtjc").val().length;
    if(len<20){
        showAlertDivLayer("请至少输入20个字符！");
        return false;
    }
    if(len>500){
        showAlertDivLayer("最多输入500个字符！");
        return false;
    }
}

function saveZxjl() {
    var zxyy = jQuery("#zxyy").val();
    var checkId = 'xh';
    if(zxyy=='01'){//挂科
        checkId += '-gks'
    }
    if(zxyy=='07'){//其它
        checkId += '-jtyy';
    }
    if (!checkNotNull(checkId)) {
        showAlertDivLayer("请将必填项填写完整！");
        return false;
    }
    check();
    var url = 'xyfd_xyzyzxjl.do?method=addZxjl&type=save';
    ajaxSubFormWithFun("demoForm",url,function(data){
        showAlertDivLayer(data["message"],{},{"clkFun":function(){
            if (parent.window){
                refershParent();
            }
            iFClose();
        }});
    });

}
function xgjl() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    var userName = jQuery("#userName").val();
    if(ids.length != 1){
        showAlertDivLayer("请选择一条您要修改的记录！");
        return false;
    }
    if(rows[0]['zxzt']=='1'){
        showAlertDivLayer("您无法修改该记录，该记录已被评论！");
        return false;
    }
    var height = jQuery(window).height();
    showDialog("修改记录", 600, height-250, "xyfd_xyzyzxjl.do?method=updateZxjl&zxid=" + rows[0]['zxid']+ "&t=" + new Date().getTime());
}

function updateZxjl() {
    var zxyy = jQuery("#zxyy").val();
    var checkId = 'zxyy';
    if(zxyy=='01'){//挂科
        checkId += '-gks'
    }
    if(zxyy=='07'){//其它
        checkId += '-jtyy';
    }
    if (!checkNotNull(checkId)) {
        showAlertDivLayer("请将必填项填写完整！");
        return false;
    }
    check();
    if(zxyy=='01'){//挂科
        jQuery("#jtyy").val('');
    }
    if(zxyy=='07'){//其它
        jQuery("#gks").val('');
    }

    var url = 'xyfd_xyzyzxjl.do?method=updateZxjl&type=save';
    ajaxSubFormWithFun("demoForm",url,function(data){
        showAlertDivLayer(data["message"],{},{"clkFun":function(){
            if (parent.window){
                refershParent();
            }
            iFClose();
        }});
    });

}
function del() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    var userName = jQuery("#userName").val();
    if(ids.length < 1){
        showAlertDivLayer("请至少选择一条您要删除的记录！");
        return false;
    }
    for(var i=0;i<ids.length;i++){
        if(rows[i]['zxzt']=='1'){
            showAlertDivLayer("您无法删除第" + (i+1) + "条选中的记录，该记录已被评论！");
            return false;
        }
    }
    showConfirmDivLayer("您确定要删除选择的记录吗？", {
        "okFun" : function() {
            jQuery.post("xyfd_xyzyzxjl.do?method=delZxjl", {
                values : ids.toString()
            }, function(data) {
                var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
                mes+="</br>";
                if(data["nodel"]!="-1"){
                    mes+="<font color='red'>"+data["nodel"]+"</font>";
                    mes+="正常运行中不能删除!";
                }
                showAlertDivLayer(mes);
                jQuery("#dataTable").reloadGrid();
            }, 'json');
        }
    });
}


function ckjl() {
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if(ids.length != 1){
        showAlertDivLayer("请选择一条您要查看的记录！");
        return false;
    }
    var height = jQuery(window).height();
    showDialog("查看记录", 600, height-250, "xyfd_xyzyzxjl.do?method=viewZxjl&zxid=" + rows[0]['zxid']+ "&t=" + new Date().getTime());
}
