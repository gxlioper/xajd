var checkId = 'xh-hdmc-dd-sj-zbdw-ddssx-xn-xq';
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='view(\""
        + rowObject["jgid"] + "\",\"" + rowObject["sjly"] + "\",\"" + cellValue + "\");'>" + cellValue
        + "</a>";
}
//查看
function view(id,sjly,xh) {
    showDialog(jQuery("#gnmkmc").val()+"查看", 800, 550, "llxxjl_jg.do?method=llxxjlJgView&jgid="
        + id + "&sjly=" + sjly + "&xh=" + xh );
}
// 保存
function save(type) {
    if(!checkNotNull(checkId)){
        showAlert("请将带<font class='red'>*</font>的项目填写完整！");
        return false;
    }
    var url = "llxxjl_jg.do?method=save&type=" + type;
    ajaxSubFormWithFun("form", url, function(data) {
        if(data["message"]=="保存成功！"){
            showAlert(data["message"],{},{"clkFun":function(){
                if (parent.window){
                    refershParent();
                }
            }});
        }else{
            showAlert(data["message"]);
        }
    });

}
//增加
function add() {
    var url = "llxxjl_jg.do?method=llxxjlJgAdd";
    var title = jQuery("#gnmkmc").val()+"填写";
    showDialog(title, 800, 550, url);
}

//修改
function update() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要修改的记录！");
    } else {
        if(rows[0]['sjly']=='1'){
            showAlertDivLayer("审核流程过来的记录不能修改！");
            return false;
        }else if(rows[0]['sjly']=='ek'||rows[0]['sjly']=='ekbl'){
            showAlertDivLayer("第二课堂活动的记录不能修改！");
            return false;
        }
        var url = 'llxxjl_jg.do?method=llxxjlJgUpdate&jgid=' + rows[0]["jgid"]
            + '&xh=' + rows[0]["xh"];
        var title = jQuery("#gnmkmc").val()+"修改";
        showDialog(title, 800, 550, url);
    }
}
// 删除
function del() {
    var ids = jQuery("#dataTable").getSeletIds();

    if (ids.length == 0){
        showAlertDivLayer("请选择您要删除的记录！");
    } else {
        var rows = jQuery("#dataTable").getSeletRow();
        for(var i=0;i<ids.length;i++){
            if(rows[i]['sjly']=='1'){
                showAlertDivLayer("审核流程过来的记录不能删除！");
                return false;
            }else if(rows[0]['sjly']=='ek'||rows[0]['sjly']=='ekbl'){
                showAlertDivLayer("第二课堂活动的记录不能修改！");
                return false;
            }
        }
        showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
            jQuery.post("llxxjl_jg.do?method=del",{values:ids.toString()},function(data){
                showAlertDivLayer(data["message"]);
                jQuery("#dataTable").reloadGrid();
            },'json');
        }});
    }
}
//导入
function dr() {
    // 调用通用的导入function，参数是导入功能模块代码。
    toImportDataNew("IMPORT_SHSJJL_JG");
    return false;

}
var DCCLBH = "dtjs_llxxjl_jg.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
    //DCCLBH导出功能编号,执行导出函数
    customExport(DCCLBH, exportData);
}

//导出方法
function exportData() {
    setSearchTj();//设置高级查询条件
    var url = "llxxjl_jg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}