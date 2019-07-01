var dataObj;
var colNum = 4;// 列数
var _parentTd;// 父类当前选中td
var gxzList = [];
jQuery(function() {
	onShow();
});

function onShow() {
	var api = frameElement.api, W = api.opener;
	_parentTd = jQuery(W.tjszDialog.content.document).find(
			"[name=tjzCur][value=1]").parent();
	var curGxz = _parentTd.find("[name='curGxz']").val();
	
	var gxzs = "";
	if (curGxz != null) {
		gxzs = curGxz.split("|");
	}

	for ( var i = 0; i < gxzs.length; i = i + 2) {
		var dm = gxzs[i];
		var mc;
		if (gxzs[i + 1] != undefined) {
			mc = gxzs[i + 1];
		} else {
			mc = dm;
		}
		var dmmc = {};
		dmmc.dm = dm;
		dmmc.mc = mc;
		gxzList.push(dmmc);
	}

	createHtml();
	setInit();// 设置初值
}

function createHtml() {
	var _tbody = jQuery("#tbody");

	for ( var i = 0; i < gxzList.length; i++) {
		var o = gxzList[i];
		var dm = o.dm;
		var mc = o.mc;
		var sHtml = "";
		sHtml += "<td>";
		sHtml += "<label>";
		sHtml += "<input type='checkbox' value='" + dm + "' name='tjzView'/>";
		sHtml += mc;
		sHtml += "</label>";
		sHtml += "</td>";
		var _tr = _tbody.find("tr:last");
		if (_tr.length == 0) {
			_tbody.append("<tr></tr>");
		} else if (_tr.find("td").length >= colNum) {
			_tbody.append("<tr></tr>");
		}
		_tbody.find("tr:last").append(sHtml);
	}

	/*
	 * 对不足的tr进行填充
	 */

	var _tr = _tbody.find("tr:last");
	var tdLength = _tr.find("td").length;
	if (tdLength < colNum) {
		var sHtml = "";
		for ( var j = 0; j < colNum - tdLength; j++) {
			sHtml += "<td>&nbsp;</td>";
		}
		_tr.append(sHtml);
	}
}

// 初始化数据
function setInit() {
	var tjz = _parentTd.find("[name='tjz']").val();
	var arr = tjz.split(",");

	jQuery.each(arr, function(index, value) {
		jQuery("input[name='tjzView'][value='" + value + "']").attr("checked",
				"checked");
	});

	// 全选按钮
	jQuery("input:checkbox[name=grid_selectAll]").change(
			function() {
				jQuery("#tbody input:checkbox").attr("checked",
						jQuery(this).prop("checked"));
			});
	if (jQuery("#tbody  input:checkbox:not(:checked)").length == 0) {
		jQuery("input:checkbox[name=grid_selectAll]")
				.attr("checked", "checked");
	}
}

/* 保存 */
function saveForm() {
	var tjz = "";
	var tjzView = "";
	tjz = jQuery("input[name=tjzView]:checked").map(function() {
		  return jQuery(this).val();
	}).get().join(',');

	if (tjz == null || tjz == "") {
		showAlert("请勾选记录！");
		return false;
	}
	
	for ( var i = 0; i < gxzList.length; i++) {
		var o = gxzList[i];
		var dm = o.dm;
		var mc = o.mc;
		if(tjz.indexOf(dm) > -1){
			if(tjzView != ""){
				tjzView += ",";
			}
			tjzView += mc;
		}
	}

	_parentTd.find("[name='tjz']").val(tjz);
	_parentTd.find("[name='tjzView']").val(tjzView);
	// W.tjszDialog.focus();
	iFClose();
}
