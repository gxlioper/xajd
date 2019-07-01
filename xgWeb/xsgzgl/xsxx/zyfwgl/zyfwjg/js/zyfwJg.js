/**
 * 志愿服务结果相关js
 */

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 学号格式化
 */
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='zyfwJgShow(\""
			+ rowObject["fwid"]+"\");'>" + cellValue
			+ "</a>";
}

/**
 * 服务地点过长截取
 */
function fwddSubString(cellValue, rowObject){
	var cellValueNotNull = cellValue ? cellValue : "";
	var cellValueNew = cellValueNotNull.length > 20 ? cellValue.substring(0,20)+"..." : cellValueNotNull;
	return "<span title='"+cellValueNotNull+"' >" + cellValueNew + "</span>";
}

/**
 * 查看
 */
function zyfwJgShow(fwid) {
	var title = jQuery("#gnmkmc").val()+"查看";
	var url = "xsxx_zyfwgl_jg.do?method=zyfwJgShow&fwid="+fwid;
	showDialog(title, 800, 500,url);
}

/**
 * 新增的保存
 */
var checkId = 'xh-xn-xq-fwkssj-fwjssj-fwddssx-fwdd-jzr-fwxss-fwnr';

function zyfwJgSaveForAdd() {
	if(!checkNotNull(checkId)){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
	if(jQuery("#fwddssx").val().endsWith("0000")){
		showAlert("请至少将省、市两级选择完整！");
		return false;
	}
	if (jQuery("#fwnr").val().length>500) {
		showAlert("服务内容最多输入500字！");
		return false;
	}
	var url = "xsxx_zyfwgl_jg.do?method=zyfwJgSaveForAdd";
	ajaxSubFormWithFun("zyfwJgForm", url, function(data) {
		 if(data["message"]=="保存成功！"||data["message"]=="提交成功！"){
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
 * 编辑的保存
 */
function zyfwJgSaveForEdit(type) {
	if(!checkNotNull(checkId)){
		showAlertDivLayer("请将必填项填写完整！");
		return false;
	}
	if(jQuery("#fwddssx").val().endsWith("0000")){
		showAlert("请至少将省、市两级选择完整！");
		return false;
	}
	if (jQuery("#fwnr").val().length>500) {
		showAlert("服务内容最多输入500字！");
		return false;
	}
	var url = "xsxx_zyfwgl_jg.do?method=zyfwJgSaveForEdit";
	ajaxSubFormWithFun("zyfwJgForm", url, function(data) {
		 if(data["message"]=="保存成功！"||data["message"]=="提交成功！"){
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
 * 填写弹框页面
 */
function add() {
	var url = "xsxx_zyfwgl_jg.do?method=zyfwJgAdd";
	var title = jQuery("#gnmkmc").val();
	showDialog(title, 800, 550, url);
}

/**
 * 修改弹框页面
 */
function edit() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {

		var sjly = rows[0]["sjly"];
		if ("1" == sjly) {
			showAlertDivLayer("审核流程过来的记录不能修改！");
			return false;
		}

		var url = 'xsxx_zyfwgl_jg.do?method=zyfwJgEdit&fwid=' + rows[0]["fwid"];
		var title = jQuery("#gnmkmc").val()+"修改";
		showDialog(title, 800, 550, url);
	}

}

/**
 * 删除
 */
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	} 
	for(var i=0;i<rows.length;i++){
		if (rows[i]["sjly"] == "1") {
			showAlertDivLayer("审核流程过来的记录不能删除！");
			return false;
		}
	}
	
	showConfirmDivLayer("您确定要删除选择的记录吗？", {
		"okFun" : function() {
			jQuery.post("xsxx_zyfwgl_jg.do?method=zyfwJgDel", {
				values : ids.toString()
			},
					function(data) {
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}, 'json');
		}
	});
}

/**
 * 导出
 */
var DCCLBH = "xsxx_zyfwgl_jg.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, exportData);
}

//导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "xsxx_zyfwgl_jg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 志愿服务结果的导入
 */
function importConfig() {
	// 调用通用的导入function，参数是导入功能模块代码。
	toImportDataNew("IMPORT_ZYFWJG");
	return false;
}

