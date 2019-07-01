
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='lkxxView(\""
			+ rowObject["id"]+"\");'>" + cellValue
			+ "</a>";
}

function lkxxView(id) {
	showDialog("临客住宿信息查看", 800, 450, "xgygl_lkxxdj.do?method=viewLkxx&id="+id);
}


function save(type) {
	var ids = null;
	if(type=='save'){
		ids = "xm-sfzh-rzld-rzfj-rzsj"
	}else{
		ids = "id-xm-sfzh-rzld-rzfj-rzsj"
	}
	if(check(ids) == false && flg == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	var url = null;
	if(type=='save'){
		url = "xgygl_lkxxdj.do?method=addLkxx&type=" + type
	}else{
		url = "xgygl_lkxxdj.do?method=editLkxx&type=" + type;
	}	
	ajaxSubFormWithFun("lkxxForm", url, function(data) {
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

function add() {
	var url = "xgygl_lkxxdj.do?method=addLkxx";
	var title = "登记";
	showDialog(title, 800, 450, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else {
		var url = 'xgygl_lkxxdj.do?method=editLkxx&id=' + rows[0]["id"];
		var title = "登记修改";
		showDialog(title, 800, 450, url);
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
				jQuery.post("xgygl_lkxxdj.do?method=delLkxx", {
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

var DCCLBH = "xgygl_lkxxgl_zsdj.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, lkxxExportData);
}

//导出方法
function lkxxExportData() {
	setSearchTj();//设置高级查询条件
	var url = "xgygl_lkxxdj.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
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

function dr(){
	toImportDataNew("IMPORT_LKXX");
	return false;
}