
/**
 * 高级查询
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 新增场地结果
 * @return
 */
function addCdjg(){
	showDialog('新增场地使用结果',780,485,'rcsw_cdgl_cdjg.do?method=cdjgSq');
}

/**
 * 新增场地结果保存操作
 * @return
 */
function addCdjgAction(){
	var xxdm = jQuery("#xxdm").val();
	var xh = jQuery('#xh').val();
	var cdid = jQuery('#cdid').val();
	
	if (xh==""){
		showAlert("请选择一个学生！");
		return false;
	}
	if (cdid==""){
		showAlert("请选择一个场地！");
		return false;
	}
	
	var checkids = "sqly-sqsjdkssj-sqsjdjssj";
	
	if(!checkNotNull(checkids)){
		showAlert("请将带<font color='red'>*</font>的项目填写完整!");
		return false;
	}
	
	if(jQuery('#sqly').val().length > 500){
		showAlert("申请理由最大字数不超过"+500+",请确认！");
		return false;
	}
	if(xxdm == "10351"){
		if(jQuery('#sqly').val().length < 50){
			showAlert("申请理由最小字数"+50+",请确认！");
			return false;
		}
	}
	var url = "rcsw_cdgl_cdjg.do?method=addCdjgAction";
		ajaxSubFormWithFun("rcswCdjgForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if(data['flag'] == 'checkerror'){
					return false;
				}else{
					if (parent.window){
						refershParent();
					}
				}
			}});
		});
}


/**
 * 删除场地结果
 * @return
 */
function deleteCdjg(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0){
		showAlertDivLayer("请选择一条您要删除的记录！");
		return false;
	} else{
		var cdjgids = [];
		for(i = 0 ; i < rows.length ; i ++){
			var jgid = rows[i]['jgid'];
			var sjly = rows[i]['sjly'];
			if(sjly == '1'){
				showAlertDivLayer("流程数据不能删除！");
				return false;
			}else{
				cdjgids.push(jgid);
			}
				
		}
		
		showConfirmDivLayer("您确定要删除所选记录？",{"okFun":function(){
			jQuery.post("rcsw_cdgl_cdjg.do?method=deleteCdjgAction",{cdjgids:cdjgids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


/**
 * 修改场地结果
 * @return
 */
function updateCdjg(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} else{
		if(rows[0]['sjly'] == '1'){
			showAlertDivLayer("流程数据不能修改！");
			return false;
		}
		showDialog('场地结果修改',780,485,'rcsw_cdgl_cdjg.do?method=cdjgXg&jgid=' + rows[0]['jgid']);
	}
}

/**
 * 更新场地结果保存操作
 * @return
 */
function updateCdjgAction(){
	var xxdm = jQuery("#xxdm").val();
	var checkids = "sqly-sqsjdkssj-sqsjdjssj";
	
	if(!checkNotNull(checkids)){
		showAlert("请将带<font color='red'>*</font>的项目填写完整!");
		return false;
	}
	
	if(jQuery('#sqly').val().length > 500){
		showAlert("申请理由最大字数不超过"+500+",请确认！");
		return false;
	}
	if(xxdm == "10351"){
		if(jQuery('#sqly').val().length < 50){
			showAlert("申请理由最小字数"+50+",请确认！");
			return false;
		}
	}
	var url = "rcsw_cdgl_cdjg.do?method=cdjgXgAction";
		ajaxSubFormWithFun("rcswCdjgForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
}


var DCCLBH = "rcsw_cdgl_cdjg.do";//dcclbh,导出功能编号


//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, exportData);
}

// 导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "rcsw_cdgl_cdjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}