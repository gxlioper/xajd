//��ʾ�ϴ���Ƭ
function showZpscDiv() {

	var xh = jQuery("#xh").val();

	if (xh == "") {
		alertError("������дѧ�ţ�");
	} else {
		tipsWindown("ϵͳ��ʾ", "id:addPic", "380", "130", "true", "", "true", "id");
	}
}

//��ʾ�ϴ��߿���Ƭ
function showgkZpscDiv() {

	var xh = jQuery("#xh").val();

	if (xh == "") {
		alertError("������дѧ�ţ�");
	} else {
		tipsWindown("ϵͳ��ʾ", "id:addGkPic", "380", "130", "true", "", "true", "id");
	}
}

// ��ʾ�ϴ���Ƭ�����Ӳ���
function showZpscZjDiv() {
	var xh = jQuery("#xh").val();
	if (xh == "") {
		alertError("������дѧ�ţ�");
	} else {
		if (checkXhisExists(xh)) {
			tipsWindown("ϵͳ��ʾ", "id:addPic", "380", "130", "true", "", "true",
					"id");
		}
	}
}

//��ʾ�ϴ��߿���Ƭ�����Ӳ���
function showgkZpscZjDiv() {
	var xh = jQuery("#xh").val();
	if (xh == "") {
		alertError("������дѧ�ţ�");
	} else {
		if (checkXhisExists(xh)) {
			tipsWindown("ϵͳ��ʾ", "id:addGkPic", "380", "130", "true", "",
					"true", "id");
		}
	}
}

// ��Ƭ�ϴ�
function uploadStuPic() {

	jQuery.ajaxSetup( {
		async : false,
		dataType : 'text'
	});

	var xh = jQuery("#xh").val();

	jQuery.ajaxFileUpload( {
		url : 'general_xsxx_zxxs_ajax.do?method=uploadStuPic&xh=' + xh,// �������˳���
		secureuri : false,
		fileElementId : 'stuPic',// input���ID
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

// �߿���Ƭ�ϴ�
function uploadStuGkPic() {
	jQuery.ajaxSetup( {
		async : false,
		dataType : 'text'
	});

	var xh = jQuery("#xh").val();

	jQuery.ajaxFileUpload( {
		url : 'general_xsxx_zxxs_ajax.do?method=uploadStuGkPic&xh=' + xh,// �������˳���
		secureuri : false,
		fileElementId : 'stuGkPic',// input���ID
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

// ���ѧ���Ƿ����
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
				showAlert("ѧ�ţ�'" + xh + "'�Ѿ����ڣ����������룡");
				// jQuery('#xh').focus();
	}
}
	});
	return flag;
}


