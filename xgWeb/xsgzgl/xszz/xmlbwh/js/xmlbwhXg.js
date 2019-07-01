function saveForm() {
	var lbmc = jQuery.trim(jQuery("#lbmc").val());
	if (lbmc == "") {
		showAlert("请将带*的项目填写完整！");
		return false;
	}
	var lbsm = jQuery.trim(jQuery("#lbsm").val());
	if (lbsm.length > 200) {
		showAlert("项目说明最大长度为200！");
		return false;
	}

	var url = "xszz_xmlbwh.do?method=xmlbwhXg&type=save";
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
