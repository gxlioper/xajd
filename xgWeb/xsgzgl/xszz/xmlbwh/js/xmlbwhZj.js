function saveForm() {
	var lbmc = jQuery.trim(jQuery("#lbmc").val());
	if (lbmc == "") {
		showAlert("�뽫��*����Ŀ��д������");
		return false;
	}
	var lbsm = jQuery.trim(jQuery("#lbsm").val());
	if (lbsm.length > 200) {
		showAlert("��Ŀ˵����󳤶�Ϊ200��");
		return false;
	}

	var url = "xszz_xmlbwh.do?method=xmlbwhZj&type=save";
	ajaxSubFormWithFun("form1", url, function(data) {
		if(data["success"] != undefined && !data["success"]){
			showAlert(data["message"]);
		}else{
			showAlert(data["message"],{},{"clkFun": function(tag) {
				if (tag == "ok") {
					refershParent();
				}
			}});
		}
	});


}