function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add() {
	var url = "xlzxnew_xsxlpc.do?method=add";
	var title = "增加心理普测结果";
	showDialog(title,770,550,url);
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'xlzxnew_xsxlpc.do?method=updateJg&id=' + rows[0]["id"];
		var title = "修改心理普测结果";
		showDialog(title, 770, 550, url);
	}
}

//增加修改结果保存
function saveForm(){
	var ids = "xh-pcjg";
	if(!checkNotNull(ids)){
		return showAlert("请将带<font class='red'>*</font>的项目填写完整！");
	}
	var url = "xlzxnew_xsxlpc.do?method=saveJg";
	ajaxSubFormWithFun("XsxlpcForm", url, function(data) {
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

//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='jgView(\""
			+ rowObject["id"] + "\");'>" + cellValue
			+ "</a>";
}

//查看学生ajaxurl跳转
function jgView(id) {
	showDialog("查看心理普测结果", 770, 500, "xlzxnew_xsxlpc.do?method=ck&id="
			+ id);
}
//删除住宿结果
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("xlzxnew_xsxlpc.do?method=delJg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
var DCCLBH = "xg_xlzxnew_xsxlpc.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH,jgExportData);
}

//导出方法
function jgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "xlzxnew_xsxlpc.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 关注，取消关注
 * @return
 */
function sz(zt){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要关注或取消关注的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("xlzxnew_xsxlpc.do?method=sz",{ids:ids.toString(),sfgz:zt},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * 导入
 * @return
 */
function dr(){
	// 调用通用的导入function，参数是导入功能模块代码。
	toImportDataNew("IMPORT_XLZX_XSPCJG");
	return false;
}


