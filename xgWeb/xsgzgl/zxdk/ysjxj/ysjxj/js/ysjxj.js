var DCCLBH = "zxdk_ysjxj_ysjxjwh.do";//dcclbh,导出功能编号

//高级查询
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

// 学号链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewHkxx(\""
			+ rowObject["juid"]+"\");'>" + cellValue
			+ "</a>";
}

//查看
function viewHkxx(juid) {
	showDialog("查看", 800, 500, "ysjxj_ysjxjwh.do?method=viewYsjxj&juid="+juid);
}

// 增加
function add() {
	var url = "ysjxj_ysjxjwh.do?method=addYsjxj";
	var title = "增加";
	showDialog(title, 800, 508, url);
}

// 修改
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else {
		var url = 'ysjxj_ysjxjwh.do?method=editYsjxj&juid=' + rows[0]["juid"];
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
				jQuery.post("ysjxj_ysjxjwh.do?method=delYsjxj", {
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
function saveYsjxj(type){
	var xh = jQuery("#xh").val();
	var xmmc = jQuery("#xmmc").val();
	var je = jQuery("#je").val();
	var ffsj = jQuery("#ffsj").val();
	var zjly = jQuery("#zjly").val();
	var xmlx = jQuery("#xmlx").val();
	if (!checkNull("xh-xmmc-je-ffsj-zjly-xmlx")) {
		return false;
	}
	if(type=='save'){
		if((xh==null || jQuery.trim(xh)== '') ||(xmmc==null || jQuery.trim(xmmc)== '') ||(je==null || jQuery.trim(je)== '')|| (ffsj==null || jQuery.trim(ffsj)== '') || (zjly==null || jQuery.trim(zjly)== '') || (xmlx==null || jQuery.trim(xmlx)== '')) {
			showAlert("请将带<font color='red'>*</font>的项目填写完整");
			return false;		
		}
	}else{
		if((xh==null || jQuery.trim(xh)== '') ||(xmmc==null || jQuery.trim(xmmc)== '') ||(je==null || jQuery.trim(je)== '')|| (ffsj==null || jQuery.trim(ffsj)== '') || (zjly==null || jQuery.trim(zjly)== '')) {
			showAlert("请将带<font color='red'>*</font>的项目填写完整");
			return false;		
		}
	}
	var url = "ysjxj_ysjxjwh.do?method=saveYsjxj&type=" + type;
	ajaxSubFormWithFun("ysjxjForm", url, function(data) {
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
	var url = "ysjxj_ysjxjwh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

// 导入
function dr(){
	toImportDataNew("IMPORT_YSJXJJGWH");
	return false;
}