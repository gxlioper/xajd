var gndm = "xsxx_update";// ���ܴ���
jQuery(function() {
	onShow();

	var lcid = jQuery("#lcid").val();
	var shid = jQuery("#shid").val();
	var sqid = jQuery("#sqid").val();
	var ywid = jQuery("#ywid").val();
	jQuery("#shlccx").load(
			"comm_spl.do?method=lccx&sqid=" + ywid + "&tt="
					+ new Date().getTime());
	jQuery("#shjgSpan").load(
			"comm_spl.do?method=shth&lcid=" + lcid + "&shid=" + shid);

});

function onShow() {
	var url = "xsxx_bysxx_xxgl.do?method=bysXxCk&type=query";
	jQuery.ajax( {
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
			var otherParams = {};
			otherParams.xs = true;// ȫ��ʾ״̬
		zdybdInit(gndm, data, otherParams);

		setXgzd();// �����޸��ֶ�
	}
	});
}

/**
 * �����޸��ֶ�
 * 
 * @return
 */
function setXgzd() {
	var sqid = jQuery("#ywid").val();

	var url = "xsxx_xsxxxg.do?method=getXgzdList";
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			sqid : sqid
		},
		dataType : "json",
		success : function(data) {
			zdybdXgzd(data);
		}
	});
}

/**
 * ��˱���
 * 
 * @return
 */
function save_sh() {
	if (jQuery("#shyj").val() == "") {
		showAlertDivLayer("����д��������");
		return false;
	}
	if (jQuery("#shjg").val() == '1' && (jQuery("#shyj").val().length < 100 || jQuery("#shyj").val().length > 200)) {
		showAlertDivLayer("������������100-200�ַ�Χ�ڣ�");
		return false;
	}
	if (jQuery("#shjg").val() == '1' && jQuery("#shyj").val().length > 500) {
		showAlertDivLayer("���������ܳ���500��");
		return false;
	}
	if (jQuery("#shyj").val().length > 500) {
		showAlertDivLayer("���������ܳ���500��");
		return false;
	}
	// �ύ���
	showConfirmDivLayer("��ȷ����˸�������", {
		"okFun" : function() {
			var url = "xsxx_bysxx_xxxgsh.do?method=xgSqShBc";
			ajaxSubFormWithFun("form1", url, function(data) {
				showAlertDivLayer(data["message"], {}, {
					"clkFun" : function() {
						refershParent();
					}
				});
			});
		}
	});
}
//������˱���
function save_plsh(shjg) {
	if (jQuery("#shyj").val() == "") {
		showAlert("����д��������");
		return false;
	}
	if (jQuery("#shyj").val().length > 500) {
		showAlert("���������ܳ���500��");
		return false;
	}
	jQuery("#shjg").val(shjg);
	// �ύ���

	jQuery("button").attr("disabled","disabled");
	var url = "xsxx_bysxx_xxxgsh.do?method=savePlSh";
	ajaxSubFormWithFun("form1", url, function(data) {
		showAlert(data["message"], {}, {
			"clkFun" : function() {
				refershParent();
			}
		});
	});

}
