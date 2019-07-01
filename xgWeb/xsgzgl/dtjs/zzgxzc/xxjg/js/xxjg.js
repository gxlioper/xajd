/**
 * 党团建设-组织关系转出，信息结果页面js
 */

var action="dtjs_xxjg.do";

//搜索
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 增加
 */
function xxjgAdd(){
	var url =action+"?method=xxjgAdd";
	var title = "增加转出结果信息";
	showDialog(title, 800, 500, url);
}

/**
 * 删除
 */
function xxjgDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("所选的记录中包含流程数据，不能删除，请重新选择！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post(action+"?method=xxjgDelete", {
					values : ids.toString()
				}, function(data) {
					//成功删除｛｝条记录 alertInfo()
					//showAlertDivLayer(mes);
					alertInfo(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}

/**
 * 修改
 */
function xxjgUpdate(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if(rows[0]["sjly"]=='1'){
			showAlertDivLayer("您选择的记录为流程数据，不能修改！");
			return false;
		}
		
		var xh=rows[0]["xh"];
		var jgid=rows[0]["jgid"];
		var url =action+"?method=xxjgUpdate&xh="+xh+"&jgid="+jgid;
		var title = "修改转出结果信息";
		showDialog(title, 800, 500, url);
	}
}

/**
 * 详情
 */
function xxjgShow(id){
	var url = action+"?method=xxjgShow&jgid="+id;
	var title = "转出结果信息";
	showDialog(title, 800, 500, url);
}

/**
 * 增加保存
 */
function saveForAdd(Ids){
	var url = "dtjs_xxjg.do?method=xxjgAdd&type=save";
	//验证必填项
	if(!checkNotNull(Ids)){
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	}
	//验证特殊格式项:是否开具婚姻证明
	if(jQuery("[name='sfkjhyzm']:checked").length == 0){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	
	ajaxSubFormWithFun("form", url, function(data) {
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

/**
 * 修改保存
 */
function saveForUpdate(Ids){
	var url = "dtjs_xxjg.do?method=xxjgUpdate&type=update";
	//验证必填项
	if(!checkNotNull(Ids)){
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	}
	//验证特殊格式项:是否开具婚姻证明
	if(jQuery("[name='sfkjhyzm']:checked").length == 0){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	
	//验证改派日志是否超过长度
	if(jQuery("#gpyy").val().length>500){
		showAlert("改派原因超过500字，请删减！");
		return false;
	}
	
	ajaxSubFormWithFun("form", url, function(data) {
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

/**
 * 导出
 */
var DCGLBH = "dtjs_xxjg.do";//dcglbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCGLBH, xshdglExportData);
}

//导出方法
function xshdglExportData() {
	setSearchTj();//设置高级查询条件
	var url = "dtjs_xxjg.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
