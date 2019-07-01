function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='ByhkgljgView(\""
			+ rowObject["jgid"] + "\");'>" + cellValue + "</a>";
}

function ByhkgljgView(jgid) {
	showDialog("结果查看", 800, 508, "byhkgl_jg.do?method=viewByhkgljg&jgid="
			+ jgid);
}

function saveByhkgljg(type) {
	var flg = true;
	var ids = null;
	var sfzq = jQuery("#sfzq").val();
	
	if("是" == sfzq){
		ids = 'hkje-sfzq-zqyy-zqqx';
	}else{
		ids = "hkje-sfzq";
	}
	if(check(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	var url = "byhkgl_jg.do?method=saveByhkgljg&type="+type;
	ajaxSubFormWithFun("byhkglJgForm", url, function(data) {
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
	var url = "byhkgl_jg.do?method=addByhkgljg";
	var title = "结果增加";
	showDialog(title, 800, 508, url);
}
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else if(rows[0]['sjly']=='1'){
			showAlertDivLayer("审核流程过来的记录不能修改！");
	}else {
		var url = 'byhkgl_jg.do?method=editByhkgljg&jgid=' + rows[0]["jgid"];
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
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("审核流程过来的记录不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("是否确定删除勾选的记录？", {
		"okFun" : function() {
		var url = "byhkgl_jg.do?method=delByhkgljg";
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
	toImportDataNew("IMPORT_BYHKJG");
	return false;

}

var DCCLBH = "zxdk_byhkgl_byhkjg.do";// dcclbh,导出功能编号

// 自定义导出 功能
function exportConfig() {
	// DCCLBH导出功能编号,执行导出函数
	customExport(DCCLBH, exportData);
}

// 导出方法
function exportData() {
	setSearchTj();// 设置高级查询条件
	var url = "byhkgl_jg.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,导出功能编号
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

//金额校验不能以0开头
function ismoney(obj){
  if(obj.value.indexOf('0') == 0){
		 showAlert("金额不能以0开头！",{},{"clkFun":function(){
				jQuery(obj).val("");
				jQuery(obj).focus();
			}});
  }
}

//月份校验不能以0开头
function isyf(obj){
  if(obj.value.indexOf('0') == 0){
		 showAlert("月份不能以0开头！",{},{"clkFun":function(){
				jQuery(obj).val("");
				jQuery(obj).focus();
			}});
  }
}