var action="fbglxsxx.do";
var title="分班管理学生";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
//点击序号跳转
function dcmcLink(cellValue, rowObject) {
	var pk = rowObject["pk"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + pk
			+ "')\" class='name'>" + cellValue + "</a>";
}
//查看信息
function ckxx(id) {
	var query=jQuery("#query").val();
	var url = action+"?method=showView&pk=" + id;
	title += "信息";
	showDialog(title, 800, 500, url);
}
function drxx(){
	toImportDataNew("IMPORT_FBGL_XSXX");
	return false;
}
//删除
function del() {
	var ids = jQuery("#dataTable").getRowCount();
	if(ids<=0){
		return showAlertDivLayer("不存在学生信息！");
	}
	var rows = jQuery("#dataTable").getSeletRow();
	for ( var int = 0; int < rows.length; int++) {
		if(null!=rows[int]["xh"]&&""!=rows[int]["xh"]){
			showAlertDivLayer("学生已编学号，不允许删除！");
			return false;
		}
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showConfirmDivLayer("您确定要删除所有记录吗？", {
			"okFun" : function() {
				jQuery.post(action+"?method=del", {
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post(action+"?method=del", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}