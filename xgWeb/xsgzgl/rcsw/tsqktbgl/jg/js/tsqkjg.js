function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='TsqkjgView(\""
			+ rowObject["jgid"] + "\");'>" + cellValue + "</a>";
}

function TsqkjgView(jgid) {
	showDialog("特殊情况通报结果查看", 800, 508, "tsqktbgl_jg.do?method=viewTsqkjg&jgid="
			+ jgid);
}

function saveTsqkjg(type) {
	var flg = true;
	var ids = null;
	if(type=='save'||type=='submit'){
		ids = 'xh-xqdm1-tbsj-tsxq-tsxqgyqk-clcj';
	}else {
		ids = 'xqdm1-tbsj-tsxq-tsxqgyqk-clcj';
	}
	if(jQuery("#xxdm").val() == "10026") 
		ids += "-wtjjcd";
	if(check(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	var xqdm1 = jQuery("#xqdm1").val();
	var xqdm2 = jQuery("#xqdm2").val();
	if(xqdm1 == xqdm2){
		showAlert("学情分类一与学情分类二重复，请重新选择！");
		return false;
	}
	var url = "tsqktbgl_jg.do?method=saveTsqkjg&type="+type;
	ajaxSubFormWithFun("tsqkjgForm", url, function(data) {
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
	var url = "tsqktbgl_jg.do?method=addTsqkjg";
	var title = "特殊情况通报结果增加";
	showDialog(title, 800, 508, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else if(rows[0]['sjly']=='1'){
			showAlertDivLayer("审核流程过来的记录不能修改！");
	}else {
		var url = 'tsqktbgl_jg.do?method=editTsqkjg&jgid=' + rows[0]["jgid"];
		var title = "特殊情况通报结果修改";
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
		var url = "tsqktbgl_jg.do?method=delTsqkjg";
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


var DCCLBH = "rcsw_tsqktbgl_jg.do";// dcclbh,导出功能编号

// 自定义导出 功能
function exportConfig() {
	// DCCLBH导出功能编号,执行导出函数
	customExport(DCCLBH, tsqkjgExportData);
}

// 导出方法
function tsqkjgExportData() {
	setSearchTj();// 设置高级查询条件
	var url = "tsqktbgl_jg.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,导出功能编号
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