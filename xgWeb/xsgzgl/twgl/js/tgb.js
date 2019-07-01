function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='tzbView(\""
			+ rowObject["jgid"]+"\");'>" + cellValue
			+ "</a>";
}

function tzbView(jgid) {
	showDialog("团组织查看", 600, 400, "tgbgl.do?method=viewTgb&jgid="+ jgid);
}

function selectTzz(type){
	var xh = jQuery("#xh").val();
	var gotopath = jQuery("#path").val()+'&xh='+xh;
	if(type=='update'){
		gotopath+='&jgid='+jQuery("#jgid").val();
	}
	if(!!xh == false){
		showAlert("请先填写学号");
		return false;
	}
	showDialog('请选择一个团组织',800,500,'tgbgl.do?method=showTzz&gotopath='+gotopath);
}

function saveTgb(type){
	var flg = true;
	var ids = null;
	ids = "xh-zwdm-rzzz-rzsj";
	if(check(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	var url;
	if(type == 'save'){
		url = "tgbgl.do?method=saveTgbForAdd";
	}else{
		url = "tgbgl.do?method=saveTgbForUpdate";
	}
	ajaxSubFormWithFun("tgbForm", url, function(data) {
		if (data["message"] == "保存成功！") {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			showAlert(data["message"]);
		}
	});
	
}

function add() {
	var url = "tgbgl.do?method=addTgbJg";
	var title = "团干部增加";
	showDialog(title, 800, 600, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else{
		var url = 'tgbgl.do?method=updateTgb&jgid=' + rows[0]["jgid"];
		var title = "团干部修改";
		showDialog(title, 800, 600, url);
	}

}

// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("是否确定删除勾选的记录？", {
		"okFun" : function() {
		var url = "tgbgl.do?method=delTgb";
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
//导入
function dr() {
	// 调用通用的导入function，参数是导入功能模块代码。
	toImportDataNew("IMPORT_TYGL_TGBGL");
	return false;
}

var DCCLBH = "tygl_tgbgl_tgbjgList.do";// dcclbh,导出功能编号

// 自定义导出 功能
function exportConfig() {
	// DCCLBH导出功能编号,执行导出函数
	customExport(DCCLBH, tzbExportData);
}

// 导出方法
function tzbExportData() {
	setSearchTj();// 设置高级查询条件
	var url = "tgbgl.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,导出功能编号
	url = addSuperSearchParams(url);// 设置高级查询参数
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