var action="jskpXmsb.do";
//�л�������/�Ѵ���ҳ��
function selectTab(obj, sbzt) {
	jQuery("#sbzt").val(sbzt);

	if (sbzt == "0") {
		jQuery("#li_sb").css("display", "");
		jQuery("#li_qx").css("display", "none");
		jQuery("#li_lcgz").css("display", "none");
		var map = getSuperSearch();
		map["sbzt"]="0";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sb").css("display", "none");
		jQuery("#li_qx").css("display", "");
		jQuery("#li_lcgz").css("display", "");
		var map = getSuperSearch();
		map["sbzt"]="1";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	reload();
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");

	searchRs();
}
function searchRs() {
	var map = getSuperSearch();
	var sbzt = jQuery("#sbzt").val();
	if (sbzt != "") {
		map["sbzt"] = sbzt;
	}

	jQuery("#dataTable").reloadGrid(map);
}
function reload() {
	jQuery("#dataTable").reloadGrid();
}

//��Ŀ�걨
function xmsb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ������Ҫ�걨�ļ�¼��");
		return false;
	}
	if(rows[0]["sbkg"]=='false'){
		showAlertDivLayer("��Ŀ�걨�ѹرգ�");
		return false;
	}
	jQuery.post(action+"?method=xmsbCheck",{xmid:ids},function(data){
		// �ظ��걨
		if("1" == data["jgzqFlg"]){
				showAlertDivLayer(data["message"]);
		}else{
			var url =action+"?method=xmsb&xmid="+ids;
			var title = "��Ŀ�걨";
			showDialog(title, 800, 500, url);
		}
	
},'json');
	
}
function saveXmsb() {	
	var hjsj =jQuery("#hjsj").val();
	var sbly =jQuery("#sbly").val();
	if(hjsj==null||hjsj==""||sbly==null||sbly=="") {
		showAlert("�뽫��������д������");
		return false;
	}
	 if(jQuery(".MultiFile-label").length<=0){
		showAlertDivLayer("���ϴ�������");
		return false;
   }
	 
	var url = action+"?method=saveXmsb";
	ajaxSubFormWithFun("jskpXmsbForm", url, function(data) {
		 if(data["success"]=="true"){
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

//ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post(action+"?method=delXmsb", {
					values : ids.toString(),
					splcid:rows[0]["splcid"]
				},
				function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}
/*
 * ���̸���
 */
function lcgz() {
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (1 != rows.length) {
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
		return false;
	} 
		showDialog("�������̸���",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splcid']);
}
