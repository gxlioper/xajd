var gndm = "xsxx_update";// 功能代码

var flszList;// 分类设置列表
var zddyList;// 字段定义列表
var bkczzd = "nj,xydm,zydm,bjdm";// 学生不可操作字段，固定为只读
jQuery(function() {
	onShow();
});

function onShow() {
	jQuery("#gndm").val(gndm);

	var url = "zdybd_flsz.do?method=getFlszList";
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			gndm : gndm
		},
		dataType : "json",
		success : function(data) {
			flszList = data;
			createFl();
		}
	});

	initZd();

	createZd();

	yhFl();// 取消无字段的分类列表

	setZdSx();

}

function initZd() {
	var url = "zdybd_zddy.do?method=getZddySimpleList";
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			gndm : gndm
		},
		dataType : "json",
		success : function(data) {
			zddyList = data;
		}
	});
}

/*
 * 生成二级内容导航
 */
function createFl() {
	for ( var i = 0; i < flszList.length; i++) {
		var sHtml = "";
		var o = flszList[i];
		var flszid = o.flszid;// 分类设置id
		var flmc = o.flmc;// 分类名称
		if (flmc != null && flmc != "") {
			sHtml += "<thead id='thead_" + flszid + "'>";
			sHtml += "<tr onclick='' style='cursor: hand;'>";
			sHtml += "<th colspan='6'>";
			sHtml += "<span>"
					+ flmc
					+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>";
			sHtml += "<label>";
			sHtml += "<input type='checkbox' class='cbvclass' style='cursor: pointer;' name='checkzdall' onclick='zdbtallClick(this,\""
					+ flszid + "\");' >全部只读";
			sHtml += "</label>";
			sHtml += "&nbsp;&nbsp;&nbsp;&nbsp;";
			sHtml += "<label>";
			sHtml += "<input type='checkbox' class='cbvclass' style='cursor: pointer;' name='checkbtall'  onclick='zdbtallClick(this,\""
					+ flszid + "\");' >全部必填";
			sHtml += "</label>";
			sHtml += "</th>";
			sHtml += "</tr>";
			sHtml += "</thead>";
			sHtml += "<tbody id='tbody_" + flszid + "'>";
			sHtml += "</tbody>";
		}
		jQuery("#content").append(sHtml);
	}
}

/*
 * 优化分类
 */
function yhFl() {
	for ( var i = 0; i < flszList.length; i++) {
		var sHtml = "";
		var o = flszList[i];
		var flszid = o.flszid;// 分类设置id
		var flmc = o.flmc;// 分类名称

		if (jQuery("#tbody_" + flszid + " input:checkbox").length == 0) {
			jQuery("#thead_" + flszid).remove();
		}

	}
}

function createZd() {
	for ( var i = 0; i < zddyList.length; i++) {
		var o = zddyList[i];
		var flszid = o.flszid;
		var zddyid = o.zddyid;
		var zd = o.zd;// 
		var bdmc = o.bdmc;// 表单名称
		if (bdmc == null || bdmc == "" || bdmc == "null") {
			continue;
		}
		var sHtml = "";
		sHtml += "<th id='th_" + zddyid + "'>";
		sHtml += "<span  name='btxh' class='bold' style='display:none;'>";
		sHtml += "<font color='red'>*</font>";
		sHtml += "</span>";
		sHtml += bdmc;
		sHtml += "<span name='sfzd'   style='display:none;'>";
		sHtml += "<font color='gray'>(只读)</font>";
		sHtml += "</span>";
		sHtml += "<span name='sfkx'  class='bold' style='display:none;'>";
		sHtml += "<font color='green'>(可写)</font>";
		sHtml += "</span>";
		sHtml += "<span name='sfbt'  class='bold' style='display:none;'>";
		sHtml += "<font color='red'>(必填)</font>";
		sHtml += "</span>";
		sHtml += "</th>";
		sHtml += "<td width='15%' id='td_" + zddyid + "'>";
		sHtml += "<label>";
		sHtml += "<input type='checkbox' name='checkzd' class='cbvclass' style='cursor: pointer;'  onclick='zdbtClick(this,\""
				+ zddyid + "\");' >只读";
		sHtml += "</label>";
		sHtml += "<label>";
		sHtml += "<input type='checkbox' name='checkbt' class='cbvclass' style='cursor: pointer;'  onclick='zdbtClick(this,\""
				+ zddyid + "\");' >必填";
		sHtml += "</label>";
		sHtml += "</td>";

		var _tbody = jQuery("#tbody_" + flszid);
		var _tr = _tbody.find("tr:last");
		if (_tr.length == 0) {
			_tbody.append("<tr></tr>");
		} else if (_tr.find("td").length == 3) {
			_tbody.append("<tr></tr>");
		}
		_tbody.find("tr:last").append(sHtml);
	}

	/*
	 * 对不足的tr进行填充
	 */
	for ( var i = 0; i < flszList.length; i++) {
		var sHtml = "";
		var o = flszList[i];
		var flszid = o.flszid;// 分类设置id

		var _tbody = jQuery("#tbody_" + flszid);
		var _tr = _tbody.find("tr:last");
		var tdLength = _tr.find("td").length;
		if (tdLength < 3) {
			for ( var j = 0; j < 3 - tdLength; j++) {
				sHtml += "<th>&nbsp;</th><td>&nbsp;</td>";
			}
			_tr.append(sHtml);
		}

	}

}

