//学费减免申请列表
var status;
jQuery(function(){
    initGridSetting();
});

function initGridSetting(){
    status  = jQuery("#status").val();
    if(status == null || status.length == 0){
        status ='dsh';
    }
    gridSetting["params"]=getSuperSearch();
    jQuery("#dataTable").initGrid(gridSetting(status));
}

function gridSetting(type){
    return {
        caption:"学费减免审核列表",
        pager:"pager",
        url:"xszz_new_xfjm.do?method=getShPageList&doType=query&status="+type,
        colList:[
            {label:'key',name:'id', index: 'id',key:true ,hidden:true},
            {label:'学号',name:'xh', index: 'xh',width:'11%',formatter:xhLink},
            {label:'姓名',name:'xm', index: 'xm',width:'8%'},
            {label:'性别',name:'xb', index: 'xb',width:'5%'},
            {label:'学院',name:'xymc', index: 'xydm',width:'13%'},
            {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
            {label:'书院',name:'symc', index: 'symc',width:'13%'},
            {label:'行政班级',name:'bjmc', index: 'bjdm',width:'13%'},
            {label:'专业班级',name:'zybjmc', index: 'bjdm',width:'13%'},
            {label:'学年',name:'xn', index: 'xn',width:'10%'},
            {label:'申请时间',name:'sqsj', index: 'sqsj',width:'9%'},
            {label:'经济困难程度',name:'dcmc', index: 'dcmc',width:'8%'},
            {label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
            {label:'审核流程状态',name:'shlzt', index: 'shlzt',hidden:true},
            {label:'审核id',name:'shid', index: 'shid',hidden:true},
            {label:'岗位id',name:'gwid', index: 'gwid',hidden:true},
            {label:'shzt',name:'shlgwmc', index: 'shlgwmc',hidden:true},
            {label:'审核状态',name:'shztmc', index: 'shztmc',width:'6%',formatter:shztLink}
        ],
        sortname: "sqsj",
        sortorder: "desc"
    };
}

function selectTab(obj,type){
    status = type;
    if(status=='ysh'){
        jQuery("#xfjmsh").hide();
    }else{
        jQuery("#xfjmsh").show();
    }
    jQuery(".ha").removeClass("ha");
    jQuery(obj).parent().addClass("ha");
    gridSetting["params"]=getSuperSearch();
    jQuery("#dataTable").initGrid(gridSetting(status));
}

function xhLink(value,row){
    return "<a href='javascript:void(0);' class='name' onclick='xfjmView(\""
        + row["id"] + "\",\"" + value + "\");'>" + value
        + "</a>";
}

function shztLink(value,row){
    return row.shlgwmc+"["+value+"]";
}


function xfjmView(id,xh){
    showDialog("学费减免信息查看",  720, 520, "xszz_new_xfjm.do?method=xfjmSq&status=view&id=" + id
        + "&xh=" + xh);
}

function xfjmsh(){
    var row = getSelectRow();
    if(!row){
        return false;
    }
    showDialog("学费减免信息审核",  720, 520, "xszz_new_xfjm.do?method=xfjmSh&status=dsh&id=" + row.id
        + "&xh=" + row.xh+"&shlc="+row.shlc+"&shid="+row.shid+"&xtgwid="+row.gwid);
}
function searchRs(){
    var map = getSuperSearch();
    map.status = status;
    jQuery("#dataTable").reloadGrid(map);
}
//审核流程跟踪
function shlcck(){
    var row = getSelectRow();
    if(!row){
        return false;
    }
    if(row.shzt=="0"){
        showAlertDivLayer("未提交申请暂无审批流程!");
        return false;
    }
    showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+row.id+"&splc="+row.shlc);

}

function getSelectRow(){
    var rows = jQuery("#dataTable").getSeletRow();
    if(rows == null || rows.length != 1){
        showAlertDivLayer("请选择一条记录！");
        return false;
    }
    return rows[0];
}

function reload(){
    searchRs();
}

