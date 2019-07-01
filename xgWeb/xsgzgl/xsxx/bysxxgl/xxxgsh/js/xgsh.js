var gndm = "xsxx_update";// 功能代码
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
			otherParams.xs = true;// 全显示状态
		zdybdInit(gndm, data, otherParams);

		setXgzd();// 设置修改字段
	}
	});
}

/**
 * 设置修改字段
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
 * 审核保存
 * 
 * @return
 */
function save_sh() {
	if (jQuery("#shyj").val() == "") {
		showAlertDivLayer("请填写审核意见！");
		return false;
	}
	if (jQuery("#shjg").val() == '1' && (jQuery("#shyj").val().length < 100 || jQuery("#shyj").val().length > 200)) {
		showAlertDivLayer("审核意见控制在100-200字范围内！");
		return false;
	}
	if (jQuery("#shjg").val() == '1' && jQuery("#shyj").val().length > 500) {
		showAlertDivLayer("审核意见不能超过500字");
		return false;
	}
	if (jQuery("#shyj").val().length > 500) {
		showAlertDivLayer("审核意见不能超过500字");
		return false;
	}
	// 提交审核
	showConfirmDivLayer("您确定审核该申请吗？", {
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
//批量审核保存
function save_plsh(shjg) {
	if (jQuery("#shyj").val() == "") {
		showAlert("请填写审核意见！");
		return false;
	}
	if (jQuery("#shyj").val().length > 500) {
		showAlert("审核意见不能超过500字");
		return false;
	}
	jQuery("#shjg").val(shjg);
	// 提交审核

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
