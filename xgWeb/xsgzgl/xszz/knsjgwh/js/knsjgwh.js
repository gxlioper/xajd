/* 导入 */
function importXf(){
	toImportDataNew("IMPORT_N720208_KNSDCWH");
	return false;
}
var DCCLBH = "xszz_knsdcjgwh.do";// dcclbh,导出功能编号
function exportConfig() {
	customExport(DCCLBH, xfExportData);
}
//导出方法
function xfExportData() {
	setSearchTj();
	var url = "xszz_knsdcwh.do?method=exportData&dcclbh=" + DCCLBH;
	url = addSuperSearchParams(url);
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}




//单个查看
function bjLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onClick='view(\""+rowObject["id"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}
function view(id,cellValue){
	showDialog('学生调查信息查看',700,430,'xszz_knsdcwh.do?method=view&id='+id+ "&xh=" + cellValue,null);
}


// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("xszz_knsdcwh.do?method=del", {
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
