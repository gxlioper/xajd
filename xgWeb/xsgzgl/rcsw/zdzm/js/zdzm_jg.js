
/**
 * 高级查询
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 新增在读证明
 * @return
 */
function addZdzmJg(){
	showDialog('新增在读证明结果',780,350,'rcsw_zdzm_jggl.do?method=addZdzmJg');
}
/**
 * 新增在读证明保存操作
 * @return
 */
function addZdzmJgAction(){
	var xh = jQuery('#xh').val();
	var sqly = jQuery('#sqly').val();
	
	if (xh=="" || sqly == ""){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
	
	if(sqly.length > 500){
		showAlertDivLayer("申请理由最大字数不超过"+500+",请确认！");
		return false;
	}
	
	var url = "rcsw_zdzm_jggl.do?method=addZdzmJgAction";
		ajaxSubFormWithFun("rcswZdzmJgForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
}


/**
 * 修改在读证明
 * @return
 */
function updateZdzmJg(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} else{
		if(rows[0]['sjly'] == '1'){
			showAlertDivLayer("该申请来自审批流数据，不能修改！");
			return false;
		}
		showDialog('在读证明结果修改',780,350,'rcsw_zdzm_jggl.do?method=updateZdzmJg&zdzmjgid=' + rows[0]['zdzmjgid']);
	}
}

/**
 * 更新在读证明保存操作
 * @return
 */
function updateZdzmJgAction(){
	var sqly = jQuery('#sqly').val();
	
	if (sqly == ""){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
	if(sqly.length > 500){
		showAlertDivLayer("申请理由最大字数不超过"+500+",请确认！");
		return false;
	}
	var url = "rcsw_zdzm_jggl.do?method=updateZdzmJgAction";
		ajaxSubFormWithFun("rcswZdzmJgForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
}

/**
 * 删除在读证明申请记录
 * @return
 */
function deleteZdzmJg(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0){
		showAlertDivLayer("请选择一条您要删除的记录！");
		return false;
	} else{
		var zdzmjgids = [];
		for(i = 0 ; i < rows.length ; i ++){
			var zdzmjgid = rows[i]['zdzmjgid'];
			var sjly = rows[i]['sjly'];
			if(sjly == '0'){
				zdzmjgids.push(zdzmjgid);
			}
		}
		
		showConfirmDivLayer("您确定要删除所选记录？",{"okFun":function(){
			jQuery.post("rcsw_zdzm_jggl.do?method=deleteZdzmJgAction",{sqids:zdzmjgids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "rcsw_zdzm_jggl.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, exportData);
}

// 导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "rcsw_zdzm_jggl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}