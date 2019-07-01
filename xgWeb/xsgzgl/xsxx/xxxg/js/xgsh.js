var gridSetting = {
	caption : "学生信息修改审核列表",
	pager : "pager",
	url : "xsxx_xsxxxg.do?method=xgsh&type=query",
	colList : [ {
		label : 'key',
		name : 'sqid',
		index : 'sqid',
		key : true,
		hidden : true
	}, {
		label : '学号',
		name : 'xh',
		index : 'xh',
		width : '12%',
		formatter : setXh
	}, {
		label : '姓名',
		name : 'xm',
		index : 'xm',
		width : '10%'
	}, {
		label : '性别',
		name : 'xb',
		index : 'xb',
		width : '5%'
	}, {
		label : jQuery("#xbmc").val(),
		name : 'xymc',
		index : 'xydm',
		width : '20%'
	}, {
		label : '班级',
		name : 'bjmc',
		index : 'bjdm',
		width : '20%'
	}, {
		label : '申请时间',
		name : 'xgsj',
		index : 'xgsj',
		width : '18%'
	}, {
		label : 'gwid',
		name : 'gwid',
		index : 'gwid',
		hidden : true
	}, {
		label : 'lcid',
		name : 'lcid',
		index : 'lcid',
		hidden : true
	}, {
		label : 'ywid',
		name : 'ywid',
		index : 'ywid',
		hidden : true
	}, {
		label : '审核岗位',
		name : 'mc',
		index : 'mc',
		hidden : true
	}, {
		label : '审核id',
		name : 'guid',
		index : 'guid',
		hidden : true
	}, {
		label : '审核状态',
		name : 'shzt',
		index : 'shzt',
		width : '15%',
		formatter : setShzt
	} ],
	params : {
		shzt : "dsh"
	},// 默认待审核
	sortname : "xgsj",
	sortorder : "desc"
}
var gridSetting2 = {
	caption : "学生信息修改审核列表",
	pager : "pager",
	url : "xsxx_xsxxxg.do?method=xgsh&type=query",
	colList : [ {
		label : 'key',
		name : 'sqid',
		index : 'sqid',
		key : true,
		hidden : true
	}, {
		label : '学号',
		name : 'xh',
		index : 'xh',
		width : '12%',
		formatter : setXh
	}, {
		label : '姓名',
		name : 'xm',
		index : 'xm',
		width : '10%'
	}, {
		label : '性别',
		name : 'xb',
		index : 'xb',
		width : '5%'
	}, {
		label : jQuery("#xbmc").val(),
		name : 'xymc',
		index : 'xydm',
		width : '15%'
	}, {
		label : '班级',
		name : 'bjmc',
		index : 'bjdm',
		width : '20%'
	}, {
		label : '审核时间',
		name : 'shsj',
		index : 'shsj',
		width : '18%'
	}, {
		label : 'gwid',
		name : 'gwid',
		index : 'gwid',
		hidden : true
	}, {
		label : '审核id',
		name : 'guid',
		index : 'guid',
		hidden : true
	}, {
		label : 'lcid',
		name : 'lcid',
		index : 'lcid',
		hidden : true
	}, {
		label : 'ywid',
		name : 'ywid',
		index : 'ywid',
		hidden : true
	}, {
		label : '审核岗位',
		name : 'mc',
		index : 'mc',
		hidden : true
	}, {
		label : '审核状态',
		name : 'shzt',
		index : 'shzt',
		width : '15%',
		formatter : setShzt

	} ],
	params : {
		shzt : "ysh"
	},// 已审核
	sortname : "shsj",
	sortorder : "desc"
};
jQuery(function() {
	var map = getSuperSearch();
	map["shzt"] = "dsh";
	gridSetting["params"] = map;
	jQuery("#dataTable").initGrid(gridSetting);
});

function setXh(cellValue, rowObject) {
	var xh = rowObject.xh;
	if (xh == null) {
		xh = "";
	}
	cellValue = "<a  href='javascript:void(0);'  class='name'  onclick='xgjgCk(\""
			+ xh
			+ "\",\""
			+ rowObject.ywid
			+ "\",\""
			+ rowObject.lcid
			+ "\" );return false;' >" + xh + "</a>";
	return cellValue;

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
		showDialog("学生信息修改审批流程跟踪", 600, 400, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['ywid'] + "&splc=" + rows[0]['lcid']);
	}
}

function xgjgCk(xh, ywid, lcid) {
	if (lcid == "") {
		showAlertDivLayer("此申请无需审核，无相关流程信息！");
		return false;
	}
	var url = "xsxx_xsxxxg.do?method=xgjgCk";
	url += "&xh=" + xh;
	url += "&sqid=" + ywid;
	showDialog("学生信息修改查看", 750, 500, url);
}

/**
 * 审核
 * 
 * @return
 */
function xgshZj() {
	var rows = jQuery("#dataTable").getSeletRow();
	var shkg = jQuery("#shkg").val();
	if(shkg=="n"){
		showAlertDivLayer("当前未开放审核，请联系管理员！");
		return false;
	}
	if (rows.length == 0) {
		showAlertDivLayer("请选择一条或多条您要修改的记录！");
	} else if (rows.length == 1) {
		var url = "xsxx_xsxxxg.do?method=xgshZj";
		url += "&timestamp=" + new Date().getTime();
		url += "&xh=" + rows[0]["xh"];
		url += "&gwid=" + rows[0]["gwid"];
		url += "&ywid=" + rows[0]["ywid"];
		url += "&shid=" + rows[0]["guid"];
		url += "&lcid=" + rows[0]["lcid"];
		showDialog("学生信息修改审核", 750, 500, url);
	} else {
		var dataJson = "";
		var url = "xsxx_xsxxxg.do?method=xgshPlzj";
//		for ( var i = 0; i < rows.length; i++) {
//			var row = rows[i];
//			if (dataJson != "") {
//				dataJson += ",";
//			}
//			dataJson += "{'xh':'" + row["xh"];
//			dataJson += "','gwid':'" + row["gwid"];
//			dataJson += "','ywid':'" + row["ywid"];
//			dataJson += "','shid':'" + row["guid"];
//			dataJson += "','lcid':'" + row["lcid"];
//			dataJson += "'}";
//		}
//		if (dataJson != "") {
//			dataJson = "[" + dataJson + "]";
//		}
		url += "&params=" + dataJson + "&timestamp=" + new Date().getTime();
		showDialog("学生信息修改批量审核", 550, 200, url);
	}

}

/**
 * 批量审核保存
 * @return
 */
function savePlsh(shjg,thgw,shyj){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var dataJson = "";
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
	jQuery.post(
			"xsxx_xsxxxgsh.do?method=savePlshlc",
			{
			 shjg:shjg,
			 dataJson:dataJson,
			 shyj:shyj,
			 thgw:thgw
			},function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					jQuery("#dataTable").reloadGrid();
				}});
			},
			'json'
		);
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
		alertInfo("请选择一条您要撤销审核的记录！");
	} else {
		splc_cx_new(rows[0]["lcid"], rows[0]["guid"], rows[0]["ywid"]);
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

//自定义导出功能（审核）
function exportConfigSh() {	
	var DCCLBH = "xsxx_xsxxxg_xgsh.do";//dcclbh,导出功能编号，执行导出函数 
	customExport(DCCLBH, exportDataSh);
}

//导出方法（审核）
function exportDataSh() {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//设置高级查询条件
	var DCCLBH = "xsxx_xsxxxg_xgsh.do";
	var url = "xsxx_xsxxxg.do?method=exportDataSh&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
