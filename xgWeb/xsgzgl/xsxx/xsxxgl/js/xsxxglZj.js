var gndm = "xsxx_add";// ���ܴ���
jQuery(function() {
	onShow();
});

function onShow() {
	zdybdInit(gndm);
}

// ������У��������Ϣ
function saveZxsSq() {

	var xh = jQuery("#xh").val();
	if(!zdybdCheck()){
		return;
	}
	// ���ѧ���Ƿ����
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
