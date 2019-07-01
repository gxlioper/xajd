
/**
 * 高级查询
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 新增
 * @return
 */
function addSq(){
	showDialog('新增班级行为记录',700,550,'szdw_bjxwjlwh.do?method=sq');
}

/**
 * 查看
 * @return
 */
function ckSq(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要查看的记录！");
		return false;
	}else{
		showDialog('查看班级行为记录',700,350,'szdw_bjxwjlwh.do?method=ck&guid=' + jQuery("#dataTable").getSeletIds()[0]);
	}
}


/**
 * 删除记录
 * @return
 */
function deleteSq(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0){
		showAlertDivLayer("请选择一条您要删除的记录！");
		return false;
	} else{
		showConfirmDivLayer("您确定要删除所选记录？",{"okFun":function(){
			jQuery.post("szdw_bjxwjlwh.do?method=deleteAction",{guids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * 修改
 * @return
 */
function updateSq(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} else{
		showDialog('班级行为记录修改',700,450,'szdw_bjxwjlwh.do?method=updateSq&guid=' + rows[0]['guid']);
	}
}

var DCCLBH = "szdw_bjxwjlwh.do";//dcclbh,导出功能编号


//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, exportData);
}

// 导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "szdw_bjxwjlwh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}