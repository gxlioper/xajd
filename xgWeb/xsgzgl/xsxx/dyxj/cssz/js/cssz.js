/**
 * 删除
 * @return
 */
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("dyxj_dyzgk.do?method=delJg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "xsxx_dyxj_cssz.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, csszExportData);
}

//导出方法
function csszExportData() {
	setSearchTj();//设置高级查询条件
	var url = "xsxxDyxjCssz.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 增加学生
 * @return
 */
function add(){
	var url = "xsxx_dyxj_dyzgk.do";
	var title = "增加资格库学生";
	showDialog(title,900, 550, url);
}