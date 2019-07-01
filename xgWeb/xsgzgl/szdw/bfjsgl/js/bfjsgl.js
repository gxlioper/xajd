var DCCLBH = "szdw_bfjs_bfjsglwh.do";// dcclbh,导出功能编号

// 自定义导出 功能
function exportConfig() {
	// DCCLBH导出功能编号,执行导出函数
	customExport(DCCLBH, bfjsExportData);
}

//导出方法
function bfjsExportData() {
	setSearchTj();// 设置高级查询条件
	var url = "bfjsgl_bfjsglwh.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,导出功能编号
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//验证考勤类型是否必填
function checkSelects(){
	var selects = jQuery("select[name='qqlxdm']");
	var flg = true;
	var nums = 0;
	jQuery.each(selects,function(i,n){
		var options = jQuery(n).find("option");
		nums = 0;
		for(j=0;j<options.length;j++){
			if(jQuery(options[j]).prop("selected") == true){
				nums++;
				if(jQuery(options[j]).val() == '' || jQuery(options[j]).val() == null){
					flg = false;
					break;
				}
			}
			if(nums>0){
				break;
			}
		}
		if(nums < 1){
			flg = false;
		}
		if(!flg){
			return false;
		}
	});
	if(!flg){
		return false;
	}else{
		return true;
	}		
}

//增加保存
function addSave() {
	var rq = jQuery("#jcrq").val();
	if(rq == null || rq == ''){
		showAlert("请选择检查日期");
		return false;
	}
	var bj = jQuery("#bjdm").val();
	if(bj == null || bj == ''){
		showAlert("请选择班级");
		return false;
	}
	
	if(!checkSelects()){
		showAlert("请填写考勤类型");
		return false;
	}
	
	var url = "bfjsgl_bfjsglwh.do?method=addSave";
	ajaxSubFormWithFun("bfjsglForm", url, function(data) {
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

function updateSave() {
	if(!checkSelects()){
		showAlert("请填写考勤类型");
		return false;
	}
	var url = "bfjsgl_bfjsglwh.do?method=updateSave";
	ajaxSubFormWithFun("bfjsglForm", url, function(data) {
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

function showView() {
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlert("请先选择一条记录");
		return false;
	}else{
		var url = 'bfjsgl_bfjsglwh.do?method=viewBfjs&jcid=' + rows[0]["jcid"];
		var title = "班风考勤查看";
		showDialog(title, 800, 550, url);
	}
	
}

function showBj(){
	var sj = jQuery("#jcrq").val();
	if(sj == null || sj == ''){
		showAlert("请先选择检查日期");
		return false;
	}else{
		showDialog('请选择一个班级',800,500,'bfjsgl_bfjsglwh.do?method=bjManage&jcrq='+sj);
	}
}

function add() {
	var url = "bfjsgl_bfjsglwh.do?method=addBfjs";
	var title = "班风考勤新增";
	showDialog(title, 800, 550, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else {
		var url = 'bfjsgl_bfjsglwh.do?method=updateBfjs&jcid=' + rows[0]["jcid"];
		var title = "班风考勤修改";
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
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("bfjsgl_bfjsglwh.do?method=delBfjs", {
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

function showxmxx(obj,jclx){
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";

	jQuery(obj).attr("class",newClass);
	jQuery("#"+jclx+"xx").toggle();
}
