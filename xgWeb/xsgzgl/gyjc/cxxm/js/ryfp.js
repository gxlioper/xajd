/**
 * ��ѯ
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	var sffp = jQuery("#sffp").val();
	map["sffp"] = sffp;
	map["xydm"] = jQuery("#xydm").val();
	map["jjlx"] = jQuery("#jjlx").val();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * ҳǩѡ��
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj, sffp) {
	jQuery("#sffp").val(sffp);
	if (sffp == "not") {
		jQuery("#li_fp").css("display", "");
		jQuery("#li_qx").css("display", "none");
		var map = getSuperSearch();
		map["sffp"]="not";
		map["xydm"] = jQuery("#xydm").val();
		map["jjlx"] = jQuery("#jjlx").val();
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_fp").css("display", "none");
		jQuery("#li_qx").css("display", "");
		var map = getSuperSearch();
		map["sffp"]="";
		map["xydm"] = jQuery("#xydm").val();
		map["jjlx"] = jQuery("#jjlx").val();
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

/**
 * ������Ա����
 * @return
 */
function saveFyfp(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length == 0){
		return showAlertDivLayer("����ѡ����Ա��");
	}
	var zghs = new Array();
	for ( var i = 0; i < rows.length; i++) {
		zghs.push(rows[i]['zgh']);
	}
	var para = {
				zghs:zghs,
				xydm:jQuery("#xydm").val(),
				jjlx:jQuery("#jjlx").val()
				};
	jQuery.post("gyjc_jcsd.do?method=saveRyFp",para,function(data){
		showAlertDivLayer(data["message"]);
		jQuery("#dataTable").reloadGrid();
	},'json');
}

/**
 * ȡ������
 * @return
 */
function cancelFp(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length == 0){
		return showAlertDivLayer("����ѡ����Ա��");
	}
	var zghs = new Array();
	for ( var i = 0; i < rows.length; i++) {
		zghs.push(rows[i]['zgh']);
	}
	var para = {
			zghs:zghs,
			xydm:jQuery("#xydm").val(),
			jjlx:jQuery("#jjlx").val()
	};
	jQuery.post("gyjc_jcsd.do?method=cancelRyfp",para,function(data){
		showAlertDivLayer(data["message"]);
		jQuery("#dataTable").reloadGrid();
	},'json');
}

/**
 * ���ػ����趨����ѯҳ��
 * @return
 */
function fhjcsd(){
	document.location.href = "xg_gyjc_jcsd.do";
}