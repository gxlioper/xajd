var checkId = 'xh-hdmc-fwdd-fwsj-fwsc-fwddssx-zbdw';

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='SthdjgView(\""
			+ rowObject["hdid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

// 保存
function saveSthdjg(type) {
	if(!checkNotNull(checkId)){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
	var url = "sthdglSthdjg.do?method=saveSthdjg&type=" + type;
	ajaxSubFormWithFun("SthdjgForm", url, function(data) {
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
//增加
function add() {
	var url = "sthdglSthdjg.do?method=addSthdjg";
	var title = jQuery("#gnmkmc").val()+"填写";
	showDialog(title, 800, 550, url);
}

//修改
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if(rows[0]['sjly']=='1'){
			showAlertDivLayer("审核流程过来的记录不能修改！");
			return false;
		}
		var url = 'sthdglSthdjg.do?method=editSthdjg&hdid=' + rows[0]["hdid"]
				+ '&xh=' + rows[0]["xh"];
		var title = jQuery("#gnmkmc").val()+"修改";
		showDialog(title, 800, 550, url);
	}
}
//查看
function SthdjgView(id, xh) {
	showDialog(jQuery("#gnmkmc").val()+"查看", 800, 550, "sthdglSthdjg.do?method=viewSthdjg&hdid="
			+ id + "&xh=" + xh);
}
// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("审核流程过来的记录不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("sthdglSthdjg.do?method=delSthdjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
//导入
function dr() {
	// 调用通用的导入function，参数是导入功能模块代码。
	toImportDataNew("IMPORT_N980104_XTHDJG");
	return false;

}
var DCCLBH = "stgl_sthdgl_sthdcx.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, sthdglSthdjgExportData);
}

//导出方法
function sthdglSthdjgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "sthdglSthdjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}