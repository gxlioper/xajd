var  gridSetting,gridSetting2;
jQuery(function (){
      gridSetting = {
        caption: "自行上报活动列表",
        pager: "pager",
        url: "xszbhd_hdsb.do?method=getSbList&type=query&flag=zxsb",
        colList: [
            {label: 'hdsbid', name: 'hdsbid', index: 'hdsbid',hidden:true,key:true},
            {label: 'dzbid', name: 'dzbid', index: 'dzbid',hidden:true},
            {label: '学年', name: 'xn', index: 'xn', width: '10%'},
            {label: '学期', name: 'xqmc', index: 'xqmc', width: '10%'},
            {label: '三会一课/党日活动', name: 'shykdrhdmc', index: 'shykdrhdmc', width: '10%'},
            {label: '活动类型', name: 'hdlxmc', index: 'hdlxmc', width: '10%'},
            {label: '主题', name: 'hdzt', index: 'hdzt', width: '15%'},
            {label: '上报时间', name: 'sbsj', index: 'sbsj', width: '10%'}
        ],
        sortname: "sbsj",
        sortorder: "asc"
    };
     gridSetting2 = {
        caption: "限时上报活动列表",
        pager: "pager",
        url: "xszbhd_hdsb.do?method=getSbList&type=query&flag=xssb",
        colList: [
            {label: 'hdsbid', name: 'hdsbid', index: 'hdsbid',hidden:true,key:true},
            {label: 'dzbid', name: 'dzbid', index: 'dzbid',hidden:true},
            {label: 'hdid', name: 'hdid', index: 'hdid',hidden:true},
            {label: '学年', name: 'xn', index: 'xn', width: '7%'},
            {label: '学期', name: 'xqmc', index: 'xqmc', width: '6%'},
            {label: '三会一课/党日活动', name: 'shykdrhdmc', index: 'shykdrhdmc', width: '10%'},
            {label: '活动类型', name: 'hdlxmc', index: 'hdlxmc', width: '10%'},
            {label: '主题', name: 'hdzt', index: 'hdzt', width: '12%'},
            {label: '开始时间', name: 'kssj', index: 'kssj', width: '7%'},
            {label: '截止时间', name: 'jzsj', index: 'jzsj', width: '7%'},
            //{label: '详情', width: '5%',formatter:detailLink},
            {label: '上报时间', name: 'sbsj', index: 'sbsj', width: '7%'},
            {label: 'zt', name: 'zt', index: 'zt', width: '10%',hidden:true},
            {label: '状态', name: 'ztmc', index: 'ztmc', width: '5%'}
        ],
        sortname: "sbsj",
        sortorder: "asc"
    };
    jQuery("#dataTable").initGrid(gridSetting);
});
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}
function query(obj,lx){
    jQuery("#comp_title li").removeClass();
    jQuery(obj).parent().attr("class","ha");
    // jQuery("#shlx").val(shlx);
    if(lx =='xssb'){
        jQuery("#type").val("xssb");
        jQuery("#dataTable").initGrid(gridSetting2);
    }else{
        jQuery("#type").val("zxsb");
        jQuery("#dataTable").initGrid(gridSetting);
    }
    searchRs();
}

function add() {
    var type = jQuery("#type").val();
    var url = "xszbhd_hdsb.do?method=add&flag=add&type="+type;
    if(type == "xssb"){
        var rows = jQuery("#dataTable").getSeletRow();
        if (rows.length != 1) {
            showAlertDivLayer("请选择一条您要上报的记录！");
            return;
        }
        url = "xszbhd_hdsb.do?method=add&flag=add&type="+type+"&hdid="+rows[0]["hdid"];
    }
    var title = "活动上报";
    showDialog(title, 600, 550, url);
}
function update() {
    var type = jQuery("#type").val();
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要修改的记录！");
        return;
    }
    if(type == "xssb" && isNull(rows[0]["hdsbid"])){
        showAlertDivLayer("请选择一条已上报的记录！");
        return;
    }
    if(type == "xssb"){
        var kssj = rows[0]["kssj"].replace(/-/g,"/");//替换字符，变成标准格式
        var jzsj = rows[0]["jzsj"].replace(/-/g,"/");//替换字符，变成标准格式
        var now=new Date();//取今天的日期
        //var now = new Date(Date.parse("2016/06/01 10:00:00"));
        var dkssj = new Date(Date.parse(kssj));
        var djzsj = new Date(Date.parse(jzsj));
        if(now<dkssj || now>djzsj){
            showAlertDivLayer("只能在活动时间内修改！");
            return;
        }
    }
    var title = "活动上报修改";
    var url = "xszbhd_hdsb.do?method=update&flag=update&type="+type+"&hdsbid="+rows[0]["hdsbid"]+"&hdid="+rows[0]["hdid"];
    showDialog(title, 600, 550, url);
}
function del() {
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length == 0) {
        showAlertDivLayer("请选择您要删除的记录！");
    } else {
        showConfirmDivLayer("您确定要删除选择的记录吗？", {
            "okFun": function () {
                 jQuery.post("xszbhd_hdsb.do?method=del", {values: ids.toString(),type:jQuery("#type").val()}, function (data) {
                     showAlertDivLayer(data["message"]);
                     jQuery("#dataTable").reloadGrid();
                 }, 'json');
            }
        });

    }
}
function view() {
    var type = jQuery("#type").val();
    var rows = jQuery("#dataTable").getSeletRow();

    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要查看的记录！");
        return;
    }
    var title = "查看";
    var url = "xszbhd_hdsb.do?method=view&type="+type+"&hdsbid="+rows[0]["hdsbid"];
    showDialog(title, 600, 420, url);
}


function isNull(value){
    if(value == "" || value == null){
        return true;
    } else{
        return false;
    }
}