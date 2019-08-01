//学费减免申请列表
jQuery(function(){
    initGridSetting();
});

function initGridSetting(){
    var gridSetting = {
        caption:"学费减免结果列表",
        pager:"pager",
        multiselect:true,
        radioselect:true,
        url:"xszz_new_xfjm.do?method=getJgPageList&doType=query",
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
            {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
            {label:'审核状态',name:'shztmc', index: 'shztmc',width:'6%'}
        ],
        sortname: "sqsj",
        sortorder: "desc"
    };

    gridSetting["params"]=getSuperSearch();
    jQuery("#dataTable").initGrid(gridSetting);
}

function xhLink(value,row){
    return "<a href='javascript:void(0);' class='name' onclick='xfjmView(\""
        + row["id"] + "\",\"" + value + "\");'>" + value
        + "</a>";
}

function xfjmView(id,xh){
    showDialog("学费减免信息查看",  720, 520, "xszz_new_xfjm.do?method=xfjmSq&status=view&id=" + id
        + "&xh=" + xh);
}
function searchRs(){
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}
//跳转学费减免申请页面
function xfjmzj(){
    showDialog("学费减免信息增加",  720, 560, "xszz_new_xfjm.do?method=xfjmJgEdit&status=add");
}
//跳转学费减免修改页面
function xfjmxg(){
    var row = getSelectRow();
    if(!row){
        return false;
    }
    showDialog("学费减免信息修改",  720, 520, "xszz_new_xfjm.do?method=xfjmJgEdit&status=update&id="+row.id);
}
//删除学费减免结果信息
function xfjmsc(){
    var row = getSelectRow();
    if(!row){
        return false;
    }
    var url = "xszz_new_xfjm.do?&method=removeJg&id="+row.id;
    showConfirmDivLayer("您确定删除该记录吗?",{"okFun":function(){
            jQuery.post(url,function(result){
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
        }});
}
//提交减免申请
function xfjmtj(){
    var row = getSelectRow();
    if(!row){
        return false;
    }
    if(row.shzt != '0'){
        showAlertDivLayer("只能对未提交的记录进行此操作！");
        return false;
    }
    var url = "xszz_new_xfjm.do?&method=sqztxg&id="+row.id+"&shzt=5";
    showConfirmDivLayer("您确定要提交该申请吗?",{"okFun":function(){
            jQuery.post(url,function(result){
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
        }});
}

//撤销减免申请
function xfjmcx(){
    var row = getSelectRow();
    if(!row){
        return false;
    }
    if(row.shzt != '5' && row.shzt != '3'){
        showAlertDivLayer("只能撤销未被审核和退回的记录！");
        return false;
    }
    var url = "xszz_new_xfjm.do?&method=sqztxg&id="+row.id+"&shzt=3";
    showConfirmDivLayer("您确定撤销该申请吗?",{"okFun":function(){
            jQuery.post(url,function(result){
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
        }});
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

//导入学费减免学生信息
function xfjmdr(){
    var url="xszz_new_xfjm.do?method=importXfjmjg";
    showDialog("学费减免结果导入", 600, 340, url);
}
//导出学费减免信息
function xfjmdc(){
    var DCCLBH='xszz_xfjm_jg.do';
    customExport(DCCLBH, exportData);
}

function exportData(){
    var DCCLBH='xszz_xfjm_jg.do';
    setSearchTj();//设置高级查询条件
    var url = "xszz_new_xfjm.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}

