var DCCLBH = "zxdk_syddm_hkwh.do";//dcclbh,导出功能编号

// 高级查询
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

// 学号链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewHkxx(\""
			+ rowObject["jgid"]+"\");'>" + cellValue
			+ "</a>";
}

// 查看
function viewHkxx(jgid) {
	showDialog("查看", 800, 500, "syddk_hk.do?method=viewHkwh&jgid="+jgid);
}

// 增加
function add() {
	var url = "syddk_hk.do?method=addHkwh";
	var title = "增加";
	showDialog(title, 800, 508, url);
}

// 修改
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else {
		var url = 'syddk_hk.do?method=editHkwh&jgid=' + rows[0]["jgid"];
		var title = "修改";
		showDialog(title, 800, 508, url);
	}

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
				jQuery.post("syddk_hk.do?method=delHkwh", {
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

// 保存
function saveHkjg(type){
	var xh = jQuery("#xh").val();
	var hkzt = jQuery("#hkzt").val();
	var hksj = jQuery("#hksj").val();
	if(type=='save'){
		if("" == xh || "" == hkzt || "" == hksj) {
			showAlert("请将带<font color='red'>*</font>的项目填写完整");
			return false;		
		}
	}else{
		if("" == hkzt || "" == hksj) {
			showAlert("请将带<font color='red'>*</font>的项目填写完整");
			return false;		
		}
	}
	var url = "syddk_hk.do?method=saveHkwh&type=" + type;
	ajaxSubFormWithFun("syddkhkwhForm", url, function(data) {
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

// 自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, exportData);
}

// 导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "syddk_hk.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

// 导入
function dr(){
	toImportDataNew("IMPORT_SYYDKHKWH");
	return false;
}
