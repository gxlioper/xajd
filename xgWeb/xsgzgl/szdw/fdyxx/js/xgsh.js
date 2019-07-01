
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
		value = "�����";
	} else if (cellValue == '1') {
		value = "ͨ��";
	} else if (cellValue == '2') {
		value = "��ͨ��";
	} else if (cellValue == '3') {
		value = "�˻�";
	} else if (cellValue == '4') {
		value = "������";
	}
	value = mc + "[" + value + "]";
	return value;
}

function shlcInfo() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {
		var shlc = rows[0]["splcid"];
		if (shlc == "") {
			showAlertDivLayer("������������ˣ������������Ϣ��");
			return false;
		}
		showDialog("����Ա��Ϣ�޸��������̸���", 600, 400, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['ywid'] + "&splc=" + rows[0]['lcid']);
	}
}

function xgjgCk(zgh, ywid, lcid) {
	if (lcid == "") {
		showAlertDivLayer("������������ˣ������������Ϣ��");
		return false;
	}
	var url = "szdw_fdyxx.do?method=xgjgCk";
	url += "&zgh=" + zgh;
	url += "&sqid=" + ywid;
	showDialog("����Ա��Ϣ�޸Ĳ鿴", 750, 500, url);
}

/**
 * ���
 * 
 * @return
 */
function xgsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("��ѡ��һ���������Ҫ�޸ĵļ�¼��");
	} else if (rows.length == 1) {
		var url = "szdw_fdyxx.do?method=xgsh";
		url += "&timestamp=" + new Date().getTime();
		url += "&zgh=" + rows[0]["zgh"];
		url += "&gwid=" + rows[0]["gwid"];
		url += "&ywid=" + rows[0]["ywid"];
		url += "&shid=" + rows[0]["guid"];
		url += "&lcid=" + rows[0]["lcid"];
		showDialog("����Ա��Ϣ�޸����", 750, 550, url);
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
		showDialog("����Ա��Ϣ�޸��������", 480, 200, url);
	}

}

/**
 * �л������롢δ����
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
 * �л����ҳtabҳ
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
 * ����
 */
function cxshnew() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ������˵ļ�¼��");
	} else {
		if (rows[0]["shjg"] == "1"){
			showAlertDivLayer("���޸����������ͨ�������ܳ�����");
		} else {
			splc_cx_new(rows[0]["lcid"], rows[0]["guid"], rows[0]["ywid"]);
		}
	}
}
/*
 * �������̳��� shid ���id splc ��������id
 */
function splc_cx_new(splc, shid, ywid) {
	// ���һ��������˺���õ�·��
	var url = "xsxx_xsxxxg.do?method=thRecordForStu";
	confirmInfo("��ȷ��Ҫ����������?", function(ty) {
		if (ty == "ok") {
			jQuery.post("xsxx_xsxxxg.do?method=updateSqzt", {
				sqid : ywid,
				shlc : splc,
				shid : shid
			}, function(data) {
				// �ж��Ƿ����һ������(1:���һ�������ɹ���
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