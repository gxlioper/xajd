
//增加
function add() {
	showDialog('填写基本分', 800, 450, 'xsxwkhJbfgl.do?method=jbfAdd');
}
//单个学生信息查看
function viewJbfgl(jbfid,cellValue){
	showDialog('学生基本分信息',800,420,'xsxwkhJbfgl.do?method=jbfView&jbfid='+jbfid+"&xh="+cellValue,null);
}
//联动
function change1() {
	var i = jQuery("#bzrcpdj").val();
	if (i == "优秀") {
		jQuery("#bzrcpfz").val("45");
	} else if (i == "良好") {
		jQuery("#bzrcpfz").val("43");
	} else if (i == "合格") {
		jQuery("#bzrcpfz").val("40");
	}
}
function change2() {
	var i = jQuery("#xscpdj").val();
	if (i == "优秀") {
		jQuery("#xscpfz").val("45");
	} else if (i == "良好") {
		jQuery("#xscpfz").val("43");
	} else if (i == "合格") {
		jQuery("#xscpfz").val("40");
	}
}
function change3() {
	var i = jQuery("#bzrcpdj").val();
	if (i == "优秀") {
		jQuery("#bzrcpfz1").val("45");
	} else if (i == "良好") {
		jQuery("#bzrcpfz1").val("43");
	} else if (i == "合格") {
		jQuery("#bzrcpfz1").val("40");
	}
}
function change4() {
	var i = jQuery("#xscpdj").val();
	if (i == "优秀") {
		jQuery("#xscpfz1").val("45");
	} else if (i == "良好") {
		jQuery("#xscpfz1").val("43");
	} else if (i == "合格") {
		jQuery("#xscpfz1").val("40");
	}
}

//增加--》保存
function save() {
	var xh = jQuery("#xh").val();
	var bzrcpdj = jQuery("#bzrcpdj").val();
	var xscpdj = jQuery("#xscpdj").val();

	if (xh == null || xh == "" || bzrcpdj == null || bzrcpdj == ""
			|| xscpdj == null || xscpdj == "") {
		showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		return false;
	}
	var url = "xsxwkhJbfgl.do?method=jbfAdd&type=save";
	ajaxSubFormWithFun("jbfglForm", url, function(data) {
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


/*刪除*/
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		alertInfo("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("xsxwkhJbfgl.do?method=JbfDelete", {
					values : ids.toString()
				}, function(data) {
					alertInfo(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}
/*修改触发*/
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		alertInfo("请先选择一条您要修改的记录！");
	} else {
		showDialog('修改基本分信息', 800, 420,
				'xsxwkhJbfgl.do?method=jbfUpdate&jbfid=' + rows[0]["jbfid"]);
	}
}
/*修改保存相关*/
function saveUpdate() {
	var bzrcpdj = jQuery("#bzrcpdj").val();
	var xscpdj = jQuery("#xscpdj").val();
	if (bzrcpdj == null || bzrcpdj == "" || xscpdj == null || xscpdj == "") {
		showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		return false;
	}
	var url = "xsxwkhJbfgl.do?method=jbfUpdate&type=save";
	ajaxSubFormWithFun("jbfglForm", url, function(data) {
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

/* 导入 */
function importXX(){
	toImportDataNew("IMPORT_N158703_JBFGL");
	return false;
}

/* 导出相关 */
function exportXX() {
	customExport('xsxwkh_jbfgl.do', exportData);
}
function exportData(){
	setSearchTj();// 设置高级查询条件
	var url = "xsxwkhJbfgl.do?method=exportData&dcclbh=" + 'xsxwkh_jbfgl.do';// dcclbh,导出功能编号,数据表字段
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
