//单个审核
function sbsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
	if (rows.length == 0) {
		showAlertDivLayer("请选择您要审核的记录")
		return false;
	}else if(rows.length != 1){
		showAlertDivLayer("请选择一条您要审核的记录")
		return false;
	}
	if (shzt != "dsh") {
		showAlertDivLayer("已处理记录不能再次审核");
		return false;
	} else{
		var url = "dtjs_tsdzbsh.do?method=dgsh&dzbid="+rows[0]["dzbid"];
		showDialog("审核", 700, 480, url);
	}
}

//审核撤销
function cancelSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要撤消的审核记录！");
	}else{
		var guid = new Array();
		jQuery.each(rows, function(i, row) {
			guid.push(row["dzbid"]);
		});
		showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
			jQuery.post("dtjs_tsdzbsh.do?method=cx",{values:guid.toString()},function(data){
				showAlertDivLayer(data["message"], {}, {
					"clkFun" : function() {
						jQuery("#dataTable").reloadGrid();
					}
				});
		},'json');
		}});
	}
}

function searchRs() {
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (null!=shzt&&shzt != "") {
		map["shzt"] = shzt;
	}else{
		map["shzt"] = "dsh";
	}
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='tsdzbView(\""
			+ rowObject["dzbid"]+"\");'>" + cellValue
			+ "</a>";
}

function tsdzbView(dzbid) {
	showDialog("特色党支部查看", 800, 550, "dtjs_tsdzb.do?method=viewTsdzb&dzbid="+dzbid);
}

//切换Tab页
function selectTab(obj, shzt) {
	jQuery("#shzt").val(shzt);
	if (shzt == "dsh") {
		jQuery("#li_sh").css("display", "");
		jQuery("#li_qx").css("display", "none");
		var map = getSuperSearch();
		map["shzt"]="dsh";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sh").css("display", "none");
		jQuery("#li_qx").css("display", "");
		var map = getSuperSearch();
		map["shzt"]="ysh";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

var DCCLBH = "dtjs_tsdzb_tsdzbsh.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, tsdzbshExportData);
}

//导出方法
function tsdzbshExportData() {
	setSearchTj();//设置高级查询条件
	var shzt = jQuery("#shzt").val();
	var url = "dtjs_tsdzbsh.do?method=exportData&shzt="+shzt+"&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}