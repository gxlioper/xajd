var gridSetting = {
    caption:"个人学风建设待审核",
    pager:"pager",
    url:"sxzzjy_grxfjssh.do?method=getList&type=query&shzt=dsh",
    colList:[
        {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
        {label:'审批流程',name:'splc', index: 'splc',hidden:true},
        {label:'学号',name:'xh', index: 'xh',width:'6%',formatter:xhLink},
        {label:'姓名',name:'xm', index: 'xm',width:'6%'},
        {label:'班级',name:'bjmc', index: 'bjmc',width:'9%'},
        {label:'学院',name:'xymc', index: 'xymc',width:'9%'},
        {label:'名称',name:'xfjsmc', index: 'xfjsmc',width:'9%'},
        {label:'申报类型',name:'sblxmc', index: 'sblxmc',width:'9%'},
        {label:'学年学期',name:'xnxq', index: 'xnxq',width:'10%'},
        {label:'审核状态',name:'shztmc', index: 'shztmc',width:'8%'},
        {label:'审核状态名称',name:'shzt', index: 'shzt',hidden:true},
        {label:'类型',name:'sqlxmc', index: 'sqlxmc',width:'6%'},
        {label:'sqlx',name:'sqlx', index: 'sqlx',hidden:true},
        {label:'jgid',name:'jgid', index: 'jgid',hidden:true},
        {label:'学期',name:'xq', index: 'xq',hidden:true},
        {label:'学期',name:'xn', index: 'xn',hidden:true},
        {label:'审核Id',name:'shid', index: 'shid',hidden:true},
        {label:'审批岗位id',name:'gwid', index: 'gwid',hidden:true},
        {lable:'审批流程Id',name:'splc', index:'splc',hidden:true}
    ],
    params:{shzt:"dsh"},//默认待审核
    sortname: "sqsj",
    sortorder: "desc",
    radioselect:false
};
var gridSetting2 = {
    caption:"个人学风建设已审核",
    pager:"pager",
    url:"sxzzjy_grxfjssh.do?method=getList&type=query",
    colList:[
        {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
        {label:'审批流程',name:'splc', index: 'splc',hidden:true},
        {label:'学号',name:'xh', index: 'xh',width:'6%',formatter:xhLink},
        {label:'姓名',name:'xm', index: 'xm',width:'6%'},
        {label:'班级',name:'bjmc', index: 'bjmc',width:'10%'},
        {label:'学院',name:'xymc', index: 'xymc',width:'10%'},
        {label:'名称',name:'xfjsmc', index: 'xfjsmc',width:'10%'},
        {label:'申报类型',name:'sblxmc', index: 'sblxmc',width:'10%'},
        {label:'学年学期',name:'xnxq', index: 'xnxq',width:'10%'},
        {label:'审核状态',name:'shztmc', index: 'shztmc',width:'8%'},
        {label:'审核状态名称',name:'shzt', index: 'shzt',hidden:true},
        {label:'类型',name:'sqlxmc', index: 'sqlxmc',width:'6%'},
        {label:'sqlx',name:'sqlx', index: 'sqlx',hidden:true},
        {label:'jgid',name:'jgid', index: 'jgid',hidden:true},
        {label:'学期',name:'xq', index: 'xq',hidden:true},
        {label:'学期',name:'xn', index: 'xn',hidden:true},
        {label:'审核Id',name:'shid', index: 'shid',hidden:true},
        {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
        {lable:'审批流程Id',name:'splc', index:'splc',hidden:true}
    ],
    params:{shzt:"ysh"},//默认待审核
    sortname: "sqsj",
    sortorder: "desc",
    radioselect:true
};

jQuery(function(){
    var map = getSuperSearch();
    gridSetting["params"] = map;
    jQuery("#dataTable").initGrid(gridSetting);
});

function searchRs(){
    var map = getSuperSearch();
    var shzt = jQuery("#shzt").val();
    if (shzt != ""){
        map["shzt"] = shzt;
    }
    jQuery("#dataTable").reloadGrid(map);
}


function selectTab(obj,shzt){
    jQuery("#shzt").val(shzt);
    if (shzt == "dsh"){
        jQuery("#li_sh").css("display","");
        jQuery("#li_qx").css("display","none");
        jQuery("#dataTable").initGrid(gridSetting);
    } else {
        jQuery("#li_sh").css("display","none");
        jQuery("#li_qx").css("display","");
        jQuery("#dataTable").initGrid(gridSetting2);
    }
    jQuery(".ha").removeClass("ha");
    jQuery(obj).parent().addClass("ha");
    searchRs();
}

function grxfjsSh(){

    var rows = jQuery("#dataTable").getSeletRow();
    var shzt = jQuery("#shzt").val();
    if(shzt=="ysh"){
        showAlertDivLayer("已处理记录不能再次审核");
        return false;
    } else if(rows.length == 0){
        showAlertDivLayer("请选择一条您要审核的记录！");
        return false;
    } else if (rows.length == 1){
        var url = "sxzzjy_grxfjssh.do?method=grxfjsSh&sqid="+rows[0]["sqid"]+'&xn=' +rows[0]["xn"]+
            '&shid=' +rows[0]["shid"]+'&gwid=' +rows[0]["gwid"]+'&splc=' +rows[0]["splc"] +
            '&xh=' + rows[0]["xh"]+'&sqlx=' + rows[0]["sqlx"];
        showDialog("个人学风建设审核",720,520,url);
    } else{
        showDialog("个人学风建设批量审核",500,300,"sxzzjy_grxfjssh.do?method=grxfjsPlsh");
    }
}

/**
 * 批量审核保存
 * @param shzt
 * @return
 */
function savePlsh(shzt,shyj){

    var rows = jQuery("#dataTable").getSeletRow();
    var guid = new Array();
    var bjdms = new Array();
    var splc = new Array();
    var gwids = new Array();
    var sqlxs = new Array();

    jQuery.each(rows,function(i,row){
        guid.push(row["sqid"]);
        bjdms.push(row["bjdm"]);
        splc.push(row["splc"]);
        gwids.push(row["gwid"]);
        sqlxs.push(row["sqlx"]);
    });

    jQuery.post(
        "sxzzjy_grxfjssh.do?method=grxfjsPlsh&type=save",
        {
            shzt:shzt,
            id:guid,
            bjdms:bjdms,
            shyj:shyj,
            gwids:gwids,
            splcs:splc,
            sqlxs:sqlxs
        },function(data){

            showAlertDivLayer(data["message"],{},{"clkFun":function(){
                jQuery("#dataTable").reloadGrid();
            }});
        },
        'json'
    );
}

function grxfjsshLcinfo(){
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length != 1){
        showAlertDivLayer("请选择一条流程跟踪记录！");
    } else {
        showDialog("个人班风建设审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
    }
}

function cancelShnew(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1){
        showAlertDivLayer("请选择一条您要撤消的审核记录！");
    } else {
        var splc = rows[0]["splc"];
        var shid = rows[0]["shid"];
        var sqid = rows[0]["sqid"];
        var sqlx = rows[0]["sqlx"];
        showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
            jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
                // 判断是否最后一级撤销(1:最后一级撤销成功）
                if("1" == data["cancelFlg"]){
                    jQuery.post("sxzzjy_grxfjssh.do?method=cancelgrxfjssh",{sqid:sqid,sqlx:sqlx},function(result){
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

function viewsh(sqid,sqlx,jgid,xh ){

    var url = "sxzzjy_grxfjssq.do?method=grxfjssqView&sqid=" + sqid;
    if("sq" != sqlx)
        url = "sxzzjy_grxfjshb.do?method=grxfjshbView&jgid=" + jgid+"&hblx="+sqlx+"&xh="+xh;
    showDialog("查看", 720,550, url);

}

function xhLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick=\"viewsh(" +
        "'"+rowObject["sqid"]+"','"+rowObject["sqlx"]+"','"+rowObject["jgid"]+"','"+rowObject["xh"]+"')\">" + cellValue
        + "</a>";
}



var DCCLBH = "rcsw_dxsylbx_grxfjssh.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
    //DCCLBH导出功能编号,执行导出函数 
    customExport(DCCLBH, grxfjsshExportData);
}

// 导出方法
function grxfjsshExportData() {
    var shlx = jQuery("#shzt").val();
    setSearchTj();//设置高级查询条件
    var url = "sxzzjy_grxfjssh.do?method=exportData&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}