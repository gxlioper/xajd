

/**
 * ��ѯ
 */
function searchRs() {
	var map = getSuperSearch();
	var shzt = jQuery("#shlx").val();
	if (null!=shzt&&shzt != "") {
		map["shlx"] = shzt;
	}else{
		map["shlx"] = "dsh";
	}
	jQuery("#dataTable").reloadGrid(map);
}



/**
 * ���
 */
function sh() {
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = jQuery("#shlx").val();
	if (rows.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ��˵ļ�¼")
		return false;
	}
	if (shzt == "ysh") {
		showAlertDivLayer("�Ѵ����¼�����ٴ����");
		return false;
	}
	if (rows.length == 1) {
		var title = "���";
		var url = "bjhdgl_bjhdsh.do?method=bjhdShDgsh&sqid=" + rows[0]["sqid"]  + '&shid=' + rows[0]["shid"] + '&gwid=' + rows[0]["gwid"];
		showDialog(title, 700, 480, url);
	} else {
		showDialog("־Ը�����������", 500, 250, "bjhdgl_bjhdsh.do?method=bjhdShPlsh");
	}
}

/**
 * ������˱���
 */
function saveForDgsh(){
	if (jQuery("#shyj").val() == ""){
		showAlert("�뽫��������д������");
		return false;
	}
	var url = "bjhdgl_bjhdsh.do?method=saveForDgsh";
	ajaxSubFormWithFun("BjhdShForm",url,function(data){
		 if(data["message"]=="����ɹ���"){
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
		});
}

/**
 * ������˱���
 */
function saveForPlsh(shjg,shyj) {
	
	var rows = jQuery("#dataTable").getSeletRow();
	var sqids = new Array();
	var gwids = new Array();
	var sqrs = new Array();
	jQuery.each(rows, function(i, row) {
        sqids.push(row["sqid"]);
		gwids.push(row["gwid"]);
        sqrs.push(row["sqr"]);
	});
	jQuery.post("bjhdgl_bjhdsh.do?method=saveForPlsh", {
		shjg : shjg,
		splc : rows[0]["splc"],
        sqids : sqids,
		gwids : gwids,
        xhs : sqrs,
		shyj : shyj
	}, function(data) {
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}

/**
 * �л�Tabҳ
 */
function selectTab(obj, shlx) {
	jQuery("#shlx").val(shlx);
	if (shlx == "dsh") {
		jQuery("#li_sh").css("display", "");
		jQuery("#li_qx").css("display", "none");
		var map = getSuperSearch();
		map["shlx"]="dsh";
		gridSetting["params"] = map;
		gridSetting["radioselect"] = false;
	} else {
		jQuery("#li_sh").css("display", "none");
		jQuery("#li_qx").css("display", "");
		var map = getSuperSearch();
		map["shlx"]="ysh";
		gridSetting["params"] = map;
		gridSetting["radioselect"] = true;
	}
	jQuery("#dataTable").initGrid(gridSetting);
	
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

/**
 * ��˳���
 */
function cancelSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
	} else {
		var splc = rows[0]["splc"];
		var shid = rows[0]["shid"];
		var sqid = rows[0]["sqid"];
		var shzt = rows[0]["shzt"];
		showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
			jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// �ж��Ƿ����һ������(1:���һ�������ɹ���
				if("1" == data["cancelFlg"]){
					jQuery.post("bjhdgl_bjhdsh.do?method=cancelShLast",{sqid:sqid,shzt:shzt},function(result){
						showAlertDivLayer(result["message"],{},{"clkFun":function(){
							jQuery("#dataTable").reloadGrid();
						}});
					},'json');
				}else{
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						jQuery("#dataTable").reloadGrid();
					}});
				}
			
		},'json');
		}});
	}
}

/**
 * ���̸���
 */
function lcgz(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (1!=ids.length){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {		
		showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}

