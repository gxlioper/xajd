function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

var DCCLBH = "stgl_zjsr_stgl.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ExportDatas);
}

//导出方法
function ExportDatas() {
	setSearchTj();//设置高级查询条件
	var url = "stgl_zjsr.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


//删除
function del() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	 }
	var flag = false;
	var ids = jQuery("#dataTable").getSeletIds();
	showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
		jQuery.post("stgl_zjsr.do?method=stglDel",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	
}

//增加页面跳转
function add(){
	showDialog('津贴发放增加',650,420,'stgl_zjsr.do?method=stglAdd');
}


//编辑页面跳转
function edit(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要修改的记录！");
	} else {
		showDialog('津贴发放修改',650,420,'stgl_zjsr.do?method=stglEdit&id=' + rows[0]["id"]);
	}
}

//保存
function save(url,checkId) {
	if (!checkNull(checkId)) {
		return false;
	}
	lock();
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data["success"] == "false"){
				showAlert(data["message"]);
			} else {
				showAlert(data["message"], {}, {	"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}});
			}
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
	});
	unlock();
}

//导入
function importConfig(){
	toImportDataNew("IMPORT_STGLZJSR");
	return false;
}

function stmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='stglView(\""+ rowObject["id"] + "\");'>" + cellValue+ "</a>";
}

function stglView(id) {
	showDialog("社团管理查看", 650, 370, "stgl_zjsr.do?method=stglView&id="+id );
}
