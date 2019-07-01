var gnmkmc = "";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='YpzljgView(\""
			+ rowObject["jgid"] + "\");'>" + cellValue + "</a>";
}

function YpzljgView(jgid) {
	 gnmkmc = jQuery("#gnmkmc").val();
	showDialog(gnmkmc+"查看", 800, 508, "ypzl_jg.do?method=viewYpzljg&jgid="
			+ jgid);
}

function setColor(value, status) {
	var color;
	if (status == '1') {
		color = "#004400";
	} else {
		color = "red";
	}
	return value = "<font color='" + color + "'>" + value + "</font>";
}

function saveYpzljg(type) {
	var flg = true;
	var ids = null;
	var xxdm = jQuery("#xxdm").val();
	if(type=='save'||type=='submit'){
		if(xxdm == '10511'){
			ids = 'xh-sqje-sqly-ytlb';
		}else{
			ids = "xh-sqje-sqly";
		}
	}else{
		if(xxdm == '10511'){
			ids = 'sqje-sqly-ytlb';
		}else{
			ids = "sqje-sqly";
		}
	}
	if(check(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	if(xxdm == '10511'){
		if(checksqjesx() == false){
			return false;
		}
		
	}
	var url = "ypzl_jg.do?method=saveYpzljg&type="+type;
	ajaxSubFormWithFun("ypzljgForm", url, function(data) {
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
	 gnmkmc = jQuery("#gnmkmc").val();
	var url = "ypzl_jg.do?method=addYpzljg";
	var title = gnmkmc+"增加";
	showDialog(title, 800, 508, url);
}
function update() {
	 gnmkmc = jQuery("#gnmkmc").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else if(rows[0]['sjly']=='1'){
			showAlertDivLayer("审核流程过来的记录不能修改！");
	}else {
		var url = 'ypzl_jg.do?method=editYpzljg&jgid=' + rows[0]["jgid"];
		var title = gnmkmc+"修改";
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
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("审核流程过来的记录不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("是否确定删除勾选的记录？", {
		"okFun" : function() {
		var url = "ypzl_jg.do?method=delYpzljg";
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
	toImportDataNew("IMPORT_YPZLJG");
	return false;

}

var DCCLBH = "zxdk_ypzl_ypzljg.do";// dcclbh,导出功能编号

// 自定义导出 功能
function exportConfig() {
	// DCCLBH导出功能编号,执行导出函数
	customExport(DCCLBH, ypzljgExportData);
}

// 导出方法
function ypzljgExportData() {
	setSearchTj();// 设置高级查询条件
	var url = "ypzl_jg.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,导出功能编号
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

function printypzlsqb(url){
	var sqid="";
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <1) {
		showAlertDivLayer("请至少选择一条记录！");
	} else {
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				sqid +=rows[i]["jgid"];
			}else{
				sqid +=rows[i]["jgid"]+",";
			}
		}		
		var url = url + "&ylzd1=" +sqid;
		window.open(url);
	}
}

//华师大补助金额上限为1000
function checksqjesx(){
	sqje =parseInt(jQuery("#sqje").val());
	if(sqje == 0){
		showAlert("补助金额不能为0！");
		return false;
	}
	if(sqje > 1000){
		showAlert("补助金额上限为1000！");
		return false;
	}
}