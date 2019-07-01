
function shzt(cellValue, rowObject) {
	var shzt = rowObject["shzt"];
	var mc = rowObject["mc"];
	var shztmc = "";
	switch (shzt) {
	case "1":
		shztmc = "ͨ��";
		break;
	case "2":
		shztmc = "��ͨ��";
		break;
	case "3":
		shztmc = "���˻�";
		break;
	case "5":
		shztmc = "�����";
		break;
	default:
		shztmc = "�����";
		break;
	}
	return mc + "[" + shztmc + "]";
}
// �л�������/�Ѵ���ҳ��
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
// ���
function xgSqSh() {
	var xxdm = jQuery("#xxdm").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("��ѡ��һ���������Ҫ��˵ļ�¼��");
		return false;
	} else if (rows.length == 1) {
		var url = "xsxx_bysxx_xxxgsh.do?method=xgSqSh";
		url += "&xh=" + rows[0]["xh"];
		url += "&gwid=" + rows[0]["gwid"];
		url += "&ywid=" + rows[0]["ywid"];
		url += "&shid=" + rows[0]["guid"];
		url += "&lcid=" + rows[0]["lcid"];
		showDialog("��ҵ������Ϣ�޸��������", 750, 500, url);
	} else {
		if(xxdm == "10511"){
			showConfirmDivLayer("������˻ᵼ������ѧ�����������һ�£���ȷ��Ҫ����������", {
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
	showDialog("��ҵ����Ϣ�޸������������", 450, 200, url);
}
// ���̸���
function shlcInfo() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {
		showDialog("��ҵ����Ϣ�޸��������̸���", 600, 400, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['ywid'] + "&splc=" + rows[0]['lcid']);
	}
}
//����
function cxshnew() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		alertInfo("��ѡ��һ����Ҫ������˵ļ�¼��");
	} else {
		xgShCx(rows[0]["lcid"], rows[0]["guid"], rows[0]["ywid"]);
	}
}
function xgShCx(splc, shid, ywid) {
	var url = "xsxx_bysxx_xxxgsh.do?method=xgShCx";
	confirmInfo("��ȷ��Ҫ����������?", function(ty) {
		if (ty == "ok") {
			jQuery.post("comm_spl.do?method=cxshnew", {
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
function reloadsh() {
	jQuery("#dataTable").reloadGrid();
}