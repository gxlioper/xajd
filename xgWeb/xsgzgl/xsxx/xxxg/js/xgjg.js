

jQuery(function() {
	var gridSetting = {
			caption : "学生信息修改审核列表",
			pager : "pager",
			url : "xsxx_xsxxxg.do?method=xgjg&type=query",
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
				width : '11%',
				formatter : setXh
			}, {
				label : '姓名',
				name : 'xm',
				index : 'xm',
				width : '8%'
			}, {
				label : '性别',
				name : 'xb',
				index : 'xb',
				width : '5%'
			}, {
				label : jQuery("#xbmc").val(),
				name : 'xymc',
				index : 'xydm',
				width : '13%'
			}, {
				label : '班级',
				name : 'bjmc',
				index : 'bjdm',
				width : '13%'
			}, {
				label : '申请时间',
				name : 'xgsj',
				index : 'xgsj',
				width : '15%'
			}, {
				label : '审核状态',
				name : 'shjg',
				index : 'shjg',
				width : '15%',
				formatter : setShjg
			}, {
				label : '审核id',
				name : 'spclid',
				index : 'spclid',
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
			} ],
			params : {
			// shzt : "dsh"
			},// 默认待审核
			sortname : "xgsj",
			sortorder : "desc"
		}
	var map = getSuperSearch();
	gridSetting["params"] = map;
	jQuery("#dataTable").initGrid(gridSetting);
});

function setXh(cellValue, rowObject) {
	var xh = rowObject.xh;
	if (xh == null) {
		xh = "";
	}
	cellValue = "<a  href='javascript:void(0);'  class='name'  onclick='xgjgCkDt(\""
			+ xh
			+ "\",\""
			+ rowObject.sqid
			+ "\" );return false;' >"
			+ xh
			+ "</a>";
	return cellValue;
}


function setShjg(cellValue, rowObject) {
	if(cellValue == "0" || cellValue == "wsh" ){
		cellValue = "未审核" ;
	}else if(cellValue == "1" || cellValue == "tg" ){
		cellValue = "通过" ;
	}else if(cellValue == "2" || cellValue == "btg" ){
		cellValue = "不通过" ;
	}else if(cellValue == "3" || cellValue == "th" ){
		cellValue = "退回" ;
	}else {
		cellValue = "审核中" ;
	}
	return cellValue;
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


/**
 * 
 * 
 * @return
 */
function xgjgCk() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要查看的记录！");
	} else {
		var url = "xsxx_xsxxxg.do?method=xgjgCk";
		url += "&xh=" + rows[0]["xh"];
		url += "&sqid=" + rows[0]["sqid"];
		showDialog("学生信息修改结果查看", 750, 500, url);
	}
}

function xgjgCkDt(xh,sqid) {

	var url = "xsxx_xsxxxg.do?method=xgjgCk";
	url += "&xh=" + xh;
	url += "&sqid=" + sqid;
	showDialog("学生信息修改结果查看", 750, 500, url);
}

function searchRs() {
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();

	if (shzt != "") {
		map["shzt"] = shzt;
	}
	jQuery("#dataTable").reloadGrid(map);
}

function exportConfigSh() {	
	var DCCLBH = "xsxx_xsxxxg_xgjg.do";//dcclbh,导出功能编号，执行导出函数 
	customExport(DCCLBH, exportDataSh);
}

function exportDataSh() {
	setSearchTj();//设置高级查询条件
	var DCCLBH = "xsxx_xsxxxg_xgjg.do";
	var url = "xsxx_xsxxxg.do?method=exportDataJg&dcclbh=" + DCCLBH ;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}