//党组织日常活动管理
jQuery(function(){
    initGridSetting();
});

function initGridSetting(){
    var userType = jQuery("#userType").val();
    var hdxdtxt = "活动心得";
    if("stu" != userType){
        hdxdtxt += "<br/>(已提交/未提交)"
    }
    var gridSetting = {
        caption:"我的活动列表",
        pager:"pager",
        url:"zhdj_dzzhd.do?method=getHdPageList&doType=query",
        colList:[
            {label:'key',name:'id', index: 'id',key:true ,hidden:true},
            {label:'活动名称',name:'hdmc', index: 'hdmc',width:'11%',formatter:hdLink},
            {label:'开始时间',name:'kssj', index: 'kssj',width:'10%'},
            {label:'结束时间',name:'jssj', index: 'jssj',width:'10%'},
            {label:'面向对象',name:'mxdxmc', index: 'mxdxmc',width:'13%'},
            {label:'面向对象代码',name:'mxdx', index: 'mxdx',hidden:true},
            {label:'发布人',name:'cjrxm', index: 'cjrxm',width:'10%'},
            {label:'发布时间',name:'cjsj', index: 'cjsj',width:'13%'},
            {label:hdxdtxt,name:'xdtj', index: 'xdtj',width:'10%',formatter:hdxdLink},
            {label:'学生心得体会是否提交',name:'xdtj',index:'xdtj',hidden:true},
            {label:'已提交数',name:'ytj',index:'ytj',hidden:true},
            {label:'未提交数',name:'wtj',index:'wtj',hidden:true},
        ],
        radioselect:true,
        sortname: "cjsj",
        sortorder: "desc"
    };

    gridSetting["params"]=getSuperSearch();
    jQuery("#dataTable").initGrid(gridSetting);
}

function hdLink(value,row){
    return "<a href='javascript:void(0);' class='name' onclick='dzzhdView(\""
        + row["id"] + "\");'>" + value + "</a>";
}

function hdxdLink(value,row){
    var userType = jQuery("#userType").val();
    if("stu" == userType){
        var xdtj = row.xdtj;
        var isTj = "未提交";
        if(xdtj != null && "1" == xdtj){
            isTj = '已提交';
        }
        return "<a class='name' href='#' onclick='showHdxdInfo(\""+row.id+"\");'>"+isTj+"</a>";
    }else{
        return '<a class="name" href="zhdj_dzzhd.do?method=getHdryList&joinStatus=1&hdid='+row.id+'">'+row.ytj+'/'+row.wtj+'</a>';
    }
}
function showHdxdInfo(id){
    showDialog("活动心得",  620, 420, "zhdj_dzzhd.do?method=hdXdInfo&status=add&hdid="+id);
}

//活动明细
function dzzhdView(id){
    showDialog("活动信息查看",  720, 520, "zhdj_dzzhd.do?method=hdInfo&status=view&id=" + id);
}
function searchRs(){
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}
//活动人员管理
function hdRygl(){
    var data= getSelectRow();
    if(!data){
        return;
    }
    document.location.href = "zhdj_dzzhd.do?method=getHdryList&joinStatus=0&hdid="+data.id;
}

//活动心得体会
function hdXd(){
    var data= getSelectRow();
    if(!data){
        return;
    }
    showDialog("活动心得体会",  620, 420, "zhdj_dzzhd.do?method=hdXdInfo&status=add&hdid="+data.id);
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

