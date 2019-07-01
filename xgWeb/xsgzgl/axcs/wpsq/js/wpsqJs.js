
jQuery(function(){
	var gridSetting = {
		caption : "物品申请列表",
		pager : "pager",
		url : "axcswpsqjs.do?method=wpsqJsList&type=query",
		colList : [ {
			label : 'key',
			name : 'sqid',
			index : 'sqid',
			key : true,
			hidden : true
		},{
			label : 'xmdm',
			name : 'xmdm',
			index : 'xmdm',
			hidden : true
		},{
			label : '审批流程',
			name : 'splc',
			index : 'splc',
			hidden : true
		}, {
			label : '学号',
			name : 'xh',
			index : 'xh',
			width : '12%',
			formatter : xhLink
		}, {
			label : '学年',
			name : 'xn',
			index : 'xn',
			width : '12%'
		}, {
			label : '姓名',
			name : 'xm',
			index : 'xm',
			width : '8%'
		}, {
			label : jQuery("#xbmc").val(),
			name : 'xymc',
			index : 'xymc',
			width : '20%'
		}, {
			label : '班级',
			name : 'bjmc',
			index : 'bjdm',
			width : '20%'
		}, {
			label : '申请时间',
			name : 'sqsj',
			index : 'sqsj',
			width : '18%'
		}, {
			label : '物品名称',
			name : 'xmmc',
			index : 'xmmc',
			width : '10%'
		}, {
			label : '物品类别',
			name : 'xmlbmc',
			index : 'xmlbmc',
			width : '8%'
		}, {
			label : '审核状态',
			name : 'shztmc',
			index : 'shztmc',
			width : '6%'
		}, {
			label : '审核状态',
			name : 'shzt',
			index : 'shzt',
			hidden : true
		}, {
			label : '申请理由代码',
			name : 'lxdm',
			index : 'lxdm',
			hidden : true
		}, {
			label : '申请开关',
			name : 'isopen',
			index : 'isopen',
			hidden : true
		} ],
		sortname : "sqsj",
		sortorder : "desc"
	}
	var map = getSuperSearch();
	gridSetting["params"] = map;
	jQuery("#dataTable").initGrid(gridSetting);
})

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function selectXm() {
	var wpxm = jQuery("input[name=xmdm]:checked");
	var gotoPath = jQuery("#gotoPath").val();
	if (wpxm.size() != 1) {
		showAlert("请选择一项您要申请的物品！");
		return false;
	}
	var xmdm = jQuery("input[name=xmdm]:checked").val();
	gotoPath = gotoPath + "&xmdm=" + xmdm + "&xh=" + jQuery("#xh").val();
	var api = frameElement.api;
	if (api) {
		if (api.get('childDialog')) {
			api.reload(api.get('parentDialog'), gotoPath);
		} else {
			var W = api.opener;
			W.location = gotoPath;
		}
	} else if (parent.window) {
		parent.window.document.location = gotoPath;
	}
	iFClose();
}

function saveWpsq(type) {
	var xh = jQuery("#xh").val();
	if (jQuery("#sqly").val() == "" || xh == "") {
		showAlert("请将必填项填写完整！");
		return false;
	}
	if (jQuery("#xmdm").val() == "" || null == jQuery("#xmdm").val()) {
		showAlert("请选择申请物品！");
		return false;
	}
	if (jQuery("#sqly").val().length>500) {
		showAlert("申请理由最多输入500字！");
		return false;
	}
	var url = "axcswpsqjs.do?method=saveWpsq&type=" + type;
	ajaxSubFormWithFun("WpsqJsForm", url, function(data) {
		showAlert(data["message"], {}, {
			"clkFun" : function() {
				if (parent.window) {
					refershParent();
				}
			}
		});
	});
}
function add() {
	var url = "axcswpsqjs.do?method=wpsqZj";
	var title = "物品申请";
	showDialog(title, 700, 480, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {

		var shzt = rows[0]["shzt"];

		if ("0" != shzt&&"3" != shzt) {
			showAlertDivLayer("只有未提交和已退回的记录才能修改！");
			return false;
		}

		var url = 'axcswpsqjs.do?method=wpsqUpdate&sqid=' + rows[0]["sqid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "物品申请修改";
		showDialog(title, 700, 480, url);
	}

}

// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("只能删除未提交或者已退回的记录！");
		return false;
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("axcswpsqjs.do?method=delWpsq", {
					values : ids.toString()
				},
						function(data) {
							var mes = "成功删除了<font color='green'>&nbsp;"
									+ data["num"] + "&nbsp;</font>条数据";
							showAlertDivLayer(mes);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
			}
		});
	}
}

// 提交
function submitBusi() {

	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("请选择未提交或者已退回的记录！");
		return false;
	}
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条您要提交的记录！");
	} else {
		if ('3' != rows[0]['shzt'] && "false" == rows[0]['isopen']) {
			showAlertDivLayer("当前物品已关闭申请，请与管理员联系！");
			return false;
		}

		var xmdm = rows[0]["xmdm"];
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("axcswpsqjs.do?method=saveUpdateWpsq&type=tj", {
					values : ids.toString(),
					xmdm : xmdm
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}

}
// 撤销
function cancel() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要撤销的记录！");
	} else if (ids.length > 1) {
		showAlertDivLayer("请选择一条您要撤销的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shzt'] != '5') {
				showAlertDivLayer("只有审核中的记录才能被撤销！");
				return false;
			}
		}

		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("axcswpsqjs.do?method=cancelWpsq", {
					values : ids.toString(),
					splcid : rows[0]['splc']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}

}
/*
 * 流程跟踪
 */
function lcgz() {
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = rows[0]["shzt"];
	if (1 != rows.length) {
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {
		if ("0" == shzt) {
			showAlertDivLayer("该记录为未提交状态，请先提交！");
			return false;
		}
		showDialog("爱心超市审批流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
	}
}

function wpsqView(sqid, xh) {
	showDialog("爱心超市申请查看", 700, 580, "axcswpsqjs.do?method=wpsqView&sqid="
			+ sqid + "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='wpsqView(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}
var DCCLBH = "axcs_axcswpsq_tea.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, wpjgExportData);
}

//导出方法
function wpjgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "axcswpsqjs.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}