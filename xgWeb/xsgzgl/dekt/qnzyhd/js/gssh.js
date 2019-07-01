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

//学号链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='zyhdView(\""
			+ rowObject["id"]+"\");'>" + cellValue
			+ "</a>";
}

//查看
function zyhdView(id) {
	showDialog("志愿活动分数查看", 800, 500, "zyhdry.do?method=viewZyhd&id="+id);
}

// 单个审核
function sbsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
	if (rows.length == 0) {
		showAlertDivLayer("请选择您要审核的记录")
		return false;
	}
	if (rows.length == 1) {
		var url = "zyhdry.do?method=dgsh&id=" + rows[0]["id"];
		showDialog("审核", 700, 520, url);
	} else {
		showDialog("批量审核", 500, 250, "zyhdry.do?method=plsh");
	}
}
// 批量审核
function savePlsh(shzt, shyj, fwjg) {
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	jQuery.each(rows, function(i, row) {
		guid.push(row["id"]);
	});
	jQuery.post("zyhdry.do?method=plsh&type=save", {
		gsshzt : shzt,
		ids : guid,
		gsshyj:shyj,
		fwjg:fwjg
	}, function(data) {
		
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}

//撤销审核
function cancelSh() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("请选择您要撤销的记录")
		return false;
	}
	var guid = new Array();
	jQuery.each(rows, function(i, row) {
		guid.push(row["id"]);
	});
	showConfirmDivLayer("您确定要撤销选择的记录吗？", {
		"okFun" : function() {
			jQuery.post("zyhdry.do?method=cancelSh", {
				ids : guid,
			}, function(data) {		
				showAlertDivLayer(data["message"], {}, {
					"clkFun" : function() {
						jQuery("#dataTable").reloadGrid();
					}
				});
			}, 'json');
		}
	})	
}


// 切换Tab页
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
