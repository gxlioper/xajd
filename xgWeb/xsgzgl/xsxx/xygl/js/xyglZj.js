var gndm = "xygl_add";// 功能代码
jQuery(function() {
	onShow();
});

function onShow() {
	zdybdInit(gndm);
}

// 保存在校生申请信息
function saveXygl() {

	var xh = jQuery("#xh").val();
	if(!zdybdCheck()){
		return;
	}
	// 检查学号是否存在
	var flag = checkXhisExistsXYGL(xh);
	if (flag) {
		// return false;
		var url = "xsxx_xyglxx.do?method=xyglZj&type=save";
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



//检查学号是否存在与(校友管理)
function checkXhisExistsXYGL(xh) {
	var flag = true;
	var url = "xsxx_xyglxx.do?method=jcXhsfcz";
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			xh : xh
		},
		dataType : "json",
		success : function(data) {
			if (data == '1') {
				flag = false;
				showAlert("学号：'" + xh + "'已经存在，请重新输入！");
				// jQuery('#xh').focus();
	}
}
	});
	return flag;
}



function showZpscZjDiv1() {
	var xh = jQuery("#xh").val();
	if (xh == "") {
		alertError("请先填写学号！");
	} else {
		
		tipsWindown("系统提示", "id:addPic", "380", "130", "true", "", "true",
					"id");
	}
}