/*
 * 只读必填复选框操作
 */
function zdbtClick(obj, zddyid) {
	var _td = jQuery("#td_" + zddyid);
	var _zdCheck = _td.find("[name='checkzd']");
	var _btCheck = _td.find("[name='checkbt']");
	var yxwk = "";
	var yxxg = "";
	var curName = jQuery(obj).attr("name");
	if (curName == "checkzd") {
		_btCheck.attr("checked",false);
		if (_zdCheck.prop("checked")) {
			yxxg = "0";
		} else {
			yxxg = "1";
		}
	} else {
		_zdCheck.attr("checked",false);
		if (_btCheck.prop("checked")) {
			yxwk = "0";
		} else {
			yxwk = "1";
		}
	}
	setZdbt(zddyid, yxwk, yxxg);
}

/*
 * 只读必填，复选框，全选操作
 */
function zdbtallClick(obj, flszid) {
	var _tbody = jQuery("#tbody_" + flszid);
	var _thead = jQuery("#thead_" + flszid);

	var _zdallCheck = _thead.find("[name='checkzdall']");// 只读全选
	var _btallCheck = _thead.find("[name='checkbtall']");// 必填全选

	var _zdCheck = _tbody.find("[name='checkzd']");// 该分类下所有只读复选框
	var _btCheck = _tbody.find("[name='checkbt']");// 该分类下所有必填复选框

	var _sfzd = _tbody.find("span[name='sfzd']");// 是否只读
	var _sfbt = _tbody.find("span[name='sfbt']");// 是否必填
	var _sfkx = _tbody.find("span[name='sfkx']");// 是否可选
	var _btxh = _tbody.find("span[name='btxh']");// 必填星号

	var curName = jQuery(obj).attr("name");

	_sfzd.parents("th").css("color", "");

	if (curName == "checkzdall") {// 只读
		if (_zdallCheck.prop("checked")) {
			_btallCheck.attr("checked",false);

			_zdCheck.attr("checked", "checked");
			_btCheck.attr("checked",false);

			_sfzd.show();
			_sfbt.hide();
			_btxh.hide();
			_sfkx.hide();
			_sfzd.parents("th").css("color", "gray");
		} else {
			_zdCheck.attr("checked",false);

			_sfzd.hide();
			_sfbt.hide();
			_btxh.hide();
			_sfkx.show();
		}
	} else {// 必填
		if (_btallCheck.prop("checked")) {
			_zdallCheck.attr("checked",false);

			_btCheck.attr("checked", "checked");
			_zdCheck.attr("checked",false);

			_sfbt.show();
			_btxh.show();
			_sfzd.hide();
			_sfkx.hide();
		} else {
			_btCheck.attr("checked",false);

			_sfkx.show();
			_sfbt.hide();
			_btxh.hide();
			_sfzd.hide();
		}
	}

}

/**
 * 设置字段属性
 * 
 * @return
 */
