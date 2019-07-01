function searchRs() {
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (null!=shzt&&shzt != "") {
		map["shzt"] = shzt;
	}else{
		map["shzt"] = "dsh";
	}
	jQuery("#dataTable").reloadGrid(map);
}

//ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='zyhdView(\""
			+ rowObject["id"]+"\");'>" + cellValue
			+ "</a>";
}

//�鿴
function zyhdView(id) {
	showDialog("־Ը������鿴", 800, 500, "zyhdry.do?method=viewZyhd&id="+id);
}

// �������
function sbsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shzt").val();
	if (rows.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ��˵ļ�¼")
		return false;
	}
	if (rows.length == 1) {
		var url = "zyhdry.do?method=dgsh&id=" + rows[0]["id"];
		showDialog("���", 700, 520, url);
	} else {
		showDialog("�������", 500, 250, "zyhdry.do?method=plsh");
	}
}
// �������
function savePlsh(shzt, shyj, fwjg) {
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	jQuery.each(rows, function(i, row) {
		guid.push(row["id"]);
	});
	jQuery.post("zyhdry.do?method=plsh&type=save", {
		gsshzt : shzt,
		ids : guid,
		gsshyj:shyj,
		fwjg:fwjg
	}, function(data) {
		
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}

//�������
function cancelSh() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼")
		return false;
	}
	var guid = new Array();
	jQuery.each(rows, function(i, row) {
		guid.push(row["id"]);
	});
	showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
		"okFun" : function() {
			jQuery.post("zyhdry.do?method=cancelSh", {
				ids : guid,
			}, function(data) {		
				showAlertDivLayer(data["message"], {}, {
					"clkFun" : function() {
						jQuery("#dataTable").reloadGrid();
					}
				});
			}, 'json');
		}
	})	
}


// �л�Tabҳ
function selectTab(obj, shzt) {
	jQuery("#shzt").val(shzt);
	if (shzt == "dsh") {
		jQuery("#li_sh").css("display", "");
		jQuery("#li_qx").css("display", "none");
		var map = getSuperSearch();
		map["shzt"]="dsh";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sh").css("display", "none");
		jQuery("#li_qx").css("display", "");
		var map = getSuperSearch();
		map["shzt"]="ysh";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}
