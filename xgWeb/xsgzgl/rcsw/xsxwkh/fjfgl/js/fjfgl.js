var ids = "xh-xn-fwxss-fwsj-fz";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='fjfglView(\""
			+ rowObject["id"]+"\");'>" + cellValue
			+ "</a>";
}
function fjfglView(id) {
	showDialog("附加分查看", 650, 450, "xsxwkhFjfgl.do?method=fjfglView&id="+id);
}
function saveFjfgl(type) {
	if(checkNull(ids)&&checkTextAreaLength("bz","备注","500")){
	var url = "xsxwkhFjfgl.do?method=saveFjfgl&type=" + type;
	ajaxSubFormWithFun("fjfglForm", url, function(data) {
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
}

//修改
function editFjfgl(type) {
	if(checkNull(ids)&&checkTextAreaLength("bz","备注","500")){
	var url = "xsxwkhFjfgl.do?method=saveEditFjfgl&type=" + type;
	ajaxSubFormWithFun("fjfglForm", url, function(data) {
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
}

function add() {
	var url = "xsxwkhFjfgl.do?method=addFjfgl";
	var title = "附加分";
	showDialog(title, 650, 450, url);
}
function update() {
	
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'xsxwkhFjfgl.do?method=editFjfgl&id=' + rows[0]["id"];
		var title = "附加分修改";
		showDialog(title, 650, 450, url);
	}

}

// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else{
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("审核流程过来的记录不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("xsxwkhFjfgl.do?method=delFjfgl", {
					values : ids.toString()
				},
						function(data) {
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
			}
		});
	}
}

var DCCLBH = "xsxwkh_fjfgl.do";//dcclbh,导出功能编号

function dr() {
	// 调用通用的导入function，参数是导入功能模块代码。
	toImportDataNew("IMPORT_N158705_FJFGL");
	return false;

}

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, fjfExportData);
}

//导出方法
function fjfExportData() {
	setSearchTj();//设置高级查询条件
	var url = "xsxwkhFjfgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}