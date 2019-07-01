var gndm = "xsxx_update";// ���ܴ���

var flszList;// ���������б�
var zddyList;// �ֶζ����б�
var bkczzd = "nj,xydm,zydm,bjdm";// ѧ�����ɲ����ֶΣ��̶�Ϊֻ��
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

	yhFl();// ȡ�����ֶεķ����б�

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
 * ���ɶ������ݵ���
 */
function createFl() {
	for ( var i = 0; i < flszList.length; i++) {
		var sHtml = "";
		var o = flszList[i];
		var flszid = o.flszid;// ��������id
		var flmc = o.flmc;// ��������
		if (flmc != null && flmc != "") {
			sHtml += "<thead id='thead_" + flszid + "'>";
			sHtml += "<tr onclick='' style='cursor: hand;'>";
			sHtml += "<th colspan='6'>";
			sHtml += "<span>"
					+ flmc
					+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>";
			sHtml += "<label>";
			sHtml += "<input type='checkbox' class='cbvclass' style='cursor: pointer;' name='checkzdall' onclick='zdbtallClick(this,\""
					+ flszid + "\");' >ȫ��ֻ��";
			sHtml += "</label>";
			sHtml += "&nbsp;&nbsp;&nbsp;&nbsp;";
			sHtml += "<label>";
			sHtml += "<input type='checkbox' class='cbvclass' style='cursor: pointer;' name='checkbtall'  onclick='zdbtallClick(this,\""
					+ flszid + "\");' >ȫ������";
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
 * �Ż�����
 */
function yhFl() {
	for ( var i = 0; i < flszList.length; i++) {
		var sHtml = "";
		var o = flszList[i];
		var flszid = o.flszid;// ��������id
		var flmc = o.flmc;// ��������

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
		var bdmc = o.bdmc;// ������
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
		sHtml += "<font color='gray'>(ֻ��)</font>";
		sHtml += "</span>";
		sHtml += "<span name='sfkx'  class='bold' style='display:none;'>";
		sHtml += "<font color='green'>(��д)</font>";
		sHtml += "</span>";
		sHtml += "<span name='sfbt'  class='bold' style='display:none;'>";
		sHtml += "<font color='red'>(����)</font>";
		sHtml += "</span>";
		sHtml += "</th>";
		sHtml += "<td width='15%' id='td_" + zddyid + "'>";
		sHtml += "<label>";
		sHtml += "<input type='checkbox' name='checkzd' class='cbvclass' style='cursor: pointer;'  onclick='zdbtClick(this,\""
				+ zddyid + "\");' >ֻ��";
		sHtml += "</label>";
		sHtml += "<label>";
		sHtml += "<input type='checkbox' name='checkbt' class='cbvclass' style='cursor: pointer;'  onclick='zdbtClick(this,\""
				+ zddyid + "\");' >����";
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
	 * �Բ����tr�������
	 */
	for ( var i = 0; i < flszList.length; i++) {
		var sHtml = "";
		var o = flszList[i];
		var flszid = o.flszid;// ��������id

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
 * ֻ�����ѡ�����
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
 * ֻ�������ѡ��ȫѡ����
 */
function zdbtallClick(obj, flszid) {
	var _tbody = jQuery("#tbody_" + flszid);
	var _thead = jQuery("#thead_" + flszid);

	var _zdallCheck = _thead.find("[name='checkzdall']");// ֻ��ȫѡ
	var _btallCheck = _thead.find("[name='checkbtall']");// ����ȫѡ

	var _zdCheck = _tbody.find("[name='checkzd']");// �÷���������ֻ����ѡ��
	var _btCheck = _tbody.find("[name='checkbt']");// �÷��������б��ѡ��

	var _sfzd = _tbody.find("span[name='sfzd']");// �Ƿ�ֻ��
	var _sfbt = _tbody.find("span[name='sfbt']");// �Ƿ����
	var _sfkx = _tbody.find("span[name='sfkx']");// �Ƿ��ѡ
	var _btxh = _tbody.find("span[name='btxh']");// �����Ǻ�

	var curName = jQuery(obj).attr("name");

	_sfzd.parents("th").css("color", "");

	if (curName == "checkzdall") {// ֻ��
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
	} else {// ����
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
 * �����ֶ�����
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
		var zdlx = o.zdlx;// �ֶ�����
		var bdmc = o.bdmc;// ������
		var yxwk = "";
		var yxxg = "";
		if (lb == "js") {
			yxwk = o.yxwk;// ����Ϊ��
			yxxg = o.yxxg;// �����޸�
		} else {
			yxwk = o.stuyxwk;// ����Ϊ��
			yxxg = o.stuyxxg;// �����޸�
			if (bkczzd.indexOf(zd) > -1) {
				yxwk = "0";// ������Ϊ��
				yxxg = "0";// �������޸�
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
 * ֻ������������
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
	if (yxxg == "0") {// �������޸�
		_sfzd.show();
		_sfbt.hide();
		_btxh.hide();
		_sfkx.hide();
		_sfzd.parents("th").css("color", "gray");
	} else {
		_sfzd.hide();
		if (yxwk == "0") {// ������Ϊ��
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
 * �Ը�ѡ������ѡ ��״̬
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
 * �л�tabҳ
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
 * ����
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
		var bdmc = o.bdmc;// ������
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
