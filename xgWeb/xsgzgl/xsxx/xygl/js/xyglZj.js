var gndm = "xygl_add";// ���ܴ���
jQuery(function() {
	onShow();
});

function onShow() {
	zdybdInit(gndm);
}

// ������У��������Ϣ
function saveXygl() {

	var xh = jQuery("#xh").val();
	if(!zdybdCheck()){
		return;
	}
	// ���ѧ���Ƿ����
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



//���ѧ���Ƿ������(У�ѹ���)
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
				showAlert("ѧ�ţ�'" + xh + "'�Ѿ����ڣ����������룡");
				// jQuery('#xh').focus();
	}
}
	});
	return flag;
}



function showZpscZjDiv1() {
	var xh = jQuery("#xh").val();
	if (xh == "") {
		alertError("������дѧ�ţ�");
	} else {
		
		tipsWindown("ϵͳ��ʾ", "id:addPic", "380", "130", "true", "", "true",
					"id");
	}
}
