
//高级查询
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 页签选择
 * @param obj
 * @param fpzt
 * @return
 */
function selectTab(obj, fpzt) {
	jQuery("#fpzt").val(fpzt);
	if (fpzt == "0") {
		jQuery("#li_xz").hide();
		jQuery("#li_sc").show();
		jQuery("#li_dc").show();
		var map = getSuperSearch();
		map["fpzt"]="0";
		if(jQuery("#xxdm").val() == '10279'){
			gridSetting1["params"]=map;
			jQuery("#dataTable").initGrid(gridSetting1);
		}else{
			gridSetting["params"]=map;
			jQuery("#dataTable").initGrid(gridSetting);
		}
	} else {
		jQuery("#li_xz").show();
		jQuery("#li_sc").hide();
		jQuery("#li_dc").hide();
		var map = getSuperSearch();
		map["fpzt"]="1";
		if(jQuery("#xxdm").val() == '10279'){
			gridSetting3["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting3);
		}else{
			gridSetting2["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting2);
		}
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}


function selStudent(){	
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0){
		showAlertDivLayer("请至少选择一位学生!");
		return false;
	} else {
		var ids = jQuery("#dataTable").getSeletIds();
		showConfirmDivLayer("您确定要增加这些学生吗？", {
			"okFun" : function() {
				jQuery.post("xpj_tsxs.do?method=plzjTsxs", {
					values : ids.toString(),
					type : "save",
					xn : jQuery("#xn").val(),
					xq : jQuery("#xq").val(),
					lxdm : jQuery("#lxdm").val()
				},
				function(data) {
					showAlert(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');							
			}
		});										
	}
}

//删除
function delTsxs(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请至少选择一位学生!");
	} else {
		showConfirmDivLayer("您确定要删除特殊学生吗？",{"okFun":function(){
			jQuery.post("xpj_tsxs.do?method=delTsxs",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
	
}

//自定义导出
function exportConfig(){
	var DCCLBH = 'pj_tsxs.do';
	customExport(DCCLBH, exportData);
}

//导出数据
function exportData(){
	var DCCLBH = 'pj_tsxs.do';
	setSearchTj();//设置高级查询条件
	var url = "xpj_tsxs.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

