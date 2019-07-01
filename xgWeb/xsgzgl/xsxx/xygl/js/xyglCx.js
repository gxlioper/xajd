var DCCLBH = "xsxx_xygl.do";// dcclbh,导出功能编号

// 查询
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

// 增加
function showXyglAdd() {
	var url = "xsxx_xyglxx.do?method=xyglZj";
	showDialog("增加校友管理信息", 900, 500, url);
}

//学号链接查看信息
function xhLink(cellValue, rowObject) {
	var xh = rowObject.xh;
	if (xh == null) {
		xh = "";
	}
	cellValue = "<a  href='javascript:void(0);'  class='name'   onclick='xyglView(\"" + xh
			+ "\");return false;' >" + xh + "</a>";
	return cellValue;
}

//新版查看弹出层
function xyglView(xh) {
	showDialog("校友管理信息查询", 850, 500, "xsxx_xyglxx.do?method=xyglCk&xh=" + xh);
}

// 修改
function showXyglEdit() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_xyglxx.do?method=xyglXg";
		url += "&xh=" + ids;
		showDialog("修改校友信息", 900, 550, url);
	} else {
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	}
}

// 删除
function deleteXygl() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len != 0) {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				var url = "xsxx_xyglxx.do?method=xyglSc";
				jQuery.post(url, {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"], {}, {
						"clkFun" : function() {
							jQuery("#dataTable").reloadGrid();
						}
					});
				}, 'json');
			}
		});
	} else {
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	}
}


// 自定义导出 功能
function xyglExportConfig() {
	// DCCLBH导出功能编号,执行导出函数
	customExport(DCCLBH, xyglExportData, 1000, 500);
}

// 导出方法
function xyglExportData() {
	setSearchTj();// 设置高级查询条件
	var url = "xsxx_xyglxx.do?dcclbh=" + DCCLBH + "&method=";
	
	url += "xyglExportData";
	
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
