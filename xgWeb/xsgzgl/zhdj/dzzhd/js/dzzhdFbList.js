//党组织日常活动管理
jQuery(function(){
    initGridSetting();
});

function initGridSetting(){
    var gridSetting = {
        caption:"我的活动列表",
        pager:"pager",
        url:"zhdj_dzzhd.do?method=getHdPageList&doType=query",
        colList:[
            {label:'key',name:'id', index: 'id',key:true ,hidden:true},
            {label:'活动名称',name:'hdmc', index: 'hdmc',width:'11%',formatter:hdLink},
            {label:'开始时间',name:'kssj', index: 'kssj',width:'8%'},
            {label:'结束时间',name:'jssj', index: 'jssj',width:'8%'},
            {label:'面向对象',name:'mxdxmc', index: 'mxdxmc',width:'13%'},
            {label:'面向对象代码',name:'mxdx', index: 'mxdx',hidden:true},
            {label:'发布人',name:'cjrxm', index: 'cjrxm',width:'13%'},
            {label:'发布时间',name:'cjsj', index: 'cjsj',width:'13%'}
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

//活动明细
function dzzhdView(id){
    showDialog("活动信息查看",  720, 520, "zhdj_dzzhd.do?method=hdInfo&status=view&id=" + id);
}
function searchRs(){
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}
//调转活动信息添加页面
function dzzhdAdd(){
    showDialog("活动信息发布",  720, 560, "zhdj_dzzhd.do?method=hdInfo&status=add");
}
//跳转修改页面
function dzzhdXg(){
    var row = getSelectRow();
    if(!row){
        return false;
    }
    showDialog("活动信息修改",  720, 520, "zhdj_dzzhd.do?method=hdInfo&status=update&id="+row.id);
}
//删除活动信息
function dzzhdSc(){
    var row = getSelectRow();
    if(!row){
        return false;
    }
    var url = "zhdj_dzzhd.do?&method=removeById&id="+row.id;
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

