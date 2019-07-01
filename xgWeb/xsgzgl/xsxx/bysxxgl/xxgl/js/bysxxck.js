var gndm = "xsxx_query";// 功能代码
jQuery(function() {
	onShow();
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
		setXgzd();
	}
	});
}

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

