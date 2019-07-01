var gndm = "xsxx_query";// ¹¦ÄÜ´úÂë
jQuery(function() {
	onShow();
});

function onShow() {
	var url = "xsxx_xsxxgl.do?method=xsxxglCk&type=query";
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
			
			tscl();
		}
	});
}

function tscl(){
	var tableName = "zdybdcon_table_xsxx_query_xsxx_jbxx";
	var shzhThName = "zdybdcon_th_sfzh";
	var _thSfzh = jQuery("#"+tableName+" th[name='"+shzhThName+"']");
	_thSfzh.html("&nbsp;");
	_thSfzh.next("td").html("&nbsp;");
}