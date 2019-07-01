//显示上传相片
function showZpscDiv() {

	var xh = jQuery("#xh").val();

	if (xh == "") {
		alertError("请先填写学号！");
	} else {
		tipsWindown("系统提示", "id:addPic", "380", "130", "true", "", "true", "id");
	}
}

//显示上传高考相片
function showgkZpscDiv() {

	var xh = jQuery("#xh").val();

	if (xh == "") {
		alertError("请先填写学号！");
	} else {
		tipsWindown("系统提示", "id:addGkPic", "380", "130", "true", "", "true", "id");
	}
}

// 显示上传相片，增加操作
function showZpscZjDiv() {
	var xh = jQuery("#xh").val();
	if (xh == "") {
		alertError("请先填写学号！");
	} else {
		if (checkXhisExists(xh)) {
			tipsWindown("系统提示", "id:addPic", "380", "130", "true", "", "true",
					"id");
		}
	}
}

//显示上传高考相片，增加操作
function showgkZpscZjDiv() {
	var xh = jQuery("#xh").val();
	if (xh == "") {
		alertError("请先填写学号！");
	} else {
		if (checkXhisExists(xh)) {
			tipsWindown("系统提示", "id:addGkPic", "380", "130", "true", "",
					"true", "id");
		}
	}
}

// 照片上传
function uploadStuPic() {

	jQuery.ajaxSetup( {
		async : false,
		dataType : 'text'
	});

	var xh = jQuery("#xh").val();

	jQuery.ajaxFileUpload( {
		url : 'general_xsxx_zxxs_ajax.do?method=uploadStuPic&xh=' + xh,// 服务器端程序
		secureuri : false,
		fileElementId : 'stuPic',// input框的ID
		success : function(data, type) {
			if (type == 'success') {
				jQuery("#xszp").attr(
						"src",
						"xsxx_xsgl.do?method=showPhoto&xh=" + xh + "&tt="
								+ new Date());
				jQuery('#zpsfcz').attr("value", "true");
				jQuery('#sczp').attr("value", "1");
				alertInfo(data);
			}
		}
	});
}

// 高考照片上传
function uploadStuGkPic() {
	jQuery.ajaxSetup( {
		async : false,
		dataType : 'text'
	});

	var xh = jQuery("#xh").val();

	jQuery.ajaxFileUpload( {
		url : 'general_xsxx_zxxs_ajax.do?method=uploadStuGkPic&xh=' + xh,// 服务器端程序
		secureuri : false,
		fileElementId : 'stuGkPic',// input框的ID
		success : function(data, type) {
			if (type == 'success') {
				jQuery("#xsgkzp").attr(
						"src",
						"xsxx_xsgl.do?method=showGkPhoto&xh=" + xh + "&tt="
								+ new Date());
				jQuery('#zpsfcz').attr("value", "true");
				alertInfo(data);
			}
		}
	});
}

// 检查学号是否存在
function checkXhisExists(xh) {
	var flag = true;
	var url = "xsxx_tygl.do?method=jcXhsfcz";
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


