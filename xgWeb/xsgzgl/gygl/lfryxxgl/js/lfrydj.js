/**
 * 新增更新
 * 
 * @return
 */
function addUpdateLfry(action) {
	if("add" === action)
		showDialog('增加来访登记', 780, 470, 'gygl_lfrydj.do?method=lfrydjAdd');
	else if("update" === action){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1){
			showAlertDivLayer("请选择一条您要修改的记录！");
			return false;
		}
		showDialog('修改', 780, 467, 'gygl_lfrydj.do?method=lfrydjUpdate&lfrdjid=' + rows[0]['lfrdjid']);
	}
}


/**
 * 新增
 * 
 * @return
 */
function addLfrydjxx() {
	var bz = jQuery('#bz').val();
	var lfrxm = jQuery('#lfrxm').val();
	var lfrxb = jQuery('#lfrxb').val();
	var lfsj = jQuery('#lfsj').val();
	var lfrsfzh = jQuery('#lfrsfzh').val();
	var xh = jQuery('#xh').val();
	var zbry = jQuery('#zbry').val();
	
	
	if ((zbry==null || jQuery.trim(zbry)== '') || (lfrsfzh==null || jQuery.trim(lfrsfzh)== '')  || (lfrxm==null || jQuery.trim(lfrxm)== '') || (lfrxb==null || jQuery.trim(lfrxb)== '') || (lfsj==null || jQuery.trim(lfsj)== '')) {
		showAlertDivLayer("请将带<font color=\"red\"> * </font>的项目填写完整！");
		return false;
	}

	if (bz.length > 500) {
		showAlertDivLayer("备注最大字数不超过500,请确认！");
		return false;
	}
	
	
	var url = "gygl_lfrydj.do?method=lfrydjAddAction";
	ajaxSubFormWithFun("lfrydjForm", url, function(data) {
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
function updateLfrydjxx() {
	var bz = jQuery('#bz').val();
	var lfrxm = jQuery('#lfrxm').val();
	var lfrxb = jQuery('#lfrxb').val();
	var lfsj = jQuery('#lfsj').val();
	var lqsj = jQuery('#lqsj').val();
	var lfrsfzh = jQuery('#lfrsfzh').val();
	var xh = jQuery('#xh').val();
	var zbry = jQuery('#zbry').val();
	
	
	if (!checkNull("zbry-lfrsfzh-lfrxm-lfrxb-lfsj")) {
		return false;
	}

	if (bz.length > 500) {
		showAlertDivLayer("备注最大字数不超过500,请确认！");
		return false;
	}
	
	
	var url = "gygl_lfrydj.do?method=lfrydjUpdateAction";
	ajaxSubFormWithFun("lfrydjForm", url, function(data) {
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
function deleteLfry(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0){
		showAlertDivLayer("请选择一条您要删除的记录！");
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	showConfirmDivLayer("您确定要删除所选记录？",{"okFun":function(){
		jQuery.post("gygl_lfrydj.do?method=lfrydjDeleteAction",{pks:ids.toString()},function(data){
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

var DCCLBH = "gygl_lfryxxgl.do";// dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	// DCCLBH导出功能编号,执行导出函数
	customExport(DCCLBH, exportData);
}

//导出方法
function exportData() {
	setSearchTj();// 设置高级查询条件
	var url = "gygl_lfrydj.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,导出功能编号
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 导入
 */
function importConfig() {
	//IMPORT_N382101，旧的导出
	toImportDataNew("IMPORT_LFRYDJ");
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
		+ "showDialog('来访信息查看' , 720 , 400 , 'gygl_lfrydj.do?method=viewLfxx&lfrdjid="
		+ rowObject['lfrdjid'] + "')" + "\"";

	var href = "href = 'javascript:void(0);'";

	var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
			+ "</a>";

	return el;
}

/**
 * 选择学生
 */
function chooseStudent(){
	var elementIds=['xh','xm','xymc','zymc','bjmc','ldmc','qsh'];
	var lddm = jQuery("#lddm").val();
	showDialog('请选择一个学生',680,480,'gygl_lfrydj.do?method=showStudents&elementIds='
	+elementIds+'&lddm='+lddm);
}

/**
 * 改变楼栋时，清空被访人信息
 */
function emptyBfrxx(){
	var elementIds=['xh','xm','xymc','zymc','bjmc','ldmc','qsh'];
	jQuery("#"+elementIds[0]).val("");
	for(var i=1;i<elementIds.length;i++){
		jQuery("#"+elementIds[i]).empty();
	}
}

