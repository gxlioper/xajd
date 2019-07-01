var gndm = "xsxx_add";// 功能代码
jQuery(function() {
	onShow();
});

function onShow() {
	zdybdInit(gndm);
}

// 保存在校生申请信息
function saveZxsSq() {

	var xh = jQuery("#xh").val();
	if(!zdybdCheck()){
		return;
	}
	// 检查学号是否存在
	var flag = checkXhisExists(xh);
	if (flag) {
		// return false;
		var url = "xsxx_xsxxgl.do?method=xsxxglZj&type=save";
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
}
