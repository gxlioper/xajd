var DCCLBH = "cjWsf_wsfcx.do";//dcclbh,导出功能编号

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function ck() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要查看的记录！");
	}else {
		var url = 'cjWsfcx.do?method=viewWsfcx&wsfid=' + rows[0]["wsfid"];
		showDialog("查看", 800, 460, url);
	}
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else {
		var url = 'cjWsfcx.do?method=editWsfcx&wsfid=' + rows[0]["wsfid"];
		var title = "修改";
		showDialog(title, 800, 460, url);
	}

}

// 删除
function cancel() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要撤销的记录！");
	} else {
		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("cjWsfcx.do?method=cxWsfcx", {
					ids : ids
				},
						function(data) {
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
			}
		});
	}
}

function savejg(type){
	var ids = null;
	if(type=='save'){
		ids = "fz";
		if(check(ids) == false){
			showAlert("请将带<font color='red'>*</font>的项目填写完整");
			return false;
		}
	}
	var fz = jQuery("#fz").val();
	var reg = /^[0-9]+(.[0-9]{1})?$/;
	var result = reg.test(fz);
	if(!result){
		showAlert("分值只允许输入阿拉伯数字，且只能输入一位小数");		
		return false;
	}
	if(parseInt(fz) > 100){
		showAlert("分值最大为100，请确认！");		
		return false;
	}
	var url = "cjWsfcx.do?method=editWsfcx&type=" +type;
	ajaxSubFormWithFun("WsfcxForm", url, function(data) {
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

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, wsfcxExportData);
}

//导出方法
function wsfcxExportData() {
	setSearchTj();//设置高级查询条件
	var url = "cjWsfcx.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
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

//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	alert(ids);
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("cjWsfcx.do?method=zjcmwsfcxDelete", {
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

