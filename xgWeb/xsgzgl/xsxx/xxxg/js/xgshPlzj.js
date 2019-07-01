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
 * 审核保存
 * 
 * @return
 */
function save_sh() {
	if (jQuery("#shyj").val() == "") {
		showAlertDivLayer("请填写审核意见！");
		return false;
	}
	if (jQuery("#shyj").val().length > 200) {
		showAlertDivLayer("审核意见不能超过200字");
		return false;
	}

	// 提交审核
	showConfirmDivLayer("您确定审核该申请吗？", {
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
			showAlert("请填写审核意见！");
			return false;
		}
	}else{
		if (shyj == "") {
			showAlert("请填写审核意见！");
			return false;
		}
	}
	if (shyj.length > 200) {
		showAlert("审核意见不能超过200字");
		return false;
	}
	jQuery("#shjg").val(shjg);
	jQuery("#thgw").val(thgw);
	// 提交审核
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

