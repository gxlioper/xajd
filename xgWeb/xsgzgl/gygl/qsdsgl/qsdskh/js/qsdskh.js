jQuery(function() {
	var gridSetting = {
			caption : "寝室导师考核列表",
			pager : "pager",
			url : "gygl_qsdskh.do?method=qsdskhManage&type=query",
			colList : [
					{ label : 'key', name : 'pk', index : 'pk', key : true,
						hidden : true },
					{ label : '学年', name : 'xn', index : 'xn', width : '10%' },
					{ label : '学期', name : 'xq', index : 'xq', width : '12%', hidden : true },
					{ label : '学期', name : 'xqmc', index : 'xqmc', width : '6%' },
					{ label : '年度', name : 'nd', index : 'nd', width : '6%' },
					{ label : '职工号', name : 'zgh', index : 'zgh', width : '15%' },
					{ label : '导师姓名', name : 'dsxm', index : 'dsxm', width : '12%' },
					{ label : '考核成绩', name : 'cj', index : 'cj', width : '10%' },
					{ label : '单位', name : 'dw', index : 'dw', width : '24%' },
					{ label : '联系电话', name : 'lxdh', index : 'lxdh', width : '15%' }],
			sortname : "zgh", sortorder : "asc" };
	gridSetting["params"] = getSuperSearch();
	jQuery("#dataTable").initGrid(gridSetting);

});

// 选择教师后，回调函数
function showFdysNotF5CallBack(rowData) {
	jQuery("#zgh").val(rowData["zgh"]);
	jQuery("#dsxm").html(rowData["xm"]);
	jQuery("#dslxdh").html(rowData["lxdh"]);
	jQuery("#dsbmmc").html(rowData["bmmc"]);
}

/**
 * 新增更新
 * 
 * @return
 */
function addUpdateQsds(action) {
	if("add" === action)
		showDialog('新增寝室导师考核', 680, 200, 'gygl_qsdskh.do?method=qsdskhAdd');
	else if("update" === action){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1){
			showAlertDivLayer("请选择一条您要修改的记录！");
			return false;
		}
		showDialog('更新寝室导师考核', 680, 200, 'gygl_qsdskh.do?method=qsdskhUpdate&zgh=' + rows[0]['zgh'] + '&xn=' + rows[0]['xn'] + '&xq=' + rows[0]['xq']);
	}
}


/**
 * 新增
 * 
 * @return
 */
function addQsdsxx() {
	var nd = jQuery('#nd').val();
	var xn = jQuery('#xn').val();
	var xq = jQuery('#xq').val();
	var zgh = jQuery('#zgh').val();
	var cj = jQuery('#cj').val();
	
	if ((nd == null || jQuery.trim(nd) == '') || (xn == null || jQuery.trim(xn) == '') || (xq == null || jQuery.trim(xq) == '') || (zgh==null || jQuery.trim(zgh) == '') || (cj==null || jQuery.trim(cj) == '')) {
		showAlertx("带<font color=\"red\">*</font>的项目必填");
		return false;
	}
	
	jQuery.post('gygl_qsdskh.do?method=qsdskhAddCheckAction',{xn:xn,xq:xq,zgh:zgh},function(data){
		if(data.message != ""){
			showAlertx(data.message);
			return false;
		}
		var url = "gygl_qsdskh.do?method=qsdskhAddAction";
		ajaxSubFormWithFun("sqdswhForm", url, function(data) {
			showAlertx(data["message"], {}, { "clkFun" : function() {
				if (parent.window) {
					refershParent();
				}
			} });
		});
	},'json');
}

/**
 * 更新
 * 
 * @return
 */
function updateQsdsxx() {
	var cj = jQuery('#cj').val();
	
	if ((cj==null || jQuery.trim(cj) == '')) {
		showAlertx("带<font color=\"red\">*</font>的项目必填");
		return false;
	}
	
	var url = "gygl_qsdskh.do?method=qsdskhUpdateAction";
	ajaxSubFormWithFun("sqdswhForm", url, function(data) {
		showAlertx(data["message"], {}, { "clkFun" : function() {
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
function deleteQsds(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0){
		showAlertDivLayer("请选择一条您要删除的记录！");
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	showConfirmDivLayer("您确定要删除所选记录？",{"okFun":function(){
		jQuery.post("gygl_qsdskh.do?method=qsdskhDeleteAction",{pks:ids.toString()},function(data){
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

var DCCLBH = "gygl_qsdskhgl.do";// dcclbh,导出功能编号

// 自定义导出 功能
function exportConfig() {
	// DCCLBH导出功能编号,执行导出函数
	customExport(DCCLBH, exportData);
}

// 导出方法
function exportData() {
	setSearchTj();// 设置高级查询条件
	var url = "gygl_qsdskh.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,导出功能编号
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 导入
 */
function importConfig() {
	toImportData("IMPORT_N381902");
	return false;
}
