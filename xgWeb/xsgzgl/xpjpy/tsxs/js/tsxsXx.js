
//�߼���ѯ
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * ҳǩѡ��
 * @param obj
 * @param fpzt
 * @return
 */
function selectTab(obj, fpzt) {
	jQuery("#fpzt").val(fpzt);
	if (fpzt == "0") {
		jQuery("#li_xz").hide();
		jQuery("#li_sc").show();
		jQuery("#li_dc").show();
		var map = getSuperSearch();
		map["fpzt"]="0";
		if(jQuery("#xxdm").val() == '10279'){
			gridSetting1["params"]=map;
			jQuery("#dataTable").initGrid(gridSetting1);
		}else{
			gridSetting["params"]=map;
			jQuery("#dataTable").initGrid(gridSetting);
		}
	} else {
		jQuery("#li_xz").show();
		jQuery("#li_sc").hide();
		jQuery("#li_dc").hide();
		var map = getSuperSearch();
		map["fpzt"]="1";
		if(jQuery("#xxdm").val() == '10279'){
			gridSetting3["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting3);
		}else{
			gridSetting2["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting2);
		}
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}


function selStudent(){	
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0){
		showAlertDivLayer("������ѡ��һλѧ��!");
		return false;
	} else {
		var ids = jQuery("#dataTable").getSeletIds();
		showConfirmDivLayer("��ȷ��Ҫ������Щѧ����", {
			"okFun" : function() {
				jQuery.post("xpj_tsxs.do?method=plzjTsxs", {
					values : ids.toString(),
					type : "save",
					xn : jQuery("#xn").val(),
					xq : jQuery("#xq").val(),
					lxdm : jQuery("#lxdm").val()
				},
				function(data) {
					showAlert(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');							
			}
		});										
	}
}

//ɾ��
function delTsxs(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("������ѡ��һλѧ��!");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ������ѧ����",{"okFun":function(){
			jQuery.post("xpj_tsxs.do?method=delTsxs",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
	
}

//�Զ��嵼��
function exportConfig(){
	var DCCLBH = 'pj_tsxs.do';
	customExport(DCCLBH, exportData);
}

//��������
function exportData(){
	var DCCLBH = 'pj_tsxs.do';
	setSearchTj();//���ø߼���ѯ����
	var url = "xpj_tsxs.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

