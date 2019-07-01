
function shzt(cellValue, rowObject) {
	var shzt = rowObject["shzt"];
	var mc = rowObject["mc"];
	var shztmc = "";
	switch (shzt) {
	case "1":
		shztmc = "通过";
		break;
	case "2":
		shztmc = "不通过";
		break;
	case "3":
		shztmc = "已退回";
		break;
	case "5":
		shztmc = "审核中";
		break;
	default:
		shztmc = "待审核";
		break;
	}
	return mc + "[" + shztmc + "]";
}
// 切换待处理/已处理页面
function selectTab(obj, shzt) {
	jQuery("#shzt").val(shzt);

	if (shzt == "dsh") {
		jQuery("#li_sh").css("display", "");
		jQuery("#li_qx").css("display", "none");

		var map = getSuperSearch();
		map["shzt"] = "dsh";
		gridSetting1["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting1);
	} else {
		jQuery("#li_sh").css("display", "none");
		jQuery("#li_qx").css("display", "");

		var map = getSuperSearch();
		map["shzt"] = "ysh";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}

	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}
function searchRs() {
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (shzt != "") {
		map["shzt"] = shzt;
	}
	jQuery("#dataTable").reloadGrid(map);
}
// 审核
function xgSqSh() {
	var xxdm = jQuery("#xxdm").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("请选择一条或多条您要审核的记录！");
		return false;
	} else if (rows.length == 1) {
		var url = "xsxx_bysxx_xxxgsh.do?method=xgSqSh";
		url += "&xh=" + rows[0]["xh"];
		url += "&gwid=" + rows[0]["gwid"];
		url += "&ywid=" + rows[0]["ywid"];
		url += "&shid=" + rows[0]["guid"];
		url += "&lcid=" + rows[0]["lcid"];
		showDialog("毕业生生信息修改申请审核", 750, 500, url);
	} else {
		if(xxdm == "10511"){
			showConfirmDivLayer("批量审核会导致所有学生的评语意见一致，您确定要这样操作吗？", {
				"okFun" : function() {
					plshCommon(rows);
				}
			});
		}else{
			plshCommon(rows);
		}
	}

}
function plshCommon(rows){
	var dataJson = "";
	var url = "xsxx_bysxx_xxxgsh.do?method=xgSqPlSh";
	for ( var i = 0; i < rows.length; i++) {
		var row = rows[i];
		if (dataJson != "") {
			dataJson += ",";
		}
		dataJson += "{'xh':'" + row["xh"];
		dataJson += "','gwid':'" + row["gwid"];
		dataJson += "','ywid':'" + row["ywid"];
		dataJson += "','shid':'" + row["guid"];
		dataJson += "','lcid':'" + row["lcid"];
		dataJson += "'}";
	}
	if (dataJson != "") {
		dataJson = "[" + dataJson + "]";
	}
	url += "&params=" + dataJson;
	showDialog("毕业上信息修改申请批量审核", 450, 200, url);
}
// 流程跟踪
function shlcInfo() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {
		showDialog("毕业生信息修改审批流程跟踪", 600, 400, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['ywid'] + "&splc=" + rows[0]['lcid']);
	}
}
//撤销
function cxshnew() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		alertInfo("请选择一条您要撤销审核的记录！");
	} else {
		xgShCx(rows[0]["lcid"], rows[0]["guid"], rows[0]["ywid"]);
	}
}
function xgShCx(splc, shid, ywid) {
	var url = "xsxx_bysxx_xxxgsh.do?method=xgShCx";
	confirmInfo("您确定要撤销操作吗?", function(ty) {
		if (ty == "ok") {
			jQuery.post("comm_spl.do?method=cxshnew", {
				shlc : splc,
				shid : shid
			}, function(data) {
				// 判断是否最后一级撤销(1:最后一级撤销成功）
					if ("1" == data["cancelFlg"]) {
						jQuery.post(url, {
							sqid : ywid
						}, function(result) {
							showAlertDivLayer(data["message"], {}, {
								"clkFun" : function() {
									jQuery("#dataTable").reloadGrid();
								}
							});
						}, 'json');
					} else {
						showAlertDivLayer(data["message"], {}, {
							"clkFun" : function() {
								jQuery("#dataTable").reloadGrid();
							}
						});
					}

				}, 'json');
		}
	});
}
function reloadsh() {
	jQuery("#dataTable").reloadGrid();
}