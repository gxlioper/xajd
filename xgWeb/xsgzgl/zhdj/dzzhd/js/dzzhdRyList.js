//党组织活动人员管理
var joinStatus;
var hdid;
jQuery(function(){
    joinStatus = jQuery("#joinStatus").val();
    hdid = jQuery("#hdid").val();
    //默认显示可加入的
    if(joinStatus == null){
        joinStatus == '0'
    }
    selectTab(joinStatus);

});


function gridSetting0(){
    var gridSetting = {
        caption: "可添加学生列表",
        pager: "pager",
        url: "zhdj_dzzhd.do?method=getHdryList&doType=query&joinStatus="+joinStatus+"&hdid="+hdid,
        colList: [
            {label: '学号', name: 'xh', index: 'xh', width: '15%',formatter:xhLink},
            {label: '姓名', name: 'xm', index: 'xm', width: '15%'},
            {label: '性别', name: 'xbmc', index: 'xbmc', width: '10%'},
            {label: '学院', name: 'xymc', index: 'xymc', width: '20%'},
            {label: '专业', name: 'zymc', index: 'zymc', width: '20%'},
            {label: '班级', name: 'bjmc', index: 'bjmc', width: '20%'},
            {label:'学院代码',name:'xydm',index:'xydm',hidden:true},
            {label:'专业代码',name:'zydm',index:'zydm',hidden:true},
            {label:'班级代码',name:'bjdm',index:'bjdm',hidden:true}
        ]
    };
    return gridSetting;
}

function gridSetting1(){
    var gridSetting = {
        caption: "已添加学生信息",
        pager: "pager",
        url: "zhdj_dzzhd.do?method=getHdryList&doType=query&joinStatus="+joinStatus+"&hdid="+hdid,
        colList: [
            {label: '学号', name: 'xh', index: 'xh', width: '10%',formatter:xhLink},
            {label: '姓名', name: 'xm', index: 'xm', width: '15%'},
            {label: '性别', name: 'xbmc', index: 'xbmc', width: '10%'},
            {label: '学院', name: 'xymc', index: 'xymc', width: '20%'},
            {label: '专业', name: 'zymc', index: 'zymc', width: '15%'},
            {label: '班级', name: 'bjmc', index: 'bjmc', width: '20%'},
            {label: '活动心得', name: 'hdxdtx', index: 'hdxdtx', width: '10%',formatter:hdxdLink},
            {label:'活动id',name:'hdid',index:'hdid',hidden:true}
        ]
    };
    return gridSetting;
}
function xhLink(value,row) {
    return '<a href="javascript:void(0);" class="name" onclick="zxsxxView(\''+value+'\');return false;">'+value+'</a>'
}
function zxsxxView(xh) {
    showDialog("学生信息查询", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh
        + "&xs");
}
function hdxdLink(value,row){
    if('已提交'== value){
        return "<a class='name' href='#' onclick='showHdxdInfo(\""+row.hdid+"\",\""+row.xh+"\");'>"+value+"</a>";
    }else{
        return value;
    }

}

function showHdxdInfo(id,xh){
    showDialog("活动心得",  620, 420, "zhdj_dzzhd.do?method=hdXdInfo&status=view&hdid="+id+"&xh="+xh);
}

//可以入 已加入群组选择
function selectTab(val){
    joinStatus = val;
    jQuery("#joinStatus").val(val);
    if(val =='0'){//可加入
        jQuery("#li_sc").hide();
        jQuery("#li_sz").show();
        jQuery("#li_xd").hide();
        jQuery("#kjr").prop("class","ha");
        jQuery("#yjr").removeClass("ha");
        gridSetting0["params"]=getSuperSearch();
        jQuery("#dataTable").initGrid(gridSetting0());
    }else{//已加入
        jQuery("#li_sc").show();
        jQuery("#li_xd").show();
        jQuery("#li_sz").hide();
        jQuery("#kjr").removeClass("ha");
        jQuery("#yjr").prop("class","ha");
        gridSetting1["params"]=getSuperSearch();
        jQuery("#dataTable").initGrid(gridSetting1());
    }
}

function searchRs(){
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}
function reload(){
    searchRs();
}

//加入活动
function addRy() {
    var rows = jQuery("#dataTable").getSeletRow();
    if(rows == null || rows.length ==0){
        showAlertDivLayer("请至少选择一条记录！");
        return false;
    }
    var xhs = "";
    jQuery.each(rows,function (index,item) {
        xhs += item.xh;
        if(index != rows.length-1){
            xhs+=',';
        }
    });
    var data = {
        xhs:xhs,
        hdid:hdid
    };
    var url = "zhdj_dzzhd.do?method=hdRySave";
    showConfirmDivLayer("你确定要添加勾选的用户吗?", {
        "okFun": function () {
            jQuery.post(url,data,function(result){
                if (result.code == 1) {
                    showAlert(result.msg,{},{"clkFun":function(){
                            reload();
                        }});
                } else {
                    //存储失败
                    showAlert(result.msg);
                    return false;
                }
            },'json');
        }
    });

}
//移除
function removeRy() {
    var rows = jQuery("#dataTable").getSeletRow();
    if(rows == null || rows.length ==0){
        showAlertDivLayer("请至少选择一条记录！");
        return false;
    }
    var xhs = "";
    jQuery.each(rows,function (index,item) {
        xhs += item.xh;
        if(index != rows.length-1){
            xhs+=',';
        }
    });
    var data = {
        xhs:xhs,
        hdid:hdid
    };
    var url = "zhdj_dzzhd.do?method=hdRyRemove";
    showConfirmDivLayer("你确定要移除所勾选的用户吗?", {
        "okFun": function () {
            jQuery.post(url, data, function (result) {
                if (result.code == 1) {
                    showAlert(result.msg, {}, {
                        "clkFun": function () {
                            reload();
                        }
                    });
                } else {
                    //存储失败
                    showAlert(result.msg);
                    return false;
                }
            }, 'json');
        }
    });
}
//查看活动心得体会
function viewHdxd(){
    var rows = jQuery("#dataTable").getSeletRow();
    if(rows == null || rows.length !=1){
        showAlertDivLayer("请选择一条记录！");
        return false;
    }
    showHdxdInfo(rows[0].hdid,rows[0].xh)

}
