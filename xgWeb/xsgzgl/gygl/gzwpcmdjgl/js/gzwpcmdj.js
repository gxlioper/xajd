/**
 * 新增更新
 * 
 * @return
 */
function addUpdateGzwp(action) {
	if("add" === action)
		showDialog('增加贵重物品出门登记', 780, 410, 'gygl_gzwpcmdj.do?method=gzwpcmdjAdd');
	else if("update" === action){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1){
			showAlertDivLayer("请选择一条您要修改的记录！");
			return false;
		}
		showDialog('修改', 780, 405, 'gygl_gzwpcmdj.do?method=gzwpcmdjUpdate&gzwpdjid=' + rows[0]['gzwpdjid']);
	}
}


/**
 * 新增
 * 
 * @return
 */
function addGzwpcmdjxx() {
	var bz = jQuery('#bz').val();
	var xh = jQuery('#xh').val();
	var zbry = jQuery('#zbry').val();
	var cmsj = jQuery('#cmsj').val();
	var wpmc = jQuery('#wpmc').val();
	
	if ((zbry==null || jQuery.trim(zbry)== '') || (xh==null || jQuery.trim(xh)== '') || (cmsj==null || jQuery.trim(cmsj)== '') || (wpmc==null || jQuery.trim(wpmc)== '')) {
		showAlertDivLayer("请将带<font color=\"red\"> * </font>的项目填写完整！");
		return false;
	}

	if (bz.length > 500) {
		showAlertDivLayer("备注最大字数不超过" + 500 + ",请确认！");
		return false;
	}
	
	
	var url = "gygl_gzwpcmdj.do?method=gzwpcmdjAddAction";
	ajaxSubFormWithFun("gzwpcmdjForm", url, function(data) {
		showAlertDivLayer(data["message"], {}, { "clkFun" : function() {
			if (parent.window) {
				refershParent();
			}
		} });
	});
}

/**
 * 更新
 * 
 * @return
 */
function updateGzwpcmdjxx() {
	var bz = jQuery('#bz').val();
	var zbry = jQuery('#zbry').val();
	var cmsj = jQuery('#cmsj').val();
	var wpmc = jQuery('#wpmc').val();
	
	if (!checkNull("zbry-cmsj-wpmc")) {
		return false;
	}
	
	if (bz.length > 500) {
		showAlertDivLayer("备注最大字数不超过" + 500 + ",请确认！");
		return false;
	}
	
	
	var url = "gygl_gzwpcmdj.do?method=gzwpcmdjUpdateAction";
	ajaxSubFormWithFun("gzwpcmdjForm", url, function(data) {
		showAlertDivLayer(data["message"], {}, { "clkFun" : function() {
			if (parent.window) {
				refershParent();
			}
		} });
	});
}

/**
 * 删除
 * @return
 */
function deleteGzwp(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0){
		showAlertDivLayer("请选择一条您要删除的记录！");
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	showConfirmDivLayer("您确定要删除所选记录？",{"okFun":function(){
		jQuery.post("gygl_gzwpcmdj.do?method=gzwpcmdjDeleteAction",{pks:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
}

/**
 * 高级查询
 * 
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

var DCCLBH = "gygl_gzwpcmdjgl.do";// dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	// DCCLBH导出功能编号,执行导出函数
	customExport(DCCLBH, exportData);
}

//导出方法
function exportData() {
	setSearchTj();// 设置高级查询条件
	var url = "gygl_gzwpcmdj.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,导出功能编号
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 导入
 */
function importConfig() {
	toImportData("IMPORT_N382201");
	return false;
}

/**
 * 查看详细信息（单条）
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLink(cellValue, rowObject) {
	var onclickfn = "onclick=\""
		+ "showDialog('贵重物品出门登记信息查看' , 720 , 285 , 'gygl_gzwpcmdj.do?method=viewWpxx&gzwpdjid="
		+ rowObject['gzwpdjid'] + "')" + "\"";

	var href = "href = 'javascript:void(0);'";

	var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
			+ "</a>";

	return el;
}


