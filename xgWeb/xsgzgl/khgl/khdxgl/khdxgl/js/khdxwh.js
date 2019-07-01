
function searchRs() {
	var map = getSuperSearch();
	var fpzt = jQuery("#fpzt").val();
	if (null!=fpzt&&fpzt != "") {
		map["fpzt"] = fpzt;
	}else{
		map["fpzt"] = "kfp";
	}
	jQuery("#dataTable").reloadGrid(map);
}
//�л�Tabҳ
function selectTab(obj, fpzt) {
	jQuery("#fpzt").val(fpzt);
	if (fpzt == "kfp") {
		jQuery("#li_fp").css("display", "");
		jQuery("#li_dr").css("display", "none");
		jQuery("#li_qxfp").css("display", "none");
		var map = getSuperSearch();
		map["fpzt"]=fpzt;
		gridSetting1["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting1);
	}else{
		jQuery("#li_fp").css("display", "none");
		jQuery("#li_dr").css("display", "");
		jQuery("#li_qxfp").css("display", "");
		var map = getSuperSearch();
		map["fpzt"]="yfp";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}
//���˶������
function khdxFp(dxlx){
	var ids = jQuery("#dataTable").getSeletIds();
	var khdxid=jQuery("#khdxid").val();
	if (ids.length == 0) {
		showAlertDivLayer("������ѡ��һ���û���");
		return false;
	} 
		 	jQuery.post("khglKhdxgl.do?method=saveKhdxFp", {
				values : ids.toString(),
				khlx:dxlx,
				khdxid:khdxid
			}, function(data) {
			    		 showAlert(data["message"],{},{"clkFun":function(){
			    				if (parent.window){
			    					refershParent();
			    				}
			    			}});
				},"json");
}

//���˶������
function khdxQxFp(dxlx){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var khdxid=jQuery("#khdxid").val();
	var checkMsg ="";
	if (ids.length == 0) {
		showAlertDivLayer("������ѡ��һ���û���");
		return false;
	} 
	for ( var i = 0; i < rows.length; i++) {
		if("1"==rows[i]["sfybpf"]){
			if(i!=0){
				checkMsg+=",";
			}
			//���˶���Ϊѧ��
			if("1"==dxlx){
				checkMsg+=rows[i]["xh"];
			}else{
				checkMsg+=rows[i]["yhm"];
			}
		}
		
	}
	if(""!=checkMsg){
		showAlertDivLayer("["+checkMsg+"]"+"�ѱ����ֲ�����ȡ�����䣡");
		return false;
	}
 	jQuery.post("khglKhdxgl.do?method=saveKhdxQxFp", {
		values : ids.toString(),
		khlx:dxlx,
		khdxid:khdxid
	}, function(data) {
	    		 showAlert(data["message"],{},{"clkFun":function(){
	    				if (parent.window){
	    					refershParent();
	    				}
	    			}});
		},"json");
}

//����
function khdxDr(dxlx) {
	// ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
	if("1"==dxlx){
		toImportDataNew("IMPORT_N930101_KHDXGL_XS");
	}else{
		toImportDataNew("IMPORT_N930101_KHDXGL_JS");
	}
	return false;

}