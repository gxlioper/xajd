var gndm = "xsxx_update";

jQuery(function() {
	onShow();
});

function onShow() {
	
	var url = "xsxx_bysxx_xxxgsq.do?method=SqXg&type=query";
	var xxdm = jQuery("#xxdm").val();
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
			zdybdInit(gndm, data);
			setXgzd();// …Ë÷√–ﬁ∏ƒ◊÷∂Œ
	}
	});
}
/**
 * …Ë÷√–ﬁ∏ƒ◊÷∂Œ
 * 
 * @return
 */
function setXgzd() {
	var sqid = jQuery("#sqid").val();
	var url = "xsxx_bysxx_xxxgsq.do?method=getXgzdList";
	url += "&timestamp=" + new Date().getTime();
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			sqid : sqid
		},
		dataType : "json",
		success : function(data) {
			zdybdReplaceZd(data);
		}
	});
}
//…Í«Î–ﬁ∏ƒ
function sqXg(type) {
	initParam();
	if (!zdybdCheck()) {
		return;
	}
	if (!getXgzdJson()) {
	}
	var sqid = jQuery("#sqid").val();
	var splc = jQuery("#splc").val();
	if (type == 'submit') {
		jQuery("#but_save").hide();
		var url = "xsxx_bysxx_xxxgsq.do?method=SqXgTj" + "&sqid="+sqid+"&splc"+splc;
		ajaxSubFormWithFun("demoform", url, function(data) {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		});
	} else {
		var url = "xsxx_bysxx_xxxgsq.do?method=SqXgBc" + "&sqid="+sqid+"&splc"+splc;
		ajaxSubFormWithFun("demoform", url, function(data) {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		});
	}
}