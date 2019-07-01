var gridSetting = {
    caption: "发布列表",
    pager: "pager",
    url: "sxzzjy_xymxbzbgl.do?method=getList&type=query",
    colList: [
        {label: 'newsid', name: 'newsid', index: 'newsid',hidden:true,key:true},
        {label: 'fbbmdm', name: 'fbbmdm', index: 'fbbmdm',hidden:true},
        {label: '标题',name:'newstitle', index: 'newstitle', width: '30%',formatter:titleLink},
        {label: '发布时间', name: 'fbsj', index: 'fbsj', width: '15%'},
        {label: '发布人', name: 'fbr', index: 'fbr', width: '12%'},
        {label: '发布部门', name: 'fbbmmc', index: 'fbbmmc', width: '15%'},
        {label: '是否发布', name: 'sffb', index: 'sffb', width: '8%'},
        {label: '是否置顶', name: 'sfzd', index: 'sfzd', width: '8%'},
        {label: '已阅读名单', width: '12%',formatter:yydmdLink}
    ],
    sortname: "sfzd desc,sffb desc,fbsj",
    sortorder: "desc"
};
jQuery(function () {
    var map = getSuperSearch();
    gridSetting["params"] = map;
    jQuery("#dataTable").initGrid(gridSetting);
});

function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}


function view(newsid) {
    var url="sxzzjy_xymxbzbgl.do?method=xymxbzbglView&newsid=" + newsid;
    document.forms[0].action = url;
    document.forms[0].target = "_blank";
    document.forms[0].submit();
    document.forms[0].target = "_self";
    //showDialog("查看", 700,550, "sxzzjy_xymxbzbgl.do?method=xymxbzbglView&newsid=" + newsid);
}

function titleLink(cellValue, rowObject) {
    var value = cellValue;
    if(cellValue.length > 18)
        value = value.substring(0,18)+"...";
    return "<a href='javascript:void(0);' class='name' title='"+cellValue+"' onclick='view(\""
        + rowObject["newsid"] + "\");'>" + value
        + "</a>";
}

function yydmdView(newsid){
    var url="sxzzjy_xymxbzbgl.do?method=getYydmd&newsid="+newsid;
    showDialog('已阅读人员名单',700,450,url);
}
function yydmdLink(cellValue, rowObject){
    return "<a href='javascript:void(0);' class='name' onclick='yydmdView(\""
        + rowObject["newsid"] + "\");'>已阅读名单</a>";
}

//预览
function newsyl(){
    var ids = jQuery("#dataTable").getSeletIds();
    if(ids.length != 1){
        showAlertDivLayer("请选择一条您要预览的记录！");
        return false;
    }else{
        view(ids[0]);
    }

}
//删除
function delNews(){
    var ids = jQuery("#dataTable").getSeletIds();
    if(ids.length == 0){
        showAlertDivLayer("请选择您要删除的记录！");
        return false;
    }else {
        var url = "sxzzjy_xymxbzbgl.do?method=xymxbzbglDel";
        showConfirmDivLayer("您确定要删除选择的记录吗？", {
            "okFun": function () {
                jQuery.post(url, {values: ids.toString()}, function (data) {
                    showAlertDivLayer(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                }, 'json');
            }
        });
    }
}
function fb(){
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length == 0) {
        showAlertDivLayer("请选择您要发布的记录！");
    } else {
        for (var i = 0; i < ids.length; i++) {
            if (rows[i]["sffb"] == "是") {
                showAlertDivLayer("记录已发布，不能发布！");
                return false;
            }
        }
        showConfirmDivLayer("您确定要发布选择的记录吗？", {
            "okFun" : function() {
                jQuery.post("sxzzjy_xymxbzbgl.do?method=xymxbzbglFb", { values : ids.toString() },
                    function(data) {
                        showAlertDivLayer(data["message"]);
                        jQuery("#dataTable").reloadGrid();
                    }, 'json');
            }
        });
    }
}
function qxfb(){
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length == 0) {
        showAlertDivLayer("请选择您要取消发布的记录！");
    } else {
        for (var i = 0; i < ids.length; i++) {
            if (rows[i]["sffb"] == "否") {
                showAlertDivLayer("记录未发布，不能取消！");
                return false;
            }
        }
        showConfirmDivLayer("您确定要取消发布选择的记录吗？", {
            "okFun" : function() {
                jQuery.post("sxzzjy_xymxbzbgl.do?method=xymxbzbglQxfb", { values : ids.toString() },
                    function(data) {
                        showAlertDivLayer(data["message"]);
                        jQuery("#dataTable").reloadGrid();
                    }, 'json');
            }
        });
    }
}
function zd(){
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length == 0) {
        showAlertDivLayer("请选择您要置顶的记录！");
    } else {
        for (var i = 0; i < ids.length; i++) {
            if (rows[i]["sfzd"] == "是") {
                showAlertDivLayer("记录已置顶，不能再置顶！");
                return false;
            }
        }
        showConfirmDivLayer("您确定要置顶选择的记录吗？", {
            "okFun" : function() {
                jQuery.post("sxzzjy_xymxbzbgl.do?method=xymxbzbglZd", { values : ids.toString() },
                    function(data) {
                        showAlertDivLayer(data["message"]);
                        jQuery("#dataTable").reloadGrid();
                    }, 'json');
            }
        });
    }
}
function qxzd(){
    var ids = jQuery("#dataTable").getSeletIds();
    var rows = jQuery("#dataTable").getSeletRow();
    if (ids.length == 0) {
        showAlertDivLayer("请选择您要取消置顶的记录！");
    } else {
        for (var i = 0; i < ids.length; i++) {
            if (rows[i]["sfzd"] == "否") {
                showAlertDivLayer("记录未置顶，不能取消！");
                return false;
            }
        }
        showConfirmDivLayer("您确定要取消置顶选择的记录吗？", {
            "okFun" : function() {
                jQuery.post("sxzzjy_xymxbzbgl.do?method=xymxbzbglQxzd", { values : ids.toString() },
                    function(data) {
                        showAlertDivLayer(data["message"]);
                        jQuery("#dataTable").reloadGrid();
                    }, 'json');
            }
        });
    }
}
function updateNews(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("请选择您要修改的记录！");
    }else{
        var url="sxzzjy_xymxbzbgl.do?method=xymxbzbglUpdate&newsid="+rows[0]["newsid"];
        showDialog('校园明星榜中榜修改',900,519,url);
    }
}
function addNews(){
    var url="sxzzjy_xymxbzbgl.do?method=xymxbzbglAdd";
    showDialog('校园明星榜中榜增加',900,519,url);
}

