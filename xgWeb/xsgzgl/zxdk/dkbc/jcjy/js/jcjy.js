//dcclbh,导出功能编号
var DCCLBH = "zxdk_jcjy_bcdc.do";

//高级查询
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
	}

//增加
function add(){
	var url = "jcjy_bcdc.do?method=jcjyAdd";
	var title = "增加";
	showDialog(title,800,500,url);
}

//修改
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else {
		var url = 'jcjy_bcdc.do?method=jcjyUpdate&juid=' + rows[0]["juid"];
		var title = "修改";
		showDialog(title,800,500,url);
	}

}

//保存
function saveJcjy(type){
	var xh = jQuery("#xh").val();
	var dclb = jQuery("#dclb").val();
	if(type=='save'){
		if( (xh==null || jQuery.trim(xh)=='')){
			showAlert("请将带<font color='red'>*</font>的项目填写完整");
			return false;
		}
	}else{
		if( (xh==null || jQuery.trim(xh)=='')){
				showAlert("请将带<font color='red'>*</font>的项目填写完整");
				return false;
			}
	}
	var url = "jcjy_bcdc.do?method=saveJcjy&type=" + type;
	ajaxSubFormWithFun("jcjyModel", url, function(data) {
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

//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("jcjy_bcdc.do?method=delJcjy", {
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

//查看
function jcjyView(juid){
	showDialog('查看',800,500,'jcjy_bcdc.do?method=jcjyView&juid='+juid);
}

//自定义导出
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, exportData);
}

// 导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "jcjy_bcdc.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//导入
function importJcjy(){
	toImportDataNew("IMPORT_ZXDKJCJY");
	return false;
}