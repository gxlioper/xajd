jQuery(function() {
	var xxdm = jQuery("#xxdm").val();
	var colList = [
					{ label : 'key', name : 'pk', index : 'pk', key : true,
						hidden : true },
					{ label : '学年', name : 'xn', index : 'xn', width : '10%', hidden : true },
					{ label : '学期', name : 'xq', index : 'xq', width : '10%', hidden : true },
					{ label : '楼栋名称', name : 'ldmc', index : 'ldmc', width : '10%' },
					{ name : 'lddm', index : 'lddm', hidden : true },
					{ label : '寝室号', name : 'qsh', index : 'qsh', formatter : qshLink, width : '7%' },
					{ label : '寝室性别', name : 'xb', index : 'xb', width : '6%' },
					{ label : '所属年级', name : 'nj', index : 'nj', width : '7%' },
					{ label : '所属'+jQuery("#xbmc").val(), name : 'bmmc', index : 'bmmc', width : '13%' },
					{ label : '导师姓名', name : 'dsxm', index : 'dsxm', width : '8%' },
					{ label : '单位', name : 'dw', index : 'dw', width : '13%' },
					{ label : '联系电话', name : 'lxdh', index : 'lxdh', width : '8%' },
					{ label : '寝室长', name : 'qszxm', index : 'qszxm', width : '8%' },
					{ label : '寝室长联系方式', name : 'qszlxdh', index : 'qszlxdh', width : '8%' } ];
	if(xxdm == "10351"){
		colList.push({ label : '学区辅导员', name : 'xqfdyxm', index : 'xqfdyxm', width : '8%' });
		colList.push({ label : '辅导员联系电话', name : 'xqfdylxdh', index : 'xqfdylxdh', width : '8%' });
	}
	var gridSetting = {
			caption : "寝室导师维护列表",
			pager : "pager",
			url : "gygl_qsdswh.do?method=qsdswhManage&type=query",
			colList : colList,
			sortname : "ldmc", sortorder : "asc" };
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

function showFdysNotF5CallBackAnother(rowData) {
	jQuery("#xqfdyxm").val(rowData["xm"]);
	jQuery("#xqfdylxdh").val(rowData["lxdh"])
	jQuery("td[name=xqfdylxdh]").html(rowData["lxdh"]);
}


/**
 * 新增更新
 * 
 * @return
 */
function addUpdateQsds(action) {
	var height = 420;
	var xxdm = jQuery("#xxdm").val();
	if(xxdm == "10351"){
		height = 450;
	}
	if("add" === action){
		showDialog('新增寝室导师', 780, height, 'gygl_qsdswh.do?method=qsdswhAdd');
	}else if("update" === action){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1){
			showAlertDivLayer("请选择一条您要修改的记录！");
			return false;
		}
		showDialog('更新寝室导师', 780, height, 'gygl_qsdswh.do?method=qsdswhUpdate&lddm=' + rows[0]['lddm'] + '&qsh=' + rows[0]['qsh'] + '&xn=' + rows[0]['xn'] + '&xq=' + rows[0]['xq']);
	}
}


/**
 * 新增
 * 
 * @return
 */
function addQsdsxx() {
	var bz = jQuery('#bz').val();
	var lddm = jQuery('#lddm').val();
	var qsh = jQuery('#qsh').val();
	var zgh = jQuery('#zgh').val();
	
	
	if ((lddm==null || jQuery.trim(lddm) == '') || (qsh == null || jQuery.trim(qsh) == '') || (zgh==null || jQuery.trim(zgh) == '')) {
		showAlertDivLayer("请将带<font color=\"red\">*</font>的项目填写完整！");
		return false;
	}

	if (bz.length > 100) {
		showAlertDivLayer("备注最大字数不超过" + 100 + ",请确认！");
		return false;
	}

	var url = "gygl_qsdswh.do?method=qsdswhAddAction";
	jQuery.post("gygl_qsdswh.do?method=qsdsIsExist",{lddm:lddm,qsh:qsh},function(data){
		if(data != null && data["message"] != null){
			showConfirmDivLayer(data["message"],{"okFun":function(){
				ajaxSubFormWithFun("sqdswhForm", url, function(data) {
					showAlertDivLayer(data["message"], {}, { "clkFun" : function() {
						if (parent.window) {
							refershParent();
						}
					} });
				});
			}});
		} else {
			ajaxSubFormWithFun("sqdswhForm", url, function(data) {
				showAlertDivLayer(data["message"], {}, { "clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				} });
			});
		}
	},'json');
}
	
/**
 * 更新
 * 
 * @return
 */
function updateQsdsxx() {
	var bz = jQuery('#bz').val();
	var zgh = jQuery('#zgh').val();
	
	if (zgh==null || jQuery.trim(zgh)== '') {
		showAlertDivLayer("请将带<font color=\"red\">*</font>的项目填写完整！");
		return false;
	}

	if (bz.length > 100) {
		showAlertDivLayer("备注最大字数不超过" + 100 + ",请确认！");
		return false;
	}
	
	
	var url = "gygl_qsdswh.do?method=qsdswhUpdateAction";
	ajaxSubFormWithFun("sqdswhForm", url, function(data) {
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
function deleteQsds(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0){
		showAlertDivLayer("请选择一条您要删除的记录！");
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	showConfirmDivLayer("您确定要删除所选记录？",{"okFun":function(){
		jQuery.post("gygl_qsdswh.do?method=qsdswhDeleteAction",{pks:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
}


/************************************************************/

function loadLdxx() {
	if(jQuery('#lddm').val() == ''){
		/********/
		jQuery('#ldxb').text('');
		jQuery('#ldcs').text('');
		jQuery('#qsch').text('');
		jQuery('#sfhlc').text('');
		/********/
		jQuery('#ch').empty();
		jQuery('#ch').append("<option value=''>--请选择--</option>");
		
		jQuery('#qsh').empty();
		jQuery('#qsh').append("<option value=''>--请选择--</option>");
		
		return false;
	}
	
	jQuery.post('gyglnew_qsgl.do?method=loadLdxx', { lddm : jQuery('#lddm')
			.val() }, function(data) {
		var qsch = parseInt(data.qsch);
		var ldcs = parseInt(data.ldcs);
		var sfhlc = data.sfhlc;
		var ldxb = data.ldxb;
		var count = 0;
		
		/********/
		jQuery('#ldxb').text(ldxb);
		jQuery('#ldcs').text(ldcs);
		jQuery('#qsch').text(qsch);
		jQuery('#sfhlc').text(sfhlc);
		/********/
		jQuery('#ch').empty();
		jQuery('#ch').append("<option value=''>--请选择--</option>");
		while (count < ldcs) {
			var chdm;
			var chmc;
			if ('否' == sfhlc) {

				if ((qsch + count) >= 0) {
					chdm = qsch > 0 ? qsch + count : qsch + count + 1;
					chmc = chdm + "层";
				} else {
					chdm = qsch + count;
					chmc = "B" + Math.abs(chdm) + "层";
				}

			} else {
				chdm = qsch + count;
				chmc = chdm < 0 ? "B" + Math.abs(chdm) + "层" : chdm + "层";
			}

			var option = "<option value=\"" + chdm + "\">" + chmc + "</option>"
			jQuery('#ch').append(option);

			count++;
		}
		loadQs();
	}, 'json');
}
function loadQs() {
	jQuery.getJSON('gyglnew_cwgl.do?method=getQshForLddm', {
		lddm : jQuery('#lddm').val(), ch : jQuery('#ch').val() },
			function(data) {
				jQuery('#qsh').empty();
				jQuery('#qsh').append("<option value=''>--请选择--</option>");
				if (data != null && data.length != 0) {
					for ( var i = 0; i < data.length; i++) {
						var option = "<option value=\"" + data[i].qsh + "\">"
								+ data[i].qsh + "</option>";
						jQuery('#qsh').append(option);
					}
				}
			});
}

function loadQsdh() {
	jQuery.getJSON('gyglnew_cwgl.do?method=getQszInfoForLddm', {
		lddm : jQuery('#lddm').val(), qsh : jQuery('#qsh').val() },
			function(data) {
				jQuery('#qszxm').html('');
				jQuery('#qszlxdh').html('');
				if (data != null && data['qszxm'] != null) {
					jQuery('#qszxm').html(data['qszxm']);
					jQuery('#qszlxdh').html(data['qszlxdh']);
				}
			});
	//var obj = new Object;
	//obj.lddm = jQuery('#lddm').val();
	//obj.ch = jQuery('#ch').val();
	//obj.qsh = jQuery('#qsh').val();
	//jQuery.getJSON('gygl_qsdswh.do?method=loadQsdsxx', obj, function(data) {
	//	var dsxm = data.dsxm;
	//	var lxdh = data.lxdh;
	//	var dw = data.dw;
	//	var bz = data.bz;
	//	jQuery('#dsxm').val(dsxm);
	//	jQuery('#lxdh').val(lxdh);
	//	jQuery('#dw').val(dw);
	//	jQuery('#bz').val(bz);
	//});
}

/*************************************************************/

/**
 * 高级查询
 * 
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

var DCCLBH = "gygl_qsdswhgl.do";// dcclbh,导出功能编号

// 自定义导出 功能
function exportConfig() {
	// DCCLBH导出功能编号,执行导出函数
	customExport(DCCLBH, exportData);
}

// 导出方法
function exportData() {
	setSearchTj();// 设置高级查询条件
	var url = "gygl_qsdswh.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,导出功能编号
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 导入
 */
function importConfig() {
	toImportData("IMPORT_N381901");
	return false;
}

//导出详细
function exportXx() {
	setSearchTj();// 设置高级查询条件
	var url = "gygl_qsdswh.do?method=exportDskh";
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 学号链接
 * 
 * @param cellValue
 * @param rowObject
 * @return
 */

function xhLink(cellValue, rowObject) {
	var onclickfn = "onclick=\""
			+ "showDialog('寝室导师详情' , 720 , 350 , 'rcsw_zdzm_sqgl.do?method=viewZdzmSq&zdzmsqid="
			+ rowObject['zdzmsqid'] + "')" + "\"";

	var href = "href = 'javascript:void(0);'";

	var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
			+ "</a>";

	return el;
}
/**
 * 寝室号链接
 * 
 * @param cellValue
 * @param rowObject
 * @return
 */

function qshLink(cellValue, rowObject) {
	var xxdm = jQuery("#xxdm").val();
	if(xxdm == "12861"){
		var onclickfn = "onclick=\""
			+ "showDialog('寝室详情' , 720 , 322 , 'gygl_qsdswh.do?method=qsdswhQsxxView&lddm="
			+ rowObject['lddm'] + "&qsh="
			+ rowObject['qsh'] + "')" + "\"";
		
		var href = "href = 'javascript:void(0);'";
		
		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
		+ "</a>";
		
		return el;
	}else if(xxdm == "10351"){
		var qshstr = rowObject['qsh'] + '';
		var onclickfn = "onclick=\""
			+ "showDialog('寝室详情' , 720 , 322 , 'gygl_qsdswh.do?method=qsdswhQsxxView&lddm="
			+ rowObject['lddm'] + "&qsh="
			+ rowObject['qsh'] + "')" + "\"";
		
		var href = "href = 'javascript:void(0);'";
		
		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
		+ "</a>";
		
		return el;
	}else{
		return cellValue;
	}
}