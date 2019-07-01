function checkNumBer(obj){
		obj.value=obj.value.replace(/[^\d]/g,'');
	}
//增加
function add() {
	showDialog('增加', 700, 450, 'ydXxgl.do?method=ydxxAdd');
}
//单个寝室信息查看
function qsLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onClick='viewYdxx(\""+rowObject["ydxxid"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}
function viewYdxx(ydxxid,cellValue){
	showDialog('寝室用电信息',700,450,'ydXxgl.do?method=ydxxView&ydxxid='+ydxxid+"&xh="+cellValue,null);
}

//增加--》保存
function save() {
	var ydyf = jQuery("#ydyf").val();
	var lddm = jQuery("#lddm").val();
	var qsh = jQuery("#qsh").val();
	var ds = jQuery("#ds").val();
	var df = jQuery("#df").val();
	var dfye = jQuery("#dfye").val();
	var bz = jQuery("#bz").val();
	if (ydyf == "" || lddm == "" || qsh == ""
			|| ds == "" || df == "" || dfye == "") {
		showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		return false;
	}
	jQuery.post("ydXxgl.do?method=ydxxAdd&type=save", {
		ydyf : ydyf,
		lddm : lddm,
		qsh:qsh,
		ds:ds,
		df:df,
		dfye:dfye,
		bz:encodeURI(encodeURI(bz))
	}, function(data) {
		if(data['message'] != ''){
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
		}
	}, 'json');
}


/*刪除*/
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		alertInfo("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("ydXxgl.do?method=ydxxDelete", {
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
	if (rows.length != 1&&rows.length==0) {
		alertInfo("请先选择一条您要修改的记录！");
	}else if(rows.length > 1){
		alertInfo("每次只能修改一条记录！");
	} else {
		showDialog('修改', 700, 450,
				'ydXxgl.do?method=ydxxUpdate&ydxxid=' + rows[0]["ydxxid"]);
	}
}
/*修改保存相关*/
function saveUpdate() {
	var ydxxid = jQuery("#ydxxid").val();
	var ydyf = jQuery("#ydyf").val();
	var lddm = jQuery("#lddm").val();
	var qsh = jQuery("#qsh").val();
	var ds = jQuery("#ds").val();
	var df = jQuery("#df").val();
	var dfye = jQuery("#dfye").val();
	var bz = jQuery("#bz").val();
	if (ydyf == "" || lddm == "" || qsh == ""
		|| ds == "" || df == "" || dfye == "") {
	showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	return false;
}
	var url = "ydXxgl.do?method=ydxxUpdatesv&type=save";
	jQuery.post(url, {
		ydxxid:ydxxid,
		ydyf : ydyf,
		lddm : lddm,
		qsh:qsh,
		ds:ds,
		df:df,
		dfye:dfye,
		bz:encodeURI(encodeURI(bz))
	}, function(data) {
		if(data['message'] == '保存成功！'){
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
		}
	}, 'json');
}

/* 导入 */
function importXX(){
	toImportDataNew("IMPORT_N382501_YDXX");
	return false;
}

/* 导出相关 */
function exportXX() {
	customExport('ydxxgl.do', exportData);
}
function exportData(){
	setSearchTj();// 设置高级查询条件
	var url = "ydXxgl.do?method=exportData&dcclbh=" + 'ydxxgl.do';// dcclbh,导出功能编号,数据表字段
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
