/**
 * 资助款发放列表页面js
 */

var action="xszz_zzkff.do";

//搜索
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 增加
 */
function zzkffAdd(){
	var url =action+"?method=zzkffZj";
	var title = "增加资助款发放信息";
	showDialog(title, 800, 500, url);
	jQuery("#dataTable").reloadGrid();
}

/**
 * 删除
 */
function zzkffDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post(action+"?method=zzkffSc", {
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
function zzkffUpdate(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var xh=rows[0]["xh"];
		var id=rows[0]["id"];
		var url =action+"?method=zzkffXg&xh="+xh+"&id="+id;
		var title = "修改资助款发放信息";
		showDialog(title, 800, 500, url);
	}
}

/**
 * 根据学号显示一条资助款发放信息的详情
 */
function zzkffShow(id){
	var url = action+"?method=zzkffXq&id="+id;
	var title = "资助款发放信息";
	showDialog(title, 800, 500, url);
}

/**
 * 增加资助款发放信息的保存
 */
function save(url,Ids){
	//验证必填项
	if(!checkNotNull(Ids)){
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	}
	//验证特殊格式项:金额为有效正数
	var je = jQuery("#je").val();
	if(isNaN(je)||je<0){
		jQuery("#je").focus();
		return showAlert("请注意金额必须为有效正数！");
	}
	
	lock();
	jQuery("#form").ajaxSubmit({
		url:url,
		type:"post",
		dataType:"json",
		success:function(data){
	 		 if(data["message"]=="保存成功！"){
	    		 showAlert(data["message"],{},{"clkFun":function(){
	    				if (parent.window){
	    					refershParent();
	    				}
	    			}});
	    	 }else{
	    		 showAlert(data["message"]);
	    	 }
		},
		contentType:"application/x-www-form-urlencoded;charset=utf-8"
	});	
	unlock();
}

/**
 * 导入导出
 */
var DCGLBH = "xszz_zzkff.do";//dcglbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCGLBH, xshdglExportData);
}

//导出方法
function xshdglExportData() {
	setSearchTj();//设置高级查询条件
	var url = "xszz_zzkff.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//导入
function importConfig(){
	toImportDataNew("IMPORT_ZZKFF");
	return false;
}