
function searchRs() {
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='view(\""
        + rowObject["id"]+"\");'>" + cellValue
        + "</a>";
}

function view(id) {
    showDialog("党课成绩查看",  600, 400, "dtjs_dkcj.do?method=dkcjView&id="+id);
}


function save(type) {
    var ids = "xh-dkcj-kssj";
    if(!checkNotNull(ids)){
        showAlert("请将带<font color='red'>*</font>的项目填写完整");
        return false;
    }
    var url = "dtjs_dkcj.do?method=save&type=" + type;
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

function add(){
    var url = "dtjs_dkcj.do?method=dkcjAdd";
    var title = "新增学生党课成绩";
    showDialog(title, 600, 400, url);
}
function update(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要修改的记录！");
    }else{
        var url = 'dtjs_dkcj.do?method=dkcjUpdate&id=' + rows[0]["id"];
        var title = "党课成绩修改";
        showDialog(title,  600, 400, url);
    }
}

// 删除
function del() {
    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length == 0) {
        showAlertDivLayer("请选择您要删除的记录！");
    } else {
        showConfirmDivLayer("是否确定删除勾选的记录？", {
            "okFun" : function() {
                var url = "dtjs_dkcj.do?method=dkcjDel";
                jQuery.post(url, {
                    values : ids.toString()
                }, function(data) {
                    if (data["success"] == false) {
                        showAlertDivLayer(data["message"]);
                    } else {
                        showAlertDivLayer(data["message"], {}, {
                            "clkFun" : function(tag) {
                                jQuery("#dataTable").reloadGrid();
                            }
                        });
                    }
                }, 'json');

            }});
    }
}


var DCCLBH = "dtjs_rdjjfzpy_dkcj.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
    //DCCLBH导出功能编号,执行导出函数
    customExport(DCCLBH, exportData);
}

//导出方法
function exportData() {
    setSearchTj();//设置高级查询条件
    var url = "dtjs_dkcj.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}


//导入
function dr1() {
    // 调用通用的导入function，参数是导入功能模块代码。
    toImportDataNew("IMPORT_DTJS_DKCJ");
    return false;

}