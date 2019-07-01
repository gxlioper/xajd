jQuery(function () {
	var xxdm = jQuery("#xxdm").val();
	if (xxdm == "12703") {
		var xh = document.getElementById("userName").value;
		xfhjglService.checkXsxfsfhj(xh, function (data) {
			var sq = "";
			if (data != null && data != "" && data != "wqf" && data != "yhj" && data != "shz") {
				if (data == "ksq") {
					sq = "\u70b9\u51fb\u7533\u8bf7\u5b66\u8d39\u7f13\u4ea4\uff01";
				}
				var msg1 = new class_message("", 200, 120, "\u6d88\u606f\u63d0\u793a\uff1a", "\u540c\u5b66\u4f60\u597d\uff0c\u672c\u5b66\u5e74\u4f60\u8fd8\u6709\u90e8\u5206\u5b66\u8d39\u5c1a\u672a\u4ea4\u6e05\uff0c\u8bf7\u53ca\u65f6\u4ea4\u6e05\u6216\u7f13\u4ea4\uff01", sq, "true", "/xgxt/rcsw_nthy_xfhjsq.do");
				msg1.rect(null, null, null, screen.height - 50);
				msg1.speed = 60;
				msg1.step = 10;
				msg1.show();
			}
		});
	}
});

