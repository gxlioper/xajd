/**
 * ��ѯ
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	var sffp = jQuery("#sffp").val();
	if (null!=sffp&&sffp != "") {
		map["sffp"] = sffp;
	}else{
		map["sffp"] = "0";
	}
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * ҳǩѡ��
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj, shzt) {
	jQuery("#sffp").val(shzt);
	if (shzt == "0") {
		jQuery("#li_bc").css("display", "");
		jQuery("#li_qx").css("display", "none");
		var map = getSuperSearch();
		map["sffp"]="0";
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_bc").css("display", "none");
		jQuery("#li_qx").css("display", "");
		var map = getSuperSearch();
		map["sffp"]="1";
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

/**
 * �������
 * @return
 */
function saveFp(){
	var rows = jQuery("#dataTable").getSeletRow();
	var url = "xpj_cpxz.do?method=saveFp";
	if(rows.length == 0){
		showAlert("��ѡ��Ҫ�������Ա��");
		return false;
	}
	var yhmArray = new Array();

	jQuery(rows).each(function(i,row){
		yhmArray.push(row['zgh']);
	})
	jQuery.post(url, {
        cpzdms:jQuery("#cpzdms").val(),
        zghs : yhmArray
	}, function(data) {
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
                refershParent();
			}
		});
	}, 'json');
}

/**
 * ȡ������
 * ȡ��ҳǩҳ��ֻ�е���ҳ��¥����¼��ѡ��ʱ��Ŵ���
 * @return
 */
function cancelFp(){
	var rows = jQuery("#dataTable").getSeletRow();
	var url = "xpj_cpxz.do?method=cancelFp";
	if(rows.length == 0){
		showAlert("��ѡ��Ҫȡ������Ĳ��������Ա��");
	}
	var yhmArray = new Array();
	jQuery(rows).each(function(i,row){
		yhmArray.push(row['zgh']);
	})
	jQuery.post(url, {
        cpzdms:jQuery("#cpzdms").val(),
		zghs : yhmArray
	}, function(data) {
		
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
                refershParent();
			}
		});
	}, 'json');
}
