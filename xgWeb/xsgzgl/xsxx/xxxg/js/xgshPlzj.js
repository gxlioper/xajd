jQuery(function() {
	onShow();


});

function onShow() {

	var lcid = jQuery("#lcid").val();
	var shid = jQuery("#shid").val();
	var sqid = jQuery("#sqid").val();
	var ywid = jQuery("#ywid").val();
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
	if (jQuery("#shyj").val().length > 200) {
		showAlertDivLayer("���������ܳ���200��");
		return false;
	}

	// �ύ���
	showConfirmDivLayer("��ȷ����˸�������", {
		"okFun" : function() {
			var url = "xsxx_xsxxxgsh.do?method=saveShlc";
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



function save_plsh(shjg,thgw) {
	var shyj = jQuery("#shyj").val();
	if(jQuery("#xxdm").val() == "14008"){
		if (shjg != "1" && shyj == "") {
			showAlert("����д��������");
			return false;
		}
	}else{
		if (shyj == "") {
			showAlert("����д��������");
			return false;
		}
	}
	if (shyj.length > 200) {
		showAlert("���������ܳ���200��");
		return false;
	}
	jQuery("#shjg").val(shjg);
	jQuery("#thgw").val(thgw);
	// �ύ���
	jQuery("button").attr("disabled","disabled");
//	var url = "xsxx_xsxxxgsh.do?method=savePlshlc";
//	ajaxSubFormWithFun("form1", url, function(data) {
//		showAlert(data["message"], {}, {
//			"clkFun" : function() {
//				refershParent();
//			}
//		});
//	});
	
	var api = frameElement.api,W = api.opener;
	W.savePlsh(shjg,thgw,shyj);
	closeDialog();
}

