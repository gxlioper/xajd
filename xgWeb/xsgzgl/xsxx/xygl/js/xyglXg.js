var gndm = "xygl_update";// 功能代码
jQuery(function() {
	onShow();
});

function onShow() {
	var url = "xsxx_xyglxx.do?method=xyglXg&type=query";
	var xxdm = jQuery("#xxdm").val();
	jQuery
			.ajax( {
				type : "post",
				async : false,
				url : url,
				data : {
					xh : jQuery("#xh").val()
				},
				dataType : "json",
				success : function(data) {
					var xm = data.xm;
					jQuery("#xmView").html(xm);
					zdybdInit(gndm, data);
				}
			});
}

function saveForm() {
	if (!zdybdCheck()) {
		return;
	}

	var sfbt = jQuery('#zpsfbt').val();
	var sfcz = jQuery('#zpsfcz').val();
	if (sfbt == "y" && "false" == sfcz) {
		alertError("请先上传一张照片！")
		return false;
	}

	var url = "xsxx_xyglxx.do?method=xyglXg&type=update";
	ajaxSubFormWithFun("form1", url, function(data) {
		if (data["success"] != undefined && !data["success"]) {
			showAlert(data["message"]);
		} else {
			showAlert(data["message"], {}, {
				"clkFun" : function(tag) {
					if (tag == "ok") {
						refershParent();
					}
				}
			});
		}
	});

}