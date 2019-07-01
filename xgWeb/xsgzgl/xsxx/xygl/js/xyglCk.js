var gndm = "xygl_query";// ¹¦ÄÜ´úÂë

jQuery(function() {
	onShow();
});


function onShow() {
	var url = "xsxx_xyglxx.do?method=xyglCk&type=query";
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
		}
	});
}

