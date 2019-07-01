function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='ZzdjgView(\""
			+ rowObject["jgid"] + "\");'>" + cellValue + "</a>";
}

function ZzdjgView(jgid) {
	showDialog("学生转走读结果查看", 800, 550, "xgygl_zdjg.do?method=viewZzdjg&jgid="
			+ jgid);
}

function saveZzdjg(type) {
	var flg = true;
	var ids = null;
	if(type=='save'||type=='submit'){
		ids = "xh-xn-xq-sqsj-sdyy"
	}else{
		ids = "xn-xq-sqsj-sdyy"
	}
	if(check(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	var url = "xgygl_zdjg.do?method=saveZzdjg&type="+type;
	ajaxSubFormWithFun("zzdjgForm", url, function(data) {
		if (data["message"] == "保存成功！") {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			showAlert(data["message"]);
		}
	});
	
}
function add() {
	var url = "xgygl_zdjg.do?method=addZzdjg";
	var title = "学生转走读结果增加";
	showDialog(title, 800, 550, url);
}
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else if(rows[0]['sjly']=='1'){
			showAlertDivLayer("审核流程过来的记录不能修改！");
	}else {
		var url = 'xgygl_zdjg.do?method=editZzdjg&jgid=' + rows[0]["jgid"];
		var title = "学生转走读结果修改";
		showDialog(title, 800, 550, url);
	}

}

// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("审核流程过来的记录不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("是否确定删除勾选的记录？", {
		"okFun" : function() {
		var url = "xgygl_zdjg.do?method=delZzdjg";
		jQuery.post(url, {
			values : ids.toString()
		}, function(data) {
			if (data["success"] == false) {
				showAlertDivLayer(data["message"]);
			} else {
				showAlertDivLayer(data["message"], {}, {
					"clkFun" : function(tag) {
						jQuery("#dataTable").reloadGrid();
					}
				});
			}
		}, 'json');
	
}});
}
}
//导入
function dr() {
	// 调用通用的导入function，参数是导入功能模块代码。
	toImportDataNew("IMPORT_ZZDJG");
	return false;

}

var DCCLBH = "xgygl_zzdgl_zdjg.do";// dcclbh,导出功能编号

// 自定义导出 功能
function exportConfig() {
	// DCCLBH导出功能编号,执行导出函数
	customExport(DCCLBH, zzdjgExportData);
}

// 导出方法
function zzdjgExportData() {
	setSearchTj();// 设置高级查询条件
	var url = "xgygl_zdjg.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,导出功能编号
	url = addSuperSearchParams(url);// 设置高级查询参数
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

function printzzdsqb(url){
	var zzdid="";
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <1) {
		showAlertDivLayer("请至少选择一条记录！");
	} else {
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				zzdid +=rows[i]["jgid"];
			}else{
				zzdid +=rows[i]["jgid"]+",";
			}
		}		
		var url = url + "&ylzd1=" +zzdid;
		window.open(url);
	}
}