function saveForm() {
	var xmmc = jQuery.trim(jQuery("#xmmc").val());
	var lbdm = jQuery("#lbdm").val();
	var je = jQuery.trim(jQuery("#je").val());
	var xmsm = jQuery("#xmsm").val();
	if (xmmc == "" || lbdm == "" || je == "") {
		showAlert("请将带*的项目填写完整！");
		return false;
	}
	
	if(xmsm.length > 500) {
		showAlert("项目说明超过500字！");
		return false;
	}
	
	if (je != "" && !checkMoney2(je)) {
		showAlert("[金额]格式不符！");
		 return false;
	}

	var url = "xszz_xmwh.do?method=xmwhZj&type=save";
	ajaxSubFormWithFun("form1", url, function(data) {
		if(data["success"] != undefined && data["success"] == 'false'){
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

jQuery(function() {
	onShow();
});

function onShow() {
	jQuery("input:radio[name=jesfxssq]").change(function() {//修改金额是否学生申请
		changeJesfxssq(jQuery(this).val());
	});
}
function changeJesfxssq(value){
	if(value=='1'){
		jQuery('#jemc').text('金额上限');
	}else{
		jQuery('#jemc').text('固定金额');
	}
}