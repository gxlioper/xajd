
function setXh(cellValue, rowObject) {
	var html = "<a  href='javascript:void(0);'  class='name'  onclick='xgjgCk(\""
			+ rowObject.zgh
			+ "\",\""
			+ rowObject.ywid
			+ "\",\""
			+ rowObject.lcid
			+ "\" );return false;' >" + rowObject.zgh + "</a>";
	return html;
}

function setShzt(cellValue, rowObject) {
	var mc = rowObject.mc;
	var value;
	if (cellValue == '0') {
		value = "待审核";
	} else if (cellValue == '1') {
		value = "通过";
	} else if (cellValue == '2') {
		value = "不通过";
	} else if (cellValue == '3') {
		value = "退回";
	} else if (cellValue == '4') {
		value = "需重审";
	}
	value = mc + "[" + value + "]";
	return value;
}

function shlcInfo() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {
		var shlc = rows[0]["splcid"];
		if (shlc == "") {
			showAlertDivLayer("此申请无需审核，无相关流程信息！");
			return false;
		}
		showDialog("辅导员信息修改审批流程跟踪", 600, 400, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['ywid'] + "&splc=" + rows[0]['lcid']);
	}
}

function xgjgCk(zgh, ywid, lcid) {
	if (lcid == "") {
		showAlertDivLayer("此申请无需审核，无相关流程信息！");
		return false;
	}
	var url = "szdw_fdyxx.do?method=xgjgCk";
	url += "&zgh=" + zgh;
	url += "&sqid=" + ywid;
	showDialog("辅导员信息修改查看", 750, 500, url);
}

/**
 * 审核
 * 
 * @return
 */
function xgsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("请选择一条或多条您要修改的记录！");
	} else if (rows.length == 1) {
		var url = "szdw_fdyxx.do?method=xgsh";
		url += "&timestamp=" + new Date().getTime();
		url += "&zgh=" + rows[0]["zgh"];
		url += "&gwid=" + rows[0]["gwid"];
		url += "&ywid=" + rows[0]["ywid"];
		url += "&shid=" + rows[0]["guid"];
		url += "&lcid=" + rows[0]["lcid"];
		showDialog("辅导员信息修改审核", 750, 550, url);
	} else {
		var dataJson = "";
		var url = "szdw_fdyxx.do?method=plsh";
		for ( var i = 0; i < rows.length; i++) {
			var row = rows[i];
			if (dataJson != "") {
				dataJson += ",";
			}
			dataJson += "{'zgh':'" + row["zgh"];
			dataJson += "','gwid':'" + row["gwid"];
			dataJson += "','ywid':'" + row["ywid"];
			dataJson += "','shid':'" + row["guid"];
			dataJson += "','lcid':'" + row["lcid"];
			dataJson += "'}";
		}
		if (dataJson != "") {
			dataJson = "[" + dataJson + "]";
		}
		url += "&params=" + dataJson + "&timestamp=" + new Date().getTime();
		showDialog("辅导员信息修改批量审核", 480, 200, url);
	}

}

/**
 * 切换已申请、未申请
 * 
 * @param obj
 * @param tabId
 * @return
 */
function selectSqxm(obj, tabId) {

	jQuery("#comp_title li").removeClass("ha");
	jQuery(obj).parent().addClass("ha");

	jQuery("#xmList tbody").css("display", "none");
	jQuery("#" + tabId).css("display", "");

	if (tabId == "ysq") {
		jQuery("#titleTr td").last().css("display", "none");
	} else {
		jQuery("#titleTr td").last().css("display", "");
	}
}

/**
 * 切换审核页tab页
 * 
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj, shzt) {
	jQuery("#shzt").val(shzt);

	if (shzt == "dsh") {
		jQuery("#li_sh").css("display", "");
		jQuery("#li_qx").css("display", "none");

		var map = getSuperSearch();
		map["shzt"] = "dsh";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
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

/*
 * 撤销
 */
function cxshnew() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要撤销审核的记录！");
	} else {
		if (rows[0]["shjg"] == "1"){
			showAlertDivLayer("该修改申请已审核通过，不能撤消！");
		} else {
			splc_cx_new(rows[0]["lcid"], rows[0]["guid"], rows[0]["ywid"]);
		}
	}
}
/*
 * 审批流程撤销 shid 审核id splc 审批流程id
 */
function splc_cx_new(splc, shid, ywid) {
	// 最后一级撤销审核后调用的路径
	var url = "xsxx_xsxxxg.do?method=thRecordForStu";
	confirmInfo("您确定要撤销操作吗?", function(ty) {
		if (ty == "ok") {
			jQuery.post("xsxx_xsxxxg.do?method=updateSqzt", {
				sqid : ywid,
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