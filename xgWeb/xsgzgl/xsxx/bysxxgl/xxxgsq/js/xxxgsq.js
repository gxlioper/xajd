jQuery(function() {
	var gridSetting = {
		caption : "毕业生信息修改申请列表",
		pager : "pager",
		url : "xsxx_bysxx_xxxgsq.do?method=XgSqCk&type=query",
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
			width : '12%'
		}, {
			label : jQuery("#xbmc").val(),
			name : 'xymc',
			index : 'xymc',
			width : '18%'
		}, {
			label : '专业',
			name : 'zymc',
			index : 'zymc',
			width : '18%'
		}, {
			label : '班级',
			name : 'bjmc',
			index : 'bjmc',
			width : '15%'
		}, {
			label : '申请时间',
			name : 'xgsj',
			index : 'xgsj',
			width : '15%'
		}, {
			label : '审核状态',
			name : 'shjgmc',
			index : 'shjgmc',
			width : '10%'
		}, {
			label : '审核状态',
			name : 'shjg',
			index : 'shjg',
			hidden : true
		}, {
			label : '审核id',
			name : 'splc',
			index : 'splc',
			hidden : true
		} ],
		params : {
		// shzt : "dsh"
		},// 默认待审核
		sortname : "xgsj",
		sortorder : "desc"
	}
	jQuery("#dataTable").initGrid(gridSetting);
});

function xxxgsq() {
	var kfxg = jQuery("#kfxg").val();// 可否修改,y,n
	if ("y" != kfxg) {
		showAlertDivLayer("当前未开放毕业生信息修改申请，请联系管理员！");
		return false;
	}
	var user = jQuery("#usertype").val();

	if ("stu" == user) {
		var xh = jQuery("#xh").val();

		var url = "xsxx_bysxx_xxxgsq.do?method=bysXxXgSq";
		url += "&xh=" + xh;
		showDialog("毕业生信息修改申请", 850, 550, url);
	} else {
		showDialog('请选择一个毕业生', 800, 500,
				'xsxx_bysxx_xxxgsq.do?method=showBysXx');
	}
}

function xxXgSq(type) {
	initParam();
	if (!zdybdCheck()) {
		return;
	}
	if (!getXgzdJson()) {
	}
	if (type == 'submit') {
		jQuery("#but_save").hide();
		var url = "xsxx_bysxx_xxxgsq.do?method=bysXxXgSqTj";
		ajaxSubFormWithFun("demoform", url, function(data) {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		});
	} else {
		var url = "xsxx_bysxx_xxxgsq.do?method=bysXxXgSqBc";
		ajaxSubFormWithFun("demoform", url, function(data) {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		});
	}
}
// 申请修改
function xgsqXg() {
	var kfxg = jQuery("#kfxg").val();// 可否修改,y,n
	var rows = jQuery("#dataTable").getSeletRow();
	if ("y" != kfxg) {
		showAlertDivLayer("当前未开放毕业生信息修改申请，请联系管理员！");
		return;
	}

	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var shjg = rows[0]["shjg"];
		if (shjg != '0' && shjg != '3') {
			showAlertDivLayer("请选择未提交或者已退回的记录！");
			return false;
		} else {

			var xh = rows[0]["xh"];
			var sqid = rows[0]["sqid"];
			var splc = rows[0]["splc"];
			var url = "xsxx_bysxx_xxxgsq.do?method=SqXg";
			url += "&xh=" + xh + "&sqid=" + sqid + "&splc=" + splc;
			showDialog("毕业生信息修改申请修改", 850, 550, url);
		}
	}
}
// 申请删除
function xgsqSc() {
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = [];
	for ( var i = 0; i < rows.length; i++) {
		ids.push(rows[i]['sqid'])
	}
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要取消申请的记录！");
	} else if (rows[0]["shjg"] != "0" && rows[0]["shjg"] != "3") {
		showAlertDivLayer("只能删除未提交或者已退回的记录！");
		return false;
	} else {
		showConfirmDivLayer("您确定要取消该申请吗？", {
			"okFun" : function() {
				jQuery.post("xsxx_bysxx_xxxgsq.do?method=del", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}
// 申请提交
function xgsqTj() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要提交的记录！");
	} else if (ids.length > 1) {
		showAlertDivLayer("请选择一条您要提交的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shjg'] != '0' && rows[i]['shjg'] != '3') {
				showAlertDivLayer("请选择未提交或者已退回的记录！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("xsxx_bysxx_xxxgsq.do?method=submitRecord", {
					sqid : rows[0]['sqid'],
					splc : rows[0]['splc'],
					xh : rows[0]['xh']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}
// 撤销
function xgsqCx() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要撤销的记录！");
	} else if (ids.length > 1) {
		showAlertDivLayer("请选择一条您要撤销的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shjg'] != '5') {
				showAlertDivLayer("只有审核中的记录才能被撤销！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {

				jQuery.post("xsxx_bysxx_xxxgsq.do?method=xgsqCx", {
					values : ids.toString(),
					lcid : rows[0]['splc']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');

			}
		});
	}

}
// 流程跟踪
function shlcInfo() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条记录！");
	} else {
		if (rows[0]["shjg"] == "0") {
			showAlertDivLayer("无相关流程信息！");
			return false;
		}
		showDialog("毕业生信息修改申请审批流程跟踪", 530, 310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
	}
}

// 点击序号跳转
function setXh(cellValue, rowObject) {
	var xh = rowObject.xh;
	var sqid = rowObject.sqid;
	if (xh == null) {
		xh = "";
	}
	cellValue = "<a  href='javascript:void(0);'  class='name'  onclick='sqxxCk(\""
			+ xh + "\",\"" + sqid + "\" );return false;' >" + xh + "</a>";
	return cellValue;
}
// 查看信息
function sqxxCk(xh, sqid) {
	var url = "xsxx_bysxx_xxxgsq.do?method=showXgXx&xh=" + xh + "&sqid=" + sqid;
	var title = "毕业生信息申请修改结果查看";
	showDialog(title, 850, 550, url);
}

// 下载登记表
function printByjdb(url) {
	var xh = "";
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length < 1) {
		showAlertDivLayer("请至少选择一条记录！");
	} else if (rows.length == 1) {

		url += "&xh=" + rows[0]["xh"];
		window.open(url);
	} else {
		for ( var i = 0; i < rows.length; i++) {
			if (i == rows.length - 1) {
				xh += rows[i]["xh"];
			} else {
				xh += rows[i]["xh"] + ",";
			}
		}
		var url = "xsxx_bysxx_xxgl.do?method=printByjdbZip" + "&xh=" + xh;
		window.open(url);
	}
}

/**
 * 
 * 
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
