
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='cjtsxsView(\""
			+ rowObject["id"]+"\");'>" + cellValue
			+ "</a>";
}

function cjtsxsView(id) {
	showDialog("春季特殊问题学生查看", 800, 550, "xljk_cjtsxs.do?method=viewCjTsxs&id="+id);
}

function save(type){
	var ids;
	if(type == 'save'){
		ids = "xh-wtms";
	}else{		
		ids = "wtms";
	}
	if(check(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	var url = "xljk_cjtsxs.do?method=saveJg&type=" + type;
	ajaxSubFormWithFun("cjtsxsForm", url, function(data) {
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

function add() {
	var url = "xljk_cjtsxs.do?method=addCjTsxs";
	var title = "春季特殊问题学生新增";
	showDialog(title, 800, 550, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else {
		var url = 'xljk_cjtsxs.do?method=editCjTsxs&id=' + rows[0]["id"];
		var title = "春季特殊问题学生修改";
		showDialog(title, 800, 550, url);
	}

}

// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	}else{
		showConfirmDivLayer("是否确定删除勾选的记录？", {"okFun" : function() {
		var url = "xljk_cjtsxs.do?method=delCjtsxs";
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

var DCCLBH = "xlzx_cjtsxs_cjtsxsgl.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, cjtsxsExportData);
}


//导出方法
function cjtsxsExportData() {
	setSearchTj();//设置高级查询条件
	var url = "xljk_cjtsxs.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


/**
 * 验证是否存在空项
 * @param ids 要验证的控件id "-"分隔
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

function dr() {
	// 调用通用的导入function，参数是导入功能模块代码。
	toImportDataNew("IMPORT_CJTSXS");
	return false;

}