function setZdSx() {
	var lb = jQuery("#lb").val();
	for ( var i = 0; i < zddyList.length; i++) {
		var o = zddyList[i];
		var flszid = o.flszid;
		var zd = o.zd;// 
		var zddyid = o.zddyid;
		var zdlx = o.zdlx;// 字段类型
		var bdmc = o.bdmc;// 表单名称
		var yxwk = "";
		var yxxg = "";
		if (lb == "js") {
			yxwk = o.yxwk;// 允许为空
			yxxg = o.yxxg;// 允许修改
		} else {
			yxwk = o.stuyxwk;// 允许为空
			yxxg = o.stuyxxg;// 允许修改
			if (bkczzd.indexOf(zd) > -1) {
				yxwk = "0";// 不允许为空
				yxxg = "0";// 不允许修改
			}
		}
		setZdbt(zddyid, yxwk, yxxg);
		setZdbtCheck(zddyid, yxwk, yxxg);
		
		if (bkczzd.indexOf(zd) > -1) {
			var _zdCheckbox = jQuery("#td_" + zddyid + " :checkbox");
			if (lb != "js") {
				_zdCheckbox.attr("disabled", "disabled");
			}else{
				_zdCheckbox.attr("disabled",false);
			}
		}		
	}
}

/**
 * 只读，必填设置
 * 
 * @param zd
 * @param yxwk
 * @param yxxg
 * @return
 */
function setZdbt(zddyid, yxwk, yxxg) {
	var _sfzd = jQuery("#th_" + zddyid).find("span[name='sfzd']");
	var _sfbt = jQuery("#th_" + zddyid).find("span[name='sfbt']");
	var _sfkx = jQuery("#th_" + zddyid).find("span[name='sfkx']");
	var _btxh = jQuery("#th_" + zddyid).find("span[name='btxh']");

	_sfzd.parents("th").css("color", "");
	if (yxxg == "0") {// 不允许修改
		_sfzd.show();
		_sfbt.hide();
		_btxh.hide();
		_sfkx.hide();
		_sfzd.parents("th").css("color", "gray");
	} else {
		_sfzd.hide();
		if (yxwk == "0") {// 不允许为空
			_sfbt.show();
			_btxh.show();
			_sfkx.hide();
		} else {
			_sfkx.show();
			_sfbt.hide();
			_btxh.hide();
		}
	}
}

/*
 * 对复选框设置选 中状态
 */
function setZdbtCheck(zddyid, yxwk, yxxg) {
	var _checkzd = jQuery("#td_" + zddyid).find("[name='checkzd']");
	var _checkbt = jQuery("#td_" + zddyid).find("[name='checkbt']");
	if (yxxg == "0") {
		_checkzd.attr("checked", "checked");
		_checkbt.attr("checked",false);
	} else {
		_checkzd.attr("checked",false);
		if (yxwk == "0") {
			_checkbt.attr("checked", "checked");
		} else {
			_checkbt.attr("checked",false);
		}
	}
}

/**
 * 切换tab页
 * 
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj, lb) {
	jQuery("#lb").val(lb);
	if (lb == "xs") {
		jQuery("#li_xs").addClass("ha");
		jQuery("#li_js").removeClass("ha");
	} else {
		jQuery("#li_js").addClass("ha");
		jQuery("#li_xs").removeClass("ha");
	}

	jQuery("[name='checkzdall']").attr("checked",false);
	jQuery("[name='checkbtall']").attr("checked",false);
	setZdSx();
}

/**
 * 保存
 * 
 * @return
 */
function save() {
	var lb = jQuery("#lb").val();
	var dataJson = "";
	for ( var i = 0; i < zddyList.length; i++) {
		var o = zddyList[i];
		var flszid = o.flszid;
		var zddyid = o.zddyid;
		var zd = o.zd;// 
		var bdmc = o.bdmc;// 表单名称
		var yxwk = "";
		var yxxg = "";

		var checkzd = jQuery("#td_" + zddyid).find("[name='checkzd']").attr(
				"checked");
		var checkbt = jQuery("#td_" + zddyid).find("[name='checkbt']").attr(
				"checked");
		if (checkzd) {
			yxxg = "0";
		} else {
			yxxg = "1";
		}

		if (checkbt) {
			yxwk = "0";
		} else {
			yxwk = "1";
		}

		if (dataJson != "") {
			dataJson += ",";
		}
		dataJson += "{zddyid:'" + zddyid + "',yxwk:'" + yxwk + "',yxxg:'"
				+ yxxg + "'}";
	}

	if (dataJson != "") {
		dataJson = "{data:[" + dataJson + "]}";
	}
	jQuery("#dataJson").val(dataJson);
	var url = "zdybd_zddy.do?method=updateSimple";
	ajaxSubFormWithFun("form1", url, function(data) {
		showAlert(data["message"]);
		initZd();

		setZdSx();
	});
}
